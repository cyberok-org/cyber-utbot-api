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
import soot.jimple.Stmt


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
    private val statesContainer = StatesContainer()
    private var nextIterationStarted = false
    private var innerCallDestination: Stmt? = null

    override val name = "CyberTaintSelector"

    init {
        classPool.insertClassPath("C:\\Users\\lesya\\uni2\\UTBotJava\\cyber-utbot-api\\src\\main\\java\\org\\testcases\\jars\\TempJar.jar")
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
        executionStates.forEachIndexed { i, stmt ->
            if (mapState(stmt)) {
                currentIndex = i
                (choosingStrategy as CyberStrategy).drop = false
                return stmt
            }
        }
        if (traceFound && executionStates.size > 1) {
            (choosingStrategy as CyberStrategy).drop = true
        }
        // random state peek
        val (state, idx) = defaultSelector.peekImpl(executionStates, currentIndex)
        currentIndex = idx
        return state
    }

    override fun pollImpl(): ExecutionState? {
        if (executionStates.size == 0) {
            return null
        }
        // take each execution state, take first that has a trace mapping (this one will be returned)
        // remove it from states. if no state leads to a trace return null
        if (currentIndex == -1) {
            executionStates.forEachIndexed { i, state ->
                if (state.stmt.javaSourceStartLineNumber == -1) {
                    executionStates.removeAt(i)
                    statesContainer.states[state.lastEdge?.src]?.let { statesContainer.states.putIfAbsent(state.stmt, it) }
                    return state
                }
                if (mapState(state)) {
                    executionStates.removeAt(i)
                    currentIndex = -1
                    (choosingStrategy as CyberStrategy).drop = false
                    return state
                }
            }
        }
        if (traceFound && executionStates.size > 1 && currentIndex == -1) {
            (choosingStrategy as CyberStrategy).drop = true
        }
        val state = defaultSelector.pollImpl(executionStates, currentIndex)
        currentIndex = -1
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
        statesContainer.states.clear()
        nextIterationStarted = true
    }

    private fun mapState(state: ExecutionState): Boolean {
        if (statesContainer.states[state.stmt]?.isNotEmpty() == true) return true
        val jimpleBody = graph.method(state.stmt).jimpleBody()
        if (nextIterationStarted) { // set ProguardExecutor's head method to the one under analysis
            println("NOW IN ${jimpleBody.method.name}")
            nextIterationStarted = false
            proguardExecutor.setHeadMethodSignature(jimpleBody.method)
            proguardExecutor.execute()
            if (proguardExecutor.traces.isEmpty()) (choosingStrategy as CyberStrategy).drop = true
        }
        println(state.stmt)
        val declaringClass = jimpleBody.method.declaringClass.name
        if (declaringClass.contains("org.cyber.utils")) return true // пока костыль
        // todo: this will only work in a single class, add inter-class analysis
        val cf = classPool.get(declaringClass).classFile
        val parentSrc = state.lastEdge?.src
        val traces =
            if (statesContainer.states[parentSrc] == null) proguardExecutor.traces else statesContainer.states[parentSrc]
        traces?.forEach {
            statesContainer.states.putIfAbsent(state.stmt, mutableSetOf())
            if (traceMapper.map(it, state.stmt, cf)) {
                statesContainer.states[state.stmt]?.add(it)
            }
        }
        if (state.stmt == innerCallDestination) {
            println("here3")
            innerCallDestination = null
        }
        if (statesContainer.states[state.stmt]?.isNotEmpty() == true) {
            traceFound = true
            return true
        } else if ((parentSrc.toString().contains("virtualinvoke") ||
                    parentSrc.toString().contains("staticinvoke"))
            && parentSrc.toString().contains(jimpleBody.method.name)
        ) {
            println("here1")
            innerCallDestination = parentSrc
            statesContainer.states[parentSrc]?.let { statesContainer.states[state.stmt]?.addAll(it) } // plus putifabsent
            return true
        } else if (innerCallDestination != null) {
            println("here2")
            statesContainer.states[parentSrc]?.let { statesContainer.states[state.stmt]?.addAll(it) }
            return true
        }
        return false
    }
}
