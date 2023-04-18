# Fuzzing

- [fuzzing](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing) - более общий вид всего fuzzing
- [fuzzer](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzer) - конкретно реализации [fuzzing](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing) и специфичные вещи

## Main interface

- [Api](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing/Api.kt) - `Fuzzing` и остальные базовые сущности с описанием, `fuzz` - entry point for every fuzzing
- [Fuzzer](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzer/Fuzzer.kt) - интерфейсы и важные вспомогательные сущности
- [ModelMutator](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzer/ModelMutator.kt) - интерфейсы мутаций
- [ModelProvider](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzer/ModelProvider.kt) - интерфейсы производителей значений
- [JavaLanguage](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzing/JavaLanguage.kt) - `fuzzing` для `java`
- [Mutations](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing/Mutations.kt)
- [Providers](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing/Providers.kt)

### Entities

- [KnownValue](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing/seeds/KnownValue.kt) - интерфейс значения (связано с мутацией)
- [FuzzedValue](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzer/FuzzedValue.kt) - обёртка для конкретного значения в `fuzzing` с дополнительной информацией
- [FuzzedParameter](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzer/FuzzedParameter.kt) - [FuzzedValue](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzer/FuzzedValue.kt) с индексом (нужен для создания какого-то аргумента, например)

## Main

Этапы `fuzzing` описаны в [UtBotSymbolicEngine](UtBotSymbolicEngine.md)

Есть [trie](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing/utils/Trie.kt), оно строится по инструкциям.

Имплементации [ModelProvider](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzer/ModelProvider.kt) создают какие-то значения, [ModelMutator](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzer/ModelMutator.kt) мутируют их для новых входов. В самом начале в качестве конкретных значений которые будут использоваться задаются, в том числе константы из кода (соответствующие `ModelProvider`).
На каждом шаге берётся новое полученное значение и отдаётся на конкретное исполнение ([ConcreteExecutor](../../../../utbot-instrumentation/src/main/kotlin/org/utbot/instrumentation/ConcreteExecutor.kt)), которое возвращает фидбек - `BaseFeedback` (из [Api](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing/Api.kt)) - содержащий указание, что делать дальше - `Control` и `node` из [trie](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing/utils/Trie.kt). Он учитывается при дальнейшем фазинге.
И так продолжается пока всё не остановят

## Where

- Mutators: [seeds](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing/seeds) - базовые, [mutators](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzer/mutators) - другие
- Providers [providers](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzing/providers) - базовые,  [mutators](../../../../utbot-fuzzers/src/main/kotlin/org/utbot/fuzzer/providers) - другие

## Other

[json-fuzzing](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing/demo/JsonFuzzing.kt) и в целом [demo](../../../../utbot-fuzzing/src/main/kotlin/org/utbot/fuzzing/demo)
