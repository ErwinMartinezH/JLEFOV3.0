package GraficoMTBD.vistaturing.recursos2D;

import javax.swing.table.DefaultTableModel;


/**
 *
 * @author javier
 */
public class RenderizadorTabla extends DefaultTableModel {

    public RenderizadorTabla() {
        super(new String[]{"Estado Actual", "Símbolo Actual", "Estado Siguiente", "Símbolo Siguiente", "Dirección"}, 0);
    }

    @Override
    public boolean isCellEditable(int row, int column) {
        return false; // Hacer las celdas no editables
    }

    @Override
    public void addRow(Object[] rowData) {
        super.addRow(rowData);
    }

}
