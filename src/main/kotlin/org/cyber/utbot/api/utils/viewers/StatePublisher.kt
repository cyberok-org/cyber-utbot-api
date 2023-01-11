package org.cyber.utbot.api.utils.viewers

import org.utbot.engine.state.ExecutionState


class StatePublisher(override val viewers: MutableList<IViewer<ExecutionState>> = mutableListOf()) :  IViewable<ExecutionState>
