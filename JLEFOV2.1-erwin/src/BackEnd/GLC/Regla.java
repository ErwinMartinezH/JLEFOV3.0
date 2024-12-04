/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package BackEnd.GLC;

/**
 *
 * @author jesus
 */
public class Regla {
    String nombre, instruccion, T_next,NoT_next,ins_next;
    
    public Regla(String s, String ss){
        this.nombre = s;
        this.instruccion = ss;
        this.ins_next = this.instruccion;//instruccion local
    }
    
    public String subInstruccionA(){
        String s = "", cache = "";
        for(int i = 0; i < instruccion.length(); i++){            
            cache = instruccion.substring(i, i+1);
            
            if(cache.equals(cache.toUpperCase()))break;            
            else s += cache;
        }
        return s;
    }
    
    public String subInstruccionB(){
        if(subInstruccionA().length() == instruccion.length()) return "";
        else return instruccion.substring(subInstruccionA().length()+1);
    }
    
    public String noTerminal(){
        if(subInstruccionA().length() == instruccion.length()) return null;
        else return instruccion.substring(subInstruccionA().length(), subInstruccionA().length()+1);
    }
    
    public void next(){//retona la cadena terminal de la regla, y almacena la instrucion faltante por ejecutar
        String T = "";
        NoT_next = "";
        int j = 0;
        for(int i= 0; i < ins_next.length(); i++){//recorrido para extraer los no terminales de la regla
        if(!ins_next.substring(i, i+1).equals(ins_next.substring(i, i+1).toUpperCase())){//se valida que no este en mayusculas
            T += ins_next.substring(i, i+1);            
            j = i;
        }else{
            NoT_next = ins_next.substring(i, i+1);
            break;
        }
    }
        //System.out.println(ins_next);
        ins_next = ins_next.substring(T.length()+NoT_next.length(), ins_next.length());
        if(NoT_next.equals(""))NoT_next = null;
        if(ins_next.equals(""))ins_next = null;
        this.T_next = T;
        //System.out.println("xxxx " + T + " uuu " + NoT_next +" ggg " + ins_next );
    }
    
    @Override
    public String toString(){
        return nombre +"-"+instruccion;
    }
    public String[] regla(){//ochoa
        return new String[] {this.nombre, this.instruccion};
    }
    

}
