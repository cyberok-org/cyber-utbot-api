package org.cyber.utbot.api.utils.additions.pathSelector

import org.utbot.engine.InterProceduralUnitGraph
import org.utbot.engine.selectors.*
import org.utbot.framework.UtSettings


// utbot-framework/src/main/kotlin/org/utbot/engine/selectors/PathSelectorBuilder.kt

fun cyberPathSelector(
    graph: InterProceduralUnitGraph,
    strategy: StrategyOption,
    builder: CyberSelectorBuilder.() -> Unit
) = CyberSelectorBuilder(graph, strategy).apply(builder).build()


class CyberSelectorBuilder internal constructor(
    graph: InterProceduralUnitGraph,
    val strategy: StrategyOption,
    context: PathSelectorContext = PathSelectorContext(graph)
) : PathSelectorBuilder<CyberSelector>(graph, context) {
    var seed: Int = UtSettings.seedInPathSelector ?: 42
    override fun build() = CyberSelector(
        withChoosingStrategy(strategy),
        requireNotNull(context.stoppingStrategy) { "StoppingStrategy isn't specified" },
        seed
    )
}
