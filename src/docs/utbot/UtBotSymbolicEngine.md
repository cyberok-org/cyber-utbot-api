# [UtBotSymbolicEngine](../../../../utbot-framework/src/main/kotlin/org/utbot/engine/UtBotSymbolicEngine.kt)

Джижок, центральная сущность. Запускается на одном методе.

Этапы создания:
- берёт [soot](../abstract/Soot.md) представление метода, переводит его в [Jimple](../abstract/Jimple.md) вид и строит по нему граф (_ExceptionalUnitGraph_).
- строит надстройку над эти графом - _globalGraph_: [InterProceduralUnitGraph](InterProceduralUnitGraph.md).
- создаёт нужные сущности для типизации, описаны в [Types](Types.md).
- создаёт [PathSelector](PathSelector.md)
- создаёт [Mocker](Mocker.md)
- создаёт [Traverser](Traverser.md)
- создаёт [ConcreteExecutor](ConcreteExecutor.md)
- создаёт [FeatureProcessor](../../../../utbot-framework/src/main/kotlin/org/utbot/analytics/FeatureProcessor.kt), trackableResources - они для очищения после выполнения _traverse_.

Этапы _traverse_:
- создаёт _initStmt_ по вершине _globalGraph_ (_Stmt_ - условно инструкция в [jimple](../abstract/Jimple.md)).
- создаёт [ExecutionState](ExecutionState.md) по _initStmt_, [SymbolicState](SymbolicState.md) и _SootMethod_.
- добавляет _initState_ в [PathSelector](PathSelector.md).
- после этого в цикле (пока активен контекст корутины (не вышло время выполнения, например)).
  - если включено конкретное исполнение (_executeConcretely_) или есть что исполнять конкретно. 
    - так и исполняет (если нет состояния _SAT_, выходит, т.е. перебирает все значения до него в _pollUntilSat_), используя [ConcreteExecutor](ConcreteExecutor.md), получает в итоге [UtSymbolicExecution](../../../../utbot-framework-api/src/main/kotlin/org/utbot/framework/plugin/api/Api.kt).
  - иначе
    - берёт состояние [ExecutionState](ExecutionState.md), предложенное [PathSelector](PathSelector.md) (если его нет, пропускает все шаги дальше (могли остаться состояния для конкретного исполнения)).
    - запускает _traverse_ из [Traverser](Traverser.md), который возвращает _Collection< ExecutionState >_. 
    - каждое полученное значение соответствующе обрабатывается (добавляется для разбора в конкретные состояния, если это что-то конкретное, добавляется в [PathSelector](PathSelector.md), если промежуточное и сохраняется и делает дополнительные действия, если терминальное).

про фазинг - [fuzzing](fuzzing.md)

Этапы _fuzzing_:
- подготовка, проверка что можно в принципе фазить функцию, вытаскивание имён аргументов (их фазим), ...
- инициализация фазера, подключение нужных `ModelProvider`, поиск констант - [FuzzerFunctions](../../../../utbot-framework/src/main/kotlin/org/utbot/fuzzer/FuzzerFunctions.kt) collectConstantsForFuzzer.
- потом в цикле смотрим на полученные значения (ровно так и описано в комментарии)
  1. serializing/deserializing it with kryo
  2. sending over rd
  3. concrete execution itself
  4. analyzing concrete result
