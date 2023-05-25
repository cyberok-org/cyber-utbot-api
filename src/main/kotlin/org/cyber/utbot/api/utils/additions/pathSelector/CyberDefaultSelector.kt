package org.cyber.utbot.api.utils.additions.pathSelector

import org.utbot.engine.InterProceduralUnitGraph
import org.utbot.engine.jimpleBody
import org.utbot.engine.state.ExecutionState
import org.utbot.framework.UtSettings
import kotlin.random.Random

internal class CyberDefaultSelector(private val graph: InterProceduralUnitGraph) {

    private val random = Random( UtSettings.seedInPathSelector ?: 42)

    fun peekImpl(executionStates: List<ExecutionState>, currentIndex: Int): Pair<ExecutionState?, Int> {
        if (executionStates.isEmpty()) {
            return null to 0
        }
        var tmpCurrentIndex = currentIndex
        if (tmpCurrentIndex == -1) {
            tmpCurrentIndex = random.nextInt(executionStates.size)
        }
//        println("peeks state: ${Thread.currentThread()} ${executionStates[tmpCurrentIndex].stmt} method ${graph.method(executionStates[tmpCurrentIndex].stmt).jimpleBody().method.name}")
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
        executionStates.remove(state)
//        println("polles state: ${Thread.currentThread()} ${state.stmt} method ${graph.method(state.stmt).jimpleBody().method.name}")
        return state
    }
}