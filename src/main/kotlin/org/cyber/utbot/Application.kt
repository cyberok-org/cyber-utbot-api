package org.cyber.utbot

import org.cyber.utbot.api.GenerateTestsSettings
import org.cyber.utbot.api.TestGenerator
import org.cyber.utbot.api.utils.printJson
import org.cyber.utbot.api.utils.toTestUnits
import org.cyber.utbot.api.utils.viewers.UTBotViewers
import org.utbot.common.PathUtil.toPath
import org.utbot.framework.TestSelectionStrategyType
import org.utbot.framework.UtSettings.testMinimizationStrategyType
import org.utbot.framework.UtSettings.useDebugVisualization
import org.utbot.framework.UtSettings.useFuzzing
import org.utbot.framework.plugin.api.CodegenLanguage
import java.nio.file.Files


fun main() {
    val settings = GenerateTestsSettings("build/classes/java/main", codegenLanguage = CodegenLanguage.JAVA,
        withUtSettings = { useFuzzing = false; useDebugVisualization = true; testMinimizationStrategyType = TestSelectionStrategyType.DO_NOT_MINIMIZE_STRATEGY },
        utbotViewers = setOf(UTBotViewers.TERMINAL_STATISTIC_VIEWER))
    val generator = TestGenerator(settings)
    val (tests, info) = generator.run(mapOf("org.testcases.taint.SeparateTraces" to "src/main/java/org/testcases/taint/SeparateTraces").toTestUnits())
//    val (tests, info) = generator.run(mapOf("org.example.Vulnerability" to "src/main/java/org/testcases/vulnerability/Vulnerability").toTestUnits())
//    val (tests, info) = generator.run(mapOf("org.example.Interprocedural" to "src/main/java/org/example/Interprocedural.java").toTestUnits())
//    val (tests, info) = generator.runBunch("build/classes/java/main", "org.example.dir")

    tests.forEach { nameAndTest ->
        Files.write("src/test/java/org/example/taint/${nameAndTest.key.takeLastWhile { it != '.' }}Test.java".toPath(), listOf(nameAndTest.value))
//        println("name=${nameAndTest.key}\n\n${nameAndTest.value}\n")
    }
    printJson(info[UTBotViewers.TERMINAL_STATISTIC_VIEWER] as String)
}
