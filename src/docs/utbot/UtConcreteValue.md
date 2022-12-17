# [UtConcreteValue](../../../../utbot-framework-api/src/main/kotlin/org/utbot/framework/plugin/api/ValueBasedApi.kt)

Конкретное значение, имеет в себе само _значение_, _тип_, _геттер_ и реализации _equals_ и _hashcode_

Например, есть **IntArray**:

```
UtConcreteValue(
    value, 
    IntArray::class.java,
    { it },
    IntArray?::contentEquals,
    IntArray?::contentHashCode
)
```
