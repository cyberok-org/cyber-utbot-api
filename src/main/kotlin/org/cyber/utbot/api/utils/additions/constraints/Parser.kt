package org.cyber.utbot.api.utils.additions.constraints

import org.utbot.engine.*
import org.utbot.engine.pc.*

data class Addr(val value: Number? = null, val name: String? = null) { // union type
    init {
        require(value == null || name == null)
        require(value != null || name != null)
    }

    override fun toString() = "Addr(${value?.let { "value=$it" } ?: "name=$name"})"
}

enum class Operation {
    LE,
    LT,
    GE,
    GT,
    EQ,
    NE
    ;

    fun reverse() = when(this) {    // x op y => y op_ret x
        LE -> GE
        LT -> GT
        GE -> LE
        GT -> LT
        EQ -> EQ
        NE -> NE
    }

    fun ne() = when(this) { // !op
        LE -> GT
        LT -> GE
        GE -> LT
        GT -> LE
        EQ -> NE
        NE -> EQ
    }

    override fun toString() = when(this) {
        LE -> "<="
        LT -> "<"
        GE -> ">="
        GT -> ">"
        EQ -> "=="
        NE -> "!="
    }

    companion object {
        fun fromUtBoolOperator(op: UtBoolOperator) = when(op) {
            Le -> LE
            Lt -> LT
            Ge -> GE
            Gt -> GT
            Eq -> EQ
            Ne -> NE
        }
    }
}

sealed class CyberExpression(val expr: UtExpression)
sealed class FinishCyberExpression(expr: UtExpression): CyberExpression(expr)

class Unknown(expr: UtExpression): CyberExpression(expr)
class CyberAddrExpression(expr: UtExpression, val addr: Addr): FinishCyberExpression(expr) {
    override fun toString(): String {
        return addr.toString()
    }
}
class CyberNumberExpression(expr: UtExpression, val value: Number): FinishCyberExpression(expr) {
    override fun toString(): String {
        return "Number($value)"
    }
}
class CyberNameExpression(expr: UtExpression, val name: String): FinishCyberExpression(expr) {
    override fun toString(): String {
        return "Name($name)"
    }
}
class CyberOpExpression(expr: UtExpression, val l: CyberExpression, val r: CyberExpression, val operation: Operation): CyberExpression(expr)

class CyberConstraint(val expr: FinishCyberExpression, val operation: Operation) { // (this by addr) op expr
    override fun toString(): String {
        return "$operation $expr"
    }
}

class CyberConstraintSet {
    private val addrToConstraints = mutableMapOf<Addr, MutableList<CyberConstraint>>()
    val result
        get() = addrToConstraints

    private fun registerOpExpression(l: CyberExpression, op: Operation, r: CyberExpression) {
        if (l is CyberAddrExpression && r is FinishCyberExpression) {
            addrToConstraints.getOrPut(l.addr) { mutableListOf() }.also { it.add(CyberConstraint(r, op)) }
        } else if (l is CyberNameExpression) {
            registerOpExpression(CyberAddrExpression(l.expr, addr = Addr(name = l.name)), op, r)
        }
    }

    fun register(expr: CyberExpression) {
        when(expr) {
            is CyberOpExpression -> {
                registerOpExpression(expr.l, expr.operation, expr.r)
                registerOpExpression(expr.r, expr.operation.reverse(), expr.l)
            }
            else -> {}
        }
    }
}

open class Parser: UtExpressionVisitor<CyberExpression> {
    override fun visit(expr: UtArraySelectExpression): CyberExpression {
        expr.index.accept(this)
        expr.arrayExpression.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtMkArrayExpression): CyberExpression {
        return Unknown(expr)
    }

    override fun visit(expr: UtArrayMultiStoreExpression): CyberExpression {
        expr.initial.accept(this)
        expr.stores.map {
            it.index.accept(this)
            it.value.accept(this)
        }
        return Unknown(expr)
    }

    override fun visit(expr: UtBvLiteral): CyberExpression {
        return CyberNumberExpression(expr, expr.value)
    }

    override fun visit(expr: UtBvConst): CyberExpression {
        return CyberNameExpression(expr, expr.name)
    }

