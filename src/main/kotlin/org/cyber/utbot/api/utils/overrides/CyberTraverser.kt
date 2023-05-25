package org.cyber.utbot.api.utils.overrides

import org.cyber.utbot.api.utils.CHECK_METHOD_PREFIX
import org.cyber.utbot.api.utils.VULNERABILITY_CHECKS_CLASS_NAME
import org.cyber.utbot.api.utils.additions.MethodSubstitution
import org.cyber.utbot.api.utils.additions.classState.StateHolder
import org.cyber.utbot.api.utils.additions.constraints.ConstraintParser
import org.cyber.utbot.api.utils.additions.fuzzing.TaintVulnerabilityChecksFuzzer
import org.cyber.utbot.api.utils.additions.fuzzing.VulnerabilityChecksFuzzer
import org.cyber.utbot.api.utils.additions.vulnerability.decorateVulnerabilityFunction
import org.cyber.utbot.api.utils.annotations.CyberModify
import org.cyber.utbot.api.utils.annotations.CyberNew
import org.cyber.utbot.api.utils.annotations.CyberNote
import org.cyber.utbot.api.utils.vulnerability.VulnerabilityChecksHolder
import org.cyber.utbot.api.utils.vulnerability.parsers.ArgumentsVulnerabilityChecksCreator
import org.utbot.common.WorkaroundReason
import org.utbot.common.unreachableBranch
import org.utbot.common.workaround
import org.utbot.engine.*
import org.utbot.engine.Hierarchy
import org.utbot.engine.pc.*
import org.utbot.engine.state.StateLabel
import org.utbot.engine.state.withLabel
import org.utbot.engine.symbolic.asHardConstraint
import org.utbot.engine.types.*
import org.utbot.framework.plugin.api.ApplicationContext
import org.utbot.framework.plugin.api.ClassId
import org.utbot.framework.plugin.api.ExecutableId
import org.utbot.framework.plugin.api.classId
import org.utbot.framework.plugin.api.util.methodId
import org.utbot.framework.util.executableId
import proguard.analysis.cpa.jvm.domain.memory.BamLocationDependentJvmMemoryLocation
import proguard.analysis.cpa.jvm.domain.taint.JvmInvokeTaintSink
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintSink
import soot.*
import soot.jimple.Stmt
import soot.jimple.internal.JInvokeStmt
import soot.jimple.internal.JSpecialInvokeExpr


