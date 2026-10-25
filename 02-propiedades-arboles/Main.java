public class Main {

    public static void main(String[] args) {

        // 1. CREAR LOS NODOS

        NodoGeneral<String> apple =
                new NodoGeneral<>("Apple");

        NodoGeneral<String> iphone =
                new NodoGeneral<>("Celulares iPhone");

        NodoGeneral<String> mac =
                new NodoGeneral<>("Mac");

        NodoGeneral<String> airpods =
                new NodoGeneral<>("AirPods");

        NodoGeneral<String> iphone12 =
                new NodoGeneral<>("iPhone 12");

        NodoGeneral<String> iphone13 =
                new NodoGeneral<>("iPhone 13");

        NodoGeneral<String> iphone15 =
                new NodoGeneral<>("iPhone 15");

        NodoGeneral<String> airpods4 =
                new NodoGeneral<>("AirPods 4");

        NodoGeneral<String> airpods5 =
                new NodoGeneral<>("AirPods 5");

        NodoGeneral<String> airpods3 =
                new NodoGeneral<>("AirPods 3");

        NodoGeneral<String> iphone12Mini =
                new NodoGeneral<>("iPhone 12 mini");

        NodoGeneral<String> iphone13Pro =
                new NodoGeneral<>("iPhone 13 Pro");

        NodoGeneral<String> airpods4Cancelacion =
                new NodoGeneral<>(
                        "AirPods 4 con cancelacion de ruido");

        // 2. CONSTRUIR LA JERARQUIA

        apple.agregarHijo(iphone);
        apple.agregarHijo(mac);
        apple.agregarHijo(airpods);

        iphone.agregarHijo(iphone12);
        iphone.agregarHijo(iphone13);
        iphone.agregarHijo(iphone15);

        airpods.agregarHijo(airpods4);
        airpods.agregarHijo(airpods5);
        airpods.agregarHijo(airpods3);

        iphone12.agregarHijo(iphone12Mini);
        iphone13.agregarHijo(iphone13Pro);

        airpods4.agregarHijo(airpods4Cancelacion);

        // 3. MOSTRAR EL ARBOL

        System.out.println("=== ARBOL DE APPLE ===");
        MetricasArbol.mostrarArbol(apple, 0);

        // 4. PROPIEDADES GENERALES

        System.out.println("\n=== PROPIEDADES ===");

        System.out.println("Raiz: " + apple.getDato());

        System.out.println(
                "Total de nodos: "
                + MetricasArbol.contarNodos(apple));

        System.out.println(
                "Total de hojas: "
                + MetricasArbol.contarHojas(apple));

        System.out.println(
                "Nodos internos: "
                + (MetricasArbol.contarNodos(apple)
                - MetricasArbol.contarHojas(apple)));

        System.out.println(
                "Grado del arbol: "
                + MetricasArbol.gradoArbol(apple));

        int altura = MetricasArbol.altura(apple);

        System.out.println("Altura del arbol: " + altura);
        System.out.println("Numero de niveles: " + (altura + 1));

        // 5. PROFUNDIDAD DE CINCO NODOS

        System.out.println("\n=== PROFUNDIDAD ===");

        mostrarProfundidad(apple, iphone);
        mostrarProfundidad(apple, mac);
        mostrarProfundidad(apple, iphone12);
        mostrarProfundidad(apple, iphone12Mini);
        mostrarProfundidad(apple, airpods4Cancelacion);

        // 6. GRADO DE ALGUNOS NODOS

        System.out.println("\n=== GRADO DE NODOS ===");

        mostrarGrado(apple);
        mostrarGrado(iphone);
        mostrarGrado(mac);
        mostrarGrado(iphone12);
        mostrarGrado(airpods4);

        // 7. TABLA COMPLETA

        System.out.println("\n=== TABLA DE METRICAS ===");

        System.out.printf(
                "%-38s | %2s | %2s | %2s | %s%n",
                "Nodo", "G", "P", "A", "Tipo");

        MetricasArbol.mostrarTablaMetricas(apple, apple);

        // 8. DOS CAMINOS

        System.out.println("\n=== CAMINOS ===");

        mostrarCamino(apple, iphone12Mini);
        mostrarCamino(apple, airpods4Cancelacion);

        // 9. COMPARAR ALTURA MANUAL Y CALCULADA

        System.out.println("\n=== COMPARACION ===");

        int alturaManual = 3;
        int alturaCalculada = MetricasArbol.altura(apple);

        System.out.println("Altura manual: " + alturaManual);
        System.out.println("Altura Java: " + alturaCalculada);

        System.out.println(
                alturaManual == alturaCalculada
                ? "COINCIDEN"
                : "REVISAR CALCULO");

        // 10. AGREGAR UN NUEVO NIVEL

        System.out.println("\n=== NUEVO NIVEL ===");

        iphone12Mini.agregarHijo(
                new NodoGeneral<>("Funda para iPhone 12 mini"));

        int nuevaAltura = MetricasArbol.altura(apple);

        System.out.println("Nueva altura: " + nuevaAltura);
        System.out.println("Nuevos niveles: " + (nuevaAltura + 1));

        MetricasArbol.mostrarArbol(apple, 0);
    }

    private static void mostrarProfundidad(
            NodoGeneral<?> raiz,
            NodoGeneral<?> nodo) {

        System.out.println(
                nodo.getDato() + ": profundidad "
                + MetricasArbol.profundidad(raiz, nodo));
    }

    private static void mostrarGrado(NodoGeneral<?> nodo) {

        System.out.println(
                nodo.getDato() + ": grado "
                + MetricasArbol.gradoNodo(nodo));
    }

    private static void mostrarCamino(
            NodoGeneral<?> raiz,
            NodoGeneral<?> destino) {

        java.util.List<NodoGeneral<?>> ruta =
                MetricasArbol.camino(raiz, destino);

        System.out.println(
                MetricasArbol.rutaComoTexto(ruta));

        System.out.println(
                "Longitud: "
                + MetricasArbol.longitudCamino(raiz, destino)
                + " aristas");
    }
}