    override fun visit(expr: UtAddrExpression): CyberExpression {
        return when(val internal = expr.internal.accept(this)) {
            is CyberNumberExpression -> CyberAddrExpression(expr, Addr(value = internal.value))
            is CyberNameExpression -> CyberAddrExpression(expr, Addr(name = internal.name))
            else -> Unknown(expr)
        }
    }

    override fun visit(expr: UtFpLiteral): CyberExpression {
        return Unknown(expr)
    }

    override fun visit(expr: UtFpConst): CyberExpression {
        return Unknown(expr)
    }

    override fun visit(expr: UtOpExpression): CyberExpression {
        expr.left.expr.accept(this)
        expr.right.expr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtTrue): CyberExpression {
        return Unknown(expr)
    }

    override fun visit(expr: UtFalse): CyberExpression {
        return Unknown(expr)
    }

    override fun visit(expr: UtEqExpression): CyberExpression {
        val l = expr.left.accept(this)
        val r = expr.right.accept(this)
        return CyberOpExpression(expr, l, r, Operation.EQ)
    }

    override fun visit(expr: UtBoolConst): CyberExpression {
        return Unknown(expr)
    }

    override fun visit(expr: NotBoolExpression): CyberExpression {
        expr.expr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtOrBoolExpression): CyberExpression {
        expr.exprs.map { it.accept(this) }
        return Unknown(expr)
    }

    override fun visit(expr: UtAndBoolExpression): CyberExpression {
        expr.exprs.map { it.accept(this) }
        return Unknown(expr)
    }

    override fun visit(expr: UtAddNoOverflowExpression): CyberExpression {
        return Unknown(expr)
    }

    override fun visit(expr: UtSubNoOverflowExpression): CyberExpression {
        return Unknown(expr)
    }

    override fun visit(expr: UtNegExpression): CyberExpression {
        expr.variable.expr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtCastExpression): CyberExpression {
        expr.variable.expr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtBoolOpExpression): CyberExpression {
        val l = expr.left.expr.accept(this)
        val r = expr.right.expr.accept(this)
        return CyberOpExpression(expr, l, r, Operation.fromUtBoolOperator(expr.operator))
    }

    override fun visit(expr: UtIsExpression): CyberExpression {
        expr.addr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtGenericExpression): CyberExpression {
        expr.addr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtIsGenericTypeExpression): CyberExpression {
        expr.addr.accept(this)
        expr.baseAddr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtEqGenericTypeParametersExpression): CyberExpression {
        expr.firstAddr.accept(this)
        expr.secondAddr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtInstanceOfExpression): CyberExpression {
        expr.constraint.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtIteExpression): CyberExpression {
        expr.condition.accept(this)
        expr.thenExpr.accept(this)
        expr.elseExpr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtMkTermArrayExpression): CyberExpression {
        return Unknown(expr)
    }

    override fun visit(expr: UtConstArrayExpression): CyberExpression {
        expr.constValue.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtArrayInsert): CyberExpression {
        expr.arrayExpression.accept(this)
        expr.index.expr.accept(this)
        expr.element.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtArrayInsertRange): CyberExpression {
        expr.arrayExpression.accept(this)
        expr.index.expr.accept(this)
        expr.elements.accept(this)
        expr.from.expr.accept(this)
        expr.length.expr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtArrayRemove): CyberExpression {
        expr.arrayExpression.accept(this)
        expr.index.expr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtArrayRemoveRange): CyberExpression {
        expr.arrayExpression.accept(this)
        expr.index.expr.accept(this)
        expr.length.expr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtArraySetRange): CyberExpression {
        expr.arrayExpression.accept(this)
        expr.index.expr.accept(this)
        expr.elements.accept(this)
        expr.from.expr.accept(this)
        expr.length.expr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtArrayShiftIndexes): CyberExpression {
        expr.arrayExpression.accept(this)
        expr.offset.expr.accept(this)
        return Unknown(expr)
    }

    override fun visit(expr: UtArrayApplyForAll): CyberExpression {
        expr.arrayExpression.accept(this)
        return Unknown(expr)
    }
}
