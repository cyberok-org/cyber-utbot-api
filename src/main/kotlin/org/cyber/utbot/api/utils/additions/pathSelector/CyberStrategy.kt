package org.cyber.utbot.api.utils.additions.pathSelector

import org.utbot.engine.InterProceduralUnitGraph
import org.utbot.engine.selectors.strategies.ChoosingStrategy
import org.utbot.engine.state.ExecutionState

class CyberStrategy(override val graph: InterProceduralUnitGraph) : ChoosingStrategy {

    var drop = false

    override fun shouldDrop(state: ExecutionState): Boolean {
        return drop
    }

}