package org.cyber.utbot

import org.cyber.utbot.api.GenerateTestsSettings
import org.cyber.utbot.api.MOCK_ALWAYS_DEFAULT
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
import org.utbot.framework.plugin.api.MockStrategyApi
import java.nio.file.Files


fun main() {
//    val classpath = "/home/andrew/UTBotJava/cyber-utbot-exploit-base/build/classes/java/main"
    val classpath = "build/classes/java/main"

    val otherMocks = emptyList<String>()
//    val otherMocks = listOf("java.nio.file.Paths", "java.io.FileWriter", "java.io.PrintWriter")
//    // mocks: "java.nio.file.Files", "java.io.File", "java.nio.file.spi.FileSystemProvider", "javax.swing.filechooser.FileSystemView"

    val settings = GenerateTestsSettings(classpath, codegenLanguage = CodegenLanguage.JAVA, mockAlways = MOCK_ALWAYS_DEFAULT + otherMocks, mockStrategy = MockStrategyApi.NO_MOCKS,
        withUtSettings = { useFuzzing = false; useDebugVisualization = true; testMinimizationStrategyType = TestSelectionStrategyType.DO_NOT_MINIMIZE_STRATEGY },
        utbotViewers = setOf(UTBotViewers.TERMINAL_STATISTIC_VIEWER)) // , vulnerabilityCheckDirectories=listOf("/home/andrew/UTBotJava/cyber-utbot-exploit-base/src/base"))
    val generator = TestGenerator(settings)
    val (tests, info) = generator.run(mapOf("org.example.Loop" to "src/main/java/org/example/Loop.java").toTestUnits())
    tests.forEach { nameAndTest ->
        Files.write("src/test/java/org/example/${nameAndTest.key.takeLastWhile { it != '.' }}Test.java".toPath(), listOf(nameAndTest.value))
    }

//    val (tests, info) = generator.run(mapOf("org.example.base.ArbitraryFileCreation" to "/home/andrew/UTBotJava/cyber-utbot-exploit-base/src/main/java/org/example/base/ArbitraryFileCreation.java").toTestUnits())
//    tests.forEach { nameAndTest ->
//        Files.write("/home/andrew/UTBotJava/cyber-utbot-exploit-base/src/test/java/org/example/base/${nameAndTest.key.takeLastWhile { it != '.' }}Test.java".toPath(), listOf(nameAndTest.value))
//    }
    printJson(info[UTBotViewers.TERMINAL_STATISTIC_VIEWER] as String)
}
