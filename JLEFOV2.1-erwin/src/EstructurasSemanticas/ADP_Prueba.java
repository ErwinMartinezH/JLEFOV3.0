/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasSemanticas;

import HerramientaADP.Automata;

/**
 *
 * @author jesus
 */
public class ADP_Prueba {
    public static void main (String args[]){

        Automata a = new Automata();
        
        a.addTransicion(0, 'a', '?', 'a', 0);
                a.addTransicion(0, 'b', '?', 'b', 0);        
                a.addTransicion(0, '?', '?', '?', 1);     
                a.addTransicion(1, 'a', 'a', '?', 1); 
                a.addTransicion(1, 'b', 'b', '?', 1);
                a.addTransicion(1, '?', 'Z', '?', 2);        
                a.setAceptacion(2, true);
        
        a.start(new char[]{'a','a','b','?','b','a','a'}); 
        
        /*
        a.addTransicion(0, 'a', '?', '1', 1);
        a.addTransicion(1, 'a', '?', '1', 2);        
        a.addTransicion(2, 'a', '?', '1', 2);     
        a.addTransicion(2, 'c', '1', '?', 3); 
        a.addTransicion(3, 'c', '1', '?', 3);
        a.addTransicion(3, 'c', 'Z', '?', 4); 
        a.addTransicion(4, '?', '?', '?', 4);
        */
    }
    
}
