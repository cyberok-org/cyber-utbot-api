package org.cyber.utbot.api.abstraction

import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.dataformat.csv.CsvSchema

data class ReportItem(
    @field:JsonProperty("target") val target: String,
    @field:JsonProperty("success") val success: Boolean,
    @field:JsonProperty("category") val category: String? = null,
    @field:JsonProperty("testsRaw") val testsRaw: String? = null,
    @field:JsonProperty("testsInfo") val testsInfo: String? = null,
    @field:JsonProperty("failReason") val failReason: String? = null,
    @field:JsonProperty("comment") val comment: String? = null
) {
    companion object {
        val schema: CsvSchema = CsvSchema.builder()
            .addColumn("target")
            .addColumn("success")
            .addColumn("category")
            .addColumn("testsRaw")
            .addColumn("testsInfo")
            .addColumn("failReason")
            .addColumn("comment")
            .build()
    }
}
