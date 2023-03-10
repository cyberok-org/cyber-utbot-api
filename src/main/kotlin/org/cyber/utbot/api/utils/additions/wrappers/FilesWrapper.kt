package org.cyber.utbot.api.utils.additions.wrappers

import org.utbot.engine.*
import soot.Scene
import soot.SootClass
import soot.SootMethod

private val cyberFilesClass: SootClass
    get() = Scene.v().getSootClass(java.nio.file.Files::class.qualifiedName)


object FilesWrapper {
    private val createFileMethod = cyberFilesClass.getMethodByName(java.nio.file.Files::createFile.name)
    private val createFileMethodSignature = createFileMethod.subSignature
    private val createDirectoriesMethod = cyberFilesClass.getMethodByName(java.nio.file.Files::createDirectories.name)
    private val createDirectoriesMethodSignature = createDirectoriesMethod.subSignature

    fun staticInvoke(method: SootMethod, parameters: List<SymbolicValue>): Pair<List<InvokeResult>?, Boolean> {
        return (when (method.subSignature) {
            createFileMethodSignature -> listOf(MethodResult(parameters[0]))
            createDirectoriesMethodSignature -> listOf(MethodResult(parameters[0]))
            else -> null
        }) to false
    }
}
