package org.cyber.utbot.api.utils.additions.pathSelector

import javassist.ClassPool
import org.cyber.utbot.api.taint.ProguardExecutor
import org.cyber.utbot.api.taint.mapping.StatesContainer
import org.cyber.utbot.api.taint.mapping.TraceMapper
import org.utbot.engine.InterProceduralUnitGraph
import org.utbot.engine.jimpleBody
import org.utbot.engine.selectors.BasePathSelector
import org.utbot.engine.selectors.strategies.ChoosingStrategy
import org.utbot.engine.selectors.strategies.StoppingStrategy
import org.utbot.engine.state.ExecutionState
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintMemoryLocationBamCpaRun
import soot.jimple.JimpleBody
import soot.jimple.Stmt
import kotlin.collections.ArrayDeque


class CyberSelector(
    choosingStrategy: ChoosingStrategy,
    stoppingStrategy: StoppingStrategy,
    jarName: String,
    private val graph: InterProceduralUnitGraph,
    private val cyberDefaultSelector: Boolean
) : BasePathSelector(choosingStrategy, stoppingStrategy) {

    var defaultSelection = false
    private val executionStates = mutableListOf<ExecutionState>()
    private var currentIndex = -1
    private val proguardExecutor: ProguardExecutor = ProguardExecutor(jarName)
    private val traceMapper = TraceMapper()
    private val classPool: ClassPool = ClassPool.getDefault()
    private var traceFound = false
    private val defaultSelector = CyberDefaultSelector(graph)
    private val container = StatesContainer()
    private var peekTraceFound = false
    // stack of inner calls
    private var innerCallDestination: ArrayDeque<Pair<Stmt?, JimpleBody?>> = ArrayDeque() // parent to method under invocation
    override val name = "CyberTaintSelector"

    init {
        classPool.insertClassPath(jarName)
        classPool.insertClassPath("C:/Users/lesya/uni2/UTBotJava/cyber-utbot-exploit-base/build/classes/java/main")
    }

    fun traceFound() = traceFound

    override fun offerImpl(state: ExecutionState) {
//        println("offer! ${state.stmt}")
        executionStates += state
        currentIndex = -1
    }

    /**
     * Takes each execution state, takes first that has a trace mapping (this one will be returned).
     * If no state leads to a trace returns a state from the default selector.
     */
    override fun peekImpl(): ExecutionState? {
//        println("peek!")
        if (defaultSelection || cyberDefaultSelector) {
            val (state, idx) = defaultSelector.peekImpl(executionStates, currentIndex)
            currentIndex = idx
            return state
        }
        if (executionStates.size == 0) {
            return null
        }
        executionStates.forEachIndexed { i, state ->
            if (mapState(state)) {
                peekTraceFound = true
                currentIndex = i
//                println("peeked1 ${state.stmt}, }, traceFound = $traceFound, currentIndex = $currentIndex")
                return state
            }
        }
        peekTraceFound = false
        // random state peek
        val (state, idx) = defaultSelector.peekImpl(executionStates, currentIndex)
        currentIndex = idx
//        println("peeked2 ${state?.stmt}, }, traceFound = $traceFound, currentIndex = $currentIndex")
        return state
    }

    /**
     * If previous peek call found a mapping state - returns that one.
     * Else, takes each execution state, takes first that has a trace mapping, removes it from states and returns it.
     * If no state leads to a trace returns a state from the default selector.
     */
    override fun pollImpl(): ExecutionState? {
        if (defaultSelection || cyberDefaultSelector) {
            val toRet =  defaultSelector.pollImpl(executionStates, currentIndex)
            currentIndex = -1
            return toRet
        }
        if (executionStates.size == 0) {
//            println("polled0, }, traceFound = $traceFound, currentIndex = $currentIndex")
            return null
        }
        if (currentIndex == -1) {
            executionStates.forEachIndexed { i, state ->
                if (state.stmt.javaSourceStartLineNumber == -1) {
                    executionStates.removeAt(i)
                    container[state.lastEdge?.src]?.let {
                        container.states.putIfAbsent(
                            state.stmt,
                            it
                        )
                    }
//                    println("polled1 ${state.stmt},label = ${state.label}, }, traceFound = $traceFound, currentIndex = $currentIndex")
                    return state
                }
                if (mapState(state)) {
                    executionStates.removeAt(i)
                    currentIndex = -1
//                    println("polled2 ${state.stmt},label = ${state.label}, }, traceFound = $traceFound, currentIndex = $currentIndex")
                    return state
                }
            }
        }
        if (traceFound && currentIndex == -1) {
            (choosingStrategy as CyberStrategy).unmatchedStates.addAll(executionStates)
        }
        if (!peekTraceFound) {
            resetTrace()
        }
        val state = defaultSelector.pollImpl(executionStates, currentIndex)
        currentIndex = -1
//        println("polled3 ${state?.stmt},label = ${state?.label}, }, traceFound = $traceFound, currentIndex = $currentIndex")
        executionStates.remove(state)
        return state
    }

    override fun removeImpl(state: ExecutionState): Boolean {
        currentIndex = -1
        return executionStates.remove(state)
    }

    override fun close() =
        executionStates.forEach {
            it.close()
        }

    override fun isEmpty() = executionStates.isEmpty()

    /**
     * This method is called whenever symbolic engine starts analysing a new method.
     * Sets used variables to their initial values.
     */
    fun onNextIteration(initState: ExecutionState): JvmTaintMemoryLocationBamCpaRun {
        val jimpleBody = graph.method(initState.stmt).jimpleBody()
//         println(jimpleBody)
        println("IN ${jimpleBody.method.name}")
        resetTrace()
        container.states.clear()
        innerCallDestination.clear()
        proguardExecutor.execute(jimpleBody)
        // todo check for sources and sinks
        defaultSelection = proguardExecutor.traces.isEmpty()
        return proguardExecutor.cpaRun
    }

    private fun mapState(state: ExecutionState): Boolean {
        val jimpleBody = graph.method(state.stmt).jimpleBody()
        if (innerCallDestination.isNotEmpty() && state.stmt == innerCallDestination.first().first) {
            innerCallDestination.removeFirst() // returned from the inner call
            if (proguardExecutor.sinks.any { it.toString().contains(jimpleBody.method.name) }) {
                return true // TODO(state that we finished trace traverse)
            }
        }
        if (container[state.stmt]?.isNotEmpty() == true) {
            return true
        }
        val declaringClass = jimpleBody.method.declaringClass.name
        if (isInternalMethod(declaringClass, jimpleBody)) {
            return true
        }
        val ancestor = state.lastEdge?.src
        val cf = classPool.get(declaringClass).classFile
        val traces = if (container[ancestor] == null || !traceFound) proguardExecutor.traces else container[ancestor]
        traces?.forEach {
            container.states.putIfAbsent(state.stmt, mutableSetOf())
            if (traceMapper.map(it, state.stmt, cf)) {
                container[state.stmt]?.add(it)
            }
        }
        if (container[state.stmt]?.isNotEmpty() == true) {
//            if (state.stmt.toString().contains("Example")) println("mapped successfully1 ${state.stmt}, tracefound = $traceFound")
            traceFound = true
            return true
        } else if (isInvocation(ancestor.toString(), jimpleBody)) {
            innerCallDestination.addFirst(ancestor to jimpleBody)
            container[ancestor]?.let { container[state.stmt]?.addAll(it) } // plus putifabsent
//            if (state.stmt.toString().contains("Example"))println("mapped successfully2 ${state.stmt}, parent = $ancestor")
            return true
        } else if (innerCallDestination.isNotEmpty() && innerCallDestination.first().first != null
            && jimpleBody.method.name.equals(innerCallDestination.first().second?.method?.name)
        ) {
            container[ancestor]?.let { container[state.stmt]?.addAll(it) }
//            if (state.stmt.toString().contains("Example"))println("mapped successfully3 ${state.stmt}, parent = $ancestor, method name = ${jimpleBody.method.name}")
            return true
        }
//        if (state.stmt.toString().contains("Example")) println("map failed ${state.stmt}")
        return false
    }

    private fun resetTrace() {
        traceFound = false
    }

    private fun isInternalMethod(clazz: String, jimpleBody: JimpleBody) =
        internalClasses.any { clazz.contains(it) } || internalMethods.any { jimpleBody.method.name.contains(it) }

    private fun isInvocation(ancestor: String, jimpleBody: JimpleBody) =
        (ancestor.contains("virtualinvoke") ||
                ancestor.contains("staticinvoke") ||
                ancestor.contains("specialinvoke") ||
                ancestor.contains("interfaceinvoke") ||
                ancestor.contains("dynamicinvoke"))
                && ancestor.contains(jimpleBody.method.name)

    private val internalClasses: MutableList<String> =
        mutableListOf(
            "org.cyber.utils",
            "org.cyber.exploitBase",
            "utbot"
        )

    private val internalMethods: MutableList<String> =
        mutableListOf(
            "internalCheck"
        )

}
