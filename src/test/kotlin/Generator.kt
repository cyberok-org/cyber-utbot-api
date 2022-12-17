import org.cyber.utbot.api.GenerateTestsSettings
import org.cyber.utbot.api.TestGenerator
import org.cyber.utbot.api.utils.GeneratedTests
import org.cyber.utbot.api.utils.TargetQualifiedName
import org.cyber.utbot.api.utils.toTestUnits
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.utbot.common.PathUtil.toPath
import org.utbot.framework.plugin.api.CodegenLanguage
import java.nio.file.Files


class Generator {
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
    fun generateTests() {
        val dir = "org.example"

        val settings = GenerateTestsSettings(classpath, codegenLanguage = CodegenLanguage.JAVA)
        val generator = TestGenerator(settings)
        val tests = generator.runBunch(classpath, dir)
        saveTests(tests)
    }

    @Test
    fun generateTest() {
        val classname = "org.testcases.ifs.Simple"
//        val classname = "org.example.Loop"

        val settings = GenerateTestsSettings(classpath, codegenLanguage = CodegenLanguage.JAVA)
        val generator = TestGenerator(settings)
        val tests = generator.run(mapOf(classname to "$sourceDir/${classname.replace('.', '/')}.java").toTestUnits())
        saveTests(tests)
    }

    @Test
    fun generateTest2() {
        val classname = "org.example.Interprocedural"

        val settings = GenerateTestsSettings(classpath, codegenLanguage = CodegenLanguage.JAVA)
        val generator = TestGenerator(settings)
        val tests = generator.run(mapOf(classname to "$sourceDir/${classname.replace('.', '/')}.java").toTestUnits())
        saveTests(tests)
    }
}
