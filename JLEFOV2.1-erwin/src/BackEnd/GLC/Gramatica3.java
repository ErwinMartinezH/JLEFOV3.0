package BackEnd.GLC;

import BackEnd.GLC.TreeNodeDerivation;

import java.util.ArrayList;
import java.util.List;
import static javax.swing.JOptionPane.INFORMATION_MESSAGE;
import static javax.swing.JOptionPane.showMessageDialog;

public class Gramatica3 {
    /*Una gramatica esta compuesta por reglas; este objeto es una gramatica
   por lo tanto tiene un conjunto de reglas.*/
    public ArrayList<Regla> reglas = new ArrayList();
    private String ep = "?";
    private String cad = "", hojas = "";
    // private string pila;
    private TreeNodeDerivation raiz;
    ArrayList<String> camino = new ArrayList();
    public boolean F = false;
    public static String alfabeto = "ab";
    public String[] validos = new String[50];
    public String[] invalidos = new String[50];
    public void setCadena(String s) {
        //this.Cadena = s;
    }

    /*una producion es igual a el objeto regla, puede constar de no terminales y
    terminales, por lo tanto para crear una se deben tener estos dos argumentos*/
    public void addProduccion(String noT, String Ins) {
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


    public void recorrido1(String cadena) {//primer metodo que hace llamada a la regla raiz
        cad = cadena;
        F = false;
        String pila = "";
        cadena = "";
        //       System.out.println("primer " + F);
        /*  for (Regla regla : reglas) {
            System.out.println(regla.nombre);
            System.out.println(regla.instruccion);
        }*/
        prueba(cadena, pila, "S");
        //       System.out.println("Ultimo " + F);
        if (F == true) {
            //System.out.println("La cadena " + cad + " es aceptada");
            //showMessageDialog(null, "Cadena aceptada :)" , "Mensaje", INFORMATION_MESSAGE);
        } else {
            //System.out.println("La cadena " + cad + " es rechazada");
            //showMessageDialog(null, "Cadena invalida :(" , "Mensaje", INFORMATION_MESSAGE);
        }
    }

    public void prueba(String cadena, String pila, String nombre) {
        if (F == false) {
            //         System.out.println("\n\n\nrecorrido");
//        System.out.println("La cadena es: " + cadena);
//        System.out.println("La pila es:" + pila);
//        System.out.println("nombre es: " + nombre);
            String cadC;
            String pilaC;
            char mayus = 0;
            String instruccion;
            boolean contieneMayuscula;
            for (Regla regla : reglas) {
                if (F == true) {
                    break;
                }

                if (regla.nombre.equals(nombre)) {
//                    System.out.println("mando el esa madre");
                    recorrido(cadena, pila, regla.nombre, regla.instruccion);
                }
            }
        }
    }

    public void recorrido(String cadena, String pila, String nombre, String instruccion) {
        if (F == false && cadena.length() <= cad.length()) {

            int j = 0;
            String cadC;
            String pilaC;
            boolean contieneMayuscula = false;
            char caracter;
            char mayus = '0';
            contieneMayuscula = false;
            for (int i = 0; i < instruccion.length(); i++) {
                caracter = instruccion.charAt(i);
                if (Character.isUpperCase(caracter)) {
                    contieneMayuscula = true;
                    //              System.out.println(caracter + "es mayuscula");
                    mayus = caracter;
                    j = i;
                    break;
                    //    i= regla.instruccion.length();

                }

            }
            /*  System.out.println("el nombre es " + nombre);
                    System.out.println("cadena " + cadena);
                        System.out.println("pila " + pila);
                    System.out.println("la regla es " +instruccion);*/
            //System.out.println("llego por aca");
            if (contieneMayuscula) {

                if (j == (instruccion.length() - 1)) {
                    // Realizar algo si la última letra es mayúscula
//                    System.out.println("La última letra de regla.instruccion es mayúscula.");
                    //  regla.instruccion.
                    instruccion = instruccion.substring(0, instruccion.length() - 1);
                    //      mayus = instrucion.
                    cadC = cadena + instruccion;
                    if (cad.startsWith(cadC)) {
                        prueba(cadC, pila, Character.toString(mayus));
                    }
                } else {
//                    System.out.println("Hay una mayuscula");

                    String[] inst = instruccion.split(Character.toString(mayus));
                    cadC = instruccion.substring(0, j);
                    cadC = cadena + cadC;
                    pilaC = instruccion.substring(j + 1);
                    pilaC = pilaC + pila;
                    prueba(cadC, pilaC, Character.toString(mayus));
                    // System.out.println("La última letra de regla.instruccion no es mayúscula.");
                }
            } else {
//                System.out.println("cadena " + cadena);
//                System.out.println("pila " + pila);
//                //  cadC = cadena + pila;
//                System.out.println("no se como llegue aca");
//
//                System.out.println(instruccion);
 /*               if (instruccion.equals("?")) {
                    cadC = cadena + pila;
                } else {
                    cadC = cadena + instruccion + pila;
                }* este medio funciona */
//                System.out.println("cadena " + cadena);
//                System.out.println("cad " + cad);
                if (instruccion.equals("?")) {
                    cadena = cadena;
                } else {
                    cadena = cadena + instruccion ;
                }

                for (int i = 0; i < pila.length(); i++) {
                    caracter = pila.charAt(i);
                    if (Character.isUpperCase(caracter)) {
                        contieneMayuscula = true;
                        //              System.out.println(caracter + "es mayuscula");
                        mayus = caracter;
                        j = i;
                        break;
                        //    i= regla.instruccion.length();

                    }
                }
                if (contieneMayuscula) {
                    if (j == (pila.length() - 1)) {
                        // Realizar algo si la última letra es mayúscula
//                    System.out.println("La última letra de regla.instruccion es mayúscula.");
                        //  regla.instruccion.
                        pila = pila.substring(0, pila.length() - 1);
                        //      mayus = instrucion.
                        cadC = cadena + pila;
                        if (cad.startsWith(cadC)) {
                            prueba(cadC, pila, Character.toString(mayus));
                        }
                    } else {
//                    System.out.println("Hay una mayuscula");

                        //  String[] inst = instruccion.split(Character.toString(mayus));
                        //          System.out.println("cadena antes " + cadena);
                        cadC = pila.substring(0, j);
                        cadC = cadena + cadC;
                        //         System.out.println("cadena despues " + cadC);
                        //         System.out.println("pila antes " + pila);

                        pilaC = pila.substring(j + 1);
                        //         System.out.println("pila despues " + pilaC);
                        //     pilaC = pilaC + pila;
                        prueba(cadC, pilaC, Character.toString(mayus));
                        // System.out.println("La última letra de regla.instruccion no es mayúscula.");
                    }
                } if(!contieneMayuscula) {
                    //       System.out.println("\ncadena "+ cadena + " pila " + pila);
                    cadC= cadena + pila;

                    if (cad.equals(cadC) ) {

                        F = true;
                    }
                }


            }
        }
    }
 
   
    public void lista(String alfa) {
    alfabeto = alfa;
    List<String> Combinaciones = Generador_com(1, 6);
   // Combinaciones.forEach(System.out::println);
    recorridocad(Combinaciones);
}

public static List<String> Generador_com(int minLength, int maxLength) {
    List<String> Combinaciones = new ArrayList<>(); // Crea una lista para almacenar las combinaciones
    for (int len = minLength; len <= maxLength; len++) {// Bucle para generar combinaciones de longitud de minLength a maxLength
        Generador2(Combinaciones, "", len);// Llama al ayudante para generar combinaciones
    }
    return Combinaciones;
}

private static void Generador2(List<String> Combinaciones, String CombinacionActual, int length) {
    if (length == 0) {
        Combinaciones.add(CombinacionActual);// Si la longitud alcanza 0, agrega la combinación actual a la lista
        return;
    }

    for (char c : alfabeto.toCharArray()) {
        Generador2(Combinaciones, CombinacionActual + c, length - 1);
    }
}

public void recorridocad(List<String>  Combinaciones) {//primer metodo que hace llamada a la regla raiz
        ArrayList<String> Cvalidas = new ArrayList<>();
        ArrayList<String> Cinvalidas = new ArrayList<>();
        for(String cadena: Combinaciones){
        cad = cadena;
        F = false;
        String pila = "";
        cadena = "";

        prueba(cadena, pila, "S");
        //       System.out.println("Ultimo " + F);
        if (F == true) {
            if(Cvalidas.size()<50){
                Cvalidas.add(cad);
              //  System.out.println("cadena " + cad  + " aceptada");
            }
                
        } else {
            if(Cinvalidas.size()<50){
                Cinvalidas.add(cad);
            //    System.out.println("cadena " + cad  + " Rechazada");
            }
        }
}
        validos = Cvalidas.toArray(new String[Cvalidas.size()]);
        invalidos = Cinvalidas.toArray(new String[Cinvalidas.size()]);
    }

    public void construirArbol() {
        raiz = new TreeNodeDerivation("S"); // Nodo raíz
        TreeNodeDerivation actual = raiz;

        for (String produccion : camino) {
            String[] partes = produccion.split(" ");
            for (String parte : partes) {
                if (!parte.equals(",")) {
                    TreeNodeDerivation nuevoNodo = new TreeNodeDerivation(parte);
                    actual.addChild(nuevoNodo);
                    actual = nuevoNodo;
                }
            }
            actual = raiz; // Reinicia al inicio para la siguiente producción
        }
    }

    // Método para imprimir el árbol de derivación
    public void imprimirArbol() {
        if (raiz != null) {
            System.out.println("Árbol de Derivación:");
            raiz.printTree(0);
        } else {
            System.out.println("El árbol de derivación no ha sido construido.");
        }
    }

    // Método para obtener la raíz del árbol de derivación
    public TreeNodeDerivation getRaiz() {
        return raiz;
    }


}
