package org.cyber.utbot.api.utils.overrides.fuzzing

import org.utbot.framework.plugin.api.util.id
import org.utbot.framework.plugin.api.util.stringClassId
import org.utbot.fuzzer.FuzzedContext
import org.utbot.fuzzer.FuzzedType
import org.utbot.fuzzing.FuzzedDescription
import org.utbot.fuzzing.providers.PrimitiveValueProvider
import org.utbot.fuzzing.seeds.RegexValue
import org.utbot.fuzzing.seeds.StringValue
import java.util.regex.Pattern
import java.util.regex.PatternSyntaxException
import kotlin.random.Random


object CyberStringValueProvider : PrimitiveValueProvider(stringClassId) {
    override fun generate(
        description: FuzzedDescription,
        type: FuzzedType
    ) = sequence {
        val constants = description.constants
            .filter { it.classId == stringClassId }
        val values = //constants
//            .mapNotNull { it.value as? String } +
            sequenceOf("some_file", "etc/passwd") // TODO(add constants here)
        values.forEach { yieldKnown(StringValue(it)) { value } }
        constants
            .filter { it.fuzzedContext.isPatterMatchingContext()  }
            .map { it.value as String }
            .distinct()
            .filter { it.isNotBlank() }
            .filter {
                try {
                    Pattern.compile(it); true
                } catch (_: PatternSyntaxException) {
                    false
                }
            }.forEach {
                yieldKnown(RegexValue(it, Random(0))) { value }
            }
    }

    private fun FuzzedContext.isPatterMatchingContext(): Boolean {
        if (this !is FuzzedContext.Call) return false
        val stringMethodWithRegexArguments = setOf("matches", "split")
        return when {
            method.classId == Pattern::class.java.id -> true
            method.classId == String::class.java.id && stringMethodWithRegexArguments.contains(method.name) -> true
            else -> false
        }
    }
}