@CyberNote("decorate invokes, add overrides")
class CyberTraverser(
    methodUnderTest: ExecutableId,
    typeRegistry: TypeRegistry,
    hierarchy: Hierarchy,
    typeResolver: TypeResolver,
    globalGraph: InterProceduralUnitGraph,
    mocker: Mocker,
    applicationContext: ApplicationContext,
    private val vulnerabilityChecksHolder: VulnerabilityChecksHolder?,
    private val stateHolder: StateHolder
) : Traverser(methodUnderTest, typeRegistry, hierarchy, typeResolver, globalGraph, mocker, applicationContext) {
    @CyberNew("smth to override")
    private val rememberedParams = mutableSetOf<List<SymbolicValue>>()
    private val objectValueToParams = mutableMapOf<ObjectValue, List<SymbolicValue>>()
    private val stmtToParams = mutableMapOf<Stmt, List<SymbolicValue>>()
    val taintEndPoints: MutableMap<BamLocationDependentJvmMemoryLocation<*>, List<JvmTaintSink>> = mutableMapOf()

    private val vulnerabilityChecksFuzzer: VulnerabilityChecksFuzzer = TaintVulnerabilityChecksFuzzer()

    @CyberNew("decorate target invoke")
    private fun decorateTarget(
        target: InvocationTarget,
        parameters: List<SymbolicValue>,
        taintedArgs: MutableSet<Pair<SymbolicValue, Int>>
    ): InvocationTarget {
        val targetClassName = target.method.declaringClass.name
        val targetFunctionName = target.method.name
//        println("target: $targetClassName $targetFunctionName")
        return vulnerabilityChecksHolder?.checks( targetClassName to targetFunctionName)?.run {
            val methodName = "$CHECK_METHOD_PREFIX\$$targetClassName.$targetFunctionName"
            if (environment.method.name == methodName && environment.method.declaringClass.name == VULNERABILITY_CHECKS_CLASS_NAME) return target
            val descriptions = mutableSetOf<String?>() // here
            val methodId = methodId(ClassId(targetClassName), targetFunctionName, target.method.returnType.classId, *target.method.parameterTypes.map { it.classId }.toTypedArray())
            val parametersInfo = null   // set it later

            val constraints = ConstraintParser.parse(parameters, environment.state.symbolicState.solver.assertions)
            val argumentChecks = map { it.description }.toSet().mapNotNull { description ->
                if (description == null) {
                    descriptions.add(null)
                    null
                } else {
                    val methods = mutableListOf<String>()
                    val booleanType = BooleanType.v()
                    val paramTypes = target.method.parameterTypes
                    this.forEach foreach@{ vulnerabilityCheck ->
                        vulnerabilityCheck.functionIds.forEach { (checkClassName, checkFunctionName) ->
                            val checkSootClass = Scene.v().getSootClass(checkClassName)
                            val checkSootMethod =
                                checkSootClass.getMethodUnsafe(checkFunctionName, paramTypes, booleanType)
                                    ?: return@foreach
                            methods.add(checkSootMethod.name)
                        }
                    }
                    val argumentChecks = vulnerabilityChecksFuzzer.
                    generate(methodId, parametersInfo, constraints, description, methods, taintedArgs, methodUnderTest)
                    if (argumentChecks == null) descriptions.add(description)
                    argumentChecks?.run { ArgumentsVulnerabilityChecksCreator.parseVulnerabilityCheck(this) }
                }
            }.toMutableList()

            forEach {
                if (it.description in descriptions) {
                    argumentChecks.add(it)
                }
            }

            val (decorateFunction, methods) = decorateVulnerabilityFunction(target, methodName, checks = argumentChecks)
            InvocationTarget(instance = null, method = decorateFunction, target.constraints)
        } ?: target
    }

    @CyberNew("resolver instance for stateHolder if needed")
    private fun createResolver(): Resolver {
        val holder = solver.check(respectSoft = false)
        return Resolver(
            hierarchy,
            memory,
            typeRegistry,
            typeResolver,
            holder as UtSolverStatusSAT,    // TODO is ok?
            methodUnderTest,
            softMaxArraySize,
            objectCounter
        )
    }

    @CyberModify("org/utbot/engine/Traverser.kt", "added update objectValueToParams")
    override fun TraversalContext.specialInvoke(invokeExpr: JSpecialInvokeExpr): List<MethodResult> {
        val instance = resolve(invokeExpr.base)
        if (instance !is ReferenceValue) error("We cannot run ${invokeExpr.methodRef} on $instance")

        nullPointerExceptionCheck(instance.addr)

        if (instance.isNullObject()) return emptyList() // Nothing to call

        @CyberNew("update objectValueToParams") run {
            if (instance is ObjectValue) {
                stmtToParams.remove(environment.state.stmt)?.let { params ->
                    objectValueToParams[instance] = params
                }
            }
        }

        val method = invokeExpr.retrieveMethod()
        val parameters = resolveParameters(invokeExpr.args, method.parameterTypes)
        val invocation = Invocation(instance, method, parameters, InvocationTarget(instance, method))

        // Calls with super syntax are represented by invokeSpecial instruction, but we don't support them in wrappers
        // TODO: https://github.com/UnitTestBot/UTBotJava/issues/819 -- Support super calls in inherited wrappers
        return commonInvokePart(invocation)
    }

    @CyberModify("org/utbot/engine/Traverser.kt", "added decorateTarget call for overrideInvocation, update stateToParams")
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
                val taintedArgs = mutableSetOf<Pair<SymbolicValue, Int>>()
                environment.state.stmt.apply {
//                    if (this is JInvokeStmt) {
                        val matchedSinks = mutableListOf<JvmTaintSink?>()
                        taintEndPoints.values.forEach { v ->
                            matchedSinks.add(v.find {
                                it.signature.fqn.contains(
                                    "${invocation.method.declaringClass.name.replace(".", "/")};${invocation.method.name}"
                                )
                            })
                        }
                        val params = invocation.parameters
                        if (matchedSinks.isNotEmpty()) {
                            matchedSinks.filterIsInstance<JvmInvokeTaintSink>().forEach { sink ->
                                val takesArgs = sink.takesArgs
                                takesArgs.forEach { taintedArgs.add(Pair(params[it - 1], it - 1)) }
                            }
                        }
//                    }
                }
                val target = InvocationTarget(invocation.instance, invocation.method)
                val newTarget = decorateTarget(target, invocation.parameters, taintedArgs) // here add tainted args
                if (target != newTarget) {
                    return invoke(newTarget, invocation.parameters)
                }
            }
            if (artificialMethodOverride.results.size > 1) {
                environment.state.definitelyFork()
            }

            return mutableListOf<MethodResult>().apply {
                for (result in artificialMethodOverride.results) {
                    when (result) {
                        is MethodResult -> add(result)
                        is GraphResult -> {
                            pushToPathSelector(
                                result.graph,
                                invocation.instance,
                                invocation.parameters,
                                result.constraints,
                                isLibraryMethod = true
                            )
                            @CyberNew("update stateToParams") run {
                                if (invocation.parameters in rememberedParams) {
                                    result.graph.stmts.find { it is JInvokeStmt && it.invokeExprBox.value is JSpecialInvokeExpr }
                                        ?.run {
                                            stmtToParams[this] = invocation.parameters
                                            rememberedParams.remove(invocation.parameters)
                                        }
                                }
                            }
                        }
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
        val matchedSinks = mutableListOf<JvmTaintSink?>()
        taintEndPoints.values.forEach { v ->
            matchedSinks.add(v.find {
                it.signature.fqn.contains(
                    "${invocation.method.declaringClass.name.replace(".", "/")};${invocation.method.name}"
                )
            })
        }
        val taintedArgs = mutableSetOf<Pair<SymbolicValue, Int>>()
        val params = invocation.parameters
        if (matchedSinks.isNotEmpty()) {
            matchedSinks.filterIsInstance<JvmInvokeTaintSink>().forEach { sink ->
                val takesArgs = sink.takesArgs
                takesArgs.forEach { taintedArgs.add(Pair(params[it - 1], it - 1)) }
            }
        }
        val overrideResults = targets
//            .map { @CyberNew("decorate target") decorateTarget(it, taintedArgs) }
            .map { @CyberNew("decorate target") decorateTarget(it, invocation.parameters, taintedArgs) }// here add tainted args
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

    @CyberModify("org/utbot/engine/Traverser.kt", "added overrides")
    override fun TraversalContext.overrideInvocation(invocation: Invocation, target: InvocationTarget?): OverrideResult {
        // If we try to override invocation itself, the target is null, and we have to process
        // the instance from the invocation, otherwise take the one from the target
        val instance = if (target == null) invocation.instance else target.instance
        val subSignature = invocation.method.subSignature

        if (subSignature == "java.lang.Class getClass()") {
            return when (instance) {
                is ReferenceValue -> {
                    val type = instance.type
                    val createClassRef = if (type is RefLikeType) {
                        typeRegistry.createClassRef(type.baseType, type.numDimensions)
                    } else {
                        error("Can't get class name for $type")
                    }
                    OverrideResult(success = true, createClassRef)
                }
                null -> unreachableBranch("Static getClass call: $invocation")
            }
        }

        // Return an unbounded symbolic variable for any overloading of method `forName` of class `java.lang.Class`
        // NOTE: we cannot match by a subsignature here since `forName` method has a few overloadings
        if (instance == null && invocation.method.declaringClass == CLASS_REF_SOOT_CLASS && invocation.method.name == "forName") {
            val forNameResult = unboundedVariable(name = "classForName", invocation.method)

            return OverrideResult(success = true, forNameResult)
        }

        // Return an unbounded symbolic variable for the `newInstance` method invocation,
        // and at the next traversing step push <init> graph of the resulted type
        if (instance?.type == CLASS_REF_TYPE && subSignature == NEW_INSTANCE_SIGNATURE) {
            val getInstanceResult = unboundedVariable(name = "newInstance", invocation.method)

            return OverrideResult(success = true, getInstanceResult)
        }

        val instanceAsWrapperOrNull = instance?.asWrapperOrNull

        if (instanceAsWrapperOrNull is UtMockWrapper && subSignature == HASHCODE_SIGNATURE) {
            val result = MethodResult(mkBVConst("hashcode${hashcodeCounter++}", UtIntSort).toIntValue())
            return OverrideResult(success = true, result)
        }

        if (instanceAsWrapperOrNull is UtMockWrapper && subSignature == EQUALS_SIGNATURE) {
            val result = MethodResult(mkBoolConst("equals${equalsCounter++}").toBoolValue())
            return OverrideResult(success = true, result)
        }

        // we cannot mock synthetic methods and methods that have private or protected arguments
        val impossibleToMock =
            invocation.method.isSynthetic || invocation.method.isProtected || parametersContainPrivateAndProtectedTypes(
                invocation.method
            )

        if (instanceAsWrapperOrNull is UtMockWrapper && impossibleToMock) {
            // TODO temporary we return unbounded symbolic variable with a wrong name.
            // TODO Probably it'll lead us to the path divergence
            workaround(WorkaroundReason.HACK) {
                val result = unboundedVariable("unbounded", invocation.method)
                return OverrideResult(success = true, result)
            }
        }

        if (instance is ArrayValue && invocation.method.name == "clone") {
            return OverrideResult(success = true, cloneArray(instance))
        }

        if (instance == null && invocation.method.declaringClass == ARRAYS_SOOT_CLASS && invocation.method.name == "copyOf") {
            return OverrideResult(success = true, copyOf(invocation.parameters))
        }

        if (instance == null && invocation.method.declaringClass == ARRAYS_SOOT_CLASS && invocation.method.name == "copyOfRange") {
            return OverrideResult(success = true, copyOfRange(invocation.parameters))
        }

        @CyberNew("add overrides related to save params") stateHolder.run {
            val parameters = if (invocation.method.name == "<init>" && instance != null) {      // TODO(not only init)
                objectValueToParams[instance] ?: invocation.parameters      // TODO(objectValueToParams.remove(instance))
            } else invocation.parameters
            overrideInvoke(instance as? ObjectValue, invocation.method, parameters) { createResolver() } ?: run {
                if (instance == null && saveArgs(invocation.method)) rememberedParams.add(invocation.parameters)
                null
            }
        }?.run { return OverrideResult(success = true, this) }

        @CyberNew("add overrides") MethodSubstitution.run {
            overrideInvoke(instance as? ObjectValue, invocation.method, invocation.parameters)
        }?.run { return OverrideResult(success = true, this) }

        instanceAsWrapperOrNull?.run {
            // For methods with concrete implementation (for example, RangeModifiableUnlimitedArray.toCastedArray)
            // we should not return successful override result.
            if (!isWrappedMethod(invocation.method)) {
                return OverrideResult(success = false)
            }

            val results = invoke(instance as ObjectValue, invocation.method, invocation.parameters)
            if (results.isEmpty()) {
                // Drop the branch and switch to concrete execution
                offerState(environment.state.withLabel(StateLabel.CONCRETE))
                queuedSymbolicStateUpdates += UtFalse.asHardConstraint()
            }
            @CyberNew("state holder set invoke result") stateHolder.run {
                setInvokeResults(instance as? ObjectValue, invocation.method,
                    results.mapNotNull { ((it as? MethodResult)?.symbolicResult as? SymbolicSuccess)?.value }) { createResolver() }
            }
            return OverrideResult(success = true, results)
        }

        return OverrideResult(success = false)
    }
}
