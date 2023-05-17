package org.cyber.utbot

import kotlinx.cli.*
import org.cyber.utbot.api.CYBER_MOCK_ALWAYS_DEFAULT
import org.cyber.utbot.api.GenerateTestsSettings
import org.cyber.utbot.api.MOCK_ALWAYS_DEFAULT
import org.cyber.utbot.api.TestGenerator
import org.cyber.utbot.api.exceptions.CyberException
import org.cyber.utbot.api.utils.TestUnit
import org.utbot.common.PathUtil.toPath
import org.utbot.framework.TestSelectionStrategyType
import org.utbot.framework.UtSettings
import org.utbot.framework.codegen.domain.ForceStaticMocking
import org.utbot.framework.codegen.domain.MockitoStaticMocking
import org.utbot.framework.codegen.domain.NoStaticMocking
import org.utbot.framework.plugin.api.MockStrategyApi
import java.io.File
import java.nio.file.Files


@OptIn(ExperimentalCli::class)
private class GenerateCommandParser: Subcommand("generate", "generate tests") {
    private fun String.toMockStrategyApi(): MockStrategyApi = when(this) {
        "do-not-mock" -> MockStrategyApi.NO_MOCKS
        "package-based" -> MockStrategyApi.NO_MOCKS
        "all-except-cut" -> MockStrategyApi.NO_MOCKS
        else -> throw CyberException("wrong mock strategy")
    }
    private fun Boolean.toTestSelectionStrategyType() = if (this) TestSelectionStrategyType.COVERAGE_STRATEGY else TestSelectionStrategyType.DO_NOT_MINIMIZE_STRATEGY
    private fun Boolean.toMockStatics() = if (this) MockitoStaticMocking else NoStaticMocking
    private fun Boolean.toForceStaticMocking() = if (this) ForceStaticMocking.FORCE else ForceStaticMocking.DO_NOT_FORCE

    private fun String.getTarget() = this.substringAfter("java/").dropLast(5).replace("/", ".")  // drop .java

    private val classpath by option(ArgType.String, "classpath", "cp", "Specifies the classpath for a class under test, many classpathes connecting with \":\"")

    private val source by option(ArgType.String, "source", "s", "Specifies source code file for a generated test")

    private val target by option(ArgType.String, "target",  description = "Specifies source code file for a generated test")

    val output by option(ArgType.String, "output", "o", "Specifies output file for a generated test")

    val printTest by option(ArgType.Boolean, "print-test", "p", "Specifies whether a test should be printed out to StdOut").default(false)

    private val mockAlways by option(ArgType.String, "mock-always", "ma", "Specifies path to \".txt\" file with names mocking fqn classes separated with \"\\n\"")

    private val vulnerabilityCheckBases by option(ArgType.String, "base-path", "b", "Specifies path to knowledge base").multiple()

    private val generationTimeout by option(ArgType.Int, "generation-timeout", "t", "Specifies the maximum time in milliseconds used to generate tests (1_200_000 by default)").default(1_200_000)

    private val onlyVulnerabilities by option(ArgType.Boolean, "only-vulnerabilities", "ov", "Not show tests without vulnerability").default(false)

    private val findVulnerabilities by option(ArgType.Boolean, "find-vulnerabilities", "fv", "Disable find vulnerabilities").default(true)

    private val cyberPathSelector by option(ArgType.Boolean, "path-selector", description = "Enable CyberPathSelector").default(false)  // TODO set default true after connection with CyberPathSelector

    private val useDebugVisualization by option(ArgType.Boolean, "debug-visualization", description = "Disable debug visualization").default(true)

    private val useFuzzing by option(ArgType.Boolean, "fuzzing", description = "Enable fuzzing").default(false)

    private val mockStrategyChoice by option(ArgType.Choice(listOf("do-not-mock", "package-based", "all-except-cut"), { it }),
        "mock-strategy",  description = "Defines the mock strategy").default("do-not-mock")

    private val mockStaticOption by option(ArgType.Boolean, "mock-statics", description = "Choose framework for mocking statics (or not mock statics at all").default(true)

    private val forceStaticMockingOption by option(ArgType.Boolean, "force-static-mocking", description = "Forces mocking static methods and constructors for \"--mock-always\" classes").default(true)

    private val testMinimizationStrategyTypeOption by option(ArgType.Boolean, "minimize-test", description = "Enable minimize test (maybe bad for vulnerability test generation)").default(false)

    lateinit var sourcePath: String
    lateinit var targetClass: String
    var outputClassName: String? = null
    lateinit var settings: GenerateTestsSettings

    override fun execute() {
        require (classpath != null) { "classpath should be set" }
        require (source != null) { "source should be set" } // source should be != null after that
        sourcePath = source!!

        require (sourcePath.endsWith(".java")) { "wrong source, it should be like \"*.java\"" }
        require (output != null || printTest) { "should be set output or print-test" }

        targetClass = target ?: sourcePath.getTarget().also {
            if (useDebugVisualization) println("cyber-utbot-cli: assume target \"$it\"")
        }

        outputClassName = output?.run {
            if (endsWith(".java")) {
                dropLast(5).takeLastWhile { it != '.' }
            } else null
        }

        val otherMocks = mockAlways?.run {
            val file = File(this)
            require (file.isFile) { "wrong mock-always path: $this" }
            file.useLines { it.toList() }
        } ?: listOf()

        val mockStrategy = mockStrategyChoice.toMockStrategyApi()
        val mockStatics = mockStaticOption.toMockStatics()
        val forceStaticMocking = forceStaticMockingOption.toForceStaticMocking()
        val testMinimizationStrategyType = testMinimizationStrategyTypeOption.toTestSelectionStrategyType()

        settings = GenerateTestsSettings(classpath,
            generationTimeout = generationTimeout.toLong(),
            mockAlways = MOCK_ALWAYS_DEFAULT + CYBER_MOCK_ALWAYS_DEFAULT + otherMocks,
            onlyVulnerabilities = onlyVulnerabilities,
            findVulnerabilities = findVulnerabilities,
            cyberPathSelector = cyberPathSelector,
            mockStrategy = mockStrategy,
            mockStatics = mockStatics,
            forceStaticMocking = forceStaticMocking,
            utbotViewers = setOf(),
            vulnerabilityCheckBases=vulnerabilityCheckBases,
            withUtSettings = {
                UtSettings.useFuzzing = useFuzzing
                UtSettings.useDebugVisualization = useDebugVisualization
                UtSettings.testMinimizationStrategyType = testMinimizationStrategyType
            }
        )
    }
}

private fun generate(generateCommandParser: GenerateCommandParser) {
    val generator = TestGenerator(generateCommandParser.settings)

    val (tests, _) = generator.run(listOf(
        TestUnit(target=generateCommandParser.targetClass, source=generateCommandParser.sourcePath, output=generateCommandParser.outputClassName)
    ))

    require(tests.size == 1) { "internal cyber-utbot-cli error: too many test files" }
    val test = tests.values.first()

    generateCommandParser.output?.run { Files.write(toPath(), listOf(test)) }

    if (generateCommandParser.printTest) {
        println(test)
    }
}

@OptIn(ExperimentalCli::class)
fun main(args: Array<String>) {
    val cyberCliParser = ArgParser("cyber-utbot-cli")
    val generateCommandParser = GenerateCommandParser()
    cyberCliParser.subcommands(generateCommandParser)
    cyberCliParser.parse(args)

    generate(generateCommandParser)
}
