public class Arista {
    private final Vertice origen;
    private final Vertice destino;
    private final double peso;
    private final boolean dirigida;

    public Arista(Vertice origen, Vertice destino, double peso, boolean dirigida) {
        if (origen == null || destino == null) {
            throw new IllegalArgumentException("El origen y el destino son obligatorios.");
        }
        if (peso < 0) {
            throw new IllegalArgumentException("El peso no puede ser negativo.");
        }
        this.origen = origen;
        this.destino = destino;
        this.peso = peso;
        this.dirigida = dirigida;
    }

    public Vertice getOrigen() { return origen; }
    public Vertice getDestino() { return destino; }
    public double getPeso() { return peso; }
    public boolean isDirigida() { return dirigida; }
}