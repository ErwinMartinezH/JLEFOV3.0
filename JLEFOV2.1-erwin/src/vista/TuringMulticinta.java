package vista;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Vector;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPopupMenu;
import javax.swing.JTable;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Erwin Martinez
 */
public class TuringMulticinta extends javax.swing.JPanel {

    private DefaultTableModel tableModel;
    private boolean isRunning = false;
    private int currentStep = 0;
    private String tape = "";
    private int headPosition = 0;
    private String currentState = "q0";
      private String textToDraw = "";

    /**
     * Creates new form TuringMachineSimulation
     */
    public TuringMulticinta() {
        initComponents();
        setupTable();
    }

    private void setupTable() {
        tableModel = new DefaultTableModel(
                new Object[][]{},
                new String[]{"Estado Actual", "Simbolo", "Simbolo siguiente", "Simbolo de escritura", "Dirección"}
        );
        tablaTransicion.setModel(tableModel);
    }
    
    // Custom JPanel class to override paintComponent method
    private class CustomPanel extends JPanel {

        @Override
        protected void paintComponent(Graphics g) {
            super.paintComponent(g);
            /*centrar la cadena a analizar en el panel, dicha cadena simulara una cinta
            dibujada en el panel.
            */    
            g.drawString(textToDraw, (getWidth() - g.getFontMetrics().stringWidth(textToDraw)) / 2, getHeight() / 2);
            

        }
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
        analizarCinta = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        iniciarProceso = new javax.swing.JButton();
        pasoAnterior = new javax.swing.JButton();
        ejecutarAnalisis = new javax.swing.JButton();
        detenerAnalisis = new javax.swing.JButton();
        pasoSiguiente = new javax.swing.JButton();
        velocidadDeSeguimiento = new javax.swing.JComboBox<>();
        areaTransiciones = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaTransicion = new javax.swing.JTable();
        limpiarTabla = new javax.swing.JButton();
        guardarDatosEnTabla = new javax.swing.JButton();
        abrirDatosEnTabla = new javax.swing.JButton();
        agregarFilaTabla = new javax.swing.JButton();
        agregarEjemplo = new javax.swing.JButton();
        AreaSeguimiento = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        areaPieVentana = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));

        areaEncabezadoVentana.setBackground(new java.awt.Color(0, 60, 84));
        areaEncabezadoVentana.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel1.setBackground(new java.awt.Color(255, 255, 255));
        jLabel1.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Simulación de Máquina de Turing Multicinta");

        javax.swing.GroupLayout areaEncabezadoVentanaLayout = new javax.swing.GroupLayout(areaEncabezadoVentana);
        areaEncabezadoVentana.setLayout(areaEncabezadoVentanaLayout);
        areaEncabezadoVentanaLayout.setHorizontalGroup(
            areaEncabezadoVentanaLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaEncabezadoVentanaLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 918, Short.MAX_VALUE)
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

        analizarCinta.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N

        jLabel8.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel8.setText("Velocidad de seguimiento (ms)");

        iniciarProceso.setBackground(new java.awt.Color(0, 60, 84));
        iniciarProceso.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        iniciarProceso.setForeground(new java.awt.Color(255, 255, 255));
        iniciarProceso.setText("Inicializar");
        iniciarProceso.setBorder(null);
        iniciarProceso.setOpaque(true);
        iniciarProceso.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iniciarProcesoActionPerformed(evt);
            }
        });

        pasoAnterior.setBackground(new java.awt.Color(0, 60, 84));
        pasoAnterior.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pasoAnterior.setForeground(new java.awt.Color(255, 255, 255));
        pasoAnterior.setText("Paso anterior ");
        pasoAnterior.setBorder(null);
        pasoAnterior.setOpaque(true);
        pasoAnterior.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasoAnteriorActionPerformed(evt);
            }
        });

        ejecutarAnalisis.setBackground(new java.awt.Color(0, 60, 84));
        ejecutarAnalisis.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        ejecutarAnalisis.setForeground(new java.awt.Color(255, 255, 255));
        ejecutarAnalisis.setText("Ejecutar");
        ejecutarAnalisis.setBorder(null);
        ejecutarAnalisis.setOpaque(true);
        ejecutarAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ejecutarAnalisisActionPerformed(evt);
            }
        });

        detenerAnalisis.setBackground(new java.awt.Color(0, 60, 84));
        detenerAnalisis.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        detenerAnalisis.setForeground(new java.awt.Color(255, 255, 255));
        detenerAnalisis.setText("Detener");
        detenerAnalisis.setBorder(null);
        detenerAnalisis.setOpaque(true);
        detenerAnalisis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                detenerAnalisisActionPerformed(evt);
            }
        });

        pasoSiguiente.setBackground(new java.awt.Color(0, 60, 84));
        pasoSiguiente.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        pasoSiguiente.setForeground(new java.awt.Color(255, 255, 255));
        pasoSiguiente.setText("Paso siguiente");
        pasoSiguiente.setBorder(null);
        pasoSiguiente.setOpaque(true);
        pasoSiguiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pasoSiguienteActionPerformed(evt);
            }
        });

        velocidadDeSeguimiento.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Velocidad", "500 ms", "1000 ms", "2000 ms" }));

        javax.swing.GroupLayout areaConfiguracionLayout = new javax.swing.GroupLayout(areaConfiguracion);
        areaConfiguracion.setLayout(areaConfiguracionLayout);
        areaConfiguracionLayout.setHorizontalGroup(
            areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaConfiguracionLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(iniciarProceso, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(analizarCinta)
                    .addGroup(areaConfiguracionLayout.createSequentialGroup()
                        .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(ejecutarAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(pasoAnterior, javax.swing.GroupLayout.DEFAULT_SIZE, 127, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(pasoSiguiente, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(detenerAnalisis, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 268, Short.MAX_VALUE)
                    .addGroup(areaConfiguracionLayout.createSequentialGroup()
                        .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(velocidadDeSeguimiento, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addComponent(analizarCinta, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel8)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(velocidadDeSeguimiento, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(iniciarProceso, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ejecutarAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(detenerAnalisis, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaConfiguracionLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pasoAnterior, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(pasoSiguiente, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        areaTransiciones.setBackground(new java.awt.Color(255, 255, 255));
        areaTransiciones.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        jLabel3.setFont(new java.awt.Font("Arial", 1, 28)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 60, 84));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("Transiciones");

        tablaTransicion.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        tablaTransicion.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Estado Actual", "Simbolo", "Simbolo siguiente", "Simbolo de escritura", "Dirección"
            }
        ));
        tablaTransicion.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                tablaTransicionMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(tablaTransicion);

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

        agregarFilaTabla.setBackground(new java.awt.Color(0, 60, 84));
        agregarFilaTabla.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        agregarFilaTabla.setForeground(new java.awt.Color(255, 255, 255));
        agregarFilaTabla.setText("Agregar Fila");
        agregarFilaTabla.setBorder(null);
        agregarFilaTabla.setOpaque(true);
        agregarFilaTabla.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarFilaTablaActionPerformed(evt);
            }
        });

        agregarEjemplo.setBackground(new java.awt.Color(0, 60, 84));
        agregarEjemplo.setFont(new java.awt.Font("Segoe UI", 1, 12)); // NOI18N
        agregarEjemplo.setForeground(new java.awt.Color(255, 255, 255));
        agregarEjemplo.setText("Agregar Ejemplo");
        agregarEjemplo.setBorder(null);
        agregarEjemplo.setOpaque(true);
        agregarEjemplo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarEjemploActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout areaTransicionesLayout = new javax.swing.GroupLayout(areaTransiciones);
        areaTransiciones.setLayout(areaTransicionesLayout);
        areaTransicionesLayout.setHorizontalGroup(
            areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaTransicionesLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaTransicionesLayout.createSequentialGroup()
                        .addGap(106, 106, 106)
                        .addComponent(guardarDatosEnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(80, 80, 80)
                        .addComponent(abrirDatosEnTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(areaTransicionesLayout.createSequentialGroup()
                        .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, areaTransicionesLayout.createSequentialGroup()
                                .addComponent(agregarFilaTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(agregarEjemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(63, 63, 63)
                                .addComponent(limpiarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 153, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );
        areaTransicionesLayout.setVerticalGroup(
            areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(areaTransicionesLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(22, 22, 22)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(areaTransicionesLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(agregarEjemplo, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(limpiarTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(agregarFilaTabla, javax.swing.GroupLayout.PREFERRED_SIZE, 34, javax.swing.GroupLayout.PREFERRED_SIZE))
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

        jPanel1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 112, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout AreaSeguimientoLayout = new javax.swing.GroupLayout(AreaSeguimiento);
        AreaSeguimiento.setLayout(AreaSeguimientoLayout);
        AreaSeguimientoLayout.setHorizontalGroup(
            AreaSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AreaSeguimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(AreaSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        AreaSeguimientoLayout.setVerticalGroup(
            AreaSeguimientoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(AreaSeguimientoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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

        if (fileChooser.showSaveDialog(TuringMulticinta.this) == JFileChooser.APPROVE_OPTION) {
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
                JOptionPane.showMessageDialog(TuringMulticinta.this, "Archivo guardado exitosamente.");
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(TuringMulticinta.this, "Error al guardar el archivo: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_guardarDatosEnTablaActionPerformed

    private void pasoAnteriorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasoAnteriorActionPerformed
        //función de paso anterior

        if (headPosition > 0) {
            headPosition--;
            dibujarCadena();
        }

        if (headPosition < 0) {
            headPosition = 0;
        }

        updateLog("Paso anterior: " + tape.charAt(headPosition));
    }//GEN-LAST:event_pasoAnteriorActionPerformed

    private void ejecutarAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ejecutarAnalisisActionPerformed
        isRunning = true;
        while (isRunning && !currentState.equals("qf")) {
            step();
        }
        dibujarCadena(); // Llama a dibujarCadena después de completar la ejecución
        updateLog("Análisis completado.");
    }//GEN-LAST:event_ejecutarAnalisisActionPerformed

    private void detenerAnalisisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_detenerAnalisisActionPerformed
        isRunning = false;
        updateLog("Análisis detenido.");
    }//GEN-LAST:event_detenerAnalisisActionPerformed

    private void pasoSiguienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pasoSiguienteActionPerformed
        step();
        dibujarCadena();
    }//GEN-LAST:event_pasoSiguienteActionPerformed

    private void iniciarProcesoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iniciarProcesoActionPerformed
        tape = analizarCinta.getText();
        headPosition = 0;
        currentState = "q0"; // Inicializa el estado al estado inicial
        currentStep = 0; // Reinicia el contador de pasos
        isRunning = true;
        updateLog("Proceso iniciado con cinta: " + tape);
        dibujarCadena();
        textToDraw = analizarCinta.getText();
    }//GEN-LAST:event_iniciarProcesoActionPerformed

    private void abrirDatosEnTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_abrirDatosEnTablaActionPerformed
        JFileChooser fileChooser = new JFileChooser();
        if (fileChooser.showOpenDialog(TuringMulticinta.this) == JFileChooser.APPROVE_OPTION) {
            File file = fileChooser.getSelectedFile();
            try (BufferedReader in = new BufferedReader(new FileReader(file))) {
                String line;
                tableModel.setRowCount(0);
                while ((line = in.readLine()) != null) {
                    Vector<String> rowData = new Vector<>();
                    for (String val : line.split(",")) {
                        rowData.add(val);
                    }
                    tableModel.addRow(rowData);
                }
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(TuringMulticinta.this, "Error al cargar el archivo: " + ex.getMessage());
            }
        }
    }//GEN-LAST:event_abrirDatosEnTablaActionPerformed

    private void agregarFilaTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarFilaTablaActionPerformed
        int selectedRow = tablaTransicion.getSelectedRow();
        if (selectedRow == -1) {
            tableModel.addRow(new Object[]{"", "", "", "", ""});
        } else {
            tableModel.insertRow(selectedRow + 1, new Object[]{"", "", "", "", ""});
        }
    }//GEN-LAST:event_agregarFilaTablaActionPerformed

    private void agregarEjemploActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarEjemploActionPerformed
        tableModel.setRowCount(0);
        // Definir las transiciones de la máquina de Turing
        tableModel.addRow(new Object[]{"q0", "0", "q0", "0", "R"});
        tableModel.addRow(new Object[]{"q0", "1", "q1", "1", "R"});
        tableModel.addRow(new Object[]{"q1", "0", "q1", "0", "R"});
        tableModel.addRow(new Object[]{"q1", "1", "q0", "1", "R"});
        tableModel.addRow(new Object[]{"q0", " ", "qf", " ", "R"}); // Transición de aceptación
        tableModel.addRow(new Object[]{"q1", " ", "qf", " ", "R"}); // Transición de aceptación

    }//GEN-LAST:event_agregarEjemploActionPerformed

    private void limpiarTablaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_limpiarTablaActionPerformed
        tableModel.setRowCount(0);
    }//GEN-LAST:event_limpiarTablaActionPerformed

    private void tablaTransicionMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaTransicionMousePressed
        if (SwingUtilities.isRightMouseButton(evt)) {
            int row = tablaTransicion.rowAtPoint(evt.getPoint());
            tablaTransicion.setRowSelectionInterval(row, row);

            JPopupMenu popupMenu = new JPopupMenu();
            JMenuItem limpiarItem = new JMenuItem("Limpiar contenido de la fila");
            JMenuItem eliminarItem = new JMenuItem("Eliminar fila");

            limpiarItem.addActionListener(e -> {
                for (int i = 0; i < tablaTransicion.getColumnCount(); i++) {
                    tablaTransicion.setValueAt("", row, i);
                }
            });

            eliminarItem.addActionListener(e -> {
                tableModel.removeRow(row);
            });

            popupMenu.add(limpiarItem);
            popupMenu.add(eliminarItem);
            popupMenu.show(evt.getComponent(), evt.getX(), evt.getY());
        }
    }//GEN-LAST:event_tablaTransicionMousePressed

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
    private javax.swing.JButton agregarEjemplo;
    private javax.swing.JButton agregarFilaTabla;
    private javax.swing.JTextField analizarCinta;
    private javax.swing.JPanel areaConfiguracion;
    private javax.swing.JPanel areaEncabezadoVentana;
    private javax.swing.JPanel areaPieVentana;
    private javax.swing.JPanel areaTransiciones;
    private javax.swing.JButton detenerAnalisis;
    private javax.swing.JButton ejecutarAnalisis;
    private javax.swing.JButton guardarDatosEnTabla;
    private javax.swing.JButton iniciarProceso;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton limpiarTabla;
    private javax.swing.JButton pasoAnterior;
    private javax.swing.JButton pasoSiguiente;
    private javax.swing.JTable tablaTransicion;
    private javax.swing.JComboBox<String> velocidadDeSeguimiento;
    // End of variables declaration//GEN-END:variables

}
