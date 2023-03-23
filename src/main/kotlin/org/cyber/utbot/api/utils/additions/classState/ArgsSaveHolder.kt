package org.cyber.utbot.api.utils.additions.classState

import soot.*

abstract class ArgsSaveHolder {
    protected abstract val saveArgsSignatures: Set<String>

    fun saveArgs(method: SootMethod): Boolean = saveArgsSignatures.contains(method.subSignature)
}
