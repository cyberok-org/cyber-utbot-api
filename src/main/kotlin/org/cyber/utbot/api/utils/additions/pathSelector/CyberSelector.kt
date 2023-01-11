package org.cyber.utbot.api.utils.additions.pathSelector

import org.utbot.engine.selectors.BasePathSelector
import org.utbot.engine.state.ExecutionState
import org.utbot.engine.selectors.strategies.ChoosingStrategy
import org.utbot.engine.selectors.strategies.StoppingStrategy
import kotlin.random.Random


class CyberSelector(choosingStrategy: ChoosingStrategy, stoppingStrategy: StoppingStrategy, seed: Int = 42) :
    BasePathSelector(choosingStrategy, stoppingStrategy) {

    private val executionStates = mutableListOf<ExecutionState>()
    private val random = Random(seed)
    private var currentIndex = -1

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
}
