package org.cyber.utbot.api.utils.additions.wrappers

import org.utbot.engine.*
import org.utbot.engine.types.STRING_TYPE
import org.utbot.framework.plugin.api.*
import org.utbot.framework.plugin.api.util.stringClassId
import org.utbot.framework.util.graph
import org.utbot.framework.util.nextModelName
import soot.Scene
import soot.SootClass
import soot.SootMethod

private val cyberCookieClass: SootClass
    get() = Scene.v().getSootClass(org.cyber.utils.overrides.CyberCookie::class.qualifiedName)

class CookieWrapper : BaseOverriddenWrapper(cyberCookieClass.name) {
    private val initMethod = overriddenClass.getMethod("<init>", listOf(STRING_TYPE, STRING_TYPE))
    private val initSignature = initMethod.subSignature

    override fun Traverser.overrideInvoke(
        wrapper: ObjectValue,
        method: SootMethod,
        parameters: List<SymbolicValue>
    ): List<InvokeResult>? = when(method.subSignature) {
        initSignature -> {
            val jimpleBody = initMethod.jimpleBody()
            val graphResult = GraphResult(jimpleBody.graph())
            listOf(graphResult)
        }
        else -> null
    }

    override fun value(resolver: Resolver, wrapper: ObjectValue): UtAssembleModel = resolver.run {
        val classId = wrapper.type.classId
        val addr = holder.concreteAddr(wrapper.addr)
        val modelName = nextModelName("cyberCookieClass")

        val instantiationCall = UtExecutableCallModel(
            instance = null,
            MethodId(
                classId,
                "<init>",
                classId,
                listOf(stringClassId, stringClassId)
            ),
            listOf(UtPrimitiveModel(""), UtPrimitiveModel(""))
        )

        return UtAssembleModel(addr, classId, modelName, instantiationCall)
    }
}