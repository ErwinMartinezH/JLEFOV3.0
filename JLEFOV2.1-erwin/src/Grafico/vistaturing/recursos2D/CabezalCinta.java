package Grafico.vistaturing.recursos2D;

import java.awt.Color;
import java.awt.Graphics2D;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class CabezalCinta implements Dibujar {

    private Letra direccionDeMemoriaLetra;
    private int x;
    private int y;
    private int ancho;
    private int largo;
    private int index;
    
    public CabezalCinta(Letra direccionDeMemoriaLetra) {
        this.setDireccionDeMemoriaLetra(direccionDeMemoriaLetra);
    }
    
    public Letra getDireccionDeMemoriaLetra() {
        return direccionDeMemoriaLetra;
    }

    public void setDireccionDeMemoriaLetra(Letra direccionDeMemoriaLetra) {
        this.direccionDeMemoriaLetra = direccionDeMemoriaLetra;

        this.x = direccionDeMemoriaLetra.getX() + this.direccionDeMemoriaLetra.getAncho() / 2;
        this.y = direccionDeMemoriaLetra.getY() + this.direccionDeMemoriaLetra.getLargo() + 3;
        this.ancho = 20;
        this.largo = 20;
        
    }

    
    @Override
    public void dibujarElemento(Graphics2D g2D) {
        g2D.setColor(Color.RED);
        g2D.fillPolygon(new int[]{x, x - ancho, x + ancho, x}, new int[]{y, y + largo, y + largo, y}, 4);
        g2D.setColor(Color.BLACK);
        g2D.drawPolygon(new int[]{x, x - ancho, x + ancho, x}, new int[]{y, y + largo, y + largo, y}, 4);

    }

}
