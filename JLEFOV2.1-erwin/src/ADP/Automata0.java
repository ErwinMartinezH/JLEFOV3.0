/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ADP;

import java.util.ArrayList;
import java.util.Stack;

/**
 *
 * @author jesus
 */
public class Automata0 {
    
    ArrayList<Estado> estado = new ArrayList();
    Stack<String> pila = new Stack();
   
    public Automata0(ArrayList al){
        this.estado = al;
        pila.push("Z");
    }
    
    public void movimiento(String args){
        
        args+="?";//el simbolo de interrogacion funge como epsilon
        
        int EstadoActual = 0;//el nombre del estado por defaul donde inicia el automata
        Estado est = estado.get(EstadoActual); //estado actual sobre el que se trabaja
        String dato = "";//variable donde se guardara el dato actual        
        int i;
       for (i = 0; i < args.length(); i++){
           dato =  args.substring(i,i+1);//lectura lineal de la cadena           
           
           if(/*est.regla(dato)*/true){//comprueba si existe una transicion con esa entrada
                             
               //modificacion de la pila
               //pila_pop(est.getReglaAplicada().pop);
               //pila_push(est.getReglaAplicada().push);
               
               EstadoActual = est.getReglaAplicada().estde;//contador--se pasa el estado al que indica la regla de la transicion               
               
           }else{//opcion para la inexisistencia de la entrada
               System.out.println("No aceptado-Falta de transicion");
               System.exit(0);
           }           
           est = estado.get(EstadoActual);//avance de estado
       }
       if (est.getAceptacion() && i == (args.length())){
           System.out.println("Cadena valida");
       }else {
           System.out.println(est.getNombre());
           System.out.println("Cadena invalida");
       }      
        System.out.println("Contenido de la pila");
        for(String e: pila){
            System.out.println(e);
        }
    }
    
    public void pila_pop(String s){
        
        if (!s.equals("?")){
            if(s.equals(pila.peek()))  pila.pop();

            else{
                System.out.println("Cadena invalida\nError// Dato: "+s+" Pila: "+pila.peek());
                System.exit(0);
            }
        }
    }
    public void pila_push(String s){
        if(!s.equals("?")){
            pila.push(s);
        }
    }
    public ArrayList<Estado> getAutomata_ArrayList(){
        return this.estado;
    }
    public Object[] getAutomata_Array(){
        return this.estado.toArray();
    }
}
