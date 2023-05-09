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

    fun const(value: Any?): Constant = value?.let {
        when(this) {
            BYTE -> when(it) {
                is Byte -> IntConstant.v(it.toInt())
                is Double -> IntConstant.v((it.toInt().toByte()).toInt())
                else -> throw CyberException("wrong type: expected byte, found ${it.typeString()}")
            }
            SHORT -> when(it) {
                is Short -> IntConstant.v(it.toInt())
                is Double -> IntConstant.v(it.toInt().toShort().toInt())
                else -> throw CyberException("wrong type: expected short, found ${it.typeString()}")
            }
            CHAR -> when(it) {
                is Char -> IntConstant.v(it.code)
                is Double -> IntConstant.v((it.toInt().toChar()).code)
                else -> throw CyberException("wrong type: expected char, found ${it.typeString()}")
            }
            INT -> when(it) {
                is Int -> IntConstant.v(it)
                is Double -> IntConstant.v(it.toInt())
                else -> throw CyberException("wrong type: expected int, found ${it.typeString()}")
            }
            LONG -> when(it) {
                is Long -> LongConstant.v(it)
                is Double -> LongConstant.v(it.toLong())
                else -> throw CyberException("wrong type: expected long, found ${it.typeString()}")
            }
            FLOAT -> when(it) {
                is Float -> FloatConstant.v(it)
                is Double -> FloatConstant.v(it.toFloat())
                else -> throw CyberException("wrong type: expected float, found ${it.typeString()}")
            }
            DOUBLE -> when(it) {
                is Double -> DoubleConstant.v(it)
                else -> throw CyberException("wrong type: expected double, found ${it.typeString()}")
            }
            BOOLEAN -> when(it) {
                is Boolean -> IntConstant.v(if (it) 1 else 0)
                else -> throw CyberException("wrong type: expected boolean, found ${it.typeString()}")
            }
            STRING -> when(it) {
                is String -> StringConstant.v(it)
                else -> throw CyberException("wrong type: expected string, found ${it.typeString()}")
            }
        }
    } ?: NullConstant.v()
}

private fun Any.typeString() = this::class.java.typeName.toString()
