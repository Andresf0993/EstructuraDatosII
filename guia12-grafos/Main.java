import java.util.List;

/**
 * Programa de demostración de la Guía 12 – Introducción a grafos y redes complejas.
 *
 * <p><b>Dominio:</b> transporte terrestre entre 10 ciudades de Colombia.<br>
 * <b>Vértices:</b> 10 ciudades. <b>Aristas:</b> 17 rutas (12 de doble vía, no
 * dirigidas, y 5 expresos de un solo sentido, dirigidos).<br>
 * <b>Peso:</b> distancia aproximada por carretera en km (valores referenciales
 * con fines académicos).</p>
 *
 * <p>Compilar: {@code javac -encoding UTF-8 *.java}<br>
 * Ejecutar: {@code java Main}</p>
 */
public class Main {

    public static void main(String[] args) {
        Grafo red = construirRedDeTransporte();

        imprimirTablaVertices(red);
        imprimirTablaAristas(red);
        imprimirClasificacion(red);
        imprimirEjercicios(red);
    }

    /** Construye el grafo del dominio de transporte (10 vértices, 17 aristas). */
    static Grafo construirRedDeTransporte() {
        Grafo g = new Grafo();

        // ---- Vértices (10) ----
        g.agregarVertice(new Vertice("Pereira", "Risaralda"));
        g.agregarVertice(new Vertice("Armenia", "Quindío"));
        g.agregarVertice(new Vertice("Manizales", "Caldas"));
        g.agregarVertice(new Vertice("Cartago", "Valle del Cauca"));
        g.agregarVertice(new Vertice("Cali", "Valle del Cauca"));
        g.agregarVertice(new Vertice("Ibagué", "Tolima"));
        g.agregarVertice(new Vertice("Medellín", "Antioquia"));
        g.agregarVertice(new Vertice("La Dorada", "Caldas"));
        g.agregarVertice(new Vertice("Honda", "Tolima"));
        g.agregarVertice(new Vertice("Bogotá", "Cundinamarca"));

        // ---- Aristas NO dirigidas y ponderadas (12): carreteras de doble vía ----
        g.agregarArista(new Arista("Pereira", "Armenia", 44, false));
        g.agregarArista(new Arista("Pereira", "Manizales", 52, false));
        g.agregarArista(new Arista("Pereira", "Cartago", 40, false));
        g.agregarArista(new Arista("Armenia", "Ibagué", 105, false));
        g.agregarArista(new Arista("Armenia", "Cali", 190, false));
        g.agregarArista(new Arista("Cartago", "Cali", 190, false));
        g.agregarArista(new Arista("Manizales", "Medellín", 215, false));
        g.agregarArista(new Arista("Pereira", "Medellín", 220, false));
        g.agregarArista(new Arista("Manizales", "La Dorada", 125, false));
        g.agregarArista(new Arista("La Dorada", "Honda", 35, false));
        g.agregarArista(new Arista("Honda", "Bogotá", 170, false));
        g.agregarArista(new Arista("Ibagué", "Bogotá", 200, false));

        // ---- Aristas DIRIGIDAS y ponderadas (5): expresos de un solo sentido ----
        g.agregarArista(new Arista("Medellín", "Bogotá", 415, true));
        g.agregarArista(new Arista("Bogotá", "Cali", 460, true));
        g.agregarArista(new Arista("Cali", "Pereira", 215, true));
        g.agregarArista(new Arista("La Dorada", "Medellín", 180, true));
        g.agregarArista(new Arista("Honda", "Ibagué", 110, true));

        return g;
    }

    // ------------------------------------------------------------------
    // Salida por consola (evidencias para el informe)
    // ------------------------------------------------------------------

    static void imprimirTablaVertices(Grafo g) {
        System.out.println("=== TABLA DE VÉRTICES (V) ===");
        System.out.printf("%-4s %-12s %-16s %-7s %-7s %-7s%n",
                "N°", "Ciudad", "Departamento", "Grado", "G.sal", "G.ent");
        int i = 1;
        for (Vertice v : g.getVertices()) {
            System.out.printf("%-4d %-12s %-16s %-7d %-7d %-7d%n", i++, v.nombre, v.departamento,
                    g.gradoIncidente(v.nombre), g.gradoSalida(v.nombre), g.gradoEntrada(v.nombre));
        }
        System.out.println("|V| = " + g.numVertices());
        System.out.println();
    }

    static void imprimirTablaAristas(Grafo g) {
        System.out.println("=== TABLA DE ARISTAS (A) ===");
        System.out.printf("%-4s %-12s %-12s %-10s %-10s%n", "N°", "Origen", "Destino", "Peso (km)", "Tipo");
        int i = 1;
        for (Arista a : g.getAristas()) {
            System.out.printf("%-4d %-12s %-12s %-10.0f %-10s%n", i++, a.origen, a.destino, a.peso,
                    a.dirigida ? "Dirigida" : "No dirig.");
        }
        System.out.println("|A| = " + g.numAristas() + " (" + g.numNoDirigidas()
                + " no dirigidas + " + g.numDirigidas() + " dirigidas)");
        System.out.println();
    }

    static void imprimirClasificacion(Grafo g) {
        System.out.println("=== CLASIFICACIÓN FORMAL: G = (V, A) ===");
        System.out.println("Orden |V|          : " + g.numVertices());
        System.out.println("Tamaño |A|         : " + g.numAristas());
        System.out.println("Arcos reales       : " + g.numArcos() + " (dirigida = 1, no dirigida = 2)");
        System.out.println("Dirección          : " + g.tipoDireccion());
        System.out.println("Ponderado          : " + (g.esPonderado() ? "sí (km)" : "no"));
        System.out.println("Conexo (débil)     : " + (g.esConexo() ? "sí" : "no"));
        System.out.println("Fuertemente conexo : " + (g.esFuertementeConexo() ? "sí" : "no"));
        System.out.println("Cíclico            : " + (g.esCiclico() ? "sí" : "no (acíclico)"));
        System.out.printf("Densidad D         : %d / (%d·%d) = %.3f -> %s%n",
                g.numArcos(), g.numVertices(), g.numVertices() - 1, g.densidad(),
                g.esDenso() ? "DENSO" : "DISPERSO");
        System.out.printf("Peso total         : %.0f km%n", g.pesoTotal());
        System.out.println();
    }

    static void imprimirEjercicios(Grafo g) {
        System.out.println("=== EJERCICIOS ===");

        // Ejercicio 1: conversión a dirigido
        Grafo dirigido = g.convertirADirigido();
        System.out.println("1) Conversión a grafo dirigido:");
        System.out.println("   Original : " + g.numAristas() + " aristas ("
                + g.numNoDirigidas() + " no dirigidas, " + g.numDirigidas() + " dirigidas)");
        System.out.println("   Dirigido : " + dirigido.numAristas() + " aristas (tipo: "
                + dirigido.tipoDireccion() + ")");
        System.out.println("   Cada ruta de doble vía pasó a ser 2 rutas de un solo sentido.");

        // Ejercicio 2: ciclo
        List<String> ciclo = g.buscarCiclo();
        System.out.println("2) Ciclo encontrado: " + (ciclo.isEmpty() ? "ninguno" : String.join(" -> ", ciclo)));

        // Ejercicio 3: densidad
        System.out.printf("3) Densidad = %.3f -> el grafo es %s (umbral %.1f)%n",
                g.densidad(), g.esDenso() ? "denso" : "disperso", Grafo.UMBRAL_DENSIDAD);
    }
}
