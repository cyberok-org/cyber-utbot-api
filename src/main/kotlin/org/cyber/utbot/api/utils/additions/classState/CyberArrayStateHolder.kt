package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.pc.UtAddrExpression
import org.utbot.framework.plugin.api.UtModel
import soot.*

class CyberArrayState<T> : AnyState<T>

class CyberArrayStateHolder(addActionByAddr: (UtAddrExpression, (ObjectValue, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit) -> Unit, registerStateHolder: (UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit,
                                  private val signatureToOverrideFunParent: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mapOf()):
    CodeGenStateHolder<CyberArrayState<SymbolicValue>, CyberArrayState<UtModel>>(addActionByAddr, registerStateHolder) {
    override val state = CyberArrayState<SymbolicValue>()
    override val codeGenerator
        get() = null

    override val signatureToSetFunResults = mapOf<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> Unit>()

    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mapOf(
        getAtSignature to {params, createResolver ->
            val parentOverrideFun = signatureToOverrideFunParent[getAtSignature]
            if (parentOverrideFun != null) {
                parentOverrideFun(params, createResolver)
            } else {
                null
            }
        },
    )

    companion object: ArgsSaveHolder() {
        private val kclass = org.cyber.utils.overrides.CyberArray::class
        private val sootClass by lazy { Scene.v().getSootClass(kclass.qualifiedName) }

        private val getAtSignature by lazy { sootClass.getMethodByName("getAt").subSignature }

        //

        override val saveArgsSignatures = setOf<String>()

        override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mapOf()
        override val signatureToSetFunResults = mapOf<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> Unit>()
    }
}
