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

Есть 2 способа генерить тесты - с использованием асинхронности (_generateAsync_) и нет (_generate_). Рассмотрим только обычный способ. В асинхронном случае, в отличие от простого в качестве генератора (то что мы делаем с символьным движком) используется _defaultTestFlow_ (его нельзя задать) и возвращается _Flow< UtResult >_ вместо _List<UtMethodTestSet>_ (на самом деле в _generate_ так же получается _Flow<UtResult>_, просто он дополнительно обрабатывается и приводится к нужному виду).

Этапы _generate_:

- настраиваются вспомогательные части
- для каждого метода создаётся движок (_UtBotSymbolicEngine_) и все они запускаются (_job_), при получении результата кладут его соответственно в _method2executions_ или в _method2errors_ в зависимости от успешности результата.
- отдельно запущен поток следящий за временем выполнения, если оно превышено, то _job_ убивается.
- в конце возвращается _List<UtMethodTestSet>_ - по каждому методу берём то, что успело получиться. Дополнительно берётся минимальное число тесткейсов, при этом используется minimizeExecutions.

_defaultTestFlow_ создаёт [TestFlow](TestFlow.md) по таймауту и движку.
