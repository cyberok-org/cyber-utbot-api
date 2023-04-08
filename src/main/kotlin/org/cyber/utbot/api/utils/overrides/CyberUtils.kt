package org.cyber.utbot.api.utils.overrides

import kotlinx.collections.immutable.PersistentList
import org.cyber.utbot.api.utils.ASSERT_CLASS_NAME
import org.cyber.utbot.api.utils.ASSERT_FUNCTION_NAME
import org.cyber.utbot.api.utils.annotations.CyberNew
import soot.jimple.Stmt
import soot.jimple.internal.JInvokeStmt
import soot.jimple.internal.JStaticInvokeExpr


@CyberNew("convenience")
fun isVulnerability(path: PersistentList<Stmt>) = path.any { ((it as? JInvokeStmt)?.invokeExprBox?.value as? JStaticInvokeExpr)?.methodRef?.run {
    name == ASSERT_FUNCTION_NAME && declaringClass.name == ASSERT_CLASS_NAME
} ?: false }
