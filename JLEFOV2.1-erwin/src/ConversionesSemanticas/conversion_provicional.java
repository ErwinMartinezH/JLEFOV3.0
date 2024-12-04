/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConversionesSemanticas;

import EstructurasSemanticas.Automata;
import EstructurasSemanticas.Estado;
import EstructurasSemanticas.Regla2;

import java.util.ArrayList;

/**
 * @author pedro
 */
public class conversion_provicional {

    private Estado[] e;
    private Regla2[] r;


    public void conversion(int i) {
        e = getAutomata(i);
        r = getGramatica(i);
    }

    public Estado[] getAutomata() {
        return e;
    }

    public Regla2[] getGramatica() {
        return r;
    }

    private Estado[] getAutomata(int i) {
        Automata a = new Automata();
        switch (i) {
            case 0://palindromos de a,b
                a.addTransicion(0, 'a', '?', 'a', 0);
                a.addTransicion(0, 'b', '?', 'b', 0);
                a.addTransicion(0, '?', '?', '?', 1);
                a.addTransicion(1, 'a', 'a', '?', 1);
                a.addTransicion(1, 'b', 'b', '?', 1);
                a.addTransicion(1, '?', 'Z', '?', 2);
                a.setAceptacion(2, true);
                break;
            case 1:
                // L = 0^n 1^m 0^n ; n,m > 0
                a.addTransicion(0, '0', '?', '0', 1);
                a.addTransicion(1, '0', '?', '0', 1);
                a.addTransicion(1, '1', '?', '?', 2);
                a.addTransicion(2, '1', '?', '?', 2);
                a.addTransicion(2, '0', '0', '?', 3);
                a.addTransicion(3, '0', '0', '?', 3);
                a.addTransicion(3, '?', 'Z', '?', 4);
                a.setAceptacion(4, true);
                break;


            case 2:// L = a^n b^n ; n >= 0
                a.addTransicion(0, 'a', '?', '1', 0);
                a.addTransicion(0, '?', '?', '?', 1);
                a.addTransicion(1, 'b', '1', '?', 1);
                a.addTransicion(1, '?', 'Z', '?', 2);
                a.setAceptacion(2, true);
                break;
            case 3: //L = a^n b^m c^m d^n donde n,m >0
                a.addTransicion(0, 'a', '?', 'b', 0);
                a.addTransicion(0, 'a', '?', 'b', 1);
                a.addTransicion(1, 'b', '?', 'd', 1);
                a.addTransicion(1, 'b', '?', 'd', 2);
                a.addTransicion(2, 'c', 'd', '?', 2);
                a.addTransicion(2, 'c', 'd', '?', 3);
                a.addTransicion(3, 'd', 'b', '?', 3);
                a.addTransicion(3, 'd', 'b', '?', 4);
                a.addTransicion(4, '?', 'Z', '?', 4);
                a.setAceptacion(4, true);
                break;


            case 4: //L={Cadenas s/Σ {a,b} que sean: a^n b^n+1; P/n>=0}
                a.addTransicion(0, 'a', '?', 'b', 0);
                a.addTransicion(0, '?', '?', '?', 1);
                a.addTransicion(1, 'b', 'b', '?', 1);
                a.addTransicion(1, 'b', '?', '?', 2);
                a.addTransicion(2, '?', 'Z', '?', 3);
                a.setAceptacion(3, true);
                break;


            case 5: // l= a^i b^j c^k donde i,j,k >=0 y i+j = k
                a.addTransicion(0, 'a', '?', 'b', 0);
                a.addTransicion(0, '?', '?', '?', 1);
                a.addTransicion(1, 'b', '?', 'b', 1);
                a.addTransicion(1, '?', '?', '?', 2);
                a.addTransicion(2, 'c', 'b', '?', 2);
                a.addTransicion(2, '?', 'Z', '?', 3);
                a.setAceptacion(3, true);
                break;
            default:
                a = null;

        }

        return a.getEstados();
    }

    private Regla2[] getGramatica(int i) {
        Regla2[] g;
        switch (i) {
            case 0://palindromos
                g = new Regla2[3];
                g[0] = new Regla2('S', new char[]{'a', 'S', 'a'});
                g[1] = new Regla2('S', new char[]{'b', 'S', 'b'});
                g[2] = new Regla2('S', new char[]{'?'});
                break;
            case 1://L = 0^n 1^m 0^n ; n,m >= 0
                g = new Regla2[4];
                g[0] = new Regla2('S', new char[]{'0', 'S', '0'});
                g[1] = new Regla2('S', new char[]{'A'});
                g[1] = new Regla2('A', new char[]{'1', 'A'});
                g[2] = new Regla2('A', new char[]{'?'});
                break;

            case 2: // L = a^n b^n ; n >= 0
                g = new Regla2[2];
                g[0] = new Regla2('S', new char[]{'a', 'S', 'b'});
                g[1] = new Regla2('S', new char[]{'?'});
                break;

            case 3: //L = a^n b^m c^m d^n donde n,m >0
                g = new Regla2[5];
                g[0] = new Regla2('S', new char[]{'a', 'A', 'd'});
                g[1] = new Regla2('A', new char[]{'a', 'A', 'd'});
                g[2] = new Regla2('A', new char[]{'B'});
                g[3] = new Regla2('B', new char[]{'b', 'B', 'c'});
                g[4] = new Regla2('B', new char[]{'b', 'c'});
                break;

            case 4: //L={Cadenas s/Σ {a,b} que sean: a^n b^n+1; P/n>=0}
                g = new Regla2[2];
                g[0] = new Regla2('S', new char[]{'a', 'S', 'b'});
                g[1] = new Regla2('S', new char[]{'b'});
                break;

            case 5: // l= a^i b^j c^k donde i,j,k >=0 y i+j = k
                g = new Regla2[4];
                g[0] = new Regla2('S', new char[]{'a', 'S', 'c'});
                g[1] = new Regla2('S', new char[]{'A'});
                g[2] = new Regla2('A', new char[]{'b', 'A', 'c'});
                g[3] = new Regla2('A', new char[]{'?'});
                break;
            default:
                g = new Regla2[0];

        }

        return g;
    }
}
