# Symbolic execution tools

Этот документ о поиске других символьных движков для `java` - содержит комментарии по существующим инструментам

Не удалось найти ничего стоящего, кроме того, что упоминается в [awesome-symbolic-execution](https://github.com/ksluckow/awesome-symbolic-execution). Далее комментарии по инструментам

В основном все проекты заброшены с `2018`

## [Java Pathfinder](https://github.com/javapathfinder/jpf-core)

Не связан с символьным исполнением, но на его основе много инструментов с ним связанных

Есть неплохой [мануал](https://github.com/javapathfinder/jpf-core/wiki/Build,-Test,-Run)

Model checking. Позиционируется как инструмент для проверки многопоточных программ. (комментарий: по функциональности чем-то схож с [lincheck](https://github.com/Kotlin/kotlinx-lincheck), но устроен иначе)

Запускается на одном файле. Ищет в нём ошибку. Содержит файл с настройками `*.jpf` (например [Rand.jpf](https://github.com/javapathfinder/jpf-core/blob/master/src/examples/Rand.jpf))

```
target = Rand

cg.enumerate_random = true
report.console.property_violation=error,trace
```

Опций может быть достаточно много, они определяют поведение.

Пример команды для запуска: ```java -jar build/RunJPF.jar  src/examples/HelloWorld.jpf```

Комментарий: работает неплохо на тех примерах, что есть. Можно для чего-то использовать, но нужно отдельно настраивать. В некоторых местах может понадобиться менять код чтобы что-то запустить, подходит для разработки, но не для проверки откуда-то взятого кода. Для текущей задачи не подходит ешё как минимум потому, что запускается на одном файле и не выдерживает что-то больше. Инструменты, основанные на нём имеют в основном те же недостатки.

## [Symbolic (Java) PathFinder](https://github.com/SymbolicPathFinder/jpf-symbc)

Расширение [Java Pathfinder](https://github.com/javapathfinder/jpf-core). Позволяет использовать символьное исполнение для примитивов. Можно выбирать солвер (опять же в `jpf`), метод, которым работает какая-то часть и настройки запуска.

пример `*.jpf` - https://github.com/SymbolicPathFinder/jpf-symbc/blob/master/src/examples/strings/GoodbyeWorld.jpf

```
target=strings.GoodbyeWorld
classpath=${jpf-symbc}/build/examples
sourcepath=${jpf-symbc}/src/examples

#symbolic.dp=choco
symbolic.strings=true
symbolic.string_dp=z3str2
symbolic.string_dp_timeout_ms=3000
symbolic.debug=true

symbolic.method= strings.GoodbyeWorld.hello(sym)
search.depth_limit = 10
search.multiple_errors=true
listener = gov.nasa.jpf.symbc.sequences.SymbolicSequenceListener
#listener = sidechannel.TimingChannelListener
vm.storage.class=nil
```

Пример команды для запуска: `LD_LIBRARY_PATH=/home/andrew/jpf/jpf-symbc/lib/ java -Xmx1024m -ea -jar /home/andrew/jpf/jpf-core/build/RunJPF.jar src/examples/strings/GoodbyeWorld.jpf`

Не заработало если пытаться что-то делать со строками - просто не заканчивает выполняться. С некоторыми примерами (без символьного выполнения строк) работало.

## [JDart](https://github.com/psycopaths/jdart)

Расширение [Java Pathfinder](https://github.com/javapathfinder/jpf-core). Concolic execution. Нужно отдельно ставить [jConstraints](https://github.com/psycopaths/jConstraints) - абстракция для работы с солвером, сам солвер. Работает скорее всего аналогично [Symbolic (Java) PathFinder](https://github.com/SymbolicPathFinder/jpf-symbc).

## [CATG](https://github.com/ksen007/janala2)

Инструмент для создания unit тестов.

Выдал просто аргументы и ещё что-то не очень понятное. 

Нужно какое-то дописывание - есть странные конструкции по типу `CATG.readInt` и во всех примерах нужен `main` - начинается с него.

Запустить можно как и написано в `readme` (комментарий: у меня не собралось и пришлось переделывать и писать свою сборку для части файлов и скрипт запуска тоже переписать немного)

Не заработала одна из зависимостей (`emma`)

BSD-2-Clause license

## [LimeTB](http://www.tcs.hut.fi/Software/lime/)

Набор инструментов для тестирования `java` кода.

Не нашлась зависимость `boolector-1.4-ffc2089-100608.tar.gz`, с другими версиями, другими способами не запустилось.

Вроде бы надо дополнительно специфицировать примеры для проверки.

## [Acteve](https://code.google.com/archive/p/acteve/)

Concolic execution для Android.

Архив с частью кода без комментариев о том что это и как с этим работать.

## [jCUTE]()

Инструмент для генерации модульных тестов. 

Вроде бы работает только для примитивов, максимально простой (комментарий: не запускал, просто по коду)

Очень плохо с лицензией. Вообще не публичный.

Очень давно заброшен.

## [jFuzz](http://people.csail.mit.edu/akiezun/jfuzz/)

Расширение [Java Pathfinder](https://github.com/javapathfinder/jpf-core). 

Фазинг, очень странно работает (судя по описанию)

## [jbse](https://github.com/pietrobraione/jbse)

Программа для анализа `java` кода в производственной среде.

Есть очень хороший [мануал](https://jbse-manual.readthedocs.io/en/latest/)

Можно запустить из кода. [пример](https://github.com/pietrobraione/jbse-examples/blob/master/src/symbols/string/RunStringDemo.java).

Много настроек, однако понятных.

Намного приятнее других инструментов и по крайней мере работает неплохо.

На небольшом файле уже может не работать (проблемы с памятью). В целом работает не очень быстро.

Очень приятный [формат вывода](https://jbse-manual.readthedocs.io/en/latest/text/getting.html)

Нет каких-то больших надстроек (как в `utbot`)

Плохо с лицензией: GPL-3.0 license

## [key](https://www.key-project.org/)

Отладчик через символьное исполнение + спецификация для проверки (комментарий: инструмент вообще не по теме, выглядит интересно, но неприменимо) 

## Комментарии

- Со сборкой инструментов было, как правило, не очень. Почти везде надо было переписывать файл для сборки. Некоторое вообще не собралось

- У многих из них неприятные лицензии

- Работа со строками на том же уровне, что и в `utbot` или ещё хуже, что чаще (или по крайней мере не удалось настроить чтобы она работала лучше)

- Почти все проекты заброшены

- Нет ничего похожего на встроенные моки (как в `utbot`)

- Как правило, инструменты предназначены для одного файла. Некоторые и что-то небольшое не выдерживают (`java heap space` или не заканчиваются)

- Требуют дополнительную нетривиальную настройку, нельзя просто запустить на чём-то

- Получаемые условия на пути намного более интерпретируемые по сравнению с `utbot`

- Многие из инструментов - "`JVM` с проверками"

## Итог

Из этих инструментов можно обратить внимание на [jbse](https://github.com/pietrobraione/jbse) (хотя у него всё равно очень много проблем). Для какой-нибудь задачи может быть подойдёт.

Может быть найдётся где-то применение для [Java Pathfinder](https://github.com/javapathfinder/jpf-core) и его улучшений (но это очень специфичная задача). Для текущей - точно нет.

Всё остальное, кажется, лучше в принципе не трогать.
