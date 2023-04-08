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
        statements.forEach { statement -> when (statement) {
            is CgDeclaration -> {
                if (newCodeGenI < codeGens.size && codeGens[newCodeGenI]!!.classId == statement.variableType) {
                    newStatements.addAll(codeGens[newCodeGenI]!!.generate(statement, getOrCreateVariable))
                    newCodeGenI++
                    while (codeGens.size > newCodeGenI && codeGens[newCodeGenI] == null) newCodeGenI++
                } else {
                    newStatements.add(statement)
                }
            }
            is CgInnerBlock -> {
                mainUpdatePart(statement.statements, newCodeGenI).also { (newStatementsMain, newCodeGenIMain) ->
                    statement.statements = newStatementsMain
                    newCodeGenI = newCodeGenIMain
                }
                newStatements.add(statement)
            }
            is CgTryCatch -> {  // only for try statements
                mainUpdatePart(statement.statements, newCodeGenI).also { (newStatementsMain, newCodeGenIMain) ->
                    statement.statements = newStatementsMain
                    newCodeGenI = newCodeGenIMain
                }
                newStatements.add(statement)
            }
            is CgLoop -> {  // CgForLoop, CgWhileLoop, CgDoWhileLoop, CgForEachLoop
                mainUpdatePart(statement.statements, newCodeGenI).also { (newStatementsMain, newCodeGenIMain) ->
                    statement.statements = newStatementsMain
                    newCodeGenI = newCodeGenIMain
                }
                newStatements.add(statement)
            }
            is CgSwitchCaseLabel -> {
                mainUpdatePart(statement.statements, newCodeGenI).also { (newStatementsMain, newCodeGenIMain) ->
                    statement.statements = newStatementsMain
                    newCodeGenI = newCodeGenIMain
                }
                newStatements.add(statement)
            }
            else -> newStatements.add(statement)
        }}
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
