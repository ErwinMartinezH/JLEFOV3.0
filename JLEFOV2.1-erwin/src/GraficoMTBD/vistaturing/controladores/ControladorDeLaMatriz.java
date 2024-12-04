package GraficoMTBD.vistaturing.controladores;

import GraficoMTBD.vistaturing.utilidades.modelos.Simbolo;
import GraficoMTBD.vistaturing.recursos2D.Transicion;
import GraficoMTBD.vistaturing.utilidades.CuadroDialogo;
import GraficoMTBD.vistaturing.recursos2D.Estado;
import GraficoMTBD.vistaturing.recursos2D.Letra;
import java.awt.Color;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPopupMenu;
import vista.TuringBidireccional;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class ControladorDeLaMatriz implements MouseListener, MouseMotionListener {

  private TuringBidireccional vTS;
  private JPopupMenu popupMenu;

  public ControladorDeLaMatriz(TuringBidireccional vTS) {
    this.vTS = vTS;
    this.vTS.getPanelDibujo().addMouseListener(this);
    this.vTS.getPanelDibujo().addMouseMotionListener(this);
    this.popupMenu = new JPopupMenu();
  }

  public void eliminarReferenciasDelMouse() {
    this.vTS.getPanelDibujo().removeMouseListener(this);
    this.vTS.getPanelDibujo().removeMouseMotionListener(this);
  }

  @Override
  public void mouseClicked(MouseEvent e) {

  }

  @Override
  public void mousePressed(MouseEvent e) {

    Transicion transicion = this.vTS.getcM().getmD().obtenerReferenciaDeMemoriaDeLaTransicion(e.getX(), e.getY());
    Estado estado = this.vTS.getcM().getmD().obtenerReferenciaDeMemoriaDelEstado(e.getX(), e.getY());
    Letra letra = this.vTS.getcM().getmD().obtenerReferenciaDeMemoriaDeLaLetra(e.getX(), e.getY());

    if (e.getButton() == 1 && transicion != null) {

      Estado es = this.vTS.getcM().getmD().referenciaPorTransicion(transicion);

      if (es.isEsEstadoFinal()) {
        JOptionPane.showMessageDialog(this.vTS, "No se pueden agregar transiciones", "Estado final", JOptionPane.ERROR_MESSAGE);
      } else {

        transicion.setColor(Color.MAGENTA);
        this.vTS.getPanelDibujo().repaint();

        JComboBox<String> estadoCombo = new JComboBox<String>() {
          {
            for (Estado estado : vTS.getcM().getmD().keySet()) {
              this.addItem(estado.getNombreEstado());
            }
          }
        };

        JComboBox<String> alfabetoCombo = new JComboBox<String>() {
          {
            for (Letra letra : vTS.getcM().getmD().getListaDeLetrasColumnas()) {
              this.addItem(letra.getLetra());
            }
          }
        };

        JComboBox<String> movimientoCombo = new JComboBox<String>() {
          {
            this.addItem("D");
            this.addItem("I");
          }
        };

        CuadroDialogo cD = new CuadroDialogo(null, estadoCombo, alfabetoCombo, movimientoCombo, vTS, e.getX(), e.getY());
        cD.setVisible(true);
      }

    } else if (e.getButton() == 3 && transicion != null) {

      System.out.println("eliminar transicion");
      transicion.setColor(Color.RED);
      this.vTS.getPanelDibujo().repaint();
      int dato = JOptionPane.showConfirmDialog(null, "¿Deseas eliminar la transicion?");

      if (dato == 0) {

        transicion.setEstado("NA");
        transicion.setMovimiento("NA");
        transicion.setPuedoDibujarLaTransicion(false);

      }

      transicion.setColor(Color.WHITE);
      this.vTS.getPanelDibujo().repaint();

    } else if (e.getButton() == 1 && estado != null) {
      ///estado.setColor(Color.YELLOW);

    } else if (e.getButton() == 1 && letra != null) {

      String parametro = JOptionPane.showInputDialog("Ingresa el nuevo valor para lectura");

      if (parametro != null) {
        if (parametro.equals("\\0")) {
          letra.setLetra(String.valueOf(Simbolo.BlancoChar));
        } else if (parametro.length() == 1) {
          letra.setLetra(parametro);
        } else {
          JOptionPane.showMessageDialog(vTS, "Longitud excedida", "Error", JOptionPane.ERROR_MESSAGE);
        }
      }

    } else if (e.getButton() == 3 && letra != null) {
      letra.setColor(Color.RED);
      this.vTS.getPanelDibujo().repaint();
      int op = JOptionPane.showConfirmDialog(vTS, "¿Estas seguro que deseas eliminar la fila?", "Eliminar", JOptionPane.YES_NO_OPTION, JOptionPane.ERROR_MESSAGE);

      if (op == 0) {

        if ((int) this.vTS.getSpinnerCaracteres().getValue() - 1 >= 1) {
          int xDesplazamiento = (int) this.vTS.getSpinnerCaracteres().getValue() * 100 <= 470 ? 470 - (int) this.vTS.getSpinnerCaracteres().getValue() * 100 : 0;

          this.vTS.getcM().setmD(this.vTS.getcM().getmD().eliminarFilaLetra(letra));
          this.vTS.getSpinnerCaracteres().setValue(this.vTS.getcM().getmD().getListaDeLetrasColumnas().size());
          this.vTS.getcM().getmD().reacondicionarMatriz(xDesplazamiento, 0, 100, 65);
        } else {
          JOptionPane.showMessageDialog(this.vTS, "Ya no hay nada más que eliminar");
        }

      } else if (op == 1) {
        letra.setColor(Color.WHITE);
      }

    } else if (e.getButton() == 3 && estado != null) {

      popupMenu = new JPopupMenu();
      JMenuItem estadoInicial = new JMenuItem("Hacer estado inicial");
      JMenuItem estadoFinal = new JMenuItem("Hacer estado final");
      JMenuItem deshacerSeleccion = new JMenuItem("Deshacer selección");
      JMenuItem eliminarEstado = new JMenuItem("Eliminar estado");

      eliminarEstado.addActionListener(actionEvent -> {

        if ((int) this.vTS.getSpinnerEstados().getValue() - 1 >= 1) {

          this.vTS.getcM().setmD(this.vTS.getcM().getmD().eliminarColumnaEstado(estado));
          this.vTS.getSpinnerEstados().setValue(this.vTS.getcM().getmD().size());

          this.vTS.getPanelDibujo().repaint();
        } else {
          JOptionPane.showMessageDialog(this.vTS, "Ya no hay nada más que eliminar");
        }
      });

      estadoInicial.addActionListener(actionEvent -> {

        estado.setEsEstadoInicial(true);
        estado.setEsEstadoFinal(false);

        this.vTS.getPanelDibujo().repaint();
      });

      estadoFinal.addActionListener(actionEvent -> {

        estado.setEsEstadoInicial(false);
        estado.setEsEstadoFinal(true);

        ArrayList<Transicion> tr = this.vTS.getcM().getmD().get(estado);

        for (Transicion tra : tr) {
          tra.setPuedoDibujarLaTransicion(false);
        }

        this.vTS.getPanelDibujo().repaint();

      });

      deshacerSeleccion.addActionListener(actionEvent -> {
        estado.setEsEstadoInicial(false);
        estado.setEsEstadoFinal(false);

        this.vTS.getPanelDibujo().repaint();
      });

      popupMenu.add(estadoInicial);
      popupMenu.add(estadoFinal);
      popupMenu.add(deshacerSeleccion);
      popupMenu.add(eliminarEstado);
      popupMenu.show(e.getComponent(), e.getX(), e.getY());

      vTS.getPanelDibujo().repaint();

    }

    this.vTS.getPanelDibujo().repaint();

  }

  @Override
  public void mouseReleased(MouseEvent e) {

  }

  @Override
  public void mouseEntered(MouseEvent e) {

  }

  @Override
  public void mouseExited(MouseEvent e) {

  }

  @Override
  public void mouseDragged(MouseEvent e) {

  }

  @Override
  public void mouseMoved(MouseEvent e) {

    this.popupMenu.setVisible(false);
    this.vTS.getcM().getmD().purgarCuadros();

    Transicion transicion = this.vTS.getcM().getmD().obtenerReferenciaDeMemoriaDeLaTransicion(e.getX(), e.getY());
    Estado estado = this.vTS.getcM().getmD().obtenerReferenciaDeMemoriaDelEstado(e.getX(), e.getY());
    Letra letra = this.vTS.getcM().getmD().obtenerReferenciaDeMemoriaDeLaLetra(e.getX(), e.getY());

    if (transicion != null) {
      transicion.setColor(Color.GREEN);
    } else if (estado != null) {
      estado.setColor(Color.YELLOW);
    } else if (letra != null) {
      letra.setColor(Color.CYAN);
    }

    this.vTS.getPanelDibujo().repaint();

  }



}
