# Guía 2. Anatomía y propiedades de un árbol

## 1. Descripción

Se construyó un árbol general en Java para representar la jerarquía de productos de Apple. La estructura utiliza una raíz, nodos internos y hojas conectados mediante relaciones padre-hijo.

## 2. Propiedades calculadas

- Raíz: Apple.
- Total de nodos: 13.
- Total de hojas: 7.
- Nodos internos: 6.
- Grado del árbol: 3.
- Altura inicial: 3 aristas.
- Número de niveles: 4.

La raíz tiene tres hijos directos. El grado del árbol es 3 porque ningún nodo tiene más de tres hijos.

## 3. Profundidad de las hojas

Mac tiene profundidad 1; iPhone 15, AirPods 5 y AirPods 3 tienen profundidad 2. iPhone 12 mini, iPhone 13 Pro y AirPods 4 con cancelación de ruido tienen profundidad 3.

## 4. Caminos

Camino 1: Apple → Celulares iPhone → iPhone 12 → iPhone 12 mini. Longitud: 3 aristas.

Camino 2: Apple → AirPods → AirPods 4 → AirPods 4 con cancelación de ruido. Longitud: 3 aristas.

## 5. Altura y recursividad

El método altura() recorre recursivamente los hijos de cada nodo y devuelve la longitud del camino más largo hasta una hoja. Una hoja tiene altura 0. El resultado inicial es 3, coincidiendo con el cálculo manual.

Al agregar Funda para iPhone 12 mini como hijo de iPhone 12 mini, la altura aumenta a 4 aristas y el árbol pasa a tener 5 niveles.

## 6. Conclusión

Este ejercicio permite comprender cómo se organizan los nodos en una estructura jerárquica y cómo se calculan sus propiedades. La recursividad facilita recorrer el árbol y obtener resultados sin tener que calcular cada rama manualmente. También se comprobó que agregar un nivel por debajo de una hoja puede aumentar la altura del árbol.
