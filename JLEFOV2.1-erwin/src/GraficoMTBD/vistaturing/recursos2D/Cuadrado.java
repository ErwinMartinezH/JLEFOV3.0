package GraficoMTBD.vistaturing.recursos2D;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Point;

/**
 *
 * @author Palacios López Javier Alberto
 */
public abstract class Cuadrado implements Dibujar {

    protected int x;
    protected int y;
    protected int ancho;
    protected int largo;
    protected Color color;

    private static final int MARGENDEERROR = 5;

    public Cuadrado(int x, int y, int ancho, int largo) {
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.largo = largo;
        this.color = Color.WHITE;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void setAncho(int ancho) {
        this.ancho = ancho;
    }

    public void setLargo(int largo) {
        this.largo = largo;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getAncho() {
        return ancho;
    }

    public int getLargo() {
        return largo;
    }

    public void reiniciarCoordenadas() {

        this.setX(0);
        this.setY(0);
        this.setAncho(0);
        this.setLargo(0);
        this.setColor(Color.WHITE);

    }

    public boolean elRatonEstaDentroDelMedioCuadrado(int ratonX, int ratonY) {

        boolean estaDentro = true;
        Point puntoRaton = new Point(ratonX, ratonY);

        if ((puntoRaton.x <= x + Cuadrado.MARGENDEERROR) || (puntoRaton.x >= x + ancho + Cuadrado.MARGENDEERROR) || (puntoRaton.y <= y + Cuadrado.MARGENDEERROR) || (puntoRaton.y >= y + largo + Cuadrado.MARGENDEERROR)) {
            estaDentro = false;
        }

        return estaDentro;

    }

    public boolean elRatonEstaDentroDelMedioCuadrado(Point p2) {
        return this.elRatonEstaDentroDelMedioCuadrado((int) p2.getX(), (int) p2.getY());
    }

    @Override
    public void dibujarElemento(Graphics2D g2D) {

        g2D.setPaint(this.color);
        g2D.fillRect(x + 2, y + 2, ancho - 2, largo - 2);

        g2D.setStroke(new BasicStroke(1.5f));
        g2D.setColor(Color.BLACK);

        g2D.drawLine(x + ancho, y, x + ancho, y + largo);
        g2D.drawLine(x, y + largo, x + ancho, y + largo);

    }

    @Override
    public String toString() {
        return "Cuadrado [ x:+ " + this.x + " y : " + this.y + " ancho : " + this.ancho + " largo : " + this.largo + " color : " + this.color + " MARGEN DE ERROR " + Cuadrado.MARGENDEERROR + "]";
    }

}
