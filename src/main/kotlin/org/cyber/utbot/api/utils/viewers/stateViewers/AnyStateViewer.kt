package org.cyber.utbot.api.utils.viewers.stateViewers

import org.cyber.utbot.api.utils.viewers.IViewer
import org.utbot.engine.state.ExecutionState


interface AnyStateViewer: IViewer<ExecutionState> {
    override fun view(info: ExecutionState)
}
