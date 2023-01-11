import org.cyber.utbot.api.GenerateTestsSettings
import org.cyber.utbot.api.TestGenerator
import org.cyber.utbot.api.utils.GeneratedTests
import org.cyber.utbot.api.utils.TargetQualifiedName
import org.cyber.utbot.api.utils.printJson
import org.cyber.utbot.api.utils.toTestUnits
import org.cyber.utbot.api.utils.viewers.UTBotViewers
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.utbot.common.PathUtil.toPath
import org.utbot.framework.plugin.api.CodegenLanguage
import java.nio.file.Files


class ConstraintsViewer {
    private val postfix = "Test.java"
    private val saveDirPrefix = "src/test/java"
    private val classpath = "build/classes/java/main"
    private val sourceDir = "src/main/java"

    @BeforeEach
    fun init() {
    }
    private fun saveTests(tests: Map<TargetQualifiedName, GeneratedTests>) {
        tests.forEach { nameAndTest ->
            val fullname = nameAndTest.key.replace(".java", "")
            val name = fullname.substringAfterLast(".")
            val path = fullname.substringBeforeLast(".").replace('.', '/')
            val saveDir = "$saveDirPrefix/$path"
            Files.createDirectories(saveDir.toPath());
            Files.write("$saveDir/$name$postfix".toPath(), listOf(nameAndTest.value))
        }
    }

    @Test
    fun generateTest() {
        val classname = "org.testcases.ifs.TernaryNested"

        val settings = GenerateTestsSettings(classpath, codegenLanguage = CodegenLanguage.JAVA, generationTimeout = 60_000, withUtSettings = { it.useFuzzing = false })
        val generator = TestGenerator(settings)
        val (tests, info) = generator.run(mapOf(classname to "$sourceDir/${classname.replace('.', '/')}.java").toTestUnits())
        saveTests(tests)
        printJson(info[UTBotViewers.TERMINAL_STATISTIC_VIEWER] as String)
    }
}
