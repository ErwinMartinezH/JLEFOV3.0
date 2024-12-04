package Grafico.vistaturing.vistas;

import Grafico.vistaturing.recursos2D.Cinta;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import javax.swing.JPanel;
import vista.TuringMachineSimulation;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class ContenedorCinta extends JPanel {

    private Cinta cintaTuring;
    private TuringMachineSimulation vTS;

    public ContenedorCinta(Cinta cintaTuring, TuringMachineSimulation vTS) {
        this.cintaTuring = cintaTuring;
        this.vTS = vTS;

        this.setBackground(new Color(249,249,249));

    }

    public Cinta getCintaTuring() {
        return cintaTuring;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        
        this.cintaTuring.dibujarElemento(g2);
        
        Dimension dimension = new Dimension((int)this.cintaTuring.maximaDimension().getWidth(), 90);
        
        this.vTS.getPanelCinta().setPreferredSize(dimension);
        this.vTS.getPanelCinta().revalidate();
    }

}
