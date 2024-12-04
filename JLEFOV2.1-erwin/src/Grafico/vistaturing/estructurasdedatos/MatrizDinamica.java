package Grafico.vistaturing.estructurasdedatos;

import Grafico.vistaturing.recursos2D.Transicion;
import Grafico.vistaturing.recursos2D.Estado;
import Grafico.vistaturing.recursos2D.Letra;
import Grafico.vistaturing.recursos2D.Dibujar;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Map.Entry;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class MatrizDinamica extends LinkedHashMap<Estado, ArrayList<Transicion>> implements Dibujar {

  private int x;
  private int y;
  private int anchoCuadrado;
  private int largoCuadrado;
  private int numeroDeEstadosFilas;
  private int numeroDeColumnasLetra;

  private ArrayList<Letra> listaDeLetrasColumnas;

  public MatrizDinamica(int x, int y, int anchoCuadrado, int largoCuadrado, int numeroDeEstadosFilas, int numeroDeColumnasLetra) {
    this.x = x;
    this.y = y;
    this.anchoCuadrado = anchoCuadrado;
    this.largoCuadrado = largoCuadrado;
    this.numeroDeEstadosFilas = numeroDeEstadosFilas;
    this.numeroDeColumnasLetra = numeroDeColumnasLetra;
    //this.inicializarMatriz();
  }

  public ArrayList<Letra> getListaDeLetrasColumnas() {
    return listaDeLetrasColumnas;
  }

  public void setListaDeLetrasColumnas(ArrayList<Letra> listaDeLetrasColumnas) {
    this.listaDeLetrasColumnas = listaDeLetrasColumnas;
  }

  public void setX(int x) {
    this.x = x;
  }

  public void setY(int y) {
    this.y = y;
  }

  public void setAnchoCuadrado(int anchoCuadrado) {
    this.anchoCuadrado = anchoCuadrado;
  }

  public void setLargoCuadrado(int largoCuadrado) {
    this.largoCuadrado = largoCuadrado;
  }

  public Dimension maximaDimensionDeLaMatriz() {
    return new Dimension(((this.listaDeLetrasColumnas.size()) * this.anchoCuadrado) + this.anchoCuadrado * 2, (this.keySet().size() * (this.largoCuadrado)) + this.largoCuadrado * 2);

  }

  public Estado obtenerReferenciaDeMemoriaDelEstado(int x, int y) {

    for (Estado estado : this.keySet()) {

      if (estado.elRatonEstaDentroDelMedioCuadrado(x, y)) {
        return estado;
      }

    }
    return null;
  }

  public Letra obtenerReferenciaDeMemoriaDeLaLetra(int x, int y) {

    for (Letra letra : this.listaDeLetrasColumnas) {

      if (letra.elRatonEstaDentroDelMedioCuadrado(x, y)) {
        return letra;
      }

    }

    return null;

  }

  public int getX() {
    return x;
  }

  public int getY() {
    return y;
  }

  public void eliminarUnCaracter() {

    this.listaDeLetrasColumnas.remove(this.listaDeLetrasColumnas.size() - 1);
    for (Map.Entry<Estado, ArrayList<Transicion>> e1 : this.entrySet()) {

      e1.getValue().remove(e1.getValue().size() - 1);

    }

  }

  public void insertarUnNuevoCaracter() {
    Letra ultimaLetra = this.listaDeLetrasColumnas.get(this.listaDeLetrasColumnas.size() - 1);
    this.listaDeLetrasColumnas.add(new Letra(String.valueOf("-"), ultimaLetra.getX() + this.anchoCuadrado, ultimaLetra.getY(), ultimaLetra.getAncho(), ultimaLetra.getLargo()));

    for (Map.Entry<Estado, ArrayList<Transicion>> e1 : this.entrySet()) {

      Transicion ultimaTransicion = e1.getValue().get(e1.getValue().size() - 1);
      e1.getValue().add(new Transicion(ultimaTransicion.getX() + this.anchoCuadrado, ultimaTransicion.getY(), ultimaTransicion.getAncho(), ultimaTransicion.getLargo(), "NA", ultimaLetra, "NA", false));

    }
  }

  public void quitarElUltimoEstado() {
    Entry<Estado, ArrayList<Transicion>> ultimoElemento = this.obtenerElUltimoEstado();
    if (ultimoElemento != null) {
      this.remove(ultimoElemento.getKey());
    }

  }

  public void insertarNuevoEstado() {

    Entry<Estado, ArrayList<Transicion>> ultimoElemento = this.obtenerElUltimoEstado();

    if (ultimoElemento != null) {

      ArrayList<Transicion> tr = new ArrayList<>();
      for (int i = 0; i < ultimoElemento.getValue().size(); i++) {
        tr.add(new Transicion(ultimoElemento.getValue().get(i).getX(), ultimoElemento.getValue().get(i).getY() + this.largoCuadrado, ultimoElemento.getValue().get(i).getAncho(), ultimoElemento.getValue().get(i).getLargo(), "NA", this.listaDeLetrasColumnas.get(i), "NA", false));
      }

      String name = this.obtenerElUltimoEstado().getKey().getNombreEstado().substring(1, this.obtenerElUltimoEstado().getKey().getNombreEstado().length());
      int numero = Integer.valueOf(name);
      this.put(new Estado(String.valueOf("q" + (numero + 1)), ultimoElemento.getKey().getX(), ultimoElemento.getKey().getY() + this.largoCuadrado, ultimoElemento.getKey().getAncho(), ultimoElemento.getKey().getLargo()), tr);

    } else {
      System.out.println("El LinkedHashMap está vacío.");
    }
  }

  public Entry<Estado, ArrayList<Transicion>> obtenerElUltimoEstado() {

    Entry<Estado, ArrayList<Transicion>> ultimoElemento = null;
    for (Entry<Estado, ArrayList<Transicion>> entry : this.entrySet()) {
      ultimoElemento = entry;
    }
    return ultimoElemento;
  }

  public Transicion obtenerReferenciaDeMemoriaDeLaTransicion(int x, int y) {

    for (Estado estado : this.keySet()) {

      for (Transicion transicion : this.get(estado)) {

        if (transicion.elRatonEstaDentroDelMedioCuadrado(x, y)) {
          return transicion;
        }

      }

    }

    return null;
  }

  public Letra obtenerReferenciaDeMemoriaDeLaLetra(String letra){
    
    for (var letras : this.getListaDeLetrasColumnas()) {
      if(letras.getLetra().equals(letra)){
        return letras;
      }
    }
    
    
    return null;
  }
  
  
  public Transicion obtenerReferenciaDeMemoriaDeLaTransicion(int numberState, String letra) {

    for (Estado estado1 : this.keySet()) {

      String state = "q" + numberState;
      if (estado1.getNombreEstado().equals(state)) {

        ArrayList<Transicion> transicionesPosEstado1 = this.get(estado1);
        ArrayList<Letra> transicionLetras = this.getListaDeLetrasColumnas();

        int tr = 0;
        for (Letra l : transicionLetras) {

          if (l.getLetra().equals(letra)) {

            return transicionesPosEstado1.get(tr);
            
          }
          tr = tr + 1;

        }

      }
    }

    return null;
  }

  public void reacondicionarMatriz(int xO, int yO, int anchoO, int largoO) {

    int x = xO;
    int y = yO;
    int ancho = anchoO;
    int largo = largoO;

    for (Letra letra : this.listaDeLetrasColumnas) {

      letra.setX(x + ancho);
      letra.setY(y);
      letra.setAncho(ancho);
      letra.setLargo(largo);

      x = x + ancho;

    }

    x = xO;
    y = yO;
    ancho = anchoO;
    largo = largoO;

    for (Map.Entry<Estado, ArrayList<Transicion>> e1 : this.entrySet()) {

      e1.getKey().setX(x);
      e1.getKey().setY(y + largo);
      e1.getKey().setAncho(ancho);
      e1.getKey().setLargo(largo);

      for (Transicion tr : e1.getValue()) {

        tr.setX(x + ancho);
        tr.setY(y + largo);
        tr.setAncho(ancho);
        tr.setLargo(largo);

        x = x + ancho;
      }
      x = xO;
      y = y + largo;

    }

  }

  public void inicializarMatriz() {

    ArrayList<Estado> listaDeEstadosFilas = new ArrayList<>();

    int y = this.y + this.largoCuadrado;

    for (int i = 0; i < numeroDeEstadosFilas; i++) {
      Estado estado = new Estado(String.valueOf("q" + i), this.x, y, this.anchoCuadrado, this.largoCuadrado);
      listaDeEstadosFilas.add(estado);
      y = y + this.largoCuadrado;

    }

    int x = this.x + this.anchoCuadrado;
    listaDeLetrasColumnas = new ArrayList<>();

    for (int i = 0; i < numeroDeColumnasLetra; i++) {

      Letra letra = new Letra(String.valueOf(i), x, this.y, this.anchoCuadrado, this.largoCuadrado);
      listaDeLetrasColumnas.add(letra);
      x = x + this.anchoCuadrado;
    }

    int puntoInicioX1 = this.x + this.anchoCuadrado;
    int puntoInicioY1 = this.y + this.largoCuadrado;

    for (int y1 = 0; y1 < numeroDeEstadosFilas; y1++) {

      ArrayList<Transicion> transiciones = new ArrayList<>();
      int tmp = puntoInicioX1;

      for (int x1 = 0; x1 < numeroDeColumnasLetra; x1++) {
        Transicion t = new Transicion(puntoInicioX1, puntoInicioY1, this.anchoCuadrado, this.largoCuadrado, "NA", this.listaDeLetrasColumnas.get(x1), "NA", false);
        transiciones.add(t);
        puntoInicioX1 = puntoInicioX1 + this.anchoCuadrado;
      }

      this.put(listaDeEstadosFilas.get(y1), transiciones);
      puntoInicioX1 = tmp;
      puntoInicioY1 = puntoInicioY1 + this.largoCuadrado;
    }

  }

  @Override
  public String toString() {
    return super.toString();
  }

  public Transicion direccionDeMemoriaDeLaTransicion(int indiceDelEstado, int indiceDeLaTransicion) {

    Estado tmpEstado = this.direccionDeMemoriaDelEstadoEn(indiceDelEstado);

    if (tmpEstado != null) {
      int indiceTransicion = 0;

      for (Transicion t1 : this.get(tmpEstado)) {

        if (indiceDeLaTransicion == indiceTransicion) {
          return t1;
        }
        indiceDeLaTransicion = indiceDeLaTransicion + 1;

      }

    }

    return null;
  }

  public Estado direccionDeMemoriaDelEstadoEn(int indice) {

    int contador = 0;

    for (Map.Entry<Estado, ArrayList<Transicion>> e1 : this.entrySet()) {

      if (indice == contador) {
        return e1.getKey();
      }
      contador = contador + 1;
    }

    return null;
  }

  public MatrizDinamica eliminarColumnaEstado(Estado estado) {

    MatrizDinamica mDTemp = new MatrizDinamica(x, y, anchoCuadrado, largoCuadrado, numeroDeEstadosFilas, numeroDeColumnasLetra);
    mDTemp.setListaDeLetrasColumnas(listaDeLetrasColumnas);

    int y = 0;
    boolean encontreEstadoHash = false;

    for (Map.Entry<Estado, ArrayList<Transicion>> e1 : this.entrySet()) {

      if (e1.getKey() == estado) {
        encontreEstadoHash = true;
        y = estado.getY();
        continue;
      }

      if (encontreEstadoHash) {
        e1.getKey().setY(y);
        for (int i = 0; i < e1.getValue().size(); i++) {
          e1.getValue().get(i).setY(y);
        }
        y = y + this.largoCuadrado;

      }
      mDTemp.put(e1.getKey(), e1.getValue());

    }
    return mDTemp;
  }

  public MatrizDinamica eliminarFilaLetra(Letra direccionDeMemoriaLetra) {

    MatrizDinamica nuevaMatrizDinamica = new MatrizDinamica(this.x, this.y, this.anchoCuadrado, this.largoCuadrado, numeroDeEstadosFilas, numeroDeColumnasLetra);

    ArrayList<Letra> nuevasLetras = new ArrayList<>();

    int x = this.x + this.anchoCuadrado;

    for (int i = 0; i < this.listaDeLetrasColumnas.size(); i++) {

      if (this.listaDeLetrasColumnas.get(i) != direccionDeMemoriaLetra) {

        nuevasLetras.add(this.listaDeLetrasColumnas.get(i));
        this.listaDeLetrasColumnas.get(i).setX(x);
        x = x + this.anchoCuadrado;
      }

    }

    nuevaMatrizDinamica.setListaDeLetrasColumnas(nuevasLetras);

    int puntoInicioX1 = this.x + this.anchoCuadrado;
    int puntoInicioY1 = this.y + this.largoCuadrado;

    for (Estado estado : this.keySet()) {

      ArrayList<Transicion> mapaTransiciones = new ArrayList<>();
      int tmp = puntoInicioX1;

      for (int i = 0; i < this.get(estado).size(); i++) {

        if (this.listaDeLetrasColumnas.get(i) != direccionDeMemoriaLetra) {

          mapaTransiciones.add(this.get(estado).get(i));
          this.get(estado).get(i).setX(puntoInicioX1);
          this.get(estado).get(i).setY(puntoInicioY1);
          puntoInicioX1 = puntoInicioX1 + this.anchoCuadrado;
        }

      }
      puntoInicioX1 = tmp;

      puntoInicioY1 = puntoInicioY1 + this.largoCuadrado;

      nuevaMatrizDinamica.put(estado, mapaTransiciones);

    }

    return nuevaMatrizDinamica;

  }

  public void purgarCuadros() {
    for (Estado estado : this.keySet()) {

      for (Transicion t : this.get(estado)) {

        t.setColor(Color.WHITE);

      }
      estado.setColor(Color.WHITE);
    }

    for (Letra letra : this.listaDeLetrasColumnas) {
      letra.setColor(Color.WHITE);
    }

  }

  public Estado referenciaPorTransicion(Transicion transicion) {

    for (Estado estado : this.keySet()) {
      ArrayList<Transicion> transiciones = this.get(estado);

      for (int i = 0; i < transiciones.size(); i++) {

        if (transiciones.get(i) == transicion) {
          return estado;
        }

      }
    }
    return null;
  }

  @Override
  public void dibujarElemento(Graphics2D g2D) {

    //new Transicion(0, 0, this.anchoCuadrado, this.largoCuadrado, "", new Letra("null",0,0,0,0), "", false).dibujarElemento(g2D);
    for (Letra l : this.listaDeLetrasColumnas) {
      l.dibujarElemento(g2D);
    }

    for (Estado estado : this.keySet()) {

      for (Transicion transicion : this.get(estado)) {

        transicion.dibujarElemento(g2D);

      }

      estado.dibujarElemento(g2D);

    }

  }

}
