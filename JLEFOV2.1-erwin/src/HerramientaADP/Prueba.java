/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HerramientaADP;

//import CI.conversion_provicional;
import BackEnd.GLC.Regla2;
import HerramientaADP.Automata;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author jesus
 */
public class Prueba {

    public static void main(String args[]) {

        Automata a = new Automata();

//        a.addTransicion(0, '0', '?', '1', 1);
//        a.addTransicion(1, '0', '?', '1', 1);
//        a.addTransicion(1, '1', '1', '?', 2);
//        a.addTransicion(2, '1', '1', '?', 2);
//        a.addTransicion(2, '1', '?', '1', 3);
//        a.addTransicion(3, '1', '?', '1', 3);
//        a.addTransicion(3, '2', '1', '?', 4);
//        a.addTransicion(4, '2', '1', '?', 4);
//        a.addTransicion(4, '3', '?', '?', 5);
//        a.addTransicion(5, '3', '?', '?', 6);
//        a.addTransicion(6, '3', '?', '?', 6);
//        a.addTransicion(6, '?', 'Z', '?', 7);
//        a.setAceptacion(7, true);
//
//        a.start(new char[]{'0', '1', '1', '2', '3', '3'});
        a.addTransicion(0, '?', 'Z', '?', 0);
        a.addTransicion(0, 'a', '?', '?', 1);
        a.addTransicion(0, 'b', '?', '?', 0);
        a.addTransicion(1, 'b', '?', '?', 0);
        a.addTransicion(1, 'a', '?', '?', 2);
        a.addTransicion(2, 'a', '?', '?', 3);
        a.addTransicion(2, 'b', '?', '?', 1);
        a.setAceptacion(0, true);
        a.setAceptacion(1, true);
        a.setAceptacion(2, true);
        
        ArrayList<Stack<Character>> listSeguimientoPila;

        a.start(new char[]{'a'});

        Estados_Validos[] esVa = a.getRecorrido();
        System.out.println("----------------------Estados para evaluar-------------: " + esVa.length);
        int iContador = 0;
//        System.out.println("iContador: " + iContador + ": " + esVa[0].origen);
        iContador++;
        for (Estados_Validos estados_Valido : esVa) {
            System.out.println("iContador: " + iContador + ": " + estados_Valido);
            iContador++;

        }

//                                System.out.println("estado: " + au.getListSeguimientoEstados());
//                                System.out.println("pila: " + au.getListSeguimientoPila());
        listSeguimientoPila = a.getListSeguimientoPila(esVa);

        System.out.println("---------Respuesta--------------: " + listSeguimientoPila.size());
        iContador = 0;
        for (Stack<Character> stack : listSeguimientoPila) {
            System.out.println("iContador: " + iContador + ": " + stack);
            iContador++;
        }
        /*
        a.addTransicion(0, 'a', '?', '1', 1);
        a.addTransicion(1, 'a', '?', '1', 2);        
        a.addTransicion(2, 'a', '?', '1', 2);     
        a.addTransicion(2, 'c', '1', '?', 3); 
        a.addTransicion(3, 'c', '1', '?', 3);
        a.addTransicion(3, 'c', 'Z', '?', 4); 
        a.addTransicion(4, '?', '?', '?', 4);
         */

 /* System.out.println("Ejemplo de como podemos extraer el contenido");
        int i = 0;//utilizaremos el ejemplo de conversion 0
        System.out.println("----------------------");
        conversion_provicional p = new conversion_provicional();
        p.conversion(i);//generamos el numero de ejemplo
        for (Estado e : p.getAutomata()){// se imprime el nuemero de ejemplo
            System.out.println(e);
        }
        System.out.println("-----------------------");
        for (Regla2 e : p.getGramatica()){// se imprime el nuemero de ejemplo
            System.out.println(e);
        }
         /*
        Estados_Validos[] t = a.getRecorrido();
        for(Estados_Validos e: t)  System.out.println(e);*/
    }

}
