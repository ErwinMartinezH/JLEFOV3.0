package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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
import GraficoMTBD.vistaturing.controladores.ControladorDeLaMatriz;
import GraficoMTBD.vistaturing.controladores.ControladorSwing;
import GraficoMTBD.vistaturing.estructurasdedatos.MatrizDinamica;
import GraficoMTBD.vistaturing.recursos2D.Cinta;
import GraficoMTBD.vistaturing.recursos2D.Estado;
import GraficoMTBD.vistaturing.recursos2D.Letra;
import GraficoMTBD.vistaturing.recursos2D.Transicion;
import GraficoMTBD.vistaturing.utilidades.leercsv.TuringMachine;
import GraficoMTBD.vistaturing.utilidades.modelos.Simbolo;
import GraficoMTBD.vistaturing.vistas.ContenedorCinta;
import GraficoMTBD.vistaturing.vistas.ContenedorMatriz;
import GraficoMTBD.vistaturing.vistas.PanelInteligente;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.util.ArrayList;
import javax.swing.JComboBox;
import javax.swing.JScrollPane;

/**
 *
 * @author Erwin Martinez
 */
public class TuringBidireccional extends javax.swing.JPanel {

  private DefaultTableModel tableModel;
  private boolean isRunning = false;
  private int currentStep = 0;
  private String tape = "";
  private int headPosition = 0;
  private String currentState = "q0";
  private String textToDraw = "";

  private ContenedorMatriz cM;
  private ContenedorCinta contenedorCinta;

  private ControladorDeLaMatriz puntero1;
  private ControladorSwing puntero2;

  private PanelInteligente panelInteligente;

  private boolean esEstandar;

  /**
   * Creates new form TuringMachineSimulation
   *
   * @param esEstandar
   */
  public TuringBidireccional(boolean esEstandar) {
    this.esEstandar = esEstandar;

    initComponents();
    setupTable();

    if (this.esEstandar) {
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

    iniciarMatriz(4, 3, esEstandar);
    this.panelInteligente = new PanelInteligente("q0", "1", "q1", "1", "D");
    this.seguimientoInteligente.add(panelInteligente);

  }

  public PanelInteligente getPanelInteligente() {
    return panelInteligente;
  }

  public void iniciarMatriz(int estados, int caracteres, boolean esEstandar) {
    System.out.println("booleaanoo " + esEstandar);
    spinnerEstados.setValue(estados);
    spinnerCaracteres.setValue(caracteres);

    MatrizDinamica mD = new MatrizDinamica(0, 0, 100, 65, ((int) this.spinnerEstados.getValue()),
        ((int) this.spinnerCaracteres.getValue()));
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

  public JPanel getSeguimientoInteligente() {
    return seguimientoInteligente;
  }

  public void setContenedorCinta(ContenedorCinta contenedorCinta) {
    this.contenedorCinta = contenedorCinta;
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
        new Object[][] {},
        new String[] { "Estado Actual", "Simbolo", "Simbolo siguiente", "Simbolo de escritura", "Dirección" });
    // tablaTransicion.setModel(tableModel);
  }

  public JScrollPane getjScrollPane1() {
    return jScrollPane1;
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
  // <editor-fold defaultstate="collapsed" desc="Generated
  // Code">//GEN-BEGIN:initComponents
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
    jLabel10 = new javax.swing.JLabel();
    areaTransiciones = new javax.swing.JPanel();
    jLabel3 = new javax.swing.JLabel();
    guardarDatosEnTabla = new javax.swing.JButton();
    abrirDatosEnTabla = new javax.swing.JButton();
    botonDibujarMatriz = new javax.swing.JButton();
    jScrollPane1 = new javax.swing.JScrollPane();
    panelDibujo = new javax.swing.JPanel();
    jLabel6 = new javax.swing.JLabel();
    spinnerEstados = new javax.swing.JSpinner();
    jLabel9 = new javax.swing.JLabel();
    spinnerCaracteres = new javax.swing.JSpinner();
    seguimientoInteligente = new javax.swing.JPanel();
    abrirEjemplos = new javax.swing.JButton();
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
    jLabel1.setText("Simulación de Máquina de Turing Bidireccional");

    javax.swing.GroupLayout areaEncabezadoVentanaLayout = new javax.swing.GroupLayout(areaEncabezadoVentana);
    areaEncabezadoVentana.setLayout(areaEncabezadoVentanaLayout);
    areaEncabezadoVentanaLayout.setHorizontalGroup(
        areaEncabezadoVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaEncabezadoVentanaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addContainerGap()));
    areaEncabezadoVentanaLayout.setVerticalGroup(
        areaEncabezadoVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaEncabezadoVentanaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addContainerGap(15, Short.MAX_VALUE)));

