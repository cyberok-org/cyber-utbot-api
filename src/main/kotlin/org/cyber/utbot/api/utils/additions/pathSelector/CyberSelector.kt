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
import org.utbot.engine.state.StateLabel
import soot.jimple.JimpleBody
import soot.jimple.Stmt
import java.util.*
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
    private val statesContainer = StatesContainer()
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
                (choosingStrategy as CyberStrategy).drop = false
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
                    statesContainer.states[state.lastEdge?.src]?.let {
                        statesContainer.states.putIfAbsent(
                            state.stmt,
                            it
                        )
                    }
                    if (state.label == StateLabel.TERMINAL && !traceFound) (choosingStrategy as CyberStrategy).drop =
                        true
                    // println("polled1 ${state.stmt},label = ${state.label}, drop = ${(choosingStrategy as CyberStrategy).drop}, traceFound = $traceFound, currentIndex = $currentIndex")
                    return state
                }
                if (mapState(state)) {
                    executionStates.removeAt(i)
                    currentIndex = -1
                    (choosingStrategy as CyberStrategy).drop = false
                    if (state.label == StateLabel.TERMINAL && !traceFound) (choosingStrategy as CyberStrategy).drop =
                        true
                    // println("polled2 ${state.stmt},label = ${state.label}, drop = ${(choosingStrategy as CyberStrategy).drop}, traceFound = $traceFound, currentIndex = $currentIndex")
                    return state
                }
            }
        }
        if (traceFound && currentIndex == -1) {
            (choosingStrategy as CyberStrategy).drop = true
        }
        if (!peekTraceFound) traceFound = false
        val state = defaultSelector.pollImpl(executionStates, currentIndex)
        currentIndex = -1
        if (state.label == StateLabel.TERMINAL && !traceFound) (choosingStrategy as CyberStrategy).drop = true
        // println("polled3 ${state.stmt},label = ${state.label}, drop = ${(choosingStrategy as CyberStrategy).drop}, traceFound = $traceFound, currentIndex = $currentIndex")
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
        innerCallDestination.clear()
    }

    private fun mapState(state: ExecutionState): Boolean {
        val jimpleBody = graph.method(state.stmt).jimpleBody()
        if (innerCallDestination.isNotEmpty() && state.stmt == innerCallDestination.first().first) {
            // println("been here")
            innerCallDestination.removeFirst()
            if (proguardExecutor.sinks.any { it.toString().contains(jimpleBody.method.name) }) {
                // println("returning")
                return true
            }
        }
        if (statesContainer.states[state.stmt]?.isNotEmpty() == true) {
            // println("mapped as was ${state.stmt}")
            return true
        }
        // println("trying to map ${state.stmt}, method ${jimpleBody.method.name}")
        if (nextIterationStarted) { // set ProguardExecutor's head method to the one under analysis
            // println(jimpleBody)
            // println("NOW IN ${jimpleBody.method.name}")
            nextIterationStarted = false
            proguardExecutor.setHeadMethodSignature(jimpleBody.method)
            proguardExecutor.execute()
            if (proguardExecutor.traces.isEmpty()) (choosingStrategy as CyberStrategy).drop = true
        }
        val declaringClass = jimpleBody.method.declaringClass.name
        if (declaringClass.contains("org.cyber.utils") || jimpleBody.method.name.contains("internalCheck")
            || declaringClass.contains("org.cyber.exploitBase")
        ) {
            // println("костыль ${state.stmt}")
            return true
        } // пока костыль
        // todo: this will only work in a single class, add inter-class analysis
        val cf = classPool.get(declaringClass).classFile
        val parentSrc = state.lastEdge?.src
        val traces =
            if (statesContainer.states[parentSrc] == null || !traceFound) proguardExecutor.traces else statesContainer.states[parentSrc]
        traces?.forEach {
            statesContainer.states.putIfAbsent(state.stmt, mutableSetOf())
            if (traceMapper.map(it, state.stmt, cf)) {
                statesContainer.states[state.stmt]?.add(it)
            }
        }
        if (statesContainer.states[state.stmt]?.isNotEmpty() == true) {
            // println("mapped successfully1 ${state.stmt}")
            traceFound = true
            return true
        } else if ((parentSrc.toString().contains("virtualinvoke") ||
                    parentSrc.toString().contains("staticinvoke") ||
                    parentSrc.toString().contains("specialinvoke")) // add the rest
            && parentSrc.toString().contains(jimpleBody.method.name)
        ) {
            innerCallDestination.addFirst(parentSrc to jimpleBody)
            statesContainer.states[parentSrc]?.let { statesContainer.states[state.stmt]?.addAll(it) } // plus putifabsent
            // println("mapped successfully2 ${state.stmt}, parent = $parentSrc")
            return true
        } else if (innerCallDestination.isNotEmpty() && innerCallDestination.first().first != null
            && jimpleBody.method.name.equals(innerCallDestination.first().second?.method?.name)
        ) {
            statesContainer.states[parentSrc]?.let { statesContainer.states[state.stmt]?.addAll(it) }
            // println("mapped successfully3 ${state.stmt}, parent = $parentSrc, method name = ${jimpleBody.method.name}")
            return true
        }
        // println("map failed ${state.stmt}")
        return false
    }
}
