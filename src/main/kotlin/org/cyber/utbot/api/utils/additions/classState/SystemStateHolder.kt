package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.exceptions.CyberException
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.types.STRING_TYPE
import soot.*

class SystemStateHolder: AnyStateHolder() {
    private val storage = mutableMapOf<String, SymbolicValue>()

    private fun CyberTraverser.getStoreValue(key: String) = storage[key]?.run { listOf(MethodResult(this)) }
        ?: run {
            val value = createObject(findNewAddr(), STRING_TYPE, false)
            storage[key] = value
            listOf(MethodResult(value))
        }

    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>) -> List<InvokeResult>?> = mutableMapOf(
        getProperty1Signature to { params ->
            val key = (params[0].concrete as Concrete).value?.toString() ?: throw CyberException("SystemStateHolder.getProperty error")
            getStoreValue(key)
        },
        getProperty2Signature to { params ->
            val key = (params[0].concrete as Concrete).value?.toString() ?: throw CyberException("SystemStateHolder.getProperty error")
            getStoreValue(key)
        },
    )

    companion object: ArgsSaveHolder() {
        private val kclass = java.lang.System::class
        private val sootClass by lazy { Scene.v().getSootClass(kclass.qualifiedName) }

        private val getProperty1Signature by lazy { sootClass.getMethod("getProperty", listOf(STREAM_TYPE)).subSignature }
        private val getProperty2Signature by lazy { sootClass.getMethod("getProperty", listOf(STREAM_TYPE, STREAM_TYPE)).subSignature }

        override val saveArgsSignatures = emptySet<String>()
    }
}
