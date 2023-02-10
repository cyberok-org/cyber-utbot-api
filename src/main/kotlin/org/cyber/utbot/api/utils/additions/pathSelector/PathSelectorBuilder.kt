package org.cyber.utbot.api.utils.additions.pathSelector

import org.utbot.engine.InterProceduralUnitGraph
import org.utbot.engine.selectors.*


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
    override fun build() = CyberSelector(
        CyberStrategy(graph), // choosing strategy
        requireNotNull(context.stoppingStrategy) { "StoppingStrategy isn't specified" },
        "C:\\Users\\lesya\\uni2\\UTBotJava\\cyber-utbot-api\\src\\main\\java\\org\\testcases\\taint\\jars\\TempJar.jar",
        graph
    )
}
