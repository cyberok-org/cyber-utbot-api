package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import soot.*

abstract class AnyStateHolder {
    protected abstract val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>) -> List<InvokeResult>?>

    fun CyberTraverser.overrideInvoke(
        method: SootMethod,
        parameters: List<SymbolicValue>,
    ): List<InvokeResult>? = signatureToOverrideFun[method.subSignature]?.run {
        this(parameters)
    }
}
