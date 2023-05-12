package org.cyber.utbot

import org.cyber.utbot.api.CYBER_MOCK_ALWAYS_DEFAULT
import org.cyber.utbot.api.GenerateTestsSettings
import org.cyber.utbot.api.MOCK_ALWAYS_DEFAULT
import org.cyber.utbot.api.TestGenerator
import org.cyber.utbot.api.abstraction.BenchInfo
import org.cyber.utbot.api.abstraction.ReportItem
import org.cyber.utbot.api.utils.*
import org.cyber.utbot.api.utils.viewers.UTBotViewers
import org.utbot.common.PathUtil.toPath
import org.utbot.framework.TestSelectionStrategyType
import org.utbot.framework.UtSettings
import org.utbot.framework.plugin.api.CodegenLanguage
import org.utbot.framework.plugin.api.MockStrategyApi
import java.nio.file.Files

class ReportCreator(settings: GenerateTestsSettings, private val category: String? = null) {    // for one examples in class only!!!
    private val generator = TestGenerator(settings.also { it.testsIgnoreEmpty = true }) // work wrong without `testsIgnoreEmpty = true`

    constructor(classpath: String, basePaths: List<String>, category: String? = null, extraMocks: List<String> = emptyList()) : this(GenerateTestsSettings(classpath, codegenLanguage = CodegenLanguage.JAVA,
        mockAlways = MOCK_ALWAYS_DEFAULT + CYBER_MOCK_ALWAYS_DEFAULT + extraMocks, mockStrategy = MockStrategyApi.NO_MOCKS, withUtSettings = { UtSettings.useFuzzing = false;
            UtSettings.useDebugVisualization = true; UtSettings.testMinimizationStrategyType = TestSelectionStrategyType.DO_NOT_MINIMIZE_STRATEGY },
        utbotViewers = setOf(UTBotViewers.TERMINAL_STATISTIC_VIEWER), vulnerabilityCheckBases=basePaths), category)

    private fun create(test: TestUnit, testSavePath: String?): ReportItem {
        return try {
            val (tests, info) = generator.run(listOf(test))
            if (tests.isNotEmpty()) {
                testSavePath?.let {
                    tests.forEach { nameAndTest ->
                        Files.write("$it/${nameAndTest.key.takeLastWhile { it != '.' }}Test.java".toPath(), listOf(nameAndTest.value))
                    }
                }
                ReportItem(test.target, success = true, category = category, testsRaw=tests[test.target], testsInfo=(info[UTBotViewers.TERMINAL_STATISTIC_VIEWER] as String).pretty())
            } else {
                ReportItem(test.target, success = false, category = category)
            }
        } catch (e: Exception) {
            ReportItem(test.target, success = false, category = category, failReason="utbot failed: ${e.message}")
        }
    }

    fun create(tests: List<TestUnit>, benchmark: String, testsSavePath: String? = null) = tests.map { create(it, testsSavePath) }.run {
        writeCsvFile(this, ReportItem.schema, "$benchmark.csv")
    }
}


fun main() {
    val bench = readCsvFile<BenchInfo>("$UTBOT_DIR/cyber-utbot-api/src/test/resources/want.csv").associate {
        it.target to it.source
    }.toTestUnits()
//        .drop(14).take(1)
        .drop(28).take(1)

    val extraMocks=listOf("org.owasp.esapi.ESAPI", "org.owasp.esapi.Encoder")
    val classpath = "/home/andrew/BenchmarkJava/target/classes:/home/andrew/jars/javax.servlet-api-3.1.0.jar:/home/andrew/jars/esapi-2.5.1.0.jar"
    val reportCreator = ReportCreator(classpath, listOf("$UTBOT_DIR/cyber-utbot-exploit-base"), category = "pathtraver", extraMocks=extraMocks)
    reportCreator.create(bench, benchmark="OWASP-Benchmark", testsSavePath="/home/andrew/BenchmarkJava/src/test/java/org/owasp/benchmark/testcode")    // testsSavePath should exist
}
