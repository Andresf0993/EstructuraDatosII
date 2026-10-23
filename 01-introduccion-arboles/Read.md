Ventaja 1: representa relaciones entre elementos.

Un árbol permite mostrar qué elementos dependen de otros. Por ejemplo, Universitaria contiene varias asignaturas y Estructura de datos 2 contiene temas como árboles y grafos. Una lista puede guardar esos mismos nombres, pero no representa directamente esas relaciones de padre e hijo.


Ventaja 2: facilita la organización por niveles.

Un árbol permite identificar la raíz, los padres, los hijos y los niveles de profundidad. Esto resulta útil para representar sistemas de archivos, organigramas y menús con submenús. En una lista, tendríamos que establecer reglas adicionales para indicar a qué elemento pertenece cada dato.

Introducción a los árboles en Java

Clasificación de los 4 escenarios

1. Sistema de archivos — Jerárquico. Las carpetas pueden contener archivos y otras carpetas, formando distintos niveles.

2. Organigrama — Jerárquico. Representa los cargos de una organización y las relaciones entre superiores y subordinados.

3. Menú de aplicación — Jerárquico. Las opciones principales pueden contener submenús y opciones secundarias.

4. Árbol genealógico — Jerárquico. Organiza las relaciones familiares entre diferentes generaciones.

Reflexión

Las estructuras de datos permiten organizar información de acuerdo con las relaciones que existen entre sus elementos. Una estructura lineal, como una lista, presenta los datos en una secuencia, mientras que un árbol permite organizarlos en niveles. Esta diferencia es importante cuando necesitamos representar información con relaciones de dependencia.

En esta actividad construí una jerarquía universitaria utilizando la clase NodoGeneral en Java. Cada nodo almacena un dato y una lista de hijos, lo que permite agregar asignaturas y temas en distintos niveles. Por ejemplo, la universidad contiene asignaturas, y una asignatura puede contener temas específicos.

Representar esta información mediante un árbol facilita la identificación de la raíz, los padres y los hijos. También permite ampliar la estructura sin tener que crear una clase diferente para cada nivel. Sin embargo, si un elemento puede tener varios padres, el modelo debe reconsiderarse porque podría tratarse de un grafo.

Este ejercicio me permitió comprender cómo se construyen las relaciones entre nodos y por qué los árboles son útiles para representar sistemas jerárquicos en programación.