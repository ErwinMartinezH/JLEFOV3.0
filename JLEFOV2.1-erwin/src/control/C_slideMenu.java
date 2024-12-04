/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package control;

import ConversionesSemanticas.Con_ADPtoGR;
import HerramientaADP.Automata;
import HerramientaADP.Estado;
import HerramientaADP.Estados_Validos;
import HerramientaADP.Transicion;
import funciones.LienzoFromScroll;
import modelo.M_estado;
import modelo.M_transicion;
//import prueba.pruebas;
import vista.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import static funciones.NmComponentes.*;
import static java.awt.event.InputEvent.CTRL_MASK;
import static java.awt.event.InputEvent.SHIFT_MASK;
import static java.awt.event.KeyEvent.VK_G;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Stack;
import javax.swing.table.DefaultTableModel;

/**
 * @author herma
 */
public class C_slideMenu implements ActionListener {

    static boolean estados = false;
    private V_popupmenu menu;
    private V_interfaz interfaz;
    private C_interfaz interfaz2;
    private List<M_estado> activo;
    private final String ACEPTACION = "Edo-Aceptacion";
    private final String NO_ACEPTACION = "Edo-No-Aceptacion";
    // Declarar el mapa a nivel de clase
    private Map<String, Boolean> botonEstados = new HashMap<>();
    private final boolean EDOACEP = true;
    private final boolean EDONOACEP = false;
    static boolean seleccionar = false;
    public Estados_Validos validos;
    private int estadoAct = 0;
    private int estadoAnt = 0;
    static boolean transicion = false;
    private boolean analizar = false;
    private boolean evaluar = false;
    private boolean ejemplos = false;
    V_tabs tabs;

    public V_lienzo lienzo;
    public V_popupmenu acept;
    // public pruebas p;
    public Estados_Validos Evalido;
    ArrayList<Estado> estado = new ArrayList();

    ArrayList<Integer> listSeguimientoEstados;
    ArrayList<Stack<Character>> listSeguimientoPila;
    int iContadoPosicionSeguimiento = -1;

    public C_slideMenu(V_tabs tabs) {
        this.tabs = tabs;
    }

