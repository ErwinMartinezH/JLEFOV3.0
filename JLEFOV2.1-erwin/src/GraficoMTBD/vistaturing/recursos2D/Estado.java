package GraficoMTBD.vistaturing.recursos2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class Estado extends Cuadrado {

  private final String nombreEstado;
  protected boolean esEstadoInicial = false;
  protected boolean esEstadoFinal = false;
  private boolean esEstadoSalvacion = false;

  public Estado(String nombreEstado, int x, int y, int ancho, int largo) {
    super(x, y, ancho, largo);
    this.nombreEstado = nombreEstado;
  }

  public String getNombreEstado() {
    return nombreEstado;
  }

  public boolean isEsEstadoInicial() {
    return esEstadoInicial;
  }

  public void setEsEstadoInicial(boolean esEstadoInicial) {
    this.esEstadoInicial = esEstadoInicial;
  }

  public boolean isEsEstadoFinal() {
    return esEstadoFinal;
  }

  public void setEsEstadoFinal(boolean esEstadoFinal) {
    this.esEstadoFinal = esEstadoFinal;
  }

  public void setEstadoSalvacionMTB(boolean esEstadoSalvacion) {
    this.esEstadoSalvacion = esEstadoSalvacion;
  }

  @Override
  public void dibujarElemento(Graphics2D g2D) {
    super.dibujarElemento(g2D);

    int x1 = x + ((ancho - (ancho / 2)) / 2);
    int y1 = (y + ((largo - (largo / 2)) / 2));
    int ancho1 = ancho / 2;
    int largo1 = largo / 2;

    if (esEstadoInicial) {
      g2D.drawPolygon(new int[]{x1 - ancho1 / 3, x1 + ancho1 / 150, x1 - ancho1 / 3}, new int[]{y1, y1 + largo1 / 2, y1 + largo1}, 3);
    }

    if (esEstadoFinal) {
      g2D.setColor(Color.BLUE);
      g2D.fillOval(x1 - 5, y1 - 5, ancho1 + 10, largo1 + 10);

      g2D.setColor(Color.BLACK);
      g2D.drawOval(x1 - 5, y1 - 5, ancho1 + 10, largo1 + 10);

    }

    g2D.setColor(Color.CYAN);
    g2D.fillOval(x1, y1, ancho1, largo1);

    g2D.setColor(Color.BLACK);
    g2D.drawOval(x1, y1, ancho1, largo1);

    g2D.setColor(Color.BLACK);
    g2D.setFont(new Font("Times New Roman", Font.PLAIN, 18));
    g2D.drawString(this.nombreEstado, (x1 + ((ancho1 - (ancho1 / 2)) / 2)), ((y1 + ((largo1 + 5) / 2))));

  }

}
