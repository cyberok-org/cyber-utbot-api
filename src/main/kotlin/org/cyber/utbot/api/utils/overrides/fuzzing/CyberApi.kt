import kotlinx.coroutines.yield
import org.cyber.utbot.api.utils.overrides.fuzzing.CyberStringValue
import org.utbot.fuzzing.*
import org.utbot.fuzzing.Result
import org.utbot.fuzzing.seeds.KnownValue
import org.utbot.fuzzing.seeds.StringValue
import org.utbot.fuzzing.utils.MissedSeed
import org.utbot.fuzzing.utils.flipCoin
import org.utbot.fuzzing.utils.transformIfNotEmpty
import kotlin.random.Random

/**
 * Starts fuzzing for this [Fuzzing] object.
 *
 * This is an entry point for every fuzzing.
 */
suspend fun <T, R, D : Description<T>, F : Feedback<T, R>> Fuzzing<T, R, D, F>.cyberFuzz(
    description: D,
    random: Random = Random(0),
    configuration: Configuration = Configuration()
) {
    class StatImpl(
        override var totalRuns: Long = 0,
        val startTime: Long = System.nanoTime(),
        override var missedTypes: MissedSeed<T, R> = MissedSeed(),
    ) : Statistic<T, R> {
        override val elapsedTime: Long
            get() = System.nanoTime() - startTime
    }
    val userStatistic = StatImpl()
    val fuzzing = this
    val typeCache = hashMapOf<T, List<Seed<T, R>>>()
    fun fuzzOne(): Node<T, R> = fuzz(
        parameters = description.parameters,
        fuzzing = fuzzing,
        description = description,
        random = random,
        configuration = configuration,
        builder = PassRoutine("Main Routine"),
        state = State(1, typeCache, userStatistic.missedTypes),
    )
    val dynamicallyGenerated = mutableListOf<Node<T, R>>()
    val seeds = Statistics<T, R, F>()
    run breaking@ {
        sequence {
            while (description.parameters.isNotEmpty()) {
                if (dynamicallyGenerated.isNotEmpty()) {
                    var fst = dynamicallyGenerated.removeFirst()
                    if (dynamicallyGenerated.isNotEmpty()) fst = dynamicallyGenerated.removeFirst()
                    println("yield dyn $fst")
                    yield(fst)
                } else {
                    val fuzzOne = fuzzOne()
                    // fuzz one value, seems to be bad, when have only a few and simple values
                    yield(fuzzOne)

                    val randomSeed = seeds.getRandomSeed(random, configuration)
                    if (randomSeed != null) {
                        val mutate = cyberMutate(
                            randomSeed,
                            fuzzing,
                            random,
                            configuration,
                            State(1, typeCache, userStatistic.missedTypes)
                            // todo add fun info
                        )
                        println("mutate")
                        mutate.result.forEach {
                            if (it is Result.Known<*, *, *>) {
                                println(((it as Result.Known<*, *, *>).value as StringValue).value)
                            }
                        }
                        dynamicallyGenerated += mutate
                    }
                }
            }
        }.forEach execution@ { values ->
            yield()
            fuzzing.update(description, userStatistic.apply {
                totalRuns++
            }, configuration)
            check(values.parameters.size == values.result.size) { "Cannot create value for ${values.parameters}" }
            val valuesCache = mutableMapOf<Result<T, R>, R>()
            val result = values.result.map { valuesCache.computeIfAbsent(it) { r -> create(r) } }
            println("here! ${result.get(0).toString()}")
            val feedback = fuzzing.handle(description, result)
            when (feedback.control) {
                Control.CONTINUE -> {
                    println("HERE CONT")
                    seeds.put(random, configuration, feedback, values)
                }
                Control.RESET_TYPE_CACHE_AND_CONTINUE -> {
                    dynamicallyGenerated.clear()
                    typeCache.clear()
                    seeds.put(random, configuration, feedback, values)
                }
                Control.STOP -> {
                    println("HERE STOP")
                    return@breaking }
                Control.PASS -> {}
            }
        }
    }
}

fun <TYPE, RESULT, DESCRIPTION : Description<TYPE>, FEEDBACK : Feedback<TYPE, RESULT>> fuzz(
    parameters: List<TYPE>,
    fuzzing: Fuzzing<TYPE, RESULT, DESCRIPTION, FEEDBACK>,
    description: DESCRIPTION,
    random: Random,
    configuration: Configuration,
    builder: Routine<TYPE, RESULT>,
    state: State<TYPE, RESULT>,
): Node<TYPE, RESULT>  {
    val typeCache = mutableMapOf<TYPE, MutableList<Result<TYPE, RESULT>>>()
    val result = parameters.map { type ->
        val results = typeCache.computeIfAbsent(type) { mutableListOf() }
        if (results.isNotEmpty() && random.flipCoin(configuration.probReuseGeneratedValueForSameType)) {
            // we need to check cases when one value is passed for different arguments
            results.random(random)
        } else {
            produce(type, fuzzing, description, random, configuration, state).also {
                results += it
            }
        }
    }
    // is not inlined to debug values generated for a concrete type
    return Node(result, parameters, builder)
}


