# Test format specification

## Reason

Примеры достаточно важны для текущей задачи:
- можно сравнивать в последствии с, например, другими движками
- можно увидеть что изменилось при обновлении проекта
- мониторить ошибки и вовремя узнать, что их исправили или понять, что где-то наоборот всё сломалось
- позволяют понять как всё работает

## Main part

**Предполагаемый формат подготовленных файлов**
- Каждый файл имеет расширение `java`. Содержит в себе единственный класс с единственной публичной функцией внутри - `fun`.
- Перед классом (в любом месте и порядке до начала) могут быть опциональные комментарии вида `// @tag(...)` (может быть сколько угодно) и `// @description(<arbitrary comment>); // @authorn(<name>); // @date(<date>)` (один или ни одного).  Подробнее о них дальше, отдельно.

**Предполагаемый формат хранения**
- Все файлы лежат в одной отдельной папке.
- Все файлы имеют расширение `java` (будет в любом случае соответствовать исходному).
- В начале идёт сам файл примера, его описание и теги (просто копируется текст из тестового соответствующего файла).
- После этого могут быть добавлены заметки в виде `// @note(<arbitrary comment>)`.
- После начинается многострочный комментарий и идёт до конца файла. В нём через разделители следует информация о сгенерированных тестах:
  - `path` - путь из [Jimple](../abstract/Jimple.md) инструкций для одного теста.
  - `assertions` - ограничения для теста, полученные `utbot` после прохождения пути.
  - `assumption` - предположения для теста, полученные `utbot` после прохождения пути.
  - `lastStatus` - пока всегда `SAT` (потом стоит собирать информацию о "недогенерированных тестах", так что будет полезен).

### Why do you need tags, notes, description, ...?

- описание нужно для того чтобы быстро понять о чём и для чего есть этот пример.
- по тегам можно будет искать. Теги должны обозначать что использует данный пример, что демонстрирует.
- заметки позволяют сказать что-то об уже полученном результате и особенностях того, как он отработал.
- автор нужен для удобства, чтобы понимать к кому обращаться за подробностями по примеру. Желательно, чтобы он был.
- дата когда был начат пример может пригодиться для сортировки (с точностью до дня в формате `ДД/ММ/ГГГГ`), фильтрации и прочего. Желательно, чтобы она была.

## Tag system

Каждый тег имеет вид `// @tag(<name>, {<key>: <value>, ...})`

Названия и возможные ключи должны быть где-то определены (хотя их можно и собирать просто анализом и где-то хранить).

Должен быть какой-то зарезервированный набор ключей, который может быть во всех случаях:
- significance - степень значимости тега для примера (`insignificant, usual, important, critical` - набор возможных значений. `usual` по умолчанию).
- ...

Примеры:
- `// @tag("if", {"significance": "important"})`
- `// @tag("loop", {"iterations max": 50})`
- `// @tag("loop", {"iterations max": "-", "significance": "insignificant"})`  // unlimited
- `// @tag("arithmetic", {"operators": {"-", "*"}})`
- `// @tag("error", {"type": "ArithmeticException"})`
- `// @tag("primes")`
- `// @tag("overflow", {"significance": "critical"})`

## For improvement, goals

- Возможно стоит скомбинировать или связать сгенерированные тесты и формат хранения.
- `// @note(...)` будет вставляться в любом случае руками. Может быть стоит получше продумать как это сделать удобным.
- Может быть есть ещё что-то важное, что хочется доставать из информации о сгенерированных тестах.
- Улучшить `Tag system`.
- Добавить какое-то пока простейшее приложение для поиска по тегам и вывода отдельно `@tag, @description, @note`.

## Tests generation realization

