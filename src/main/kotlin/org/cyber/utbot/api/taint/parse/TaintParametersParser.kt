package org.cyber.utbot.api.taint.parse

import com.google.gson.Gson
import org.cyber.utbot.api.taint.parse.wrappers.SinkWrapper
import org.cyber.utbot.api.taint.parse.wrappers.SourceWrapper
import proguard.analysis.cpa.domain.taint.TaintSource
import proguard.analysis.cpa.jvm.domain.taint.JvmInvokeTaintSink
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintSink
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintSource
import proguard.classfile.MethodSignature
import java.io.File

private val gson = Gson()

internal fun parseSources(directory: String): MutableSet<JvmTaintSource> {
    val sources = mutableSetOf<JvmTaintSource>()
    File(directory).walk().forEach { file ->
        if (file.isDirectory) return@forEach
        val source = gson.fromJson(file.readText(), SourceWrapper::class.java)
        source.apply {
            sources.add(
                JvmTaintSource(
                    MethodSignature(
                        containingClass,
                        methodName,
                        descriptor
                    ),
                    taintsThis, taintsReturn, taintsArgs, taintsGlobals
                ),
            )
        }
    }
    return sources
}

internal fun parseSinks(directory: String): MutableSet<JvmInvokeTaintSink> {
    val sinks = mutableSetOf<JvmInvokeTaintSink>()
    File(directory).walk().forEach { file ->
        if (file.isDirectory) return@forEach
        val sink = gson.fromJson(file.readText(), SinkWrapper::class.java)
        sink.apply {
            sinks.add(
                JvmInvokeTaintSink(
                    MethodSignature(containingClass, methodName, descriptor),
                    takesInstance, takesArgs, takesGlobals
                ),
            )
        }
    }
    return sinks
}
