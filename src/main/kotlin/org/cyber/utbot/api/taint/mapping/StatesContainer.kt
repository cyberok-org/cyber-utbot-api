package org.cyber.utbot.api.taint.mapping

import proguard.analysis.cpa.jvm.domain.memory.BamLocationDependentJvmMemoryLocation
import soot.jimple.Stmt

typealias Traces = MutableSet<List<BamLocationDependentJvmMemoryLocation<*>>>

class StatesContainer {
    val states: MutableMap<Stmt, Traces> = mutableMapOf()

    operator fun get(index: Stmt?): Traces? {
        return states[index]
    }
}

