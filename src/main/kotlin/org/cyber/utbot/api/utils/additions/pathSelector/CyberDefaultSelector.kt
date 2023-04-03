package org.cyber.utbot.api.utils.additions.pathSelector

import org.utbot.engine.state.ExecutionState
import org.utbot.framework.UtSettings
import kotlin.random.Random

internal class CyberDefaultSelector {

    private val random = Random( UtSettings.seedInPathSelector ?: 42)

    fun peekImpl(executionStates: List<ExecutionState>, currentIndex: Int): Pair<ExecutionState?, Int> {
        if (executionStates.isEmpty()) {
            return null to 0
        }
        var tmpCurrentIndex = currentIndex
        if (tmpCurrentIndex == -1) {
            tmpCurrentIndex = random.nextInt(executionStates.size)
        }
        return executionStates[tmpCurrentIndex] to tmpCurrentIndex
    }

    fun pollImpl(executionStates: MutableList<ExecutionState>, currentIndex: Int): ExecutionState? {
        if (executionStates.isEmpty()) {
            return null
        }
        var tmpCurrentIndex = currentIndex
        if (tmpCurrentIndex == -1) {
            tmpCurrentIndex = random.nextInt(executionStates.size)
        }
        val state = executionStates[tmpCurrentIndex]
        executionStates.removeAt(tmpCurrentIndex)
        return state
    }
}