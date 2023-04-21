# Brief

Начнём с основных параметров (по крайней мере тех, что есть в **utbot-cli** (все они перенесены в **cyber-utbot-api**, так что будем сразу смотреть на его представление)).

**cyber-utbot-api** - обёртка над **utbot** (аналогичная **utbot-cli**, но предназначенная для вызова из кода и с возможностью расширения в дальнейшем).

## cyber-utbot-api parameters

[GenerateTestsSettings](cyberok/api/GenerateTestsSettings.md).

## Examples

[Generator](../test/kotlin/Generator.kt) - здесь сразу есть примеры для запуска **cyber-utbot-api** в текущем репозитории.

## UTBot inside

Существенная часть из **utbot-framework** (в частности вызываемая из **cyber-utbot-api**) - [TestCaseGenerator](utbot/TestCaseGenerator.md). Стоит начать с неё.

О том где лежат какие-то основополагающие части - [where](utbot/where.md)

Какие-то заметки - [notes](utbot/notes.md)

В целом все доки по **utbot** в этом репозитории - [тут](utbot). Какие-то доки от самого **utbot** - [тут](../../../docs).

## UTBot VM options

Какие-то падения utbot решались добавлением следующих опций для запуска. 

```
VM options (configuration): 
-Xmx1536M
--add-opens=java.base/java.lang=ALL-UNNAMED
--add-opens=java.base/java.lang.reflect=ALL-UNNAMED
--add-opens=java.base/java.io=ALL-UNNAMED
--add-opens java.base/java.util=ALL-UNNAMED
--add-opens java.base/java.nio.file=ALL-UNNAMED
--add-opens java.base/sun.nio.fs=ALL-UNNAMED
--add-opens java.base/java.nio.file.spi=ALL-UNNAMED
--add-opens java.desktop/sun.awt=ALL-UNNAMED
--add-opens java.base/java.nio.charset=ALL-UNNAMED
--add-opens java.base/java.lang=ALL-UNNAMED
--add-opens java.base/java.lang.ref=ALL-UNNAMED
--add-opens java.base/java.lang.invoke=ALL-UNNAMED
--add-opens java.base/sun.security.util=ALL-UNNAMED
--add-exports=java.base/sun.nio.ch=ALL-UNNAMED
--add-exports=jdk.unsupported/sun.misc=ALL-UNNAMED
--add-exports=java.base/sun.nio.ch=ALL-UNNAMED
--add-exports=java.base/sun.nio.fs=ALL-UNNAMED
--add-exports=java.base/sun.nio.cs=ALL-UNNAMED
```
