package org.cyber.utbot.api.utils.additions.classState.codeGeneration

import org.cyber.utbot.api.exceptions.CyberException
import org.utbot.framework.codegen.domain.models.*
import org.utbot.framework.codegen.util.nullLiteral
import org.utbot.framework.plugin.api.*
import org.utbot.framework.plugin.api.util.id
import org.utbot.framework.plugin.api.util.objectClassId

private val nullType = nullLiteral().type

fun cgMethodCallFromDeclaration(instance: CgDeclaration, method: String, vararg arguments: Any?, returnType: ClassId = nullType): CgMethodCall {
    val argumentsTypes = mutableListOf<ClassId>()
    val argumentsLiterals = mutableListOf<CgLiteral>()
    arguments.forEach {
        val classId = it?.run { this::class.java.id } ?: objectClassId  // TODO is ok?
        argumentsTypes.add(classId)
        argumentsLiterals.add(CgLiteral(classId, it))
    }
    return CgMethodCall(instance.variable, MethodId(instance.variableType, method, returnType, argumentsTypes), argumentsLiterals)
}

fun UtModel.asString() = when (this) {
    is UtNullModel -> null
    is UtAssembleModel -> ((instantiationCall).params[0] as UtPrimitiveModel).value as String
    else -> throw CyberException("UtModel.asString: wrong model")
}

// wrong types

fun unsafeCgMethodCall(caller: CgExpression?, classId: ClassId, method: String, vararg arguments: CgExpression): CgMethodCall =
    CgMethodCall(caller, MethodId(classId, method, nullType, listOf()), arguments.toList())

fun unsafeCgMethodCallArgsLiteral(caller: CgExpression?, classId: ClassId, method: String, vararg arguments: Any?): CgMethodCall =
    unsafeCgMethodCall(caller, classId, method, *arguments.map { CgLiteral(nullType, it) }.toTypedArray())
