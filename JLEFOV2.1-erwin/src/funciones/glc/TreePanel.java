/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package funciones.glc;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import javax.swing.GroupLayout;
import javax.swing.JPanel;

/**
 *
 * @author pedro
 */
public class TreePanel extends JPanel {

    /**
     * Creates new form TreePanel
     */
    private TreeNodeDerivation raiz;

    public TreePanel(TreeNodeDerivation raiz) {
        this.raiz = raiz;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        setBackground(Color.WHITE);
        if (raiz != null) {
            dibujarArbol(g, raiz, getWidth() / 2, 30);
        }
    }

 private void dibujarArbol(Graphics g, TreeNodeDerivation nodo, int x, int y) {
    // Subdividir el nodo si es necesario
    subdividirNodo(nodo);

    // Ahora procede a dibujar el árbol normalmente
    dibujarArbolRecursivo(g, nodo, x, y);
}

private void subdividirNodo(TreeNodeDerivation nodo) {
    String valor = nodo.getValue();

    // Realiza la subdivisión solo si el valor tiene longitud mayor a 1
    if (valor.length() > 1) {
        for (int i = 0; i < valor.length(); i++) {
            String subValor = valor.substring(i, i + 1);
            TreeNodeDerivation subnodo = new TreeNodeDerivation(subValor);
            nodo.addChild(subnodo);

            // Realiza la llamada recursiva para seguir subdividiendo si es necesario
            subdividirNodo(subnodo);
        }
    }
}

private void dibujarArbolRecursivo(Graphics g, TreeNodeDerivation nodo, int x, int y) {
    String valor = nodo.getValue();
    int anchoTexto = g.getFontMetrics().stringWidth(valor);
    int alturaTexto = g.getFontMetrics().getHeight();

    // Calcula el tamaño del círculo basado en el texto
    int radio = Math.max(anchoTexto, alturaTexto) / 2;

    // Ajusta el tamaño del círculo
    int diametro = 2 * radio;
    int ajuste = 5; // Valor de ajuste para centrar el texto en el círculo

    // Ajusta el tamaño del círculo
    g.setColor(Color.BLUE);
    g.fillOval(x - radio, y - radio, diametro, diametro);

    g.setColor(Color.BLACK);
    g.drawOval(x - radio, y - radio, diametro, diametro);

    // Establece el color del texto a blanco
    g.setColor(Color.WHITE);
    g.drawString(valor, x - anchoTexto / 2, y + alturaTexto / 2 - ajuste);

    ArrayList<TreeNodeDerivation> hijos = nodo.getChildren();
    int cantidadHijos = hijos.size();

    // Ajusta el espacio vertical entre nodos en el mismo nivel
    int espacioVertical = 30; // Puedes ajustar esto según tus preferencias

    for (int i = 0; i < cantidadHijos; i++) {
        int hijoX = x; // Mantiene la misma posición horizontal para los hijos
        int hijoY = y + espacioVertical; // Ajusta la posición vertical

        // Ajusta la posición vertical para cada hijo
        hijoY += i * (diametro + espacioVertical);

        // Dibuja el hijo con el mismo tamaño
        dibujarArbolRecursivo(g, hijos.get(i), hijoX, hijoY);

        // Dibuja la línea desde el padre hasta el hijo
        g.drawLine(x, y + radio, hijoX, hijoY - radio);
        espacioVertical=30;
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

        GroupLayout layout = new GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 400, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(GroupLayout.Alignment.LEADING)
            .addGap(0, 300, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
}