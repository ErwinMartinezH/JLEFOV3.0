package GraficoMTBD.vistaturing.utilidades.leercsv;

import java.io.*;
import java.util.*;

public class TuringMachine {
  // Conjunto de estados
  private Set<Integer> Q;
  // Estados iniciales
  private Set<Integer> q0;
  // Estados finales
  private Set<Integer> qf;
  // Alfabeto de la máquina
  private Set<String> alphabet;
  // Lista de transiciones
  private List<Transition> transitions;
  // Bandera para indicar si el alfabeto fue especificado por el usuario
  private boolean alphabetSpecified = false;

  public TuringMachine() {
    Q = new HashSet<>();
    q0 = new HashSet<>();
    qf = new HashSet<>();
    alphabet = new HashSet<>();
    transitions = new ArrayList<>();
  }

  public void loadMachine(String filename) {
    try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
      String line;
      while ((line = br.readLine()) != null) {
        line = line.trim();

        if (line.isEmpty()) {
          continue;
        }

        if (line.startsWith("Q =")) {
          Q = parseStateSet(line);
        } else if (line.startsWith("q0 =")) {
          q0 = parseStateSet(line);
        } else if (line.startsWith("qf =")) {
          qf = parseStateSet(line);
        } else if (line.startsWith("Sigma =")) {
          alphabet = parseSymbolSet(line);
          alphabetSpecified = true;
        } else if (line.contains("->")) {
          Transition transition = parseTransition(line);
          if (transition != null) {
            transitions.add(transition);
            Q.add(transition.q);
            Q.add(transition.p);
          }
        }
      }

      // Si el alfabeto no se especificó en el archivo, lo detectamos de las transiciones
      if (!alphabetSpecified) {
        detectAlphabetFromTransitions();
      }

    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  private Set<Integer> parseStateSet(String line) {
    Set<Integer> states = new HashSet<>();

    int start = line.indexOf('{');
    int end = line.indexOf('}');
    if (start != -1 && end != -1 && start < end) {
      String content = line.substring(start + 1, end).trim();
      String[] parts = content.split(",");
      for (String part : parts) {
        states.add(Integer.parseInt(part.trim()));
      }
    }
    return states;
  }

  private Set<String> parseSymbolSet(String line) {
    Set<String> symbols = new HashSet<>();

    int start = line.indexOf('{');
    int end = line.indexOf('}');
    if (start != -1 && end != -1 && start < end) {
      String content = line.substring(start + 1, end).trim();
      String[] parts = content.split(",");
      for (String part : parts) {
        symbols.add(part.trim());
      }
    }
    return symbols;
  }

  private Transition parseTransition(String line) {
    line = line.trim();
    if (line.isEmpty()) {
      return null;
    }

    String[] parts = line.split("->");
    if (parts.length != 2) {
      System.err.println("Línea inválida: " + line);
      return null;
    }

    String left = parts[0].trim();
    String right = parts[1].trim();

    left = left.replace("(", "").replace(")", "");
    String[] leftParts = left.split(",");
    if (leftParts.length != 2) {
      System.err.println("Parte izquierda inválida: " + left);
      return null;
    }

    int q = Integer.parseInt(leftParts[0].trim());
    String s = leftParts[1].trim();

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

  private void detectAlphabetFromTransitions() {
    for (Transition t : transitions) {
      // Ahora incluimos todos los símbolos, incluyendo "\0"
      alphabet.add(t.s);
      alphabet.add(t.x);
    }
  }

  public Set<Integer> getStates() {
    return Q;
  }

  public Set<Integer> getInitialStates() {
    return q0;
  }

  public Set<Integer> getFinalStates() {
    return qf;
  }

  public Set<String> getAlphabet() {
    return alphabet;
  }

  public List<Transition> getTransitions() {
    return transitions;
  }

  // Clase interna para representar una transición
  public class Transition {

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

    public int getQ() {
      return q;
    }

    public void setQ(int q) {
      this.q = q;
    }

    public String getS() {
      return s;
    }

    public void setS(String s) {
      this.s = s;
    }

    public int getP() {
      return p;
    }

    public void setP(int p) {
      this.p = p;
    }

    public String getX() {
      return x;
    }

    public void setX(String x) {
      this.x = x;
    }

    public String getD() {
      return D;
    }

    public void setD(String D) {
      this.D = D;
    }

    
    
    @Override
    public String toString() {
      return "(" + q + ", " + s + ") -> (" + p + ", " + x + ", " + D + ")";
    }
  }
}
