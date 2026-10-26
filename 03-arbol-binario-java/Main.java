/**
 * Guía 3: construcción manual de un árbol binario de 7 nodos.
 */
public class Main {

    public static void main(String[] args) {

        // 1. Crear los siete nodos.
        NodoArbol<String> a = new NodoArbol<>("Área general");
        NodoArbol<String> b = new NodoArbol<>("Ecuaciones");
        NodoArbol<String> c = new NodoArbol<>("Figuras");
        NodoArbol<String> d = new NodoArbol<>("Ecuación lineal: (2x+4=10)");
        NodoArbol<String> e = new NodoArbol<>("Ecuación cuadrática: (x^2-5x+6=0)");
        NodoArbol<String> f = new NodoArbol<>("Área del triángulo: (A=bh/2)");
        NodoArbol<String> g = new NodoArbol<>("Área del círculo: (A=pi r^2)");

        /*
         * 2. Enlazar las referencias:
         *
         *                   Área general
         *        /                            \
         *       Ecuaciones                  Figuras
         *         /       \                  /      \
         *     (2x+4=10) (x^2-5x+6=0)    (A=bh/2)   (A=pi r^2)
         *
         * Es binario, lleno, completo y perfecto.
         */
        a.setIzquierdo(b);
        a.setDerecho(c);

        b.setIzquierdo(d);
        b.setDerecho(e);

        c.setIzquierdo(f);
        c.setDerecho(g);

        // 3. Crear el objeto árbol.
        ArbolBinario<String> arbol = new ArbolBinario<>(a);

        // 4. Método solicitado por la guía.
        System.out.println("=== RAÍZ E HIJOS ===");
        arbol.imprimirRaizYHijos();

        // 5. Mostrar toda la estructura.
        System.out.println("\n=== ÁRBOL COMPLETO ===");
        arbol.imprimirArbol();

        // 6. Probar propiedades.
        System.out.println("\n=== PROPIEDADES ===");
        System.out.println("Nodos: " + arbol.contarNodos());
        System.out.println("Hojas: " + arbol.contarHojas());
        System.out.println("Altura: " + arbol.altura());
        System.out.println("Grado de A: " + a.grado());
        System.out.println("¿D es hoja?: " + d.esHoja());
        System.out.println("¿A es hoja?: " + a.esHoja());

        // 7. Prueba segura con null.
        System.out.println("\n=== CONTROL DE NULL ===");

        System.out.println(
            "Hijo izquierdo de D: " + d.getIzquierdo()
        );

        if (d.getIzquierdo() != null) {
            System.out.println(d.getIzquierdo().getDato());
        } else {
            System.out.println(
                "D no tiene hijo izquierdo; la referencia es null."
            );
        }
System.out.println("\n");
        System.out.println(
            "Hijo izquierdo de B: " + b.getIzquierdo()
        );

        if (b.getIzquierdo() != null) {
            System.out.println(b.getIzquierdo().getDato());
        } else {
            System.out.println(
                "B no tiene hijo izquierdo; la referencia es null."
            );
        }

        // 8. Probar un árbol vacío.
        System.out.println("\n=== ÁRBOL VACÍO ===");
        ArbolBinario<Integer> vacio = new ArbolBinario<>();
        vacio.imprimirRaizYHijos();
    }
}
