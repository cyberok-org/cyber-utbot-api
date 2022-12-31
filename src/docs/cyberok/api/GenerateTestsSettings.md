# [GenerateTestsSettings](../../../main/kotlin/org/cyber/utbot/api/GenerateTestsSettings.kt)

### classpath: String? = null

Указывает путь к классам по умолчанию (его можно переопределить при запуске тестирования в **cyber-utbot-api** через одноимённый параметр) для тестируемого класса. Например, это может быть _build/classes/java/main_

### mockStrategy: [MockStrategyApi](../../../../../utbot-framework-api/src/main/kotlin/org/utbot/framework/plugin/api/Api.kt) = MockStrategyApi.NO_MOCKS

Определяет _mock_ стратегию. По умолчанию запускается без них.

[//]: # (TODO за что конкретно отвечает)

### testFrameworkGen: [TestFrameworkGen](../../../main/kotlin/org/cyber/utbot/api/GenerateTestsSettings.kt) = TestFrameworkGen.JUNIT5

Используемый фреймворк для тестирования. Переводится в соответсвующее значение изнутри [TestFramework](../../../../../utbot-framework/src/main/kotlin/org/utbot/framework/codegen/Domain.kt)

### mockStatics: [StaticsMocking](../../../../../utbot-framework/src/main/kotlin/org/utbot/framework/codegen/Domain.kt) = StaticsMocking.defaultItem

Выбирает фреймворк _mock_ для статических методов (или не использовать _mock_ для статических методов вообще)

[//]: # (TODO проверить)

### forceStaticMocking: [ForceStaticMocking](../../../../../utbot-framework/src/main/kotlin/org/utbot/framework/codegen/Domain.kt) = ForceStaticMocking.defaultItem

Принудительно использует _mock_ для статических методов и конструкторов для классов из **mockAlways**.

[//]: # (TODO проверить)

### treatOverflowAsError: [TreatOverflowAsError](../../../../../utbot-framework-api/src/main/kotlin/org/utbot/framework/plugin/api/Api.kt) = TreatOverflowAsError.defaultItem

Отвечает за то надо ли кидать ошибку при переполнении.

[//]: # (TODO проверить)

### mockAlways: Iterable<String> = [MOCK_ALWAYS_DEFAULT](../../../main/kotlin/org/cyber/utbot/api/GenerateTestsSettings.kt)

**fully qualified name** классов, чтобы принудительно использовать _mock_ для их статических методов и конструкторов (можно использовать его несколько раз, чтобы предоставить несколько классов)

[//]: # (TODO проверить)

### generationTimeout: Long = [LONG_GENERATION_TIMEOUT](../../../main/kotlin/org/cyber/utbot/api/GenerateTestsSettings.kt)

Указывает максимальное время в миллисекундах, для генерации тестов.

### projectRoot: String? = null

Указывает **root of the relative paths** в отчете **sarif**, необходимый для правильного отображения ссылок; можно установить автоматически

[//]: # (TODO проверить)

### sarifReport: String?

Указывает выходной файл для отчета статического анализа

[//]: # (TODO проверить)

### codegenLanguage: [CodegenLanguage](../../../../../utbot-framework-api/src/main/kotlin/org/utbot/framework/plugin/api/Api.kt) = CodegenLanguage.defaultItem

Определяет язык кодогенерации
