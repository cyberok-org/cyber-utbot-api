package org.cyber.utbot.api

import org.cyber.utbot.api.abstraction.extraChecks.ExtraVulnerabilityCheck
import org.cyber.utbot.api.utils.viewers.UTBotViewers
import org.utbot.engine.Mocker
import org.utbot.framework.TrustedLibraries
import org.utbot.framework.UtSettings
import org.utbot.framework.codegen.domain.*
import org.utbot.framework.plugin.api.CodegenLanguage
import org.utbot.framework.plugin.api.MockStrategyApi
import org.utbot.framework.plugin.api.TreatOverflowAsError


private const val LONG_GENERATION_TIMEOUT = 1_200_000L
val MOCK_ALWAYS_DEFAULT = Mocker.defaultSuperClassesToMockAlwaysNames.toList()
val CYBER_MOCK_ALWAYS_DEFAULT = listOf(         // TODO(division by packages)
    "java.net.URL",
    "java.io.FileInputStream",
    "java.net.URI",
    "java.io.FileOutputStream",
    "java.io.File",
    "javax.servlet.http.HttpServletRequest",
    "javax.servlet.http.Cookie",
    "javax.servlet.RequestDispatcher",
    "javax.servlet.http.HttpServletResponse",
//    "javax.servlet.ServletConfig",    // TODO not work custom classes if mock it (interface)???
    "java.io.PrintWriter",
    "java.io.FileWriter",
    "java.util.BitSet",
)

enum class TestFrameworkGen {
    JUNIT4,
    JUNIT5,
    TESTING;

    fun toTestFramework(): TestFramework {
        return when(this) {
            JUNIT4 -> Junit4
            JUNIT5 -> Junit5
            TESTING -> TestNg
        }
    }
}

/**
 * Parameters that are needed to generate
 */
class GenerateTestsSettings(
    /**
     * Specifies the default classpath for a class under test
     */
    val classpath: String? = null,

    /**
     * Defines the mock strategy
     */
    val mockStrategy: MockStrategyApi = MockStrategyApi.NO_MOCKS,

    /**
     * Test framework to be used
     */
    val testFrameworkGen: TestFrameworkGen = TestFrameworkGen.JUNIT5,

    /**
     * Choose framework for mocking statics (or not mock statics at all)
     */
    val mockStatics: StaticsMocking = StaticsMocking.defaultItem,

    /**
     * Forces mocking static methods and constructors for "mockAlways" classes
     */
    val forceStaticMocking: ForceStaticMocking = ForceStaticMocking.defaultItem,

    /**
     * Treat overflows as errors
     */
    val treatOverflowAsError: TreatOverflowAsError = TreatOverflowAsError.defaultItem,

    /**
     * Classes fully qualified name to force mocking theirs static methods and constructors (you can use it multiple times to provide few classes)
     */
    val mockAlways: Iterable<String> = MOCK_ALWAYS_DEFAULT + CYBER_MOCK_ALWAYS_DEFAULT,

    /**
     * Specifies the maximum time in milliseconds used to generate tests
     */
    val generationTimeout: Long = LONG_GENERATION_TIMEOUT,

    /**
     * Specifies the root of the relative paths in the sarif report that are required to show links correctly; can set automatically
     */
    val projectRoot: String? = null, // TODO(check it)

    /**
     * Specifies output file for the static analysis report
     */
    val sarifReport: String? = null, // TODO(check it)

    /**
     * Defines the codegen language
     */
    val codegenLanguage: CodegenLanguage = CodegenLanguage.defaultItem,

    /**
     * withUtSettings
     */
    withUtSettings: (settings: UtSettings) -> Unit = {},


    // cyber settings //

    /**
     * state and other viewers for utbot
     */
    val utbotViewers: Set<UTBotViewers> = setOf(UTBotViewers.TERMINAL_STATISTIC_VIEWER),

    /**
     * ignore utbot pathSelectors parameters, use our
     */
    val cyberPathSelector: Boolean = false,

    /**
     * if false - just run utbot without extra analyze   // TODO(remove later)
     */
    val findVulnerabilities: Boolean = true,

    /**
     * list of directories with files of the form VulnerabilityStandard for the description of analyzed vulnerabilities
     */
    val vulnerabilityCheckDirectories: List<String> = listOf("src/exploitBase"),

    /**
     * *$vulnerabilityCheckDirectory/$vulnerabilityCheckFuncsSuffix* - path to functions checks descriptions directory
     */
    val vulnerabilityChecksAnalysisSuffix: String = "funcs",

    /**
     * *$vulnerabilityCheckDirectory/vulnerabilityChecksSuffix* - path to checks, referenced by *vulnerabilityChecksAnalysisSuffix files*. So path - *$vulnerabilityCheckDirectories/$vulnerabilityChecksSuffix/${path declared from function check file}*
     */
    val vulnerabilityChecksSuffix: String = "checks",

    /**
     * structures similar to those analyzed from the base (vulnerabilityCheckDirectories), set in runtime
     */
    val extraVulnerabilityChecks: List<ExtraVulnerabilityCheck> = emptyList(),

    /**
     * generate tests only for vulnerabilities if true
     */
    val onlyVulnerabilities: Boolean = false,

    /**
     * whether to ignore classes to pass non-generated tests
     */
    var testsIgnoreEmpty: Boolean = false,

    /**
     * libraries that utbot will trust
     * already trust: java, sun, javax, com.sun, org.omg, org.xml, org.w3c.dom
     */
    trustedLibraries: List<String> = listOf()
) {
    init {  // check is settings correct
        TrustedLibraries.extraTrustedLibraries = trustedLibraries
        assert(generationTimeout > 0) { "GenerateTestsSettings.generationTimeout should be more then 0" }
        UtSettings.checkSolverTimeoutMillis = 0   // disabled z3 timeout
        UtSettings.runInstrumentedProcessWithDebug = false   // java.lang.NoClassDefFoundError without that (with true instrumentation failed before producing 'NoClassDefFoundError')
        withUtSettings(UtSettings)
    }
}
