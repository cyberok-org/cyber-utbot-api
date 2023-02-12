package org.cyber.utbot.api.utils.overrides

import org.cyber.utbot.api.utils.ASSERT_CLASS_NAME
import org.cyber.utbot.api.utils.ASSERT_FUNCTION_NAME
import org.cyber.utbot.api.utils.annotations.CyberModify
import org.cyber.utbot.api.utils.annotations.CyberNew
import org.utbot.framework.codegen.CodeGenerator
import org.utbot.framework.codegen.CodeGeneratorResult
import org.utbot.framework.codegen.domain.*
import org.utbot.framework.codegen.domain.models.*
import org.utbot.framework.codegen.renderer.CgAbstractRenderer
import org.utbot.framework.codegen.services.language.CgLanguageAssistant
import org.utbot.framework.codegen.tree.CgTestClassConstructor
import org.utbot.framework.codegen.tree.ututils.UtilClassKind
import org.utbot.framework.codegen.util.stringLiteral
import org.utbot.framework.plugin.api.*
import soot.jimple.internal.JInvokeStmt
import soot.jimple.internal.JVirtualInvokeExpr
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter

@CyberModify("org/utbot/framework/codegen/CodeGenerator.kt", "add test annotations about vulnerability")
class CyberCodeGenerator(
    classUnderTest: ClassId,
    paramNames: MutableMap<ExecutableId, List<String>> = mutableMapOf(),
    generateUtilClassFile: Boolean = false,
    testFramework: TestFramework = TestFramework.defaultItem,
    mockFramework: MockFramework = MockFramework.defaultItem,
    staticsMocking: StaticsMocking = StaticsMocking.defaultItem,
    forceStaticMocking: ForceStaticMocking = ForceStaticMocking.defaultItem,
    generateWarningsForStaticMocking: Boolean = true,
    codegenLanguage: CodegenLanguage = CodegenLanguage.defaultItem,
    cgLanguageAssistant: CgLanguageAssistant = CgLanguageAssistant.getByCodegenLanguage(CodegenLanguage.defaultItem),
    parameterizedTestSource: ParametrizedTestSource = ParametrizedTestSource.defaultItem,
    runtimeExceptionTestsBehaviour: RuntimeExceptionTestsBehaviour = RuntimeExceptionTestsBehaviour.defaultItem,
    hangingTestsTimeout: HangingTestsTimeout = HangingTestsTimeout(),
    enableTestsTimeout: Boolean = true,
    testClassPackageName: String = classUnderTest.packageName
): CodeGenerator(classUnderTest, paramNames, generateUtilClassFile, testFramework, mockFramework, staticsMocking, forceStaticMocking,
    generateWarningsForStaticMocking, codegenLanguage, cgLanguageAssistant, parameterizedTestSource, runtimeExceptionTestsBehaviour,
    hangingTestsTimeout, enableTestsTimeout, testClassPackageName) {
    @CyberNew("get last assert")
    private fun List<Step>.lastAssertOrNull(): Step? = this.dropLastWhile {
        ((it.stmt as? JInvokeStmt)?.invokeExprBox?.value as? JVirtualInvokeExpr)?.methodRef?.run {
            !(name == ASSERT_FUNCTION_NAME && declaringClass.name == ASSERT_CLASS_NAME)
        } ?: true
    }.lastOrNull()

    @CyberNew("add information about vulnerability")
    private fun addAnnotations(cgClassFile: CgClassFile, testSets: List<CgMethodTestSet>): CgClassFile {
        val msgByMethodName = testSets
            .flatMap { it.executions.toMutableList() }
            .map { it.testMethodName to ((it as? UtSymbolicExecution)?.fullPath?.lastAssertOrNull()?.stmt?.invokeExprBox?.value
                    as? JVirtualInvokeExpr)?.args?.first()?.toString()?.run { substring(1, this.length - 1) } }
            .filter { it.second != null }
            .toMap()
        cgClassFile.declaredClass.body.methodRegions.map { cluster ->
            cluster.content.map { region ->
                region.content.map { method ->
                    msgByMethodName[method.name]?.run {
                        method.annotations = method.annotations.toMutableList().also { it.add(CgSingleArgAnnotation(CyberOk.vulnerability, stringLiteral(this))) }
                    }
                }
            }
        }
        return cgClassFile
    }

    override fun generateAsStringWithTestReport(
        testSets: Collection<UtMethodTestSet>,
        testClassCustomName: String?,
    ): CodeGeneratorResult {
        val cgTestSets = testSets.map { CgMethodTestSet(it) }.toList()
        return withCustomContext(testClassCustomName) {
            context.withTestClassFileScope {
                val astConstructor = CgTestClassConstructor(context)
                val renderer = CgAbstractRenderer.makeRenderer(context)
                val testClassModel = TestClassModel.fromTestSets(classUnderTest, cgTestSets)

                fun now() = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss.SSS"))

                logger.info { "Code generation phase started at ${now()}" }
                val testClassFile = astConstructor.construct(testClassModel).run { @CyberNew("add annotations") addAnnotations(this, cgTestSets) }
                logger.info { "Code generation phase finished at ${now()}" }

                logger.info { "Rendering phase started at ${now()}" }
                testClassFile.accept(renderer)
                logger.info { "Rendering phase finished at ${now()}" }

                CodeGeneratorResult(
                    generatedCode = renderer.toString(),
                    utilClassKind = UtilClassKind.fromCgContextOrNull(context),
                    testsGenerationReport = astConstructor.testsGenerationReport
                )
            }
        }
    }
}
