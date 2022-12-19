# [PathSelectorContext](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/PathSelectorBuilder.kt)

Содержит:
- [InterProceduralUnitGraph](InterProceduralUnitGraph.md) - сам граф.
- [DistanceStatistics](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/strategies/DistanceStatistics.kt) - вычисляет расстояния между _Stmt_.
- [SubpathStatistics](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/strategies/SubpathStatistics.kt) - вычисляет частоту вложенных путей операторов с определённым значением длиной.
- [StatementsStatistics](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/strategies/StatementsStatistics.kt) - вычисляет количество посещений текущей инструкции состояния и количество посещенных инструкций в текущем методе.
- [EdgeVisitCountingStatistics](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/strategies/EdgeVisitCountingStatistics.kt) - вычисляет сколько раз ребра были посещены во время обхода графа.
- [GeneratedTestCountingStatistics](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/strategies/GeneratedTestCountingStatistics.kt) - считает число сгенерированных тестов.
- [StoppingStrategy](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/strategies/StoppingStrategy.kt) - какая-то стратегия, говорящая на шаге надо ли остановиться.

Всех их может и не быть. Что-то из этого используется классами с этим контекстом.
