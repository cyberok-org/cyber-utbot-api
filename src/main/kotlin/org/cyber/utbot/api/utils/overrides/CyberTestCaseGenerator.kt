package org.cyber.utbot.api.utils.overrides
import mu.KLogger
import mu.KotlinLogging
import org.cyber.utbot.api.utils.additions.classState.codeGeneration.CodeGen
import org.cyber.utbot.api.utils.annotations.CyberModify
import org.cyber.utbot.api.utils.viewers.StatePublisher
import org.cyber.utbot.api.utils.vulnerability.VulnerabilityChecksHolder
import org.utbot.engine.EngineController
import org.utbot.engine.UtBotSymbolicEngine
import org.utbot.framework.plugin.api.*
import org.utbot.framework.plugin.services.JdkInfo
import org.utbot.framework.util.toModel
import java.nio.file.Path


open class CyberTestCaseGenerator(
    buildDirs: List<Path>,
    classpath: String?,
    dependencyPaths: String,
    jdkInfo: JdkInfo,
    private val cyberPathSelector: Boolean,
    private val findVulnerabilities: Boolean,
    private val onlyVulnerabilities: Boolean,
    private val statePublisher: StatePublisher,
    private val vulnerabilityChecksHolder: VulnerabilityChecksHolder?,
    private val codeGen: CodeGen?
) : TestCaseGenerator(buildDirs, classpath, dependencyPaths, jdkInfo) {
    private val logger: KLogger = KotlinLogging.logger {}

    @CyberModify("org/utbot/framework/plugin/api/TestCaseGenerator.kt", "CyberUtBotSymbolicEngine instead of UtBotSymbolicEngine")
    override fun createSymbolicEngine(
        controller: EngineController,
        method: ExecutableId,
        mockStrategyApi: MockStrategyApi,
        chosenClassesToMockAlways: Set<ClassId>,
        applicationContext: ApplicationContext,
        executionTimeEstimator: ExecutionTimeEstimator
    ): UtBotSymbolicEngine {
        logger.debug("Starting symbolic execution for $method  --$mockStrategyApi--")
        return CyberUtBotSymbolicEngine(
            controller,
            method,
            classpathForEngine,
            dependencyPaths = dependencyPaths,
            mockStrategy = mockStrategyApi.toModel(),
            chosenClassesToMockAlways = chosenClassesToMockAlways,
            applicationContext = applicationContext,
            solverTimeoutInMillis = executionTimeEstimator.updatedSolverCheckTimeoutMillis,
            cyberPathSelector,
            findVulnerabilities,
            onlyVulnerabilities,
            statePublisher = statePublisher,
            vulnerabilityChecksHolder = vulnerabilityChecksHolder,
            codeGen = codeGen
        )
    }
}
