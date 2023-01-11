package org.cyber.utbot.api.utils.viewers.stateViewers

import org.utbot.engine.state.ExecutionState
import org.utbot.engine.state.StateLabel


abstract class StateViewer : AnyStateViewer {
    override fun view(info: ExecutionState) {
        when (info.label) {
            StateLabel.INTERMEDIATE -> intermediateView(info)
            StateLabel.CONCRETE -> concreteView(info)
            StateLabel.TERMINAL -> terminalView(info)
        }
    }

    protected abstract fun intermediateView(state: ExecutionState)

    protected abstract fun concreteView(state: ExecutionState)

    protected abstract fun terminalView(state: ExecutionState)
}
