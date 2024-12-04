/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasSemanticas;

import java.util.ArrayList;
import java.util.Stack;
/**
 *
 * @author pedro
 */
public class Automata {
    private ArrayList <Estado> estados = new ArrayList();
    private Stack<Character> pila = new Stack();
    private ArrayList<Estados_Validos> lista = new ArrayList<>();
    
    private char[] cadena;    
    private int q;
    private int index;
    
    public void start(char[] c){  
        this.q = 0;      
        this.index = 0; 
        pila = new Stack();       
        pila.push('Z');
        /*cadena = new char[(2*c.length)+1];
        for (int i = 0; i < cadena.length; i++){
            cadena[i] = (i%2 == 0) ? '?' : c[(i-1)/2];
        }
        
        //*/
        cadena = new char [c.length+1];
        for(int i = 0; i < cadena.length; i++){
            cadena[i] = (i == cadena.length - 1) ? '?' : c[i];
        }
                
        if(evaluacion(q)){
            System.out.println("Cadena valida");
        }else {
            System.out.println("Cadena invalida");
        }
    }
    
    private boolean evaluacion (int q){        
        int cache = q;
        boolean status = false;
        Estado est = new Estado(q, getEstado(q).getAceptacion());
        est.setTransiciones(getEstado(q).getTransiciones());
        
        Transicion[] tr = new Transicion[est.getTransiciones().size()]; 
        int i = 0;
        for(Transicion t : est.getTransiciones()){
            tr[i++] = t;
        }
        if(index < cadena.length){
            for(i = 0; i < tr.length; i++){
                if(tr[i].input == cadena[index]){ 
                    if (pop(tr[i].pop)){
                        push(tr[i].push);
                        int j = q;
                        q = tr[i].estde;
                        index++;
                        if(evaluacion(q)) {
                            status = true;
                            lista.add(new Estados_Validos(cache,tr[i].input,tr[i].pop,tr[i].push,tr[i].estde));
                            //lista.add(new char[]{cache,tr[i].input,tr[i].pop,tr[i].push,(char)tr[i].estde});
                            //lista += cache + " " + tr[i].input + " " + tr[i].pop + " " + tr[i].push + " " + tr[i].estde + "\n";
                            break;
                        }
                        else {
                            index--;
                            pop(tr[i].push);
                            push(tr[i].pop);
                        }
                    }  
                }
            }
        } else if(est.getAceptacion()){
            //lista += q + "? Z ?\n";
            status = pila.empty();
        }            
        
        return status;
    }

    public ArrayList <Estado> estados(){
        return estados;
    }

    public Estados_Validos[] getRecorrido(){
        Estados_Validos[] validos = new Estados_Validos[lista.size()];
        Estados_Validos[] retorno = new Estados_Validos[lista.size()];
        
        int i = 0;
        
        for(Estados_Validos e : lista) validos[i++] = e;
        
        int j=0;
        for(i = validos.length-1; i> -1; i--) retorno[j++] = validos[i];
        
        return retorno;
    }
    
    public void setAceptacion(int nombre, boolean b){
        for(Estado e : estados){
            if(e.getNombre() == nombre){
                e.setAceptacion(b);
            }
        }
    }
    
    public void addTransicion(int orig, char input, char pop, char push, int dest){
        //buscamos estado
        if(existencia(orig)) getEstado(orig).addTransicion(new Transicion(dest, input, pop, push));
        else{
            estados.add(new Estado(orig));
            getEstado(orig).addTransicion(new Transicion(dest, input, pop, push));
        }
        if(!existencia(dest)) estados.add(new Estado(dest));
    }
    
    private boolean existencia(int nombre){
        boolean b = false;
        for (Estado e: estados){
            if(e.getNombre() == nombre){
                b =true;
                break;
            }            
        }
        return b;
    }
    
    private Estado getEstado(int nombre){
        Estado es = null;
        for (Estado e: estados){
            if(e.getNombre() == nombre){
                es =e;
                break;
            }            
        }
        return es;
    }
    
    public Estado[] getEstados(){
        Estado[] e = new Estado[estados.size()];
        int i = 0;
        for(Estado es: estados){
            e[i++] = es;
        }
        return e;
    }
    
    private boolean pop(char pop){
        if(pop != '?'){
            if(pila.peek() == pop){
                pila.pop();
                return true;
            }
            else{
                return false;
                }
        }else return true;
    }
    
    private void push(char push){
        if(push != '?'){
            pila.push(push);
        }
    }
    @Override
    public String toString(){
        String s="";
        for (Estado e: estados){
            s +=e;            
        }
        return s;
    }
    
}
