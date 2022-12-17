# [UtExecutionInstrumentation](../../../../utbot-framework/src/main/kotlin/org/utbot/framework/concrete/UtExecutionInstrumentation.kt)

- _delegateInstrumentation_ - [InvokeInstrumentation](../../../../utbot-instrumentation/src/main/kotlin/org/utbot/instrumentation/instrumentation/InvokeInstrumentation.kt) - вызывает функцию и оборачивает результат в [Result](https://docs.oracle.com/javase/7/docs/api/javax/xml/transform/Result.html)

- _instrumentationContext_ - [InstrumentationContext](../../../../utbot-framework/src/main/kotlin/org/utbot/framework/concrete/InstrumentationContext.kt) - берёт какую-то информацию после инструментации и использует её при вызове.

- _traceHandler_ = [TraceHandler](../../../../utbot-instrumentation/src/main/kotlin/org/utbot/instrumentation/instrumentation/et/TraceHandler.kt)

- _pathsToUserClasses_ - пути до классов пользователя

Инструментация: игнорирует **arguments**, потому что будут построены конкретные аргументы из моделей, переданных через **parameters** (имеют тип [UtConcreteExecutionData](../../../../utbot-framework/src/main/kotlin/org/utbot/framework/concrete/UtExecutionInstrumentation.kt))

[UtConcreteExecutionData](../../../../utbot-framework/src/main/kotlin/org/utbot/framework/concrete/UtExecutionInstrumentation.kt) содержит внутри [EnvironmentModels](EnvironmentModels.md) необходимый для построения параметров конкретного вызова.

Этапы при вызове функции:

- достаются _parametersModels_ из параметров вызова метода (инстанс класса и аргументы) имеющие тип [UtModel](UtModel.md)
- [MockValueConstructor](MockValueConstructor.md) принимает _instrumentationContext_ и
  - создаёт реальные параметры _params_ (для вызова класса, учитывающие контекст). Они имеют тип _UtConcreteValue_ (получаются из _mock_ по _ClassId_ _UtModel_).
  - устанавливает значения статических полей
  - применяет _mock_ на статические методы и создание инстансов. 
  - создаёт _context_ ([UtContext](../../../../utbot-framework-api/src/main/kotlin/org/utbot/framework/plugin/api/util/UtContext.kt), содержащий _ClassLoader_)
  - запускает в отдельном потоке с полученным контекстом метод с параметрами, получается _concreteResult_
  - берет все полученные по ходу выполнения объекты и складывает в кеш.
  - Создаёт стратегию, определяющую по каким классам надо создавать составную модель - _UtCompositeModel_. (по тем что есть в кеше или объявлены пользователем).
  - [UtModelConstructor](UtModelConstructor.md) строится по кешу и стратегии (полученных ранее) и
    - получает UtModel по конкретному значению _concreteResult_ (полученному ранее)
    - создаёт _stateAfterParametersWithThis_, _stateAfterStatics_ по параметрам и статическим полям
    - получает _stateAfter_, и используя его создаёт и возвращает [UtConcreteExecutionResult](../../../../utbot-framework/src/main/kotlin/org/utbot/framework/concrete/UtExecutionInstrumentation.kt)
