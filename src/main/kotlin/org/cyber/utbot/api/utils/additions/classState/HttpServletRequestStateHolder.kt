package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.exceptions.CyberException
import org.cyber.utbot.api.utils.additions.classState.codeGeneration.HttpServletRequestCodeGen
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.pc.*
import org.utbot.engine.types.STRING_TYPE
import org.utbot.framework.plugin.api.UtModel
import soot.*

class HttpServletRequestState<T> : AnyState<T> {
    var headers = mutableMapOf<T, T>()
    var headersConcreteKey = mutableMapOf<String, T>()
    var indexToKey = mutableListOf<Any?>()      // 1) T = SymbolicValue, 2) T = UtModel | String
    var concreteKeyToValue = mutableMapOf<T, String>()
    var headersConcreteKeyToKeys = mutableMapOf<String, MutableSet<String>>()    // because of lowercase

    var parameters = mutableMapOf<T, T>()
    var parametersConcreteKey = mutableMapOf<String, T>()
    var parametersIndexToKey = mutableListOf<Any?>()      // 1) T = SymbolicValue, 2) T = UtModel | String
    var parametersConcreteKeyToValue = mutableMapOf<T, String>()

    var cookies = mutableMapOf<Int, Any?>()  // CookieStateHolder or CookieState<UtModel>
}

