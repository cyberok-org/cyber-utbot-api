package org.cyber.utbot.api.utils

typealias TargetQualifiedName = String
typealias SourceCodeFileName = String
typealias OutputFileName = String
typealias GeneratedTests = String


class TestUnit(
    /**
     * Target class fully qualified name
     */
    val target: TargetQualifiedName,

    /**
     * Specifies source code file for a generated test
     */
    val source: SourceCodeFileName,

    /**
     * Specifies output file for a generated test if smth went wrong; generated from the class under test if not given
     */
    val output: OutputFileName? = null
)

fun Map<String, String>.toTestUnits(): List<TestUnit> = this.map { TestUnit(target=it.key, source=it.value) }
