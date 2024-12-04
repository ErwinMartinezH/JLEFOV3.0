package GraficoMTBD.vistaturing.recursos2D;

import java.awt.Dimension;
import java.awt.Graphics2D;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import vista.TuringBidireccional;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class Cinta extends ArrayList<Letra> implements Dibujar {

  protected String alfabeto;

  private int x;
  private int y;
  private int anchoCuadrado;
  private int largoCuadrado;
  private static final int NUMEROBLANCOS = 5;
  private TuringBidireccional vTS;
  protected CabezalCinta cabeza;

  public int minimo = 0;

  public Cinta(TuringBidireccional vTS, String alfabeto, int x, int y, int anchoCuadro, int largoCuadrado) {

    this.x = x;
    this.y = y;
    this.anchoCuadrado = anchoCuadro;
    this.largoCuadrado = largoCuadrado;
    this.alfabeto = alfabeto;
    this.vTS = vTS;

    this.inicializarCinta(x, y, anchoCuadro, largoCuadrado);
    this.cabeza = new CabezalCinta(this.get(0));

  }

  public Dimension maximaDimension() {
    return new Dimension((anchoCuadrado * this.size()) + 50, 120);
  }

  public void cambiarCadena(String nuevaCadena) {

    this.clear();
    int tmpX = x;

    for (int i = 0; i < nuevaCadena.length(); i++) {
      this.add(new Letra(String.valueOf(nuevaCadena.charAt(i)), tmpX, y, anchoCuadrado, largoCuadrado));
      tmpX = tmpX + anchoCuadrado;
    }

    //this.add(new Letra(String.valueOf(Simbolo.BlancoChar), tmpX, y, anchoCuadrado, largoCuadrado));
    this.moverCabezal(0);
    this.hacerEfecto();

  }

  public void cambiarCadena(String nuevaCadena, int index) {

    this.clear();
    int tmpX = x;
    System.out.println("nueva cadena * " + nuevaCadena + " CABEZAL " + index);

    for (int i = 0; i < nuevaCadena.length(); i++) {
      this.add(new Letra(String.valueOf(nuevaCadena.charAt(i)), tmpX, y, anchoCuadrado, largoCuadrado));
      tmpX = tmpX + anchoCuadrado;
    }

    if (index >= this.size()) {
      System.out.println("la cabeza supero el limite de la cadena");

    } else {
      this.moverCabezal(index);
      this.hacerEfecto();

    }
    System.out.println("-----> " + this.get(index).getLetra());

    //lo que pasa aqui es que toma la cinta anterior con letras BBBB 0000 1 BBBB
    //LUEGO VUELVE A HACER LA MISMA OPERACION DEL INICIO LE CONCATENA MÁS LETRAS BBBB BBBB 0000 1 BBBBB
  }

  public static int offset = 0;

  public void agregarCadenaConMetodoAux(ArrayList<String> cadenas, int indiceDeseado, int offset) {
    this.clear();
    int tmpX = x;
    for (int i = 0; i < cadenas.size(); i++) {
      this.add(new Letra(String.valueOf(cadenas.get(i).charAt(0)), tmpX, y, anchoCuadrado, largoCuadrado));
      tmpX = tmpX + anchoCuadrado;
    }

    int indiceReal = indiceDeseado + offset;

   
    
    System.out.println("indice reeal "+indiceReal);

    this.moverCabezal(indiceReal);
    this.hacerEfecto();

  }


  public void cambiarCadena(ArrayList<String> cadenas, int index, int pos, boolean aceptada) {
    this.clear();
    int tmpX = x;

    if (this.minimo >= index) {
      this.minimo = index;
    }

    for (int i = 0; i < cadenas.size(); i++) {
      this.add(new Letra(String.valueOf(cadenas.get(i).charAt(0)), tmpX, y, anchoCuadrado, largoCuadrado));
      tmpX = tmpX + anchoCuadrado;
    }

    int valorReal = index + Math.abs(this.minimo);

    if (index >= this.size()) {
      System.out.println("la cabeza supero el limite de la cadena");

    } else {
      try {
        this.moverCabezal(valorReal);
        this.hacerEfecto();
      } catch (IndexOutOfBoundsException ex) {
        System.out.println("error");
        if (aceptada) {
          JOptionPane.showMessageDialog(null, "La cadena fue aceptadaaaaa");
        } else {
          JOptionPane.showMessageDialog(null, "La cadena fue rechazadaaaaa");
        }

      }

    }

  }

  public void moverCabezal(int index) {
    this.cabeza = new CabezalCinta(this.get(index));
  }

  protected void inicializarCinta(int x, int y, int anchoCuadro, int largoCuadrado) {

    int tmpX = x;

    for (int i = 0; i < alfabeto.length(); i++) {
      this.add(new Letra(String.valueOf(alfabeto.charAt(i)), tmpX, y, anchoCuadro, largoCuadrado));
      tmpX = tmpX + anchoCuadro;
    }

    for (int i = 0; i < 5; i++) {

      this.add(new Letra("B", tmpX, y, anchoCuadro, largoCuadrado));
      tmpX = tmpX + anchoCuadro;

    }

  }

  private void hacerEfecto() {

    for (Letra letra : this) {
      new Hilo(letra, vTS).start();
    }

  }

  @Override
  public void dibujarElemento(Graphics2D g2D) {

    for (Letra letra : this) {

      g2D.drawLine(letra.getX(), letra.getY(), letra.getX() + letra.getAncho(), letra.getY());
      g2D.drawLine(letra.getX(), letra.getY(), letra.getX(), letra.getY() + letra.getLargo());

      letra.dibujarElemento(g2D);
    }

    this.cabeza.dibujarElemento(g2D);

  }

  public String getAlfabeto() {
    return alfabeto;
  }

  private class Hilo extends Thread {

    private Letra letra;
    private TuringBidireccional vTS;

    public Hilo(Letra letra, TuringBidireccional vTS) {
      this.letra = letra;
      this.vTS = vTS;
    }

    @Override
    public void run() {

      int tmpY = letra.getY();
      int i = tmpY;

      while (i <= 80) {
        this.letra.setY(i);
        tmpY = tmpY + i;
        i = i + 5;

        this.vTS.getContenedorCinta().repaint();

        try {
          Thread.sleep(2);
        } catch (InterruptedException ex) {
          System.out.println(ex.getMessage());
        }
      }

      while (i >= 10) {
        this.letra.setY(i);

        tmpY = tmpY - i;

        i = i - 5;

        this.vTS.getContenedorCinta().repaint();
        try {
          Thread.sleep(1);
        } catch (InterruptedException ex) {
          System.out.println(ex.getMessage());
        }
      }

    }

  }
}
