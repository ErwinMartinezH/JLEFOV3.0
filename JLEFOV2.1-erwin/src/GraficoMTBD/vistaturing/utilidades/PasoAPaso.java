package GraficoMTBD.vistaturing.utilidades;

import GraficoMTBD.vistaturing.recursos2D.Estado;
import GraficoMTBD.vistaturing.recursos2D.Transicion;

/**
 *
 * @author Javier Palacios
 */
public class PasoAPaso {

  private final Estado direccionDeMemoriaEstado;
  private final Transicion direccionDeMemoriaTransicion;
  private final int posicionCabezal;
  private final String accion;
  private final String cinta;
  
  public PasoAPaso(Estado direccionDeMemoriaEstado, Transicion direccionDeMemoriaTransicion, int posicionCabezal, String accion, String cinta) {
    this.direccionDeMemoriaEstado = direccionDeMemoriaEstado;
    this.direccionDeMemoriaTransicion = direccionDeMemoriaTransicion;
    this.posicionCabezal = posicionCabezal;
    this.accion = accion;
    this.cinta = cinta;
  }

  public Estado getDireccionDeMemoriaEstado() {
    return direccionDeMemoriaEstado;
  }

  public Transicion getDireccionDeMemoriaTransicion() {
    return direccionDeMemoriaTransicion;
  }

  public int getPosicionCabezal() {
    return posicionCabezal;
  }

  public String getAccion() {
    return accion;
  }

  public String getCinta() {
    return cinta;
  }

  
}
