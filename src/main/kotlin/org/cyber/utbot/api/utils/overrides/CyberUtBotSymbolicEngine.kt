package org.cyber.utbot.api.utils.overrides

import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import org.cyber.utbot.api.utils.additions.pathSelector.cyberPathSelector
import org.cyber.utbot.api.utils.annotations.CyberModify
import org.cyber.utbot.api.utils.annotations.CyberNew
import org.cyber.utbot.api.utils.viewers.StatePublisher
import org.cyber.utbot.api.utils.vulnerability.VulnerabilityChecksHolder
import org.utbot.analytics.Predictors
import org.utbot.common.bracket
import org.utbot.common.debug
import org.utbot.engine.*
import org.utbot.engine.pc.UtSolver
import org.utbot.engine.pc.UtSolverStatusSAT
import org.utbot.engine.selectors.StrategyOption
import org.utbot.engine.selectors.nurs.NonUniformRandomSearch
import org.utbot.engine.selectors.pollUntilFastSAT
import org.utbot.engine.selectors.strategies.GraphViz
import org.utbot.engine.state.ExecutionStackElement
import org.utbot.engine.state.ExecutionState
import org.utbot.engine.state.StateLabel
import org.utbot.engine.symbolic.SymbolicState
import org.utbot.framework.UtSettings
import org.utbot.framework.UtSettings.processUnknownStatesDuringConcreteExecution
import org.utbot.framework.UtSettings.useDebugVisualization
import org.utbot.framework.plugin.api.*
import org.utbot.framework.plugin.api.util.description
import kotlin.system.measureTimeMillis


