# [PathSelector](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/selectors/PathSelector.kt)

Контейнер для [ExecutionState](ExecutionState.md).

основные: _offer_ - добавляет [ExecutionState](ExecutionState.md), poll - берёт какой-то  [ExecutionState](ExecutionState.md).

_pathSelector_ в [UtBotSymbolicEngine](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/UtBotSymbolicEngine.kt) выдаёт какую-то из имплементаций _PathSelector_.

Разные _PathSelector_ используют разные стратегии для выбора нужной вершины. Это потенциальная точка расширения.
