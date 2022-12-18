# [ConcreteExecutor](../../../../utbot-instrumentation/src/main/kotlin/org/utbot/instrumentation/ConcreteExecutor.kt)

_pathsToUserClasses_ - пути до классов

_pathsToDependencyClasses_ - зависимости для классов (если необходимо)

_instrumentation_ - наследника [Instrumentation](../../../../utbot-instrumentation/src/main/kotlin/org/utbot/instrumentation/instrumentation/Instrumentation.kt) (который в свою очередь является [ClassFileTransformer](https://docs.oracle.com/javase/7/docs/api/java/lang/instrument/ClassFileTransformer.html))

[ClassFileTransformer](https://docs.oracle.com/javase/7/docs/api/java/lang/instrument/ClassFileTransformer.html) - интерфейс для преобразования файлов классов. Преобразование происходит до того, как класс будет определен JVM.

Этапы _executeAsync_:
- эти шаги выполняются от [UtInstrumentationProcess](../../../../utbot-instrumentation/src/main/kotlin/org/utbot/instrumentation/rd/UtInstrumentationProcess.kt) (он подготавливает процесс к выполнению, инициализирует [rd](https://github.com/JetBrains/rd)) через _withProcess_ (так же может инструментировать код перед выполнением шагов дальше).
  - переводит аргументы и параметры в _ByteArray_ при помощи [KryoHelper](../../../../utbot-instrumentation/src/main/kotlin/org/utbot/instrumentation/util/KryoHelper.kt) (класс для сериализации/десериализации).
  - создаёт [InvokeMethodCommandParams](../../../../utbot-instrumentation/src/main/kotlin/org/utbot/instrumentation/rd/generated/ChildProcessModel.Generated.kt)
  - используя [rd](https://github.com/JetBrains/rd), запускается метод с полученным на прошлом шаге и возвращается десериализованный результат.

[док по rd из **utbot**](../../../../docs/RD for UnitTestBot.md)
