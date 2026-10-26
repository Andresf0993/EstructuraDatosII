/**
 * Ejercicio: árbol binario de expresiones.
 *
 *          /
 *        /   \
 *       *     +
 *      / \   / \
 *     100   2 10  40
 *
 * Expresión: (3 + 5) * (10 - 2)
 */
public class EjemploArbolExpresion {

    public static void main(String[] args) {

        NodoArbol<String> div = new NodoArbol<>("/");
        NodoArbol<String> mult = new NodoArbol<>("*");
        NodoArbol<String> sum = new NodoArbol<>("+");

        NodoArbol<String> n3 = new NodoArbol<>("100");
        NodoArbol<String> n5 = new NodoArbol<>("2");
        NodoArbol<String> n10 = new NodoArbol<>("10");
        NodoArbol<String> n2 = new NodoArbol<>("30");

        div.setIzquierdo(mult);
        div.setDerecho(sum);

        mult.setIzquierdo(n3);
        mult.setDerecho(n5);

        sum.setIzquierdo(n10);
        sum.setDerecho(n2);

        ArbolBinario<String> arbol = new ArbolBinario<>(div);

        System.out.println("=== ÁRBOL DE EXPRESIÓN ===");
        arbol.imprimirArbol();

        System.out.println(
            "\nResultado de (100 * 2) / (10 + 30): "
            + evaluar(div)
        );
    }

    public static int evaluar(NodoArbol<String> nodo) {

        if (nodo == null) {
            throw new IllegalArgumentException(
                "No se puede evaluar una referencia null."
            );
        }

        if (nodo.esHoja()) {
            return Integer.parseInt(nodo.getDato());
        }

        int izq = evaluar(nodo.getIzquierdo());
        int der = evaluar(nodo.getDerecho());

        return switch (nodo.getDato()) {
            case "+" -> izq + der;
            case "-" -> izq - der;
            case "*" -> izq * der;
            case "/" -> izq / der;
            default -> throw new IllegalArgumentException(
                "Operador no válido: " + nodo.getDato()
            );
        };
    }
}
