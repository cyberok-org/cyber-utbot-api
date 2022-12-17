# [ConcreteExecutor](../../../../utbot-instrumentation/src/main/kotlin/org/utbot/instrumentation/ConcreteExecutor.kt)

_pathsToUserClasses_ - пути до классов

_pathsToDependencyClasses_ - зависимости для классов (если необходимо)

_instrumentation_ - наследника [Instrumentation](../../../../utbot-instrumentation/src/main/kotlin/org/utbot/instrumentation/instrumentation/Instrumentation.kt) (который в свою очередь является [ClassFileTransformer](https://docs.oracle.com/javase/7/docs/api/java/lang/instrument/ClassFileTransformer.html))

[ClassFileTransformer](https://docs.oracle.com/javase/7/docs/api/java/lang/instrument/ClassFileTransformer.html) - интерфейс для преобразования файлов классов. Преобразование происходит до того, как класс будет определен JVM.

[//]: # (TODO)
