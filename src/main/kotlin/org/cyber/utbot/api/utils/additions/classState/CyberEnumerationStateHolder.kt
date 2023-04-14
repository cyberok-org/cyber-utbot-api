package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.pc.UtAddrExpression
import org.utbot.framework.plugin.api.UtModel
import soot.*

class CyberEnumerationState<T> : AnyState<T>

class CyberEnumerationStateHolder(addActionByAddr: (UtAddrExpression, (ObjectValue, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit) -> Unit, registerStateHolder: (UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit,
                                  private val signatureToOverrideFunParent: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mapOf()):
    CodeGenStateHolder<CyberEnumerationState<SymbolicValue>, CyberEnumerationState<UtModel>>(addActionByAddr, registerStateHolder) {
    override val state = CyberEnumerationState<SymbolicValue>()
    override val codeGenerator
        get() = null

    override val signatureToSetFunResults = mapOf<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> Unit>()

    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mapOf(
        toStringSignature to {params, createResolver ->
            val parentOverrideFun = signatureToOverrideFunParent[toStringSignature]
            if (parentOverrideFun != null) {
                parentOverrideFun(params, createResolver)
            } else {
                null
            }
        },
        hasMoreElementsSignature to { params, createResolver ->
            val parentOverrideFun = signatureToOverrideFunParent[hasMoreElementsSignature]
            if (parentOverrideFun != null) {
                parentOverrideFun(params, createResolver)
            } else {
                null
            }
        },
        nextElementSignature to { params, createResolver ->
            val parentOverrideFun = signatureToOverrideFunParent[nextElementSignature]
            if (parentOverrideFun != null) {
                parentOverrideFun(params, createResolver)
            } else {
                null
            }
        },
    )

    companion object: ArgsSaveHolder() {
        private val kclass = org.cyber.utils.overrides.CyberEnumeration::class
        private val sootClass by lazy { Scene.v().getSootClass(kclass.qualifiedName) }

        private val toStringSignature by lazy { sootClass.getMethodByName("toString").subSignature }
        private val hasMoreElementsSignature by lazy { sootClass.getMethodByName("hasMoreElements").subSignature }
        private val nextElementSignature by lazy { sootClass.getMethodByName("nextElement").subSignature }

        //

        override val saveArgsSignatures = setOf<String>()

        override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mapOf()
        override val signatureToSetFunResults = mapOf<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> Unit>()
    }
}
