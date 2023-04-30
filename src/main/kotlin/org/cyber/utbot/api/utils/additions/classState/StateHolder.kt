package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.additions.classState.codeGeneration.CodeGen
import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.InvokeResult
import org.utbot.engine.ObjectValue
import org.utbot.engine.Resolver
import org.utbot.engine.SymbolicValue
import org.utbot.engine.pc.Simplificator
import org.utbot.engine.pc.UtAddrExpression
import org.utbot.engine.pc.UtArraySelectExpression
import org.utbot.framework.plugin.api.EnvironmentModels
import org.utbot.framework.plugin.api.UtModel
import soot.SootMethod


internal val simplificator = Simplificator()

class StateHolder(private val codeGen: CodeGen? = null) {
    init {
        codeGen?.clear()
    }

    private val addrToHolder = mutableMapOf<UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>>()

    private val addrToAction = mutableMapOf<UtAddrExpression, MutableList<(ObjectValue, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit>>()

    companion object {
        private val classQnToHolderInit = mapOf<String?, ((UtAddrExpression, (ObjectValue, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit) -> Unit, (UtAddrExpression, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit) -> CodeGenStateHolder<*, *>>(
            org.cyber.utils.overrides.CyberPath::class.qualifiedName to { x, y -> PathStateHolder(x, y) },
            javax.servlet.http.Cookie::class.qualifiedName to { x, y -> CookieStateHolder(x, y) },
            javax.servlet.http.HttpServletRequest::class.qualifiedName to { x, y -> HttpServletRequestStateHolder(x, y) },
            javax.servlet.ServletRequest::class.qualifiedName to { x, y -> HttpServletRequestStateHolder(x, y) },   // TODO is it ok?
            javax.servlet.http.HttpServletResponse::class.qualifiedName to { x, y -> HttpServletResponseStateHolder(x, y) },
            java.lang.System::class.qualifiedName to { x, y -> SystemStateHolder(x, y) },
            org.cyber.utils.overrides.CyberEnumeration::class.qualifiedName to { x, y -> CyberEnumerationStateHolder(x, y) },
//            org.cyber.utils.overrides.CyberArray::class.qualifiedName to { x, y -> CyberArrayStateHolder(x, y) },
        )

        private val classQnToHolderObj = mapOf(
            java.nio.file.Path::class.qualifiedName to PathStateHolder,
            org.cyber.utils.overrides.CyberPath::class.qualifiedName to PathStateHolder,
            javax.servlet.http.Cookie::class.qualifiedName to CookieStateHolder,
            javax.servlet.http.HttpServletRequest::class.qualifiedName to HttpServletRequestStateHolder,
            javax.servlet.ServletRequest::class.qualifiedName to HttpServletRequestStateHolder,
            javax.servlet.http.HttpServletResponse::class.qualifiedName to HttpServletResponseStateHolder,
            java.lang.System::class.qualifiedName to SystemStateHolder,
            org.cyber.utils.overrides.CyberEnumeration::class.qualifiedName to CyberEnumerationStateHolder,
//            org.cyber.utils.overrides.CyberArray::class.qualifiedName to CyberArrayStateHolder,
        )
    }

    private fun registerStateHolderInstance(addr: UtAddrExpression, stateHolder: CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) { // FIXME
        addrToHolder[addr] = stateHolder
    }

    private fun addActionByAddr(addr: UtAddrExpression, action: (ObjectValue, CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) -> Unit) {
        addrToAction.getOrPut(addr) { mutableListOf() }.add(action)
    }

    private fun applyActions(addr: UtAddrExpression, wrapper: ObjectValue, stateHolder: CodeGenStateHolder<AnyState<SymbolicValue>, AnyState<UtModel>>) {
        addrToAction[addr]?.forEach { action ->
            action(wrapper, stateHolder)
        }
    }

    private fun findHolder(wrapper: ObjectValue, classQn: String? = null) =
        addrToHolder[wrapper.addr]?.run { this } ?: classQn?.run {
            classQnToHolderInit[this]?.run {
                this({addr, action -> addActionByAddr(addr, action)}) { addr, stateHolder -> registerStateHolderInstance(addr, stateHolder) }.also { addrToHolder[wrapper.addr] = it }
            }?.also {
                (((wrapper.addr.internal as? UtArraySelectExpression)?.arrayExpression as? UtArraySelectExpression)?.index as? UtAddrExpression)?.run {
                    applyActions(this, wrapper, it)
                }
            }
        }

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

    fun CyberTraverser.setInvokeResults(
        wrapper: ObjectValue?,
        method: SootMethod,
        invokeResults:  List<SymbolicValue>,
        createResolver: () -> Resolver
    ) {
        (wrapper?.run { findHolder(this, method.declaringClass.name) }
            ?: findHolderObj(method.declaringClass.name))?.run {
            with(this) { setInvokeResults(method, invokeResults, createResolver) }
        }
    }

    fun saveArgs(method: SootMethod): Boolean =
        findHolderObj(method.declaringClass.name)?.saveArgs(method) ?: false

    fun updateCodeGenInfo(stateBefore: EnvironmentModels, parametersAddresses: List<UtAddrExpression?>, resolveModel: (value: SymbolicValue) -> UtModel) {
        codeGen?.run {
            register(stateBefore, parametersAddresses.map {
                it?.let {
                    addrToHolder[it]?.run {
                        codeGenerator?.resolve(state, resolveModel)
                    }
                }
            })
        }
    }
}
