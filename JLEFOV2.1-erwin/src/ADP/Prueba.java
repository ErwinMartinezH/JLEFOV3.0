/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ADP;

import BackEnd.GLC.Regla2;
import BackEnd.GLC.Gramatica2;
import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class Prueba {
    public static void main (String args[]){ 
        
        Automata a = new Automata();
        Automata b = new Automata();
        Gramatica2 gr = new Gramatica2();
        a.addTransicion(0, 'a', '?', '1', 1);
        a.addTransicion(1, 'a', '?', '1', 1);        
        a.addTransicion(1, 'b', '?', '?', 2);     
        a.addTransicion(2, 'c', '1', '?', 3); 
        a.addTransicion(3, 'c', '1', '?', 3);
        a.addTransicion(3, 'c', 'Z', '?', 4); 
        a.addTransicion(4, '?', '?', '?', 4);       
        a.setAceptacion(4, true);
        
        a.start(new char[]{'a','a','b','c','c','c'}); 
        a.start(new char[]{'a','b','c','c'});
        a.start(new char[]{'a','a','b','c','c'});  
        
        /*
        a.addTransicion(0, 'a', '?', '1', 1);
        a.addTransicion(1, 'a', '?', '1', 2);        
        a.addTransicion(2, 'a', '?', '1', 2);     
        a.addTransicion(2, 'c', '1', '?', 3); 
        a.addTransicion(3, 'c', '1', '?', 3);
        a.addTransicion(3, 'c', 'Z', '?', 4); 
        a.addTransicion(4, '?', '?', '?', 4);
        */
        
        
        System.out.println("Ejemplo de como podemos extraer el contenido");
        int i = 3;//utilizaremos el ejemplo de conversion 0
        System.out.println("----------------------");
        conversion_provicional p = new conversion_provicional();
        p.conversion(i);//generamos el numero de ejemplo
        for (Estado e : p.getAutomata().getEstados()){// se imprime el nuemero de ejemplo
            System.out.println(e);
        }
        b= p.getAutomata();
        
        System.out.println("Comprobacion del automata");
        b.start(new char[]{'a','a','a','b','b','b'});
        System.out.println("-----------------------");
        for (Regla2 e : p.getGramatica()){// se imprime el nuemero de ejemplo
            System.out.println(e);
        }
        gr.addProduccion(p.getGramatica());
        System.out.println("comprobacion de la gramatica");
        gr.recorrido1("aaabbb");
         /*
        Estados_Validos[] t = a.getRecorrido();
        for(Estados_Validos e: t)  System.out.println(e);*/
    }
    
}
