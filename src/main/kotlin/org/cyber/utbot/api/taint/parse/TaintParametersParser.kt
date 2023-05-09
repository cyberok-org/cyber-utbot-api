package org.cyber.utbot.api.taint.parse

import com.google.gson.Gson
import org.cyber.utbot.api.taint.parse.wrappers.SinkWrapper
import org.cyber.utbot.api.taint.parse.wrappers.SourceWrapper
import proguard.analysis.cpa.jvm.domain.taint.JvmInvokeTaintSink
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintSource
import proguard.classfile.MethodSignature
import java.io.File

private val gson = Gson()

internal fun parseSources(directory: String): MutableSet<JvmTaintSource> {
    val sources = mutableSetOf<JvmTaintSource>()
    File(directory).walk().forEach { file ->
        if (file.isDirectory
            && file.path.toString().replace("/", "").replace("\\", "")
            != directory.replace("/", "").replace("\\", "")
        ) {
            sources.addAll(parseSources(file.path.toString()))
            return@forEach
        } else if (file.isDirectory) return@forEach
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
        if (file.isDirectory
            && file.path.toString().replace("/", "").replace("\\", "")
            != directory.replace("/", "").replace("\\", "")
        ) {
            sinks.addAll(parseSinks(file.path.toString()))
            return@forEach
        } else if (file.isDirectory) return@forEach
        val sink = gson.fromJson(file.readText(), SinkWrapper::class.java)
        sink.apply {
            if (takesArgs.size <= 1) {
                sinks.add(
                    JvmInvokeTaintSink(
                        MethodSignature(
                            containingClass,
                            signature.substringBefore("("),
                            "(" + signature.substringAfter("(")
                        ),
                        takesInstance, takesArgs, takesGlobals
                    ),
                )
            } else {
                takesArgs.forEach {
                    sinks.add(
                        JvmInvokeTaintSink(
                            MethodSignature(
                                containingClass,
                                signature.substringBefore("("),
                                "(" + signature.substringAfter("(")
                            ),
                            takesInstance, setOf(it), takesGlobals
                        ),
                    )
                }
            }
        }
    }
    return sinks
}
