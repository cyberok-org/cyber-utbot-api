package org.cyber.utbot.api.utils.overrides

import org.cyber.utbot.api.utils.CHECK_METHOD_PREFIX
import org.cyber.utbot.api.utils.VULNERABILITY_CHECKS_CLASS_NAME
import org.cyber.utbot.api.utils.additions.vulnerability.decorateVulnerabilityFunction
import org.cyber.utbot.api.utils.annotations.CyberModify
import org.cyber.utbot.api.utils.annotations.CyberNew
import org.cyber.utbot.api.utils.annotations.CyberNote
import org.cyber.utbot.api.utils.vulnerability.VulnerabilityChecksHolder
import org.utbot.engine.*
import org.utbot.engine.Hierarchy
import org.utbot.engine.pc.*
import org.utbot.engine.types.TypeRegistry
import org.utbot.engine.types.TypeResolver
import org.utbot.framework.plugin.api.ExecutableId
import org.utbot.framework.util.executableId
import soot.*


@CyberNote("decorate invokes")
class CyberTraverser(
    methodUnderTest: ExecutableId,
    typeRegistry: TypeRegistry,
    hierarchy: Hierarchy,
    typeResolver: TypeResolver,
    globalGraph: InterProceduralUnitGraph,
    mocker: Mocker,
    private val vulnerabilityChecksHolder: VulnerabilityChecksHolder
) : Traverser(methodUnderTest, typeRegistry, hierarchy, typeResolver, globalGraph, mocker) {
    @CyberNew("decorate target invoke")
    private fun decorateTarget(target: InvocationTarget): InvocationTarget {
        val targetClassName = target.method.declaringClass.name
        val targetFunctionName = target.method.name
        return vulnerabilityChecksHolder.checks( targetClassName to targetFunctionName)?.run  {
            val methodName = "$CHECK_METHOD_PREFIX\$$targetClassName.$targetFunctionName"
            if (environment.method.name == methodName && environment.method.declaringClass.name == VULNERABILITY_CHECKS_CLASS_NAME) return target
            val decorateFunction = decorateVulnerabilityFunction(target, methodName, checks = this)
            InvocationTarget(instance = null, method = decorateFunction, target.constraints)
        } ?: target
    }

    @CyberModify("org/utbot/engine/Traverser.kt", "added decorateTarget call for overrideInvocation")
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
            return emptyList()
        }

        // If so, return the result of the override
        if (artificialMethodOverride.success) {
            @CyberNew("decorate target") run {
                val target = InvocationTarget(invocation.instance, invocation.method)
                val newTarget = decorateTarget(target)
                if (target != newTarget) {
                    return invoke(target, invocation.parameters)
                }
            }
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
        val overrideResults = targets
            .map { @CyberNew("decorate target") decorateTarget(it) }
            .map { it to overrideInvocation(invocation, it) }

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
        return overriddenResults + originResults
    }
}
