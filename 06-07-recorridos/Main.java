public class Main {
    public static void main(String[] args) {
        ArbolBSTProducto arbol = new ArbolBSTProducto();

        arbol.insertar(new Producto(27, "iphone 11", 250000));
        arbol.insertar(new Producto(30, "samsung galaxy s10", 10000));
        arbol.insertar(new Producto(3, "Airpods 3 pro", 20000));
        arbol.insertar(new Producto(9, "ipad ", 150000));
        arbol.insertar(new Producto(80, "Impresora", 200000));
        arbol.insertar(new Producto(65, "iphone 18", 560000));
        arbol.insertar(new Producto(40, "Xiaomi Redmi A7 Pro", 300000));
        arbol.insertar(new Producto(11, "mac ", 800000));
        arbol.insertar(new Producto(99, "iphone Duo", 600000));

        System.out.println("======================================");
        System.out.println("ACTIVIDAD: EXPLORADORES DEL ÁRBOL");
        System.out.println("======================================");

        arbol.preorden();
        arbol.inorden();
        arbol.postorden();
        arbol.porNiveles();

        System.out.println("\n¿Inorden devuelve las claves ordenadas?");
        System.out.println(arbol.comprobarInorden());
    }
}
