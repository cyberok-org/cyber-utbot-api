# [InterProceduralUnitGraph](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/InterProceduralUnitGraph.kt)

Вершины графа - _Stmt_, рёбра - [Edge](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/state/ExecutionState.kt) - имеет _src_ _Stmt_, _dst_ _Stmt_ и _decisionNum_ (номер, позволяющий идентифицировать конкретно это ребро между _Stmt_).

Содержит в качестве основы _ExceptionalUnitGraph_ и вспомогательные вещи. Какие-то из них:

_method_ - возвращает _SootMethod_ по _Stmt_.

_succStmts, succ, ..._ - несколько методов, возвращающих ребра после вершины с текущим _Stmt_.

_join_ - присоединяет граф к текущему _Stmt_. Необходимость для этого может возникать при создании синтетического метода с вызовом инициализатора, при переопределении какого-то вызова (например _ArrayList_), в случае многомерных массивов и прочее.

_traversed_ - помечает состояние как пройденное (принимает [ExecutionState](ExecutionState.md)). 

так же есть методы для покрытия, посещения рёбер, вершин и не только.
