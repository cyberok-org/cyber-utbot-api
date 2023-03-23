package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.exceptions.CyberException
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.types.STRING_TYPE
import soot.*

class HttpServletRequestStateHolder: AnyStateHolder() {     // TODO(add parameters to constructor CyberHttpServletRequest)
    private var headers = mutableMapOf<String, SymbolicValue>()

    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>) -> List<InvokeResult>?> = mutableMapOf(
        getHeaderSignature to { params ->
            val key = (params[0].concrete as Concrete).value?.toString() ?: throw CyberException("HttpServletRequestStateHolder.getHeader error")
            headers[key]?.run { listOf(MethodResult(this)) } ?: run {
                val value = createObject(findNewAddr(), STRING_TYPE, false)
                headers[key] = value
                listOf(MethodResult(value))
            }
        },
    )

    companion object: ArgsSaveHolder() {
        private val kclass = javax.servlet.http.HttpServletRequest::class
        private val sootClass by lazy { Scene.v().getSootClass(kclass.qualifiedName) }

        private val getHeaderSignature by lazy { sootClass.getMethodByName("getHeader").subSignature }

        override val saveArgsSignatures = emptySet<String>()
    }
}
