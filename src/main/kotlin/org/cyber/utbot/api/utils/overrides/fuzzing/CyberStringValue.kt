package org.cyber.utbot.api.utils.overrides.fuzzing

import com.google.gson.Gson
import kotlinx.serialization.Serializable
import org.cyber.utbot.api.utils.UTBOT_DIR
import org.utbot.fuzzing.Configuration
import org.utbot.fuzzing.Mutation
import org.utbot.fuzzing.seeds.KnownValue
import org.utbot.fuzzing.seeds.StringValue
import org.utbot.fuzzing.utils.flipCoin
import java.io.File
import kotlin.random.Random


class CyberStringValue(override val value: String) : StringValue(value) {
    private val fuzzingBaseDir = "$UTBOT_DIR/cyber-utbot-exploit-base/src/fuzzing/base"
    private val gson = Gson()

    override fun mutations(): List<Mutation<KnownValue>> {
        println("mutations")
        return listOf(Mutation { source, random, configuration ->
//            require(source == this)
            // we can miss some mutation for a purpose
            val position = random.nextInt(value.length + 1)
            var result: String = value
            val payloads = getPayloadsList(configuration)
            if (random.flipCoin(50)) {
                result = addSuffix(result, payloads)
            }
            if (random.flipCoin(50)) {
                result = addSuffix(result, payloads)
                if (random.flipCoin(50)) {
                    result = addSuffix(result, payloads)
                }
            }
            StringValue(result)
        })
    }

    private fun getPayloadsList(configuration: Configuration): List<String> {
        return when (Configuration.vulnerabilityType) {
            "UNIX OS command injection" -> {
                gson.fromJson(
                    File("$fuzzingBaseDir/cmdi/unix/cmdi.json").readText(),
                    PayloadWrapper::class.java
                ).parameters
            }

            "Windows OS command injection" -> {
                gson.fromJson(
                    File("$fuzzingBaseDir/cmdi/windows/cmdi.json").readText(),
                    PayloadWrapper::class.java
                ).parameters
            }

            "arbitrary file creation" -> {
                gson.fromJson(File("$fuzzingBaseDir/afc/afc.json").readText(), PayloadWrapper::class.java).parameters
            }

            "arbitrary file modification" -> {
                gson.fromJson(File("$fuzzingBaseDir/afm/afm.json").readText(), PayloadWrapper::class.java).parameters
            }

            else -> listOf()
        }
    }

    private fun addSuffix(value: String, payloads: List<String>): String {
        return buildString {
            append(value)
            append(payloads[Random.nextInt(payloads.size)])
        }
    }

    @Serializable // todo fix warning
    inner class PayloadWrapper(
        val description: String,
        val parameters: List<String>,
    )

}