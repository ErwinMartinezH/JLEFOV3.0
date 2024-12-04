package GraficoMTBD.vistaturing.vistas;

import GraficoMTBD.vistaturing.estructurasdedatos.MatrizDinamica;
import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Point2D;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class ContenedorMatriz extends JPanel {

  private MatrizDinamica mD;
  public JScrollPane direccionDeMemoriaScrollPane;

  public ContenedorMatriz(MatrizDinamica mD) {
    this.mD = mD;
    this.setBackground(new Color(249, 249, 249));

  }

  public void setmD(MatrizDinamica mD) {
    this.mD = mD;
  }

  public MatrizDinamica getmD() {
    return mD;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    Graphics2D g2 = (Graphics2D) g;
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
    //pintarGradiente(g2);

    this.mD.dibujarElemento(g2);

    Dimension dm = this.mD.maximaDimensionDeLaMatriz();

    this.setPreferredSize(new Dimension((int) (dm.getWidth() + this.mD.getX()), (int) (dm.getHeight() + this.mD.getY())));
    this.revalidate();

  }

  private void pintarGradiente(Graphics2D g2) {
    int ancho = getWidth();
    int largo = getHeight();

    Color colorFin = new Color(0, 153, 204);
    Color colorInicio = new Color(0, 153, 204);

    GradientPaint gradiente = new GradientPaint(new Point2D.Float(0, 0), colorInicio, new Point2D.Float(0, largo), colorFin);
    g2.setPaint(gradiente);

    float transparencia = 0.5f;
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, transparencia));

    g2.fillRect(0, 0, ancho, largo);
    g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1.0f));
  }

}
