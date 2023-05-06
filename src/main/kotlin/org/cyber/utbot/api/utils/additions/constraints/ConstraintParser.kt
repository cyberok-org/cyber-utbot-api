package org.cyber.utbot.api.utils.additions.constraints

import org.utbot.engine.pc.UtBoolExpression

typealias ConstraintVarName = String

object ConstraintParser {
    fun parse(names: Set<ConstraintVarName>, constraints: Set<UtBoolExpression>): Iterable<Constraint> {    // TODO
        return listOf()
    }
}
