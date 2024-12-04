/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.CI;

import BackEnd.ADP.Estado;
import BackEnd.GLC.Regla;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 *
 * @author jesus
 */

/**NOTAS
 * debe de mandarse a llamar esta clase desde otro para que se realice una convercion en paralelo
 * java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new clase().setVisible(true);
            }
        });
 * @author jesus
 */
public class Con_GRtoADP {
    
    //variable para almacenar todas las producciones que componen a la gramatica
    java.util.ArrayList <Regla> producciones;
    //variable para definir el tipo de gramatica
    byte TYPE_GR = -1;
    final byte TYPE_GR_0 = 0;
    final byte TYPE_GR_1 = 1;
    
    /*variables para crear el automata*/
    java.util.ArrayList <Estado> estados = new ArrayList<>();
    Estado est;
    //para controlar el nombre del estado
    private int icontrol;
    //estados de aceptacion
    private ArrayList <String> estAcep;

    
    /*CONSTRUCTOR
    Una gramatica es el conjunto de reglas gramaticales, por lo tanto el constructor de esta clase dedicada
    a la conversion de una GLC a un ADP necesita el conjunto de reglas gramaticales
    Estas almacenadas en un objeto ArrayList
    */
    public Con_GRtoADP(java.util.ArrayList <Regla> producciones) {
        this.producciones = producciones; 
        icontrol = 0;
        estAcep = new ArrayList();
               
        typeGrm();
        //obtener las cabeceras que seran estados finales o de aceptacion 
       // ifFinal();
        //aplicacion de la conversion
        conversion();
    }
       
    public void addEstado(){
        
    }
    
    public void conversion(){
        
        switch (TYPE_GR){
            case TYPE_GR_0:
                cvn0();
                break;
                
            case TYPE_GR_1:
                break;
            default: System.out.println("GLC no categorizada");
        }
    }
    
    /*TIPO CERO
    Esta gramatica cumple la caracteristica de que se puede resolver como un AFD
    dejando los pop y push en epsilon
    Caracteristica:
    -las cabeceras que contiene una produccion con un terminal se transforman en
    un estado que lleva al estado final
    */
    public void cvn0(){        
        String estado_final = "qf";
        String estado = "q";
        int count_est = 0;
        
        String T = "";//sera la transicion
        String Not = "", Q="";//nodo destino
        String q = "";//nodo origen
        
        ArrayList<Head_State> mapeo_Cabeceras_Estados = new ArrayList();
                
        String[] nomANDinst;
        
        for (Regla r_actual: producciones){
            nomANDinst = r_actual.regla();
            
            if(!existencia_mapa(mapeo_Cabeceras_Estados, nomANDinst[0])){
                q = estado+count_est;
                mapeo_Cabeceras_Estados.add(new Head_State(nomANDinst[0], q));
                count_est++;
            }
            T = getTtoT0(nomANDinst[1]);
            Not = getNTtoT0(nomANDinst[1]);
            
            if(existencia_mapa(mapeo_Cabeceras_Estados, Not)){
                Q = getState(mapeo_Cabeceras_Estados, Not);
            }else {
                if(Not.equals("")) Q = estado_final + count_est;
                else Q = estado + count_est;
                mapeo_Cabeceras_Estados.add(new Head_State(Not, Q));
                count_est++;            }       
                System.out.println(q+" - "+T+" - "+Q);
        }
    }
    
    private boolean existencia_mapa(ArrayList<Head_State> mapeo_Cabeceras_Estados, String s){
        boolean ex = false;
        for (Head_State e: mapeo_Cabeceras_Estados){
                if(s.equals(e.getHead())) {
                    return true;
                }
            } 
        return ex;
    }
    
    private String getState(ArrayList<Head_State> mapeo_Cabeceras_Estados, String s){
        String ss = null;
        for (Head_State e: mapeo_Cabeceras_Estados){
                if(s.equals(e.getHead())) ss=e.state;
                break;
            }
        return ss;
    }
    
    private String getTtoT0(String regla){
        String s = "", cache = "";
        for(int i = 0; i < regla.length(); i++){            
            cache = regla.substring(i, i+1);
            
            if(cache.equals(cache.toUpperCase()))break;            
            else s += cache;
        }
        return s;
    }
    
    private String getNTtoT0(String regla){
        String s = "", cache = "";
        for(int i = 0; i < regla.length(); i++){            
            cache = regla.substring(i, i+1);
            
            if(cache.equals(cache.toUpperCase()))
                s += cache;
        }
        return s;
    }
    
    /*METODO QUE ANALIZA CUALES SERAN LOS ESTADOS FINALES
    
    */
    public void ifFinal(){
        boolean iffinal = true;
        String[] nomANDinst;//nombre y instruccion
        for (Regla r_actual: producciones){
            iffinal = true;
            nomANDinst = r_actual.regla();
            
            //analizando la instrucion en busca de terminales
            for(int i = 0; i < nomANDinst[1].length(); i++){
                String cache = nomANDinst[1].substring(i, i+1);                
                if(cache.equals(cache.toUpperCase())){//evalua si hay un no termial
                    iffinal = false;
                    break;
                }
            }
            if (iffinal){
                estAcep.add(nomANDinst[0]);
            }
        }
    }
    
    /*CLASIFICACION DEL TIPO DE GRAMATICA
    No se puede generalizar un algoritmo de conversion para todas las GLC, sin embargo
    estas se pueden categorizar, ya que hay muchas que comparten caracterizticas en comun, 
    dando como resultado agrupaciones
    
    se puede aumentar el nivel de clasificacion pero no bajar
    */
    public void typeGrm(){
        String[] nomANDinst;
        for(Regla r : this.producciones){
            nomANDinst = r.regla();
            for(int i = 0; i < nomANDinst[1].length(); i++){
                String cache = nomANDinst[1].substring(i, i+1);
                if(cache.equals(cache.toUpperCase())){
                    
                    //tipo 0, quiere decir que se puede convertir a un simple automata
                    if(i == (nomANDinst[1].length()-1)){                        
                        TYPE_GR = (TYPE_GR < TYPE_GR_0) ? TYPE_GR_0: TYPE_GR;
                        break;
                    }else{
                        TYPE_GR = (TYPE_GR < TYPE_GR_1) ? TYPE_GR_1: TYPE_GR;
                    }
                }
            }    
        }
    }
    
    private class Head_State{
        private String head;
        private String state;

        public Head_State(String head, String state) {
            this.head = head;
            this.state = state;
        }
        
        public String[] getHead_State(){
            return new String[] {head, state};
        }

        public String getHead() {
            return head;
        }

        public String getState() {
            return state;
        }
        
        
    }
}
