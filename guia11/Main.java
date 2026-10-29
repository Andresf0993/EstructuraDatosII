import java.util.List;
import java.util.Scanner;

/**
 * Programa de prueba del mini motor de autocompletado.
 * Carga un diccionario de 40 palabras, ejecuta pruebas verificables
 * y ofrece un modo interactivo (opcional: java Main -i).
 */
public class Main {

    private static final String[] DICCIONARIO = {
        "carro", "moto", "motor", "estado", "frenos",
        "rines", "casco", "guantes", "silla", "timon",
        "espejo", "baul", "comparendo", "patios", "pase",
        "arbol", "lluvia", "pato", "conduccion", "gasolina",
        "aceite", "tecnomecanica", "soat", "cadena", "chaleco",
        "exosto", "parqueadero", "aire", "picap", "uber",
        "velocidad", "aceleracion", "antesedentes", "claxon", "herramienta",
        "llave", "alarma", "luces", "direccion", "recursividad"
    };

    private static int pruebasOk = 0;
    private static int pruebasTotal = 0;

    public static void main(String[] args) {
        Trie trie = new Trie();
        for (String palabra : DICCIONARIO) {
            trie.insertar(palabra);
        }
        System.out.println("=== Mini motor de autocompletado (Trie) ===");
        System.out.println("Palabras cargadas: " + trie.tamanio());

        // 1) Autocompletado con 5 prefijos (+ 1 inexistente)
        System.out.println("\n--- Sugerencias (maximo 5) ---");
        String[] prefijos = {"car", "est", "ar", "co", "di", "xyz"};
        for (String p : prefijos) {
            List<String> sug = trie.sugerir(p);
            System.out.printf("Prefijo \"%s\" -> %s%n", p, sug.isEmpty() ? "(sin sugerencias)" : sug);
        }

        // 2) Pruebas verificables
        System.out.println("\n--- Pruebas automaticas ---");
        verificar("buscar(\"lluvia\") es true", trie.buscar("lluvia"));
        verificar("buscar(\"estado\") es true (ignora mayusculas)", trie.buscar("estado"));
        verificar("buscar(\"pro\") es false (solo prefijo)", !trie.buscar("pro"));
        verificar("buscar(\"programas\") es false", !trie.buscar("programas"));
        verificar("empiezaCon(\"prog\") es true", trie.empiezaCon("prog"));
        verificar("empiezaCon(\"zz\") es false", !trie.empiezaCon("zz"));
        verificar("sugerir(\"pro\") devuelve 5 resultados", trie.sugerir("pro").size() == 5);
        verificar("sugerir(\"pro\", 3) devuelve 3 resultados", trie.sugerir("pro", 3).size() == 3);
        verificar("sugerir(\"pila\") devuelve [pila]", trie.sugerir("pila").equals(List.of("pila")));
        verificar("sugerir(\"xyz\") devuelve lista vacia", trie.sugerir("xyz").isEmpty());
        trie.insertar("nodo"); // duplicado
        verificar("insertar duplicado no cambia el tamano (40)", trie.tamanio() == 40);
        System.out.printf("%nResultado: %d/%d pruebas correctas%n", pruebasOk, pruebasTotal);

        // 3) Modo interactivo opcional
        if (args.length > 0 && args[0].equals("-i")) {
            modoInteractivo(trie);
        }
    }

    private static void verificar(String descripcion, boolean condicion) {
        pruebasTotal++;
        if (condicion) pruebasOk++;
        System.out.println((condicion ? "[OK]    " : "[FALLA] ") + descripcion);
    }

    private static void modoInteractivo(Trie trie) {
        System.out.println("\nModo interactivo. Escriba un prefijo (o 'salir').");
        try (Scanner sc = new Scanner(System.in)) {
            while (true) {
                System.out.print("> ");
                if (!sc.hasNextLine()) break;
                String linea = sc.nextLine().trim();
                if (linea.equalsIgnoreCase("salir")) break;
                System.out.println(trie.sugerir(linea));
            }
        }
    }
}
