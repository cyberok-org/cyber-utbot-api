package org.cyber.utbot.api.utils

import com.fasterxml.jackson.dataformat.csv.CsvMapper
import com.fasterxml.jackson.dataformat.csv.CsvParser
import com.fasterxml.jackson.dataformat.csv.CsvSchema
import com.google.gson.GsonBuilder
import com.google.gson.reflect.TypeToken
import java.io.FileReader
import java.io.FileWriter

typealias TargetQualifiedName = String
typealias SourceCodeFileName = String
typealias OutputFileName = String
typealias GeneratedTests = String
typealias JSON = String
typealias FullyQualifiedName = String
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

fun JSON.pretty(): String {
    val type = object : TypeToken<Map<String, Any?>?>() {}.type
    val gson = GsonBuilder().setPrettyPrinting().create()
    val res: Map<String, Any> = gson.fromJson(this, type)
    return gson.toJson(res)
}

fun printJson(json: JSON) = println(json.pretty())

val csvMapper = CsvMapper().apply {
    enable(CsvParser.Feature.TRIM_SPACES)
    enable(CsvParser.Feature.SKIP_EMPTY_LINES)
}

inline fun <reified T> writeCsvFile(data: Collection<T>, schema: CsvSchema, fileName: String) {
    FileWriter(fileName).use { writer ->
        csvMapper.writer(schema.withHeader())
            .writeValues(writer)
            .writeAll(data)
            .close()
    }
}

inline fun <reified T> readCsvFile(fileName: String): List<T> { // schema: CsvSchema,
    FileReader(fileName).use { reader ->
        return csvMapper
            .readerFor(T::class.java)
            .with(CsvSchema.emptySchema().withHeader())
            .readValues<T>(reader)
            .readAll()
            .toList()
    }
}
