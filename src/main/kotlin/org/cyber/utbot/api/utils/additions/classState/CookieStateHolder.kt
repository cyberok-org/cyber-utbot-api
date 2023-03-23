package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.types.STRING_TYPE
import soot.*

class CookieStateHolder: AnyStateHolder() {
    private var name: SymbolicValue? = null             // String
    private var value: SymbolicValue? = null            // String

    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>) -> List<InvokeResult>?> = mutableMapOf(
        initSignature to { params ->
            name?.run {
                name = params[0]
                value = params[1]
            }
            null
        },
    )

    companion object: ArgsSaveHolder() {
        private val kclass = javax.servlet.http.Cookie::class
        private val sootClass by lazy { Scene.v().getSootClass(kclass.qualifiedName) }

        private val initSignature by lazy { sootClass.getMethod("<init>", listOf(STRING_TYPE, STRING_TYPE)).subSignature }

        //

        override val saveArgsSignatures = emptySet<String>()

        override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>) -> List<InvokeResult>?> = mutableMapOf()
    }
}
