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
import org.utbot.engine.symbolic.SymbolicStateUpdate
import org.utbot.engine.symbolic.asAssumption
import org.utbot.engine.symbolic.asHardConstraint
import org.utbot.engine.types.TypeRegistry
import org.utbot.engine.types.TypeResolver
import org.utbot.framework.plugin.api.ExecutableId
import org.utbot.framework.util.graph
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

    @CyberModify("org/utbot/engine/Traverser.kt", "added decorateTarget call")
    override fun TraversalContext.invoke(
        target: InvocationTarget,
        parameters: List<SymbolicValue>
    ): List<MethodResult> = @CyberNew("\"decorateTarget(target, parameters).let { target ->\" new, decorate target") decorateTarget(target).let { target ->
        with(target.method) {
            val substitutedMethod = typeRegistry.findSubstitutionOrNull(this)

            if (isNative && substitutedMethod == null) return processNativeMethod(target)

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
                    )

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
                    pushToPathSelector(graph, target.instance, parameters, target.constraints, isLibraryMethod)
                    emptyList()
                }
            }
        }
    }
}
