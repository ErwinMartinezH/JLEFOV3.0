/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ADP;

import BackEnd.GLC.Regla2;
import java.util.ArrayList;

/**
 *
 * @author pedro
 */
public class conversion_provicional {
    
  //  private Estado[] e;
    private Regla2[] r;
    private Automata a = new Automata();
   // Automata a = new Automata();
    
    public void conversion(int i){
        a = getAutomata(i);
        r = getGramatica(i);
    }


    public Automata getAutomata() {
        return a;
    }

    public Regla2[] getGramatica() {
        return r;
    }
    
    private Automata getAutomata(int i){
       // Automata a = new Automata();
       Automata a = new Automata();
        switch(i){
            case 0://palindromos de a,b
                a.addTransicion(0, 'a', '?', 'a', 0);
                a.addTransicion(0, 'b', '?', 'b', 0);        
                a.addTransicion(0, '?', '?', '?', 1);     
                a.addTransicion(1, 'a', 'a', '?', 1); 
                a.addTransicion(1, 'b', 'b', '?', 1);
                a.addTransicion(1, '?', 'Z', '?', 2);        
                a.setAceptacion(2, true);
                break;
            case 1:// L = 0^n 1^m 0^n ; n,m > 0
                a.addTransicion(0, '0', '?', '0', 1);
                a.addTransicion(1, '0', '?', '0', 1);        
                a.addTransicion(1, '1', '?', '?', 2);     
                a.addTransicion(2, '1', '?', '?', 2); 
                a.addTransicion(2, '0', '0', '?', 3);
                a.addTransicion(3, '0', '0', '?', 3); 
                a.addTransicion(3, '?', 'Z', '?', 4); 
                a.setAceptacion(4, true);
                break;
            case 2:// L = a^i b c^k ; i,k > 0 & i < k
                a.addTransicion(0, 'a', '?', '1', 1);
                a.addTransicion(1, 'a', '?', '1', 1);        
                a.addTransicion(1, 'b', '?', '?', 2);     
                a.addTransicion(2, 'c', '1', '?', 3); 
                a.addTransicion(3, 'c', '1', '?', 3);
                a.addTransicion(3, 'c', 'Z', '?', 4); 
                a.addTransicion(4, '?', '?', '?', 4);       
                a.setAceptacion(4, true);
                break;
            case 3://a^i b^i
                a.addTransicion(0, 'a', '?', 'a', 0);
                a.addTransicion(0, 'b', 'a', '?', 1);        
                a.addTransicion(1, 'b', 'a', '?', 1);     
                a.addTransicion(1, '?', 'Z', '?', 2); 
    
                a.setAceptacion(2, true);
                break;
                
            default: a = null;
        }
        
        return a;
    }
    
    private Regla2[] getGramatica(int i){
        Regla2[] g;
        switch(i){
            case 0://palindromos
                g = new Regla2[3];
                g[0] = new Regla2('S', new char[]{'a','S','a'});
                g[1] = new Regla2('S', new char[]{'b','S','b'});
                g[2] = new Regla2('S', new char[]{'?'});
                break;
            case 1://L = 0^n 1^m 0^n ; n,m >= 0
                g = new Regla2[4];
                g[0] = new Regla2('S', new char[]{'0','S','0'});
                g[1] = new Regla2('S', new char[]{'0','A','0'});
                g[2] = new Regla2('A', new char[]{'1','A'});
                g[3] = new Regla2('A', new char[]{'1'});
                break;
            case 2:// L = a^i b c^k ; i,k > 0 & i < k
                g = new Regla2[5];
                g[0] = new Regla2('S', new char[]{'a','A','c'});
                g[1] = new Regla2('A', new char[]{'a','A','B'});
                g[2] = new Regla2('A', new char[]{'b','c'});
                g[3] = new Regla2('B', new char[]{'c','B'});
                g[4] = new Regla2('B', new char[]{'c'});
                break;
            case 3://a^i b^i
                g = new Regla2[2];
                g[0] = new Regla2('S', new char[]{'a','S','b'});
                g[1] = new Regla2('S', new char[]{'?'});
                break;
            default : g = new Regla2[0];
        }
        
        return g;
    }
}
