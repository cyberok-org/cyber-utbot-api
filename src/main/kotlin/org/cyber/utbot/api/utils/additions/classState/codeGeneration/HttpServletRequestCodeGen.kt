package org.cyber.utbot.api.utils.additions.classState.codeGeneration

import org.cyber.utbot.api.exceptions.CyberException
import org.cyber.utbot.api.utils.additions.classState.CookieState
import org.cyber.utbot.api.utils.additions.classState.CookieStateHolder
import org.cyber.utbot.api.utils.additions.classState.HttpServletRequestState
import org.utbot.engine.SymbolicValue
import org.utbot.framework.codegen.domain.models.*
import org.utbot.framework.plugin.api.*
import org.utbot.framework.plugin.api.util.id

class HttpServletRequestCodeGen private constructor(state: HttpServletRequestState<UtModel>): AnyCodeGen<HttpServletRequestState<UtModel>>(state) {
    override val classId = ClassId("javax.servlet.http.HttpServletRequest")

    override fun generate(statementsBufferAndDeclaration: List<CgStatement>, statementPos: Int, getOrCreateVariable: (model: UtModel, name: String?) -> CgValue): Pair<List<CgStatement>, Int> {
        val l = mutableListOf<CgStatement>()
        l.addAll(statementsBufferAndDeclaration.take(statementPos))

        val declaration = statementsBufferAndDeclaration[statementPos] as? CgDeclaration ?: throw CyberException("generate error: not CgDeclaration")
        l.add(declaration)

        state.indexToKey.forEach { key -> when(key) {
            is UtModel -> {
                val value = state.headers[key]!!
                val cgGetHeader = cgMethodCallFromVariable(declaration.toVariable(), "getHeader", key.asString())
                l.add(mockitoWhenThenReturn(cgGetHeader, value.asString()))
            }
            is String -> {
                val value = state.headersConcreteKey[key]!!
                state.headersConcreteKeyToKeys[key]?.forEach {
                    val cgGetHeader = cgMethodCallFromVariable(declaration.toVariable(), "getHeader", it)
                    l.add(mockitoWhenThenReturn(cgGetHeader, value.asString()))
                } ?: throw CyberException("HttpServletRequestCodeGen fail")
            }
            else -> throw CyberException("HttpServletRequestCodeGen fail")
        }}

        state.parametersIndexToKey.forEach { key -> when(key) {
            is UtModel -> {
                val value = state.parameters[key]!!
                val cgGetParameter = cgMethodCallFromVariable(declaration.toVariable(), "getParameter", key.asString())
                l.add(mockitoWhenThenReturn(cgGetParameter, value.asString()))
            }
            is String -> {
                val value = state.parametersConcreteKey[key]!!
                val cgGetParameter = cgMethodCallFromVariable(declaration.toVariable(), "getParameter", key)
                l.add(mockitoWhenThenReturn(cgGetParameter, value.asString()))
            }
            else -> throw CyberException("HttpServletRequestCodeGen fail")
        }}

        val newStatePos = l.size    // analyze further from this index

        var pos = statementPos + 1
        while (statementsBufferAndDeclaration.size > pos) {
            val statement = statementsBufferAndDeclaration[pos]
            if (statement is CgAssignment && statement.lValue.type == javax.servlet.http.Cookie::class.id) {
                (((statement.lValue as? CgArrayElementAccess)?.index as CgLiteral?)?.value as? Int)?.let { index ->
                    (state.cookies.remove(index) as? CookieState<UtModel>)?.let { value ->  // TODO to cookie codeGen
                        value.name?.run {
                            val cgGetName = cgMethodCallFromVariable(statement.rValue.toVariable(), "getName")
                            l.add(mockitoWhenThenReturn(cgGetName, asString()))
                        }
                        value.value?.run {
                            val cgValueName = cgMethodCallFromVariable(statement.rValue.toVariable(), "getValue")
                            l.add(mockitoWhenThenReturn(cgValueName, asString()))
                        }
                        value.path?.run {
                            val cgGetPath = cgMethodCallFromVariable(statement.rValue.toVariable(), "getPath")
                            l.add(mockitoWhenThenReturn(cgGetPath, asString()))
                        }
                        value.secure?.run {
                            val cgGetSecure = cgMethodCallFromVariable(statement.rValue.toVariable(), "getSecure")
                            l.add(mockitoWhenThenReturn(cgGetSecure, asPrimitive()))
                        }
                        value.maxAge?.run {
                            val cgGetMaxAge = cgMethodCallFromVariable(statement.rValue.toVariable(), "getMaxAge")
                            l.add(mockitoWhenThenReturn(cgGetMaxAge, asPrimitive()))
                        }
                        value.domain?.run {
                            val cgGetDomain = cgMethodCallFromVariable(statement.rValue.toVariable(), "getDomain")
                            l.add(mockitoWhenThenReturn(cgGetDomain, asString()))
                        }
                    }
                }
            }
            l.add(statement)
            pos++
        }
        return l to newStatePos
    }

    companion object : CodeGenCreator<HttpServletRequestState<SymbolicValue>, HttpServletRequestState<UtModel>> {
        override fun resolve(state: HttpServletRequestState<SymbolicValue>, resolveModel: (value: SymbolicValue) -> UtModel):
                AnyCodeGen<HttpServletRequestState<UtModel>> = state.run {
            HttpServletRequestCodeGen(HttpServletRequestState<UtModel>().also {
                it.headers = headers.resolve(resolveModel)
                it.headersConcreteKey = headersConcreteKey.resolveConcreteKey(resolveModel)
                it.headersConcreteKeyToKeys = headersConcreteKeyToKeys
                val valueToModel = (headers.keys zip it.headers.keys).toMap()
                it.indexToKey = indexToKey.map { key ->
                    valueToModel[key] ?: concreteKeyToValue[key]
                }.toMutableList()

                it.cookies = cookies.entries.associate { p -> p.key to (p.value as CookieStateHolder).state.run {
                    CookieState<UtModel>().also { cookie ->
                        cookie.name = name?.let(resolveModel)
                        cookie.value = value?.let(resolveModel)
                        cookie.path = path?.let(resolveModel)
                        cookie.secure = secure?.let(resolveModel)
                        cookie.maxAge = maxAge?.let(resolveModel)
                        cookie.domain = domain?.let(resolveModel)
                    }
                } }.toMutableMap()

                it.parameters = parameters.resolve(resolveModel)
                it.parametersConcreteKey = parametersConcreteKey.resolveConcreteKey(resolveModel)
                val parametersValueToModel = (parameters.keys zip it.parameters.keys).toMap()
                it.parametersIndexToKey = parametersIndexToKey.map { key ->
                    parametersValueToModel[key] ?: parametersConcreteKeyToValue[key]
                }.toMutableList()
            })
        }
    }
}
