package org.cyber.utbot.api.utils.overrides

import kotlinx.collections.immutable.persistentListOf
import org.cyber.utbot.api.utils.annotations.CyberModify
import org.cyber.utbot.api.utils.annotations.CyberNotModify
import org.utbot.engine.*
import org.utbot.engine.util.mockListeners.MockListenerController
import org.utbot.framework.plugin.api.ApplicationContext
import org.utbot.framework.plugin.api.ClassId
import org.utbot.framework.plugin.api.id
import org.utbot.framework.util.executableId
import soot.RefType
import soot.SootMethod

class CyberMocker(
    strategy: MockStrategy,
    classUnderTest: ClassId,
    hierarchy: Hierarchy,
    chosenClassesToMockAlways: Set<ClassId>,
    mockListenerController: MockListenerController? = null,
    applicationContext: ApplicationContext,
) : Mocker(strategy, classUnderTest, hierarchy, chosenClassesToMockAlways, mockListenerController, applicationContext) {
    @CyberModify("org/utbot/engine/Mocks.kt", "create own CyberUtMockWrapper")
    override fun mock(type: RefType, mockInfo: UtMockInfo): MockedObjectInfo {
        val objectValue = if (shouldMock(type, mockInfo)) createMockObject(type, mockInfo) else null
        return construct(objectValue, mockInfo)
    }

    @CyberModify("org/utbot/engine/Mocks.kt", "create own CyberUtMockWrapper")
    override fun forceMock(type: RefType, mockInfo: UtMockInfo): MockedObjectInfo {
        mockListenerController?.onShouldMock(strategy, mockInfo)

        val objectValue = createMockObject(type, mockInfo)
        return construct(objectValue, mockInfo)
    }
}

@CyberNotModify("org/utbot/engine/Mocks.kt", "improve later")
class CyberUtMockWrapper(type: RefType, private val mockInfo: UtMockInfo): UtMockWrapper(type, mockInfo) {
    override fun Traverser.invoke(
        wrapper: ObjectValue,
        method: SootMethod,
        parameters: List<SymbolicValue>
    ): List<InvokeResult> {
        return when (method.name) {
            "<init>" -> listOf(MethodResult(voidValue))
            else -> {
                val mockNumber = nextMockNumber()
                val label = createMockLabel(mockNumber)
                val generator = (method.returnType as? RefType)?.let { type ->
                    UtMockInfoGenerator { mockAddr ->
                        UtObjectMockInfo(type.id, mockAddr)
                    }
                }
                // TODO it's a bug JIRA:1287
                val mockValue = createConst(method.returnType, label, generator)
                val updates =
                    MemoryUpdate(
                        mockInfos = persistentListOf(
                            MockInfoEnriched(
                                mockInfo,
                                mapOf(method.executableId to listOf(MockExecutableInstance(mockNumber, mockValue)))
                            )
                        )
                    )
                listOf(MethodResult(mockValue, memoryUpdates = updates))
            }
        }
    }
}

@CyberModify("org/utbot/engine/Mocks.kt", "create own CyberUtMockWrapper")
private fun createMockObject(type: RefType, mockInfo: UtMockInfo) =
    objectValue(type, mockInfo.addr, CyberUtMockWrapper(type, mockInfo))
