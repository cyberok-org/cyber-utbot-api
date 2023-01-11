package org.cyber.utbot.api.utils.viewers.stateViewers

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import org.cyber.utbot.api.abstraction.JsonStatistic
import org.cyber.utbot.api.abstraction.JsonStatistics
import org.cyber.utbot.api.utils.JSON
import org.utbot.engine.state.ExecutionState


class EndNotTerminalStatisticViewer : StateViewer() {
    private val gson: Gson = GsonBuilder().create()
    private val states = mutableMapOf<Int, ExecutionState>()

    private fun ExecutionState.getHash() = path.hashCode()

    private fun anyView(state: ExecutionState, add: Boolean = true) {
        if (state.path.size > 1) {
            states.remove(state.path.dropLast(1).hashCode())
        }
        if (add) {
            states[state.getHash()] = state
        }
    }

    private fun clear() {
        states.clear()
    }

    override fun intermediateView(state: ExecutionState) = anyView(state)

    override fun concreteView(state: ExecutionState) = anyView(state)

    override fun terminalView(state: ExecutionState) = anyView(state, false)

    // clear after run
    fun result(): JSON {
        val statistics = states.values.map { state ->
            JsonStatistic(
                path = state.path.map { it.toString() },
                assertions = state.symbolicState.solver.assertions.map { it.toString() },
                assumption = state.symbolicState.solver.assumption.constraints.map { it.toString() },
                lastStatus = state.symbolicState.solver.lastStatus.toString()
            )
        }
        clear()
        return gson.toJson(JsonStatistics(statistics))
    }
}
