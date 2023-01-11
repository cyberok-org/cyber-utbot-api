package org.cyber.utbot.api.utils.viewers.stateViewers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.cyber.utbot.api.abstraction.JsonStatistic
import org.cyber.utbot.api.abstraction.JsonStatistics
import org.cyber.utbot.api.utils.JSON
import org.utbot.engine.state.ExecutionState


class TerminalStatisticViewer : StateViewer() {
    private val gson: Gson = GsonBuilder().create()
    private val statistics = mutableListOf<JsonStatistic>()

    override fun intermediateView(state: ExecutionState) {}

    override fun concreteView(state: ExecutionState) {}

    override fun terminalView(state: ExecutionState) {
        statistics.add(
            JsonStatistic(
                path = state.path.map { it.toString() },
                assertions = state.symbolicState.solver.assertions.map { it.toString() },
                assumption = state.symbolicState.solver.assumption.constraints.map { it.toString() },
                lastStatus = state.symbolicState.solver.lastStatus.toString()
            ))
    }

    fun result(): JSON = gson.toJson(JsonStatistics(statistics))
}
