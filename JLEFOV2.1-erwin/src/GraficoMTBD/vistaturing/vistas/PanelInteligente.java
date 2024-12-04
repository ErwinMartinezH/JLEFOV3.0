
package GraficoMTBD.vistaturing.vistas;

import GraficoMTBD.vistaturing.recursos2D.Estado;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import javax.swing.JPanel;

/**
 *
 * @author
 */
public class PanelInteligente extends JPanel {

  public String estado;
  public String leer;

  public String ir;
  public String movimiento;
  public String operacion;

  public PanelInteligente(String nameEstado, String leer, String irEstado, String movimiento, String operacion) {
    this.setBackground(Color.WHITE);
    this.estado = nameEstado;
    this.leer = leer;
    this.ir = irEstado;
    this.movimiento = movimiento;
    this.operacion = operacion;

  }

  @Override
  public void paintComponent(Graphics g) {
    super.paintComponent(g);
    Graphics2D g2 = (Graphics2D) g;
    int x = 60;
    int y = 15;
    
    g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

    g2.setFont(new Font("Arial", 2, 40));
    //g2.drawString("(", x - 7, y + 17);
    
    g2.setColor(Color.BLACK);
    g2.drawOval(x + 5, y - 8, 35, 35);
    g2.setColor(Color.CYAN);
    g2.fillOval(x + 5, y - 8, 35, 35);

    g2.setColor(Color.BLACK);
    g2.setFont(new Font("Arial", 2, 20));
    g2.drawString(this.estado, x + 12, y + 14);

    g2.setFont(new Font("Arial", 2, 45));
    g2.drawString(",", x + 45, y + 17);

    g2.setFont(new Font("Arial", 2, 40));
    g2.drawString(this.leer, x + 52, y + 22);

    g2.setFont(new Font("Arial", 2, 40));
    //g2.drawString(")", x + 72, y + 17);

    
    g2.drawPolygon(new int[]{x+90, x+90, x+115, x+115, x+140, x+115, x+115}, 
                   new int[]{y-8 ,y+20, y+10 , y+23 , y+5  , y-15 ,  y    }, 7);
    g2.setPaint(Color.GREEN);
    g2.fillPolygon(new int[]{x+90, x+90, x+115, x+115, x+140, x+115, x+115}, 
                   new int[]{y-8 ,y+20, y+10 , y+23 , y+5  , y-15 ,  y    }, 7);
    g2.setFont(new Font("Arial", 2, 40));
    //g2.drawString("=>", x + 85, y + 22);

    g2.setFont(new Font("Arial", 2, 40));
    //g2.drawString("(", x + 135, y + 17);

    
    g2.setColor(Color.BLACK);
    g2.drawOval(x + 150, y - 8, 35, 35);
    g2.setColor(Color.CYAN);
    g2.fillOval(x + 150, y - 8, 35, 35);
        
    g2.setColor(Color.BLACK);
    g2.setFont(new Font("Arial", 2, 20));
    g2.drawString(this.ir, x + 157, y + 14);
    
    g2.setFont(new Font("Arial", 2, 45));
    g2.drawString(",", x + 190, y + 17);
    
    g2.setFont(new Font("Arial", 2, 40));
    g2.drawString(this.movimiento, x + 200, y + 22);

    g2.setFont(new Font("Arial", 2, 45));
    g2.drawString(",", x + 222, y + 17);
    
    g2.setFont(new Font("Arial", 2, 40));
    g2.drawString(this.operacion, x + 236, y + 22);
    
    g2.setFont(new Font("Arial", 2, 40));
    //g2.drawString(")", x + 265, y + 17);

    
  }

}
