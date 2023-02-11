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
        stmt: Stmt,
        cf: ClassFile,
    ): Boolean {
        val trace = traceArg.reversed()
        val methods = cf.methods
        val className = cf.name.replace('.', '/')
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