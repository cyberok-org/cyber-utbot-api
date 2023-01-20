package org.cyber.utbot.api.utils.overrides

import org.cyber.utbot.api.utils.annotations.CyberModify
import org.cyber.utbot.api.utils.annotations.CyberNew
import org.cyber.utbot.api.utils.vulnerability.stateUpdates
import org.utbot.common.WorkaroundReason
import org.utbot.common.workaround
import org.utbot.engine.*
import org.utbot.engine.pc.*
import org.utbot.engine.state.StateLabel
import org.utbot.engine.state.withLabel
import org.utbot.engine.symbolic.SymbolicStateUpdate
import org.utbot.engine.symbolic.asHardConstraint
import org.utbot.engine.symbolic.asSoftConstraint
import org.utbot.engine.types.TypeRegistry
import org.utbot.engine.types.TypeResolver
import org.utbot.framework.UtSettings
import org.utbot.framework.plugin.api.ExecutableId
import soot.SootMethod
import soot.SootMethodRef
import soot.Value
import soot.jimple.internal.JDynamicInvokeExpr
import soot.jimple.internal.JSpecialInvokeExpr
import soot.jimple.internal.JStaticInvokeExpr

class CyberTraverser(
    methodUnderTest: ExecutableId,
    typeRegistry: TypeRegistry,
    hierarchy: Hierarchy,
    typeResolver: TypeResolver,
    globalGraph: InterProceduralUnitGraph,
    mocker: Mocker
) : Traverser(methodUnderTest, typeRegistry, hierarchy, typeResolver, globalGraph, mocker) {
    private var invokeStateUpdate = SymbolicStateUpdate()

    @CyberNew("add vulnerability constraints")
    private fun updateVulnerabilityStates(instance: SymbolicValue?, method: SootMethod, resolvedParameters: List<SymbolicValue>) {
//        logger.info { "updateVulnerabilityStates ${instance?.type?.toString()} to ${method.name}" }
        stateUpdates[instance?.type?.toString() to method.name]?.let { func ->
            val symbolicStateUpdates = func(resolvedParameters)
            logger.info { "updateVulnerabilityStates $symbolicStateUpdates" }
            invokeStateUpdate += symbolicStateUpdates
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
            return emptyList()
        }

        // If so, return the result of the override
        if (artificialMethodOverride.success) {
            if (artificialMethodOverride.results.size > 1) {
                environment.state.definitelyFork()
            }

            return mutableListOf<MethodResult>().apply {
                for (result in artificialMethodOverride.results) {
                    when (result) {
                        is MethodResult -> add(result)
                        is GraphResult -> pushToPathSelector(
                            result.graph,
                            invocation.instance,
                            invocation.parameters,
                            result.constraints,
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
                                target.constraints + result.constraints,
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
        @CyberModify("org/utbot/engine/Traverser.kt", "was: return overriddenResults + originResults") return (overriddenResults + originResults).map {
                result -> MethodResult(result.symbolicResult, result.symbolicStateUpdate.plus(invokeStateUpdate))
        }
    }

    @CyberModify("org/utbot/engine/Traverser.kt", "add invokeStateUpdate to MethodResult")
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
        val methodResult = MethodResult(symbolicResult, @CyberNew("add vulnerability constraints") invokeStateUpdate)

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
            val methodResultWithUpdates = methodResult.copy(symbolicStateUpdate = queuedSymbolicStateUpdates + updates + @CyberNew("add vulnerability constraints") methodResult.symbolicStateUpdate)
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
}
