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
            var result: String = value
            val payloadsConfig = getPayloadsList(configuration) ?: return@Mutation StringValue(result)
            val command = getCommand(value, random, payloadsConfig.commands)
            result = command
            result = addArguments(result, random, payloadsConfig.args, command)
            result = addPrefix(result, random, payloadsConfig.prefixes)
            result = addSuffix(result, random, payloadsConfig.suffixes)
            result = mutateCommand(result, random, command)
            StringValue(result)
        })
    }

    private fun getPayloadsList(configuration: Configuration): PayloadsConfig? {
        fun getPayload(dir: String) = PayloadsConfig(
            gson.fromJson(
                File("$dir/commands.json").readText(),
                PayloadWrapper::class.java
            ).parameters,
            gson.fromJson(
                File("$dir/arguments.json").readText(),
                PayloadWrapper::class.java
            ).parameters,
            gson.fromJson(
                File("$dir/prefixes.json").readText(),
                PayloadWrapper::class.java
            ).parameters,
            gson.fromJson(
                File("$dir/suffixes.json").readText(),
                PayloadWrapper::class.java
            ).parameters
        )
        return when (Configuration.vulnerabilityType) {
            "UNIX OS command injection" -> getPayload("$fuzzingBaseDir/cmdi/unix")

            "Windows OS command injection" -> getPayload("$fuzzingBaseDir/cmdi/windows")

            "arbitrary file creation" -> getPayload("$fuzzingBaseDir/afc")

            "arbitrary file modification" -> getPayload("$fuzzingBaseDir/afm")

            "arbitrary file reading" -> getPayload("$fuzzingBaseDir/afm")

            else -> null
        }
    }

    private fun getCommand(value: String, random: Random, commands: List<String>): String {
        return buildString {
            append(commands[Random.nextInt(commands.size)])
            append(value)
        }
    }

    private fun addArguments(value: String, random: Random, args: List<String>, command: String): String {
        return buildString {
            append(value)
            append(args[Random.nextInt(args.size)]) // make command dependent
        }
    }

    private fun addPrefix(value: String, random: Random, prefixes: List<String>): String {
        return buildString {
            append(value)
            if (random.flipCoin(50)) {
                append(prefixes[Random.nextInt(prefixes.size)])
                if (random.flipCoin(50)) {
                    append(prefixes[Random.nextInt(prefixes.size)])
                }
            }
        }
    }

    private fun addSuffix(value: String, random: Random, suffixes: List<String>): String {
        return buildString {
            append(value)
            if (random.flipCoin(50)) {
                append(suffixes[Random.nextInt(suffixes.size)])
                if (random.flipCoin(50)) {
                    append(suffixes[Random.nextInt(suffixes.size)])
                }
            }
        }
    }

    private fun mutateCommand(value: String, random: Random, command: String): String {
        return buildString {
            append(value)
        }
    }

    @Serializable // todo fix warning
    inner class PayloadWrapper(
        val description: String,
        val parameters: List<String>,
    )

    inner class PayloadsConfig(
        val commands: List<String>,
        val args: List<String>,
        val prefixes: List<String>,
        val suffixes: List<String>,
    )

}