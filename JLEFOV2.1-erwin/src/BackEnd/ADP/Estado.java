/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.ADP;

import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class Estado {
    /*
    private ArrayList<Transicion> Transiciones = new ArrayList();
    private Transicion regla; //regla que aplica
    public int nombre;
    private boolean aceptacion;
    private boolean StateVoid = false;
    
    //Inicializa el estado con su nombre
    public Estado (int nombre, boolean acep){//luego se debe validar que no se repita el nombre
        this.nombre = nombre;
        this.aceptacion = acep;
    }
    
    public Estado (int nombre){
        this.nombre = nombre;
    }
    
    public void setAceptacion(boolean t){
        this.aceptacion = t;
    }
    
    //anadir transicion al estado
    public void addTransicion(Transicion t){
        Transiciones.add(t);
    }
    
    //busca la regla valida
    public boolean regla(String input){//input : valor que se esta leyendo
        boolean b = false;
        for(Transicion e : Transiciones){
            if (e.input.equals(input)){
                b = true;
                regla = e;
                break;
            }
        }
        return b;
    }
        
    //retorna la regla valida a utilizar
    public Transicion getReglaAplicada(){
        return regla;
    }
    
    //retorna el nombre del estado
    public int getNombre(){
        return nombre;
    }
    
    public ArrayList<Transicion> getTransiciones(){
        return this.Transiciones;
    }
    
    public void setTransiciones(ArrayList<Transicion> t){
        this.Transiciones = t;
    }
    
    public boolean getAceptacion(){
        return this.aceptacion;
    }
    
    public void setStateVoid(boolean t){
        StateVoid = t;
    }
    
    public boolean StateVoid(){
        return StateVoid;
    }
    
    @Override
    public String toString(){
        String s ="";
        for(Transicion e: Transiciones){
            s += nombre+"{\n" +e.toString()+"\n}\n";
        }
        return s;
    }
    */
    private ArrayList<Transicion> Transiciones = new ArrayList();
    private Transicion regla; //regla que aplica
    private int nombre;
    private boolean aceptacion;
    private boolean StateVoid = false;
    
    //Inicializa el estado con su nombre
    public Estado (int nombre, boolean acep){//luego se debe validar que no se repita el nombre
        this.nombre = nombre;
        this.aceptacion = acep;
    }
    
    public Estado (int nombre){
        this.nombre = nombre;
    }
    
    public void setAceptacion(boolean t){
        this.aceptacion = t;
    }
    
    //anadir transicion al estado
    public void addTransicion(Transicion t){
        Transiciones.add(t);
    }
    
    //busca la regla valida
    public boolean regla(char input){//input : valor que se esta leyendo
        boolean b = false;
        for(Transicion e : Transiciones){
            if (e.input == input){
                b = true;
                regla = e;
                break;
            }
        }
        return b;
    }
        
    //retorna la regla valida a utilizar
    public Transicion getReglaAplicada(){
        return regla;
    }
    
    //retorna el nombre del estado
    public int getNombre(){
        return nombre;
    }
    
    public ArrayList<Transicion> getTransiciones(){
        return this.Transiciones;
    }
    
    public void setTransiciones(ArrayList<Transicion> t){
        this.Transiciones = t;
    }
    
    public boolean getAceptacion(){
        return this.aceptacion;
    }
    
    public void setStateVoid(boolean t){
        StateVoid = t;
    }
    
    public boolean StateVoid(){
        return StateVoid;
    }
    
    @Override
    public String toString(){
        String s =nombre+" {\n";
        for(Transicion e: Transiciones){
            s += e.toString()+"\n";
        }
        return s+"}\n\n";
    }
}
