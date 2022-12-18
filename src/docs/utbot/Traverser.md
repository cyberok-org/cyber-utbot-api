# [Traverser](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/Traverser.kt)

Этапы создания:
- создаёт и хранит в себe:
  - _visitedStmts_.
  - [Environment](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/DataClasses.kt). - [SootMethod](../abstract/Soot.md) и [ExecutionState](ExecutionState.md).
  - queuedSymbolicStateUpdates: [SymbolicStateUpdate](SymbolicStateUpdate.md).
  - [Simplificator](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/pc/Simplificator.kt) - выполняет перезаписи выражений с конкретными значениями. И от него же [MemoryUpdateSimplificator](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/simplificators/MemoryUpdateSimplificator.kt).
  - и некоторые вспомогательные вещи.

_traverseStmt_ - берёт _Stmt_ и в зависимости от его типа делегирует соответствующему траверсеру.

_traverseException_ - берёт _Stmt_ и [SymbolicFailure](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/DataClasses.kt) и действует по разному в зависимости от того есть ли _catch_ блок.

Этапы _traverse_:
- создаёт [TraversalContext](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/TraversalContext.kt) - содержит изменяемый контекст [ExecutionState](ExecutionState.md) во время обхода одной [jimple](../abstract/Jimple.md) инструкции.
- берёт [ExecutionState](ExecutionState.md) с которым его запустили, берёт из него _Stmt_.
- обновляет с ним [Environment](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/DataClasses.kt).
- берёт последнее ребро графа из [ExecutionState](ExecutionState.md) и посещает его (если оно существует).
- вызывает _traverseException_ при ошибке, _traverseStmt_ иначе.
- очищает queuedSymbolicStateUpdates (приравнивает его пустому [SymbolicStateUpdate](SymbolicStateUpdate.md)).
- возвращает следующие состояние, полученные из [TraversalContext](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/TraversalContext.kt).

Этапы _traverseIfStmt_ (много подобных, взят для примера):
- получает оба ребра из этого _Stmt_ (используя _globalGraph_: [InterProceduralUnitGraph](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/InterProceduralUnitGraph.kt)).
- получает [ResolvedCondition](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/DataClasses.kt) - содержит само условие, ограничения, что мы хотим добавить и [SymbolicStateUpdateForResolvedCondition](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/DataClasses.kt) обновление состояния в положительном и отрицательном случаях.
- получает из этого [ResolvedCondition](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/DataClasses.kt) ограничения пути (_path constraint_) в положительном и отрицательном случаях и аналогично мягкие ограничения (_soft constraint_).
- проверяется _isAssumeExpr_ (лучше почитать что это в комментарии к коду).
- получает _positiveCaseState_: [ExecutionState](ExecutionState.md), копируя текущее состояние и вызывая обновление [update](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/state/ExecutionStateUpdates.kt) по ребру и [SymbolicStateUpdate](SymbolicStateUpdate.md).
- и добавляет полученный _positiveCaseState_ в [TraversalContext](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/TraversalContext.kt), от которого и запускалась сама функция (_traverseIfStmt_).
- далее делает почти аналогичное для отрицательного случая и _negativeCaseState_.
