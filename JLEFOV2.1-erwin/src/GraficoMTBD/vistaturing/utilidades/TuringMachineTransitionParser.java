package GraficoMTBD.vistaturing.utilidades;

import java.io.*;
import java.util.*;

public class TuringMachineTransitionParser {

  public static void maina(String[] args) {
    // Nombre del archivo CSV
    String filename = "src/leercsv/transiciones.csv";

    // Lista para almacenar las transiciones
    List<Transition> transitions = new ArrayList<>();

    // Lectura del archivo
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        // Procesar cada línea
        Transition transition = parseTransition(line);
        if (transition != null) {
          transitions.add(transition);
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }

    // Imprimir las transiciones leídas
    for (Transition t : transitions) {
      System.out.println(t);
    }
  }

  // Método para analizar una línea y crear una instancia de Transition
  static Transition parseTransition(String line) {
    line = line.trim();
    if (line.isEmpty()) {
      return null;
    }

    // Dividir la línea en parte izquierda y derecha usando '->'
    String[] parts = line.split("->");
    if (parts.length != 2) {
      System.err.println("Línea inválida: " + line);
      return null;
    }

    String left = parts[0].trim();
    String right = parts[1].trim();

    // Procesar la parte izquierda (q, s)
    left = left.replace("(", "").replace(")", "");
    String[] leftParts = left.split(",");
    if (leftParts.length != 2) {
      System.err.println("Parte izquierda inválida: " + left);
      return null;
    }

    int q = Integer.parseInt(leftParts[0].trim());
    String s = leftParts[1].trim();

    // Procesar la parte derecha (p, x, D)
    right = right.replace("(", "").replace(")", "");
    String[] rightParts = right.split(",");
    if (rightParts.length != 3) {
      System.err.println("Parte derecha inválida: " + right);
      return null;
    }

    int p = Integer.parseInt(rightParts[0].trim());
    String x = rightParts[1].trim();
    String D = rightParts[2].trim();

    return new Transition(q, s, p, x, D);
  }
}

// Clase para representar una transición de la máquina de Turing
class Transition {

  int q;      // Estado actual
  String s;   // Símbolo actual
  int p;      // Estado siguiente
  String x;   // Símbolo a escribir
  String D;   // Dirección ('D' o 'I')

  public Transition(int q, String s, int p, String x, String D) {
    this.q = q;
    this.s = s;
    this.p = p;
    this.x = x;
    this.D = D;
  }

  @Override
  public String toString() {
    return "(" + q + ", " + s + ") -> (" + p + ", " + x + ", " + D + ")";
  }
}
