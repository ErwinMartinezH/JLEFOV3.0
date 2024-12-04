/*
* Esta clase se encarga de realizar el rastreo en una ventana nueva que pinta en el lienzo
* de acuerdo a lo que el usuario ingresa por cadena
*/
package HerramientaADP;

import control.C_automata;
import vista.V_lienzo;

import javax.swing.*;
import java.util.Stack;

import static funciones.NmComponentes.ADP;

public class Rastrea implements Runnable {
    private Automata automata;
    private V_lienzo lienzo;
    private String cadena;
    private int pasoActual;
    private Stack<String> pila;
    private Stack<Integer> estados;
    private boolean cadenaAceptada;


    public Rastrea(Automata automata, String cadena) {
        this.automata = automata;
        this.cadena = cadena;
        this.pila = new Stack<>();
        this.estados = new Stack<>();
        this.pasoActual = 0;
        this.pila.push("Z"); // Suponiendo que Z es el símbolo inicial de la pila
    }

    /**
     * Método para iniciar el rastreo.
     */
    public void iniciarRastreo() {
        // Reseteamos a los valores iniciales
        pila.clear();
        pila.push("Z");
        estados.clear();
        estados.push(0); // Estado inicial
        pasoActual = 0;
        cadenaAceptada = false;

        // Realiza el rastreo inicial
        rastrear();
    }

    /**
     * Método para rastrear la cadena en el autómata de pila.
     */
    private void rastrear() {
        // Aquí debería ir la lógica para rastrear la cadena en el autómata de pila
        // Se actualizaría cadenaAceptada según el resultado del rastreo
    }

    /**
     * Método para avanzar al siguiente paso en el rastreo.
     */
    public void siguientePaso() {
        // Lógica para ir al siguiente paso
        if (pasoActual < cadena.length()) {
            // Aquí se implementaría el cambio de estado y la manipulación de la pila
            pasoActual++;
        }
    }

    /**
     * Método para retroceder al paso anterior en el rastreo.
     */
    public void pasoAnterior() {
        // Lógica para retroceder al paso anterior
        if (pasoActual > 0) {
            // Aquí se implementaría el cambio de estado y la manipulación de la pila
            pasoActual--;
        }
    }

    @Override
    public void run() {
        //prevenir que no se haya sellecionado nada
        if (cadena.equals("")) {
            JOptionPane.showMessageDialog(null, "Elige una cadena de la tabla",
                    "Rastreo Paso a Paso", JOptionPane.INFORMATION_MESSAGE);
            return ;
    }
        new Thread(() ->{
           SwingUtilities.invokeLater(() ->{
               if (!lienzo.getTipoPanel().equals(ADP)) {
                   iniciarRastreo();
               }
           });
        });
    }



    // Métodos adicionales para obtener información del estado actual de la pila, estados, etc.
    // ...

    // Getters y setters según sea necesario
    // ...
}

/**
 * Clase de ejemplo para demostrar el uso de la clase Rastrea.
 */
/*public class EjemploRastreo {
    public static void main(String[] args) {
        // Crear el automata de pila con sus estados y transiciones
        Automata automata = new Automata( /*estados, transiciones*/ /*);

        // Crear la instancia de Rastrea con el autómata y la cadena a analizar
//        Rastrea rastreo = new Rastrea(automata, "aabb");

        // Iniciar el rastreo
  //      rastreo.iniciarRastreo();

        // Ejemplo de navegación en el rastreo
    //    rastreo.siguientePaso();
        // Supongamos que el usuario quiere retroceder
      //  rastreo.pasoAnterior();
        // Continuar con el rastreo
        // ...
//    }
}*/