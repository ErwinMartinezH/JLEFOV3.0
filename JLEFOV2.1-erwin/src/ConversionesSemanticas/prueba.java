/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ConversionesSemanticas;

import EstructurasSemanticas.Estado;
import EstructurasSemanticas.Regla2;
import java.util.ArrayList;

/**
 *hola mundo
 * @author jesus
 */
public class prueba {
    public static void main (String args[]){
        
    /////////////////////   GLC        /////////////////////////////////////////        
        
        ArrayList<EstructurasSemanticas.Regla2> producciones = new ArrayList();
        
        
        producciones.add(new Regla2('S',new char [] {'a','S','b'}));
        producciones.add(new Regla2('a',new char [] {'c','d'}));
        producciones.add(new Regla2('A',new char [] {'?'}));

        Con_GRtoADP2 cv = new Con_GRtoADP2(producciones);
        //cv.getEstados();
        
        ArrayList<EstructurasSemanticas.Estado> ess = cv.estados;
        EstructurasSemanticas.Estado es;
        ArrayList <EstructurasSemanticas.Transicion> t;
        for(EstructurasSemanticas.Estado e : ess){
            System.out.println(e.getNombre());
            t = e.getTransiciones();
            for (EstructurasSemanticas.Transicion f: t){
                System.out.println(f);
            }
            System.out.println("------------");
        }
        
        
     /////////////////////   ADP        ////////////////////////////////////////
     
     System.out.println("Ejemplo de como podemos extraer el contenido");
        int i = 0;//utilizaremos el ejemplo de conversion 0
        System.out.println("----------------------");
        conversion_provicional p = new conversion_provicional();
        p.conversion(i);//generamos el numero de ejemplo
        for (Estado e : p.getAutomata()){// se imprime el nuemero de ejemplo
            System.out.println(e);
        }
        System.out.println("-----------------------");
        for (Regla2 e : p.getGramatica()){// se imprime el nuemero de ejemplo
            System.out.println(e);
        }
         /*
        Estados_Validos[] t = a.getRecorrido();
        for(Estados_Validos e: t)  System.out.println(e);*/
    }  
}
