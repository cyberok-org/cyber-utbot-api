package org.cyber.utbot.api.utils

import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken

typealias TargetQualifiedName = String
typealias SourceCodeFileName = String
typealias OutputFileName = String
typealias GeneratedTests = String
typealias JSON = String
typealias FullyQualifiedName = String?
typealias FunctionName = String
typealias FunctionId = Pair<FullyQualifiedName, FunctionName>


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

fun printJson(json: JSON) {
    val type = object : TypeToken<Map<String, Any?>?>() {}.type
    val gson = GsonBuilder().setPrettyPrinting().create()
    val res: Map<String, Any> = gson.fromJson(json, type)
    println(gson.toJson(res))
}