[//]: #TODO (Tests generation realization)

Сейчас файлы в формате хранения нельзя получить просто запуском (это стоит сделать). 

Пока сам пример копируется для хранения руками, информация о сгенерированных тестах берётся копированием из консоли результата запуска `cyber-utbot-api` (с предварительными изменениями, описанными ниже).

Изменение для вывода - стоит добавить в начало [UtBotSymbolicEngine](../utbot-framework/src/main/kotlin/org/utbot/engine/UtBotSymbolicEngine.kt) `consumeTerminalState`:

```kotlin
println("----------------------------------------------------------------------------------------------------")
println("path: ${state.path.joinToString(separator=",\n\t")}")
state.symbolicState.solver.apply {
    println("assertions: ${assertions.joinToString(separator=",\n\t")}")
    println("assumption: $assumption")
    println("lastStatus: $lastStatus")
}
println()
```

## Example

```java
package org.testcases.formalGen;

// @author(pogrebnoijak)
// @date(19/12/2022)
// @description(This is an design example, it does not show anything worthwhile)

// @tag("if", {"significance": "important", "condition": "difficult"})`
// @tag("overflow")`
// @tag("arithmetic", {"operators": {"+", "*"}, "significance": "insignificant"})
// @tag("simple")`

public class Simple {
  public int fun(int x, int y) {
    int a = x;
    if (x > 0 && x * 2 < 0) {
      a = y;
    }
    return a + x;
  }
}

// @note(found all 3 conditions, works correctly)

/*
----------------------------------------------------------------------------------------------------
path: r0 := @this: org.testcases.formalGen.Simple,
	i0 := @parameter0: int,
	i2 := @parameter1: int,
	i3 = i0,
	if i0 <= 0 goto $i4 = i3 + i0,
	$i4 = i3 + i0
assertions: (Le (int addr: (BVInt32 p_this)) (int addr: Int32 0)),
	(or (is addr: (BVInt32 p_this) org.testcases.formalGen.Simple), (eq addr: (BVInt32 p_this) addr: Int32 0)),
	(Ge (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4486)),
	(Ge (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4)),
	(eq (select (array isMock : Int32 -> Bool) addr: (BVInt32 p_this)) false),
	(not (Eq (int addr: (BVInt32 p_this)) (int addr: Int32 0))),
	(Le (int (BVInt32 p0)) (int Int32 0)),
	(mkTermArray (array arraysLength : Int32 -> Int32)),
	(mkTermArray (array addrToNumDimensions : Int32 -> Int32)),
	(mkTermArray (array addrToTypeId : Int32 -> Int32))
assumption:
lastStatus: SAT

----------------------------------------------------------------------------------------------------
path: r0 := @this: org.testcases.formalGen.Simple,
	i0 := @parameter0: int,
	i2 := @parameter1: int,
	i3 = i0,
	if i0 <= 0 goto $i4 = i3 + i0,
	$i1 = i0 * 2,
	if $i1 >= 0 goto $i4 = i3 + i0,
	i3 = i2,
	$i4 = i3 + i0
assertions: (Le (int addr: (BVInt32 p_this)) (int addr: Int32 0)),
	(or (is addr: (BVInt32 p_this) org.testcases.formalGen.Simple), (eq addr: (BVInt32 p_this) addr: Int32 0)),
	(Ge (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4486)),
	(Ge (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4)),
	(eq (select (array isMock : Int32 -> Bool) addr: (BVInt32 p_this)) false),
	(not (Eq (int addr: (BVInt32 p_this)) (int addr: Int32 0))),
	(Gt (int (BVInt32 p0)) (int Int32 0)),
	(Lt (int (Mul (int (BVInt32 p0)) (int Int32 2))) (int Int32 0)),
	(mkTermArray (array arraysLength : Int32 -> Int32)),
	(mkTermArray (array addrToNumDimensions : Int32 -> Int32)),
	(mkTermArray (array addrToTypeId : Int32 -> Int32))
assumption:
lastStatus: SAT

----------------------------------------------------------------------------------------------------
path: r0 := @this: org.testcases.formalGen.Simple,
	i0 := @parameter0: int,
	i2 := @parameter1: int,
	i3 = i0,
	if i0 <= 0 goto $i4 = i3 + i0,
	$i1 = i0 * 2,
	if $i1 >= 0 goto $i4 = i3 + i0,
	$i4 = i3 + i0
assertions: (Le (int addr: (BVInt32 p_this)) (int addr: Int32 0)),
	(or (is addr: (BVInt32 p_this) org.testcases.formalGen.Simple), (eq addr: (BVInt32 p_this) addr: Int32 0)),
	(Ge (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToTypeId : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4486)),
	(Ge (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 0)),
	(Le (int (select (array addrToNumDimensions : Int32 -> Int32) addr: (BVInt32 p_this))) (int Int32 4)),
	(eq (select (array isMock : Int32 -> Bool) addr: (BVInt32 p_this)) false),
	(not (Eq (int addr: (BVInt32 p_this)) (int addr: Int32 0))),
	(Gt (int (BVInt32 p0)) (int Int32 0)),
	(Ge (int (Mul (int (BVInt32 p0)) (int Int32 2))) (int Int32 0)),
	(mkTermArray (array arraysLength : Int32 -> Int32)),
	(mkTermArray (array addrToNumDimensions : Int32 -> Int32)),
	(mkTermArray (array addrToTypeId : Int32 -> Int32))
assumption:
lastStatus: SAT
 */
```
