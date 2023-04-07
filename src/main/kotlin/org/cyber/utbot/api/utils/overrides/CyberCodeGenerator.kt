package org.cyber.utbot.api.utils.overrides

import org.cyber.utbot.api.utils.ASSERT_CLASS_NAME
import org.cyber.utbot.api.utils.ASSERT_FUNCTION_NAME
import org.cyber.utbot.api.utils.additions.classState.codeGeneration.CodeGen
import org.cyber.utbot.api.utils.annotations.CyberModify
import org.cyber.utbot.api.utils.annotations.CyberNew
import org.utbot.framework.codegen.CodeGenerator
import org.utbot.framework.codegen.CodeGeneratorResult
import org.utbot.framework.codegen.domain.*
import org.utbot.framework.codegen.domain.models.*
import org.utbot.framework.codegen.domain.models.builders.SimpleTestClassModelBuilder
import org.utbot.framework.codegen.services.language.CgLanguageAssistant
import org.utbot.framework.codegen.tree.CgComponents
import org.utbot.framework.codegen.tree.CgSimpleTestClassConstructor
import org.utbot.framework.codegen.tree.ututils.UtilClassKind
import org.utbot.framework.codegen.util.stringLiteral
import org.utbot.framework.plugin.api.*
import soot.jimple.internal.JInvokeStmt
import soot.jimple.internal.JStaticInvokeExpr

@CyberModify("org/utbot/framework/codegen/CodeGenerator.kt", "add test annotations about vulnerability")
class CyberCodeGenerator(
    classUnderTest: ClassId,
    projectType: ProjectType,
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
    testClassPackageName: String = classUnderTest.packageName,
    private val codeGen: CodeGen?
): CodeGenerator(classUnderTest, projectType, paramNames, generateUtilClassFile, testFramework, mockFramework, staticsMocking, forceStaticMocking,
    generateWarningsForStaticMocking, codegenLanguage, cgLanguageAssistant, parameterizedTestSource, runtimeExceptionTestsBehaviour,
    hangingTestsTimeout, enableTestsTimeout, testClassPackageName) {
    @CyberNew("get last assert")
    private fun List<Step>.lastAssertOrNull(): Step? = this.dropLastWhile {
        ((it.stmt as? JInvokeStmt)?.invokeExprBox?.value as? JStaticInvokeExpr)?.methodRef?.run {
            !(name == ASSERT_FUNCTION_NAME && declaringClass.name == ASSERT_CLASS_NAME)
        } ?: true
    }.lastOrNull()

    @CyberNew("add information about vulnerability")
    private fun addAnnotations(cgClassFile: CgClassFile, testSets: List<CgMethodTestSet>): CgClassFile {
        val msgByMethodName = testSets
            .flatMap { it.executions.toMutableList() }
            .map { it.testMethodName to ((it as? UtSymbolicExecution)?.fullPath?.lastAssertOrNull()?.stmt?.invokeExprBox?.value
                    as? JStaticInvokeExpr)?.args?.first()?.toString()?.run { substring(1, this.length - 1) } }
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

    override fun generateForSimpleClass(testSets: List<CgMethodTestSet>): CodeGeneratorResult {
        val astConstructor = CgSimpleTestClassConstructor(context)
        val testClassModel = SimpleTestClassModelBuilder(context).createTestClassModel(classUnderTest, testSets)

        logger.info { "Code generation phase started at ${now()}" }
        val testClassFile = astConstructor.construct(testClassModel).run { @CyberNew("add annotations") addAnnotations(this, testSets) }
        logger.info { "Code generation phase finished at ${now()}" }

        @CyberNew("additional generation") codeGen?.run {
            val variableConstructor = CgComponents.getVariableConstructorBy(context) // FIXME

            val stateBeforeToName = mutableMapOf<String, EnvironmentModels>()

            testSets.forEach { methods ->
                methods.executions.forEach {
                    stateBeforeToName[it.testMethodName!!] = it.stateBefore // FIXME !!
                }
            }

            testClassFile.declaredClass.body.methodRegions.forEach { cluster ->
                cluster.content.forEach { region ->
                    region.content.forEach { method ->
                        method.statements = generate(stateBeforeToName[method.name]!!, method.statements) { model: UtModel, name: String? ->    // FIXME !!
                            variableConstructor.getOrCreateVariable(model, name)
                        }
                    }
                }
            }
        }

        val generatedCode = renderToString(testClassFile)

        return CodeGeneratorResult(
            generatedCode = generatedCode,
            utilClassKind = UtilClassKind.fromCgContextOrNull(context),
            testsGenerationReport = astConstructor.testsGenerationReport
        )
    }
}
