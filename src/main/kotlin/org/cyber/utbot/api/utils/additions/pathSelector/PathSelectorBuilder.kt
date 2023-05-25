package org.cyber.utbot.api.utils.additions.pathSelector

import org.utbot.engine.InterProceduralUnitGraph
import org.utbot.engine.selectors.*


// utbot-framework/src/main/kotlin/org/utbot/engine/selectors/PathSelectorBuilder.kt

fun cyberPathSelector(
    graph: InterProceduralUnitGraph,
    strategy: StrategyOption,
    analysedJar: String,
    cyberDefaultSelector: Boolean,
    builder: CyberSelectorBuilder.() -> Unit
) = CyberSelectorBuilder(graph, strategy, analysedJar, cyberDefaultSelector).apply(builder).build()


class CyberSelectorBuilder internal constructor(
    graph: InterProceduralUnitGraph,
    val strategy: StrategyOption,
    private val analysedJar: String,
    private val cyberDefaultSelector: Boolean,
    context: PathSelectorContext = PathSelectorContext(graph)
) : PathSelectorBuilder<CyberSelector>(graph, context) {
    override fun build() = CyberSelector(
        CyberStrategy(graph), // choosing strategy
        requireNotNull(context.stoppingStrategy) { "StoppingStrategy isn't specified" },
        analysedJar,
        graph,
        cyberDefaultSelector
    )
}
