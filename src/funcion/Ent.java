/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package funcion;

/**
 *
 * @author erwin
 */
public class Ent {
    Ent sig = null;
    int numeroEstado;
    char lee;
    char escribe;
    char direccion;
  
    public Ent(int numeroEstado, char lee, char escribe, char direccion) {
      this.lee = lee;
      this.escribe = escribe;
      this.direccion = direccion;
      this.numeroEstado = numeroEstado;
    }
  }
