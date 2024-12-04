package funcionMTBD;

public class Ent {

    Ent sig = null;
    int numeroEstado;
    char lee;
    char escribe;
    char direccion;

    public Ent(int numeroEstado, char lee, char escribe, char direccion) {
        this.lee = lee;
        this.escribe = escribe;
        this.direccion = direccion;
        this.numeroEstado = numeroEstado;
    }

}
