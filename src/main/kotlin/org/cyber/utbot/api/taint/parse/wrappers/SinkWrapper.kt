package org.cyber.utbot.api.taint.parse.wrappers

import kotlinx.serialization.Serializable

@Serializable
class SinkWrapper(
    val containingClass: String,
    val signature: String,
    val takesInstance: Boolean,
    val takesArgs: Set<Int>,
    val takesGlobals: Set<String>
)
