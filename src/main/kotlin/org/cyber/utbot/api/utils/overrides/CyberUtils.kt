package org.cyber.utbot.api.utils.overrides

import org.cyber.utbot.api.utils.annotations.CyberNew
import org.utbot.engine.MethodResult
import org.utbot.engine.symbolic.SymbolicStateUpdate

@CyberNew("convenience")
operator fun SymbolicStateUpdate.plus(update: SymbolicStateUpdate?) = update?.let { this + update } ?: this


@CyberNew("convenience")
fun MethodResult.update(update: SymbolicStateUpdate?) = MethodResult(symbolicResult, symbolicStateUpdate + update)
