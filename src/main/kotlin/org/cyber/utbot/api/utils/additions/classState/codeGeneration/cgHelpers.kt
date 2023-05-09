package org.cyber.utbot.api.utils.additions.classState.codeGeneration

import org.cyber.utbot.api.exceptions.CyberException
import org.utbot.framework.codegen.domain.models.*
import org.utbot.framework.codegen.util.nullLiteral
import org.utbot.framework.plugin.api.*
import org.utbot.framework.plugin.api.util.id
import org.utbot.framework.plugin.api.util.objectClassId

private val nullType = nullLiteral().type

fun cgMethodCallFromVariable(variable: CgVariable, method: String, vararg arguments: Any?, returnType: ClassId = nullType): CgMethodCall {
    val argumentsTypes = mutableListOf<ClassId>()
    val argumentsLiterals = mutableListOf<CgLiteral>()
    arguments.forEach {
        val classId = it?.run { this::class.java.id } ?: objectClassId  // TODO is ok?
        argumentsTypes.add(classId)
        argumentsLiterals.add(CgLiteral(classId, it))
    }
    return CgMethodCall(variable, MethodId(variable.type, method, returnType, argumentsTypes), argumentsLiterals)
}

fun CgStatement.toVariable(): CgVariable = when(this) { // TODO implement for other types
    is CgVariable -> this
    is CgTypeCast -> expression.toVariable()
    is CgDeclaration -> variable
    else -> throw CyberException("toVariable for $this not implemented")
}

fun UtModel.asString() = when (this) {
    is UtNullModel -> null
    is UtAssembleModel -> ((instantiationCall).params[0] as UtPrimitiveModel).value as String
    else -> throw CyberException("UtModel.asString: wrong model")
}

fun UtModel.asPrimitive() = when (this) {
    is UtPrimitiveModel -> this.value
    else -> throw CyberException("UtModel.asPrimitive: wrong model")
}

fun mockitoWhenThenReturn(cgMethodCall: CgMethodCall, returnValue: Any?): CgStatementExecutableCall {
    val givenCall = unsafeCgMethodCall(null, org.mockito.Mockito::class.id, "when", cgMethodCall)
    val willReturnCall = unsafeCgMethodCallArgsLiteral(givenCall, org.mockito.stubbing.OngoingStubbing::class.id, "thenReturn", returnValue)
    return CgStatementExecutableCall(willReturnCall)
}

// wrong types

fun unsafeCgMethodCall(caller: CgExpression?, classId: ClassId, method: String, vararg arguments: CgExpression): CgMethodCall =
    CgMethodCall(caller, MethodId(classId, method, nullType, listOf()), arguments.toList())

fun unsafeCgMethodCallArgsLiteral(caller: CgExpression?, classId: ClassId, method: String, vararg arguments: Any?): CgMethodCall =
    unsafeCgMethodCall(caller, classId, method, *arguments.map { CgLiteral(nullType, it) }.toTypedArray())
