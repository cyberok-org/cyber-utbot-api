package org.cyber.utbot.api.utils.additions.pathSelector

import org.utbot.engine.state.ExecutionState
import org.utbot.framework.UtSettings
import kotlin.random.Random

internal class CyberDefaultSelector {

    private val random = Random( UtSettings.seedInPathSelector ?: 42)

    fun peekImpl(executionStates: List<ExecutionState>, currentIndex: Int): Pair<ExecutionState, Int> {
        var tmpCurrentIndex = -1
        if (currentIndex == -1) {
            tmpCurrentIndex = random.nextInt(executionStates.size)
        }
        return executionStates[tmpCurrentIndex] to tmpCurrentIndex
    }

    fun pollImpl(executionStates: MutableList<ExecutionState>, currentIndex: Int): Pair<ExecutionState, Int> {
        var tmpCurrentIndex = -1
        if (currentIndex == -1) {
            tmpCurrentIndex = random.nextInt(executionStates.size)
        }
        val state = executionStates[currentIndex]
        executionStates.removeAt(tmpCurrentIndex)
        return state to -1
    }
}