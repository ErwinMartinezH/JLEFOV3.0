package funcionMTBD;

public class Estado {

    char simbolo;
    boolean bandera = false; // determina si es un estado de aceptacion
    Ent fr = null;
    Ent fin = null;

    public void agregar(int q, char lee, char escribe, char dir) { // crea las entradas del estado

        Ent nuevo = new Ent(q, lee, escribe, dir);// Crea una cola de las entradas de cada estado
        if (fr == null) {
            fr = nuevo;
            fin = nuevo;
        } else {
            fin.sig = nuevo;
            fin = nuevo;
            {
            }
        }
    }

    public String paso(char simbolo) { // recibe el el simbolo que lee y el estado actual y manda los nuevos junto con la direccion
        Ent aux = fr;
        boolean bandera = false;
        String texto = "";
        while (aux != null) {
            if (aux.lee == simbolo) {
                texto = String.valueOf(aux.numeroEstado);
                bandera = true;
                break;
            } else {
                aux = aux.sig;
            }
        }
        if (bandera == true) {
            texto = texto + "," + aux.escribe + "," + aux.direccion; // 
            //System.out.println(texto);
            return texto;
        } else {
            return "rechazo";
        }
    }

    public void contar() {

    }
}
