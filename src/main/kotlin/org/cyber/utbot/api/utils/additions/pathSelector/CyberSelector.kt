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

    init {
        classPool.insertClassPath("C:\\Users\\lesya\\UTBotJava\\cyber-utbot-api\\src\\main\\java\\org\\testcases\\jars\\JarTemp.jar")
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
        if (currentIndex == -1) {
            currentIndex = random.nextInt(executionStates.size)
        }
        return executionStates[currentIndex]
    }

    override fun pollImpl(): ExecutionState? {
        if (executionStates.size == 0) {
            return null
        }
        if (currentIndex == -1) {
            currentIndex = random.nextInt(executionStates.size)
        }
        val state = executionStates[currentIndex]
        val map = mapTraces(state)
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
        return traceMapper.map(proguardExecutor.traces.random(), jimpleBody.units, cf)
    }
}
