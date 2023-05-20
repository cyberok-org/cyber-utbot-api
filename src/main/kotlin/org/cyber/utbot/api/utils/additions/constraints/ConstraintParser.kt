package org.cyber.utbot.api.utils.additions.constraints

import org.utbot.engine.SymbolicValue
import org.utbot.engine.pc.*

object ConstraintParser {
    fun parse(names: List<SymbolicValue>, constraints: Set<UtBoolExpression>): Constraints {
        val parser = Parser()
        val constraintSet = CyberConstraintSet()

        constraints.map {
            constraintSet.register(it.accept(parser))
        }
        return Constraints(names, constraintSet.result)
    }
}
