package org.cyber.utbot.api.utils.additions.wrappers

import org.utbot.engine.*
import org.utbot.framework.plugin.api.UtAssembleModel
import org.utbot.framework.plugin.api.UtExecutableCallModel
import org.utbot.framework.plugin.api.classId
import org.utbot.framework.plugin.api.util.constructorId
import org.utbot.framework.util.nextModelName
import soot.Scene
import soot.SootClass
import soot.SootMethod


private val cyberHttpServletResponseClass: SootClass
    get() = Scene.v().getSootClass(org.cyber.utils.overrides.CyberHttpServletResponse::class.qualifiedName)

class HttpServletResponseWrapper : BaseOverriddenWrapper(cyberHttpServletResponseClass.name) {
    private val setContentTypeSignature = overriddenClass.getMethodByName(org.cyber.utils.overrides.CyberHttpServletResponse::setContentType.name).subSignature

    override fun Traverser.overrideInvoke(
        wrapper: ObjectValue,
        method: SootMethod,
        parameters: List<SymbolicValue>
    ): List<InvokeResult>? = when(method.subSignature) {
        setContentTypeSignature ->  listOf(MethodResult(voidValue))
        else -> null
    }

    override fun value(resolver: Resolver, wrapper: ObjectValue): UtAssembleModel = resolver.run {
        val classId = wrapper.type.classId
        val addr = holder.concreteAddr(wrapper.addr)
        val modelName = nextModelName("cyberHttpServletResponseClass")

        val instantiationCall = UtExecutableCallModel(
            instance = null,
            constructorId(classId),
            emptyList()
        )

        return UtAssembleModel(addr, classId, modelName, instantiationCall)
    }
}
