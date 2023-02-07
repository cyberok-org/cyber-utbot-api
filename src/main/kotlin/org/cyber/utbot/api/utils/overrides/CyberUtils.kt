package org.cyber.utbot.api.utils.overrides

import org.cyber.utbot.api.abstraction.Vulnerability
import org.cyber.utbot.api.utils.annotations.CyberNew
import org.utbot.engine.MethodResult
import org.utbot.engine.pc.UtBoolExpression
import org.utbot.engine.pc.mkNot
import org.utbot.engine.pc.mkOr
import org.utbot.engine.symbolic.asHardConstraint
import org.utbot.framework.plugin.api.UtExecutionResult
import org.utbot.framework.plugin.api.UtExplicitlyThrownException


@CyberNew("convenience")
fun MethodResult.update(updates: Set<UtBoolExpression>) = MethodResult(symbolicResult, symbolicStateUpdate + updates.asHardConstraint())


@CyberNew("convenience")
fun List<Vulnerability>?.correctPathConstraints(): Set<UtBoolExpression> =
    this?.map { it.constraints }?.flatten()?.let { setOf(mkNot(mkOr(it))) } ?: emptySet()


@CyberNew("convenience")
fun isVulnerability(result: UtExecutionResult) = result is UtExplicitlyThrownException && result.exception is org.cyber.utils.VulnerabilityException
