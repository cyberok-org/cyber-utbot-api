package org.cyber.utbot.api.taint

import org.cyber.utbot.api.taint.parse.parseSinks
import org.cyber.utbot.api.taint.parse.parseSources
import proguard.analysis.cpa.domain.taint.TaintAbstractState
import proguard.analysis.cpa.domain.taint.TaintSource
import proguard.analysis.cpa.jvm.domain.memory.BamLocationDependentJvmMemoryLocation
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintMemoryLocationBamCpaRun
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintSink
import proguard.analysis.cpa.jvm.util.CfaUtil
import proguard.classfile.ClassPool
import proguard.classfile.MethodSignature
import soot.SootMethod

class ProguardExecutor(private val jarName: String) {
    var traces: Set<List<BamLocationDependentJvmMemoryLocation<*>>> = mutableSetOf()
    private var headMethod: HeadMethod? = null
    val sources: MutableSet<TaintSource> = mutableSetOf()
    val sinks: MutableSet<JvmTaintSink> = mutableSetOf()

    fun execute() {
        val programClassPool: ClassPool = JarUtil.readJar(jarName, "**", false)
        val cfa = CfaUtil.createInterproceduralCfaFromClassPool(programClassPool)
        sources.addAll(parseSources("C:\\Users\\lesya\\uni2\\UTBotJava\\cyber-utbot-api\\src\\main\\resources\\org\\cyber\\utbot\\api\\taint\\sources"))
        sinks.addAll(parseSinks("C:\\Users\\lesya\\uni2\\UTBotJava\\cyber-utbot-api\\src\\main\\resources\\org\\cyber\\utbot\\api\\taint\\sinks"))
        val cpaRun = JvmTaintMemoryLocationBamCpaRun.Builder().setCfa(cfa)
            // todo: replace with the method under analysis
            .setMainSignature(MethodSignature(headMethod?.clazz, headMethod?.method, headMethod?.descriptor))
            .setTaintSources(sources)
            .setTaintSinks(sinks)
            .setMaxCallStackDepth(-1)
            .setThreshold(TaintAbstractState.bottom)
            .build()
        traces = cpaRun.extractLinearTraces()
        traces.forEach { t ->
            println("\nNEW\n")
            t.forEach { println(it.toString()) }
        }
    }

    fun setHeadMethodSignature(method: SootMethod) {
        val name = method.name
        val sig = method.bytecodeSignature.split(name)
        val clazz = sig[0]
            .substringBeforeLast(":")
            .replace("<", "")
            .replace(".", "/")
        val descriptor = sig[1].replace(">", "")
        headMethod = HeadMethod(clazz, name, descriptor)
    }

    data class HeadMethod(val clazz: String, val method: String, val descriptor: String)
}
