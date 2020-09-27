# Test Técnico java

## Diagrama UML
![Diagrama UML](uml_diagram.png?raw=true)

## Documentación
Cada clase y método se encuentra comentariada (JavaDoc) explicando sus parámetros, lo que devuelve, en caso de hacerlo
y además se diseñó un diagrama UML con el plugin para IntellIJidea simpleUML

## Puntos extras
* En caso de que no exista ningún empleado libre, la llamada entrante será almacenada en una lista enlazada en forma de cola FIFO, para luego ser atendida cuando se libere algún empleado.

* En caso de que lleguen más de 10 llamadas concurrentes, se procederá de igual forma que el punto anterior.

## Nota
El código no es lo más eficiente posible con el trabajo multithread, quizás haciendo uso de javaRx, podría ser que resultara más eficiente, pero por falta de experiencia en esta materia decidí hacerlo de la forma convencional utilizando el API Executor.



