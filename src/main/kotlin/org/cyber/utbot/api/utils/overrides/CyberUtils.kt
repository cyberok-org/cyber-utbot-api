package org.cyber.utbot.api.utils.overrides

import org.cyber.utbot.api.utils.annotations.CyberNew
import org.utbot.framework.plugin.api.UtExecutionResult
import org.utbot.framework.plugin.api.UtExplicitlyThrownException


@CyberNew("convenience")
fun isVulnerability(result: UtExecutionResult) = result is UtExplicitlyThrownException && result.exception is org.cyber.utils.VulnerabilityException
