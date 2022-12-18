# Types

Часть про использование типов.

[TypeRegistry](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/types/TypeRegistry.kt) - модуль, умеющий регистрировать и хранить типы (**Type <-> unique type id (int)**; **Object address -> to type id**)

[Hierarchy](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/Hierarchy.kt) - содержит иерархию типов по всем загруженным классам.

[TypeResolver](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/types/TypeResolver.kt) - используя две сущности, описанные выше способен проводить преобразования типов, выводить какие-то факты про них и создавать [TypeStorage](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/DataClasses.kt).
