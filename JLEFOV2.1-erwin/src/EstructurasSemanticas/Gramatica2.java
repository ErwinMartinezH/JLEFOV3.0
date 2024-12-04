/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasSemanticas;

import BackEnd.GLC.Regla2;

import java.util.ArrayList;

/**
 *
 * @author jesus
 */
public class Gramatica2 {
    /*Una gramatica esta compuesta por reglas; este objeto es una gramatica
    por lo tanto tiene un conjunto de reglas.*/
    private ArrayList<Regla2> reglas = new ArrayList();
    private String ep = "?";
    private String cad = "", hojas="";
    int max;
    // private string pila;
    boolean F = false;
    /*una producion es igual a el objeto regla, puede constar de no terminales y
    terminales, por lo tanto para crear una se deben tener estos dos argumentos*/
    public void addProduccion(char Not,char[] Ter){
        this.reglas.add(new Regla2(Not,Ter));
    }

    public void addProduccion(Regla2[] r){
        for(Regla2 a: r){
            this.reglas.add(a);
        }

    }

    public void removeProduccion(char c){
        ArrayList<Regla2> nuevalista = new ArrayList<>();
        for(Regla2 r : reglas){
            if(r.getInstruccion()[0] != '?')
                nuevalista.add(r);
        }
        reglas = nuevalista;
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
        max = cad.length() + 5;
        //   System.out.println("pila " + pila.length() + "max " + max);
        prueba(cadena, pila, "S",0);

        //       System.out.println("Ultimo " + F);
        if (F == true) {

            System.out.println("La cadena " + cad + " es aceptada");
        } else {
            System.out.println("La cadena " + cad + " es rechazada");
        }
    }

    public void prueba(String cadena, String pila, String nombre, int cont) {
        if (F == false && pila.length() < max && cont < cad.length()*2+8) {
            //         System.out.println("\n\n\nrecorrido");
//        System.out.println("La cadena es: " + cadena);
//        System.out.println("La pila es:" + pila);
//        System.out.println("nombre es: " + nombre);
//System.out.println("si llego");
            String cadC;
            String pilaC;
            char mayus = 0;
            String instruccion;
            boolean contieneMayuscula;
            System.out.println("\n*********************************************************************");
            System.out.println("prueba " +"cadena "+ cadena +" pila: "+ pila + " nombre"+nombre + " cont:"+ cont);
            for (Regla2 regla : reglas) {
                if (F == true) {
                    break;
                }
                //            System.out.println("estoy en el for: " + j);
                //      j++;
//                System.out.println("regla " + regla.nombre + "-" + regla.instruccion);
//                System.out.println("FLa cadena es: " + cadena);
//                System.out.println("el nombre es " + nombre);
//                System.out.println("La pila es:" + pila);
                //if (regla.nombre.equals(nombre)) {
//                    System.out.println("si son iguales");
                //}
                instruccion = new String(regla.getInstruccion());

                if (String.valueOf(regla.getNombre()).equals(nombre)) {
                    System.out.println("instruccion" + instruccion);
                    System.out.println("get " + String.valueOf(regla.getNombre())+ "-" + nombre);

                    //      System.out.println("");
                    System.out.println("manda a Recorrido " +"cadena "+ cadena +" pila: "+ pila + " nombre "+nombre + " instruccion: "+ instruccion +" cont: "+ cont);
                    //         System.out.println("Recorrido,  " +cadena + pila + nombre + instruccion + cont);
                    recorrido(cadena, pila, nombre, instruccion, cont);
                }
            }

        }
    }

