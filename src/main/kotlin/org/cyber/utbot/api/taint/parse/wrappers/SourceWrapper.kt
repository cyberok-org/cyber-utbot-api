package org.cyber.utbot.api.taint.parse.wrappers

import kotlinx.serialization.Serializable

@Serializable
class SourceWrapper(
    val containingClass: String,
    val methodName: String,
    val descriptor: String,
    val description: String?,
    val taintsThis: Boolean,
    val taintsReturn: Boolean,
    val taintsArgs: Set<Int>,
    val taintsGlobals: Set<String>
)
