/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.CI;

import BackEnd.ADP.Estado;
import BackEnd.ADP.Transicion;
import BackEnd.GLC.Regla2;
import java.util.ArrayList;
import static BackEnd.FUNCIONES.DELIM_ALF.isNT;

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
public class Con_GRtoADP2 {
    
    //variable para almacenar todas las producciones que componen a la gramatica
    java.util.ArrayList <Regla2> producciones;
    
    //variable para definir el tipo de gramatica
    byte TYPE_GR = -1;
    final byte TYPE_GR_0 = 0;
    final byte TYPE_GR_1 = 1;
    final byte TYPE_GR_2 = 2;
    
    /*variables para crear el automata*/
    public java.util.ArrayList <Estado> estados = new ArrayList<>();
    
    private int q = 0;
    
    /*CONSTRUCTOR
    Una gramatica es el conjunto de reglas gramaticales, por lo tanto el constructor de esta clase dedicada
    a la conversion de una GLC a un ADP necesita el conjunto de reglas gramaticales
    Estas almacenadas en un objeto ArrayList
    */
    public Con_GRtoADP2(java.util.ArrayList <Regla2> producciones) {
        estados.add(new Estado(0));
        this.producciones = producciones; 
        removeProduccion();
        conversion('S'); 
        for(Estado e: estados){
                    if(e.StateVoid()){
                        e.addTransicion(new Transicion(q, '?', 'Z', '?'));
                    }
                }
    }
    /*
    public void conversion(){
        
        //con fines de prueba:
        TYPE_GR = TYPE_GR_2;
        
        switch (TYPE_GR){
            case TYPE_GR_0:
                cvn0();
                break;
                
            case TYPE_GR_1:
                break;
            
            case TYPE_GR_2:                
                cv2('S');
                estados.add(new Estado(++q,true));
                for(Estado e: estados){
                    if(e.StateVoid()){
                        e.addTransicion(new Transicion(q, "?", "Z", "?"));
                    }
                }
                
                break;
            default: System.out.println("GLC no categorizada");
        }
    }*/
    
