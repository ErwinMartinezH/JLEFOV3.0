package Grafico.vistaturing.utilidades;

import Grafico.vistaturing.utilidades.modelos.MT;
import Grafico.vistaturing.utilidades.modelos.MTContext;

/**
 *
 * @author javi2
 */
public class Almacen {

  private MTContext maquinasTuring;

  public Almacen(MTContext ctx) {
    this.maquinasTuring = ctx;
  }

  public MTContext getAccion() {
    return maquinasTuring;
  }

  @Override
  public String toString() {
    var estadoActual = (maquinasTuring.getInfo().size() > 0) ? maquinasTuring.getInfo().get(0).getValor() : null;
    var simboloActual = (maquinasTuring.getInfo().size() > 1) ? maquinasTuring.getInfo().get(1).getValor() : null;
    var estadoTransicion = (maquinasTuring.getInfo().size() > 2) ? maquinasTuring.getInfo().get(2).getValor() : null;
    var simboloTransicion = (maquinasTuring.getInfo().size() > 3) ? maquinasTuring.getInfo().get(3).getValor() : null;
    MT.Direccion direccionTransicion = (maquinasTuring.getInfo().size() > 4) ? maquinasTuring.getInfo().get(4).getValor() : null;

    return "ACCION "+ this.getAccion().getAccion() +" \n"
            + "  Estado Actual: " + estadoActual + "\n"
            + "  Simbolo Actual: " + simboloActual + "\n"
            + "  Estado Transición: " + estadoTransicion + "\n"
            + "  Símbolo Transición: " + simboloTransicion + "\n"
            + "  Dirección Transición: " + direccionTransicion + "\n"
            + "CINTA VIEJA:\n"
            + "  " + maquinasTuring.getMT().getViejo().getCinta() + "\n"
            + "  POSICION CABEZAL VIEJO       " + this.maquinasTuring.getMT().getViejo().getCinta().getPosicion() + "\n"
            + "CINTA NUEVA:\n"
            + "  " + maquinasTuring.getMT().getNuevo().getCinta() + "\n"
            + "POSICION CABEZAL NUEVO       " + this.maquinasTuring.getMT().getNuevo().getCinta().getPosicion() + "\n";

  }

}
