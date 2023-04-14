package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.additions.classState.codeGeneration.asString
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import soot.*

abstract class AnyStateHolder {
    protected abstract val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?>
    protected abstract val signatureToSetFunResults: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> Unit>

    fun CyberTraverser.overrideInvoke(
        method: SootMethod,
        parameters: List<SymbolicValue>,
        createResolver: () -> Resolver
    ): List<InvokeResult>? = signatureToOverrideFun[method.subSignature]?.run {
        this(parameters, createResolver)
    }

    fun CyberTraverser.setInvokeResults(
        method: SootMethod,
        invokeResults:  List<SymbolicValue>,
        createResolver: () -> Resolver
    ) = signatureToSetFunResults[method.subSignature]?.run {
        this(invokeResults, createResolver)
    }

    protected fun overrideFunHelper(value: SymbolicValue, resolver: Resolver, funIfConcrete: (SymbolicValue, String?) -> List<InvokeResult>?, funIfSymbolic: (SymbolicValue) -> List<InvokeResult>?): List<InvokeResult>? {     // TODO test it, work only with String
        val resolved = resolver.resolveModels(listOf(value))
        val paramAfter = resolved.modelsAfter.parameters.first().asString()
        val paramBefore = resolved.modelsBefore.parameters.first().asString()
        val concrete = paramAfter != paramBefore
        return if (concrete) {
            funIfConcrete(value, paramAfter)
        } else {
            funIfSymbolic(value)
        }
    }
}
