    package ConversionesSemanticas;

    import EstructurasSemanticas.Estado;
    import EstructurasSemanticas.Transicion;
    import control.C_automata;
    import modelo.M_estado;
    import modelo.M_transicion;
    import vista.V_interfaz;
    import vista.V_panel;
    import vista.V_panelConversionADPtoGLC;

    import javax.swing.*;
    import javax.swing.table.DefaultTableModel;
    import java.awt.event.InputEvent;
    import java.awt.event.KeyEvent;
    import java.util.ArrayList;
    import java.util.Iterator;
    import java.util.List;
    import java.util.Stack;

    import static java.awt.event.InputEvent.CTRL_MASK;
    import static java.awt.event.InputEvent.SHIFT_MASK;
    import static java.awt.event.KeyEvent.VK_G;


    public class Con_ADPtoGR {
        private V_panelConversionADPtoGLC vistaConversion;
        V_interfaz interfaz;

        public void sp(ArrayList<Estado> estados) {
            ArrayList<Estado> estado = estados;
            for (Estado e : estados) {
                System.out.println("aceptacion: " + e.getAceptacion() + " nombre " + e.getNombre());
            }
        }

        public void conversion(ArrayList<Estado> estados) {
            // Stack<Triple<Integer, String, Character>> pila = new Stack<>();
            List<Lista> pila = new ArrayList<>();
            char nombre;
            char[] instruccion = {};
            String inst = "";
            //      ArrayList<Estado> estado = estados;
            for (Estado e : estados) { // Revisa todos los estados
                //       System.out.println( "aceptacion: "+ e.getAceptacion() + " nombre " + e.getNombre());
                if (e.getTransiciones().isEmpty())
                    System.out.println(NumL(e.getNombre()) + "-> ?");
                //agregarFila(Character.toString(NumL(e.getNombre()).charAt(0)), "?");
                for (Transicion t : e.getTransiciones()) { // Revisa todas las transiciones de cada estado
                    //         System.out.println("Estado de origen " +  e.getNombr6e() +   "  " + nal(e.getNombre())+ " Estado de destino " + t.getEstde() + " "+ nal(t.getEstde()) + " entrada " + t.getInput() );
                    if (estados.get(t.getEstde()).getAceptacion() == true) {
                        if (estados.get(t.getEstde()).getNombre() != e.getNombre() && t.getInput() != '?') {
                            nombre = (NumL(e.getNombre()).charAt(0));
                            inst = t.getInput() + NumL(t.getEstde());
                            instruccion = inst.toCharArray();
                            System.out.println(nombre + "->" + inst);
                            agregarFila(Character.toString(nombre), inst);
                        }
                        nombre = (NumL(e.getNombre()).charAt(0));
                        inst = Character.toString(t.getInput());
                        instruccion = inst.toCharArray();
                        System.out.println(nombre + "->" + inst);
                        agregarFila(Character.toString(nombre), inst);
                    } else {
                        nombre = (NumL(e.getNombre()).charAt(0));
                        inst = t.getInput() + NumL(t.getEstde());
                        if (t.getPush() != '?' && t.getEstde() == e.getNombre()) {
                            pila.add(new Lista(t.getEstde(), inst, t.getPush()));
                        } else if (t.getPop() != '?' && t.getEstde() == e.getNombre() && t.getPop() != 'Z') {

                            Iterator<Lista> iterator = pila.iterator();
                            while (iterator.hasNext()) {
                                Lista elemento = iterator.next();
                                if (t.getPop() == elemento.pila) {
                                    nombre = NumL(elemento.estado).charAt(0);
                                    inst = elemento.pro + t.getInput();
                                    System.out.println(nombre + "->" + inst);
                                    iterator.remove();
                                    agregarFila(Character.toString(nombre), inst);
                                }
                            }
                               /* if (t.getPop() != pila.peek().getThird()) {
                                Triple<Integer, String, Character> aux = pila.pop();
                                Triple<Integer, String, Character> elementoEnCima = pila.pop();
                                nombre = NumL(elementoEnCima.getFirst()).charAt(0);
                                inst = elementoEnCima.getSecond() + t.getInput();
                                System.out.println(nombre + "->" + inst);
                                pila.push(aux);}
                            } else {
                                Triple<Integer, String, Character> elementoEnCima = pila.pop();
                                nombre = NumL(elementoEnCima.getFirst()).charAt(0);
                                inst = elementoEnCima.getSecond() + t.getInput();
                                System.out.println(nombre + "->" + inst);
                            }*/
                        } else {
                            instruccion = inst.toCharArray();
                            System.out.println(nombre + "->" + inst);
                            agregarFila(Character.toString(nombre), inst);
                        }
                    }
                }

            }
        }

        public String NumL(int num) {
            if (num == 0) {
                return "S";
            } else {
                return Character.toString((char) (num + 64));
            }
        }

        public Con_ADPtoGR() {
            vistaConversion = new V_panelConversionADPtoGLC();
            vistaConversion.setVisible(true);
            vistaConversion.setDefaultCloseOperation(vistaConversion.DISPOSE_ON_CLOSE);
        }

        private void agregarFila(String nombre, String inst) {
            Object[] fila = {nombre, inst};
            vistaConversion.agregarFila(fila);
        }
    }

