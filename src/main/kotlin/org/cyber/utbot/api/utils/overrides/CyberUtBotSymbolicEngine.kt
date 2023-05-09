package org.cyber.utbot.api.utils.overrides

import kotlinx.collections.immutable.persistentListOf
import kotlinx.coroutines.*
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.FlowCollector
import kotlinx.coroutines.flow.flow
import org.cyber.utbot.api.utils.additions.pathSelector.CyberSelector
import mu.KotlinLogging
import org.cyber.utbot.api.exceptions.CyberException
import org.cyber.utbot.api.utils.additions.classState.StateHolder
import org.cyber.utbot.api.utils.additions.pathSelector.cyberPathSelector
import org.cyber.utbot.api.utils.annotations.CyberModify
import org.cyber.utbot.api.utils.annotations.CyberNew
import org.cyber.utbot.api.utils.annotations.CyberNotModify
import org.cyber.utbot.api.utils.overrides.fuzzing.cyberRunJavaFuzzing
import org.cyber.utbot.api.utils.viewers.StatePublisher
import org.cyber.utbot.api.utils.vulnerability.VulnerabilityChecksHolder
import org.utbot.analytics.Predictors
import org.utbot.common.debug
import org.utbot.common.measureTime
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
import org.utbot.engine.util.mockListeners.MockListenerController
import org.utbot.framework.UtSettings
import org.utbot.framework.UtSettings.processUnknownStatesDuringConcreteExecution
import org.utbot.framework.UtSettings.useDebugVisualization
import org.utbot.framework.plugin.api.*
import org.utbot.framework.plugin.api.util.*
import org.utbot.framework.util.classesToLoad
import org.utbot.fuzzer.UtFuzzedExecution
import org.utbot.fuzzer.collectConstantsForFuzzer
import org.utbot.fuzzing.*
import org.utbot.fuzzing.utils.Trie
import org.utbot.instrumentation.instrumentation.execution.UtConcreteExecutionResult
import soot.tagkit.ParamNamesTag
import java.lang.reflect.Method
import kotlin.math.min
import kotlin.system.measureTimeMillis

@CyberNotModify("org/utbot/engine/UtBotSymbolicEngine.kt", "shold be private in UtBotSymbolicEngine")
private val logger = KotlinLogging.logger {}

