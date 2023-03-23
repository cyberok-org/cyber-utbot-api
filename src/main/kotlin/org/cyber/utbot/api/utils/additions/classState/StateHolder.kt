package org.cyber.utbot.api.utils.additions.classState

import org.cyber.utbot.api.utils.overrides.CyberTraverser
import org.utbot.engine.InvokeResult
import org.utbot.engine.ObjectValue
import org.utbot.engine.SymbolicValue
import org.utbot.engine.pc.UtAddrExpression
import soot.SootMethod


class StateHolder {
    private val addrToHolder = mutableMapOf<UtAddrExpression, AnyStateHolder>()

    companion object {
        private val classQnToHolderInit = mapOf(
            org.cyber.utils.overrides.CyberPath::class.qualifiedName to { PathStateHolder() },
            javax.servlet.http.Cookie::class.qualifiedName to { CookieStateHolder() },
            javax.servlet.http.HttpServletRequest::class.qualifiedName to { HttpServletRequestStateHolder() },
            javax.servlet.http.HttpServletResponse::class.qualifiedName to { HttpServletResponseStateHolder() },
            java.lang.System::class.qualifiedName to { SystemStateHolder() },
        )

        private val classQnToHolderObj = mapOf(
            java.nio.file.Path::class.qualifiedName to PathStateHolder,
            org.cyber.utils.overrides.CyberPath::class.qualifiedName to PathStateHolder,
            javax.servlet.http.Cookie::class.qualifiedName to CookieStateHolder,
            javax.servlet.http.HttpServletRequest::class.qualifiedName to HttpServletRequestStateHolder,
            javax.servlet.http.HttpServletResponse::class.qualifiedName to HttpServletResponseStateHolder,
            java.lang.System::class.qualifiedName to SystemStateHolder,
        )
    }

    private fun findHolder(wrapper: ObjectValue, classQn: String? = null) =
        addrToHolder[wrapper.addr] ?: classQn?.run { classQnToHolderInit[this]?.run {
            this().also { addrToHolder[wrapper.addr] = it }
        }}

    private fun findHolderObj(classQn: String?) = classQnToHolderObj[classQn]

    fun CyberTraverser.overrideInvoke(
        wrapper: ObjectValue?,
        method: SootMethod,
        parameters: List<SymbolicValue>
    ): List<InvokeResult>? = (wrapper?.run { findHolder(this, method.declaringClass.name) }
        ?: findHolderObj(method.declaringClass.name))?.run {
            with(this) { overrideInvoke(method, parameters) }
        }

    fun saveArgs(method: SootMethod): Boolean =
        findHolderObj(method.declaringClass.name)?.saveArgs(method) ?: false
}
