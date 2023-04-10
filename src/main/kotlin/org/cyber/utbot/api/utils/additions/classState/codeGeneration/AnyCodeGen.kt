package org.cyber.utbot.api.utils.additions.classState.codeGeneration

import org.cyber.utbot.api.utils.additions.classState.AnyState
import org.utbot.framework.codegen.domain.models.CgDeclaration
import org.utbot.framework.codegen.domain.models.CgStatement
import org.utbot.framework.codegen.domain.models.CgValue
import org.utbot.framework.plugin.api.ClassId
import org.utbot.framework.plugin.api.UtModel

abstract class AnyCodeGen<out T: AnyState<UtModel>>(protected val state: T) {
    abstract val classId: ClassId

    abstract fun generate(declaration: CgDeclaration, getOrCreateVariable: (model: UtModel, name: String?) -> CgValue): List<CgStatement>
}
