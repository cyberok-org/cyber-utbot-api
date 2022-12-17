# [TestCaseGenerator](../../../../utbot-framework/src/main/kotlin/org/utbot/framework/plugin/api/TestCaseGenerator.kt)

Генерирует тестовые примеры: один за другим или весь набор для тестируемого метода (подробнее в описании класса)

То, что не описано:

- _dependencyPaths_ - пути с зависимостями в каком-то виде 

- _engineActions_ - события выполняемые после создания [движка](UtBotSymbolicEngine.md) (_createSymbolicEngine_) на нём.

- _isCanceled_ - передаётся, чтобы знать когда стоит всё прекратить (не инициализируется/не выполняется (создаётся пустой [UtMethodTestSet](../../../../utbot-framework-api/src/main/kotlin/org/utbot/framework/plugin/api/Api.kt)))

Этапы создания:

- инициализирует [Soot](../abstract/Soot.md)
- создаёт и запускает [ConcreteExecutor](ConcreteExecutor.md) с [UtExecutionInstrumentation](UtExecutionInstrumentation.md) если установлен [warmupConcreteExecution](../../../../utbot-framework-api/src/main/kotlin/org/utbot/framework/UtSettings.kt) - инструментирует все классы перед началом (запускает он на задефайненных данных асинхронно (_разогревает_))

[UtExecutionInstrumentation](UtExecutionInstrumentation.md) - конкретный класс для инструментации

[//]: # (TODO)

