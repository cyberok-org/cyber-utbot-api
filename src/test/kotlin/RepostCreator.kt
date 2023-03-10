import org.cyber.utbot.api.GenerateTestsSettings
import org.cyber.utbot.api.TestGenerator
import org.cyber.utbot.api.utils.toTestUnits
import org.cyber.utbot.api.utils.viewers.UTBotViewers
import org.junit.jupiter.api.Test
import org.utbot.framework.TestSelectionStrategyType
import org.utbot.framework.UtSettings
import org.utbot.framework.plugin.api.CodegenLanguage
import org.utbot.framework.plugin.api.MockStrategyApi


class RepostCreator {
    @Test
    fun generateTest() {
        val classpath = "/home/andrew/UTBotJava/cyber-utbot-exploit-base/build/classes/java/main"
        val settings = GenerateTestsSettings(classpath, codegenLanguage = CodegenLanguage.JAVA, mockStrategy = MockStrategyApi.NO_MOCKS,
            withUtSettings = { UtSettings.useFuzzing = false; UtSettings.useDebugVisualization = true; UtSettings.testMinimizationStrategyType = TestSelectionStrategyType.DO_NOT_MINIMIZE_STRATEGY },
            utbotViewers = setOf(UTBotViewers.TERMINAL_STATISTIC_VIEWER), vulnerabilityCheckDirectories=listOf("/home/andrew/UTBotJava/cyber-utbot-exploit-base/src/base"))
        val generator = TestGenerator(settings)
        val (tests, info) = generator.run(mapOf("org.example.base.ArbitraryFileCreation" to "/home/andrew/UTBotJava/cyber-utbot-exploit-base/src/main/java/org/example/base/ArbitraryFileCreation.java").toTestUnits())
        tests.forEach { (name, testsPart) ->
            println(name)
            println(testsPart)
            println()
        }
//        printJson(info[UTBotViewers.TERMINAL_STATISTIC_VIEWER] as String)
    }
}
