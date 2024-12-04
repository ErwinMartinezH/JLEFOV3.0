package ConversionesSemanticas;

import EstructurasSemanticas.Estado;
import EstructurasSemanticas.Automata;
import ConversionesSemanticas.Con_ADPtoGR;

public class PruebaAutomata {

    public static void main(String args[]) {
        //    Conversion con = new Conversion();

        ejemplo7();
    }

    public static void ejemplo1() {
        Automata a = new Automata();
        Con_ADPtoGR con = new Con_ADPtoGR();
        //palindromos de a,b

        a.addTransicion(0, 'a', '?', 'a', 0);
        a.addTransicion(0, 'b', '?', 'b', 0);
        a.addTransicion(0, '?', '?', '?', 1);
        a.addTransicion(1, 'a', 'a', '?', 1);
        a.addTransicion(1, 'b', 'b', '?', 1);
        a.addTransicion(1, '?', 'Z', '?', 2);
        a.setAceptacion(2, true);


        System.out.println("Estados" + a.getEstados());
        a.start(new char[]{'a', 'a', 'b','?', 'b', 'a', 'a'});
        a.start(new char[]{'a','a', 'b', 'a'});
        a.start(new char[]{'a', 'a','?', 'b', 'b'});

        con.conversion(a.estados());
    }

    public static void ejemplo2() {
        Automata a = new Automata();
        Con_ADPtoGR con = new Con_ADPtoGR();
        // L = 0^n 1^m 0^n ; n,m > 0
        a.addTransicion(0, '0', '?', '0', 1);
        a.addTransicion(1, '0', '?', '0', 1);
        a.addTransicion(1, '1', '?', '?', 2);
        a.addTransicion(2, '1', '?', '?', 2);
        a.addTransicion(2, '0', '0', '?', 3);
        a.addTransicion(3, '0', '0', '?', 3);
        a.addTransicion(3, '?', 'Z', '?', 4);
        a.setAceptacion(4, true);



        System.out.println("Estados" + a.getEstados());
        a.start("0001111000".toCharArray());
        a.start("0001100".toCharArray());
        a.start("00100".toCharArray());


        con.conversion(a.estados());
    }

    public static void ejemplo3() {
        Automata a = new Automata();
        Con_ADPtoGR con = new Con_ADPtoGR();

        // L = a^n b^n ; n >= 0
        a.addTransicion(0, 'a', '?', '1', 0);
        a.addTransicion(0, '?', '?', '?', 1);
        a.addTransicion(1, 'b', '1', '?', 1);
        a.addTransicion(1, '?', 'Z', '?', 2);
        a.setAceptacion(2, true);

        System.out.println("Estados" + a.getEstados());
        a.start("000111".toCharArray());
        a.start("000110".toCharArray());
        a.start("01".toCharArray());

        con.conversion(a.estados());
    }

    public static void ejemplo4() {
        Automata a = new Automata();
        Con_ADPtoGR con = new Con_ADPtoGR();
        //L = a^n b^m c^m d^n donde n,m >0
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
        a.start("aabcdd".toCharArray());
        a.start("abcd".toCharArray());
        a.start("abbccdd".toCharArray());

        System.out.println("Estados" + a.getEstados());


        con.conversion(a.estados());
    }
    public static void ejemplo5() {
        Automata a = new Automata();
        Con_ADPtoGR con = new Con_ADPtoGR();
        //L={Cadenas s/Σ {a,b} que sean: a^n b^n+1; P/n>=0}
        a.addTransicion(0, 'a', '?', 'b', 0);
        a.addTransicion(0, '?', '?', '?', 1);
        a.addTransicion(1, 'b', 'b', '?', 1);
        a.addTransicion(1, 'b', '?', '?', 2);
        a.addTransicion(2, '?', 'Z', '?', 3);
        a.setAceptacion(3, true);
        a.start("aaa?bbbb".toCharArray());
        a.start("aa?bb".toCharArray());
        a.start("a?bb".toCharArray());




        con.conversion(a.estados());
    }
    public static void ejemplo6() {
        Automata a = new Automata();
        Con_ADPtoGR con = new Con_ADPtoGR();
        // l= a^i b^j c^k donde i,j,k >=0 y i+j = k
        a.addTransicion(0, 'a', '?', 'b', 0);
        a.addTransicion(0, '?', '?', '?', 1);
        a.addTransicion(1, 'b', '?', 'b', 1);
        a.addTransicion(1, '?', '?', '?', 2);
        a.addTransicion(2, 'c', 'b', '?', 2);
        a.addTransicion(2, '?', 'Z', '?', 3);
        a.setAceptacion(3, true);
        a.start("aaa?bbb?cccccc".toCharArray());
        a.start("aa?bb?cccc".toCharArray());
        a.start("a?b?c".toCharArray());




        con.conversion(a.estados());
    }

    public static void ejemplo7() {
        Automata a = new Automata();
        Con_ADPtoGR con = new Con_ADPtoGR();
        //L={Cadenas s/Σ {a,b} que sean: a^n b^n+1; P/n>=0}
        a.addTransicion(0, 'a', '?', 'a', 0);
        a.addTransicion(0, 'b', 'a', '?', 1);
        a.addTransicion(1, 'b', 'a', '?', 1);
        a.addTransicion(1, '?', 'Z', '?', 2);
        a.setAceptacion(2, true);
        a.start("aaa?bbb".toCharArray());
        a.start("aabb".toCharArray());
        a.start("aaabb".toCharArray());
        con.conversion(a.estados());
    }
}
