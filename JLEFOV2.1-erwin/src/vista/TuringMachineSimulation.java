package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;
import Grafico.vistaturing.controladores.ControladorDeLaMatriz;
import Grafico.vistaturing.controladores.ControladorSwing;
import Grafico.vistaturing.estructurasdedatos.MatrizDinamica;
import Grafico.vistaturing.recursos2D.Cinta;
import Grafico.vistaturing.recursos2D.Letra;
import Grafico.vistaturing.utilidades.modelos.Simbolo;
import Grafico.vistaturing.vistas.ContenedorCinta;
import Grafico.vistaturing.vistas.ContenedorMatriz;
import GraficoMTBD.vistaturing.utilidades.leercsv.TuringMachine;
import java.util.ArrayList;

/**
 *
 * @author Erwin Martinez
 */
public class TuringMachineSimulation extends javax.swing.JPanel {

  private DefaultTableModel tableModel;
  private boolean isRunning = false;
  private int currentStep = 0;
  private String tape = "";
  private int headPosition = 0;
  private String currentState = "q0";
  private String textToDraw = "";

  private ContenedorMatriz cM;
  private ContenedorCinta contenedorCinta;

  /**
   * Creates new form TuringMachineSimulation
   */
  public TuringMachineSimulation() {
   initComponents();
    setupTable();
    

    if (true) {
      this.jLabel1.setText("Simulación de Máquina de Turing Estándar");
      this.jLabel2.setText("2024 Simulación máquina de Turing Estándar");
    }
    

    spinnerEstados.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1) {
      @Override
      public Object getNextValue() {
        Object nextValue = super.getNextValue();
        return (nextValue == null || (Integer) nextValue < 1) ? getValue() : nextValue;
      }

      @Override
      public Object getPreviousValue() {
        Object previousValue = super.getPreviousValue();
        return (previousValue == null || (Integer) previousValue < 1) ? getValue() : previousValue;
      }
    });

    spinnerCaracteres.setModel(new SpinnerNumberModel(1, 1, Integer.MAX_VALUE, 1) {
      @Override
      public Object getNextValue() {
        Object nextValue = super.getNextValue();
        return (nextValue == null || (Integer) nextValue < 1) ? getValue() : nextValue;
      }

      @Override
      public Object getPreviousValue() {
        Object previousValue = super.getPreviousValue();
        return (previousValue == null || (Integer) previousValue < 1) ? getValue() : previousValue;
      }
    });

    JSpinner.DefaultEditor editor = (JSpinner.DefaultEditor) spinnerEstados.getEditor();
    editor.getTextField().setEditable(false);

    JSpinner.DefaultEditor editore = (JSpinner.DefaultEditor) spinnerCaracteres.getEditor();
    editore.getTextField().setEditable(false);

    iniciarMatriz(4, 3, true);
   

  }

  public JPanel getPanelCinta() {
    return panelCinta;
  }

  public ContenedorCinta getContenedorCinta() {
    return contenedorCinta;
  }

  public ContenedorMatriz getcM() {
    return cM;
  }

  public JPanel getPanelDibujo() {
    return panelDibujo;
  }

  public JButton getBotonDibujarMatriz() {
    return botonDibujarMatriz;
  }

  public JButton getBotonAceptar() {
    return botonAceptar;
  }

  public JButton getBotonAceptar1() {
    return botonAceptar1;
  }

  public JTextField getCajaCadena() {
    return cajaCadena;
  }

  public JButton getGuardarDatosEnTabla() {
    return guardarDatosEnTabla;
  }

  private void setupTable() {
    tableModel = new DefaultTableModel(
            new Object[][]{},
            new String[]{"Estado Actual", "Simbolo", "Simbolo siguiente", "Simbolo de escritura", "Dirección"}
    );
    //tablaTransicion.setModel(tableModel);
  }

  public JSpinner getSpinnerEstados() {
    return spinnerEstados;
  }

  public JSpinner getSpinnerCaracteres() {
    return spinnerCaracteres;
  }

  public JButton getPasoAnterior() {
    return pasoAnterior;
  }

  public JButton getPasoSiguiente() {
    return pasoSiguiente;
  }

  /**
   * This method is called from within the constructor to initialize the form.
   * WARNING: Do NOT modify this code. The content of this method is always
   * regenerated by the Form Editor.
   */
  @SuppressWarnings("unchecked")
  // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
  private void initComponents() {

    areaEncabezadoVentana = new javax.swing.JPanel();
    jLabel1 = new javax.swing.JLabel();
    areaConfiguracion = new javax.swing.JPanel();
    jLabel5 = new javax.swing.JLabel();
    jLabel7 = new javax.swing.JLabel();
    cajaCadena = new javax.swing.JTextField();
    jLabel8 = new javax.swing.JLabel();
    botonAceptar = new javax.swing.JButton();
    pasoAnterior = new javax.swing.JButton();
    botonAceptar1 = new javax.swing.JButton();
    detenerAnalisis = new javax.swing.JButton();
    pasoSiguiente = new javax.swing.JButton();
    velocidadDeSeguimiento = new javax.swing.JComboBox<>();
    labelPasoAPaso = new javax.swing.JLabel();
    areaTransiciones = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    limpiarTabla = new javax.swing.JButton();
    guardarDatosEnTabla = new javax.swing.JButton();
    abrirDatosEnTabla = new javax.swing.JButton();
    botonDibujarMatriz = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    panelDibujo = new javax.swing.JPanel();
    jLabel6 = new javax.swing.JLabel();
    spinnerEstados = new javax.swing.JSpinner();
    jLabel9 = new javax.swing.JLabel();
    spinnerCaracteres = new javax.swing.JSpinner();
    AreaSeguimiento = new javax.swing.JPanel();
    jLabel4 = new javax.swing.JLabel();
    jScrollPane3 = new javax.swing.JScrollPane();
    panelCinta = new javax.swing.JPanel();
    areaPieVentana = new javax.swing.JPanel();
    jLabel2 = new javax.swing.JLabel();

    setBackground(new java.awt.Color(255, 255, 255));

    areaEncabezadoVentana.setBackground(new java.awt.Color(0, 60, 84));
    areaEncabezadoVentana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jLabel1.setBackground(new java.awt.Color(255, 255, 255));
    jLabel1.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
    jLabel1.setForeground(new java.awt.Color(255, 255, 255));
    jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel1.setText("Simulación de Máquina de Turing");

    javax.swing.GroupLayout areaEncabezadoVentanaLayout = new javax.swing.GroupLayout(areaEncabezadoVentana);
    areaEncabezadoVentana.setLayout(areaEncabezadoVentanaLayout);
    areaEncabezadoVentanaLayout.setHorizontalGroup(
      areaEncabezadoVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(areaEncabezadoVentanaLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    areaEncabezadoVentanaLayout.setVerticalGroup(
      areaEncabezadoVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(areaEncabezadoVentanaLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel1)
        .addContainerGap(15, Short.MAX_VALUE))
    );

    areaConfiguracion.setBackground(new java.awt.Color(255, 255, 255));
    areaConfiguracion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jLabel5.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
    jLabel5.setForeground(new java.awt.Color(0, 60, 84));
    jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel5.setText("Configuración inicial");

    jLabel7.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    jLabel7.setText("Cinta a analizar");

    cajaCadena.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

    jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
    jLabel8.setText("Velocidad de seguimiento (ms)");

    botonAceptar.setBackground(new java.awt.Color(0, 60, 84));
    botonAceptar.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    botonAceptar.setForeground(new java.awt.Color(255, 255, 255));
    botonAceptar.setText("Cargar valor en cinta");
    botonAceptar.setBorder(null);
    botonAceptar.setOpaque(true);
    botonAceptar.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonAceptarActionPerformed(evt);
      }
    });

    pasoAnterior.setBackground(new java.awt.Color(0, 60, 84));
    pasoAnterior.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    pasoAnterior.setForeground(new java.awt.Color(255, 255, 255));
    pasoAnterior.setText("Paso anterior ");
    pasoAnterior.setBorder(null);
    pasoAnterior.setOpaque(true);

    botonAceptar1.setBackground(new java.awt.Color(0, 60, 84));
    botonAceptar1.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    botonAceptar1.setForeground(new java.awt.Color(255, 255, 255));
    botonAceptar1.setText("Ejecutar");
    botonAceptar1.setBorder(null);
    botonAceptar1.setOpaque(true);

    detenerAnalisis.setBackground(new java.awt.Color(0, 60, 84));
    detenerAnalisis.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    detenerAnalisis.setForeground(new java.awt.Color(255, 255, 255));
    detenerAnalisis.setText("Detener");
    detenerAnalisis.setBorder(null);
    detenerAnalisis.setOpaque(true);

    pasoSiguiente.setBackground(new java.awt.Color(0, 60, 84));
    pasoSiguiente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
    pasoSiguiente.setForeground(new java.awt.Color(255, 255, 255));
    pasoSiguiente.setText("Paso siguiente");
    pasoSiguiente.setBorder(null);
    pasoSiguiente.setOpaque(true);

    velocidadDeSeguimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Velocidad", "500 ms", "1000 ms", "2000 ms" }));

    labelPasoAPaso.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    labelPasoAPaso.setText("Paso : NA");

    javax.swing.GroupLayout areaConfiguracionLayout = new javax.swing.GroupLayout(areaConfiguracion);
    areaConfiguracion.setLayout(areaConfiguracionLayout);
    areaConfiguracionLayout.setHorizontalGroup(
      areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(areaConfiguracionLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(botonAceptar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(cajaCadena)
          .addGroup(areaConfiguracionLayout.createSequentialGroup()
            .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
              .addComponent(botonAceptar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(pasoAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
            .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(pasoSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addComponent(detenerAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
          .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
          .addComponent(velocidadDeSeguimiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(areaConfiguracionLayout.createSequentialGroup()
            .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel7)
              .addComponent(jLabel8)
              .addComponent(labelPasoAPaso))
            .addGap(0, 0, Short.MAX_VALUE)))
        .addContainerGap())
    );
    areaConfiguracionLayout.setVerticalGroup(
      areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(areaConfiguracionLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(jLabel7)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(cajaCadena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jLabel8)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(velocidadDeSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
        .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(botonAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(detenerAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(pasoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(pasoSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(labelPasoAPaso)
        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
    );

    areaTransiciones.setBackground(new java.awt.Color(255, 255, 255));
    areaTransiciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jLabel3.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(0, 60, 84));
    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel3.setText("Transiciones");

    limpiarTabla.setBackground(new java.awt.Color(0, 60, 84));
    limpiarTabla.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    limpiarTabla.setForeground(new java.awt.Color(255, 255, 255));
    limpiarTabla.setText("Limpiar transiciones");
    limpiarTabla.setBorder(null);
    limpiarTabla.setOpaque(true);
    limpiarTabla.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        limpiarTablaActionPerformed(evt);
      }
    });

    guardarDatosEnTabla.setBackground(new java.awt.Color(0, 60, 84));
    guardarDatosEnTabla.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    guardarDatosEnTabla.setForeground(new java.awt.Color(255, 255, 255));
    guardarDatosEnTabla.setText("Guardar transiciones");
    guardarDatosEnTabla.setBorder(null);
    guardarDatosEnTabla.setOpaque(true);
    guardarDatosEnTabla.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        guardarDatosEnTablaActionPerformed(evt);
      }
    });

    abrirDatosEnTabla.setBackground(new java.awt.Color(0, 60, 84));
    abrirDatosEnTabla.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    abrirDatosEnTabla.setForeground(new java.awt.Color(255, 255, 255));
    abrirDatosEnTabla.setText("Cargar transiciones");
    abrirDatosEnTabla.setBorder(null);
    abrirDatosEnTabla.setOpaque(true);
    abrirDatosEnTabla.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        abrirDatosEnTablaActionPerformed(evt);
      }
    });

    botonDibujarMatriz.setBackground(new java.awt.Color(0, 60, 84));
    botonDibujarMatriz.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    botonDibujarMatriz.setForeground(new java.awt.Color(255, 255, 255));
    botonDibujarMatriz.setText("Nueva matriz");
    botonDibujarMatriz.setBorder(null);
    botonDibujarMatriz.setOpaque(true);
    botonDibujarMatriz.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        botonDibujarMatrizActionPerformed(evt);
      }
    });

    panelDibujo.setLayout(new java.awt.GridLayout(1, 0));
    jScrollPane1.setViewportView(panelDibujo);

    jLabel6.setText("Estados");

    jLabel9.setText("Leer");

    javax.swing.GroupLayout areaTransicionesLayout = new javax.swing.GroupLayout(areaTransiciones);
    areaTransiciones.setLayout(areaTransicionesLayout);
    areaTransicionesLayout.setHorizontalGroup(
      areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(areaTransicionesLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(areaTransicionesLayout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(0, 0, Short.MAX_VALUE))
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaTransicionesLayout.createSequentialGroup()
            .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
              .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
              .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaTransicionesLayout.createSequentialGroup()
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerEstados, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel9)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(spinnerCaracteres, javax.swing.GroupLayout.PREFERRED_SIZE, 82, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(63, 63, 63)
                .addComponent(botonDibujarMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(limpiarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
            .addContainerGap())
          .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaTransicionesLayout.createSequentialGroup()
            .addGap(0, 0, Short.MAX_VALUE)
            .addComponent(guardarDatosEnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(abrirDatosEnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addGap(84, 84, 84))))
    );
    areaTransicionesLayout.setVerticalGroup(
      areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(areaTransicionesLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(botonDibujarMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(limpiarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel6)
          .addComponent(spinnerEstados, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(jLabel9)
          .addComponent(spinnerCaracteres, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
          .addComponent(guardarDatosEnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE)
          .addComponent(abrirDatosEnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 33, javax.swing.GroupLayout.PREFERRED_SIZE))
        .addContainerGap())
    );

    AreaSeguimiento.setBackground(new java.awt.Color(255, 255, 255));
    AreaSeguimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jLabel4.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
    jLabel4.setForeground(new java.awt.Color(0, 60, 84));
    jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel4.setText("Cadena de seguimiento");

    panelCinta.setLayout(new java.awt.BorderLayout());
    jScrollPane3.setViewportView(panelCinta);

    javax.swing.GroupLayout AreaSeguimientoLayout = new javax.swing.GroupLayout(AreaSeguimiento);
    AreaSeguimiento.setLayout(AreaSeguimientoLayout);
    AreaSeguimientoLayout.setHorizontalGroup(
      AreaSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AreaSeguimientoLayout.createSequentialGroup()
        .addContainerGap()
        .addGroup(AreaSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addGroup(AreaSeguimientoLayout.createSequentialGroup()
            .addGap(6, 6, 6)
            .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 882, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
          .addGroup(AreaSeguimientoLayout.createSequentialGroup()
            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addContainerGap())))
    );
    AreaSeguimientoLayout.setVerticalGroup(
      AreaSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(AreaSeguimientoLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
        .addGap(17, 17, 17))
    );

    areaPieVentana.setBackground(new java.awt.Color(0, 60, 84));
    areaPieVentana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel2.setText("2024 Simulación máquina de Turring");

    javax.swing.GroupLayout areaPieVentanaLayout = new javax.swing.GroupLayout(areaPieVentana);
    areaPieVentana.setLayout(areaPieVentanaLayout);
    areaPieVentanaLayout.setHorizontalGroup(
      areaPieVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(areaPieVentanaLayout.createSequentialGroup()
        .addContainerGap()
        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addContainerGap())
    );
    areaPieVentanaLayout.setVerticalGroup(
      areaPieVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaPieVentanaLayout.createSequentialGroup()
        .addGap(0, 0, Short.MAX_VALUE)
        .addComponent(jLabel2))
    );

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addComponent(areaEncabezadoVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addComponent(areaPieVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
      .addGroup(layout.createSequentialGroup()
        .addContainerGap()
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
          .addComponent(AreaSeguimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addGroup(layout.createSequentialGroup()
            .addComponent(areaConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
            .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
            .addComponent(areaTransiciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        .addContainerGap())
    );
    layout.setVerticalGroup(
      layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
      .addGroup(layout.createSequentialGroup()
        .addComponent(areaEncabezadoVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
          .addComponent(areaConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
          .addComponent(areaTransiciones, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(AreaSeguimiento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
        .addComponent(areaPieVentana, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
    );
  }// </editor-fold>//GEN-END:initComponents

    private void guardarDatosEnTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarDatosEnTablaActionPerformed
      JFileChooser fileChooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("Archivos CSV", "csv");
      fileChooser.setFileFilter(filter);
      fileChooser.setSelectedFile(new File("transiciones.csv"));

      if (fileChooser.showSaveDialog(TuringMachineSimulation.this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        if (!file.getName().toLowerCase().endsWith(".csv")) {
          file = new File(file.getParentFile(), file.getName() + ".csv");
        }
        try (PrintWriter out = new PrintWriter(new FileWriter(file))) {
          for (int i = 0; i < tableModel.getRowCount(); i++) {
            for (int j = 0; j < tableModel.getColumnCount(); j++) {
              out.print(tableModel.getValueAt(i, j));
              if (j < tableModel.getColumnCount() - 1) {
                out.print(",");
              }
            }
            out.println();
          }
          JOptionPane.showMessageDialog(TuringMachineSimulation.this, "Archivo guardado exitosamente.");
        } catch (IOException ex) {
          JOptionPane.showMessageDialog(TuringMachineSimulation.this, "Error al guardar el archivo: " + ex.getMessage());
        }
      }
    }//GEN-LAST:event_guardarDatosEnTablaActionPerformed

  public JLabel getLabelPasoAPaso() {
    return labelPasoAPaso;
  }

    private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonAceptarActionPerformed
      tape = cajaCadena.getText();
      headPosition = 0;
      currentState = "q0"; // Inicializa el estado al estado inicial
      currentStep = 0; // Reinicia el contador de pasos
      isRunning = true;
      updateLog("Proceso iniciado con cinta: " + tape);
      dibujarCadena();
      textToDraw = cajaCadena.getText();
    }//GEN-LAST:event_botonAceptarActionPerformed

    private void abrirDatosEnTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirDatosEnTablaActionPerformed
      JFileChooser fileChooser = new JFileChooser();
      FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
      fileChooser.setFileFilter(filter);

      if (fileChooser.showOpenDialog(TuringMachineSimulation.this) == JFileChooser.APPROVE_OPTION) {
        File file = fileChooser.getSelectedFile();
        TuringMachine tm = new TuringMachine();
        tm.loadMachine(file.getAbsolutePath());

        System.out.println("Conjunto de estados Q: " + tm.getStates());
        System.out.println("Estados iniciales q0: " + tm.getInitialStates());
        System.out.println("Estados finales qf: " + tm.getFinalStates());
        System.out.println("Alfabeto " + tm.getAlphabet());
        System.out.println("Transiciones:");

        for (TuringMachine.Transition t : tm.getTransitions()) {
          System.out.println(t);
        }

        this.panelCinta.removeAll();
        this.panelCinta.repaint();
        this.panelCinta.revalidate();
        this.panelDibujo.removeAll();
        this.panelDibujo.repaint();
        this.panelDibujo.revalidate();
        this.puntero1.eliminarReferenciasDelMouse();
        this.puntero2.eliminarReferencias();

        this.iniciarMatriz(tm.getStates().size(), tm.getAlphabet().size(), true);

        ArrayList<String> letras = new ArrayList<>();
        for (String cadena : tm.getAlphabet()) {

          if (cadena.equals("\\0")) {
            letras.add(String.valueOf(Simbolo.BlancoChar));
            continue;
          }

          letras.add(cadena);
        }

        int contador = 0;
        for (Letra l : this.getcM().getmD().getListaDeLetrasColumnas()) {
          l.setLetra(letras.get(contador));
          contador = contador + 1;
        }

        MatrizDinamica md = this.getcM().getmD();
        for (TuringMachine.Transition t : tm.getTransitions()) {
          var d = md.obtenerReferenciaDeMemoriaDeLaTransicion(t.getQ(), t.getS());
          if (t.getS().equals("\\0")) {
            System.out.println("voy a tener un blanco");
            d = md.obtenerReferenciaDeMemoriaDeLaTransicion(t.getQ(), "\0");
            if (d == null) {
              System.out.println("no encuentro a mi blanco");
            }
          }

          System.out.println("buscando " + t.toString());
          Letra l = md.obtenerReferenciaDeMemoriaDeLaLetra(t.getX());
          if (t.getX().equals("\\0")) {
            l = md.obtenerReferenciaDeMemoriaDeLaLetra("\0");
          }

          d.setLetra(l);
          d.setEstado("q" + t.getP());

          String m = "Izquierda";
          if (t.getD().equals("D")) {
            m = "Derecha";
          }

          d.setMovimiento(m);
          d.setPuedoDibujarLaTransicion(true);

        }

        for (var s : tm.getInitialStates()) {
          for (var e : this.getcM().getmD().keySet()) {
            if (e.getNombreEstado().equals("q" + s)) {
              e.setEsEstadoInicial(true);
            }
          }
        }

        for (var s : tm.getFinalStates()) {
          for (var e : this.getcM().getmD().keySet()) {
            if (e.getNombreEstado().equals("q" + s)) {
              e.setEsEstadoFinal(true);
            }
          }
        }

        this.panelDibujo.repaint();
        this.panelDibujo.revalidate();
        this.getcM().repaint();

      }
    }//GEN-LAST:event_abrirDatosEnTablaActionPerformed

    private void botonDibujarMatrizActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_botonDibujarMatrizActionPerformed
      tableModel.setRowCount(0);
      // Definir las transiciones de la máquina de Turing
      tableModel.addRow(new Object[]{"q0", "0", "q0", "0", "R"});
      tableModel.addRow(new Object[]{"q0", "1", "q1", "1", "R"});
      tableModel.addRow(new Object[]{"q1", "0", "q1", "0", "R"});
      tableModel.addRow(new Object[]{"q1", "1", "q0", "1", "R"});
      tableModel.addRow(new Object[]{"q0", " ", "qf", " ", "R"}); // Transición de aceptación
      tableModel.addRow(new Object[]{"q1", " ", "qf", " ", "R"}); // Transición de aceptación

    }//GEN-LAST:event_botonDibujarMatrizActionPerformed

  public void iniciarMatriz(int estados, int caracteres, boolean esEstandar) {
    System.out.println("booleaanoo " + esEstandar);
    spinnerEstados.setValue(estados);
    spinnerCaracteres.setValue(caracteres);

    MatrizDinamica mD = new MatrizDinamica(0, 0, 100, 65, ((int) this.spinnerEstados.getValue()), ((int) this.spinnerCaracteres.getValue()));
    this.cM = new ContenedorMatriz(mD);

    this.panelDibujo.add(this.cM);

    mD.inicializarMatriz();

    this.contenedorCinta = new ContenedorCinta(new Cinta(this, "", 10, 10, 60, 50), this);
    this.contenedorCinta.setSize(this.contenedorCinta.getWidth(), 30);

    this.panelCinta.add(this.contenedorCinta);

    puntero1 = new ControladorDeLaMatriz(this);

    puntero2 = new ControladorSwing(this);
    puntero2.setEsUnaMaquinaDeTuringEstandar(esEstandar);
  }
  
  private ControladorDeLaMatriz puntero1;
  private ControladorSwing puntero2;
  
    private void limpiarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarTablaActionPerformed
      tableModel.setRowCount(0);
    }//GEN-LAST:event_limpiarTablaActionPerformed

  private void dibujarCadena() {

    JPanel panelCadena = new JPanel();
    panelCadena.setLayout(new BoxLayout(panelCadena, BoxLayout.X_AXIS));

    for (int i = 0; i < tape.length(); i++) {
      JLabel label = new JLabel(String.valueOf(tape.charAt(i)));
      label.setBorder(BorderFactory.createLineBorder(Color.BLACK));
      label.setPreferredSize(new Dimension(30, 30));
      label.setHorizontalAlignment(SwingConstants.CENTER);
      if (i == headPosition) {
        label.setBackground(Color.YELLOW);
        label.setOpaque(true);
      }
      panelCadena.add(label);
    }
  }

  private void updateLog(String message) {
    //no sirve esto

  }

  /*
    *@param headposition indica la posicion 
    *@param currentStep indica el paso en el que se encuentra
    *@param currentState señala en que estado se encuentra actualmente
    *@param currentSymbol indica el simbolo que lee
    *@param writeSymbol indica el simbolo que escribira
    *@param direction señala la direccion que tomara en la cinta
   */
  private void step() {
    if (currentState.equals("qf")) {
      updateLog("La máquina ha terminado.");
      return;
    }

    char currentSymbol = tape.charAt(headPosition);
    boolean transitionFound = false;

    for (int i = 0; i < tableModel.getRowCount(); i++) {
      if (tableModel.getValueAt(i, 0).equals(currentState)
              && tableModel.getValueAt(i, 1).toString().charAt(0) == currentSymbol) {

        currentState = (String) tableModel.getValueAt(i, 2);
        char writeSymbol = tableModel.getValueAt(i, 3).toString().charAt(0);
        String direction = (String) tableModel.getValueAt(i, 4);

        StringBuilder newTape = new StringBuilder(tape);
        newTape.setCharAt(headPosition, writeSymbol);
        tape = newTape.toString();

        if (direction.equals("R")) {
          headPosition++;
        } else if (direction.equals("L")) {
          headPosition--;
        }

        updateLog("Paso " + (++currentStep) + ": Estado " + currentState
                + ", Símbolo " + currentSymbol + " -> " + writeSymbol
                + ", Mover " + direction);
        transitionFound = true;
        break;
      }
    }

    if (!transitionFound) {
      updateLog("No se encontró transición para el estado " + currentState
              + " y símbolo " + currentSymbol + ". La máquina se detiene.");
      currentState = "qf";
    }

    dibujarCadena(); // Llama a dibujarCadena después de cada paso
  }


  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel AreaSeguimiento;
  private javax.swing.JButton abrirDatosEnTabla;
  private javax.swing.JPanel areaConfiguracion;
  private javax.swing.JPanel areaEncabezadoVentana;
  private javax.swing.JPanel areaPieVentana;
  private javax.swing.JPanel areaTransiciones;
  private javax.swing.JButton botonAceptar;
  private javax.swing.JButton botonAceptar1;
  private javax.swing.JButton botonDibujarMatriz;
  private javax.swing.JTextField cajaCadena;
  private javax.swing.JButton detenerAnalisis;
  private javax.swing.JButton guardarDatosEnTabla;
  private javax.swing.JLabel jLabel1;
  private javax.swing.JLabel jLabel2;
  private javax.swing.JLabel jLabel3;
  private javax.swing.JLabel jLabel4;
  private javax.swing.JLabel jLabel5;
  private javax.swing.JLabel jLabel6;
  private javax.swing.JLabel jLabel7;
  private javax.swing.JLabel jLabel8;
  private javax.swing.JLabel jLabel9;
  private javax.swing.JScrollPane jScrollPane1;
  private javax.swing.JScrollPane jScrollPane3;
  private javax.swing.JLabel labelPasoAPaso;
  private javax.swing.JButton limpiarTabla;
  private javax.swing.JPanel panelCinta;
  private javax.swing.JPanel panelDibujo;
  private javax.swing.JButton pasoAnterior;
  private javax.swing.JButton pasoSiguiente;
  private javax.swing.JSpinner spinnerCaracteres;
  private javax.swing.JSpinner spinnerEstados;
  private javax.swing.JComboBox<String> velocidadDeSeguimiento;
  // End of variables declaration//GEN-END:variables

}
