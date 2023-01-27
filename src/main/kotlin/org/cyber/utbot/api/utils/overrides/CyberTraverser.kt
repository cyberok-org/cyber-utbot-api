package org.cyber.utbot.api.utils.overrides

import org.cyber.utbot.api.utils.annotations.CyberModify
import org.cyber.utbot.api.utils.annotations.CyberNew
import org.cyber.utbot.api.utils.annotations.CyberNote
import org.cyber.utbot.api.utils.vulnerability.stateUpdates
import org.utbot.common.WorkaroundReason
import org.utbot.common.workaround
import org.utbot.engine.*
import org.utbot.engine.Hierarchy
import org.utbot.engine.pc.*
import org.utbot.engine.state.StateLabel
import org.utbot.engine.state.withLabel
import org.utbot.engine.symbolic.SymbolicStateUpdate
import org.utbot.engine.symbolic.asAssumption
import org.utbot.engine.symbolic.asHardConstraint
import org.utbot.engine.symbolic.asSoftConstraint
import org.utbot.engine.types.TypeRegistry
import org.utbot.engine.types.TypeResolver
import org.utbot.framework.UtSettings
import org.utbot.framework.plugin.api.ExecutableId
import org.utbot.framework.util.executableId
import org.utbot.framework.util.graph
import soot.*
import soot.jimple.internal.JDynamicInvokeExpr
import soot.jimple.internal.JSpecialInvokeExpr
import soot.jimple.internal.JStaticInvokeExpr
import soot.toolkits.graph.ExceptionalUnitGraph


