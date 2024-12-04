/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package HerramientaADP;

/**
 *
 * @author pedro
 */
public class Estados_Validos {
    public int origen, destino;
    public char input, pop, push;

    public Estados_Validos(int orgien, char input, char pop, char push, int destino) {
        this.origen = orgien;
        this.input = input;
        this.pop = pop;
        this.push = push;
        this.destino = destino;
    }
    
    @Override
    public String toString(){
        return (String)(origen +"") + " " + input + " " + pop + " " + push + " " +(destino+"");
    }
    
}