    public void activandoBotonesdeComponent(ActionEvent e) {
        Component cmp = (Component) e.getSource();
        String name = cmp.getName();
        V_lienzo cursor = new LienzoFromScroll().obtener(tabs);
        cursor.setCursor(new Cursor(Cursor.CROSSHAIR_CURSOR));

        // Desactivar botones si estamos en un tipo de panel como AF o AFD
        if (cursor.getTipoPanel().equals(AF) || cursor.getTipoPanel().equals(AFD)) {
            if ("Ejemplos".equals(name) || "ADP a GLC".equals(name)) {
                // Desactivar el botón 
                JOptionPane.showMessageDialog(null, "Este botón no se puede usar en un AF o AFD en este momento. Ve al panel ADP y podras reactivar los botones en ADP al presionar el botón Estado", "Error", JOptionPane.ERROR_MESSAGE);
                interfaz2.slideMenu.getComponents()[5].setEnabled(false);
                interfaz2.slideMenu.getComponents()[3].setEnabled(false);
                if (evaluar && ejemplos) {
                    evaluar = false;
                    ejemplos = false;
                }
                interfaz2.slideMenu.updateUI();

            }
        } // Reactivar los botones cuando estemos en el tipo de panel ADP
        else if (cursor.getTipoPanel().equals(ADP)) {
            try {
                if ("Ejemplos".equals(name) || "ADP a GLC".equals(name) || "Dibujar Estados".equals(name)) {
                    // Activar el botón
                    interfaz2.slideMenu.getComponents()[5].setEnabled(true);
                    interfaz2.slideMenu.getComponents()[3].setEnabled(true);
                    interfaz2.slideMenu.updateUI();
                }
            } catch (Exception e1) {
                System.out.println(e1);
            }
        }

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Component cmp = (Component) e.getSource();
        Component cmpo = (Component) e.getSource();
        String name = cmpo.getName();
        V_lienzo cursor = new LienzoFromScroll().obtener(tabs);
        activandoBotonesdeComponent(e);
        switch (cmp.getName()) {
            case ESTADO:
                if (!estados) {
                    estados = true;
                    seleccionar = false;
                    transicion = false;
                    evaluar = false;
                    ejemplos = false;
                    analizar = false;
                    // V_lienzo cursor = new LienzoFromScroll().obtener(tabs);
                    System.out.println(cursor.getTipoPanel());
                    if (cursor.getTipoPanel().equals("Autómata Pila")) {
                        System.out.println("no agregar.............");
                        break;
                    }
                    cursor.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                break;
            case TRANSICION:
                if (!transicion) {
                    transicion = true;
                    estados = false;
                    seleccionar = false;
                    evaluar = false;
                    ejemplos = false;
                    analizar = false;
                    // V_lienzo cursor = new LienzoFromScroll().obtener(tabs);
                    if (cursor.getTipoPanel().equals("Autómata Pila")) {
                        break;
                    }
                    cursor.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                }
                break;
            case SELECCIONAR:
                if (!seleccionar) {
                    seleccionar = true;
                    estados = false;
                    transicion = false;
                    evaluar = false;
                    ejemplos = false;
                    analizar = false;
                    // V_lienzo cursor = new LienzoFromScroll().obtener(tabs);
                    cursor.setCursor(new Cursor(Cursor.HAND_CURSOR));
                }
                break;
            case ANALIZAR:
                if (!analizar) {
                    analizar = true;
                    seleccionar = false;
                    evaluar = false;
                    estados = false;
                    transicion = false;
                    ejemplos = false;

                }
                analizar = false;
                if (tabs.getSelectedComponent() != null) {
                    V_lienzo check = new LienzoFromScroll().obtener(tabs);
                    check.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    MouseListener[] ml = check.getMouseListeners();
                    C_automata c = (C_automata) ml[0];
                    if (!check.getTipoPanel().equals(ER) && !c.getEstados().isEmpty()
                            && !c.getTransiciones().isEmpty() && !check.getTipoPanel().equals(GLC)
                            && !check.getTipoPanel().equals(ADP)) {
                        check.rastreo();

                    } else if (check.getTipoPanel().equals(ADP)) {
                        System.out.println("Soy ADP");
                        if (tabs.getSelectedComponent() != null) {
                            Paso_paso ps = new Paso_paso();
                            if (!check.getTipoPanel().equals(ER) && !c.getEstados().isEmpty()
                                    && !c.getTransiciones().isEmpty() && !check.getTipoPanel().equals(GLC)
                                    && !check.getTipoPanel().equals(AF)) {// analiza el automata correctamente
                                // check.paso_paso();
                                Paso_paso p = new Paso_paso();
                                p.getjButton2().addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {// accion de boton de retroceso
                                        System.out.println("Soy anterior");

                                        iContadoPosicionSeguimiento--;

                                        if (iContadoPosicionSeguimiento <= -1) {
                                            iContadoPosicionSeguimiento = 0;
                                        }

                                        if (iContadoPosicionSeguimiento < listSeguimientoPila.size()
                                                && iContadoPosicionSeguimiento < listSeguimientoEstados.size()) {
                                            Object[] nuevaFila = listSeguimientoPila.get(iContadoPosicionSeguimiento)
                                                    .toArray();
                                            Collections.reverse(Arrays.asList(nuevaFila));

                                            DefaultTableModel model = (DefaultTableModel) p.getjTable1().getModel();
                                            model.setRowCount(0);

                                            for (Object object : nuevaFila) {
                                                model.addRow(new Object[]{object});
                                            }

                                            int iNodoID = listSeguimientoEstados.get(iContadoPosicionSeguimiento);

                                            for (M_estado estado1 : c.getEstados()) {
                                                if (estado1.getIdEstado() == iNodoID) {
                                                    estado1.setColores(Color.orange, Color.black, Color.WHITE);
                                                } else {
                                                    estado1.setColores(Color.blue, Color.yellow, Color.WHITE);
                                                }
                                            }

                                            p.getjTable1().setModel(model);
                                            p.repaint();
                                            p.getjTable1().repaint();

                                            check.repaint();
                                        }

                                    }
                                });
                                p.getjButton3().addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {// Accion de boton de avance

                                        System.out.println("Soy siguiente");
                                        iContadoPosicionSeguimiento++;

                                        if (!(iContadoPosicionSeguimiento < listSeguimientoPila.size())) {
                                            iContadoPosicionSeguimiento = 0;
                                        }
                                        System.out.println("---------------Ver cantidades seguimientio-----------");
                                        System.out.println(iContadoPosicionSeguimiento);
                                        System.out.println(listSeguimientoPila.size());
                                        System.out.println(listSeguimientoEstados.size());
                                        if (iContadoPosicionSeguimiento < listSeguimientoPila.size()
                                                && iContadoPosicionSeguimiento < listSeguimientoEstados.size()) {
                                            Object[] nuevaFila = listSeguimientoPila.get(iContadoPosicionSeguimiento)
                                                    .toArray();
                                            Collections.reverse(Arrays.asList(nuevaFila));

                                            DefaultTableModel model = (DefaultTableModel) p.getjTable1().getModel();
                                            model.setRowCount(0);

                                            for (Object object : nuevaFila) {
                                                model.addRow(new Object[]{object});
                                            }

                                            int iNodoID = listSeguimientoEstados.get(iContadoPosicionSeguimiento);

                                            for (M_estado estado1 : c.getEstados()) {
                                                System.out.println(estado1.getTipo());
                                                if (estado1.getIdEstado() == iNodoID
                                                        && estado1.getTipo().equals("Edo-Aceptacion")) {
                                                    estado1.setColores(Color.GREEN, Color.yellow, Color.WHITE);
                                                } else if (estado1.getIdEstado() == iNodoID) {
                                                    estado1.setColores(Color.orange, Color.black, Color.WHITE);
                                                    //
                                                } else {
                                                    estado1.setColores(Color.blue, Color.yellow, Color.WHITE);
                                                }
                                            }

                                            p.getjTable1().setModel(model);
                                            p.repaint();
                                            p.getjTable1().repaint();

                                            check.repaint();
                                        }

                                    }
                                });
                                p.getjButton1().addActionListener(new ActionListener() {
                                    @Override
                                    public void actionPerformed(ActionEvent e) {// accion del boton que evalua la cadena
                                        System.out.println(
                                                "--------------------------Evaluando cadena--------------------------");
                                        // String cadena = p.getjTextField2().getText();
                                        // System.out.println(cadena);
                                        List<M_transicion> transiciones = c.getTransiciones();
                                        List<M_estado> edos = c.getEstados();
                                        System.out.println("transiciones: " + transiciones);
                                        System.out.println("edos: " + edos);
                                        ArrayList<Estado> estados = new ArrayList();
                                        HashMap<Integer, ArrayList<Transicion>> mapEstadosAutomata = new HashMap<>();
                                        listSeguimientoEstados = new ArrayList<>();
                                        iContadoPosicionSeguimiento = -1;
                                        Automata au = new Automata();
                                        for (int i = 0; i < transiciones.size(); i++) {
                                            M_transicion mTransicion = transiciones.get(i);
                                            int iNodoOrigen = mTransicion.getOrigen();
                                            int iNodoDestino = mTransicion.getDestino();
                                            String[] sValores = mTransicion.getAlfabeto().split(",");
                                            String sLectura = sValores[0].substring(1);
                                            String sPop = sValores[1].substring(1);
                                            String sPush = sValores[2].substring(1, sValores[2].length() - 1);
                                            System.out.println("Origen: " + iNodoOrigen + " Destino: " + iNodoDestino
                                                    + " sLectura: " + sLectura + " pop: " + sPop + " spush: " + sPush);
                                            au.addTransicion(iNodoOrigen, sLectura.charAt(0), sPop.charAt(0),
                                                    sPush.charAt(0), iNodoDestino);
                                        }
                                        for (M_estado estado : edos) {
                                            int iNodoId = estado.getIdEstado();
                                            au.setAceptacion(iNodoId, estado.getTipo().equals("Edo-Aceptacion"));
                                        }
                                        //
                                        System.out.println("----------------------Estados para evaluar-------------");
                                        for (Estado estado : estados) {
                                            System.out.println(
                                                    "estaod: " + estado.getNombre() + " " + estado.getAceptacion());
                                            System.out.println(estado.getTransiciones());
                                        }
                                        // System.out.println(estados);
                                        if (p.getjTextField2().getText().isEmpty()) {
                                            p.getjTextField2().setText("?");
                                        }
                                        System.out.println(p.getjTextField2().getText());
                                        ;
                                        au.start(p.getjTextField2().getText().toCharArray());
                                        // aqui se añade una condicional donde se pregunta si el automata acepta la
                                        // cadena o no
                                        if (au.startg(p.getjTextField2().getText().toCharArray())) {
                                            // JOptionPane.showMessageDialog(tabs, "Cadena aceptada");
                                            // muestra en verde(RGB) el backgroud del label
                                            p.setLabel("Cadena aceptada");
                                            p.getJPanel3().setBackground(new Color(0, 255, 0));
                                        } else {
                                            // JOptionPane.showMessageDialog(tabs, "Cadena rechazada");
                                            // muestra en rojo(RGB) el backgroud del label
                                            p.setLabel("Cadena rechazada");
                                            p.getJPanel3().setBackground(new Color(241, 61, 47, 255));

                                        }
                                        Estados_Validos[] esVa = au.getRecorrido();
                                        listSeguimientoEstados = new ArrayList<>();
                                        if (esVa != null && esVa.length >= 1) {
                                            listSeguimientoEstados.add(esVa[0].origen);
                                        }
                                        for (Estados_Validos estados_Valido : esVa) {
                                            System.out.println(estados_Valido);
                                            listSeguimientoEstados.add(estados_Valido.destino);
                                        }
                                        // System.out.println("estado: " + au.getListSeguimientoEstados());
                                        // System.out.println("pila: " + au.getListSeguimientoPila());
                                        listSeguimientoPila = au.getListSeguimientoPila(esVa);

                                        for (Stack<Character> stack : listSeguimientoPila) {
                                            System.out.println(stack);
                                        }
                                        // listSeguimientoEstados = au.getListSeguimientoEstados();
                                        // DefaultTableModel model = new DefaultTableModel();
                                        // Crear una JTable con el modelo de datos

                                    }
                                });
                                p.setVisible(true);
                                p.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
                            } else {
                                JOptionPane.showMessageDialog(tabs, "No hay nada que analizar",
                                        "Analizar Autómata", JOptionPane.INFORMATION_MESSAGE);
                                break;
                            }
                        } else {
                            JOptionPane.showMessageDialog(tabs, "No hay nada que analizar",
                                    "Analizar Autómata", JOptionPane.INFORMATION_MESSAGE);
                            break;
                        }
                        break;
                    }
                } else {
                    JOptionPane.showMessageDialog(tabs, "No hay nada que analizar",
                            "Analizar Autómata", JOptionPane.INFORMATION_MESSAGE);
                    break;
                }
                break;

            case EVALUAR:
                V_lienzo check = new LienzoFromScroll().obtener(tabs);
                if (tabs.getSelectedComponent() != null) {
                    if (!evaluar) {
                        evaluar = true;
                        analizar = false;
                        estados = false;
                        transicion = false;
                        seleccionar = false;
                        ejemplos = false;
                    }

                    // V_lienzo check = new LienzoFromScroll().obtener(tabs);
                    check.setCursor(new Cursor(Cursor.DEFAULT_CURSOR));
                    MouseListener[] ml = check.getMouseListeners();
                    C_automata c = (C_automata) ml[0];

                    if (!check.getTipoPanel().equals(ER) && !c.getEstados().isEmpty()
                            && !c.getTransiciones().isEmpty() && !check.getTipoPanel().equals(GLC)
                            && !check.getTipoPanel().equals(AF) && !check.getTipoPanel().equals(AFD)) {
                        ArrayList<EstructurasSemanticas.Estado> est = new ArrayList<>();
                        EstructurasSemanticas.Automata a = new EstructurasSemanticas.Automata();
                        Con_ADPtoGR con = new Con_ADPtoGR();
                        c.getTransiciones().forEach((t) -> {
                            // Obtener los componentes de la transición
                            int origen = t.getOrigen();
                            char lectura = t.getAlfabeto().charAt(1);
                            char pop = t.getAlfabeto().charAt(4);
                            char push = t.getAlfabeto().charAt(7);
                            int destino = t.getDestino();
                            // Añadir la transición al autómata
                            a.addTransicion(origen, lectura, pop, push, destino);
                            System.out.println(origen + " " + lectura + " " + pop + " " + push + " " + destino);
                        });
                        c.getEstados().forEach((y) -> {
                            if (y.getTipo().equals("Edo-Aceptacion")) {
                                boolean acepto = true;
                                System.out.println(
                                        y.getIdEstado() + " " + y.getEtiqueta() + " " + y.getTipo() + " " + acepto);
                                a.setAceptacion(y.getIdEstado(), acepto);
                            } else {
                                System.out.println("No es estado aceptacion");
                            }
                        });
                        con.conversion(a.estados());

                    } else {
                        // cmp.setEnabled(false);
                        if (check.getTipoPanel().equals(ADP)) {
                            JOptionPane.showMessageDialog(tabs, "No hay nada que convertir de ADP a GLC",
                                    "Convertir ADP a GLC", JOptionPane.INFORMATION_MESSAGE);
                        } else if (check.getTipoPanel().equals(AF)) {
                            JOptionPane.showMessageDialog(tabs, "No hay nada que convertir de AF a GLC",
                                    "Convertir AF a GLC", JOptionPane.INFORMATION_MESSAGE);
                        }

                        break;
                    }
                } else {
                    // cmp.setEnabled(false);
                    if (check.getTipoPanel().equals(ADP)) {
                        JOptionPane.showMessageDialog(tabs, "No hay nada que convertir de ADP a GLC",
                                "Convertir ADP a GLC", JOptionPane.INFORMATION_MESSAGE);
                    } else if (check.getTipoPanel().equals(AF)) {

                        JOptionPane.showMessageDialog(tabs, "No hay nada que convertir de AF a GLC",
                                "Convertir AF a GLC", JOptionPane.INFORMATION_MESSAGE);
                    }
                    break;
                }

                break;
            case EJEMPLOS:

                System.out.println("---------------------ejemplooooooooooooooooooooos---------------------");
                // V_lienzo cursor = new LienzoFromScroll().obtener(tabs);
                System.out.println(cursor.getTipoPanel());
                if (cursor.getTipoPanel().equals(ADP)) {
                    // cmp.setVisible(true); // Mostrar el botón en panel ADP
                    // System.out.println(cursor.ctrl.getTransiciones());
                    List<M_estado> esta = new ArrayList<>();
                    List<M_transicion> trnasiciones = new ArrayList<>();
                    String[] opciones = {"Ejemplo 1", "Ejemplo 2", "Ejemplo 3"};
                    JComboBox b_ejeplosGramaticas = new JComboBox<>(opciones);
                    int opcion = JOptionPane.showConfirmDialog(null, b_ejeplosGramaticas, "Ingrese un valor",
                            JOptionPane.OK_CANCEL_OPTION);

                    // Verificar si se presionó OK (u otra opción según tus necesidades)
                    if (opcion == JOptionPane.OK_OPTION) {
                        // Obtener el valor ingresado
                        String seleccion = (String) b_ejeplosGramaticas.getSelectedItem();
                        System.out.println(seleccion);
                        if (seleccion.equals("Ejemplo 1")) {
                            esta.add(new M_estado(100, 120, 0, "Edo-Inicial"));
                            esta.add(new M_estado(250, 160, 1, "Edo-Transicion"));
                            esta.add(new M_estado(350, 220, 2, "Edo-Aceptacion"));

                            trnasiciones.add(new M_transicion(100, 120, 100, 120, "arco", 0, 0, "(a, ?, a)"));
                            trnasiciones.add(new M_transicion(100, 140, 100, 140, "arco", 0, 0, "(b, ?, b)"));
                            trnasiciones.add(new M_transicion(100, 120, 250, 160, "simple", 0, 1, "(?, ?, ?)"));
                            trnasiciones.add(new M_transicion(250, 160, 250, 160, "arco", 1, 1, "(a, a, ?)"));
                            trnasiciones.add(new M_transicion(250, 180, 250, 180, "arco", 1, 1, "(b, b, ?)"));
                            trnasiciones.add(new M_transicion(250, 160, 350, 220, "simple", 1, 2, "(?, Z, ?)"));

                            JFrame frameConversionADPaGLC;

                            frameConversionADPaGLC = new JFrame("Conversión ADP a un GLC");
                            frameConversionADPaGLC.setSize(500, 500);
                            frameConversionADPaGLC.setLocationRelativeTo(null);

                            ImageIcon icono = new ImageIcon("src/img_icon/arbol.png");
                            frameConversionADPaGLC.setIconImage(icono.getImage());
                            frameConversionADPaGLC.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                            frameConversionADPaGLC.setVisible(false);

                            V_panelADPaGLC conversionADPaGLC = new V_panelADPaGLC(esta, trnasiciones, 0);
                            JScrollPane jscroll = new JScrollPane(conversionADPaGLC);

                            frameConversionADPaGLC.add(jscroll);
                            frameConversionADPaGLC.setVisible(true);
                        } else if (seleccion.equals("Ejemplo 2")) {

                            esta.add(new M_estado(100, 120, 0, "Edo-Inicial"));
                            esta.add(new M_estado(250, 160, 1, "Edo-Transicion"));
                            esta.add(new M_estado(400, 200, 2, "Edo-Transicion"));
                            esta.add(new M_estado(550, 240, 3, "Edo-Transicion"));
                            esta.add(new M_estado(700, 280, 4, "Edo-Aceptacion"));

                            trnasiciones.add(new M_transicion(100, 120, 250, 160, "simple", 0, 1, "(0, ?, 0)"));
                            trnasiciones.add(new M_transicion(250, 160, 250, 160, "arco", 1, 1, "(0, ?, 0)"));
                            trnasiciones.add(new M_transicion(250, 160, 400, 200, "simple", 1, 2, "(1, ?, ?)"));
                            trnasiciones.add(new M_transicion(400, 200, 400, 200, "arco", 2, 2, "(1, ?, ?)"));
                            trnasiciones.add(new M_transicion(400, 200, 550, 240, "simple", 2, 3, "(0, 0, ?)"));
                            trnasiciones.add(new M_transicion(550, 240, 550, 240, "arco", 3, 3, "(0, 0, ?)"));
                            trnasiciones.add(new M_transicion(550, 240, 700, 280, "simple", 3, 4, "(?, Z, ?)"));

                            JFrame frameConversionADPaGLC;

                            frameConversionADPaGLC = new JFrame("Conversión ADP a un GLC");
                            frameConversionADPaGLC.setSize(500, 500);
                            frameConversionADPaGLC.setLocationRelativeTo(null);

                            ImageIcon icono = new ImageIcon("src/img_icon/arbol.png");
                            frameConversionADPaGLC.setIconImage(icono.getImage());
                            frameConversionADPaGLC.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                            frameConversionADPaGLC.setVisible(false);

                            V_panelADPaGLC conversionADPaGLC = new V_panelADPaGLC(esta, trnasiciones, 1);
                            JScrollPane jscroll = new JScrollPane(conversionADPaGLC);

                            frameConversionADPaGLC.add(jscroll);
                            frameConversionADPaGLC.setVisible(true);
                        } else if (seleccion.equals("Ejemplo 3")) {
                            esta.add(new M_estado(100, 120, 0, "Edo-Inicial"));
                            esta.add(new M_estado(250, 160, 1, "Edo-Transicion"));
                            esta.add(new M_estado(400, 200, 2, "Edo-Transicion"));
                            esta.add(new M_estado(550, 240, 3, "Edo-Transicion"));
                            esta.add(new M_estado(700, 280, 4, "Edo-Aceptacion"));

                            trnasiciones.add(new M_transicion(100, 120, 250, 160, "simple", 0, 1, "(a, ?, 1)"));
                            trnasiciones.add(new M_transicion(250, 160, 250, 160, "arco", 1, 1, "(a, ?, 1)"));
                            trnasiciones.add(new M_transicion(250, 160, 400, 200, "simple", 1, 2, "(b, ?, ?)"));
                            trnasiciones.add(new M_transicion(400, 200, 550, 240, "simple", 2, 3, "(c, 1, ?)"));
                            trnasiciones.add(new M_transicion(550, 240, 550, 240, "arco", 3, 3, "(c, 1, ?)"));
                            trnasiciones.add(new M_transicion(550, 240, 700, 280, "simple", 3, 4, "(c, Z, ?)"));
                            trnasiciones.add(new M_transicion(700, 280, 700, 280, "arco", 4, 4, "(?, ?, ?)"));

                            JFrame frameConversionADPaGLC;

                            frameConversionADPaGLC = new JFrame("Conversión ADP a un GLC");
                            frameConversionADPaGLC.setSize(500, 500);
                            frameConversionADPaGLC.setLocationRelativeTo(null);

                            ImageIcon icono = new ImageIcon("src/img_icon/arbol.png");
                            frameConversionADPaGLC.setIconImage(icono.getImage());
                            frameConversionADPaGLC.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
                            frameConversionADPaGLC.setVisible(false);

                            V_panelADPaGLC conversionADPaGLC = new V_panelADPaGLC(esta, trnasiciones, 2);
                            JScrollPane jscroll = new JScrollPane(conversionADPaGLC);

                            frameConversionADPaGLC.add(jscroll);
                            frameConversionADPaGLC.setVisible(true);
                        }

                        // Hacer algo con el valor (en este caso, mostrarlo en la consola)
                        // System.out.println("Valor ingresado: " + valorIngresado);
                    }

                    cursor.ctrl.setEstados(esta);
                    cursor.ctrl.setTransiciones(trnasiciones);
                    cursor.repaint();
                    // .add(new M_estado(50, 50, 0, "Edo-Transicion"));
                    // activo.add(new M_estado(200, 150, 1, "Edo-Transicion"));
                    break;
                } else {
                    break;
                }
        }
    }
}
