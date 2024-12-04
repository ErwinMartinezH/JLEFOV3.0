/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.GLC;

import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class Gramatica {
    /*Una gramatica esta compuesta por reglas; este objeto es una gramatica
    por lo tanto tiene un conjunto de reglas.*/
    private ArrayList<Regla> reglas = new ArrayList();
    private String ep = "?";
    private String cad = "", hojas="", Cadena;
            
    public void setCadena(String s){
        //this.Cadena = s;
    }
    /*una producion es igual a el objeto regla, puede constar de no terminales y
    terminales, por lo tanto para crear una se deben tener estos dos argumentos*/
    public void addProduccion(String noT, String Ins){
        /*
        String nombre = "",instruccion = "",cache = "";
        
        for(int i = 0; i<s.length(); i++){//se separa el nombre de la regla
            
            cache = s.substring(i, i+1);
            
            if(cache.equals("-")) break;//cuado se llege al delimitador se detiene el ciclo
            else nombre+=cache;//hasta que no se llegue al delimitador se continua almacenando el nombre
        }
        
        instruccion = s.substring(nombre.length()+1);*/
        this.reglas.add(new Regla(noT, Ins));
    }
    
    public String getProduccion(){
        String s = "";
        for(Regla sr: this.reglas){
            s += sr.toString() + "\n";
        }
        return s;
    }   
    
    public Regla getProduccion(String nombre){//retorna una regla tomando como argumento su nombre
        Regla r = null;
        for (Regla e: reglas){
            if(e.nombre.equals(nombre)) {
                r =e;
                break;
            }            
        }
        return r;
    }
    
    public Regla getProduccion(String nombre, ArrayList<String> reglaInvalida ){//retorna una regla basandose en su nombre, y descarte de producciones
        Regla r = null;
        for (Regla e: this.reglas){
            if (e.nombre.equals(nombre)){//ciclo para regla
                boolean val = true;
                for(String i: reglaInvalida){//ciclo para reglainvalida
                    if(e.instruccion.equals(i)) val = false; 
                }
                if(val){
                    r = e;
                    break;
                }
            }       
        }
        return r;
    }
    
    public String subCadena(String s){
        String ss = "",cache = "";
        for(int i = 0; i < s.length(); i++){            
            cache = s.substring(i, i+1);
            
            if(cache.equals(cache.toUpperCase()))break;            
            else ss += cache;
        }
        return ss;
    }
    
    public void recorrido1(String cadena){//primer metodo que hace llamada a la regla raiz
        cad = cadena;
        if(recorrido("S")){
            if(this.cad.equals(""))
            System.out.println("Cadena aceptada");
            else System.out.println("Cadena invalida");
        }
        else System.out.println("Cadena invalida");
        
        /*quitar Regla r = Gramatica.this.getProduccion("S");
        this.Cadena = cadena;
        this.hojas = "";
        
        int lona = r.subInstruccionA().length(), lonb = r.subInstruccionB().length();
        
        if(cadena.length() >= (lona + lonb)){//se valida que la longitud de la produccion sea menor o igual que la cadena
            if(r.subInstruccionA().equals(cadena.substring(0, lona))) {//aqui inicia y termina el arbol
            //if(r.subInstruccionA().equals(subCadena(cadena))) {
                hojas += r.subInstruccionA();
                
                
                ///----------analizar
                /*cadena = cadena.substring(lona);
                if (r.noTerminal() != null)
                    recorrido(cadena, getRegla(r.noTerminal()), new ArrayList());
                */
        /*quitar
                if(hojas.equals(this.Cadena))
                    System.out.println("Cadena aceptada: " + hojas);
                    else System.out.println("No se encontro una produccion valida");//anilizar este camino, quizas sea la respuesta para no terminales
            }else {
                ArrayList<String> reglasInvalidas = new ArrayList();
                reglasInvalidas.add(r.instruccion);
                recorrido(cadena, Gramatica.this.getProduccion(r.nombre, reglasInvalidas) , reglasInvalidas);
            }
        }*/
        
    }
    
    private void recorrido(String cadena, Regla r, ArrayList<String> reglaInvalida){        
        if(r != null){
            
            int lona = r.subInstruccionA().length(), lonb = r.subInstruccionB().length();
            if(cadena.length() >= (lona + lonb)){//se valida que la longitud de la produccion sea menor o igual que la cadena
                if(r.subInstruccionA().equals(cadena.substring(0, lona))) {
                //if(r.subInstruccionA().equals(subCadena(cadena))) {//aqui inicia y termina el arbol
                    hojas += r.subInstruccionA();
                    
                    /*
                    cadena = cadena.substring(lona);
                    if (r.noTerminal() != null)
                        recorrido(cadena, getRegla(cadena.substring(0,1)), new ArrayList());*/
                    if(hojas.equals(this.Cadena))
                    System.out.println("Cadena aceptada: " + hojas);
                    else System.out.println("No se encontro una produccion valida");
                }
                else { 
                    //this.hojas=""; parte de lo que hay que analizar
                    reglaInvalida.add(r.instruccion);
                    recorrido(cadena, Gramatica.this.getProduccion(r.nombre, reglaInvalida), reglaInvalida);
                }
            }else System.out.println("No se encontro una produccion valida");
        }else System.out.println("No se encontro una produccion valida");
    }
    
    public String recorridoRecursivo(String regla, String cadena){
        /*for (String e: reglas){
            
        }*/
        
        return "";
    }
    
    public boolean recorrido(String nombre_regla){
        //conseguir parametros
        Regla r = new Regla (getProduccion(nombre_regla).nombre, getProduccion(nombre_regla).instruccion);
        //String T_regla = r.next(), NoT_regla = r.NoT_next;
        //
        
        //Respaldar parametros
        String r_regla = r.instruccion, r_cadena = cad;
        //
        
        //variables para la seleccion de la regla
        ArrayList <String> BlackList = new ArrayList();
        final byte procesando = 0, valido =1, invalido =2;
        byte estado = 0;
        boolean r_punto_inicial = true;
        //
        
        //variables para confirmar proceso
        boolean aceptada = false;
        //
        
        //Aplicacion de regla valida
        r.next();
        /*NOTA: se entiende que las producciones no deben estar vacias al momento del llenado,
        para este algoritmo, cuando se encuentre una produccion vacio, se inferira que la instruccion
        ha llegado a su fuin, por lo cual el vacio se debe validar al recolectar informacion en la insterfaz*/
        while(estado == procesando){
            
            System.out.println(getLinea()+".Terminal de la regla : " + r.T_next);
        
            //System.out.println("--"+cad.substring(0, r.T_next.length()));
            //si la longitud del Ternminal es mayor a la cadena, se invalida la produccion
            if(r.T_next.length() > cad.length()){
                estado = invalido;                
            }
            //
            
            //Se valida la igualdad del terminal con su porcio de cadena equivalente en longitud
            else if(r.T_next.equals(this.cad.substring(0, r.T_next.length()))){                
                System.out.println(getLinea()+".terminal comparado "+ r.T_next);
                System.out.println(getLinea()+".cadena comparada "+this.cad.substring(0, r.T_next.length()));
               //se modifica la cadena
               this.cad = this.cad.substring(r.T_next.length(), this.cad.length());
                System.out.println(getLinea() + ".Cadena nueva " +cad);
                System.out.println(getLinea() + ".regla " + r.instruccion);
                if(r.NoT_next != null){//si quedan no terminales por leer
                     if(recorrido(r.NoT_next)){
                         estado = procesando;  
                         r.next();
                         System.out.println(getLinea() + ".nueva sub regla " + r.ins_next);
                         System.out.println(getLinea()+".Terminal de la sub regla : " + r.T_next);
                     }
                     else {
                         cad = r_cadena;
                         estado = invalido;
                     }
               }else estado = valido;
            }
            //
            
            //si el valor noTerminal no es mayor, pero tampoco es igual, se debe buscar otra regla
            else estado = invalido;
            
            //se genera la busqueda de una nueva regla
            if(estado == invalido) {                
                BlackList.add(r.instruccion);
                Regla reg = getProduccion(nombre_regla, BlackList);
                if (reg != null){
                    r = new Regla(reg.nombre, reg.instruccion);
                    r.next();
                    estado = procesando;
                }else estado = invalido;                
            }
            //
        }
        //
        
        if (estado == valido) aceptada = true;
        else aceptada= false;
        System.out.println(getLinea() + ".cad " + cad);
        return aceptada;
    }
    
    public static int getLinea() {//retorna el numero de linea, ayuda en la depuracion de codigo
        return Thread.currentThread().getStackTrace()[2].getLineNumber();
    }

}
