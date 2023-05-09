package org.cyber.utbot.api.utils.overrides.fuzzing

import cyberFuzz
import org.utbot.fuzzing.*
import kotlin.random.Random

suspend fun <TYPE, RESULT, DESCRIPTION : Description<TYPE>, FEEDBACK : Feedback<TYPE, RESULT>> cyberRunFuzzing(
    provider: ValueProvider<TYPE, RESULT, DESCRIPTION>,
    description: DESCRIPTION,
    random: Random = Random(0),
    configuration: Configuration = Configuration(),
    handle: suspend (description: DESCRIPTION, values: List<RESULT>) -> FEEDBACK
) {
    CyberBaseFuzzing(listOf(provider), handle).cyberFuzz(description, random, configuration)
}


/**
 * Implements base concepts that use providers to generate values for some types.
 *
 * @param providers is a list of "type to values" generator
 * @param exec this function is called when fuzzer generates values of type R to run it with target program.
 */
open class CyberBaseFuzzing<T, R, D : Description<T>, F : Feedback<T, R>>(
    open val providers: List<ValueProvider<T, R, D>>,
    open val exec: suspend (description: D, values: List<R>) -> F
) : Fuzzing<T, R, D, F> {

    var update: suspend (D, Statistic<T, R>, Configuration) -> Unit = { d, s, c -> super.update(d, s, c) }

    constructor(vararg providers: ValueProvider<T, R, D>, exec: suspend (description: D, values: List<R>) -> F) : this(providers.toList(), exec)

    override fun generate(description: D, type: T): Sequence<Seed<T, R>> {
        return providers.asSequence().flatMap { provider ->
            try {
                if (provider.accept(type)) {
                    provider.generate(description, type)
                } else {
                    emptySequence()
                }
            } catch (t: Throwable) {
                //logger.error(t) { "Error occurs in value provider: $provider" }
                emptySequence()
            }
        }
    }

    override suspend fun handle(description: D, values: List<R>): F {
        return exec(description, values)
    }

    override suspend fun update(description: D, statistic: Statistic<T, R>, configuration: Configuration) {
        update.invoke(description, statistic, configuration)
    }
}