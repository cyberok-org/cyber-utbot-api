package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.additions.classState.codeGeneration.CodeGen
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.InvokeResult
import org.utbot.engine.ObjectValue
import org.utbot.engine.Resolver
import org.utbot.engine.SymbolicValue
import org.utbot.engine.pc.UtAddrExpression
import org.utbot.framework.plugin.api.EnvironmentModels
import org.utbot.framework.plugin.api.UtModel
import soot.SootMethod


class StateHolder(private val codeGen: CodeGen? = null) {
    init {
        codeGen?.clear()
    }

    private val addrToHolder = mutableMapOf<UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>>()

    companion object {
        private val classQnToHolderInit = mapOf<String?, ((UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit) -> CodeGenStateHolder<*, *>>(
            org.cyber.utils.overrides.CyberPath::class.qualifiedName to { x -> PathStateHolder(x) },
            javax.servlet.http.Cookie::class.qualifiedName to { x -> CookieStateHolder(x) },
            javax.servlet.http.HttpServletRequest::class.qualifiedName to { x -> HttpServletRequestStateHolder(x) },
            javax.servlet.http.HttpServletResponse::class.qualifiedName to { x -> HttpServletResponseStateHolder(x) },
            java.lang.System::class.qualifiedName to { x -> SystemStateHolder(x) },
            org.cyber.utils.overrides.CyberEnumeration::class.qualifiedName to { x -> CyberEnumerationStateHolder(x) },
        )

        private val classQnToHolderObj = mapOf(
            java.nio.file.Path::class.qualifiedName to PathStateHolder,
            org.cyber.utils.overrides.CyberPath::class.qualifiedName to PathStateHolder,
            javax.servlet.http.Cookie::class.qualifiedName to CookieStateHolder,
            javax.servlet.http.HttpServletRequest::class.qualifiedName to HttpServletRequestStateHolder,
            javax.servlet.http.HttpServletResponse::class.qualifiedName to HttpServletResponseStateHolder,
            java.lang.System::class.qualifiedName to SystemStateHolder,
            org.cyber.utils.overrides.CyberEnumeration::class.qualifiedName to CyberEnumerationStateHolder,
        )
    }

    private fun findHolder(wrapper: ObjectValue, classQn: String? = null) =
        addrToHolder[wrapper.addr]?.run { this } ?: classQn?.run { classQnToHolderInit[this]?.run {
            this{ addr, stateHolder -> registerStateHolderInstance(addr, stateHolder) }.also { addrToHolder[wrapper.addr] = it }
        }}

    private fun findHolderObj(classQn: String?) = classQnToHolderObj[classQn]

    fun CyberTraverser.overrideInvoke(
        wrapper: ObjectValue?,
        method: SootMethod,
        parameters: List<SymbolicValue>,
        createResolver: () -> Resolver
    ): List<InvokeResult>? = (wrapper?.run { findHolder(this, method.declaringClass.name) }
        ?: findHolderObj(method.declaringClass.name))?.run {
            with(this) { overrideInvoke(method, parameters, createResolver) }
        }

    private fun registerStateHolderInstance(addr: UtAddrExpression, stateHolder: CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) { // FIXME
        addrToHolder[addr] = stateHolder
    }

    fun saveArgs(method: SootMethod): Boolean =
        findHolderObj(method.declaringClass.name)?.saveArgs(method) ?: false

    fun updateCodeGenInfo(stateBefore: EnvironmentModels, parametersAddresses: List<UtAddrExpression>, resolveModel: (value: SymbolicValue) -> UtModel) {
        codeGen?.run {
            register(stateBefore, parametersAddresses.map {
                addrToHolder[it]?.run {
                    codeGenerator?.resolve(state, resolveModel)
                }
            })
        }
    }
}
