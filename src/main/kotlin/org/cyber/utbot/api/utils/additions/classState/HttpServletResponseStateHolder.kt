package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import soot.*

class HttpServletResponseStateHolder: AnyStateHolder() {
    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>) -> List<InvokeResult>?> = mutableMapOf(
    )

    companion object: ArgsSaveHolder() {
        private val kclass = javax.servlet.http.HttpServletResponse::class
        private val sootClass by lazy { Scene.v().getSootClass(kclass.qualifiedName) }

        //

        override val saveArgsSignatures = emptySet<String>()

        override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>) -> List<InvokeResult>?> = mutableMapOf()
    }
}
