# Fundamental parts

- [TestCaseGenerator](../../../utbot-framework/src/main/kotlin/org/utbot/framework/plugin/api/TestCaseGenerator.kt) - сущность генерирующая тестовые примеры ([дока про него](utbot/TestCaseGenerator.md))
- [UtBotSymbolicEngine](../../../utbot-framework/src/main/kotlin/org/utbot/engine/UtBotSymbolicEngine.kt) - движок, центральная часть ([дока про него](utbot/UtBotSymbolicEngine.md))
- [ExecutionState](../../../utbot-framework/src/main/kotlin/org/utbot/engine/state/ExecutionState.kt) - сущность, хранящая текущее состояние выполнения ([дока про него](utbot/ExecutionState.md))
- [SymbolicState](../../../utbot-framework/src/main/kotlin/org/utbot/engine/symbolic/SymbolicState.kt) - сущность, хранящая символьное состояние, часть [ExecutionState](../../../utbot-framework/src/main/kotlin/org/utbot/engine/state/ExecutionState.kt) ([дока про него](utbot/SymbolicState.md))
- [UtExpression](../../../utbot-framework/src/main/kotlin/org/utbot/engine/pc/UtExpression.kt) - базовый класс для всех выражений, переведенных в выражение Z3 ([дока про него](utbot/UtExpression.md))
- [UtSolver](../../../utbot-framework/src/main/kotlin/org/utbot/engine/pc/UtSolver.kt) - сущность, манипулирующая ограничениями, посылает запросы на Z3. ([дока про него](utbot/UtSolver.md))
