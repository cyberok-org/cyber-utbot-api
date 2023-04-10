package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.additions.classState.codeGeneration.HttpServletRequestCodeGen
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.pc.UtAddrExpression
import org.utbot.engine.types.STRING_TYPE
import org.utbot.framework.plugin.api.UtModel
import soot.*

class HttpServletRequestState<T>: AnyState<T> {
    var headers = mutableMapOf<T, T>()
    var headersConcreteKey = mutableMapOf<String, T>()
    var headersConcreteKeyToKeys = mutableMapOf<String, MutableSet<String>>()    // because of lowercase
}

class HttpServletRequestStateHolder(registerStateHolder: (UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit):
    CodeGenStateHolder<HttpServletRequestState<SymbolicValue>, HttpServletRequestState<UtModel>>(registerStateHolder) {     // TODO(add parameters to constructor CyberHttpServletRequest)
    override val state = HttpServletRequestState<SymbolicValue>()
    override val codeGenerator
        get() = HttpServletRequestCodeGen.Companion

    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mutableMapOf(
        getHeaderSignature to { params, createResolver ->
            val funIfSymbolic = { key: SymbolicValue ->     // ignore lowercase condition
                state.headers[key]?.run { listOf(MethodResult(this)) } ?: run {
                    val value = createObject(findNewAddr(), STRING_TYPE, false)
                    state.headers[key] = value
                    listOf(MethodResult(value))
                }
            }
            val funIfConcrete = { key: String? ->
                key?.lowercase()?.run {
                    (state.headersConcreteKeyToKeys.getOrPut(this) { mutableSetOf() }).add(key)
                    state.headersConcreteKey[this]?.run { listOf(MethodResult(this)) } ?: run {
                        val value = createObject(findNewAddr(), STRING_TYPE, false)
                        state.headersConcreteKey[this] = value
                        listOf(MethodResult(value))
                    }
                }
            }

            val param = params[0]
            if (state.headers.containsKey(param)) {
                funIfSymbolic(param)
            } else {
                val resolver = createResolver()
                overrideFunHelper(param, resolver, funIfConcrete, funIfSymbolic)
            }
        },
//        getRequestURLSignature to { _, _ ->
//            null
//        },
//        getRequestURISignature to { _, _ ->
//            null
//        },
        getHeadersSignature to { params, createResolver ->  // TODO
            val addr = findNewAddr()
            registerStateHolder(addr, CyberEnumerationStateHolder(registerStateHolder, mapOf(
                enumerationToStringSignature to { _, _ ->
                    null
                }
            )))
            listOf(MethodResult(createObject(addr, cyberEnumerationType, useConcreteType = false)))
        },
        getCookiesSignature to { params, createResolver ->  // TODO
            null
        },
//        getQueryStringSignature to { _, _ ->
//            null
//        },
        getHeaderNamesSignature to { params, createResolver ->  // TODO
            null
        },
    )

    companion object: ArgsSaveHolder() {
        private val kclass = javax.servlet.http.HttpServletRequest::class
        private val sootClass by lazy { Scene.v().getSootClass(kclass.qualifiedName) }

        private val getHeaderSignature by lazy { sootClass.getMethodByName("getHeader").subSignature }
        private val getRequestURLSignature by lazy { sootClass.getMethodByName("getRequestURL").subSignature }
        private val getRequestURISignature by lazy { sootClass.getMethodByName("getRequestURI").subSignature }
        private val getHeadersSignature by lazy { sootClass.getMethodByName("getHeaders").subSignature }
        private val getCookiesSignature by lazy { sootClass.getMethodByName("getCookies").subSignature }
        private val getQueryStringSignature by lazy { sootClass.getMethodByName("getQueryString").subSignature }
        private val getHeaderNamesSignature by lazy { sootClass.getMethodByName("getHeaderNames").subSignature }

        //

        private val enumerationKclass = org.cyber.utils.overrides.CyberEnumeration::class
        private val enumerationSootClass by lazy { Scene.v().getSootClass(enumerationKclass.qualifiedName) }

        private val enumerationToStringSignature by lazy { enumerationSootClass.getMethodByName("toString").subSignature }

        val cyberEnumerationType: RefType
            get() = Scene.v().getSootClass(org.cyber.utils.overrides.CyberEnumeration::class.java.canonicalName).type

        //

        override val saveArgsSignatures = emptySet<String>()

        override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mutableMapOf()
    }
}
