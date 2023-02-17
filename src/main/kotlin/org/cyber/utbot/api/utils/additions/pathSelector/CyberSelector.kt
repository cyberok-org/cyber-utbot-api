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
import soot.jimple.JimpleBody
import soot.jimple.Stmt
import kotlin.collections.ArrayDeque


class CyberSelector(
    choosingStrategy: ChoosingStrategy,
    stoppingStrategy: StoppingStrategy,
    jarName: String,
    private val graph: InterProceduralUnitGraph
) : BasePathSelector(choosingStrategy, stoppingStrategy) {

    private val executionStates = mutableListOf<ExecutionState>()
    private var currentIndex = -1
    private val proguardExecutor: ProguardExecutor = ProguardExecutor(jarName)
    private val traceMapper = TraceMapper()
    private val classPool: ClassPool = ClassPool.getDefault()
    private var traceFound = false
    private val defaultSelector = CyberDefaultSelector()
    private val container = StatesContainer()
    private var nextIterationStarted = false
    private var peekTraceFound = false

    // stack of inner calls
    private var innerCallDestination: ArrayDeque<Pair<Stmt?, JimpleBody?>> =
        ArrayDeque() // parent to method under invocation

    override val name = "CyberTaintSelector"

    init {
        classPool.insertClassPath(jarName)
    }

    fun traceFound() = traceFound

    fun resetTrace() {
        traceFound = false
    }

    override fun offerImpl(state: ExecutionState) {
        executionStates += state
        currentIndex = -1
    }

    override fun peekImpl(): ExecutionState? {
        if (executionStates.size == 0) {
            return null
        }
        // take each execution state, take first that has a trace mapping (this one will be returned)
        // remove it from states. if no state leads to a trace return null
        executionStates.forEachIndexed { i, state ->
            if (mapState(state)) {
                peekTraceFound = true
                currentIndex = i
//                (choosingStrategy as CyberStrategy).drop = false
                // println("peeked1 ${state.stmt}, drop = ${(choosingStrategy as CyberStrategy).drop}, traceFound = $traceFound, currentIndex = $currentIndex")
                return state
            }
        }
        peekTraceFound = false
//        if (traceFound) {
//            (choosingStrategy as CyberStrategy).drop = true
//        }
        // random state peek
        val (state, idx) = defaultSelector.peekImpl(executionStates, currentIndex)
        currentIndex = idx
        // println("peeked2 ${state.stmt}, drop = ${(choosingStrategy as CyberStrategy).drop}, traceFound = $traceFound, currentIndex = $currentIndex")
        return state
    }

    override fun pollImpl(): ExecutionState? {
        if (executionStates.size == 0) {
//            // println("polled0, drop = ${(choosingStrategy as CyberStrategy).drop}, traceFound = $traceFound, currentIndex = $currentIndex")
            return null
        }
        // take each execution state, take first that has a trace mapping (this one will be returned)
        // remove it from states. if no state leads to a trace return null
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
//                    if (state.stmt.toString().contains("taint"))println("polled1 ${state.stmt},label = ${state.label}, drop = ${(choosingStrategy as CyberStrategy).drop}, traceFound = $traceFound, currentIndex = $currentIndex")
                    return state
                }
                if (mapState(state)) {
                    executionStates.removeAt(i)
                    currentIndex = -1
//                    (choosingStrategy as CyberStrategy).drop = false
//                    if (state.stmt.toString().contains("taint"))println("polled2 ${state.stmt},label = ${state.label}, drop = ${(choosingStrategy as CyberStrategy).drop}, traceFound = $traceFound, currentIndex = $currentIndex")
                    return state
                }
            }
        }
        if (traceFound && currentIndex == -1) {
            (choosingStrategy as CyberStrategy).unmatchedStates.addAll(executionStates)
//            (choosingStrategy as CyberStrategy).drop = true
        }
        if (!peekTraceFound) {
            traceFound = false
        }
        val state = defaultSelector.pollImpl(executionStates, currentIndex)
        currentIndex = -1
