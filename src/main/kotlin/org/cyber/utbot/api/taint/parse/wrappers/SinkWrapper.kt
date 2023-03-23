package org.cyber.utbot.api.taint.parse.wrappers

import kotlinx.serialization.Serializable

@Serializable
class SinkWrapper(
    val containingClass: String,
    val methodName: String,
    val descriptor: String,
    val description: String?,
    val takesInstance: Boolean,
    val takesArgs: Set<Int>,
    val takesGlobals: Set<String>
)
