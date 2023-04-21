package org.cyber.utbot

import org.cyber.utbot.api.CYBER_MOCK_ALWAYS_DEFAULT
import org.cyber.utbot.api.GenerateTestsSettings
import org.cyber.utbot.api.MOCK_ALWAYS_DEFAULT
import org.cyber.utbot.api.TestGenerator
import org.cyber.utbot.api.utils.UTBOT_DIR
import org.cyber.utbot.api.utils.toTestUnits
import org.cyber.utbot.api.utils.viewers.UTBotViewers
import org.utbot.common.PathUtil.toPath
import org.utbot.framework.TestSelectionStrategyType
import org.utbot.framework.UtSettings.summaryGenerationType
import org.utbot.framework.UtSettings.testMinimizationStrategyType
import org.utbot.framework.UtSettings.useDebugVisualization
import org.utbot.framework.UtSettings.useFuzzing
import org.utbot.framework.plugin.api.CodegenLanguage
import org.utbot.framework.plugin.api.MockStrategyApi
import java.nio.file.Files

fun main() {
//        val (tests, info) = generator.run(mapOf("org.testcases.taint.FileSystemUsage" to "src/main/java/org/testcases/taint/FileSystemUsage").toTestUnits())
//    val (tests, info) = generator.run(mapOf("org.testcases.taint.sepclasses.SinkClass" to "src/main/java/org/testcases/taint/sepclasses/SinkClass").toTestUnits())
//    val (tests, info) = generator.run(mapOf("org.testcases.taint.Fibonacci" to "src/main/java/org/testcases/taint/Fibonacci").toTestUnits())
//    val (tests, info) = generator.run(mapOf("org.example.Vulnerability" to "src/main/java/org/testcases/vulnerability/Vulnerability").toTestUnits())
//    val (tests, info) = generator.run(mapOf("org.example.Interprocedural" to "src/main/java/org/example/Interprocedural.java").toTestUnits())
//    val (tests, info) = generator.runBunch("build/classes/java/main", "org.example.dir")
//    val classpath = "/home/andrew/UTBotJava/cyber-utbot-exploit-base/build/classes/java/main"
//    val classpath = "C:/Users/lesya/uni2/UTBotJava/cyber-utbot-api/build/classes/java/main"
    val classpath = "C:/Users/lesya/Downloads/jars/javax.servlet-api-3.1.0.jar"
//    val classpath = "build/classes/java/main"
//    val classpath = "C:/Users/lesya/BenchmarkJava/target/classes"
//    val classpath = "build/classes/java/main:/home/andrew/.jdks/openjdk-17.0.2/bin/javax.servlet-api-3.1.0.jar"
////    val classpath = "/home/andrew/ex/build/classes/java/main:/home/andrew/.jdks/openjdk-17.0.2/bin/javax.servlet-api-3.1.0.jar"
    val otherMocks = emptyList<String>()
////    val otherMocks = listOf<String>()
//    val settings = GenerateTestsSettings(classpath, codegenLanguage = CodegenLanguage.JAVA, mockAlways = MOCK_ALWAYS_DEFAULT + otherMocks, mockStrategy = MockStrategyApi.NO_MOCKS,
//        withUtSettings = { useFuzzing = false; useDebugVisualization = true; },
//        utbotViewers = setOf(UTBotViewers.TERMINAL_STATISTIC_VIEWER)) //, vulnerabilityCheckDirectories=listOf("$UTBOT_DIR/cyber-utbot-exploit-base/src/base"))
//    val generator = TestGenerator(settings)
//    val (tests, info) = generator.run(mapOf("org.testcases.taint.Example" to "src/main/java/org/testcases/taint/Example").toTestUnits())
//
////    val (tests, info) = generator.run(mapOf("org.owasp.benchmark.testcode3.BenchmarkTest00133" to "C:/Users/lesya/BenchmarkJava/src/main/java/org/owasp/benchmark/testcode3/BenchmarkTest00133.java").toTestUnits())
//    tests.forEach { nameAndTest ->
//        Files.write(
//            "src/test/java/org/example/taint/${nameAndTest.key.takeLastWhile { it != '.' }}Test.java".toPath(),
//            listOf(nameAndTest.value)
//        )
////        println("name=${nameAndTest.key}\n\n${nameAndTest.value}\n")
//    }
    val settings = GenerateTestsSettings(classpath, codegenLanguage = CodegenLanguage.JAVA, mockAlways = MOCK_ALWAYS_DEFAULT + CYBER_MOCK_ALWAYS_DEFAULT + otherMocks, mockStrategy = MockStrategyApi.NO_MOCKS,
        withUtSettings = { useFuzzing = false; useDebugVisualization = true; testMinimizationStrategyType = TestSelectionStrategyType.DO_NOT_MINIMIZE_STRATEGY; },
        utbotViewers = setOf(UTBotViewers.TERMINAL_STATISTIC_VIEWER), vulnerabilityCheckDirectories=listOf("$UTBOT_DIR/cyber-utbot-exploit-base/src/base"))
    val generator = TestGenerator(settings)
//    val (tests, info) = generator.run(mapOf("org.example.Loop" to "src/main/java/org/example/Loop.java").toTestUnits())
//    val (tests, info) = generator.run(mapOf("org.example.checks.Example" to "src/main/java/org/example/checks/Example.java").toTestUnits())
    val (tests, info) = generator.run(mapOf("org.example.BenchmarkCheck" to "src/main/java/org/example/BenchmarkCheck").toTestUnits())
//    println(tests)
    tests.forEach { nameAndTest ->
        Files.write("src/test/java/org/example/${nameAndTest.key.takeLastWhile { it != '.' }}Test.java".toPath(), listOf(nameAndTest.value))
    }

//    val (tests, info) = generator.run(mapOf("org.example.base.ArbitraryFileCreation" to "$UTBOT_DIR/cyber-utbot-exploit-base/src/main/java/org/example/base/ArbitraryFileCreation.java").toTestUnits())
//    tests.forEach { nameAndTest ->
//        Files.write("$UTBOT_DIR/cyber-utbot-exploit-base/src/test/java/org/example/base/${nameAndTest.key.takeLastWhile { it != '.' }}Test.java".toPath(), listOf(nameAndTest.value))
//    }
//    printJson(info[UTBotViewers.TERMINAL_STATISTIC_VIEWER] as String)
}
