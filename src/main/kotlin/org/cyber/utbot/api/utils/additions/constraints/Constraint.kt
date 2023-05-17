package org.cyber.utbot.api.utils.additions.constraints

import org.utbot.engine.pc.UtAddrExpression

class Constraints(val arguments: List<UtAddrExpression>, val addrToConstraint: MutableMap<Addr, MutableList<CyberConstraint>>)
