# Jimple

Это типизированное промежуточное представление с тремя адресами, подходящее для оптимизации, предоставляемое [Soot](http://soot-oss.github.io/soot/)

Подгружает необходимое (например не берёт то, что не нужно для примеров или что не используется) из базовых классов [classesToLoad](../../../../utbot-framework/src/main/kotlin/org/utbot/framework/util/SootUtils.kt) (переписанные стандартные и специфичные)

[JimpleBody](https://www.sable.mcgill.ca/soot/doc/soot/jimple/JimpleBody.html) - Реализация класса [Body](https://www.sable.mcgill.ca/soot/doc/soot/Body.html) для промежуточного представления Jimple.

[Body](https://www.sable.mcgill.ca/soot/doc/soot/Body.html) - Абстрактный базовый класс, моделирующий тело метода Java.

[Jimple](https://www.sable.mcgill.ca/soot/doc/soot/jimple/Jimple.html) - содержит все конструкторы компонентов грамматики Jimple для тела _JimpleBody_.
