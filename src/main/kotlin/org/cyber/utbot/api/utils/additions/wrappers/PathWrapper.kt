package org.cyber.utbot.api.utils.additions.wrappers

import org.cyber.utbot.api.exceptions.CyberException
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.*
import org.utbot.engine.pc.UtAddrExpression
import org.utbot.framework.plugin.api.*
import org.utbot.framework.plugin.api.util.stringClassId
import org.utbot.framework.util.graph
import org.utbot.framework.util.nextModelName
import soot.Scene
import soot.SootClass
import soot.SootMethod

private val cyberPathClass: SootClass
    get() = Scene.v().getSootClass(org.cyber.utils.overrides.CyberPath::class.qualifiedName)


class PathWrapper : BaseOverriddenWrapper(cyberPathClass.name) {
    private val toStringMethodSignature = overriddenClass.getMethodByName(org.cyber.utils.overrides.CyberPath::toString.name).subSignature
    private val initMethodSignature = overriddenClass.getMethod("<init>", emptyList()).subSignature
    private val symbolicValuesByAddress = mutableMapOf<UtAddrExpression, MutableMap<String, Any>>()

    override fun Traverser.overrideInvoke(
        wrapper: ObjectValue,
        method: SootMethod,
        parameters: List<SymbolicValue>
    ): List<InvokeResult>? = when (method.subSignature) {
        initMethodSignature -> {
            if (symbolicValuesByAddress[wrapper.addr] == null) {
                val symbolicValues = mutableMapOf<String, Any>()
                (this as CyberTraverser).getParams(wrapper)?.run {
                    symbolicValues["path"] = this[0]
                    symbolicValues["other"] = this[1]
                }
                symbolicValuesByAddress[wrapper.addr] = symbolicValues
            }
            null
        }
        toStringMethodSignature -> {
            listOf(MethodResult(symbolicValuesByAddress[wrapper.addr]?.let { it["path"] as ObjectValue }
                ?: throw CyberException("PathWrapper ${wrapper.type} not exist")))
        }
        else -> null
    }

    override fun value(resolver: Resolver, wrapper: ObjectValue): UtAssembleModel = resolver.run {
        val classId = wrapper.type.classId
        val addr = holder.concreteAddr(wrapper.addr)
        val modelName = nextModelName("cyberPath")

        val values = collectFieldModels(wrapper.addr, overriddenClass.type)
        val valueField = FieldId(overriddenClass.id, "value")
        val valueModel = values[valueField]!!

        val instantiationCall = UtExecutableCallModel(
            instance = null,
            MethodId(
                classId,
                "of",
                classId,
                listOf(stringClassId)
            ), listOf(valueModel)
        )

        return UtAssembleModel(addr, classId, modelName, instantiationCall)
    }

    companion object {
        private val ofMethod = cyberPathClass.getMethodByName(org.cyber.utils.overrides.CyberPath::of.name)
        private val ofMethodSignature = ofMethod.subSignature

        fun staticInvoke(method: SootMethod, parameters: List<SymbolicValue>): Pair<List<InvokeResult>?, Boolean> {
            return when (method.subSignature) {
                ofMethodSignature -> symbolicOfMethodImpl(parameters) ?: (null to false)
                else -> null to false
            }
        }

        private fun symbolicOfMethodImpl(
            parameters: List<SymbolicValue>
        ): Pair<List<InvokeResult>?, Boolean>? {
            return ofMethod?.run {
                val jimpleBody = this.jimpleBody()
                val graphResult = GraphResult(jimpleBody.graph())
                listOf(graphResult) to (parameters.isNotEmpty())
            }
        }
    }
}
