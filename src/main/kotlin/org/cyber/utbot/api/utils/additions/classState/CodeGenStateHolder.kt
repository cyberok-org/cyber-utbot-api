package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.additions.classState.codeGeneration.CodeGenCreator
import org.utbot.engine.ObjectValue
import org.utbot.engine.SymbolicValue
import org.utbot.engine.pc.UtAddrExpression
import org.utbot.framework.plugin.api.UtModel

abstract class CodeGenStateHolder<out S: AnyState<SymbolicValue>, out M: AnyState<UtModel>>(
    protected val addActionByAddr: (UtAddrExpression, (ObjectValue, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit) -> Unit,
    protected val registerStateHolder: (UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit): AnyStateHolder() {
    abstract val state: S
    abstract val codeGenerator: CodeGenCreator<S, M>?
}
