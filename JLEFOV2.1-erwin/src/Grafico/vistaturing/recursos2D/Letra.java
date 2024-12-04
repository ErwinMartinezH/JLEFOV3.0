package Grafico.vistaturing.recursos2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class Letra extends Cuadrado {

    protected String letra;

    public Letra(String letra, int x, int y, int ancho, int largo) {
        super(x, y, ancho, largo);
        this.letra = letra;
    }

    public String getLetra() {
        return letra;
    }

    public void setLetra(String letra) {
        this.letra = letra;
    }
    
    @Override
    public void dibujarElemento(Graphics2D g2D) {
        super.dibujarElemento(g2D);

        g2D.setColor(Color.BLACK);
        g2D.setFont(new Font("Times New Roman", Font.BOLD, 50));
        g2D.drawString(this.letra, x + ancho / 4, y + (largo-10) );

    }

}
