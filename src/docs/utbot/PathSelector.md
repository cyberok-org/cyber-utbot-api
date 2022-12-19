# [PathSelector](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/PathSelector.kt)

Контейнер для [ExecutionState](ExecutionState.md).

основная функциональность:
_offer_ - добавляет [ExecutionState](ExecutionState.md), _poll_ - берёт какой-то  [ExecutionState](ExecutionState.md). Хорошо описано в коде [PathSelector](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/PathSelector.kt)

_pathSelector_ в [UtBotSymbolicEngine](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/UtBotSymbolicEngine.kt) выдаёт какую-то из имплементаций _PathSelector_. Ограничивает её числом шагов (_pathSelectorStepsLimit_ из [UtSettings](../../../../utbot-framework-api/src/main/kotlin/org/utbot/framework/UtSettings.kt)).

Разные _PathSelector_ используют разные стратегии для выбора нужной вершины, используют и хранят в себе граф ([InterProceduralUnitGraph](InterProceduralUnitGraph.md)). Это потенциальная точка расширения.

## [CoveredNewSelector](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/nurs/CoveredNewSelector.kt)

Один из [PathSelector](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/PathSelector.kt) - [CoveredNewSelector](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/nurs/CoveredNewSelector.kt). Рассмотрим его для примера.

При построении каждого [PathSelector](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/PathSelector.kt) используется наследник [PathSelectorBuilder](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/PathSelectorBuilder.kt) (дефолтно содержит дополнительную функциональность, которую можно добавить. Например, _withStepsLimit_ для ограничения числа шагов - упомянуто выше). Так же, помимо графа хранит контекст [PathSelectorContext](PathSelectorContext.md).

[CoveredNewSelectorBuilder](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/PathSelectorBuilder.kt) - один из таких наследников [PathSelectorBuilder](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/PathSelectorBuilder.kt). Дополнительно определяет _seed_ для генератора. Добавляет использование [DistanceStatistics](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/strategies/DistanceStatistics.kt). В итоге он строит наконец [CoveredNewSelector](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/nurs/CoveredNewSelector.kt).

Он дополнительно в [ExecutionState](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/state/ExecutionState.kt) определяет _cost_ - он позволяет выбирать состояния выполнения, наиболее близкие к непокрытым (в комментарии к коду [CoveredNewSelector](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/nurs/CoveredNewSelector.kt) написано подробнее).
