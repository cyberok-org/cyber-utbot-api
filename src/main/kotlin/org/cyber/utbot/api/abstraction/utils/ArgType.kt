package org.cyber.utbot.api.abstraction.utils

import org.cyber.utbot.api.exceptions.CyberException
import org.utbot.engine.types.STRING_TYPE
import soot.*
import soot.Type
import soot.jimple.*


enum class ArgType {
    BYTE,
    SHORT,
    CHAR,
    INT,
    LONG,
    FLOAT,
    DOUBLE,
    BOOLEAN,
    STRING;

    companion object {
        fun fromString(type: String) = when(type) {
            "byte"      -> BYTE
            "short"     -> SHORT
            "char"      -> CHAR
            "int"       -> INT
            "long"      -> LONG
            "float"     -> FLOAT
            "double"    -> DOUBLE
            "boolean"   -> BOOLEAN
            "string"    -> STRING
            else        -> throw CyberException("wrong type: $type")
        }
    }

    val type: Type
        get() = when(this) {
            BYTE -> ByteType.v()
            SHORT -> ShortType.v()
            CHAR -> CharType.v()
            INT -> IntType.v()
            LONG -> LongType.v()
            FLOAT -> FloatType.v()
            DOUBLE -> DoubleType.v()
            BOOLEAN -> BooleanType.v()
            STRING -> STRING_TYPE
        }

    fun const(value: Any?): Constant = value?.let { // TODO (is it ok???)
        when(this) {
            BYTE -> IntConstant.v(((it as Double).toInt().toByte()).toInt())
            SHORT -> IntConstant.v(((it as Double).toInt().toShort()).toInt())
            CHAR -> IntConstant.v(((it as Double).toInt().toChar()).code)
            INT -> IntConstant.v((it as Double).toInt())
            LONG -> LongConstant.v((it as Double).toLong())
            FLOAT -> FloatConstant.v((it as Double).toFloat())
            DOUBLE -> DoubleConstant.v(it as Double)
            BOOLEAN -> IntConstant.v(if (it as Boolean) 1 else 0)
            STRING -> StringConstant.v(it as String)
        }
    } ?: NullConstant.v()
}
