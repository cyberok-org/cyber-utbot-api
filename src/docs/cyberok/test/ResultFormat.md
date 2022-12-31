# Result format

## Main part

Представляет собой `json` с ключами `path`, `assertions`, `assumption` - списки строк, `lastStatus` - строка

Все это информация о сгенерированном тесте
- `path` - путь из [Jimple](../../abstract/Jimple.md) инструкций для одного теста.
- `assertions` - ограничения для теста, полученные `utbot` после прохождения пути.
- `assumption` - предположения для теста, полученные `utbot` после прохождения пути.
- `lastStatus` - пока всегда `SAT` (потом стоит собирать информацию о "недогенерированных тестах", так что будет полезен).

## Example

Пример для одного из тестов класса `Simple` из [TestFormat](TestFormat.md), полученного `UTBot`

```json
{
    "path": [
        "r0 := @this: org.testcases.formalGen.Simple",
        "i0 := @parameter0: int",
        "i2 := @parameter1: int",
        "i3 = i0",
        "if i0 <= 0 goto $i4 = i3 + i0",
        "$i4 = i3 + i0"
    ],
    "assertions": [
        "(Le (int addr: (BVInt32 p_this)) (int addr: Int32 0))",
        "(or (is addr: (BVInt32 p_this) org.testcases.formalGen.Simple), (eq addr: (BVInt32 p_this) addr: Int32 0))",
        "(Ge (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0))",
        "(Le (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4486))",
        "(Ge (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0))",
        "(Le (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4))",
        "(eq (select (array isMock : Int32 -> Bool) addr: (BVInt32 p_this)) false)",
        "(not (Eq (int addr: (BVInt32 p_this)) (int addr: Int32 0)))",
        "(Le (int (BVInt32 p0)) (int Int32 0))",
        "(mkTermArray (array arraysLength : Int32 -> Int32))",
        "(mkTermArray (array addrToNumDimensions : Int32 -> Int32))",
        "(mkTermArray (array addrToTypeId : Int32 -> Int32))"
    ],
    "assumption": [],
    "lastStatus": "SAT"
}
```

## Notes

- возможно будет потом модифицирован, стоит изучить другие инструменты, обобщить результат и адаптировать алгоритмы