@CyberNote("perhaps in some places adding constraints is unnecessary, this will be gradually corrected")
class CyberTraverser(
    methodUnderTest: ExecutableId,
    typeRegistry: TypeRegistry,
    hierarchy: Hierarchy,
    typeResolver: TypeResolver,
    globalGraph: InterProceduralUnitGraph,
    mocker: Mocker
) : Traverser(methodUnderTest, typeRegistry, hierarchy, typeResolver, globalGraph, mocker) {
    @CyberNote("should be null before each operation \"traverse\"")
    private var invokeStateUpdate: SymbolicStateUpdate? = null

    @CyberNew("add vulnerability constraints")
    private fun updateVulnerabilityStates(instance: SymbolicValue?, method: SootMethod, resolvedParameters: List<SymbolicValue>) {
//        logger.info { "updateVulnerabilityStates ${instance?.type?.toString()} to ${method.name}" }
        stateUpdates[instance?.type?.toString() to method.name]?.let { func ->
            val symbolicStateUpdate = func(resolvedParameters)
            logger.info { "updateVulnerabilityState $symbolicStateUpdate" }
            invokeStateUpdate = symbolicStateUpdate
        }
    }

    @CyberModify("org/utbot/engine/Traverser.kt", "add update invokeStateUpdate")
    override fun TraversalContext.staticInvoke(invokeExpr: JStaticInvokeExpr): List<MethodResult> {
        val parameters = resolveParameters(invokeExpr.args, invokeExpr.method.parameterTypes)
        val result = mockMakeSymbolic(invokeExpr) ?: mockStaticMethod(invokeExpr.method, parameters)

        if (result != null) return result

        val method = invokeExpr.retrieveMethod()
        @CyberNew("add vulnerability constraints") updateVulnerabilityStates(null, method, parameters)
        val invocation = Invocation(null, method, parameters, InvocationTarget(null, method))
        return commonInvokePart(invocation)
    }

    @CyberModify("org/utbot/engine/Traverser.kt", "add update invokeStateUpdate")
    /**
     * Identifies different invocation targets by finding all overloads of invoked method.
     * Each target defines/reduces object type to set of concrete (not abstract, not interface)
     * classes with particular method implementation.
     */
    override fun TraversalContext.virtualAndInterfaceInvoke(
        base: Value,
        methodRef: SootMethodRef,
        parameters: List<Value>
    ): List<MethodResult> {
        val instance = resolve(base)
        if (instance !is ReferenceValue) error("We cannot run $methodRef on $instance")

        nullPointerExceptionCheck(instance.addr)

        if (instance.isNullObject()) return emptyList() // Nothing to call

        val method = methodRef.resolve()
        val resolvedParameters = resolveParameters(parameters, method.parameterTypes)
        @CyberNew("add vulnerability constraints") updateVulnerabilityStates(instance, method, resolvedParameters)
        val invocation = Invocation(instance, method, resolvedParameters) {
            when (instance) {
                is ObjectValue -> findInvocationTargets(instance, methodRef.subSignature.string)
                is ArrayValue -> listOf(InvocationTarget(instance, method))
            }
        }
        return commonInvokePart(invocation)
    }

    @CyberModify("org/utbot/engine/Traverser.kt", "add update invokeStateUpdate")
    override fun TraversalContext.specialInvoke(invokeExpr: JSpecialInvokeExpr): List<MethodResult> {
        val instance = resolve(invokeExpr.base)
        if (instance !is ReferenceValue) error("We cannot run ${invokeExpr.methodRef} on $instance")

        nullPointerExceptionCheck(instance.addr)

        if (instance.isNullObject()) return emptyList() // Nothing to call

        val method = invokeExpr.retrieveMethod()
        val parameters = resolveParameters(invokeExpr.args, method.parameterTypes)
        @CyberNew("add vulnerability constraints") updateVulnerabilityStates(instance, method, parameters)
        val invocation = Invocation(instance, method, parameters, InvocationTarget(instance, method))

        // Calls with super syntax are represented by invokeSpecial instruction, but we don't support them in wrappers
        // TODO: https://github.com/UnitTestBot/UTBotJava/issues/819 -- Support super calls in inherited wrappers
        return commonInvokePart(invocation)
    }

    @CyberModify("org/utbot/engine/Traverser.kt", "add update invokeStateUpdate")
    override fun TraversalContext.dynamicInvoke(invokeExpr: JDynamicInvokeExpr): List<MethodResult> {
        val invocation = with(dynamicInvokeResolver) { resolveDynamicInvoke(invokeExpr) }

        if (invocation == null) {
            workaround(WorkaroundReason.HACK) {
                logger.warn { "Marking state as a concrete, because of an unknown dynamic invoke instruction: $invokeExpr" }
                // The engine does not yet support JDynamicInvokeExpr, so switch to concrete execution if we encounter it
                offerState(environment.state.withLabel(StateLabel.CONCRETE))
                queuedSymbolicStateUpdates += UtFalse.asHardConstraint()
                return emptyList()
            }
        } else @CyberNew("add vulnerability constraints") updateVulnerabilityStates(invocation.instance, invocation.method, invocation.parameters)

        return commonInvokePart(invocation)
    }

    @CyberModify("org/utbot/engine/Traverser.kt", "add invokeStateUpdate to MethodResult")
    override fun TraversalContext.commonInvokePart(invocation: Invocation): List<MethodResult> {
        val method = invocation.method.executableId

        // This code is supposed to support generic information from signatures for nested methods.
        // If we have some method 'foo` and a method `bar(List<Integer>), and inside `foo`
        // there is an invocation `bar(object)`, this object must have information about
        // its `Integer` generic type.
        invocation.parameters.forEachIndexed { index, param ->
            if (param !is ReferenceValue) return@forEachIndexed

            updateGenericTypeInfoFromMethod(method, param, parameterIndex = index + 1)
        }

        if (invocation.instance != null) {
            updateGenericTypeInfoFromMethod(method, invocation.instance!!, parameterIndex = 0)
        }

        /**
         * First, check if there is override for the invocation itself, not for the targets.
         *
         * Note that calls with super syntax are represented by invokeSpecial instruction, but we don't support them in wrappers,
         * so here we resolve [invocation] to the inherited method invocation if it's something like:
         *
         * ```java
         * public class InheritedWrapper extends BaseWrapper {
         *     public void add(Object o) {
         *         // some stuff
         *         super.add(o); // this resolves to InheritedWrapper::add instead of BaseWrapper::add
         *     }
         * }
         * ```
         *
         * TODO: https://github.com/UnitTestBot/UTBotJava/issues/819 -- Support super calls in inherited wrappers
         */
        val artificialMethodOverride = overrideInvocation(invocation, target = null)

        // The problem here is that we might have an unsat current state.
        // We get states with `SAT` last status only for traversing,
        // but during the parameters resolving this status might be changed.
        // It happens inside the `org.utbot.engine.Traverser.initStringLiteral` function.
        // So, if it happens, we should just drop the state we processing now.
        if (environment.state.solver.lastStatus is UtSolverStatusUNSAT) {
            @CyberNew("clear constraints") run { invokeStateUpdate = null }
            return emptyList()
        }

        @CyberNew("check invoke conditions") run {
            val sootClass = Scene.v().getSootClass("org.cyber.utils.Utils")
            val vulnerabilityAssert = sootClass.getMethod("vulnerabilityAssertByMsg", listOf()) //STRING_TYPE
            // TODO(add assert with msg later)
//            val argList = ArrayList<Value>()
//            val vulnerabilityAssertExpr = Jimple.v().newStaticInvokeExpr(vulnerabilityAssert.makeRef(), argList)
//            val state = updateQueued(
//                Edge(environment.state.stmt, JInvokeStmt(vulnerabilityAssertExpr), 0),
//                invokeStateUpdate
//            )

            val graph = ExceptionalUnitGraph(vulnerabilityAssert.activeBody)
            val constraints = invokeStateUpdate?.let { setOf(mkNot(mkAnd(it.hardConstraints.constraints.toList()))) } ?: emptySet()
            pushToPathSelector(graph, null, listOf(), constraints=constraints)
        }

        // If so, return the result of the override
        if (artificialMethodOverride.success) {
            if (artificialMethodOverride.results.size > 1) {
                environment.state.definitelyFork()
            }
            @CyberNew("constraints after clear") val constraints = invokeStateUpdate?.hardConstraints?.constraints ?: emptySet()
            @CyberNew("clear constraints") run { invokeStateUpdate = null }
            return mutableListOf<MethodResult>().apply {
                for (result in artificialMethodOverride.results) {
                    when (result) {
                        is MethodResult -> add(result)
                        is GraphResult -> pushToPathSelector(
                            result.graph,
                            invocation.instance,
                            invocation.parameters,
                            result.constraints + @CyberNew("add vulnerability constraints") constraints,
                            isLibraryMethod = true
                        )
                    }
                }
            }
        }

        // If there is no such invocation, use the generator to produce invocation targets
        val targets = invocation.generator.invoke()

        // Take all the targets and run them, at least one target must exist
        require(targets.isNotEmpty()) { "No targets for $invocation" }

        // Note that sometimes invocation on the particular targets should be overridden as well.
        // For example, Collection.size will produce two targets (ArrayList and HashSet)
        // that will override the invocation.
        val overrideResults = targets.map { it to overrideInvocation(invocation, it) }

        if (overrideResults.sumOf { (_, overriddenResult) -> overriddenResult.results.size } > 1) {
            environment.state.definitelyFork()
        }

        // Separate targets for which invocation should be overridden
        // from the targets that should be processed regularly.
        val (overridden, original) = overrideResults.partition { it.second.success }

        val overriddenResults = overridden
            .flatMap { (target, overriddenResult) ->
                mutableListOf<MethodResult>().apply {
                    for (result in overriddenResult.results) {
                        when (result) {
                            is MethodResult -> add(result)
                            is GraphResult -> pushToPathSelector(
                                result.graph,
                                // take the instance from the target
                                target.instance,
                                invocation.parameters,
                                // It is important to add constraints for the target as well, because
                                // those constraints contain information about the type of the
                                // instance from the target
                                target.constraints + result.constraints + @CyberNew("add vulnerability constraints") (invokeStateUpdate?.hardConstraints?.constraints ?: emptySet()),
                                // Since we override methods of the classes from the standard library
                                isLibraryMethod = true
                            )
                        }
                    }
                }
            }

        // Add results for the targets that should be processed without override
        val originResults = original.flatMap { (target: InvocationTarget, _) ->
            invoke(target, invocation.parameters)
        }

        // Return their concatenation
        @CyberModify("org/utbot/engine/Traverser.kt", "was: return overriddenResults + originResults") return (overriddenResults + originResults).map { result ->
            val symbolicUpdateResult = result.symbolicStateUpdate + invokeStateUpdate
            @CyberNew("clear constraints") run { invokeStateUpdate = null }
            MethodResult(result.symbolicResult, symbolicUpdateResult)
        }
    }

    @CyberModify("org/utbot/engine/Traverser.kt", "no change now. Will be updated later")
     override fun TraversalContext.processResult(symbolicResult: SymbolicResult) {
        val resolvedParameters = environment.state.parameters.map { it.value }

        //choose types that have biggest priority
        resolvedParameters
            .filterIsInstance<ReferenceValue>()
            .forEach { queuedSymbolicStateUpdates += constructConstraintForType(it, it.possibleConcreteTypes).asSoftConstraint() }

        val returnValue = (symbolicResult as? SymbolicSuccess)?.value as? ObjectValue
        if (returnValue != null) {
            queuedSymbolicStateUpdates += constructConstraintForType(returnValue, returnValue.possibleConcreteTypes).asSoftConstraint()
        }

        //fill arrays with default 0/null and other stuff
        addSoftDefaults()

        //deal with @NotNull annotation
        val isNotNullableResult = environment.method.returnValueHasNotNullAnnotation()
        if (symbolicResult is SymbolicSuccess && symbolicResult.value is ReferenceValue && isNotNullableResult) {
            queuedSymbolicStateUpdates += mkNot(mkEq(symbolicResult.value.addr, nullObjectAddr)).asHardConstraint()
        }

        val symbolicState = environment.state.symbolicState + queuedSymbolicStateUpdates
        val memory = symbolicState.memory
        val solver = symbolicState.solver

        //no need to respect soft constraints in NestedMethod
        val holder = solver.check(respectSoft = !environment.state.isInNestedMethod())

        if (holder !is UtSolverStatusSAT) {
            logger.trace { "processResult<${environment.method.signature}> UNSAT" }
            return
        }
        val methodResult = MethodResult(symbolicResult)

        //execution frame from level 2 or above
        if (environment.state.isInNestedMethod()) {
            // static fields substitution
            // TODO: JIRA:1610 -- better way of working with statics
            val updates = if (environment.method.name == STATIC_INITIALIZER && UtSettings.substituteStaticsWithSymbolicVariable) {
                substituteStaticFieldsWithSymbolicVariables(
                    environment.method.declaringClass,
                    memory.queuedStaticMemoryUpdates()
                )
            } else {
                MemoryUpdate() // all memory updates are already added in [environment.state]
            }
            val methodResultWithUpdates = methodResult.copy(symbolicStateUpdate = queuedSymbolicStateUpdates + updates)
            val stateToOffer = pop(methodResultWithUpdates)
            offerState(stateToOffer)

            logger.trace { "processResult<${environment.method.signature}> return from nested method" }
            return
        }

        // toplevel method
        // TODO: investigate very strange behavior when some constraints are not added leading to failing CodegenExampleTest::firstExampleTest fails
        val terminalExecutionState = environment.state.copy(
            symbolicState = symbolicState,
            methodResult = methodResult, // the way to put SymbolicResult into terminal state
            label = StateLabel.TERMINAL
        )
        offerState(terminalExecutionState)
    }

    @CyberModify("org/utbot/engine/Traverser.kt", "added vulnerability constraints where needed")
    override fun TraversalContext.invoke(
        target: InvocationTarget,
        parameters: List<SymbolicValue>
    ): List<MethodResult> = with(target.method) {
        val substitutedMethod = typeRegistry.findSubstitutionOrNull(this)

        if (isNative && substitutedMethod == null) return processNativeMethod(target).also { @CyberNew("add vulnerability constraints") it.map { result -> result.update(invokeStateUpdate) } }

        // If we face UtMock.assume call, we should continue only with the branch
        // where the predicate from the parameters is equal true
        when {
            isUtMockAssume || isUtMockAssumeOrExecuteConcretely -> {
                val param = UtCastExpression(parameters.single() as PrimitiveValue, BooleanType.v())

                val assumptionStmt = mkEq(param, UtTrue)
                val (hardConstraints, assumptions) = if (isUtMockAssume) {
                    // For UtMock.assume we must add the assumeStmt to the hard constraints
                    setOf(assumptionStmt) to emptySet()
                } else {
                    // For assumeOrExecuteConcretely we must add the statement to the assumptions.
                    // It is required to have opportunity to remove it later in case of unsat state
                    // because of it and execute the state concretely.
                    emptySet<UtBoolExpression>() to setOf(assumptionStmt)
                }

                val symbolicStateUpdate = SymbolicStateUpdate(
                    hardConstraints = hardConstraints.asHardConstraint(),
                    assumptions = assumptions.asAssumption()
                ) + @CyberNew("add vulnerability constraints") invokeStateUpdate

                val stateToContinue = updateQueued(
                    globalGraph.succ(environment.state.stmt),
                    symbolicStateUpdate
                )
                offerState(stateToContinue)

                // we already pushed state with the fulfilled predicate, so we can just drop our branch here by
                // adding UtFalse to the constraints.
                queuedSymbolicStateUpdates += UtFalse.asHardConstraint()
                emptyList()
            }
            declaringClass == utOverrideMockClass -> utOverrideMockInvoke(target, parameters)
            declaringClass == utLogicMockClass -> utLogicMockInvoke(target, parameters)
            declaringClass == utArrayMockClass -> utArrayMockInvoke(target, parameters)
            isUtMockForbidClassCastException -> isUtMockDisableClassCastExceptionCheckInvoke(parameters)
            else -> {
                val graph = substitutedMethod?.jimpleBody()?.graph() ?: jimpleBody().graph()
                pushToPathSelector(graph, target.instance, parameters, target.constraints + @CyberNew("add vulnerability constraints") (invokeStateUpdate?.hardConstraints?.constraints ?: emptySet()), isLibraryMethod)
                emptyList()
            }
        }.also { @CyberNew("add vulnerability constraints") it.map { result -> result.update(invokeStateUpdate) } }
    }
}
