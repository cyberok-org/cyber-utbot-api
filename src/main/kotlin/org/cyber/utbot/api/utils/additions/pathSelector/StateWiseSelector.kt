package org.cyber.utbot.api.utils.additions.pathSelector

import org.utbot.engine.InterProceduralUnitGraph
import org.utbot.engine.selectors.BasePathSelector
import org.utbot.engine.selectors.PathSelector
import org.utbot.engine.selectors.RandomSelector
import org.utbot.engine.selectors.strategies.ChoosingStrategy
import org.utbot.engine.selectors.strategies.StoppingStrategy
import org.utbot.engine.state.ExecutionState

class CyberStateViseSelector(
    choosingStrategy: ChoosingStrategy,
    stoppingStrategy: StoppingStrategy,
    jarName: String,
    graph: InterProceduralUnitGraph,
) : PathSelector {

    private val cyberSelector = CyberTaintSelector(choosingStrategy, stoppingStrategy, jarName, graph)
    private val defaultSelector = CyberDefaultSelector(choosingStrategy, stoppingStrategy, 42)

    /**
     * Can be either default ot cyber selector, depending on the state of analysis
     */
    private var activeSelector: BasePathSelector = defaultSelector
    private var nextIteration = true

    override val name = "CyberStrategicSelector"

    override fun offer(state: ExecutionState) = activeSelector.offer(state)

    override fun remove(state: ExecutionState): Boolean = activeSelector.remove(state)

    override fun queue(): List<Pair<ExecutionState, Double>> = activeSelector.queue()

    override fun isEmpty(): Boolean = activeSelector.isEmpty()

    override fun close() =activeSelector.close()

    override fun peek(): ExecutionState? {
        return activeSelector.peek()
    }

    override fun poll(): ExecutionState? {
        return activeSelector.poll()
    }

    fun setNextIteration(value: Boolean) {
        nextIteration = value
        if (value) {
            // todo clear and stuff
        }
    }

    private fun cyberToDefault() {
        if (activeSelector !is CyberTaintSelector) return
        defaultSelector.setExecutionStates((activeSelector as CyberTaintSelector).executionStates())
        activeSelector = defaultSelector
    }

    private fun defaultToCyber() {
        if (activeSelector !is RandomSelector) return
        cyberSelector.setExecutionStates((activeSelector as CyberDefaultSelector).executionStates())
        activeSelector = cyberSelector
    }
}

