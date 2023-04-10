package org.cyber.utbot.api.utils.additions.classState.codeGeneration

import org.cyber.utbot.api.exceptions.CyberException
import org.cyber.utbot.api.utils.additions.classState.AnyState
import org.utbot.framework.codegen.domain.models.CgDeclaration
import org.utbot.framework.codegen.domain.models.CgStatement
import org.utbot.framework.codegen.domain.models.CgValue
import org.utbot.framework.plugin.api.EnvironmentModels
import org.utbot.framework.plugin.api.UtModel

class CodeGen {
    private val generators = mutableMapOf<EnvironmentModels, List<AnyCodeGen<AnyState<UtModel>>?>>()

    fun register(stateBefore: EnvironmentModels, codeGens: List<AnyCodeGen<AnyState<UtModel>>?>) {
        generators[stateBefore] = codeGens
    }

    fun generate(stateBefore: EnvironmentModels, statements: List<CgStatement>, getOrCreateVariable: (model: UtModel, name: String?) -> CgValue): List<CgStatement> {    // FIXME !!
        val codeGens = generators[stateBefore] ?: throw CyberException("CodeGen fail")
        var codeGenI = 0
        while (codeGens.size > codeGenI && codeGens[codeGenI] == null) { codeGenI++ }

        val newStatements = mutableListOf<CgStatement>()
        statements.forEach { statement ->
            if (statement is CgDeclaration) {
                if (codeGens.size > codeGenI && codeGens[codeGenI]!!.classId == statement.variableType) {
                    newStatements.addAll(codeGens[codeGenI]!!.generate(statement, getOrCreateVariable))
                    codeGenI++
                    while (codeGens.size > codeGenI && codeGens[codeGenI] == null) { codeGenI++ }
                } else {
                    newStatements.add(statement)
                }
            } else {
                newStatements.add(statement)
            }
        }
        return newStatements
    }

    fun clear() = generators.clear()
}
