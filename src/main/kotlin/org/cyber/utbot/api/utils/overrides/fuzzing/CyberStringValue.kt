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
    private var tainted = false

    fun mutations(tainted: Boolean = false): List<Mutation<KnownValue>> {
        this.tainted = tainted
        return mutations()
    }

    override fun mutations(): List<Mutation<KnownValue>> {
        return listOf(Mutation { source, random, configuration ->
//            require(source == this)
            if (tainted) {
                var result = ""
                val payloadsConfig = getPayloadsList(configuration) ?: return@Mutation StringValue(result)
                val command = getCommand(value, random, payloadsConfig.commands)
                result = command
                if (Configuration.vulnerabilityType.contains("injection")) result =
                    addArguments(result, random, payloadsConfig.args, command)
                result = addPrefix(result, random, payloadsConfig.prefixes)
                if (result.length < 18) result = addSuffix(result, random, payloadsConfig.suffixes)
                result = mutateCommand(result, random, command)
//                println("result $result")
                StringValue(result)
            } else {
                val position = random.nextInt(value.length + 1)
                var result: String = value
                if (random.flipCoin(configuration.probStringRemoveCharacter)) {
                    result = tryRemoveChar(random, result, position) ?: value
                }
                if (result.length < configuration.maxStringLengthWhenMutated && random.flipCoin(configuration.probStringAddCharacter)) {
                    result = tryAddChar(random, result, position)
                }
                StringValue(result)
            }
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
            val command = commands[Random.nextInt(commands.size)]
            append(if (!value.contains(command)) command else value)
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
            if (random.flipCoin(50) && value.length < 16) {
                val pref = prefixes[Random.nextInt(prefixes.size)]
                append(pref)
                if (random.flipCoin(50) && value.length + pref.length < 18) {
                    append(prefixes[Random.nextInt(prefixes.size)])
                }
            }
            append(value)
        }
    }

    private fun addSuffix(value: String, random: Random, suffixes: List<String>): String {
        return buildString {
            append(value)
            if (random.flipCoin(50) && value.length < 16) {
                val suff = suffixes[Random.nextInt(suffixes.size)]
                append(suff)
                if (random.flipCoin(50) && value.length + suff.length < 18) {
                    append(suffixes[Random.nextInt(suffixes.size)])
                }
            }
        }
    }

    private fun mutateCommand(value: String, random: Random, command: String): String { // TODO(mutate)
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

    private fun tryAddChar(random: Random, value: String, position: Int): String {
        val charToMutate = if (value.isNotEmpty()) {
            value.random(random)
        } else {
            // use any meaningful character from the ascii table
            random.nextInt(33, 127).toChar()
        }
        return buildString {
            append(value.substring(0, position))
            // try to change char to some that is close enough to origin char
            val charTableSpread = 64
            if (random.nextBoolean()) {
                append(charToMutate - random.nextInt(1, charTableSpread))
            } else {
                append(charToMutate + random.nextInt(1, charTableSpread))
            }
            append(value.substring(position, value.length))
        }
    }

    private fun tryRemoveChar(random: Random, value: String, position: Int): String? {
        if (position >= value.length) return null
        val toRemove = random.nextInt(value.length)
        return buildString {
            append(value.substring(0, toRemove))
            append(value.substring(toRemove + 1, value.length))
        }
    }

}