class CyberUtBotSymbolicEngine(
    controller: EngineController,
    methodUnderTest: ExecutableId,
    classpath: String,
    dependencyPaths: String,
    mockStrategy: MockStrategy = MockStrategy.NO_MOCKS,
    chosenClassesToMockAlways: Set<ClassId>,
    applicationContext: ApplicationContext,
    solverTimeoutInMillis: Int = UtSettings.checkSolverTimeoutMillis,
    cyberPathSelector: Boolean = false,
    findVulnerabilities: Boolean = true,
    private var onlyVulnerabilities: Boolean = true,
    private val statePublisher: StatePublisher = StatePublisher(),
    vulnerabilityChecksHolder: VulnerabilityChecksHolder?,
    analysedJar: String,
    cyberDefaultSelector: Boolean,
    private val stateHolder: StateHolder?,
) : UtBotSymbolicEngine(controller, methodUnderTest, classpath, dependencyPaths, mockStrategy, chosenClassesToMockAlways, solverTimeoutInMillis, applicationContext) {
    init {  // set our selector
        pathSelector = if (cyberPathSelector) {
            cyberPathSelector(globalGraph, StrategyOption.DISTANCE, analysedJar, cyberDefaultSelector) {
                withStepsLimit(UtSettings.pathSelectorStepsLimit)
            }
        } else {
            pathSelector(globalGraph, typeRegistry)
        }
        if (findVulnerabilities) {
            mocker = CyberMocker(
                mockStrategy,
                classUnderTest,
                hierarchy,
                chosenClassesToMockAlways,
                MockListenerController(controller),
                applicationContext = applicationContext
            )
            traverser = CyberTraverser(
                methodUnderTest,
                typeRegistry,
                hierarchy,
                typeResolver,
                globalGraph,
                mocker,
                applicationContext = applicationContext,
                vulnerabilityChecksHolder = vulnerabilityChecksHolder,
                stateHolder = stateHolder ?: throw CyberException("CyberUtBotSymbolicEngine init fail")
            )

            // for overrides
            classesToLoad = classesToLoad.plus(arrayOf(
                // add all overrides here!!!
                org.cyber.utils.overrides.CyberPath::class,
            ).map { it.java }.toTypedArray())

//            classToWrapper += (mutableMapOf<TypeToBeWrapped, WrapperType>().apply {
//                // add all overrides here!!!
//            }.apply {
//                val applicationClassLoader = UtContext::class.java.classLoader
//                values.distinct().forEach {
//                    val kClass = applicationClassLoader.loadClass(it.className).kotlin
//                    putSootClass(kClass, it)
//                }
//            })

//            wrappers = wrappers + mutableMapOf(
//                // add all overrides here!!!
//            ).apply {
//                arrayOf(
//                    // add all overrides here!!!
//                ).let { putAll(it) }
//            }.also {
//                val missedWrappers = it.keys.filterNot { key ->
//                    Scene.v().getSootClass(key.name).type in classToWrapper.keys
//                }
//
//                require(missedWrappers.isEmpty()) {
//                    "Missed wrappers for classes [${missedWrappers.joinToString(", ")}]"
//                }
//            }
        } else {
            if (onlyVulnerabilities) {
                onlyVulnerabilities = false
                logger.warn { "ignore onlyVulnerabilities because findVulnerabilities is false" }
            }
        }
    }

//    @CyberNew("update CodeGen info")
//    private fun updateCodeGenInfo(stateBefore: EnvironmentModels, parameters: List<SymbolicValue>, resolver: Resolver) {
//        val params = if (stateBefore.parameters.size == parameters.size) parameters else parameters.drop(1)
//        require(stateBefore.parameters.size == params.size) { "update CodeGen info fail" }
//        stateHolder?.updateCodeGenInfo(stateBefore, params.map { it.addr }) { value -> resolver.resolveModel(value) }
//    }

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

        @CyberNew("inform selector about the start of a new selection iteration & add taint endpoints")
        if (pathSelector is CyberSelector && traverser is CyberTraverser) {
            (traverser as CyberTraverser).taintEndPoints.clear()
            (traverser as CyberTraverser).taintEndPoints.putAll((pathSelector as CyberSelector).onNextIteration(initState).endPointToSinks)
        }

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


                    logger.debug().measureTime({ "concolicStrategy<$methodUnderTest>: execute concretely"} ) {
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
                                concreteExecutor.executeConcretely(methodUnderTest, stateBefore, instrumentation, UtSettings.concreteExecutionDefaultTimeoutInInstrumentedProcessMillis)

                            if (concreteExecutionResult.violatesUtMockAssumption()) {
                                logger.debug { "Generated test case violates the UtMock assumption: $concreteExecutionResult" }
                                return@measureTime
                            }

//                            @CyberNew("update CodeGen info") updateCodeGenInfo(stateBefore, resolvedParameters, resolver)

                            val concreteUtExecution = UtSymbolicExecution(
                                stateBefore,
                                concreteExecutionResult.stateAfter,
                                concreteExecutionResult.result,
                                instrumentation,
                                mutableListOf(),
                                listOf(),
                                concreteExecutionResult.coverage
                            )
                            @CyberModify("filter emit") if (!onlyVulnerabilities) {
                                emit(concreteUtExecution)
                            }

                            logger.debug { "concolicStrategy<${methodUnderTest}>: returned $concreteUtExecution" }
                        } catch (e: CancellationException) {
                            logger.debug(e) { "Cancellation happened" }
                        } catch (e: InstrumentedProcessDeathException) {
                            @CyberModify("filter emit") if (!onlyVulnerabilities) {
                                emitFailedConcreteExecutionResult(stateBefore, e)
                            }
                        } catch (e: Throwable) {
                            @CyberModify("filter emit") if (!onlyVulnerabilities) {
                                emit(UtError("Concrete execution failed", e))
                            }
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
                            traverser.engine = this@CyberUtBotSymbolicEngine
                            traverser.traverse(state)
                        } catch (ex: Throwable) {
                            @CyberModify("filter emit") if (!onlyVulnerabilities) {
                                emit(UtError(ex.description, ex))
                            }
                            return@measureTimeMillis
                        }
                        for (newState in newStates) {
                            @CyberNew("view new states") statePublisher.viewUpdate(newState)
                            when (newState.label) {
                                StateLabel.INTERMEDIATE -> pathSelector.offer(newState)
                                StateLabel.CONCRETE -> statesForConcreteExecution.add(newState)
                                StateLabel.TERMINAL -> {
                                    @CyberNew("ignore terminal state if the trace was not found yet")
//                                    if (pathSelector is CyberSelector && !(pathSelector as CyberSelector).defaultSelection) {
//                                        if (!(pathSelector as CyberSelector).traceFound()) {
//                                            println("continued(((")
//                                            continue
//                                        }
//
//                                    }
                                    println("TERMINAL: ${newState.stmt}, from ${state.stmt}, method: ${graph.body}")
                                    consumeTerminalState(newState)
                                }
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
    override suspend fun FlowCollector<UtResult>.consumeTerminalState(state: ExecutionState) {
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

        @CyberNew("filter emit flag") val needEmit = !onlyVulnerabilities || isVulnerability(state.path)

        val stateBefore = modelsBefore.constructStateForMethod(methodUnderTest)
        val stateAfter = modelsAfter.constructStateForMethod(methodUnderTest)
        require(stateBefore.parameters.size == stateAfter.parameters.size)

//        @CyberNew("update CodeGen info") updateCodeGenInfo(stateBefore, parameters, resolver)

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

            @CyberModify("filter emit") if (onlyVulnerabilities) return
            emit(symbolicUtExecution)
            return
        }

        if (checkStaticMethodsMock(symbolicUtExecution)) {
            logger.debug {
                buildString {
                    append("processResult<${methodUnderTest}>: library static methods mock found ")
                    append("(we do not support it in concrete execution yet), ")
                    append("emit purely symbolic result $symbolicUtExecution")
                }
            }

            @CyberModify("filter emit") if (!needEmit) return
            emit(symbolicUtExecution)
            return
        }


        //It's possible that symbolic and concrete stateAfter/results are diverged.
        //So we trust concrete results more.
        try {
            logger.debug().measureTime({ "processResult<$methodUnderTest>: concrete execution" } ) {

                //this can throw CancellationException
                val concreteExecutionResult = concreteExecutor.executeConcretely(
                    methodUnderTest,
                    stateBefore,
                    instrumentation,
                    UtSettings.concreteExecutionDefaultTimeoutInInstrumentedProcessMillis
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
        } catch (e: InstrumentedProcessDeathException) {
            @CyberModify("filter emit") if (onlyVulnerabilities) return
            emitFailedConcreteExecutionResult(stateBefore, e)
        } catch (e: CancellationException) {
            logger.debug(e) { "Cancellation happened" }
        } catch (e: Throwable) {
            @CyberModify("filter emit") if (onlyVulnerabilities) return
            emit(UtError("Default concrete execution failed", e));
        }
    }

    /**
     * Run fuzzing flow.
     *
     * @param until is used by fuzzer to cancel all tasks if the current time is over this value
     * @param transform provides model values for a method
     */
    @CyberModify("replace original java language fuzzing by cyber fuzzing")
    override fun fuzzing(until: Long, context: UtContext, transform: (JavaValueProvider) -> JavaValueProvider) = withUtContext(context) {
        flow {
            val isFuzzable = methodUnderTest.parameters.all { classId ->
                classId != Method::class.java.id && // causes the instrumented process crash at invocation
                        classId != Class::class.java.id  // causes java.lang.IllegalAccessException: java.lang.Class at sun.misc.Unsafe.allocateInstance(Native Method)
            }
            val hasMethodUnderTestParametersToFuzz = methodUnderTest.parameters.isNotEmpty()
            if (!isFuzzable || !hasMethodUnderTestParametersToFuzz && methodUnderTest.isStatic) {
                // Currently, fuzzer doesn't work with static methods with empty parameters
                return@flow
            }
            val errorStackTraceTracker = Trie(StackTraceElement::toString)
            var attempts = 0
            val attemptsLimit = UtSettings.fuzzingMaxAttempts
            val names = graph.body.method.tags.filterIsInstance<ParamNamesTag>().firstOrNull()?.names ?: emptyList()
            var testEmittedByFuzzer = 0
            cyberRunJavaFuzzing(
                defaultIdGenerator,
                methodUnderTest,
                collectConstantsForFuzzer(graph),
                names,
                listOf(transform(ValueProvider.of(org.cyber.utbot.api.utils.overrides.fuzzing.defaultValueProviders(defaultIdGenerator))))
                // todo fst info here
            ) { thisInstance, descr, values ->
                println("values: ")
                values.forEach { println(it.summary) }
                if (thisInstance?.model is UtNullModel) {
                    // We should not try to run concretely any models with null-this.
                    // But fuzzer does generate such values, because it can fail to generate any "good" values.
                    return@cyberRunJavaFuzzing BaseFeedback(Trie.emptyNode(), Control.PASS)
                }

                val diff = until - System.currentTimeMillis() + 3000
                val thresholdMillisForFuzzingOperation = 0 // may be better use 10-20 millis as it might not be possible
                // to concretely execute that values because request to instrumentation process involves
                // 1. serializing/deserializing it with kryo
                // 2. sending over rd
                // 3. concrete execution itself
                // 4. analyzing concrete result
                if (controller.job?.isActive == false || diff <= thresholdMillisForFuzzingOperation) {
                    println("diff: $diff, threshhold: $thresholdMillisForFuzzingOperation")
                    logger.info { "Fuzzing overtime: $methodUnderTest" }
                    logger.info { "Test created by fuzzer: $testEmittedByFuzzer" }
                    return@cyberRunJavaFuzzing BaseFeedback(result = Trie.emptyNode(), control = Control.STOP)
                }

                val initialEnvironmentModels = EnvironmentModels(thisInstance?.model, values.map { it.model }, mapOf())

                val concreteExecutionResult: UtConcreteExecutionResult? = try {
                    val timeoutMillis = min(UtSettings.concreteExecutionDefaultTimeoutInInstrumentedProcessMillis, diff)
                    concreteExecutor.executeConcretely(
                        methodUnderTest,
                        initialEnvironmentModels,
                        listOf(),
                        timeoutMillis
                    )
                } catch (e: CancellationException) {
                    logger.debug { "Cancelled by timeout" }; null
                } catch (e: InstrumentedProcessDeathException) {
                    emitFailedConcreteExecutionResult(initialEnvironmentModels, e); null
                } catch (e: Throwable) {
                    emit(UtError("Default concrete execution failed", e)); null
                }

                // in case an exception occurred from the concrete execution
                concreteExecutionResult ?: return@cyberRunJavaFuzzing BaseFeedback(
                    result = Trie.emptyNode(),
                    control = Control.PASS
                )
                println("concrete result: ${concreteExecutionResult.result}")

                if (concreteExecutionResult.violatesUtMockAssumption()) {
                    logger.debug { "Generated test case by fuzzer violates the UtMock assumption: $concreteExecutionResult" }
                    return@cyberRunJavaFuzzing BaseFeedback(result = Trie.emptyNode(), control = Control.PASS)
                }
                println("here1")

                val coveredInstructions = concreteExecutionResult.coverage.coveredInstructions
                var trieNode: Trie.Node<Instruction>? = null
                if (coveredInstructions.isNotEmpty()) {
                    println("here2")
                    trieNode = descr.tracer.add(coveredInstructions)
                    if (trieNode.count > 100) {
                        println("here3")
                        if (++attempts >= attemptsLimit) {
                            println("here4")
                            return@cyberRunJavaFuzzing BaseFeedback(result = Trie.emptyNode(), control = Control.STOP)
                        }
                        println("here5")
                        return@cyberRunJavaFuzzing BaseFeedback(result = trieNode, control = Control.CONTINUE)
                    }
                } else {
                    println("here6")
                    logger.error { "Coverage is empty for $methodUnderTest with $values" }
                    val result = concreteExecutionResult.result
                    if (result is UtSandboxFailure) {
                        println("here7")
                        val stackTraceElements = result.exception.stackTrace.reversed()
                        if (errorStackTraceTracker.add(stackTraceElements).count > 1) {
                            return@cyberRunJavaFuzzing BaseFeedback(result = Trie.emptyNode(), control = Control.PASS)
                        }
                    }
                }

                println("EMIT ${values[0].summary}, ${values[1].summary}")
                emit(
                    UtFuzzedExecution(
                        stateBefore = initialEnvironmentModels,
                        stateAfter = concreteExecutionResult.stateAfter,
                        result = concreteExecutionResult.result,
                        coverage = concreteExecutionResult.coverage,
                        fuzzingValues = values,
                        fuzzedMethodDescription = descr.description
                    )
                )

                testEmittedByFuzzer++
                BaseFeedback(result = trieNode ?: Trie.emptyNode(), control = Control.CONTINUE)
            }
        }
    }

}