class CyberUtBotSymbolicEngine(
    controller: EngineController,
    methodUnderTest: ExecutableId,
    classpath: String,
    dependencyPaths: String,
    mockStrategy: MockStrategy = MockStrategy.NO_MOCKS,
    chosenClassesToMockAlways: Set<ClassId>,
    solverTimeoutInMillis: Int = UtSettings.checkSolverTimeoutMillis,
    cyberPathSelector: Boolean = false,
    findVulnerabilities: Boolean = true,
    private val onlyVulnerabilities: Boolean = true,
    private val statePublisher: StatePublisher = StatePublisher(),
    vulnerabilityChecksHolder: VulnerabilityChecksHolder = VulnerabilityChecksHolder(),
) : UtBotSymbolicEngine(controller, methodUnderTest, classpath, dependencyPaths, mockStrategy, chosenClassesToMockAlways, solverTimeoutInMillis) {
    init {  // set our selector
        if (cyberPathSelector) {
            pathSelector = cyberPathSelector(globalGraph, StrategyOption.DISTANCE) {
                withStepsLimit(UtSettings.pathSelectorStepsLimit)
            }
        }
        if (findVulnerabilities) {
            traverser = CyberTraverser(
                methodUnderTest,
                typeRegistry,
                hierarchy,
                typeResolver,
                globalGraph,
                mocker,
                vulnerabilityChecksHolder = vulnerabilityChecksHolder
            )
        }
    }

    @CyberModify("org/utbot/engine/UtBotSymbolicEngine.kt", "add StateViewer")
    override fun traverseImpl(): Flow<UtResult> = flow {

        require(trackableResources.isEmpty())

        if (useDebugVisualization) GraphViz(globalGraph, pathSelector)

        val initStmt = graph.head
        val initState = ExecutionState(
            initStmt,
            SymbolicState(UtSolver(typeRegistry, trackableResources, solverTimeoutInMillis)),
            executionStack = persistentListOf(ExecutionStackElement(null, method = graph.body.method))
        )

        pathSelector.offer(initState)

        pathSelector.use {

            while (currentCoroutineContext().isActive) {
                if (controller.stop)
                    break

                if (controller.paused) {
                    try {
                        yield()
                    } catch (e: CancellationException) { //todo in future we should just throw cancellation
                        break
                    }
                    continue
                }

                stateSelectedCount++
                pathLogger.trace {
                    "traverse<$methodUnderTest>: choosing next state($stateSelectedCount), " +
                            "queue size=${(pathSelector as? NonUniformRandomSearch)?.size ?: -1}"
                }

                if (controller.executeConcretely || statesForConcreteExecution.isNotEmpty()) {
                    val state = pathSelector.pollUntilFastSAT()
                        ?: statesForConcreteExecution.pollUntilSat(processUnknownStatesDuringConcreteExecution)
                        ?: break
                    // This state can contain inconsistent wrappers - for example, Map with keys but missing values.
                    // We cannot use withWrapperConsistencyChecks here because it needs solver to work.
                    // So, we have to process such cases accurately in wrappers resolving.

                    logger.trace { "executing $state concretely..." }


                    logger.debug().bracket("concolicStrategy<$methodUnderTest>: execute concretely") {
                        val resolver = Resolver(
                            hierarchy,
                            state.memory,
                            typeRegistry,
                            typeResolver,
                            state.solver.lastStatus as UtSolverStatusSAT,
                            methodUnderTest,
                            softMaxArraySize,
                            traverser.objectCounter
                        )

                        val resolvedParameters = state.methodUnderTestParameters
                        val (modelsBefore, _, instrumentation) = resolver.resolveModels(resolvedParameters)
                        val stateBefore = modelsBefore.constructStateForMethod(methodUnderTest)

                        try {
                            val concreteExecutionResult =
                                concreteExecutor.executeConcretely(methodUnderTest, stateBefore, instrumentation)

                            if (concreteExecutionResult.violatesUtMockAssumption()) {
                                logger.debug { "Generated test case violates the UtMock assumption: $concreteExecutionResult" }
                                return@bracket
                            }

                            val concreteUtExecution = UtSymbolicExecution(
                                stateBefore,
                                concreteExecutionResult.stateAfter,
                                concreteExecutionResult.result,
                                instrumentation,
                                mutableListOf(),
                                listOf(),
                                concreteExecutionResult.coverage
                            )
                            emit(concreteUtExecution)

                            logger.debug { "concolicStrategy<${methodUnderTest}>: returned $concreteUtExecution" }
                        } catch (e: CancellationException) {
                            logger.debug(e) { "Cancellation happened" }
                        } catch (e: ConcreteExecutionFailureException) {
                            emitFailedConcreteExecutionResult(stateBefore, e)
                        } catch (e: Throwable) {
                            emit(UtError("Concrete execution failed", e))
                        }
                    }

                } else {
                    val state = pathSelector.poll()

                    // state is null in case states queue is empty
                    // or path selector exceed some limits (steps limit, for example)
                    if (state == null) {
                        // check do we have remaining states that we can execute concretely
                        val pathSelectorStatesForConcreteExecution = pathSelector
                            .remainingStatesForConcreteExecution
                            .map { it.withWrapperConsistencyChecks() }
                        if (pathSelectorStatesForConcreteExecution.isNotEmpty()) {
                            statesForConcreteExecution += pathSelectorStatesForConcreteExecution
                            logger.debug {
                                "${pathSelectorStatesForConcreteExecution.size} remaining states " +
                                        "were moved from path selector for concrete execution"
                            }
                            continue // the next step in while loop processes concrete states
                        } else {
                            break
                        }
                    }

                    state.executingTime += measureTimeMillis {
                        val newStates = try {
                            traverser.traverse(state)
                        } catch (ex: Throwable) {
                            emit(UtError(ex.description, ex))
                            return@measureTimeMillis
                        }
                        for (newState in newStates) {
                            @CyberNew("view new states") statePublisher.viewUpdate(newState)
                            when (newState.label) {
                                StateLabel.INTERMEDIATE -> pathSelector.offer(newState)
                                StateLabel.CONCRETE -> statesForConcreteExecution.add(newState)
                                StateLabel.TERMINAL -> consumeTerminalState(newState)
                            }
                        }

                        // Here job can be cancelled from within traverse, e.g. by using force mocking without Mockito.
                        // So we need to make it throw CancelledException by method below:
                        currentCoroutineContext().job.ensureActive()
                    }

                    // TODO: think about concise modifying globalGraph in Traverser and UtBotSymbolicEngine
                    globalGraph.visitNode(state)
                }
            }
        }
    }

    @CyberModify("org/utbot/engine/UtBotSymbolicEngine.kt", "filter terminal states")
    override suspend fun FlowCollector<UtResult>.consumeTerminalState(
        state: ExecutionState,
    ) {
        // some checks to be sure the state is correct
        require(state.label == StateLabel.TERMINAL) { "Can't process non-terminal state!" }
        require(!state.isInNestedMethod()) { "The state has to correspond to the MUT" }

        val memory = state.memory
        val solver = state.solver
        val parameters = state.parameters.map { it.value }
        val symbolicResult = requireNotNull(state.methodResult?.symbolicResult) { "The state must have symbolicResult" }
        val holder = requireNotNull(solver.lastStatus as? UtSolverStatusSAT) { "The state must be SAT!" }

        val predictedTestName = Predictors.testName.predict(state.path)
        Predictors.testName.provide(state.path, predictedTestName, "")

        // resolving
        val resolver = Resolver(
            hierarchy,
            memory,
            typeRegistry,
            typeResolver,
            holder,
            methodUnderTest,
            softMaxArraySize,
            traverser.objectCounter
        )

        val (modelsBefore, modelsAfter, instrumentation) = resolver.resolveModels(parameters)

        val symbolicExecutionResult = resolver.resolveResult(symbolicResult)

        @CyberNew("filter emit flag") val needEmit = !onlyVulnerabilities || isVulnerability(symbolicExecutionResult)

        val stateBefore = modelsBefore.constructStateForMethod(methodUnderTest)
        val stateAfter = modelsAfter.constructStateForMethod(methodUnderTest)
        require(stateBefore.parameters.size == stateAfter.parameters.size)

        val symbolicUtExecution = UtSymbolicExecution(
            stateBefore = stateBefore,
            stateAfter = stateAfter,
            result = symbolicExecutionResult,
            instrumentation = instrumentation,
            path = entryMethodPath(state),
            fullPath = state.fullPath()
        )

        globalGraph.traversed(state)

        if (!UtSettings.useConcreteExecution ||
            // Can't execute concretely because overflows do not cause actual exceptions.
            // Still, we need overflows to act as implicit exceptions.
            (UtSettings.treatOverflowAsError && symbolicExecutionResult is UtOverflowFailure)
        ) {
            logger.debug {
                "processResult<${methodUnderTest}>: no concrete execution allowed, " +
                        "emit purely symbolic result $symbolicUtExecution"
            }
            @CyberNew("filter emit") if (!needEmit) return
            emit(symbolicUtExecution)
            return
        }

        // Check for lambda result as it cannot be emitted by concrete execution
        (symbolicExecutionResult as? UtExecutionSuccess)?.takeIf { it.model is UtLambdaModel }?.run {
            logger.debug {
                "processResult<${methodUnderTest}>: impossible to create concrete value for lambda result ($model), " +
                        "emit purely symbolic result $symbolicUtExecution"
            }

            emit(symbolicUtExecution)
            return
        }

        //It's possible that symbolic and concrete stateAfter/results are diverged.
        //So we trust concrete results more.
        try {
            logger.debug().bracket("processResult<$methodUnderTest>: concrete execution") {

                //this can throw CancellationException
                val concreteExecutionResult = concreteExecutor.executeConcretely(
                    methodUnderTest,
                    stateBefore,
                    instrumentation
                )

                if (concreteExecutionResult.violatesUtMockAssumption()) {
                    logger.debug { "Generated test case violates the UtMock assumption: $concreteExecutionResult" }
                    return
                }

                val concolicUtExecution = symbolicUtExecution.copy(
                    stateAfter = concreteExecutionResult.stateAfter,
                    result = concreteExecutionResult.result,
                    coverage = concreteExecutionResult.coverage
                )

                @CyberNew("filter emit") if (!needEmit) return
                emit(concolicUtExecution)
                logger.debug { "processResult<${methodUnderTest}>: returned $concolicUtExecution" }
            }
        } catch (e: ConcreteExecutionFailureException) {
            emitFailedConcreteExecutionResult(stateBefore, e)
        }
    }
}
