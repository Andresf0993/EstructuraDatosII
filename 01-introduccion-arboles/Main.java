import java.util.ArrayList;
import java.util.List;
class NodoGeneral<T> {
 private T dato;
 private List<NodoGeneral<T>> hijos = new ArrayList<>();
 public NodoGeneral(T dato) { this.dato = dato; }
 public void agregarHijo(NodoGeneral<T> hijo) { hijos.add(hijo); }
 public T getDato() { return dato; }

 public List<NodoGeneral<T>> getHijos() { return hijos; }
}
public class Main {
    public static void main(String[] args) {

        NodoGeneral<String> raiz =
                new NodoGeneral<>("Universitaria");

        NodoGeneral<String> estructura =
                new NodoGeneral<>("estructura de datos 2");

        NodoGeneral<String> calculo =
                new NodoGeneral<>("calculo");

        NodoGeneral<String> catedra =
                new NodoGeneral<>("catedra colombia");

        // Agregamos las hijas de la raíz
        raiz.agregarHijo(estructura);
        raiz.agregarHijo(calculo);
        raiz.agregarHijo(catedra);

        // Agregamos las hijas de estructura de datos 2
        estructura.agregarHijo(new NodoGeneral<>("ing sistemas"));
        estructura.agregarHijo(new NodoGeneral<>("ing software"));

        // Agregamos las hijas de calculo
        calculo.agregarHijo(new NodoGeneral<>("ing sistemas"));
        calculo.agregarHijo(new NodoGeneral<>("ing software"));
        calculo.agregarHijo(new NodoGeneral<>("ing industrial"));

        // Agregamos una hija de catedra colombia
        catedra.agregarHijo(new NodoGeneral<>("veterinaria"));
        catedra.agregarHijo(new NodoGeneral<>("psicologia"));
        catedra.agregarHijo(new NodoGeneral<>("derecho"));

        // Imprimimos la raíz
        System.out.println("Raíz: " + raiz.getDato());

        // Imprimimos las hijas de la raíz
        System.out.println("Hija 1: " + raiz.getHijos().get(0).getDato());
        System.out.println("Hija 2: " + raiz.getHijos().get(1).getDato());
        System.out.println("Hija 3: " + raiz.getHijos().get(2).getDato());

        // Imprimimos las hijas de estructura de datos 2
        System.out.println("Hija 1.1: " +
                raiz.getHijos().get(0).getHijos().get(0).getDato());

        System.out.println("Hija 1.2: " +
                raiz.getHijos().get(0).getHijos().get(1).getDato());

         System.out.println("Hija 2.1: " +
                raiz.getHijos().get(1).getHijos().get(0).getDato());

         System.out.println("Hija 2.2: " +
                raiz.getHijos().get(1).getHijos().get(1).getDato());

         System.out.println("Hija 2.3: " +
                raiz.getHijos().get(1).getHijos().get(2).getDato());

         System.out.println("Hija 3.1: " +
                raiz.getHijos().get(2).getHijos().get(0).getDato());

         System.out.println("Hija 3.2: " +
                raiz.getHijos().get(2).getHijos().get(1).getDato());

         System.out.println("Hija 3.3: " +
                raiz.getHijos().get(2).getHijos().get(2).getDato());
    }
}