package org.cyber.utbot.api

import org.utbot.engine.Mocker
import org.utbot.framework.UtSettings
import org.utbot.framework.codegen.*
import org.utbot.framework.plugin.api.CodegenLanguage
import org.utbot.framework.plugin.api.MockStrategyApi
import org.utbot.framework.plugin.api.TreatOverflowAsError
import org.utbot.common.AbstractSettings


private const val LONG_GENERATION_TIMEOUT = 1_200_000L
val MOCK_ALWAYS_DEFAULT = Mocker.defaultSuperClassesToMockAlwaysNames.toList()

enum class TestFrameworkGen {
    JUNIT4,
    JUNIT5,
    TESTING;

    fun toTestFramework(): TestFramework = when(this) {
        JUNIT4 -> Junit4
        JUNIT5 -> Junit5
        TESTING -> TestNg
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
    val mockAlways: Iterable<String> = MOCK_ALWAYS_DEFAULT,

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
    withUtSettings: (settings: UtSettings) -> Unit = {}
) {
    init {  // check is settings correct
        mockAlways.forEach { fullyQualifiedName ->
            Class.forName(fullyQualifiedName, false, ClassLoader.getSystemClassLoader())
        }
        assert(generationTimeout > 0) { "GenerateTestsSettings.generationTimeout should be more then 0" }
        withUtSettings(UtSettings)
    }
}
