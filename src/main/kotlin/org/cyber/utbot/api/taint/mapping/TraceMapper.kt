package org.cyber.utbot.api.taint.mapping

import javassist.bytecode.ClassFile
import javassist.bytecode.MethodInfo
import proguard.analysis.cpa.jvm.domain.memory.BamLocationDependentJvmMemoryLocation
import proguard.classfile.MethodSignature
import soot.UnitPatchingChain
import soot.jimple.Stmt

class TraceMapper {
    fun map(
        traceArg: List<BamLocationDependentJvmMemoryLocation<*>>,
        jimpleUnits: UnitPatchingChain,
        cf: ClassFile,
    ): MutableMap<ProguardInstruction, JimpleInstruction> {
        val trace = traceArg.reversed()
        val methods = cf.methods
        val className = cf.name.replace('.', '/')
        val instructionsMap = mutableMapOf<ProguardInstruction, JimpleInstruction>()
        trace.forEach { el ->
            val programLocation = el.programLocation
            val signature = programLocation.signature
            val method = methodByDescriptor(methods, signature, className)
            method?.let { met ->
                val lineNumber = met.getLineNumber(programLocation.offset)
                val unit = jimpleUnits.find { it.javaSourceStartLineNumber == lineNumber }
                unit?.let {
                    val proguardInstruction = ProguardInstruction(lineNumber, programLocation)
                    val jimpleInstruction = JimpleInstruction(lineNumber, it)
                    instructionsMap[proguardInstruction] = jimpleInstruction
                }
            }
        }
        return instructionsMap
    }

    fun map(
        traceArg: List<BamLocationDependentJvmMemoryLocation<*>>,
        stmt: Stmt,
        cf: ClassFile,
    ): Boolean {
        val trace = traceArg.reversed()
        val methods = cf.methods
        val className = cf.name.replace('.', '/')
        val l = mutableListOf<String>()
        trace.forEach { el ->
            val programLocation = el.programLocation
            val signature = programLocation.signature
            val method = methodByDescriptor(methods, signature, className)
            method?.let { met ->
                val lineNumber = met.getLineNumber(programLocation.offset)
                // if we found a corresponding jimple instruction for the current observed trace instruction
                if (stmt.javaSourceStartLineNumber == lineNumber) return true
            }
        }
        return false
    }

    private fun methodByDescriptor(
        methods: MutableList<MethodInfo>,
        signature: MethodSignature,
        className: String
    ) = methods.find { "L${className};${it.name}${it.descriptor}" == signature.toString() }
}