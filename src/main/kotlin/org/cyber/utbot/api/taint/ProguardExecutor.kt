package org.cyber.utbot.api.taint

import org.cyber.utbot.api.taint.parse.parseSinks
import org.cyber.utbot.api.taint.parse.parseSources
import org.cyber.utbot.api.utils.UTBOT_DIR
import proguard.analysis.cpa.jvm.domain.memory.BamLocationDependentJvmMemoryLocation
import proguard.analysis.cpa.jvm.domain.taint.JvmInvokeTaintSink
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintMemoryLocationBamCpaRun
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintSource
import proguard.analysis.cpa.jvm.util.CfaUtil
import proguard.classfile.ClassPool
import proguard.classfile.MethodSignature
import soot.SootMethod
import soot.jimple.JimpleBody

class ProguardExecutor(private val jarName: String) {
    var traces: Set<List<BamLocationDependentJvmMemoryLocation<*>>> = mutableSetOf()
    val sinks: MutableSet<JvmInvokeTaintSink> = mutableSetOf()
    lateinit var cpaRun: JvmTaintMemoryLocationBamCpaRun
    private var headMethod: HeadMethod? = null
    private val sources: MutableSet<JvmTaintSource> = mutableSetOf()
    private val classPool: javassist.ClassPool = javassist.ClassPool.getDefault()

    init {
        classPool.insertClassPath(jarName)
    }

    fun execute(jimpleBody: JimpleBody) {
        setHeadMethodSignature(jimpleBody.method)
        val programClassPool: ClassPool = JarUtil.readJar(jarName, "**", false)
        val cfa = CfaUtil.createInterproceduralCfa(programClassPool)
        sources.addAll(parseSources("$UTBOT_DIR/cyber-utbot-exploit-base/src/taint/base/sources"))
        sinks.addAll(parseSinks("$UTBOT_DIR/cyber-utbot-exploit-base/src/taint/base/sinks"))
        cpaRun = JvmTaintMemoryLocationBamCpaRun.Builder().setCfa(cfa)
            .setMainSignature(MethodSignature(headMethod?.clazz, headMethod?.method, headMethod?.descriptor))
            .setTaintSources(sources)
            .setTaintSinks(sinks)
            .setMaxCallStackDepth(-1)
            .build()
        traces = cpaRun.extractLinearTraces()
        traces.forEach { t ->
            println("\nNEW\n")
            t.forEach { println(it.toString()) }
        }
    }

    private fun setHeadMethodSignature(method: SootMethod) {
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
