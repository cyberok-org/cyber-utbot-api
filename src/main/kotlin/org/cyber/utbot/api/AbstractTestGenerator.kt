package org.cyber.utbot.api

import org.cyber.utbot.api.abstraction.extraChecks.ExtraVulnerabilityCheck
import org.cyber.utbot.api.utils.additions.classState.codeGeneration.CodeGen
import org.cyber.utbot.api.utils.overrides.CyberCodeGenerator
import org.cyber.utbot.api.utils.overrides.CyberTestCaseGenerator
import org.cyber.utbot.api.utils.viewers.StatePublisher
import org.cyber.utbot.api.utils.viewers.UTBotViewers
import org.cyber.utbot.api.utils.vulnerability.VulnerabilityChecksHolder
import org.utbot.common.PathUtil
import org.utbot.common.PathUtil.toPath
import org.utbot.common.PathUtil.toURL
import org.utbot.common.toPath
import org.utbot.framework.UtSettings
import org.utbot.framework.codegen.*
import org.utbot.framework.codegen.domain.*
import org.utbot.framework.plugin.api.*
import org.utbot.framework.plugin.services.JdkInfoDefaultProvider
import org.utbot.summary.summarizeAll
import java.io.File
import java.net.URLClassLoader
import java.nio.file.Files
import java.nio.file.Path
import java.nio.file.Paths
import java.time.LocalDateTime


abstract class AbstractTestGenerator {
    protected abstract val treatOverflowAsError: TreatOverflowAsError
    protected abstract val mockStrategy: MockStrategyApi
    protected abstract val generationTimeout: Long
    protected abstract val forceStaticMocking: ForceStaticMocking
    protected abstract val staticsMocking: StaticsMocking
    protected abstract val codegenLanguage: CodegenLanguage
    protected abstract val testFramework: TestFramework
    //
    protected abstract val utbotViewers: Set<UTBotViewers>
    protected abstract val cyberPathSelector: Boolean
    protected abstract val findVulnerabilities: Boolean
    protected abstract val vulnerabilityCheckDirectories: List<String>
    protected abstract val vulnerabilityChecksAnalysisSuffix: String
    protected abstract val vulnerabilityChecksSuffix: String
    protected abstract val extraVulnerabilityChecks: List<ExtraVulnerabilityCheck>
    protected abstract val onlyVulnerabilities: Boolean
    protected abstract val testsIgnoreEmpty: Boolean
    protected abstract val analysedJar: String
    protected abstract val cyberDefaultSelector: Boolean
    protected abstract val codeGen: CodeGen

    private var classpath: String? = null
    protected lateinit var classLoader: URLClassLoader
    protected val newlineSeparator: String by lazy { System.lineSeparator() }

    protected val statePublisher: StatePublisher by lazy { StatePublisher(utbotViewers.mapNotNull { it.stateViewer() }.toMutableList()) }
    private val vulnerabilityChecksHolder: VulnerabilityChecksHolder? by lazy {
        if (findVulnerabilities) {
            VulnerabilityChecksHolder(vulnerabilityCheckDirectories, vulnerabilityChecksAnalysisSuffix, vulnerabilityChecksSuffix)
                .also { it.register(extraVulnerabilityChecks) }
        } else null
    }

    protected fun updateClassLoader(classpath: String) {
        if (this.classpath != classpath) {
            this.classpath = classpath
            val urls = classpath
                .split(File.pathSeparator)
                .map { uri ->
                    uri.toPath().toURL()
                }
                .toTypedArray()
            classLoader = URLClassLoader(urls)
        }
    }

    protected fun getWorkingDirectory(classFqn: String): Path? {
        val classRelativePath = PathUtil.classFqnToPath(classFqn) + ".class"
        val classAbsoluteURL = classLoader.getResource(classRelativePath) ?: return null
        val classAbsolutePath = PathUtil.replaceSeparator(classAbsoluteURL.toPath().toString())
            .removeSuffix(classRelativePath)
        return Paths.get(classAbsolutePath)
    }

    protected fun initializeGenerator(workingDirectory: Path): TestCaseGenerator {
        val classPathNormalized =
            classLoader.urLs.joinToString(separator = File.pathSeparator) { it.toPath().absolutePath }
        // TODO: SAT-1566 Set UtSettings parameters.
        UtSettings.treatOverflowAsError = treatOverflowAsError == TreatOverflowAsError.AS_ERROR

        return CyberTestCaseGenerator(
            listOf(workingDirectory),
            classPathNormalized,
            System.getProperty("java.class.path"),
            JdkInfoDefaultProvider().info,
            cyberPathSelector,
            findVulnerabilities,
            onlyVulnerabilities,
            statePublisher,
            vulnerabilityChecksHolder,
            analysedJar,
            cyberDefaultSelector,
            codeGen
        )
    }

    private fun initializeCodeGenerator(classUnderTest: ClassId): CodeGenerator {
        val generateWarningsForStaticMocking =
            forceStaticMocking == ForceStaticMocking.FORCE && staticsMocking is NoStaticMocking
        return CyberCodeGenerator(
            testFramework = testFramework,
            projectType = ProjectType.PureJvm,  // mb customizable later
            classUnderTest = classUnderTest,
            codegenLanguage = codegenLanguage,
            staticsMocking = staticsMocking,
            forceStaticMocking = forceStaticMocking,
            generateWarningsForStaticMocking = generateWarningsForStaticMocking,
            codeGen = codeGen
        )
    }

    protected open fun generateTestSets(
        testCaseGenerator: TestCaseGenerator,
        targetMethods: List<ExecutableId>,
        sourceCodeFile: Path? = null,
        searchDirectory: Path,
        chosenClassesToMockAlways: Set<ClassId>
    ): List<UtMethodTestSet> =
        testCaseGenerator.generate(
            targetMethods,
            mockStrategy,
            chosenClassesToMockAlways,
            generationTimeout
        ).let {
            if (sourceCodeFile != null) it.summarizeAll(searchDirectory, sourceCodeFile.toFile()) else it
        }

    protected open fun generateTest(
        classUnderTest: ClassId,
        testClassname: String,
        testSets: List<UtMethodTestSet>
    ): String =
        initializeCodeGenerator(
            classUnderTest
        ).generateAsString(testSets, testClassname)

    protected fun saveToFile(snippet: String, outputPath: String?) =
        outputPath?.let {
            Files.write(it.toPath(), listOf(snippet))
        }

    protected fun ClassId.targetMethods(): List<MethodId> =
        allMethods.filter { it.classId == this }.toList()

    protected fun now(): LocalDateTime = LocalDateTime.now()
}
