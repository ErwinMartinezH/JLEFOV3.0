/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package funcionMTBD;

import java.util.Arrays;
import java.util.Scanner;

public class Maquina {

  /**
   * @param args the command line arguments
   */
  public static void main(String[] args) {
    Scanner leer = new Scanner(System.in);
    Scanner esc = new Scanner(System.in);
    int menu = 1, creaEstado = 0, d = 1, i, numeroEstado, j, p, k, c, R,menuPruebas, contador=0, Estados_usados;
    String transicion = "";
    String js, cadenaNueva;
    String[] simbolos = new String[4];
    Estado estados[] = new Estado[50];
  //  Automata automata = new Automata();
    for (j = 0; j < 50; j++) {
      estados[j] = new Estado();
      //estados[j] = null;
    }
    while (menu != 0) {
      try {
        System.out
            .println("\n1. Crear estado\n" + "2. Crear transicion\n" + "3. Ejecutar\n" + "4. Validar\n" + "5. Casos de prueba\n" + "6. Crear automata\n" + "0. Salir");
        menu = leer.nextInt();
      } catch (Exception sd) {
        System.out.println("No es una opcion");
      }
      switch (menu) {
        case 1: {
          //estados[creaEstado] = new Estado();
          System.out.println("Estado " + creaEstado + " creado:");
          creaEstado++;
          i = creaEstado - 1;
          System.out.println("Presione 1 para que sea un estado de finalizacion y 0 para que no lo sea");
          d = leer.nextInt();
          if (d == 1) {
            estados[i].bandera = true;
          }
          d = 1;
          System.out.println("Presione cuantas entradas quiere ingresar");
          p = leer.nextInt();
          for (j = 0; j < p; j++) {
            System.out.println("Ingrese la transicion separada por comas");// El estado al que lo manda, lo que lee lo
                                                                           // que escribe y la direccion
            transicion = esc.nextLine();
            simbolos = transicion.split(",");
            estados[i].agregar(Integer.parseInt(simbolos[0]), simbolos[1].charAt(0), simbolos[2].charAt(0),
                simbolos[3].charAt(0));

          }
        }
        case 2 : {
          System.out.println("ingrese el numero del estado al que la quiere agregar transisiones");
          i = leer.nextInt();
          d = 1;
          while (d == 1) {
            System.out.println("Presione 1 para crear entrada y 0 para ir al menu principal");
            d = leer.nextInt();
            if (d == 1) {
              System.out.println("Ingrese la transicion separada por comas");// El estado al que lo manda, lo que lee lo
                                                                             // que escribe y la direccion
              transicion = esc.nextLine();
              simbolos = transicion.split(",");
              estados[i].agregar(Integer.parseInt(simbolos[0]), simbolos[1].charAt(0), simbolos[2].charAt(0),
                  simbolos[3].charAt(0));
            }
          }
        }
        case 3 : {
          System.out.println("Ingrese la cadena a evaluar");
          contador = 0;
          String cadena = esc.nextLine();
          cadena = cadena + "BBBB";
          char[] cadenaC = cadena.toCharArray();
          numeroEstado = 0;
          j = 0;
          while (j < cadena.length()) {

            transicion = estados[numeroEstado].paso(cadenaC[j]);
            System.out.println("\nLee un " + cadenaC[j]); // indica que letra esta leyendo en el paso a paso
            // System.out.println("llego aca " + transicion);
            if (transicion == "rechazo") {
              System.out.println("Cadena " + cadena + " rechazada");
              break;
            }
            if (contador >= 1500) {
              System.out.println("La maquina tiene bucles infinitos");
              break;
            }
            simbolos = transicion.split(",");
            contador++;
            numeroEstado = Integer.parseInt(simbolos[0]);
            System.out.println("Cambia el simbolo " + cadenaC[j] + " por el simbolo " + simbolos[1].charAt(0));
            cadenaC[j] = simbolos[1].charAt(0);
            System.out.println("La cadena actual es: " + String.valueOf(cadenaC));
            if (simbolos[2].equals("I") || simbolos[2].equals("i")) {
              if(j==0){
               // System.out.println("Cadena " + cadena + " rechazada");
             //     cadena = "B" + cadena;
              //    char [] CadenaA = cadena.toCharArray();
                    String aux= "B" + String.valueOf(cadenaC);
                   cadenaC = aux.toCharArray();
                  j=1;
              }
              j--;
            } else if (simbolos[2].equals("D") || simbolos[2].equals("d")) {
                if(j== (cadenaC.length -1 )){
                      String aux=  String.valueOf(cadenaC) + "B";
                      cadenaC = aux.toCharArray();
                  }
              j++;
            }
            if (estados[numeroEstado].bandera == true) {
              cadenaNueva = String.valueOf(cadenaC);
              System.out.println("Cadena " + cadenaNueva + " Aceptada, Cadena antigua: " + cadena);
              break;
            }
          }

        }

        case 4 : {
          System.out.println(
              "Presiona 1 para mostrar todas las cadena, 2 para mostrar las cadenas que son aceptadas y 3 para mostrar las cadenas que son rechazadas");
          R = leer.nextInt();
          for (k = 1; k <= 8; k++) {
            js = Integer.toString(k);
            c = 1;
            for (i = 1; i <= k; i++) {
              c = c * 2;
            }
            for (i = 0; i < c; i++) {
              String binary = Integer.toBinaryString(i); // Crea un numero en binario que sea una cadena
              String cadena = String.format(("%" + js + "s"), binary).replace(' ', '0'); // Elimina los 0 a la izquierda
                                                                                         // dependiendo la cadena
              contador=0;
              char[] cadenaC = cadena.toCharArray(); // Convierte la cadena en un areglo de chars para poder
                                                     // modificarlos
              numeroEstado = 0; // numero de estado
              // int estado = 0;
              j = 0; // Esta valor se mueve a traves de los simbolos mientras avanza la cadena
              while (j < cadena.length()) {
                transicion = estados[numeroEstado].paso(cadenaC[j]); // Manda el simbolo y el estando en el que esta
                if (transicion == "rechazo") { // Si no hay una entrada con ese simbolo la maquina falla
                  if (R == 1 || R == 3) {
                    System.out.println("Cadena " + cadena + " rechazada");
                  }
                  break;
                }
                if(contador >= 1500){
                  System.out.println("La maquina tiene bucles infinitos");
                  k=9;
                  i=c;
                  break;
                }
                simbolos = transicion.split(","); // Regresa los valores de la entrada
                numeroEstado = Integer.parseInt(simbolos[0]); // Es el estado al que viaja la maquina
                cadenaC[j] = simbolos[1].charAt(0); // El valor que va a escribir
                contador++;
                if (simbolos[2].equals("I") || simbolos[2].equals("i")) { // Es la direccion a la que va la cinta
                  if(j==0){
               // System.out.println("Cadena " + cadena + " rechazada");
             //     cadena = "B" + cadena;
              //    char [] CadenaA = cadena.toCharArray();
                    String aux= "B" + String.valueOf(cadenaC);
                   cadenaC = aux.toCharArray();
                  j=1;
              }
                  j--;
                } else if (simbolos[2].equals("D") || simbolos[2].equals("d")) {
                  if(j== (cadenaC.length -1 )){
                      String aux=  String.valueOf(cadenaC) + "B";
                      cadenaC = aux.toCharArray();
                  }
                    j++;
                }
                if (estados[numeroEstado].bandera == true) { // comprueba si llego a un estado final
                  cadenaNueva = String.valueOf(cadenaC);
                  if (R == 1 || R == 2) {
                    System.out.println("Cadena " + cadenaNueva + " Aceptada, Cadena antigua: " + cadena );
                  }
                  break;
                }
              }
              
            }
          }
        }
        case 5 : {
          for(i=0;i<estados.length;i++){
            estados[i].bandera=false;
            estados[i].fin =null;
            estados[i].fr =null;
            
          }
          System.out.println("Selecciona el caso de prueba que quieres ver:\n1. Intercalado\n2. Resta.\n3. Suma de 3 Numeros\n4. longitud Impar Error" +
          "\n5. longitud Impar  \n6. Sin 000 Ciclado \n7. inicia 0 Ciclado \n8. cadenas Con Inicio Par Ceros \n9. que Tenga Dos 0 seguidos" +
          "\n10. sin Subcadena 000 \n11. iniciar Terminar 1 \n12.iniciar Terminar Con El Mismo Simbolo \n13.AnBn \n14. Resta bi \n15. Mover el primer valor al final" +
          "\n16. Mover el ultimo valor al principio \n17.Mueve los 1 al principio"        );
          menuPruebas = leer.nextInt();
          switch(menuPruebas){
            case 1 : {
              estados[0].agregar(1, '0', '0', 'D');
              estados[0].agregar(2, '1', '1', 'D');
              estados[1].agregar(2, '1', '1', 'D');
              estados[1].agregar(3, 'B', 'B', 'D');
              estados[2].agregar(1, '0', '0', 'D');
              estados[2].agregar(3, 'B', 'B', 'D');
              estados[3].bandera = true;
          }
            case 2:{
              estados[0].agregar(1,'0','A','D');
              estados[1].agregar(1,'0','0','D');
              estados[1].agregar(2,'1','1','D');
              estados[2].agregar(3,'0','1','I');
              estados[3].agregar(4,'0','1','D');
              estados[3].agregar(3,'1','1','I');
              estados[3].agregar(8,'A','B','D');
              estados[4].agregar(3,'0','1','I');
              estados[4].agregar(4,'1','1','D');
              estados[4].agregar(5,'B','B','I');
              estados[5].agregar(6,'0','0','D');
              estados[5].agregar(5,'1','1','I');
              estados[5].agregar(8,'A','0','D');
              estados[6].agregar(7,'1','0','D');
              estados[7].agregar(8,'1','B','I');
              estados[8].bandera = true;

          }
          case 3 : {
            estados[0].agregar(1, '0', '0', 'D');
            estados[1].agregar(1, '0', '0', 'D');
            estados[1].agregar(2, '1', '0', 'D');
            estados[2].agregar(3, '0', '0', 'D');
            estados[3].agregar(3, '0', '0', 'D');
            estados[3].agregar(4, '1', '0', 'D');
            estados[4].agregar(5, '0', '0', 'D');
            estados[5].agregar(5, '0', '0', 'D');
            estados[5].agregar(6, 'B', 'B', 'D');
            estados[6].bandera = true;
        }
        case 4 : {
            //Se cicla
            estados[0].agregar(1, '0', '0', 'D');
            estados[0].agregar(1, '1', '1', 'I');

            estados[1].agregar(0, '0', '0', 'I');
            estados[1].agregar(0, '1', '1', 'D');
            estados[1].agregar(2, 'B', 'B', 'D');
            estados[2].bandera = true;
        }
        case 5 : {
            estados[0].agregar(1, '0', '0', 'D');
            estados[0].agregar(1, '1', '1', 'D');

            estados[1].agregar(0, '0', '0', 'D');
            estados[1].agregar(0, '1', '1', 'D');
            estados[1].agregar(2, 'B', 'B', 'I');
            estados[2].bandera = true;

        }
        case 6 : {
            //se cicla
            estados[0].agregar(1, '0', '0', 'D');
            estados[0].agregar(3, '1', '1', 'D');

            estados[1].agregar(2, '0', '0', 'D');
            estados[1].agregar(3, '1', '1', 'D');
            estados[1].agregar(2, 'B', 'B', 'I');

            estados[2].agregar(3, '1', '1', 'D');
            estados[2].agregar(1, 'B', 'B', 'D');

            estados[3].agregar(1, '0', '0', 'D');
            estados[3].agregar(3, '1', '1', 'D');
            estados[3].agregar(0, 'B', 'B', 'I');

            estados[4].bandera = true;


        }
        case 7 : {
            //
            estados[0].agregar(1, '0', '0', 'D');

            estados[1].agregar(1, '0', '0', 'D');
            estados[1].agregar(1, '1', '1', 'D');
            estados[1].agregar(1, 'B', 'B', 'D');


            estados[2].bandera = true;


        }
        case 8 : {
            //
            estados[0].agregar(2, '0', '0', 'D');

            estados[1].agregar(2, '0', '0', 'D');
            estados[1].agregar(3, '1', '1', 'D');
            estados[1].agregar(4, 'B', 'B', 'I');

            estados[2].agregar(1, '0', '0', 'D');

            estados[3].agregar(3, '0', '0', 'D');
            estados[3].agregar(3, '1', '1', 'D');
            estados[3].agregar(4, 'B', 'B', 'I');


            estados[4].bandera = true;


        }
        case 9 : {
            //
            estados[0].agregar(1, '0', '0', 'D');
            estados[0].agregar(2, '1', '1', 'D');

            estados[1].agregar(3, '0', '0', 'D');
            estados[1].agregar(2, '1', '1', 'D');

            estados[2].agregar(1, '0', '0', 'D');
            estados[2].agregar(0, '1', '1', 'D');

            estados[3].agregar(3, '0', '0', 'D');
            estados[3].agregar(3, '1', '1', 'D');
            estados[3].agregar(4, 'B', 'B', 'I');

            estados[4].bandera = true;
        }
        case 10 : {
            //
            estados[0].agregar(1, '0', '0', 'D');
            estados[0].agregar(3, '1', '1', 'D');

            estados[1].agregar(2, '0', '0', 'D');
            estados[1].agregar(3, '1', '1', 'D');
            estados[1].agregar(4, 'B', 'B', 'I');

            estados[2].agregar(4, 'B', 'B', 'I');
            estados[2].agregar(3, '1', '1', 'D');

            estados[3].agregar(1, '0', '0', 'D');
            estados[3].agregar(3, '1', '1', 'D');
            estados[3].agregar(4, 'B', 'B', 'I');

            estados[4].bandera = true;
        }
        case 11 : {
            //
            estados[0].agregar(1, '1', '1', 'D');

            estados[1].agregar(1, '0', '0', 'D');
            estados[1].agregar(1, '1', '1', 'D');
            estados[1].agregar(2, 'B', 'B', 'I');

            estados[2].agregar(3, '1', '1', 'D');

            estados[3].bandera = true;
        }
        case 12 : {
            //
            estados[0].agregar(1, '0', '0', 'D');
            estados[0].agregar(3, '1', '1', 'D');
            estados[0].agregar(5, 'B', 'B', 'D');

            estados[1].agregar(1, '0', '0', 'D');
            estados[1].agregar(1, '1', '1', 'D');
            estados[1].agregar(2, 'B', 'B', 'I');

            estados[2].agregar(5, '0', '0', 'D');

            estados[3].agregar(3, '0', '0', 'D');
            estados[3].agregar(3, '1', '1', 'D');
            estados[3].agregar(4, 'B', 'B', 'I');

            estados[4].agregar(5, '1', '1', 'D');

            estados[5].bandera = true;
        }
        case 13 : {
            //
            estados[0].agregar(1, '0', 'X', 'D');
            estados[0].agregar(5, 'B', 'B', 'D');
            estados[0].agregar(5, 'Y', 'Y', 'I');

            estados[1].agregar(1, '0', '0', 'D');
            estados[1].agregar(2, '1', '1', 'D');

            estados[2].agregar(2, '1', '1', 'D');
            estados[2].agregar(3, 'B', 'B', 'I');
            estados[2].agregar(3, 'Y', 'Y', 'I');

            estados[3].agregar(4, '1', 'Y', 'I');

            estados[4].agregar(4, '0', '0', 'I');
            estados[4].agregar(4, '1', '1', 'I');
            estados[4].agregar(0, 'X', 'X', 'D');

            estados[5].bandera = true;
        }
            case 14 : {
            //
            estados[0].agregar(1, '0', '0', 'D');
            
            estados[1].agregar(1, '0', '0', 'D');
            estados[1].agregar(2, '1', '1', 'D');

            estados[2].agregar(7, '0', '0', 'D');
            estados[2].agregar(4, 'B', 'B', 'I');

            estados[3].agregar(5, '1', '1', 'I');
            estados[3].agregar(3, '0', '0', 'I');
            
            estados[4].agregar(9, '1', 'B', 'D');
            
            estados[5].agregar(5, '0', '0', 'I');
            estados[5].agregar(6, 'B', 'B', 'D');

            estados[6].agregar(1, '0', 'B', 'D');
            
            estados[7].agregar(7, '0', '0', 'D');
            estados[7].agregar(8, 'B', 'B', 'I');
            
            estados[8].agregar(3, '0', 'B', 'I');
            
            estados[9].bandera = true;
        }
            case 15 : {
            //
            estados[0].agregar(2, '0', '0', 'D');
            estados[0].agregar(1, '1', '1', 'D');

            estados[1].agregar(1, '0', '0', 'D');
            estados[1].agregar(1, '1', '1', 'D');
            estados[1].agregar(3, 'B', '1', 'I');
            
            estados[2].agregar(2, '0', '0', 'D');
            estados[2].agregar(2, '1', '1', 'D');
            estados[2].agregar(3, 'B', '0', 'I');
            
            estados[3].agregar(3, '0', '0', 'I');
            estados[3].agregar(3, '1', '1', 'I');
            estados[3].agregar(4, 'B', 'B', 'D');
            
            estados[4].agregar(5, '0', 'B', 'D');
            estados[4].agregar(5, '1', 'B', 'D');
            
            estados[5].agregar(5, '1', '1', 'D');
            estados[5].agregar(5, '0', '0', 'D');
            estados[5].agregar(6, 'B', 'B', 'D');

            estados[6].bandera = true;
        }
            case 16 : {
            //
            estados[0].agregar(0, '0', '0', 'D');
            estados[0].agregar(0, '1', '1', 'D');
            estados[0].agregar(1, 'B', 'B', 'I');

            estados[1].agregar(2, '0', 'B', 'I');
            estados[1].agregar(3, '1', 'B', 'I');
            
            estados[2].agregar(2, '0', '0', 'I');
            estados[2].agregar(2, '1', '1', 'I');
            estados[2].agregar(4, 'B', '0', 'D');
            
            estados[3].agregar(3, '0', '0', 'I');
            estados[3].agregar(3, '1', '1', 'I');
            estados[3].agregar(4, 'B', '1', 'D');
            
            estados[4].agregar(4, '0', '0', 'D');
            estados[4].agregar(4, '1', '1', 'D');
            estados[4].agregar(5, 'B', 'B', 'B');

            estados[5].bandera = true;
        }
        
            case 17 : {
            //
            estados[0].agregar(0, '0', '0', 'D');
            estados[0].agregar(1, '1', '1', 'D');
            estados[0].agregar(5, 'B', 'B', 'D');

            estados[1].agregar(1, '1', '1', 'D');
            estados[1].agregar(2, 'B', 'B', 'I');
            
            estados[2].agregar(3, '1', 'B', 'I');
            
            estados[3].agregar(4, 'B', '1', 'D');
            estados[3].agregar(3, '1', '1', 'I');
            estados[3].agregar(3, '0', '0', 'I');
            
            estados[4].agregar(4, '1', '1', 'D');
            estados[4].agregar(0, '0', '0', 'D');

            estados[5].bandera = true;
        }
        }
      }
      case 6 : {
            Estados_usados =0;
            while( estados[Estados_usados] != null&& estados[Estados_usados].fr != null){
                Estados_usados++;
            }
   //         automata.crear_nodos(Estados_usados);

            Estados_usados =0;
             while( estados[Estados_usados] != null && estados[Estados_usados].fr != null){
                  Ent fr = estados[Estados_usados].fr;
                  Ent fin = estados[Estados_usados].fin;
                  Ent aux = fr;
                  while(aux != null) {
                      String Arco = aux.lee + ";" + aux.escribe + ";" + aux.direccion;
                //      automata.arcos(("q" + Integer.toString(Estados_usados)), ("q" + Integer.toString(aux.numeroEstado)), Arco);
                      aux = aux.sig;
                  }
                  Estados_usados++;
             }
        //     automata.mostrar();

      }
        case 0 : {
          System.out.println("Saliendo...");
          System.exit(0);
        }
        default : {
          System.out.println("Opcion no valida");
        }

      }
    }
  }
}
