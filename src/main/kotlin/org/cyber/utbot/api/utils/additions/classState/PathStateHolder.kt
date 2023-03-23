package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.additions.ELEMENT_ARRAY_TYPE
import org.cyber.utbot.api.utils.additions.JAVA_NET_URI_TYPE
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.types.STRING_TYPE
import soot.*

class PathStateHolder: AnyStateHolder() {
    private var path: SymbolicValue? = null         // String
    private var more: SymbolicValue? = null         // List<String>
    private var uri: SymbolicValue? = null          // java.net.URI

    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>) -> List<InvokeResult>?> = mutableMapOf(
        toStringSignature to {
            path?.run {
                listOf(MethodResult(path as ObjectValue))
            }
        },
        init1Signature to { params ->
            uri ?: run {
                uri = params[0]
            }
            null
        },
        init2Signature to {params ->
            path ?: run {
                path = params[0]
                more = params[1]
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

        override val saveArgsSignatures = setOf(
            ofMethod1Signature,
            ofMethod2Signature
        )
    }
}
