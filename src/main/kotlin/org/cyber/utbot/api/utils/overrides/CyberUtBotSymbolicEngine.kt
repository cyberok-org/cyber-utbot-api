package org.cyber.utbot.api.utils.overrides

import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import org.cyber.utbot.api.utils.additions.pathSelector.cyberPathSelector
import org.cyber.utbot.api.utils.annotations.CyberModify
import org.cyber.utbot.api.utils.annotations.CyberNew
import org.cyber.utbot.api.utils.viewers.StatePublisher
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
    private val statePublisher: StatePublisher = StatePublisher(),
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
            )
        }
    }

    @CyberModify("org/utbot/engine/UtBotSymbolicEngine.kt", "add StateViewer")
    override fun traverseImpl(): Flow<UtResult> = flow {

        require(trackableResources.isEmpty())

        if (UtSettings.useDebugVisualization) GraphViz(globalGraph, pathSelector)

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
                        ?: statesForConcreteExecution.pollUntilSat(UtSettings.processUnknownStatesDuringConcreteExecution)
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
                            softMaxArraySize
                        )

                        val resolvedParameters = state.methodUnderTestParameters
                        val (modelsBefore, _, instrumentation) = resolver.resolveModels(resolvedParameters)
                        val stateBefore = modelsBefore.constructStateForMethod(methodUnderTest)

                        try {
                            val concreteExecutionResult =
                                concreteExecutor.executeConcretely(methodUnderTest, stateBefore, instrumentation)

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
}
