package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.pc.UtAddrExpression
import org.utbot.framework.plugin.api.UtModel
import soot.*

class HttpServletResponseState<T> : AnyState<T>

class HttpServletResponseStateHolder(addActionByAddr: (UtAddrExpression, (ObjectValue, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit) -> Unit, registerStateHolder: (UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit):
    CodeGenStateHolder<HttpServletResponseState<SymbolicValue>, HttpServletResponseState<UtModel>>(addActionByAddr, registerStateHolder) {
    override val state = HttpServletResponseState<SymbolicValue>()
    override val codeGenerator
        get() = null

    override val signatureToSetFunResults = mapOf<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> Unit>()
    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mapOf()

    companion object: ArgsSaveHolder() {
        private val kclass = javax.servlet.http.HttpServletResponse::class
        private val sootClass by lazy { Scene.v().getSootClass(kclass.qualifiedName) }

        //

        override val saveArgsSignatures = emptySet<String>()

        override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mapOf()
        override val signatureToSetFunResults = mapOf<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> Unit>()
    }
}
