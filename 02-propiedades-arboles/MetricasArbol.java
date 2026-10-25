
import java.util.ArrayList;
import java.util.List;

public class MetricasArbol {

    public static int contarNodos(NodoGeneral<?> nodo) {
        int total = 1;

        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            total += contarNodos(hijo);
        }

        return total;
    }

    public static int contarHojas(NodoGeneral<?> nodo) {
        if (nodo.getHijos().isEmpty()) {
            return 1;
        }

        int total = 0;

        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            total += contarHojas(hijo);
        }

        return total;
    }

    public static int gradoNodo(NodoGeneral<?> nodo) {
        return nodo.getHijos().size();
    }

    public static int gradoArbol(NodoGeneral<?> nodo) {
        int max = gradoNodo(nodo);

        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            max = Math.max(max, gradoArbol(hijo));
        }

        return max;
    }

    // La hoja tiene altura 0.
    public static int altura(NodoGeneral<?> nodo) {
        if (nodo.getHijos().isEmpty()) {
            return 0;
        }

        int max = 0;

        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            max = Math.max(max, altura(hijo));
        }

        return 1 + max;
    }

    // Devuelve -1 si el nodo no pertenece al árbol.
    public static int profundidad(
            NodoGeneral<?> raiz,
            NodoGeneral<?> buscado) {

        return buscarProfundidad(raiz, buscado, 0);
    }

    private static int buscarProfundidad(
            NodoGeneral<?> actual,
            NodoGeneral<?> buscado,
            int nivel) {

        if (actual == buscado) {
            return nivel;
        }

        for (NodoGeneral<?> hijo : actual.getHijos()) {
            int resultado =
                    buscarProfundidad(hijo, buscado, nivel + 1);

            if (resultado != -1) {
                return resultado;
            }
        }

        return -1;
    }

    public static List<NodoGeneral<?>> camino(
            NodoGeneral<?> raiz,
            NodoGeneral<?> destino) {

        List<NodoGeneral<?>> ruta = new ArrayList<>();

        if (buscarCamino(raiz, destino, ruta)) {
            return ruta;
        }

        return new ArrayList<>();
    }

    private static boolean buscarCamino(
            NodoGeneral<?> actual,
            NodoGeneral<?> destino,
            List<NodoGeneral<?>> ruta) {

        ruta.add(actual);

        if (actual == destino) {
            return true;
        }

        for (NodoGeneral<?> hijo : actual.getHijos()) {
            if (buscarCamino(hijo, destino, ruta)) {
                return true;
            }
        }

        ruta.remove(ruta.size() - 1);
        return false;
    }

    public static int longitudCamino(
            NodoGeneral<?> raiz,
            NodoGeneral<?> destino) {

        List<NodoGeneral<?>> ruta = camino(raiz, destino);

        if (ruta.isEmpty()) {
            return -1;
        }

        return ruta.size() - 1;
    }

    public static String rutaComoTexto(
            List<NodoGeneral<?>> ruta) {

        StringBuilder texto = new StringBuilder();

        for (int i = 0; i < ruta.size(); i++) {
            if (i > 0) {
                texto.append(" -> ");
            }

            texto.append(ruta.get(i).getDato());
        }

        return texto.toString();
    }

    public static void mostrarArbol(
            NodoGeneral<?> nodo,
            int nivel) {

        for (int i = 0; i < nivel; i++) {
            System.out.print("    ");
        }

        System.out.println("- " + nodo.getDato());

        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            mostrarArbol(hijo, nivel + 1);
        }
    }

    public static void mostrarTablaMetricas(
            NodoGeneral<?> nodo,
            NodoGeneral<?> raiz) {

        String tipo;

        if (nodo == raiz) {
            tipo = "Raiz / interno";
        } else if (nodo.getHijos().isEmpty()) {
            tipo = "Hoja";
        } else {
            tipo = "Interno";
        }

        System.out.printf(
                "%-38s | %2d | %2d | %2d | %s%n",
                nodo.getDato(),
                gradoNodo(nodo),
                profundidad(raiz, nodo),
                altura(nodo),
                tipo
        );

        for (NodoGeneral<?> hijo : nodo.getHijos()) {
            mostrarTablaMetricas(hijo, raiz);
        }
    }
}
