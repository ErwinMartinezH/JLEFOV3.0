package vista;

import java.awt.Color;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.table.DefaultTableModel;

import modelo.M_estado;
import modelo.M_transicion;

public class V_panelADPaGLC extends JTable {

    private List<M_estado> estados;
    private List<M_transicion> transiciones;

    public V_panelADPaGLC(List<M_estado> estados, List<M_transicion> transiciones, int iconversionProvicional) {
        this.estados = estados;
        this.transiciones = transiciones;
        // this.setBackground(Color.GREEN);

        String[] columnas = { "Cabeza", "Regla" };
        if (iconversionProvicional == 0) {

            Object[][] datos = {
                    { "S", "aSa" },
                    { "S", "bSb" },
                    { "S", "?" }
            };
            DefaultTableModel model = new DefaultTableModel(datos, columnas);

            this.setModel(model);
        }

        if (iconversionProvicional == 1) {

            Object[][] datos = {
                    { "S", "0S0" },
                    { "S", "0A0" },
                    { "A", "1A" },
                    { "A", "1" }
            };
            DefaultTableModel model = new DefaultTableModel(datos, columnas);

            this.setModel(model);
        }

        if (iconversionProvicional == 2) {

            Object[][] datos = {
                    { "S", "aAc" },
                    { "A", "aAB" },
                    { "A", "bc" },
                    { "B", "cB" },
                    { "B", "c" },
            };
            DefaultTableModel model = new DefaultTableModel(datos, columnas);

            this.setModel(model);
        }

    }

}
