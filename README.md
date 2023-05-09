# cyber-utbot-api
## Abstract

[UTBot](https://github.com/UnitTestBot/UTBotJava) - UnitTestBot is the tool for automated unit test generation and precise code analysis.

`cyber-utbot-api` - обёртка над `utbot`, позволяющая запускать его из кода.

Она работает по аналогии с `utbot-cli`, в ней отдельно вынесены настройки для `cli`, можно задать настройки самого `utbot` внутри.

Основная функциональность: генерирует тесты для уязвимостей (какие именно зависит от базы знаний), работает аккуратнее и лучше в некоторых специфичных местах (например есть встроенная улучшенная поддержка `javax.servlet`)

## Build

```bash
git clone https://github.com/UnitTestBot/UTBotJava
cd UTBotJava/
git checkout fe0b89c789da5559bf8912e0179cdcee026e6137
git clone https://github.com/cyberok-org/cyber-utbot-api
```

После стоит открыть `ide` и попытаться собрать. Потом приминить `patch` командной ниже и ещё раз собрать.

```bash
cd cyber-utbot-api/scripts/
bash patch_apply.sh
```

Дальше всё собирается командой (есть внутренние тесты для примеров которые и должны падать)

```gradle
gradle build -x test
```

### Knowledge base

Для проекта есть база знаний (приватная), её можно тоже склонировать, если есть доступ (**cyber-utbot-exploit-base**).

## Run

### Fast start

Запустить `main` в [Simple](src/main/kotlin/org/cyber/utbot/Simple.kt).

### Simple

[Simple](src/main/kotlin/org/cyber/utbot/Simple.kt) - `main` с которого можно из кода запускать `utbot` (упрощенный, для демонстрации)

[VulnerabilityChecker](src/test/kotlin/VulnerabilityChecker.kt) - тот же пример что и в [Simple](src/main/kotlin/org/cyber/utbot/Simple.kt) с использованием `extraVulnerabilityChecks` и явным заданием базы знаний из кода.

### Main

[Application](src/main/kotlin/org/cyber/utbot/Application.kt) - `main` с которого можно из кода запускать `utbot`.

[ReportCreator](src/main/kotlin/org/cyber/utbot/ReportCreator.kt) - заготовка для запуска бенчмарков.

[Generator](src/test/kotlin/Generator.kt), [ConstraintsViewer](src/test/kotlin/ConstraintsViewer.kt) - Другой способ, возможно более удобный, делать это через тесты.

Запуск в общем случае:

- Предварительно стоит добавить эти **VM options** в конфигурацию запуска.

    ```
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

- Заменить переменную **classpath** (там должен быть `build` текущего проекта (_build/classes/java/main_), `build` тестируемого проекта (например _/home/andrew/ex/build/classes/java/main_) и пути до нужных `jar` (например _/home/andrew/.jdks/openjdk-17.0.2/bin/javax.servlet-api-3.1.0.jar_))

- Выбрать нужную базу и задать настройки. В настройках запуска - классе `GenerateTestsSettings` можно указать путь до базы знаний в `vulnerabilityCheckDirectories`. [Application](src/main/kotlin/org/cyber/utbot/Application.kt) настроен на (**cyber-utbot-exploit-base**). Дефолтно задана [база](src/base) для примера

- Запустить (например [Application](src/main/kotlin/org/cyber/utbot/Application.kt))

## Details

Пока что стоит собирать сторонний проект добавив, скопировав туда папку [cyber-utbot-exploit-base/src/main/java/org/cyber](../cyber-utbot-exploit-base/src/main/java/org/cyber). Это временная мера. Копируются туда проверочные функции и часть для корректной работы `cyber-utbot-api`. Если нет доступа к приватной базе можно скопировать [org/cyber](org/cyber) - содержит то же самое, но базовое, для примера.

Подробнее про формат проверок можно почитать [тут](src/docs/cyberok/api/VulnerabilityChecks.md)

## Docs

[brief](src/docs/brief.md) - доки по всему. Там есть описания для:
- `cyberok.api` - доки по настройкам, стандарту базы знаний и механизму проверки.
- `cyberok.test` - доки по спецификации тестов (формат хранения), концепция приложения для сравнения разных инструментов/версий одного инструмента. 
- `utbot` - внутренние доки по `utbot`, [utbot scheme](src/docs/utbot/Scheme.md) - схема работы `utbot`
- `abstract` - что-то отдельное, не относящаяся к теме, но полезное при изучении.

## Tests

[testcases](src/main/java/org/testcases) - папка с примерами (запускается на `utbot`).

[test](src/test/java/org/testcases) - папка с результатами запуска тестов.

[testcasesGen](src/main/java/org/testcasesGen) - папка c примерами в предполагаемом формате хранения. Спецификация формата хранения - [test format](src/docs/cyberok/test/TestFormatSpecification.md) (текущие примеры пока не удовлетворяют требованием спецификации).
