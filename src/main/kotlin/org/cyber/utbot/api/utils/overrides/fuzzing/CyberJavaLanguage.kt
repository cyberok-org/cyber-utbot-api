package org.cyber.utbot.api.utils.overrides.fuzzing

import mu.KotlinLogging
import org.cyber.utbot.api.utils.annotations.CyberNew
import org.utbot.framework.plugin.api.ClassId
import org.utbot.framework.plugin.api.ExecutableId
import org.utbot.framework.plugin.api.Instruction
import org.utbot.framework.plugin.api.util.executable
import org.utbot.framework.plugin.api.util.isConstructor
import org.utbot.framework.plugin.api.util.isStatic
import org.utbot.fuzzer.*
import org.utbot.fuzzing.*
import org.utbot.fuzzing.providers.*
import org.utbot.fuzzing.utils.Trie
import java.lang.reflect.Type
import java.util.concurrent.CancellationException
import java.util.concurrent.TimeUnit
import kotlin.random.Random

private val logger = KotlinLogging.logger {}

suspend fun cyberRunJavaFuzzing(
    idGenerator: IdentityPreservingIdGenerator<Int>,
    methodUnderTest: ExecutableId,
    constants: Collection<FuzzedConcreteValue>,
    names: List<String>,
    providers: List<ValueProvider<FuzzedType, FuzzedValue, FuzzedDescription>> = defaultValueProviders(idGenerator),
    exec: suspend (thisInstance: FuzzedValue?, description: FuzzedDescription, values: List<FuzzedValue>) -> BaseFeedback<Trie.Node<Instruction>, FuzzedType, FuzzedValue>
) {
    val random = Random(0)
    val classUnderTest = methodUnderTest.classId
    val name = methodUnderTest.classId.simpleName + "." + methodUnderTest.name
    val returnType = methodUnderTest.returnType
    val parameters = methodUnderTest.parameters // TODO(configure by taint, remove some useless parameters)

    // For a concrete fuzzing run we need to track types we create.
    // Because of generics can be declared as recursive structures like `<T extends Iterable<T>>`,
    // we should track them by reference and do not call `equals` and `hashCode` recursively.
    val typeCache = hashMapOf<Type, FuzzedType>()
    /**
     * To fuzz this instance, the class of it is added into head of parameters list.
     * Done for compatibility with old fuzzer logic and should be reworked more robust way.
     */
    fun createFuzzedMethodDescription(self: ClassId?) = FuzzedMethodDescription(
        name, returnType, listOfNotNull(self) + parameters, constants
    ).apply {
        compilableName = if (!methodUnderTest.isConstructor) methodUnderTest.name else null
        className = classUnderTest.simpleName
        canonicalName = classUnderTest.canonicalName
        isNested = classUnderTest.isNested
        packageName = classUnderTest.packageName
        parameterNameMap = { index ->
            when {
                self != null && index == 0 -> "this"
                self != null -> names.getOrNull(index - 1)
                else -> names.getOrNull(index)
            }
        }
        fuzzerType = {
            try {
                when {
                    self != null && it == 0 -> toFuzzerType(methodUnderTest.executable.declaringClass, typeCache)
                    self != null -> toFuzzerType(methodUnderTest.executable.genericParameterTypes[it - 1], typeCache)
                    else -> toFuzzerType(methodUnderTest.executable.genericParameterTypes[it], typeCache)
                }
            } catch (_: Throwable) {
                null
            }
        }
        shouldMock = { false }
    }

    val thisInstance = with(methodUnderTest) {
        if (!isStatic && !isConstructor) { classUnderTest } else { null }
    }
    val tracer = Trie(Instruction::id)
    val descriptionWithOptionalThisInstance = FuzzedDescription(createFuzzedMethodDescription(thisInstance), tracer, typeCache, random)
    val descriptionWithOnlyParameters = FuzzedDescription(createFuzzedMethodDescription(null), tracer, typeCache, random)
    val start = System.nanoTime()
    try {
        logger.info { "Starting fuzzing for method: $methodUnderTest" }
        logger.info { "\tuse thisInstance = ${thisInstance != null}" }
        logger.info { "\tparameters = $parameters" }
        var totalExecutionCalled = 0
        println("run fuzzing")
        @CyberNew("modify configuration")
        (cyberRunFuzzing(
        provider = ValueProvider.of(providers),
        description = descriptionWithOptionalThisInstance, random,
        configuration = CyberConfiguration()
    ) { _, t ->
        totalExecutionCalled++
        if (thisInstance == null) {
            exec(null, descriptionWithOnlyParameters, t)
        } else {
            exec(t.first(), descriptionWithOnlyParameters, t.drop(1))
        }
    })
        val totalFuzzingTime = System.nanoTime() - start
        logger.info { "Finishing fuzzing for method: $methodUnderTest in ${TimeUnit.NANOSECONDS.toMillis(totalFuzzingTime)} ms" }
        logger.info { "\tTotal execution called: $totalExecutionCalled" }
    } catch (ce: CancellationException) {
        val totalFuzzingTime = System.nanoTime() - start
        logger.info { "Fuzzing is stopped because of timeout. Total execution time: ${TimeUnit.NANOSECONDS.toMillis(totalFuzzingTime)} ms" }
        logger.debug(ce) { "\tStacktrace:" }
    } catch (t: Throwable) {
        logger.info(t) { "Fuzzing is stopped because of an error" }
    }
}

fun defaultValueProviders(idGenerator: IdentityPreservingIdGenerator<Int>) = listOf(
    BooleanValueProvider,
    IntegerValueProvider,
    FloatValueProvider,
    CyberStringValueProvider,
    NumberValueProvider,
    ObjectValueProvider(idGenerator),
    ArrayValueProvider(idGenerator),
    EnumValueProvider(idGenerator),
    ListSetValueProvider(idGenerator),
    MapValueProvider(idGenerator),
    IteratorValueProvider(idGenerator),
    EmptyCollectionValueProvider(idGenerator),
    DateValueProvider(idGenerator),
//    NullValueProvider,
)