package org.cyber.utbot.api.utils.overrides

import org.cyber.utbot.api.utils.annotations.CyberNew
import org.utbot.engine.MethodSymbolicImplementation
import org.utbot.engine.ObjectValue
import org.utbot.engine.Resolver
import org.utbot.engine.WrapperInterface
import org.utbot.framework.plugin.api.UtModel
import org.utbot.framework.plugin.api.UtPrimitiveModel

@CyberNew("simple wrapper, convenience")
data class CyberConcreteSimpleWrapper(private val value: Any?) : WrapperInterface {
    override val wrappedMethods: Map<String, MethodSymbolicImplementation> = emptyMap()

    override fun value(resolver: Resolver, wrapper: ObjectValue): UtModel = UtPrimitiveModel(value ?: "")
}
