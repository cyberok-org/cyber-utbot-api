package org.cyber.utbot.api.utils.additions.classState.codeGeneration

import org.cyber.utbot.api.utils.additions.classState.AnyState
import org.utbot.framework.codegen.domain.models.*
import org.utbot.framework.plugin.api.EnvironmentModels
import org.utbot.framework.plugin.api.UtModel

class CodeGen {
    private val generators = mutableMapOf<EnvironmentModels, List<AnyCodeGen<AnyState<UtModel>>?>>()

    fun register(stateBefore: EnvironmentModels, codeGens: List<AnyCodeGen<AnyState<UtModel>>?>) {
        generators[stateBefore] = codeGens
    }

    // TODO test it, improve
    private fun updateStatements(statements: List<CgStatement>, newStatements: MutableList<CgStatement>, codeGens: List<AnyCodeGen<AnyState<UtModel>>?>, codeGenI: Int, getOrCreateVariable: (model: UtModel, name: String?) -> CgValue): Int {
        fun mainUpdatePart(statements: List<CgStatement>, codeGenI: Int): Pair<List<CgStatement>, Int> {
            val newSubStatements = mutableListOf<CgStatement>()
            return newSubStatements to updateStatements(statements, newSubStatements, codeGens, codeGenI, getOrCreateVariable)
        }

        var newCodeGenI = codeGenI
        var statementPos = 0
        var statementsBuffer = statements.toMutableList()
        while (statementsBuffer.size > statementPos) {
            when (val statement = statementsBuffer[statementPos]) {
                is CgDeclaration -> {
                    if (newCodeGenI < codeGens.size && codeGens[newCodeGenI]!!.classId == statement.variableType) {
                        val (updatedStatementsBuffer, pos) = codeGens[newCodeGenI]!!.generate(statementsBuffer, statementPos, getOrCreateVariable)
                        statementsBuffer = updatedStatementsBuffer.toMutableList()
                        statementPos = pos
                        newCodeGenI++
                        while (codeGens.size > newCodeGenI && codeGens[newCodeGenI] == null) newCodeGenI++
                        continue
                    }
                }
                is CgInnerBlock -> {
                    mainUpdatePart(statement.statements, newCodeGenI).also { (newStatementsMain, newCodeGenIMain) ->
                        statement.statements = newStatementsMain
                        newCodeGenI = newCodeGenIMain
                    }
                }
                is CgTryCatch -> {  // only for try statements
                    mainUpdatePart(statement.statements, newCodeGenI).also { (newStatementsMain, newCodeGenIMain) ->
                        statement.statements = newStatementsMain
                        newCodeGenI = newCodeGenIMain
                    }
                }
                is CgLoop -> {  // CgForLoop, CgWhileLoop, CgDoWhileLoop, CgForEachLoop
                    mainUpdatePart(statement.statements, newCodeGenI).also { (newStatementsMain, newCodeGenIMain) ->
                        statement.statements = newStatementsMain
                        newCodeGenI = newCodeGenIMain
                    }
                }
                is CgSwitchCaseLabel -> {
                    mainUpdatePart(statement.statements, newCodeGenI).also { (newStatementsMain, newCodeGenIMain) ->
                        statement.statements = newStatementsMain
                        newCodeGenI = newCodeGenIMain
                    }
                }
                else -> {}
            }
            statementPos++
        }
        newStatements.addAll(statementsBuffer)
        return newCodeGenI
    }

    fun generate(stateBefore: EnvironmentModels, statements: List<CgStatement>, getOrCreateVariable: (model: UtModel, name: String?) -> CgValue): List<CgStatement> {    // FIXME !!
        val codeGens = generators[stateBefore] ?: return statements
        var codeGenI = 0
        while (codeGens.size > codeGenI && codeGens[codeGenI] == null) { codeGenI++ }
        val newStatements = mutableListOf<CgStatement>()
        updateStatements(statements, newStatements, codeGens, codeGenI, getOrCreateVariable)
        return newStatements
    }

    fun clear() = generators.clear()
}