    /*TIPO CERO
    Esta gramatica cumple la caracteristica de que se puede resolver como un AFD
    dejando los pop y push en epsilon
    Caracteristica:
    -las cabeceras que contiene una produccion con un terminal se transforman en
    un estado que lleva al estado final
    */
    public void cvn0(){        
        //gramatica 0
    }
    public void cvn1(){
        
    } 
    /*@Pedro    
    */
    public void conversion(char c){
        int QO = q;
        Integer QE = null;
        Regla2[] p = getNPTerminals(c);
               
        for (int i = 0; i<p.length; i++){
            char[] r = p[i].getInstruccion();
            for(int j = 0; j < r.length; j++){
                Transicion t;
                String pop, push;
                if(isNT(r[j]) && r[j] != c)conversion(r[j]);
                else if(isNT(r[j]) && r[j] == c){ 
                    estados.get(q).addTransicion(new Transicion(QO, '?', '?', '?'));
                    addTerminals(c);
                    if (j == r.length-1) estados.get(q).setStateVoid(true);
                    QE = q;
                }else if(!isNT(r[j])){
                    //----->
                    if (j>0) pop = (isNT(r[j-1])) ? "1" : "?";                                              
                    else pop = "?";
                    //<-----
                    //----->
                    if (j<r.length-1){
                        if (isNT(r[j+1])) push = (j+1 == r.length-1) ? "?" : "1";                            
                        else push = "?";
                    }else push = "?";                    
                    //<-----
                    estados.get(j == 0 ? QO : q).addTransicion(new Transicion(q+1, (r[j]), pop.charAt(0), push.charAt(0)));
                    estados.add(new Estado(++q));
                    if(j == r.length-1){
                        estados.get(q).addTransicion(new Transicion(QE, '?', '?', '?'));
                        estados.get(q).setStateVoid(true);
                        QE = null;
                        }
                }
            }            
        }
        addTerminals(c,QO,q);
        estados.get(q).setStateVoid(true);
    }
    public void addTerminals(char c, int QO, int qc){
        int QU  = qc + 1;        
        Regla2[] terminales = getPTerminals(c);
        if(terminales.length > 0){
            Transicion t =  null;
            for (int i = 0; i < terminales.length; i++){
                QU += (terminales[i].getInstruccion().length - 1);
            }
            for (int i = 0; i< terminales.length; i++){

                char[] terminal= terminales[i].getInstruccion();
                for (int j = 0; j< terminal.length; j++){
                    if(j+1 == terminal.length){
                        t = new Transicion(QU, terminal[j], '?', '?');
                        estados.get(j == 0 ? QO : qc).addTransicion(t);  
                    }else{
                        t = new Transicion(qc+1, terminal[j], '?', '?');
                        estados.get(j == 0 ? QO : qc).addTransicion(t);                    
                        estados.add(new Estado(++qc));
                    }   
                }            
            }   
            estados.add(new Estado(QU));
            q++;
        }
    }
    public void addTerminals(char c){
        int QO = q;
        int QU  = q + 1;        
        Regla2[] terminales = getPTerminals(c);
        if(terminales.length > 0){
            Transicion t =  null;
            for (int i = 0; i < terminales.length; i++){
                QU += (terminales[i].getInstruccion().length - 1);
            }
            for (int i = 0; i< terminales.length; i++){

                char[] terminal= terminales[i].getInstruccion();
                for (int j = 0; j< terminal.length; j++){
                    if(j+1 == terminal.length){
                        t = new Transicion(QU, terminal[j], '?', '?');
                        estados.get(j == 0 ? QO : q).addTransicion(t);  
                    }else{
                        t = new Transicion(q+1, terminal[j], '?', '?');
                        estados.get(j == 0 ? QO : q).addTransicion(t);                    
                        estados.add(new Estado(++q));
                    }   
                }            
            }   
            estados.add(new Estado(QU));
            q++;
        }
    }
    
    public Regla2[] getPTerminals(char c){
        ArrayList<Regla2> term = new ArrayList();
        for(Regla2 e : this.producciones){
            if(e.getNombre() == c){
                boolean terminal = true;
                char[] cr = e.getInstruccion();
                for(int i = 0; i<cr.length; i++){
                    if(isNT(cr[i])) {
                        terminal = false;
                        break;
                    }
                }
                if(terminal) term.add(e);
            }
        }        
        int i = 0;
        Regla2[] terminals = new Regla2[term.size()];
        for(Regla2 e : term) terminals[i++] = new Regla2(e.getNombre(), e.getInstruccion());
      
        return terminals;
    }
    
    public Regla2[] getNPTerminals(char c){
        ArrayList<Regla2> term = new ArrayList<>();
        for (Regla2 e : this.producciones){
            if(e.getNombre() == c){
                char[] cr = e.getInstruccion();
                for(int i = 0; i < cr.length; i++)
                    if(isNT(cr[i])){
                        term.add(e);
                        break;
                    }                
            }
        }
        int i = 0;
        Regla2[] terminals = new Regla2[term.size()];
        for(Regla2 e : term) terminals[i++] = new Regla2(e.getNombre(), e.getInstruccion());       
        
        return terminals;
    }
    
    public void removeProduccion(){
        ArrayList<Regla2> nuevalista = new ArrayList<>();
        for(Regla2 r : producciones){
            if(r.getInstruccion()[0] == '?');
            else nuevalista.add(r);
        }
        producciones = nuevalista;
    }
        
    /*CLASIFICACION DEL TIPO DE GRAMATICA
    No se puede generalizar un algoritmo de conversion para todas las GLC, sin embargo
    estas se pueden categorizar, ya que hay muchas que comparten caracterizticas en comun, 
    dando como resultado agrupaciones
    
    se puede aumentar el nivel de clasificacion pero no bajar
    */
    public void typeGrm(){
        /*String[] nomANDinst;
        for(GLC.Regla r : this.producciones){
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
        }*/
    }
}
