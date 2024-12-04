package Grafico.vistaturing.utilidades;

import Grafico.vistaturing.recursos2D.Transicion;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import vista.TuringMachineSimulation;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class CuadroDialogo extends JDialog {

  private JComboBox<String> comboBox1;
  private JComboBox<String> comboBox2;
  private JComboBox<String> comboBox3;
  private TuringMachineSimulation mt;

  public CuadroDialogo(JFrame parent, JComboBox<String> comboBox_1, JComboBox<String> comboBox_2, JComboBox<String> comboBox_3, TuringMachineSimulation m_t, int x, int y) {
    super(parent, "Selecciona opciones", true);
    
    comboBox1 = comboBox_1;
    comboBox2 = comboBox_2;
    comboBox3 = comboBox_3;
    mt = m_t;

    JPanel panel = new JPanel(new GridLayout(3, 2));
    panel.add(new JLabel("Estado:"));
    panel.add(comboBox1);
    panel.add(new JLabel("Alfabeto:"));
    panel.add(comboBox2);
    panel.add(new JLabel("Movimiento:"));
    panel.add(comboBox3);

    JButton okButton = new JButton("Confirmar");
    JButton cancelButton = new JButton("Cancelar");

    okButton.addActionListener(e -> {
      String estado = (String) comboBox1.getSelectedItem();
      String lecturaE = (String) comboBox2.getSelectedItem();
      String movimiento = (String) comboBox3.getSelectedItem();
      
      
      Transicion t = this.mt.getcM().getmD().obtenerReferenciaDeMemoriaDeLaTransicion(x, y);
      t.setPuedoDibujarLaTransicion(true);
      t.setEstado(estado);
      t.setLetra(m_t.getcM().getmD().getListaDeLetrasColumnas().get(comboBox_2.getSelectedIndex()));
      t.setMovimiento(movimiento);
      
      
     //this.vistaTuringSimple.getMatrizTuring().getMatriz2D()[x][0].setLecturaEscritura(this.vistaTuringSimple.getMatrizTuring().getAlfabeto()[x1]);

      
  /*    this.mt.getMatrizTuring().getMatriz2D()[x][y].setPuedoDibujarLetras(true);
      this.mt.getMatrizTuring().getMatriz2D()[x][y].setNombreEstado(estado);
      this.mt.getMatrizTuring().getMatriz2D()[x][y].setLecturaEscritura(m_t.getMatrizTuring().getAlfabeto()[comboBox2.getSelectedIndex()]);
      this.mt.getMatrizTuring().getMatriz2D()[x][y].setMovimientoCinta(movimiento);
      
      this.mt.getMatrizTuring().repaint();*/

      dispose();
    });

    cancelButton.addActionListener(e -> {
      dispose();
    });
    
    this.setBackground(new Color(0,153,204));
    this.getContentPane().setBackground(new Color(0,153,204));
    JPanel buttonPanel = new JPanel();
    buttonPanel.setBackground(Color.WHITE);
    buttonPanel.add(okButton);
    buttonPanel.add(cancelButton);

    add(panel, BorderLayout.CENTER);
    add(buttonPanel, BorderLayout.SOUTH);

    pack();
    setLocationRelativeTo(parent);
    setResizable(false);
  }

}
