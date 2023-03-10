package org.cyber.utbot.api.abstraction

import org.cyber.utbot.api.utils.SourceCodeFileName
import org.cyber.utbot.api.utils.TargetQualifiedName

data class BenchInfo(val target: TargetQualifiedName, val source: SourceCodeFileName) {
    constructor() : this("", "")    // for jackson
}
