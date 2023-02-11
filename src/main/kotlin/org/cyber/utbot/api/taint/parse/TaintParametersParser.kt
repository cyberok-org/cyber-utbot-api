package org.cyber.utbot.api.taint.parse

import com.google.gson.Gson
import org.cyber.utbot.api.taint.parse.wrappers.SinkWrapper
import org.cyber.utbot.api.taint.parse.wrappers.SourceWrapper
import proguard.analysis.cpa.domain.taint.TaintSource
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintSink
import java.io.File

private val gson = Gson()

internal fun parseSources(directory: String): MutableSet<TaintSource> {
    val sources = mutableSetOf<TaintSource>()
    File(directory).walk().forEach { file ->
        if (file.isDirectory) return@forEach
        val source = gson.fromJson(file.readText(), SourceWrapper::class.java)
        source.apply {
            sources.add(
                TaintSource(
                    containingClass + signature,
                    taintsThis,
                    taintsReturn,
                    taintsArgs,
                    taintsGlobals
                )
            )
        }
    }
    return sources
}

internal fun parseSinks(directory: String): MutableSet<JvmTaintSink> {
    val sinks = mutableSetOf<JvmTaintSink>()
    File(directory).walk().forEach { file ->
        if (file.isDirectory) return@forEach
        val sink = gson.fromJson(file.readText(), SinkWrapper::class.java)
        sink.apply {
            sinks.add(
                JvmTaintSink(
                    containingClass + signature,
                    takesInstance,
                    takesArgs,
                    takesGlobals
                )
            )
        }
    }
    return sinks
}