class HttpServletRequestStateHolder(addActionByAddr: (UtAddrExpression, (ObjectValue, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit) -> Unit, registerStateHolder: (UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit):
    CodeGenStateHolder<HttpServletRequestState<SymbolicValue>, HttpServletRequestState<UtModel>>(addActionByAddr, registerStateHolder) {
    override val state = HttpServletRequestState<SymbolicValue>()
    override val codeGenerator
        get() = HttpServletRequestCodeGen.Companion

    private fun CyberTraverser.getHeaderImpl(params: List<SymbolicValue>, createResolver: () -> Resolver): List<InvokeResult>? {
        val funIfSymbolic = { key: SymbolicValue ->     // ignore lowercase condition
            state.headers[key]?.run { listOf(MethodResult(this)) } ?: run {
                if (maxHeaders != null && maxHeaders <= state.indexToKey.size) {
                    null
                } else {
                    val value = createObject(findNewAddr(), STRING_TYPE, false)
                    state.headers[key] = value
                    state.indexToKey.add(key)
                    listOf(MethodResult(value))
                }
            }
        }
        val funIfConcrete = { param: SymbolicValue, key: String? ->
            key?.lowercase()?.run {
                state.headersConcreteKeyToKeys.getOrPut(this) { mutableSetOf() }.add(key)
                state.headersConcreteKey[this]?.run { listOf(MethodResult(this)) } ?: run {
                    if (maxHeaders != null && maxHeaders <= state.indexToKey.size) {
                        null    // not support headers after it
                    } else {
                        val addr = findNewAddr()
//                        val typeStorage = TypeStorage.constructTypeStorageWithSingleType(STRING_TYPE)
//                        val typeConstraint = typeRegistry.typeConstraint(addr, typeStorage).all().asHardConstraint()
//                        queuedSymbolicStateUpdates += typeConstraint
                        val value = createObject(addr, STRING_TYPE, false)
                        state.headersConcreteKey[this] = value
                        state.concreteKeyToValue[param] = this
                        state.indexToKey.add(param)
                        listOf(MethodResult(value))
                    }
                }
            }
        }

        val param = params[0]
        return if (state.headers.containsKey(param)) {
            funIfSymbolic(param)
        } else {
            val resolver = createResolver()
            overrideFunHelper(param, resolver, funIfConcrete, funIfSymbolic)
        }
    }

    private fun CyberTraverser.getParameterImpl(params: List<SymbolicValue>, createResolver: () -> Resolver): List<InvokeResult>? { // FIXME copypaste
        val funIfSymbolic = { key: SymbolicValue ->     // ignore lowercase condition
            state.parameters[key]?.run { listOf(MethodResult(this)) } ?: run {
                if (maxParameters != null && maxParameters <= state.parametersIndexToKey.size) {
                    null
                } else {
                    val value = createObject(findNewAddr(), STRING_TYPE, false)
                    state.parameters[key] = value
                    state.parametersIndexToKey.add(key)
                    listOf(MethodResult(value))
                }
            }
        }
        val funIfConcrete = { param: SymbolicValue, key: String? ->
            key?.lowercase()?.run {
                state.parametersConcreteKey[this]?.run { listOf(MethodResult(this)) } ?: run {
                    if (maxParameters != null && maxParameters <= state.parametersIndexToKey.size) {
                        null    // not support parameters after it
                    } else {
                        val addr = findNewAddr()
                        val value = createObject(addr, STRING_TYPE, false)
                        state.parametersConcreteKey[this] = value
                        state.parametersConcreteKeyToValue[param] = this
                        state.parametersIndexToKey.add(param)
                        listOf(MethodResult(value))
                    }
                }
            }
        }

        val param = params[0]
        return if (state.parameters.containsKey(param)) {
            funIfSymbolic(param)
        } else {
            val resolver = createResolver()
            overrideFunHelper(param, resolver, funIfConcrete, funIfSymbolic)
        }
    }

    override val signatureToSetFunResults: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> Unit> = mapOf(
        getCookiesSignature to { params, createResolver ->
            addActionByAddr(((params as ArrayList<*>)[0] as ArrayValue).addr) { wrapper, stateHolder ->
                val indexExpr = (wrapper.addr.internal as UtArraySelectExpression).index
                val index = when(indexExpr) {
                    is UtOpExpression -> simplificator.visit(indexExpr) as UtBvLiteral
                    is UtBvLiteral -> indexExpr
                    else -> throw CyberException("wrong addr expr: $indexExpr")
                }.value as Int
                if (index !in state.cookies) {
                    state.cookies[index] = stateHolder as CookieStateHolder
                } else {
                    (stateHolder as CookieStateHolder).state = (state.cookies[index] as CookieStateHolder).state
                }
            }
        },
    )

    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mapOf(
        getHeaderSignature to { params, createResolver ->
            getHeaderImpl(params, createResolver)
        },
//        getRequestURLSignature to { _, _ ->
//            null
//        },
//        getRequestURISignature to { _, _ ->
//            null
//        },
        getHeadersSignature to { params, createResolver ->
            val addr = findNewAddr()
            var hasMoreElementsI = 0
            registerStateHolder(addr, CyberEnumerationStateHolder(addActionByAddr, registerStateHolder, mapOf(
                enumerationToStringSignature to { _, _ ->
                    getHeaderImpl(params, createResolver)
                },
                enumerationHasMoreElementsSignature to { _, _ ->
                    if (hasMoreElementsI == 0) {
                        listOf(MethodResult(true.toPrimitiveValue()))
                    } else {
                        listOf(MethodResult(false.toPrimitiveValue()))
                    }
                },
                enumerationNextElementSignature to { _, _ ->
                    if (hasMoreElementsI == 0) {
                        hasMoreElementsI++
                        getHeaderImpl(params, createResolver)
                    } else {
                        null    // throw later
                    }
                }
            )))
            listOf(MethodResult(createObject(addr, cyberEnumerationType, useConcreteType = false)))
        },
//        getCookiesSignature to { params, createResolver ->
//            null
//        },
//        getQueryStringSignature to { _, _ ->
//            null
//        },
        getHeaderNamesSignature to { _, createResolver ->
            val addr = findNewAddr()
            var hasMoreElementsI = 0
            registerStateHolder(addr, CyberEnumerationStateHolder(addActionByAddr, registerStateHolder, mapOf(
                enumerationToStringSignature to { _, _ ->
                    listOf(MethodResult(state.indexToKey[hasMoreElementsI] as SymbolicValue))
                },
                enumerationHasMoreElementsSignature to { _, _ ->
                    if (maxHeaders == null || maxHeaders > hasMoreElementsI) {
                        listOf(MethodResult(true.toPrimitiveValue()))
                    } else {
                        listOf(MethodResult(false.toPrimitiveValue()))
                    }
                },
                enumerationNextElementSignature to { _, _ ->
                    if (maxHeaders == null || maxHeaders > hasMoreElementsI) {
                        val key = if (hasMoreElementsI == state.indexToKey.size) {
                            if (produceHeaders) {
                                val key = createObject(findNewAddr(), STRING_TYPE, false)
                                getHeaderImpl(listOf(key), createResolver)
                                hasMoreElementsI++
                                key
                            } else {
                                null
                            }
                        } else {
                            val key = state.indexToKey[hasMoreElementsI] as SymbolicValue
                            hasMoreElementsI++
                            key
                        }
                        key?.let { listOf(MethodResult(it)) }
                    } else {
                        null    // throw later
                    }
                }
            )))
            listOf(MethodResult(createObject(addr, cyberEnumerationType, useConcreteType = false)))
        },
        getParameterSignature to { params, createResolver ->
            getParameterImpl(params, createResolver)
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
        private val kclassServletRequest = javax.servlet.ServletRequest::class
        private val sootClassServletRequest by lazy { Scene.v().getSootClass(kclassServletRequest.qualifiedName) }

        private val getParameterSignature by lazy { sootClassServletRequest.getMethodByName("getParameter").subSignature }

        //

        private val enumerationKclass = org.cyber.utils.overrides.CyberEnumeration::class
        private val enumerationSootClass by lazy { Scene.v().getSootClass(enumerationKclass.qualifiedName) }

        private val enumerationToStringSignature by lazy { enumerationSootClass.getMethodByName("toString").subSignature }
        private val enumerationHasMoreElementsSignature by lazy { enumerationSootClass.getMethodByName("hasMoreElements").subSignature }
        private val enumerationNextElementSignature by lazy { enumerationSootClass.getMethodByName("nextElement").subSignature }

        val cyberEnumerationType: RefType
            get() = Scene.v().getSootClass(org.cyber.utils.overrides.CyberEnumeration::class.java.canonicalName).type



//        private val arrayKclass = org.cyber.utils.overrides.CyberArray::class
//        private val arraySootClass by lazy { Scene.v().getSootClass(arrayKclass.qualifiedName) }
//
//        private val arrayGetAtSignature by lazy { arraySootClass.getMethodByName("getAt").subSignature }
//
//        val cyberArrayType: RefType
//            get() = Scene.v().getSootClass(org.cyber.utils.overrides.CyberArray::class.java.canonicalName).type

        //

        override val saveArgsSignatures = emptySet<String>()

        override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mapOf()
        override val signatureToSetFunResults = mapOf<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> Unit>()

        private val maxHeaders: Int? = 8
        private val maxParameters: Int? = 8
        private const val produceHeaders = true;
    }
}
