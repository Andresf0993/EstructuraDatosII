public class Vertice {
    private final String id;
    private final String lugar;
    private int grado;
    private int gradoSalida;
    private int gradoEntrada;

    public Vertice(String id, String lugar) {
        this.id = id;
        this.lugar = lugar;
    }

    public String getId() { return id; }
    public String getLugar() { return lugar; }
    public int getGrado() { return grado; }
    public int getGradoSalida() { return gradoSalida; }
    public int getGradoEntrada() { return gradoEntrada; }

    void registrarAristaNoDirigida() {
        grado++;
        gradoSalida++;
        gradoEntrada++;
    }

    void registrarSalidaDirigida() {
        grado++;
        gradoSalida++;
    }

    void registrarEntradaDirigida() {
        grado++;
        gradoEntrada++;
    }
}
