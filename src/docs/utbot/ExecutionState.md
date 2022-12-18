# [ExecutionState](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/state/ExecutionState.kt)

Ключевая сущность для хранения текущего состояния выполнения.

Содержит в себе:
- _Stmt_ - условно инструкция в [jimple](../abstract/Jimple.md).
- [SymbolicState](SymbolicState.md) - хранит текущее символьное состояние.
- _executionStack_: _PersistentList< [ExecutionStackElement](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/state/ExecutionStackElement.kt) >_.
- _path_: _PersistentList< Stmt >_ - путь из _Stmt_ до текущего состояния.
- _visitedStatementsHashesToCountInPath_: _PersistentMap<Int, Int>_ - хранит сколько раз каждая инструкция есть в _path_.
- _decisionPath_: _PersistentList < Int >_ - хранит _decisionNum_ рёбер на пути (условно номер пропорциональный времени создания ребра, id). 
- _edges_: _PersistentSet< Edge >_ - все разобранные рёбра в состоянии.
- _stmts_: _PersistentMap<Stmt, Int>_ - по _Stmt_ выдаёт длину пути с ним (для упорядоченности).
- _pathLength_ - текущая длина пути.
- _lastEdge_ - последнее ребро.
- _lastMethod_: [SootMethod](../abstract/Soot.md)? - последний метод.
- _methodResult_: [MethodResult](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/DataClasses.kt)? - результат вызова метода _Success/Failure_ и [SymbolicStateUpdate](SymbolicStateUpdate.md)
- _exception_: [SymbolicFailure](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/DataClasses.kt)?.
- _label_: [StateLabel](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/state/ExecutionState.kt).
- _StateAnalyticsProperties_ - информация о состоянии выполнения для аналитики.

Форвардит [UtSolver](UtSolver.md) и [Memory](Memory.md) в [SymbolicState](SymbolicState.md).

Содержит вспомогательные функции.
