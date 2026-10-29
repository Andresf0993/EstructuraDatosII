import java.util.LinkedList;
import java.util.Queue;

public class ArbolBSTProducto {
    private NodoProducto raiz;

    public void insertar(Producto producto) {
        raiz = insertarRec(raiz, producto);
    }

    private NodoProducto insertarRec(NodoProducto nodo, Producto producto) {
        if (nodo == null) {
            return new NodoProducto(producto);
        }

        int nuevo = producto.getCodigo();
        int actual = nodo.getProducto().getCodigo();

        if (nuevo < actual) {
            nodo.setIzquierdo(insertarRec(nodo.getIzquierdo(), producto));
        } else if (nuevo > actual) {
            nodo.setDerecho(insertarRec(nodo.getDerecho(), producto));
        } else {
            System.out.println("El código " + nuevo + " ya existe.");
        }

        return nodo;
    }

    public void preorden() {
        System.out.println("\nPREORDEN:");
        preordenRec(raiz);
        System.out.println();
    }

    private void preordenRec(NodoProducto nodo) {
        if (nodo != null) {
            System.out.print(nodo.getProducto() + "  ");
            preordenRec(nodo.getIzquierdo());
            preordenRec(nodo.getDerecho());
        }
    }

    public void inorden() {
        System.out.println("\nINORDEN:");
        inordenRec(raiz);
        System.out.println();
    }

    private void inordenRec(NodoProducto nodo) {
        if (nodo != null) {
            inordenRec(nodo.getIzquierdo());
            System.out.print(nodo.getProducto() + "  ");
            inordenRec(nodo.getDerecho());
        }
    }

    public void postorden() {
        System.out.println("\nPOSTORDEN:");
        postordenRec(raiz);
        System.out.println();
    }

    private void postordenRec(NodoProducto nodo) {
        if (nodo != null) {
            postordenRec(nodo.getIzquierdo());
            postordenRec(nodo.getDerecho());
            System.out.print(nodo.getProducto() + "  ");
        }
    }

    public void porNiveles() {
        System.out.println("\nPOR NIVELES:");

        if (raiz == null) {
            System.out.println("El árbol está vacío.");
            return;
        }

        Queue<NodoProducto> cola = new LinkedList<>();
        cola.offer(raiz);

        while (!cola.isEmpty()) {
            NodoProducto actual = cola.poll();
            System.out.print(actual.getProducto() + "  ");

            if (actual.getIzquierdo() != null) {
                cola.offer(actual.getIzquierdo());
            }
            if (actual.getDerecho() != null) {
                cola.offer(actual.getDerecho());
            }
        }
        System.out.println();
    }

    public boolean comprobarInorden() {
        return comprobarOrden(raiz, new int[]{Integer.MIN_VALUE});
    }

    private boolean comprobarOrden(NodoProducto nodo, int[] anterior) {
        if (nodo == null) return true;

        if (!comprobarOrden(nodo.getIzquierdo(), anterior)) return false;

        int codigoActual = nodo.getProducto().getCodigo();
        if (codigoActual < anterior[0]) return false;

        anterior[0] = codigoActual;
        return comprobarOrden(nodo.getDerecho(), anterior);
    }
}
