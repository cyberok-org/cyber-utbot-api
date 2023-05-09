package org.cyber.utbot

import org.cyber.utbot.api.GenerateTestsSettings
import org.cyber.utbot.api.TestGenerator
import org.cyber.utbot.api.utils.toTestUnits
import org.cyber.utbot.api.utils.viewers.UTBotViewers
import org.utbot.common.PathUtil.toPath
import org.utbot.framework.TestSelectionStrategyType
import org.utbot.framework.UtSettings.testMinimizationStrategyType
import org.utbot.framework.UtSettings.useFuzzing
import java.nio.file.Files


fun main() {
    val classpath = "build/classes/java/main"

    val settings = GenerateTestsSettings(classpath, generationTimeout=120_000,
        withUtSettings = { useFuzzing = false; testMinimizationStrategyType = TestSelectionStrategyType.DO_NOT_MINIMIZE_STRATEGY; },
        utbotViewers = setOf(UTBotViewers.TERMINAL_STATISTIC_VIEWER), onlyVulnerabilities = true)
    val generator = TestGenerator(settings)
    val (tests, info) = generator.run(mapOf("org.example.Example" to "src/main/java/org/example/Example.java").toTestUnits())
    println(tests)
//    tests.forEach { nameAndTest ->
//        Files.write("src/test/java/org/example/${nameAndTest.key.takeLastWhile { it != '.' }}Test.java".toPath(), listOf(nameAndTest.value))
//    }
//    printJson(info[UTBotViewers.TERMINAL_STATISTIC_VIEWER] as String)
}