fun <TYPE, RESULT, DESCRIPTION : Description<TYPE>, FEEDBACK : Feedback<TYPE, RESULT>> produce(
    type: TYPE,
    fuzzing: Fuzzing<TYPE, RESULT, DESCRIPTION, FEEDBACK>,
    description: DESCRIPTION,
    random: Random,
    configuration: Configuration,
    state: State<TYPE, RESULT>,
): Result<TYPE, RESULT> {
    val candidates = state.cache.computeIfAbsent(type) {
        fuzzing.generate(description, type).toList()
    }.map {
        @Suppress("UNCHECKED_CAST")
        when (it) {
            is Seed.Simple<TYPE, RESULT> -> Result.Simple(it.value, it.mutation)
            is Seed.Known<TYPE, RESULT, out KnownValue> -> Result.Known(it.value, it.build as KnownValue.() -> RESULT)
            is Seed.Recursive<TYPE, RESULT> -> reduce(
                it,
                fuzzing,
                description,
                random,
                configuration,
                state
            )
            is Seed.Collection<TYPE, RESULT> -> reduce(it, fuzzing, description, random, configuration, state)
        }
    }
    if (candidates.isEmpty()) {
        throw NoSeedValueException(type)
    }
    return candidates.random(random)
}

/**
 *  reduces [Seed.Recursive] type.  When `configuration.recursionTreeDepth` limit is reached it calls
 *  `Seed.Recursive#empty` routine to create an empty object.
 */
fun <TYPE, RESULT, DESCRIPTION : Description<TYPE>, FEEDBACK : Feedback<TYPE, RESULT>> reduce( // todo also move
    task: Seed.Recursive<TYPE, RESULT>,
    fuzzing: Fuzzing<TYPE, RESULT, DESCRIPTION, FEEDBACK>,
    description: DESCRIPTION,
    random: Random,
    configuration: Configuration,
    state: State<TYPE, RESULT>,
): Result<TYPE, RESULT> {
    return if (state.recursionTreeDepth > configuration.recursionTreeDepth) {
        Result.Empty { task.empty.builder() }
    } else try {
        Result.Recursive(
            construct = fuzz(
                task.construct.types,
                fuzzing,
                description,
                random,
                configuration,
                task.construct,
                State(state.recursionTreeDepth + 1, state.cache, state.missedTypes)
            ),
            modify = task.modify
                .toMutableList()
                .transformIfNotEmpty {
                    shuffle(random)
                    take(random.nextInt(size + 1))
                }
                .mapTo(arrayListOf()) { routine ->
                    fuzz(
                        routine.types,
                        fuzzing,
                        description,
                        random,
                        configuration,
                        routine,
                        State(state.recursionTreeDepth + 1, state.cache, state.missedTypes)
                    )
                }
        )
    } catch (nsv: NoSeedValueException) {
        @Suppress("UNCHECKED_CAST")
        state.missedTypes[nsv.type as TYPE] = task
        if (configuration.generateEmptyRecursiveForMissedTypes) {
            Result.Empty { task.empty.builder() }
        } else {
            throw nsv
        }
    }
}


/**
 * reduces [Seed.Collection] type. When `configuration.recursionTreeDepth` limit is reached it creates
 * an empty collection and doesn't do any modification to it.
 */