    public void recorrido(String cadena, String pila, String nombre, String instruccion, int cont) {
        int min;
        cont++;
        min = (int) pila.chars().filter(Character::isUpperCase).count();
        min = pila.length() - min;
        System.out.println("llego aca -----------------------------------------");
        if (F == false && (cadena.length()+min) <= cad.length() && pila.length() < max) {
            System.out.println("\n inicio, iteracion " + cont);
            System.out.println("pila :" + pila + " min " + min);
            System.out.println("cadena: "+ cadena);
            System.out.println("instruccion " + instruccion);

            int j = 0;
            String cadC= "";
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

            if (contieneMayuscula) {

                if (j == (instruccion.length() - 1)) {// la ultima letra es mayuscula
                    if (instruccion.length() == 1) { // verifica si la intruccion solo es de tamaño 1 y es no terminal
                        cadC = cadena;

                    } else {
                        instruccion = instruccion.substring(0, instruccion.length() - 1);
                        //      mayus = instrucion.
                        cadC = cadena + instruccion;
                    }
                    System.out.println("Mayuscula al final\n pila actualizada: " + pila);
                    System.out.println("cadena actualizada: " + cadC);

                    if (cad.startsWith(cadC)) {
                        prueba(cadC, pila, Character.toString(mayus), cont);
                    }
                } else {// hay mayusculas

                    if (instruccion.equals(instruccion.toUpperCase()) && instruccion.matches("[A-Z]+"))  {// si todas son mayusculas
                        cadC = cadena;
                        pilaC = instruccion.substring(j + 1) + pila;
                        System.out.println("Todas son mayusculas\n pila actualizada: " + pilaC);
                        //  System.out.println("cadena actualizada: " + cadC);
                    } else if (Character.isUpperCase(instruccion.charAt(0))) { // si la primera en mayuscula
                        pilaC =  instruccion.substring(1) + pila;
                        cadC = cadena;
                        System.out.println("la primera es mayuscula\n pila actualizada: " + pila);
                        System.out.println("cadena actualizada: " + cadC);
                    } else {

                        cadC =  instruccion.substring(0, j);
                        cadC = cadena + cadC;
                        pilaC = instruccion.substring(j + 1) + pila;
                        System.out.println("Hay una mayuscula\n pila actualizada: " + pila);
                        System.out.println("cadena actualizada: " + cadC);

                    }

                    if (cad.startsWith(cadC)) {
                        System.out.println("se activa");
                        System.out.println("cadena actual " + cadC  + " Pila actual " + pilaC + " Instruccion " + Character.toString(mayus) + " cadena actual " +cont);
                        prueba(cadC, pilaC, Character.toString(mayus),cont);
                    }
                }
            } else {
                if (instruccion.equals("?")) {
                    cadena = cadena;
                } else {
                    cadena = cadena + instruccion;
                }
                System.out.println("no habia\n pila actualizada: " + pila);
                System.out.println("cadena actualizada: " + cadena);
                for (int i = 0; i < pila.length(); i++) {
                    caracter = pila.charAt(i);
                    if (Character.isUpperCase(caracter)) {
                        contieneMayuscula = true;

                        mayus = caracter;
                        j = i;
                        break;


                    }
                }
                if (contieneMayuscula) {
                    if (j == (pila.length() - 1)) {// Realizar algo si la última letra es mayúscula
                        if (pila.length() == 1) {
                            cadC = cadena;
                            pilaC= "";
                        } else {
                            pila = pila.substring(0, pila.length() - 1);
                            //      mayus = instrucion.
                            cadC = cadena + pila;
                            pilaC= "";
                        }
                        if (cad.startsWith(cadC)) {
                            System.out.println("si es igual");
                            prueba(cadC, pilaC, Character.toString(mayus),cont);
                        }
                    } else {
                        if (Character.isUpperCase(pila.charAt(0))) {
                            pilaC = pila.substring(1);
                            cadC = cadena;
                        } else {
                            //aqui tambien me quede editando
                            cadC = pila.substring(0, j);
                            cadC = cadena + cadC;

                            pilaC = pila.substring(j + 1);
                        }

                        if (cad.startsWith(cadC)) {
                            System.out.println("Si es igual primis viene:" + mayus+ ", instruccion" + instruccion );
                            prueba(cadC, pilaC, Character.toString(mayus),cont);
                        }
                    }
                }
                if (!contieneMayuscula) {
                    cadC = cadena + pila;

                    if (cad.equals(cadC)) {

                        F = true;
                    }
                }

            }
        }
    }
}
