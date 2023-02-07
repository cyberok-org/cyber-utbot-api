package org.cyber.utbot.api.utils.additions.pathSelector

import javassist.ClassPool
import org.cyber.utbot.api.taint.ProguardExecutor
import org.cyber.utbot.api.taint.mapping.JimpleInstruction
import org.cyber.utbot.api.taint.mapping.ProguardInstruction
import org.cyber.utbot.api.taint.mapping.TraceMapper
import org.utbot.engine.InterProceduralUnitGraph
import org.utbot.engine.jimpleBody
import org.utbot.engine.selectors.BasePathSelector
import org.utbot.engine.selectors.strategies.ChoosingStrategy
import org.utbot.engine.selectors.strategies.StoppingStrategy
import org.utbot.engine.state.ExecutionState
import kotlin.random.Random


class CyberSelector(
    choosingStrategy: ChoosingStrategy,
    stoppingStrategy: StoppingStrategy,
    seed: Int = 42,
    jarName: String,
    private val graph: InterProceduralUnitGraph
) :
    BasePathSelector(choosingStrategy, stoppingStrategy) {

    private val executionStates = mutableListOf<ExecutionState>()
    private val random = Random(seed)
    private var currentIndex = -1
    private val proguardExecutor: ProguardExecutor = ProguardExecutor(jarName)
    private val traceMapper = TraceMapper()
    private val classPool: ClassPool = ClassPool.getDefault()
    private var traceFound = false

    init {
        classPool.insertClassPath("C:\\Users\\lesya\\uni2\\UTBotJava\\cyber-utbot-api\\src\\main\\java\\org\\testcases\\jars\\TempJar.jar")
        proguardExecutor.execute()
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
            if (mapStmt(stmt)) {
                currentIndex = i
                (choosingStrategy as CyberStrategy).drop = false
                println("PEEK SUCCESS!")
                traceFound = true
                return stmt
            }
        }
        // random state peek
        if (currentIndex == -1) {
            currentIndex = random.nextInt(executionStates.size)
        }
        if (traceFound && executionStates.size > 1) {
            println("PEEK FAILED!")
            (choosingStrategy as CyberStrategy).drop = true
        }
        return executionStates[currentIndex]
    }

    override fun pollImpl(): ExecutionState? {
        if (executionStates.size == 0) {
            return null
        }
        // take each execution state, take first that has a trace mapping (this one will be returned)
        // remove it from states. if no state leads to a trace return null
        executionStates.forEachIndexed { i, stmt ->
            if (mapStmt(stmt)) {
                executionStates.removeAt(i)
                currentIndex = -1
                println("POLL SUCCESS!")
                (choosingStrategy as CyberStrategy).drop = false
                traceFound = true
                return stmt
            }
        }
        if (traceFound && executionStates.size > 1) {
            println("POLL FAILED!")
            (choosingStrategy as CyberStrategy).drop = true
        }
        return randomState()
    }

    private fun randomState(): ExecutionState {
        if (currentIndex == -1) {
            currentIndex = random.nextInt(executionStates.size)
        }
        val state = executionStates[currentIndex]
        executionStates.removeAt(currentIndex)
        currentIndex = -1
        return state
    }

    override fun removeImpl(state: ExecutionState): Boolean {
        currentIndex = -1
        return executionStates.remove(state)
    }

    override fun close() {
        executionStates.forEach {
            it.close()
        }
    }

    override fun isEmpty() =
        executionStates.isEmpty()

    override val name = "CyberSelector"

    private fun mapTraces(state: ExecutionState): MutableMap<ProguardInstruction, JimpleInstruction> {
        val jimpleBody = graph.method(state.stmt).jimpleBody()
        val declaringClass = jimpleBody.method.declaringClass.name
        // todo: this will only work in a single class, add inter-class analysis
        val cf = classPool.get(declaringClass).classFile
        return traceMapper.map(proguardExecutor.traces.random(), jimpleBody.units, cf) // todo map each trace
    }
    private fun mapStmt(state: ExecutionState): Boolean {
        if (state.stmt.javaSourceStartLineNumber == -1) return true
        val jimpleBody = graph.method(state.stmt).jimpleBody()
        val declaringClass = jimpleBody.method.declaringClass.name
        // todo: this will only work in a single class, add inter-class analysis
        val cf = classPool.get(declaringClass).classFile
        proguardExecutor.traces.forEach {
            if (traceMapper.map(it, state.stmt, cf) || state.stmt.javaSourceStartLineNumber == 11 || declaringClass.contains("org.cyber.utils")) return true
        }
        return false
    }
}