fun <TYPE, RESULT, DESCRIPTION : Description<TYPE>, FEEDBACK : Feedback<TYPE, RESULT>>  reduce( // todo also move
    task: Seed.Collection<TYPE, RESULT>,
    fuzzing: Fuzzing<TYPE, RESULT, DESCRIPTION, FEEDBACK>,
    description: DESCRIPTION,
    random: Random,
    configuration: Configuration,
    state: State<TYPE, RESULT>,
): Result<TYPE, RESULT> {
    return if (state.recursionTreeDepth > configuration.recursionTreeDepth) {
        Result.Empty { task.construct.builder(0) }
    } else try {
        val iterations = when {
            state.iterations >= 0 && random.flipCoin(configuration.probCreateRectangleCollectionInsteadSawLike) -> state.iterations
            else -> random.nextInt(1, configuration.collectionIterations + 1)
        }
        Result.Collection(
            construct = org.utbot.fuzzing.fuzz(
                task.construct.types,
                fuzzing,
                description,
                random,
                configuration,
                task.construct,
                State(state.recursionTreeDepth + 1, state.cache, state.missedTypes, iterations)
            ),
            modify = if (true) { //(random.flipCoin(configuration.probCollectionMutationInsteadCreateNew)) {
                val result = org.utbot.fuzzing.fuzz(
                    task.modify.types,
                    fuzzing,
                    description,
                    random,
                    configuration,
                    task.modify,
                    State(state.recursionTreeDepth + 1, state.cache, state.missedTypes, iterations)
                )
                arrayListOf(result).apply {
                    (1 until iterations).forEach { _ ->
                        add(cyberMutate(result, fuzzing, random, configuration, state))
                    }
                }
            } else {
                (0 until iterations).map {
                    org.utbot.fuzzing.fuzz(
                        task.modify.types,
                        fuzzing,
                        description,
                        random,
                        configuration,
                        task.modify,
                        State(state.recursionTreeDepth + 1, state.cache, state.missedTypes, iterations)
                    )
                }
            },
            iterations = iterations
        )
    } catch (nsv: NoSeedValueException) {
        @Suppress("UNCHECKED_CAST")
        state.missedTypes[nsv.type as TYPE] = task
        if (configuration.generateEmptyCollectionsForMissedTypes) {
            Result.Empty { task.construct.builder(0) }
        } else {
            throw nsv
        }
    }
}

/**
 *  Starts mutations of some seeds from the object tree.
 */
@Suppress("UNCHECKED_CAST")
fun <TYPE, RESULT, DESCRIPTION : Description<TYPE>, FEEDBACK : Feedback<TYPE, RESULT>> cyberMutate(
    node: Node<TYPE, RESULT>,
    fuzzing: Fuzzing<TYPE, RESULT, DESCRIPTION, FEEDBACK>,
    random: Random,
    configuration: Configuration,
    state: State<TYPE, RESULT>,
): Node<TYPE, RESULT> {
    if (node.result.isEmpty()) return node
    val indexOfMutatedResult = Configuration.taintedArgs[Random.nextInt(Configuration.taintedArgs.size)] + 1 // the first param is the method description afaik
    // random.chooseOne(node.result.map(::rate).toDoubleArray())
    println("idx == $indexOfMutatedResult")
    val mutated = when (val resultToMutate = node.result[indexOfMutatedResult]) {
        is Result.Simple<TYPE, RESULT> -> Result.Simple(resultToMutate.mutation(resultToMutate.result, random), resultToMutate.mutation)
        is Result.Known<TYPE, RESULT, out KnownValue> -> {
            val mutations = CyberStringValue(((resultToMutate.value) as StringValue).value).mutations() // todo(unchecked cast)
            if (mutations.isNotEmpty()) {
                Result.Known(
                    mutations.random(random).mutate(resultToMutate.value, random, configuration),
                    resultToMutate.build as KnownValue.() -> RESULT
                )
            } else {
                resultToMutate
            }
        }
        is Result.Recursive<TYPE, RESULT> -> {
            if (resultToMutate.modify.isEmpty() || random.flipCoin(configuration.probConstructorMutationInsteadModificationMutation)) {
                Result.Recursive(
                    construct = cyberMutate(resultToMutate.construct, fuzzing, random, configuration, State(state.recursionTreeDepth + 1, state.cache, state.missedTypes)),
                    modify = resultToMutate.modify
                )
            } else if (random.flipCoin(configuration.probShuffleAndCutRecursiveObjectModificationMutation)) {
                Result.Recursive(
                    construct = resultToMutate.construct,
                    modify = resultToMutate.modify.shuffled(random).take(random.nextInt(resultToMutate.modify.size + 1).coerceAtLeast(1))
                )
            } else {
                Result.Recursive(
                    construct = resultToMutate.construct,
                    modify = resultToMutate.modify.toMutableList().apply {
                        val i = random.nextInt(0, resultToMutate.modify.size)
                        set(i, cyberMutate(resultToMutate.modify[i], fuzzing, random, configuration, State(state.recursionTreeDepth + 1, state.cache, state.missedTypes)))
                    }
                )
            }
        }
        is Result.Collection<TYPE, RESULT> -> Result.Collection(
            construct = resultToMutate.construct,
            modify = resultToMutate.modify.toMutableList().apply {
                if (isNotEmpty()) {
                    if (random.flipCoin(100 - configuration.probCollectionShuffleInsteadResultMutation)) {
                        val i = random.nextInt(0, resultToMutate.modify.size)
                        set(i, cyberMutate(resultToMutate.modify[i], fuzzing, random, configuration, State(state.recursionTreeDepth + 1, state.cache, state.missedTypes)))
                    } else {
                        shuffle(random)
                    }
                }
            },
            iterations = resultToMutate.iterations
        )
        is Result.Empty -> resultToMutate
    }
    return Node(node.result.toMutableList().apply {
        set(indexOfMutatedResult, mutated)
    }, node.parameters, node.builder)
}