package org.cyber.utbot.api.taint

import org.cyber.utbot.api.taint.util.extract
import proguard.analysis.cpa.domain.taint.TaintAbstractState
import proguard.analysis.cpa.domain.taint.TaintSource
import proguard.analysis.cpa.jvm.domain.memory.BamLocationDependentJvmMemoryLocation
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintMemoryLocationBamCpaRun
import proguard.analysis.cpa.jvm.domain.taint.JvmTaintSink
import proguard.analysis.cpa.jvm.util.CfaUtil
import proguard.classfile.ClassPool
import proguard.classfile.MethodSignature

class ProguardExecutor(private val jarName: String) {
    var traces: Set<List<BamLocationDependentJvmMemoryLocation<*>>> = mutableSetOf()

    fun execute() {
        val programClassPool: ClassPool = JarUtil.readJar(jarName, "**", false)
        val cfa = CfaUtil.createInterproceduralCfaFromClassPool(programClassPool)
        val sources: MutableSet<TaintSource> = mutableSetOf()
        val sinks: MutableSet<JvmTaintSink> = mutableSetOf()
        extract("sources.txt", this.javaClass)
            .lines()
            .forEach {
                sources.add(
                    TaintSource(
                        it,
                        false,
                        true, // todo: use ~json files, put these values there
                        setOf(),
                        setOf()
                    )
                )
            }
        extract("sinks.txt", this.javaClass)
            .lines()
            .forEach {
                sinks.add(
                    JvmTaintSink(
                        it,
                        false,
                        setOf(1),
                        setOf()
                    )
                )
            }
        val cpaRun = JvmTaintMemoryLocationBamCpaRun.Builder().setCfa(cfa)
            // todo: replace with the method under analysis
            .setMainSignature(MethodSignature("org/testcases/temp/Temp", "main", "([Ljava/lang/String;)V"))
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
}
