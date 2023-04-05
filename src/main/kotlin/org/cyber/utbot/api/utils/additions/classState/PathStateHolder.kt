package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.additions.ELEMENT_ARRAY_TYPE
import org.cyber.utbot.api.utils.additions.JAVA_NET_URI_TYPE
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.pc.UtAddrExpression
import org.utbot.engine.types.STRING_TYPE
import org.utbot.framework.plugin.api.UtModel
import soot.*

class PathState<T>: AnyState<T> {
    var path: T? = null         // String
    var more: T? = null         // List<String>
    var uri: T? = null          // java.net.URI
}

class PathStateHolder(registerStateHolder: (UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit):
    CodeGenStateHolder<PathState<SymbolicValue>, PathState<UtModel>>(registerStateHolder) {
    override val state = PathState<SymbolicValue>()
    override val codeGenerator
        get() = null

    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mutableMapOf(
        toStringSignature to {_, _ ->
            state.path?.run {
                listOf(MethodResult(state.path as ObjectValue))
            }
        },
        init1Signature to { params, _ ->
            state.uri ?: run {
                state.uri = params[0]
            }
            null
        },
        init2Signature to {params, _ ->
            state.path ?: run {
                state.path = params[0]
                state.more = params[1]
            }
            null
        },
    )

    companion object: ArgsSaveHolder() {
        private val kclass = java.nio.file.Path::class
        private val sootClass by lazy { Scene.v().getSootClass(kclass.qualifiedName) }
        private val overrideKclass = org.cyber.utils.overrides.CyberPath::class
        private val overrideSootClass by lazy { Scene.v().getSootClass(overrideKclass.qualifiedName) }

        private val toStringSignature by lazy { overrideSootClass.getMethodByName("toString").subSignature }
        private val init1Signature by lazy { overrideSootClass.getMethod("<init>", listOf(JAVA_NET_URI_TYPE)).subSignature }
        private val init2Signature by lazy { overrideSootClass.getMethod("<init>", listOf(STRING_TYPE, ELEMENT_ARRAY_TYPE(STRING_TYPE))).subSignature }

        private val ofMethod1Signature by lazy { sootClass.getMethod("of", listOf(JAVA_NET_URI_TYPE)).subSignature }
        private val ofMethod2Signature by lazy { sootClass.getMethod("of", listOf(STRING_TYPE, ELEMENT_ARRAY_TYPE(STRING_TYPE))).subSignature }

        //

        override val saveArgsSignatures = setOf(
            ofMethod1Signature,
            ofMethod2Signature
        )

        override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mutableMapOf()
    }
}
