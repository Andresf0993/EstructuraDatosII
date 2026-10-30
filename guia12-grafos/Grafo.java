import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class Grafo {
    public static void main(String[] args) {
        List<Vertice> vertices = Arrays.asList(
            new Vertice("A", "Terminal"),
            new Vertice("B", "Centro"),
            new Vertice("C", "Hospital"),
            new Vertice("D", "Universidad"),
            new Vertice("E", "Parque"),
            new Vertice("F", "Estadio"),
            new Vertice("G", "Mercado"),
            new Vertice("H", "Aeropuerto"),
            new Vertice("I", "Museo"),
            new Vertice("J", "Puerto"),
            new Vertice("K", "Biblioteca"),
            new Vertice("L", "Zona Industrial")
        );

        List<Arista> aristas = Arrays.asList(
            new Arista(buscar(vertices, "A"), buscar(vertices, "B"), 2.0, false),
            new Arista(buscar(vertices, "B"), buscar(vertices, "C"), 1.2, false),
            new Arista(buscar(vertices, "C"), buscar(vertices, "D"), 2.8, false),
            new Arista(buscar(vertices, "D"), buscar(vertices, "E"), 1.1, false),
            new Arista(buscar(vertices, "E"), buscar(vertices, "F"), 1.7, false),
            new Arista(buscar(vertices, "F"), buscar(vertices, "G"), 1.3, false),
            new Arista(buscar(vertices, "G"), buscar(vertices, "H"), 5.0, false),
            new Arista(buscar(vertices, "H"), buscar(vertices, "I"), 3.1, false),
            new Arista(buscar(vertices, "I"), buscar(vertices, "J"), 4.2, false),
            new Arista(buscar(vertices, "J"), buscar(vertices, "K"), 1.8, false),
            new Arista(buscar(vertices, "K"), buscar(vertices, "L"), 2.4, false),
            new Arista(buscar(vertices, "A"), buscar(vertices, "C"), 3.5, false),
            new Arista(buscar(vertices, "B"), buscar(vertices, "D"), 2.3, false),
            new Arista(buscar(vertices, "E"), buscar(vertices, "G"), 2.2, false),
            new Arista(buscar(vertices, "I"), buscar(vertices, "K"), 2.1, false)
        );

        registrarGrados(vertices, aristas);
        List<Arista> grafoDirigido = convertirEnDirigido(aristas);
        int arcos = contarArcos(aristas);
        double densidad = arcos / (double) (vertices.size() * (vertices.size() - 1));
        double pesoTotal = sumarPesos(aristas);

        imprimirVertices(vertices);
        imprimirAristas(aristas);
        imprimirClasificacion(vertices, aristas, arcos, densidad, pesoTotal);
        imprimirEjercicios(aristas, grafoDirigido.size(), densidad);
    }

    private static Vertice buscar(List<Vertice> vertices, String id) {
        for (Vertice vertice : vertices) {
            if (vertice.getId().equals(id)) return vertice;
        }
        throw new IllegalArgumentException("No existe el vertice " + id);
    }

    private static void registrarGrados(List<Vertice> vertices, List<Arista> aristas) {
        for (Arista arista : aristas) {
            if (arista.isDirigida()) {
                arista.getOrigen().registrarSalidaDirigida();
                arista.getDestino().registrarEntradaDirigida();
            } else {
                arista.getOrigen().registrarAristaNoDirigida();
                arista.getDestino().registrarAristaNoDirigida();
            }
        }
    }

    private static List<Arista> convertirEnDirigido(List<Arista> aristas) {
        List<Arista> resultado = new ArrayList<>();
        for (Arista arista : aristas) {
            if (arista.isDirigida()) {
                resultado.add(new Arista(arista.getOrigen(), arista.getDestino(), arista.getPeso(), true));
            } else {
                resultado.add(new Arista(arista.getOrigen(), arista.getDestino(), arista.getPeso(), true));
                resultado.add(new Arista(arista.getDestino(), arista.getOrigen(), arista.getPeso(), true));
            }
        }
        return resultado;
    }

    private static int contarArcos(List<Arista> aristas) {
        int total = 0;
        for (Arista arista : aristas) total += arista.isDirigida() ? 1 : 2;
        return total;
    }

    private static double sumarPesos(List<Arista> aristas) {
        double total = 0.0;
        for (Arista arista : aristas) total += arista.getPeso();
        return total;
    }

    private static void imprimirVertices(List<Vertice> vertices) {
        System.out.println("=== TABLA DE VERTICES (V) ===");
        System.out.printf("%-4s %-5s %-20s %-8s %-8s %-8s%n",
            "No.", "ID", "Lugar", "Grado", "G.sal", "G.ent");
        for (int i = 0; i < vertices.size(); i++) {
            Vertice v = vertices.get(i);
            System.out.printf("%-4d %-5s %-20s %-8d %-8d %-8d%n",
                i + 1, v.getId(), v.getLugar(), v.getGrado(),
                v.getGradoSalida(), v.getGradoEntrada());
        }
        System.out.println("|V| = " + vertices.size());
        System.out.println();
    }

    private static void imprimirAristas(List<Arista> aristas) {
        System.out.println("=== TABLA DE ARISTAS (A) ===");
        System.out.printf("%-4s %-24s %-24s %-12s %-12s%n",
            "No.", "Origen", "Destino", "Peso (km)", "Tipo");
        int noDirigidas = 0;
        int dirigidas = 0;
        for (int i = 0; i < aristas.size(); i++) {
            Arista a = aristas.get(i);
            String origen = a.getOrigen().getId() + "-" + a.getOrigen().getLugar();
            String destino = a.getDestino().getId() + "-" + a.getDestino().getLugar();
            String tipo = a.isDirigida() ? "Dirigida" : "No dirig.";
            if (a.isDirigida()) dirigidas++; else noDirigidas++;
            System.out.printf(Locale.US, "%-4d %-24s %-24s %-12.1f %-12s%n",
                i + 1, origen, destino, a.getPeso(), tipo);
        }
        System.out.printf("|A| = %d (%d no dirigidas + %d dirigidas)%n",
            aristas.size(), noDirigidas, dirigidas);
        System.out.println();
    }

    private static void imprimirClasificacion(List<Vertice> vertices, List<Arista> aristas,
                                               int arcos, double densidad, double pesoTotal) {
        int noDirigidas = 0;
        int dirigidas = 0;
        for (Arista arista : aristas) {
            if (arista.isDirigida()) dirigidas++; else noDirigidas++;
        }
        String direccion = dirigidas == 0 ? "no dirigida" :
            (noDirigidas == 0 ? "dirigida" : "mixta");

        System.out.println("=== CLASIFICACION FORMAL: G = (V, A) ===");
        System.out.printf("Orden |V|          : %d%n", vertices.size());
        System.out.printf("Tamano |A|         : %d%n", aristas.size());
        System.out.printf("Arcos reales       : %d (dirigida = 1, no dirigida = 2)%n", arcos);
        System.out.printf("Direccion          : %s%n", direccion);
        System.out.println("Ponderado          : si (km)");
        System.out.printf("Conexo (debil)     : %s%n", esConexo(vertices, aristas, true) ? "si" : "no");
        System.out.printf("Fuertemente conexo*: %s%n", esFuertementeConexo(vertices, aristas) ? "si" : "no");
        System.out.println("Ciclico            : si (A-B-C-A)");
        System.out.printf(Locale.US, "Densidad D         : %d / (%d*%d) = %.3f -> %s%n",
            arcos, vertices.size(), vertices.size() - 1, densidad,
            densidad < 0.5 ? "DISPERSO" : "DENSO");
        System.out.printf(Locale.US, "Peso total         : %.1f km%n", pesoTotal);
        System.out.println("* Expansion: cada arista no dirigida equivale a dos arcos opuestos.");
        System.out.println();
    }

    private static void imprimirEjercicios(List<Arista> aristas, int arcosDirigidos, double densidad) {
        int noDirigidas = 0;
        int dirigidas = 0;
        for (Arista arista : aristas) {
            if (arista.isDirigida()) dirigidas++; else noDirigidas++;
        }
        System.out.println("=== EJERCICIOS ===");
        System.out.println("1) Conversion a grafo dirigido:");
        System.out.printf("   Original : %d aristas (%d no dirigidas, %d dirigidas)%n",
            aristas.size(), noDirigidas, dirigidas);
        System.out.printf("   Dirigido : %d arcos%n", arcosDirigidos);
        System.out.println("   Cada ruta de doble via paso a dos arcos de un solo sentido.");
        System.out.println();
        System.out.println("2) Ciclo encontrado:");
        System.out.println("   Terminal -> Centro -> Hospital -> Terminal");
        System.out.println();
        System.out.printf(Locale.US, "3) Densidad = %.3f -> el grafo es %s (umbral 0.5)%n",
            densidad, densidad < 0.5 ? "disperso" : "denso");
    }

    private static boolean esConexo(List<Vertice> vertices, List<Arista> aristas,
                                    boolean ignorarDireccion) {
        Map<String, List<String>> adyacencia = crearAdyacencia(vertices, aristas, ignorarDireccion);
        return alcanzar(vertices.get(0).getId(), adyacencia).size() == vertices.size();
    }

    private static boolean esFuertementeConexo(List<Vertice> vertices, List<Arista> aristas) {
        Map<String, List<String>> adyacencia = crearAdyacencia(vertices, aristas, false);
        for (Vertice inicio : vertices) {
            if (alcanzar(inicio.getId(), adyacencia).size() != vertices.size()) return false;
        }
        return true;
    }

    private static Map<String, List<String>> crearAdyacencia(List<Vertice> vertices,
                                                               List<Arista> aristas,
                                                               boolean ignorarDireccion) {
        Map<String, List<String>> adyacencia = new HashMap<>();
        for (Vertice v : vertices) adyacencia.put(v.getId(), new ArrayList<String>());
        for (Arista a : aristas) {
            adyacencia.get(a.getOrigen().getId()).add(a.getDestino().getId());
            if (!a.isDirigida() || ignorarDireccion) {
                adyacencia.get(a.getDestino().getId()).add(a.getOrigen().getId());
            }
        }
        return adyacencia;
    }

    private static Set<String> alcanzar(String inicio, Map<String, List<String>> adyacencia) {
        Set<String> visitados = new HashSet<>();
        Queue<String> cola = new ArrayDeque<>();
        visitados.add(inicio);
        cola.add(inicio);
        while (!cola.isEmpty()) {
            String actual = cola.remove();
            for (String vecino : adyacencia.getOrDefault(actual, Collections.<String>emptyList())) {
                if (visitados.add(vecino)) cola.add(vecino);
            }
        }
        return visitados;
    }
}
