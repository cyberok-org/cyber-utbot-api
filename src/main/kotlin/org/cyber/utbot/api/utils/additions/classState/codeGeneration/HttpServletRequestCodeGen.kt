package org.cyber.utbot.api.utils.additions.classState.codeGeneration

import org.cyber.utbot.api.exceptions.CyberException
import org.cyber.utbot.api.utils.additions.classState.HttpServletRequestState
import org.utbot.engine.SymbolicValue
import org.utbot.framework.codegen.domain.models.*
import org.utbot.framework.plugin.api.*
import org.utbot.framework.plugin.api.util.id

class HttpServletRequestCodeGen private constructor(state: HttpServletRequestState<UtModel>): AnyCodeGen<HttpServletRequestState<UtModel>>(state) {
    override val classId = ClassId("javax.servlet.http.HttpServletRequest")

    override fun generate(declaration: CgDeclaration, getOrCreateVariable: (model: UtModel, name: String?) -> CgValue): List<CgStatement> {
        return mutableListOf<CgStatement>().also { l ->
            l.add(declaration)
            state.headers.forEach {
                val cgGetHeader = cgMethodCallFromDeclaration(declaration, "getHeader", it.key.asString())
                val givenCall = unsafeCgMethodCall(null, org.mockito.Mockito::class.id, "when", cgGetHeader)
                val willReturnCall = unsafeCgMethodCallArgsLiteral(givenCall, org.mockito.stubbing.OngoingStubbing::class.id, "thenReturn", it.value.asString())
                l.add(CgStatementExecutableCall(willReturnCall))
            }
            state.headersConcreteKey.forEach {
                state.headersConcreteKeyToKeys[it.key]?.forEach { key ->
                    val cgGetHeader = cgMethodCallFromDeclaration(declaration, "getHeader", key)
                    val givenCall = unsafeCgMethodCall(null, org.mockito.Mockito::class.id, "when", cgGetHeader)
                    val willReturnCall = unsafeCgMethodCallArgsLiteral(givenCall, org.mockito.stubbing.OngoingStubbing::class.id, "thenReturn", it.value.asString())
                    l.add(CgStatementExecutableCall(willReturnCall))
                } ?: throw CyberException("HttpServletRequestCodeGen internal error: headersConcreteKeyToKeys should exist")
            }
        }
    }

    companion object : CodeGenCreator<HttpServletRequestState<SymbolicValue>, HttpServletRequestState<UtModel>> {
        override fun resolve(state: HttpServletRequestState<SymbolicValue>, resolveModel: (value: SymbolicValue) -> UtModel):
                AnyCodeGen<HttpServletRequestState<UtModel>> = state.run {
            HttpServletRequestCodeGen(HttpServletRequestState<UtModel>().also {
                it.headers = headers.resolve(resolveModel)
                it.headersConcreteKey = headersConcreteKey.resolveConcreteKey(resolveModel)
                it.headersConcreteKeyToKeys = headersConcreteKeyToKeys
            })
        }
    }
}
