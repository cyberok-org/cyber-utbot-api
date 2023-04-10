package org.cyber.utbot.api.utils.additions.classState.codeGeneration

import org.cyber.utbot.api.utils.additions.classState.AnyState
import org.utbot.engine.SymbolicValue
import org.utbot.framework.plugin.api.UtModel

interface CodeGenCreator<out T: AnyState<SymbolicValue>, out K: AnyState<UtModel>> {    // FIXME same AnyState
    fun resolve(state: @UnsafeVariance T, resolveModel: (value: SymbolicValue) -> UtModel): AnyCodeGen<K>
}
