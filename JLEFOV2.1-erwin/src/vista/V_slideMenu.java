/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import control.C_slideMenu;
import funciones.botonesLib.RSButtonMetro;
import modelo.M_estado;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import static funciones.NmComponentes.*;

/**
 * @author herma
 */
public class V_slideMenu extends JPanel {

    private V_tabs tabs;
    private C_slideMenu ctrl;
    public V_lienzo lienzo;
    private final String rutaIconos = "/img_icon/";

    V_slideMenu(V_tabs tabs) {
        this.tabs = tabs;
        ctrl = new C_slideMenu(tabs);
        componentes();
    }

    private void componentes() {
        setBackground(new Color(46, 66, 114));

        estado.setName(ESTADO);
        estado.setToolTipText(ESTADO);
        transicion.setName(TRANSICION);
        transicion.setToolTipText(TRANSICION);
        seleccionar.setName(SELECCIONAR);
        seleccionar.setToolTipText(SELECCIONAR);
        evaluar.setName(EVALUAR);
        evaluar.setToolTipText(EVALUAR);
        analizar.setName(ANALIZAR);
        analizar.setToolTipText(ANALIZAR);
        ejemplos.setName(EJEMPLOS);
        ejemplos.setToolTipText(EJEMPLOS);

        info.setFocusable(false);

        estado.setIcon(new ImageIcon(getClass()
                .getResource(rutaIconos + "automata-24.png")));
        estado.setColorHover(new Color(217, 189, 107));
        estado.setFocusable(false);
        estado.addActionListener(ctrl);
        estado.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlAction(e);
            }
        });
        add(estado);

        transicion.setIcon(new ImageIcon(getClass()
                .getResource(rutaIconos + "transicion-24.png")));
        transicion.setColorHover(new Color(217, 189, 107));
        transicion.setFocusable(false);
        transicion.addActionListener(ctrl);
        transicion.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlAction(e);
            }
        });
        add(transicion);

        seleccionar.setIcon(new ImageIcon(getClass()
                .getResource(rutaIconos + "seleccionar-24.png")));
        seleccionar.setColorHover(new Color(217, 189, 107));
        seleccionar.setFocusable(false);
        seleccionar.addActionListener(ctrl);
        seleccionar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlAction(e);
            }
        });
        add(seleccionar);

        evaluar.setIcon(new ImageIcon(getClass()
                .getResource(rutaIconos + "evaluar-24.png")));
        evaluar.setColorHover(new Color(217, 189, 107));
        evaluar.setFocusable(false);
        evaluar.addActionListener(ctrl);
        evaluar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlAction(e);
            }
        });
        add(evaluar);

        analizar.setIcon(new ImageIcon(getClass()
                .getResource(rutaIconos + "analizar-24.png")));
        analizar.setColorHover(new Color(217, 189, 107));
        analizar.setFocusable(false);
        analizar.addActionListener(ctrl);
        analizar.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controlAction(e);
            }
        });
        add(analizar);

        info.setBorder(BorderFactory.createTitledBorder(null,
                "INFORMACIÓN", TitledBorder.LEFT, TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14), Color.black));
        info.setFont(new Font("Arial", Font.BOLD, 12));
        info.setBackground(new Color(159, 176, 220));
        info.setForeground(new Color(42, 42, 42));
        info.setEditable(false);
        info.setText(BIENVENIDA);
        add(info);

        scroll.setViewportView(info);

        ejemplos.setColorHover(new Color(217, 189, 107));
        ejemplos.setFocusable(false);
        ejemplos.addActionListener(ctrl);
        // ejemplos.addMouseListener(new MouseAdapter() {
        //     @Override
        //     public void mouseClicked(MouseEvent e) {
        //         System.out.println("---------------------ejemplooooooooooooooooooooos---------------------");
        //     }
        // });
        add(ejemplos);

        layouts();
    }

    private void controlAction(MouseEvent e) {
        Component cmp = (Component) e.getSource();
        switch (cmp.getName()) {
            case ESTADO:
                estado.setSelected(true);
                transicion.setSelected(false);
                seleccionar.setSelected(false);
                evaluar.setSelected(false);
                analizar.setSelected(false);
                estado.setColorPresionado();
                transicion.setColorSinPresionar();
                seleccionar.setColorSinPresionar();
                evaluar.setColorSinPresionar();
                analizar.setColorSinPresionar();
                info.setText(INFO_ESTADO);
                break;
            case TRANSICION:
                transicion.setSelected(true);
                estado.setSelected(false);
                seleccionar.setSelected(false);
                evaluar.setSelected(false);
                analizar.setSelected(false);
                transicion.setColorPresionado();
                estado.setColorSinPresionar();
                seleccionar.setColorSinPresionar();
                evaluar.setColorSinPresionar();
                analizar.setColorSinPresionar();
                info.setText(INFO_TRANSICION);
                break;
            case SELECCIONAR:
                seleccionar.setSelected(true);
                estado.setSelected(false);
                transicion.setSelected(false);
                evaluar.setSelected(false);
                analizar.setSelected(false);
                seleccionar.setColorPresionado();
                estado.setColorSinPresionar();
                transicion.setColorSinPresionar();
                evaluar.setColorSinPresionar();
                analizar.setColorSinPresionar();
                info.setText(INFO_SELECCIONAR);
                break;
            case EVALUAR:
                evaluar.setSelected(true);
                estado.setSelected(false);
                transicion.setSelected(false);
                seleccionar.setSelected(false);
                ejemplos.setSelected(false);
                ejemplos.setColorSinPresionar();
                evaluar.setColorPresionado();
                estado.setColorSinPresionar();
                transicion.setColorSinPresionar();
                seleccionar.setColorSinPresionar();
                info.setText(INFO_EVALUAR);
                break;
            case ANALIZAR:
                analizar.setSelected(true);
                estado.setSelected(false);
                transicion.setSelected(false);
                evaluar.setSelected(false);
                seleccionar.setSelected(false);
                analizar.setColorPresionado();
                estado.setColorSinPresionar();
                transicion.setColorSinPresionar();
                evaluar.setColorSinPresionar();
                seleccionar.setColorSinPresionar();
                info.setText(INFO_ANALIZAR);
                break;
        }
    }

    private final RSButtonMetro analizar = new RSButtonMetro("        ANALIZAR");
    private final RSButtonMetro evaluar = new RSButtonMetro("       ADP a GLC");
    private final RSButtonMetro estado = new RSButtonMetro("           ESTADO");
    private final RSButtonMetro seleccionar = new RSButtonMetro("SELECCIONAR");
    private final RSButtonMetro transicion = new RSButtonMetro("   TRANSICIÓN");
    private final RSButtonMetro ejemplos = new RSButtonMetro("   Ejemplos");
    private final JTextPane info = new JTextPane();
    private final JScrollPane scroll = new JScrollPane();

    private void layouts() {
        GroupLayout panel_menuLayout = new GroupLayout(this);
        setLayout(panel_menuLayout);
        panel_menuLayout.setHorizontalGroup(
                panel_menuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel_menuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(panel_menuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                                        .addComponent(analizar, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(transicion, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(evaluar, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(seleccionar, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addComponent(ejemplos, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                        .addGroup(GroupLayout.Alignment.TRAILING,
                                                panel_menuLayout.createSequentialGroup()
                                                        .addGap(0, 0, Short.MAX_VALUE)
                                                        .addComponent(estado, GroupLayout.PREFERRED_SIZE, 140,
                                                                GroupLayout.PREFERRED_SIZE))
                                        .addGroup(panel_menuLayout.createSequentialGroup())
                                        .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 138,
                                                GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE))
                                .addContainerGap()));
        panel_menuLayout.setVerticalGroup(
                panel_menuLayout.createParallelGroup(GroupLayout.Alignment.LEADING)
                        .addGroup(panel_menuLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(estado, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(11, 11, 11)
                                .addComponent(transicion, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(seleccionar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(ejemplos, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(45, 45, 45)
                                .addComponent(evaluar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(analizar, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE,
                                        GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(scroll, GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                                .addContainerGap()));
    }

}