//        if (state.stmt.toString().contains("taint")) println("polled3 ${state.stmt},label = ${state.label}, drop = ${(choosingStrategy as CyberStrategy).drop}, traceFound = $traceFound, currentIndex = $currentIndex")
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

    fun onNextIteration() {
        traceFound = false
        container.states.clear()
        nextIterationStarted = true
        innerCallDestination.clear()
    }

    private fun mapState(state: ExecutionState): Boolean {
        val jimpleBody = graph.method(state.stmt).jimpleBody()
        if (innerCallDestination.isNotEmpty() && state.stmt == innerCallDestination.first().first) {
            // println("been here")
            innerCallDestination.removeFirst()
            if (proguardExecutor.sinks.any { it.toString().contains(jimpleBody.method.name) }) {
                return true // TODO(state that we finished trace traverse)
            }
        }
        if (container[state.stmt]?.isNotEmpty() == true) {
            // println("mapped as was ${state.stmt}")
            return true
        }
        // println("trying to map ${state.stmt}, method ${jimpleBody.method.name}")
        if (nextIterationStarted) { // set ProguardExecutor's head method to the one under analysis
            println(jimpleBody)
            println("NOW IN ${jimpleBody.method.name}")
            nextIterationStarted = false
            proguardExecutor.setHeadMethodSignature(jimpleBody.method)
            proguardExecutor.execute()
            if (proguardExecutor.traces.isEmpty()) {
                (choosingStrategy as CyberStrategy).unmatchedStates.add(state) //drop = true
            }
        }
        val declaringClass = jimpleBody.method.declaringClass.name
        if (isInternalMethod(declaringClass, jimpleBody)) {
            // println("костыль ${state.stmt}")
            return true
        }
        val cf = classPool.get(declaringClass).classFile
        val ancestor = state.lastEdge?.src
        val traces = if (container[ancestor] == null || !traceFound) proguardExecutor.traces else container[ancestor]
        traces?.forEach {
            container.states.putIfAbsent(state.stmt, mutableSetOf())
            if (traceMapper.map(it, state.stmt, cf)) {
                container[state.stmt]?.add(it)
            }
        }
        if (container[state.stmt]?.isNotEmpty() == true) {
//            if (state.stmt.toString().contains("taint")) println("mapped successfully1 ${state.stmt}, tracefound = $traceFound")
            traceFound = true
            return true
        } else if (isInvocation(ancestor.toString(), jimpleBody)) {
            innerCallDestination.addFirst(ancestor to jimpleBody)
            container[ancestor]?.let { container[state.stmt]?.addAll(it) } // plus putifabsent
//            if (state.stmt.toString().contains("taint"))println("mapped successfully2 ${state.stmt}, parent = $ancestor")
            return true
        } else if (innerCallDestination.isNotEmpty() && innerCallDestination.first().first != null
            && jimpleBody.method.name.equals(innerCallDestination.first().second?.method?.name)
        ) {
            container[ancestor]?.let { container[state.stmt]?.addAll(it) }
//            if (state.stmt.toString().contains("taint"))println("mapped successfully3 ${state.stmt}, parent = $ancestor, method name = ${jimpleBody.method.name}")
            return true
        }
//        if (state.stmt.toString().contains("taint")) println("map failed ${state.stmt}")
        return false
    }

    private fun isInternalMethod(clazz: String, jimpleBody: JimpleBody) =
        internalClasses.any { clazz.contains(it) } || internalMethods.any { jimpleBody.method.name.contains(it) }

    private fun isInvocation(ancestor: String, jimpleBody: JimpleBody) =
        (ancestor.contains("virtualinvoke") ||
                ancestor.contains("staticinvoke") ||
                ancestor.contains("specialinvoke") ||
                ancestor.contains("interfaceinvoke") ||
                ancestor.contains("dynamiconvoke"))
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
