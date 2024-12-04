package BackEnd.GLC;

public class Prueba3 {
    public static void main (String args[]){
        //     Gramatica gr = new Gramatica();
        Gramatica3 gr = new Gramatica3()    ;

        gr.addProduccion("S","aSb" );
        gr.addProduccion("S","a" );

        gr.recorrido1("");
        //     gr.recorrido1("abababababa");
        gr.recorrido1("a");
        gr.recorrido1("aa");
        gr.recorrido1("aaa");
        gr.lista("ab");
         
        String[] validos = gr.validos ;
        String[] invalidos = gr.invalidos;
        System.out.println("Validos");
        for( String valor : validos){
            System.out.println(valor);
        }
        System.out.println("\n\n\nInvalidos");
        for( String valor : invalidos){
            System.out.println(valor);
        }

    }
}
