package org.cyber.utbot.api.utils.additions.constraints

import org.utbot.engine.SymbolicValue

class Constraints(val arguments: List<SymbolicValue>, val addrToConstraint: MutableMap<Addr, MutableList<CyberConstraint>>)
