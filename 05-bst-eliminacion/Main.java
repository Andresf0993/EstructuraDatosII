/**
 * Guía 5. BST II: eliminación de nodos.
 *
 * Se prueban de forma independiente los tres casos:
 * 1. eliminación de una hoja;
 * 2. eliminación de un nodo con un hijo;
 * 3. eliminación de un nodo con dos hijos.
 *
 * El tercer caso elimina la raíz, cumpliendo además
 * el ejercicio adicional de la guía.
 */
public class Main {

    public static void main(String[] args) {

        probarCasoHoja();
        probarCasoUnHijo();
        probarCasoDosHijosYRaiz();
        probarValorInexistente();
    }

    // ==========================================================
    // CASO 1: ELIMINAR UNA HOJA
    // ==========================================================

    private static void probarCasoHoja() {

        System.out.println(
                "========================================"
        );
        System.out.println(
                "CASO 1: ELIMINAR NODO HOJA"
        );
        System.out.println(
                "========================================"
        );

        ArbolBST arbol = crearArbolBase();

        System.out.println(
                "\nANTES de eliminar 40:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden antes:"
        );
        arbol.mostrarInorden();

        arbol.eliminar(40);

        System.out.println(
                "\nDESPUÉS de eliminar 40:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden después:"
        );
        arbol.mostrarInorden();

        System.out.println(
                "¿BST válido?: "
                        + arbol.esBSTValido()
        );

        System.out.println(
                "¿40 existe?: "
                        + arbol.buscar(40)
        );

        System.out.println();
    }

    // ==========================================================
    // CASO 2: ELIMINAR UN NODO CON UN HIJO
    // ==========================================================

    private static void probarCasoUnHijo() {

        System.out.println(
                "========================================"
        );
        System.out.println(
                "CASO 2: ELIMINAR NODO CON UN HIJO"
        );
        System.out.println(
                "========================================"
        );

        /*
         * Se agrega 130 como hijo derecho de 120.
         *
         * Antes:
         *
         *        140
         *       /
         *      120
         *        \
         *         130
         *
         * Al eliminar 120, la referencia de 140
         * debe pasar directamente a 130.
         */
        ArbolBST arbol = crearArbolConNodoDeUnHijo();

        System.out.println(
                "\nANTES de eliminar 120:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden antes:"
        );
        arbol.mostrarInorden();

        arbol.eliminar(120);

        System.out.println(
                "\nDESPUÉS de eliminar 120:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden después:"
        );
        arbol.mostrarInorden();

        System.out.println(
                "¿BST válido?: "
                        + arbol.esBSTValido()
        );

        System.out.println(
                "¿120 existe?: "
                        + arbol.buscar(120)
        );

        System.out.println(
                "¿130 sigue existiendo?: "
                        + arbol.buscar(130)
        );

        System.out.println();
    }

    // ==========================================================
    // CASO 3: ELIMINAR UN NODO CON DOS HIJOS
    // Y, AL MISMO TIEMPO, ELIMINAR LA RAÍZ
    // ==========================================================

    private static void probarCasoDosHijosYRaiz() {

        System.out.println(
                "========================================"
        );
        System.out.println(
                "CASO 3: DOS HIJOS / ELIMINAR RAÍZ"
        );
        System.out.println(
                "========================================"
        );

        ArbolBST arbol = crearArbolBase();

        System.out.println(
                "\nANTES de eliminar la raíz 100:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden antes:"
        );
        arbol.mostrarInorden();

        /*
         * 50 tiene dos hijos.
         * Su sucesor inorden es el mínimo
         * del subárbol derecho: 60.
         */
        int sucesor =
                arbol.minValor(
                        arbol.getRaiz().getDerecho()
                );

        System.out.println(
                "\nSucesor inorden de 100: "
                        + sucesor
        );

        arbol.eliminar(100);

        System.out.println(
                "\nDESPUÉS de eliminar la raíz 100:"
        );
        arbol.imprimirArbol();

        System.out.println(
                "\nInorden después:"
        );
        arbol.mostrarInorden();

        System.out.println(
                "Nueva raíz: "
                        + arbol.getRaiz().getDato()
        );

        System.out.println(
                "¿BST válido?: "
                        + arbol.esBSTValido()
        );

        System.out.println(
                "¿100 existe?: "
                        + arbol.buscar(100)
        );

        System.out.println();
    }

    // ==========================================================
    // PRUEBA EXTRA: VALOR INEXISTENTE
    // ==========================================================

    private static void probarValorInexistente() {

        System.out.println(
                "========================================"
        );
        System.out.println(
                "PRUEBA EXTRA: ELIMINAR VALOR INEXISTENTE"
        );
        System.out.println(
                "========================================"
        );

        ArbolBST arbol = crearArbolBase();

        int antes = arbol.contarNodos();

        arbol.eliminar(999);

        int despues = arbol.contarNodos();

        System.out.println(
                "Nodos antes: " + antes
        );

        System.out.println(
                "Nodos después: " + despues
        );

        System.out.println(
                "¿BST válido?: "
                        + arbol.esBSTValido()
        );
    }

    // ==========================================================
    // ÁRBOLES DE PRUEBA
    // ==========================================================

    /**
     * BST base:
     *
     *          100
     *        /    \
     *      60      140
     *     /  \    /  \
     *   40   80  120   160
     */
    private static ArbolBST crearArbolBase() {

        int[] valores = {
                100, 60, 140, 40, 80, 120, 160
        };

        ArbolBST arbol = new ArbolBST();

        for (int valor : valores) {
            arbol.insertar(valor);
        }

        return arbol;
    }

    /**
     * BST con un nodo que tiene exactamente un hijo:
     *
     *          100
     *        /    \
     *      60      140
     *     /  \    /  \
     *   40   80  120  160
     *               \
     *                130
     *
     * El nodo 60 tiene un único hijo: 130.
     */
    private static ArbolBST crearArbolConNodoDeUnHijo() {

        int[] valores = {
                100, 60, 140, 40,
                80, 120, 160, 130
        };

        ArbolBST arbol = new ArbolBST();

        for (int valor : valores) {
            arbol.insertar(valor);
        }

        return arbol;
    }
}
