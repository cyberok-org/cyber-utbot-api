package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.exceptions.CyberException
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.pc.UtAddrExpression
import org.utbot.engine.types.STRING_TYPE
import org.utbot.framework.plugin.api.UtModel
import soot.*

class SystemState<T>: AnyState<T> {
    object Values: AnyState<SymbolicValue> {
        var properties = mutableMapOf<SymbolicValue, SymbolicValue>()
        var propertiesConcreteKey = mutableMapOf<String, SymbolicValue>()
    }

    object Models: AnyState<UtModel> {
        var properties = mutableMapOf<UtModel, UtModel>()
        var propertiesConcreteKey = mutableMapOf<String, UtModel>()
    }
}

class SystemStateHolder(registerStateHolder: (UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit):
    CodeGenStateHolder<SystemState<SymbolicValue>, SystemState<UtModel>>(registerStateHolder) {
    override val state = SystemState<SymbolicValue>()
    override val codeGenerator
        get() = null

    override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mutableMapOf()

    companion object: ArgsSaveHolder() {
        private val kclass = java.lang.System::class
        private val sootClass by lazy { Scene.v().getSootClass(kclass.qualifiedName) }

        private val getProperty1Signature by lazy { sootClass.getMethod("getProperty", listOf(STRING_TYPE)).subSignature }
        private val getProperty2Signature by lazy { sootClass.getMethod("getProperty", listOf(STRING_TYPE, STRING_TYPE)).subSignature }

        override val saveArgsSignatures = emptySet<String>()

        private fun CyberTraverser.getStoreValue(param: SymbolicValue, createResolver: () -> Resolver): List<InvokeResult> {
            val funIfSymbolic = { key: SymbolicValue ->
                SystemState.Values.properties[key]?.run { listOf(MethodResult(this)) } ?: run {
                    val value = createObject(findNewAddr(), STRING_TYPE, false)
                    SystemState.Values.properties[key] = value
                    listOf(MethodResult(value))
                }
            }
            val funIfConcrete = { key: String? ->
                key?.run {
                    SystemState.Values.propertiesConcreteKey[this]?.run { listOf(MethodResult(this)) } ?: run {
                        val value = createObject(findNewAddr(), STRING_TYPE, false)
                        SystemState.Values.propertiesConcreteKey[this] = value
                        listOf(MethodResult(value))
                    }
                }
            }

            return if (SystemState.Values.properties.containsKey(param)) {
                funIfSymbolic(param)
            } else {
                val resolver = createResolver()
                overrideFunHelper(param, resolver, funIfConcrete, funIfSymbolic) ?: throw CyberException("SystemStateHolder.getStoreValue failed")
            }
        }

        override val signatureToOverrideFun: Map<String, CyberTraverser.(List<SymbolicValue>, () -> Resolver) -> List<InvokeResult>?> = mutableMapOf(
            getProperty1Signature to { params, createResolver ->
                getStoreValue(params[0], createResolver)
            },
            getProperty2Signature to { params, createResolver ->
                getStoreValue(params[0], createResolver) + listOf(MethodResult(params[1] as ObjectValue))
            },
        )
    }
}
