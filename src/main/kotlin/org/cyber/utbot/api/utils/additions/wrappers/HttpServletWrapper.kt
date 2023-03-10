package org.cyber.utbot.api.utils.additions.wrappers

import org.utbot.engine.*
import org.utbot.framework.plugin.api.UtAssembleModel
import org.utbot.framework.plugin.api.UtExecutableCallModel
import org.utbot.framework.plugin.api.classId
import org.utbot.framework.plugin.api.util.constructorId
import org.utbot.framework.util.graph
import org.utbot.framework.util.nextModelName
import soot.Scene
import soot.SootClass
import soot.SootMethod

private val httpServletClass: SootClass
    get() = Scene.v().getSootClass(org.cyber.utils.overrides.CyberHttpServlet::class.qualifiedName)

class HttpServletWrapper : BaseOverriddenWrapper(httpServletClass.name) {
    private val initMethod = overriddenClass.getMethod("<init>", emptyList())
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
        val modelName = nextModelName("cyberHttpServletClass")

        val instantiationCall = UtExecutableCallModel(
            instance = null,
            constructorId(classId),
            listOf()
        )

        return UtAssembleModel(addr, classId, modelName, instantiationCall)
    }
}