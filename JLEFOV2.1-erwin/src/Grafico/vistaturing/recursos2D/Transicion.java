package Grafico.vistaturing.recursos2D;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class Transicion extends Cuadrado {

    private String estado;
    private Letra letra;
    private String movimiento;

    private boolean puedoDibujarLaTransicion;

    public Transicion(int x, int y, int ancho, int largo, String estado, Letra letra, String movimiento, boolean puedoDibujarLaTransicion) {
        super(x, y, ancho, largo);
        this.estado = estado;
        this.letra = letra;
        this.movimiento = movimiento;
        this.puedoDibujarLaTransicion = puedoDibujarLaTransicion;
    }
      
    public void setEstado(String estado) {
        this.estado = estado;
    }

    public void setLetra(Letra letra) {
        this.letra = letra;
    }

    public void setMovimiento(String movimiento) {
        this.movimiento = movimiento;
    }
    
    public void setPuedoDibujarLaTransicion(boolean puedoDibujarLaTransicion) {
        this.puedoDibujarLaTransicion = puedoDibujarLaTransicion;
    }

    public String getEstado() {
        return estado;
    }

    public Letra getLetra() {
        return letra;
    }

    public String getMovimiento() {
        return movimiento;
    }
    
    

    @Override
    public void dibujarElemento(Graphics2D g2D) {
        super.dibujarElemento(g2D);

        g2D.setFont(new Font("Arial", Font.PLAIN, 15));

        g2D.setColor(Color.BLACK);
        if (puedoDibujarLaTransicion) {
            g2D.drawString((this.estado + "," + this.letra.getLetra() + "," + this.movimiento), x+5 , y + largo / 2);
        }

    }

    @Override
    public String toString() {
        //String padre = super.toString();
        
        return /*padre+" \n "+*/"Transicion [  estado : "+this.estado+" direccionLetra: "+this.letra.getLetra()+" movimiento: "+this.movimiento+"]";
    }
    
    
    

}
