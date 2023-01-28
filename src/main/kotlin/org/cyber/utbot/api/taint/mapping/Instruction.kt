package org.cyber.utbot.api.taint.mapping

import proguard.analysis.cpa.jvm.cfa.nodes.JvmCfaNode
import soot.Unit

abstract class AbstractInstruction(val lineNumber: Int)

class JimpleInstruction(lineNumber: Int, val instruction: Unit) : AbstractInstruction(lineNumber)

class ProguardInstruction(lineNumber: Int, val instruction: JvmCfaNode) : AbstractInstruction(lineNumber)
