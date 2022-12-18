# [UtSolver](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/pc/UtSolver.kt)

Содержит в себе:
- [TypeRegistry](Types.md).
- Context Z3.
- Params - параметры для Z3.
- [BaseQuery](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/pc/Query.kt) - базовый класс, представляющий неизменяемый запрос ограничений к Z3.
- [Assumption](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/symbolic/SymbolicStateUpdate.kt).
- _hardConstraintsNotYetAddedToZ3Solver_ - новые ограничения для Z3.
- _z3Solver_: Solver.
- translator: [Z3TranslatorVisitor](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/pc/Z3TranslatorVisitor.kt) - переводит [UtExpression](UtExpression.md) в _Expr_ Z3.
- failedAssumptions - невыполнимые ограничения из [Assumption](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/symbolic/SymbolicStateUpdate.kt) выше.

Есть так же другие вспомогательные вещи.

_add_ - добавляет [HardConstraint](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/symbolic/SymbolicStateUpdate.kt), [SoftConstraint](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/symbolic/SymbolicStateUpdate.kt) и [Assumption](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/symbolic/SymbolicStateUpdate.kt) к текущему инстансу, возвращают копию _UtSolver_.

_check_ - проверяет всё на _z3Solver_.

[док по assumption из **utbot**](../../../../docs/AssumptionsMechanism.md)