    areaConfiguracion.setBackground(new java.awt.Color(255, 255, 255));
    areaConfiguracion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jLabel5.setFont(new java.awt.Font("Arial", 1, 24)); // NOI18N
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

    velocidadDeSeguimiento.setModel(new javax.swing.DefaultComboBoxModel<>(
        new String[] { "100", "300", "500", "1000", "1500", "2000", "2500", "3000", "3500", "4000", "4500", "5000" }));
    velocidadDeSeguimiento.setSelectedIndex(2);

    labelPasoAPaso.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
    labelPasoAPaso.setText("Paso : NA");

    jLabel10.setText("ms");

    javax.swing.GroupLayout areaConfiguracionLayout = new javax.swing.GroupLayout(areaConfiguracion);
    areaConfiguracion.setLayout(areaConfiguracionLayout);
    areaConfiguracionLayout.setHorizontalGroup(
        areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaConfiguracionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(botonAceptar, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cajaCadena)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE)
                    .addGroup(areaConfiguracionLayout.createSequentialGroup()
                        .addGroup(areaConfiguracionLayout
                            .createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(botonAceptar1, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pasoAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pasoSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detenerAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE,
                                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(areaConfiguracionLayout.createSequentialGroup()
                        .addGroup(
                            areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                .addComponent(jLabel7, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel8, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(labelPasoAPaso, javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(velocidadDeSeguimiento, javax.swing.GroupLayout.Alignment.LEADING, 0,
                                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel10)))
                .addContainerGap()));
    areaConfiguracionLayout.setVerticalGroup(
        areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaConfiguracionLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 44,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel7)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cajaCadena, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(velocidadDeSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(botonAceptar, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonAceptar1, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detenerAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pasoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pasoSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 45,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(labelPasoAPaso)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)));

    areaTransiciones.setBackground(new java.awt.Color(255, 255, 255));
    areaTransiciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jLabel3.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
    jLabel3.setForeground(new java.awt.Color(0, 60, 84));
    jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel3.setText("Transiciones:");

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

    seguimientoInteligente.setBackground(new java.awt.Color(255, 255, 255));
    seguimientoInteligente.setLayout(new java.awt.BorderLayout());

    abrirEjemplos.setBackground(new java.awt.Color(0, 60, 84));
    abrirEjemplos.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
    abrirEjemplos.setForeground(new java.awt.Color(255, 255, 255));
    abrirEjemplos.setText("Ejemplos");
    abrirEjemplos.setBorder(null);
    abrirEjemplos.setOpaque(true);
    abrirEjemplos.addActionListener(new java.awt.event.ActionListener() {
      public void actionPerformed(java.awt.event.ActionEvent evt) {
        abrirEjemplosActionPerformed(evt);
      }
    });

    javax.swing.GroupLayout areaTransicionesLayout = new javax.swing.GroupLayout(areaTransiciones);
    areaTransiciones.setLayout(areaTransicionesLayout);
    areaTransicionesLayout.setHorizontalGroup(
        areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaTransicionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 604,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(areaTransicionesLayout.createSequentialGroup()
                        .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 184,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(seguimientoInteligente, javax.swing.GroupLayout.PREFERRED_SIZE, 414,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaTransicionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(guardarDatosEnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(areaTransicionesLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerEstados, javax.swing.GroupLayout.PREFERRED_SIZE, 74,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(jLabel9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(spinnerCaracteres, javax.swing.GroupLayout.PREFERRED_SIZE, 82,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(63, 63, 63)
                        .addComponent(botonDibujarMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
                            javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(abrirDatosEnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abrirEjemplos, javax.swing.GroupLayout.PREFERRED_SIZE, 153,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));
    areaTransicionesLayout.setVerticalGroup(
        areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaTransicionesLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(seguimientoInteligente, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 246,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(botonDibujarMatriz, javax.swing.GroupLayout.PREFERRED_SIZE, 34,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(spinnerEstados, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9)
                    .addComponent(spinnerCaracteres, javax.swing.GroupLayout.PREFERRED_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abrirDatosEnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardarDatosEnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                        javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(abrirEjemplos, javax.swing.GroupLayout.PREFERRED_SIZE, 33,
                        javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap()));

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
                        .addComponent(jScrollPane3, javax.swing.GroupLayout.PREFERRED_SIZE, 882,
                            javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(AreaSeguimientoLayout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap()))));
    AreaSeguimientoLayout.setVerticalGroup(
        AreaSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AreaSeguimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50,
                    javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane3, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE)
                .addGap(17, 17, 17)));

    areaPieVentana.setBackground(new java.awt.Color(0, 60, 84));
    areaPieVentana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

    jLabel2.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
    jLabel2.setForeground(new java.awt.Color(255, 255, 255));
    jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
    jLabel2.setText("2024 Simulación máquina de Turing Bidireccional");

    javax.swing.GroupLayout areaPieVentanaLayout = new javax.swing.GroupLayout(areaPieVentana);
    areaPieVentana.setLayout(areaPieVentanaLayout);
    areaPieVentanaLayout.setHorizontalGroup(
        areaPieVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaPieVentanaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                    Short.MAX_VALUE)
                .addContainerGap()));
    areaPieVentanaLayout.setVerticalGroup(
        areaPieVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaPieVentanaLayout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(jLabel2)));

    javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
    this.setLayout(layout);
    layout.setHorizontalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(areaEncabezadoVentana, javax.swing.GroupLayout.DEFAULT_SIZE,
                javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(areaPieVentana, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE,
                Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(AreaSeguimiento, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(areaConfiguracion, javax.swing.GroupLayout.PREFERRED_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(areaTransiciones, javax.swing.GroupLayout.DEFAULT_SIZE,
                            javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap()));
    layout.setVerticalGroup(
        layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(areaEncabezadoVentana, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(areaConfiguracion, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(areaTransiciones, javax.swing.GroupLayout.DEFAULT_SIZE,
                        javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(AreaSeguimiento, javax.swing.GroupLayout.DEFAULT_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(areaPieVentana, javax.swing.GroupLayout.PREFERRED_SIZE,
                    javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)));
  }// </editor-fold>//GEN-END:initComponents

  private void guardarDatosEnTablaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_guardarDatosEnTablaActionPerformed

    var matriz = this.getcM().getmD();
    ArrayList<String> listaEstados = new ArrayList<>();

    // Agregar los nombres de los estados a la lista, eliminando la "q"
    for (var i : matriz.entrySet()) {
      listaEstados.add(i.getKey().getNombreEstado().replace("q", ""));
    }

    // Crear la cadena de estados sin la "q"
    String conjuntoEstados = "Q = {" + String.join(",", listaEstados) + "}";

    // Listas para almacenar estados iniciales y finales, sin la "q"
    ArrayList<String> listaEstadosIniciales = new ArrayList<>();
    ArrayList<String> listaEstadosFinales = new ArrayList<>();

    // Iterar sobre los estados para separar iniciales y finales, eliminando la "q"
    for (var i : matriz.entrySet()) {
      if (i.getKey().isEsEstadoInicial()) {
        listaEstadosIniciales.add(i.getKey().getNombreEstado().replace("q", ""));
      }
      if (i.getKey().isEsEstadoFinal()) {
        listaEstadosFinales.add(i.getKey().getNombreEstado().replace("q", ""));
      }
    }

    // Crear las cadenas de estados iniciales y finales sin la "q"
    String conjuntoEstadosIniciales = "q0 = {" + String.join(",", listaEstadosIniciales) + "}";
    String conjuntoEstadosFinales = "qf = {" + String.join(",", listaEstadosFinales) + "}";

    // Construir las transiciones, eliminando la "q"
    String conjuntoTransicion = "";
    for (var m : matriz.entrySet()) {
      Estado estado = m.getKey();
      ArrayList<Letra> letras = matriz.getListaDeLetrasColumnas();
      ArrayList<Transicion> transicionesEstado = m.getValue();

      for (int transicionIndex = 0; transicionIndex < transicionesEstado.size(); transicionIndex++) {
        Letra filaLetra = letras.get(transicionIndex);
        Transicion transicion = transicionesEstado.get(transicionIndex);

        if (transicion.getPuedoDibujarTransicion()) {
          String estadoActual = estado.getNombreEstado().replace("q", "");
          String estadoDestino = transicion.getEstado().replace("q", "");
          String letraActual = filaLetra.getLetra().equals("\0") ? "\\0" : filaLetra.getLetra();
          String letraDestino = transicion.getLetra().getLetra().equals("\0") ? "\\0"
              : transicion.getLetra().getLetra();

          if (transicion.getLetra().getLetra().equals("\0") || filaLetra.getLetra().equals("\0")) {
            if (filaLetra.getLetra().equals("\0")) {
              conjuntoTransicion += "(" + estadoActual + ",\\0) -> (" + estadoDestino + ",\\0,"
                  + transicion.getMovimiento() + ")\n";
            } else {
              conjuntoTransicion += "(" + estadoActual + "," + letraActual + ") -> (" + estadoDestino + ",\\0,"
                  + transicion.getMovimiento() + ")\n";
            }
          } else {
            conjuntoTransicion += "(" + estadoActual + "," + letraActual + ") -> (" + estadoDestino + "," + letraDestino
                + "," + transicion.getMovimiento() + ")\n";
          }
        }
      }
    }

    // Mostrar el JFileChooser para seleccionar el archivo de destino
    JFileChooser fileChooser = new JFileChooser();
    fileChooser.setDialogTitle("Guardar archivo CSV");
    fileChooser.setAcceptAllFileFilterUsed(false);
    fileChooser.setFileFilter(new javax.swing.filechooser.FileNameExtensionFilter("CSV Files", "csv"));

    int userSelection = fileChooser.showSaveDialog(null);
    if (userSelection == JFileChooser.APPROVE_OPTION) {
      File fileToSave = fileChooser.getSelectedFile();
      // Asegurar que el archivo tenga extensión .csv
      if (!fileToSave.getName().endsWith(".csv")) {
        fileToSave = new File(fileToSave.getAbsolutePath() + ".csv");
      }

      try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileToSave))) {
        // Escribir los datos en el archivo CSV
        writer.write(conjuntoEstados + "\n");
        writer.write(conjuntoEstadosIniciales + "\n");
        writer.write(conjuntoEstadosFinales + "\n");
        writer.write(conjuntoTransicion);
        writer.flush(); // Asegura que todo se escriba al archivo
        JOptionPane.showMessageDialog(null, "Datos guardados exitosamente en: " + fileToSave.getAbsolutePath());
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, "Error al guardar el archivo: " + e.getMessage());
      }
    }
  }// GEN-LAST:event_guardarDatosEnTablaActionPerformed

  public JLabel getLabelPasoAPaso() {
    return labelPasoAPaso;
  }

  private void botonAceptarActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonAceptarActionPerformed
    tape = cajaCadena.getText();
    headPosition = 0;
    currentState = "q0"; // Inicializa el estado al estado inicial
    currentStep = 0; // Reinicia el contador de pasos
    isRunning = true;
    dibujarCadena();
    textToDraw = cajaCadena.getText();
  }// GEN-LAST:event_botonAceptarActionPerformed

  public JComboBox<String> getVelocidadDeSeguimiento() {
    return velocidadDeSeguimiento;
  }

  public JButton getAbrirDatosEnTabla() {
    return abrirDatosEnTabla;
  }

  private void abrirDatosEnTablaActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_abrirDatosEnTablaActionPerformed

    JFileChooser fileChooser = new JFileChooser();
    FileNameExtensionFilter filter = new FileNameExtensionFilter("CSV Files", "csv");
    fileChooser.setFileFilter(filter);

    if (fileChooser.showOpenDialog(TuringBidireccional.this) == JFileChooser.APPROVE_OPTION) {
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

      System.out.println("mi tipo de maquina esEstandar? " + esEstandar);
      this.iniciarMatriz(tm.getStates().size(), tm.getAlphabet().size(), esEstandar);

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

        String m = "I";
        if (t.getD().equals("D")) {
          m = "D";
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

  }// GEN-LAST:event_abrirDatosEnTablaActionPerformed

  private void botonDibujarMatrizActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_botonDibujarMatrizActionPerformed
    tableModel.setRowCount(0);
    // Definir las transiciones de la máquina de Turing
    tableModel.addRow(new Object[] { "q0", "0", "q0", "0", "R" });
    tableModel.addRow(new Object[] { "q0", "1", "q1", "1", "R" });
    tableModel.addRow(new Object[] { "q1", "0", "q1", "0", "R" });
    tableModel.addRow(new Object[] { "q1", "1", "q0", "1", "R" });
    tableModel.addRow(new Object[] { "q0", " ", "qf", " ", "R" }); // Transición de aceptación
    tableModel.addRow(new Object[] { "q1", " ", "qf", " ", "R" }); // Transición de aceptación

  }// GEN-LAST:event_botonDibujarMatrizActionPerformed

  private void cargarEjemplo(String filePath) {
    TuringMachine tm = new TuringMachine();
    tm.loadMachine(filePath);

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

    System.out.println("mi tipo de maquina esEstandar? " + esEstandar);
    this.iniciarMatriz(tm.getStates().size(), tm.getAlphabet().size(), esEstandar);

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

      String m = "I";
      if (t.getD().equals("D")) {
        m = "D";
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

  private void abrirEjemplosActionPerformed(java.awt.event.ActionEvent evt) {// GEN-FIRST:event_abrirEjemplosActionPerformed
    /*
     * Este metodo Lanza un mensaje de dialogo y te hace elegir una opcion(son 5
     * opciones).
     * Dependiendo la eleccion se abre el ejemplo correspondiente.
     */

    if (esEstandar) {
      System.out.println("Estoy en maquina de turing estandar");

      // Lanza un dialogo para elegir el ejemplo(1,2,3)
      switch (JOptionPane.showInputDialog(null, "Que ejemplo quieres ver?", "Elegir ejemplo",
          JOptionPane.INFORMATION_MESSAGE, null, new String[] { "Ejemplo 1", "Ejemplo 2", "Ejemplo 3", "Ejemplo 4", "Ejemplo 5" }, "Ejemplo 1")
          .toString()) {
        case "Ejemplo 1":
          // Abre el ejemplo 1 de /src/ejemplos/ejemplo1.csv
          cargarEjemplo("src/ejemplos/ejemplo1.csv");
          break;
        case "Ejemplo 2":
          // Abre el ejemplo 2 de /src/ejemplos/ejemplo2.csv
          cargarEjemplo("src/ejemplos/ejemplo2.csv");
          break;
        case "Ejemplo 3":
          // Abre el ejemplo 3 de /src/ejemplos/ejemplo3.csv
          cargarEjemplo("src/ejemplos/ejemplo3.csv");
          break;
        case "Ejemplo 4":
          // Abre el ejemplo 4 de /src/ejemplos/ejemplo4.csv
          cargarEjemplo("src/ejemplos/ejemplo4.csv");
          break;
        case "Ejemplo 5":
          // Abre el ejemplo 5 de /src/ejemplos/ejemplo5.csv
          cargarEjemplo("src/ejemplos/ejemplo5.csv");
          break;
      }

    } else if (!esEstandar) {
      System.out.println("Estoy en maquina de turing bidireccional");

      // Lanza un dialogo para elegir el ejemplo(1,2,3)
      switch (JOptionPane.showInputDialog(null, "Que ejemplo quieres ver?", "Elegir ejemplo",
          JOptionPane.INFORMATION_MESSAGE, null, new String[] { "Ejemplo 1", "Ejemplo 2", "Ejemplo 3", "Ejemplo 4", "Ejemplo 5" }, "Ejemplo 1")
          .toString()) {
        case "Ejemplo 1":
          // Abre el ejemplo 1 de /src/ejemplos/ejemplo1.csv
          cargarEjemplo("src/ejemplos/ejemplo1B.csv");
          break;
        case "Ejemplo 2":
          // Abre el ejemplo 2 de /src/ejemplos/ejemplo2.csv
          cargarEjemplo("src/ejemplos/ejemplo2B.csv");
          break;
        case "Ejemplo 3":
          // Abre el ejemplo 3 de /src/ejemplos/ejemplo3.csv
          cargarEjemplo("src/ejemplos/ejemplo3B.csv");
          break;
        case "Ejemplo 4":
          // Abre el ejemplo 4 de /src/ejemplos/ejemplo4.csv
          cargarEjemplo("src/ejemplos/ejemplo4B.csv");
          break;
        case "Ejemplo 5":
          // Abre el ejemplo 5 de /src/ejemplos/ejemplo5.csv
          cargarEjemplo("src/ejemplos/ejemplo5B.csv");
          break;
      }

    }

  }// GEN-LAST:event_abrirEjemplosActionPerformed

  public JButton getDetenerAnalisis() {
    return detenerAnalisis;
  }

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

  // Variables declaration - do not modify//GEN-BEGIN:variables
  private javax.swing.JPanel AreaSeguimiento;
  private javax.swing.JButton abrirDatosEnTabla;
  private javax.swing.JButton abrirEjemplos;
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
  private javax.swing.JLabel jLabel10;
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
  private javax.swing.JPanel panelCinta;
  private javax.swing.JPanel panelDibujo;
  private javax.swing.JButton pasoAnterior;
  private javax.swing.JButton pasoSiguiente;
  private javax.swing.JPanel seguimientoInteligente;
  private javax.swing.JSpinner spinnerCaracteres;
  private javax.swing.JSpinner spinnerEstados;
  private javax.swing.JComboBox<String> velocidadDeSeguimiento;
  // End of variables declaration//GEN-END:variables

}
