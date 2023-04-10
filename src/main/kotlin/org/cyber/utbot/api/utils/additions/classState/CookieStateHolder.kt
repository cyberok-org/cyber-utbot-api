package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.pc.UtAddrExpression
import org.utbot.engine.types.STRING_TYPE
import org.utbot.framework.plugin.api.UtModel
import soot.*

class CookieState<T>: AnyState<T> {
    var name: T? = null
    var value: T? = null
    var path: T? = null
    var secure: T? = null
    var maxAge: T? = null
    var domain: T? = null
}

class CookieStateHolder(registerStateHolder: (UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit):
    CodeGenStateHolder<CookieState<SymbolicValue>, CookieState<UtModel>>(registerStateHolder) {
    override val state = CookieState<SymbolicValue>()
    override val codeGenerator
        get() = null

    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mutableMapOf(
        initSignature to { params, _ ->
            state.name = params[0]
            state.value = params[1]
            null
        },
        getNameSignature to { _, _ ->
            listOf(MethodResult(state.name as ObjectValue))
        },
        setValueSignature to { params, _ ->
            state.value = params[0]
            null
        },
        getValueSignature to { _, _ ->
            listOf(MethodResult(state.value as ObjectValue))
        },
        setPathSignature to { params, _ ->
            state.path = params[0]
            null
        },
        getPathSignature to { _, _ ->
            if (state.path == null) {
                state.path = ObjectValue(TypeStorage.constructTypeStorageWithSingleType(STRING_TYPE), findNewAddr(), null)
            }
            listOf(MethodResult(state.path as ObjectValue))
        },
        setSecureSignature to { params, _ ->
            state.secure = params[0]
            null
        },
        getSecureSignature to { _, _ ->
            if (state.secure == null) {
                state.secure = false.primitiveToSymbolic()
            }
            listOf(MethodResult(state.secure as PrimitiveValue))
        },
        setMaxAgeSignature to { params, _ ->
            state.maxAge = params[0]
            null
        },
        getMaxAgeSignature to { _, _ ->
            if (state.maxAge == null) {
                state.maxAge = (-1).primitiveToSymbolic()
            }
            listOf(MethodResult(state.maxAge as PrimitiveValue))
        },
        setDomainSignature to { params, _ ->
            state.domain = params[0]
            null
        },
        getDomainSignature to { _, _ ->
            if (state.domain == null) {
                state.domain = ObjectValue(TypeStorage.constructTypeStorageWithSingleType(STRING_TYPE), findNewAddr(), null)
            }
            listOf(MethodResult(state.domain as ObjectValue))
        },
    )

    companion object: ArgsSaveHolder() {
        private val kclass = javax.servlet.http.Cookie::class
        private val sootClass by lazy { Scene.v().getSootClass(kclass.qualifiedName) }

        private val initSignature by lazy { sootClass.getMethod("<init>", listOf(STRING_TYPE, STRING_TYPE)).subSignature }
        private val getNameSignature by lazy { sootClass.getMethodByName("getName").subSignature }
        private val setValueSignature by lazy { sootClass.getMethodByName("setValue").subSignature }
        private val getValueSignature by lazy { sootClass.getMethodByName("getValue").subSignature }
        private val setPathSignature by lazy { sootClass.getMethodByName("setPath").subSignature }
        private val getPathSignature by lazy { sootClass.getMethodByName("getPath").subSignature }
        private val setSecureSignature by lazy { sootClass.getMethodByName("setSecure").subSignature }
        private val getSecureSignature by lazy { sootClass.getMethodByName("getSecure").subSignature }
        private val setMaxAgeSignature by lazy { sootClass.getMethodByName("setMaxAge").subSignature }
        private val getMaxAgeSignature by lazy { sootClass.getMethodByName("getMaxAge").subSignature }
        private val setDomainSignature by lazy { sootClass.getMethodByName("setDomain").subSignature }
        private val getDomainSignature by lazy { sootClass.getMethodByName("getDomain").subSignature }

        override val saveArgsSignatures = emptySet<String>()

        override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mutableMapOf()
    }
}
