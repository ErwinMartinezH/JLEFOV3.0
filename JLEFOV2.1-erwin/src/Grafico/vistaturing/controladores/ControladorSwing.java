package Grafico.vistaturing.controladores;

import Grafico.vistaturing.recursos2D.RenderizadorTabla;
import Grafico.vistaturing.utilidades.Almacen;
import Grafico.vistaturing.utilidades.PasoAPaso;
import Grafico.vistaturing.utilidades.modelos.MT;
import Grafico.vistaturing.utilidades.modelos.MTContext;
import Grafico.vistaturing.utilidades.modelos.Simbolo;
import java.awt.Color;
import Grafico.vistaturing.estructurasdedatos.MatrizDinamica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import Grafico.vistaturing.recursos2D.Estado;
import Grafico.vistaturing.recursos2D.Letra;
import Grafico.vistaturing.recursos2D.Transicion;
import vista.TuringMachineSimulation;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class ControladorSwing implements ActionListener, ChangeListener {

  private TuringMachineSimulation vTS;
  private ArrayList<Almacen> almacen;
  private ArrayList<PasoAPaso> listaMovimientos;
  private int contadorPasoAPaso = 0;

  private boolean esUnaMaquinaDeTuringEstandar;

  private boolean esAceptada = false;

  public ControladorSwing(TuringMachineSimulation vTS) {
    this.vTS = vTS;
    this.listaMovimientos = null;
    this.vTS.getBotonDibujarMatriz().addActionListener(this);
    this.vTS.getBotonAceptar().addActionListener(this);
    this.vTS.getSpinnerCaracteres().addChangeListener(this);
    this.vTS.getSpinnerEstados().addChangeListener(this);
    this.vTS.getBotonAceptar1().addActionListener(this);
    this.vTS.getPasoAnterior().addActionListener(this);
    this.vTS.getPasoSiguiente().addActionListener(this);
    this.vTS.getGuardarDatosEnTabla().addActionListener(this);
  }

  public void eliminarReferencias() {
    this.vTS.getBotonDibujarMatriz().removeActionListener(this);
    this.vTS.getBotonAceptar().removeActionListener(this);
    this.vTS.getSpinnerCaracteres().removeChangeListener(this);
    this.vTS.getSpinnerEstados().removeChangeListener(this);
    this.vTS.getBotonAceptar1().removeActionListener(this);
    this.vTS.getPasoAnterior().removeActionListener(this);
    this.vTS.getPasoSiguiente().removeActionListener(this);
    this.vTS.getGuardarDatosEnTabla().removeActionListener(this);

  }

  @Override
  public void actionPerformed(ActionEvent e) {

    int xDesplazamiento = (int) this.vTS.getSpinnerCaracteres().getValue() * 100 <= 470 ? 470 - (int) this.vTS.getSpinnerCaracteres().getValue() * 100 : 0;

    if (e.getSource() == this.vTS.getBotonDibujarMatriz()) {

      JSpinner spinerEstados = this.vTS.getSpinnerEstados();
      JSpinner spinnerCaracteres = this.vTS.getSpinnerCaracteres();

      Grafico.vistaturing.estructurasdedatos.MatrizDinamica nuevaMatrizDinamica = new Grafico.vistaturing.estructurasdedatos.MatrizDinamica(0, 0, 100, 65, ((int) spinerEstados.getValue()), ((int) spinnerCaracteres.getValue()));
      nuevaMatrizDinamica.inicializarMatriz();
      this.vTS.getcM().setmD(nuevaMatrizDinamica);
      this.vTS.getcM().repaint();

    } else if (e.getSource() == this.vTS.getBotonAceptar()) {
      System.out.println("funcionando");
      //this.vTS.getVt().setCadena(this.vTS.getCajaCadena().getText());
      this.vTS.getContenedorCinta().getCintaTuring().cambiarCadena(this.vTS.getCajaCadena().getText());

    } else if (e.getSource() == this.vTS.getBotonAceptar1()) {

      this.listaMovimientos = new ArrayList<>();

      RenderizadorTabla rT = new RenderizadorTabla();
      //this.vTS.getTablaTransiciones().setModel(rT);

      MatrizDinamica mapa = this.vTS.getcM().getmD();

      MT mt = new MT();
      Estado estadoInicial = null;

      for (Map.Entry<Estado, ArrayList<Transicion>> entrada : mapa.entrySet()) {
        int estadoSinLetra = Integer.parseInt(entrada.getKey().getNombreEstado().substring(1, entrada.getKey().getNombreEstado().length()));

        if (entrada.getKey().isEsEstadoInicial()) {
          mt.setEstadoInicial(estadoSinLetra);
          estadoInicial = entrada.getKey();
        }

        mt.addEstado(estadoSinLetra);

        if (entrada.getKey().isEsEstadoFinal()) {
          mt.addEstadoAceptacion(estadoSinLetra);
        }

      }
      int legales = 0;
      for (Map.Entry<Estado, ArrayList<Transicion>> entrada : mapa.entrySet()) {

        //System.out.println("State : "+entrada.getKey().getNombreEstado());
        //System.out.println("Is final : "+entrada.getKey().isEsEstadoInicial());
        int estadoSinLetra = Integer.parseInt(entrada.getKey().getNombreEstado().substring(1, entrada.getKey().getNombreEstado().length()));

        int contadorLetras = 0;
        for (Transicion tr : entrada.getValue()) {
          //(GO TO, '3' ,)
          //System.out.println(tr.toString());

          String estado = tr.getEstado();
          Letra letra = tr.getLetra();
          String movimiento = tr.getMovimiento();

          if (!(estado.equals("NA") && movimiento.equals("NA"))) {
            System.out.println("existe una transicion " + tr.toString());

            int tempEstadoSinLetra = estadoSinLetra;
            char letraEnEstado = this.vTS.getcM().getmD().getListaDeLetrasColumnas().get(contadorLetras).getLetra().charAt(0);

            int tempEstadoSinLetra1 = Integer.parseInt(estado.substring(1, estado.length()));
            char letraEnEstado1 = letra.getLetra().charAt(0);
            MT.Direccion direccion = MT.Direccion.Izquierda;

            if (movimiento.equals("Derecha")) {
              direccion = MT.Direccion.Derecha;
            }

            System.out.println(tempEstadoSinLetra + "," + letraEnEstado + " = " + tempEstadoSinLetra1 + "," + letraEnEstado1 + "," + direccion);

            mt.addTransicion(tempEstadoSinLetra, letraEnEstado, tempEstadoSinLetra1, letraEnEstado1, direccion);

          }

          contadorLetras = contadorLetras + 1;

        }

      }

      int heapSpace = 200;

      ArrayList<Letra> listaEnlazadaSimbolosCinta = this.vTS.getContenedorCinta().getCintaTuring();
      String cadenaDeSimbolosDeLaLista = "";
      for (Letra letra : listaEnlazadaSimbolosCinta) {
        cadenaDeSimbolosDeLaLista += letra.getLetra();
      }

      this.almacen = new ArrayList<>();
      int movimientosLegales[] = {0};

      mt.ejecutar(cadenaDeSimbolosDeLaLista, ctx -> {

        if (almacen.size() >= heapSpace) {
          JOptionPane.showMessageDialog(this.vTS, "Problema demasiado grande o sin solución");
          throw new RuntimeException("Problema demasiado grande o sin solución");
        }

        if ((movimientosLegales[0]) <= -1) {

          if (esUnaMaquinaDeTuringEstandar) {
            JOptionPane.showMessageDialog(this.vTS, "Este problema requiere una MTB");
            throw new RuntimeException("Problema para una MTB");
          }
        }

        if (ctx.getAccion() == MTContext.Accion.CargarCadena) {
          this.almacen.add(new Almacen(ctx));
        }

        if (ctx.getAccion() == MTContext.Accion.CambiarEstado) {
          this.almacen.add(new Almacen(ctx));
        }

        if (ctx.getAccion() == MTContext.Accion.EditarSimbolo) {
          this.almacen.add(new Almacen(ctx));
        }

        if (ctx.getAccion() == MTContext.Accion.Aceptar) {
          esAceptada = true;
          this.almacen.add(new Almacen(ctx));
        }

        if (ctx.getAccion() == MTContext.Accion.Rechazar) {
          this.almacen.add(new Almacen(ctx));
        }

      });

//      System.out.println("**************************");
//      for (Almacen almacen : this.almacen) {
//        if (almacen.getAccion().getAccion() == MTContext.Accion.EditarSimbolo) {
//          System.out.println(almacen.toString());
//        }
//      }
      new HiloMatrizCinta(almacen, mapa).start();

    } else if (e.getSource() == this.vTS.getPasoSiguiente()) {

      if (this.listaMovimientos == null) {
        System.out.println("Primero debes hacer una evaluación");
      } else {

        for (Map.Entry<Estado, ArrayList<Transicion>> entrada : this.vTS.getcM().getmD().entrySet()) {

          entrada.getKey().setColor(Color.WHITE);
          for (Transicion tr : entrada.getValue()) {
            tr.setColor(Color.WHITE);
          }

        }
        this.vTS.getcM().repaint();

        if (this.listaMovimientos.size() >= this.contadorPasoAPaso + 1) {
          this.contadorPasoAPaso = this.contadorPasoAPaso + 1;

          if (this.listaMovimientos.get(this.contadorPasoAPaso).getDireccionDeMemoriaEstado() != null) {
            this.listaMovimientos.get(this.contadorPasoAPaso).getDireccionDeMemoriaEstado().setColor(Color.ORANGE);
            this.listaMovimientos.get(this.contadorPasoAPaso).getDireccionDeMemoriaTransicion().setColor(Color.CYAN);

          }

          this.vTS.getContenedorCinta().getCintaTuring().moverCabezal(this.listaMovimientos.get(this.contadorPasoAPaso).getPosicionCabezal());
          this.vTS.getContenedorCinta().getCintaTuring().cambiarCadena(this.listaMovimientos.get(this.contadorPasoAPaso).getCinta(), this.listaMovimientos.get(this.contadorPasoAPaso).getPosicionCabezal());

          this.vTS.getLabelPasoAPaso().setText("Paso : " + this.contadorPasoAPaso);

          this.vTS.getContenedorCinta().repaint();

        } else {
          JOptionPane.showMessageDialog(null, "Ya no hay más movimientos");
          this.contadorPasoAPaso = this.listaMovimientos.size() - 1;
        }

      }

    } else if (e.getSource() == this.vTS.getPasoAnterior()) {
      if (this.listaMovimientos == null) {
        System.out.println("Primero debes hacer una evaluación");
      } else {

        for (Map.Entry<Estado, ArrayList<Transicion>> entrada : this.vTS.getcM().getmD().entrySet()) {

          entrada.getKey().setColor(Color.WHITE);
          for (Transicion tr : entrada.getValue()) {
            tr.setColor(Color.WHITE);
          }

        }
        this.vTS.getcM().repaint();

        if (this.contadorPasoAPaso - 1 >= 0) {
          this.contadorPasoAPaso = this.contadorPasoAPaso - 1;

          if (this.listaMovimientos.get(this.contadorPasoAPaso).getDireccionDeMemoriaEstado() != null) {
            this.listaMovimientos.get(this.contadorPasoAPaso).getDireccionDeMemoriaEstado().setColor(Color.ORANGE);
            this.listaMovimientos.get(this.contadorPasoAPaso).getDireccionDeMemoriaTransicion().setColor(Color.CYAN);

          }

          this.vTS.getContenedorCinta().getCintaTuring().moverCabezal(this.listaMovimientos.get(this.contadorPasoAPaso).getPosicionCabezal());
          this.vTS.getContenedorCinta().getCintaTuring().cambiarCadena(this.listaMovimientos.get(this.contadorPasoAPaso).getCinta(), this.listaMovimientos.get(this.contadorPasoAPaso).getPosicionCabezal());

          this.vTS.getLabelPasoAPaso().setText("Paso : " + this.contadorPasoAPaso);
          this.vTS.getContenedorCinta().repaint();

        } else {
          JOptionPane.showMessageDialog(null, "Ya no hay más movimientos");
          this.contadorPasoAPaso = 0;
        }

      }

    } else if (e.getSource() == this.vTS.getGuardarDatosEnTabla()) {

      for (Map.Entry<Estado, ArrayList<Transicion>> entrada : this.vTS.getcM().getmD().entrySet()) {

        System.out.println(entrada.getKey().getNombreEstado());
        System.out.println(entrada.getKey().isEsEstadoInicial());
        System.out.println(entrada.getKey().isEsEstadoInicial());

        for (Transicion tr : entrada.getValue()) {

          System.out.println(tr.getEstado());
          System.out.println(tr.getLetra().getLetra());
          System.out.println(tr.getMovimiento());

        }

      }

    }

    this.vTS.getcM().repaint();
    //this.vTS.getVt().repaint();
    //this.vTS.setPuedoPasarElMouse(true);
  }

  @Override
  public void stateChanged(ChangeEvent e
  ) {
    int xDesplazamiento = (int) this.vTS.getSpinnerCaracteres().getValue() * 100 <= 470 ? 470 - (int) this.vTS.getSpinnerCaracteres().getValue() * 100 : 0;

    int numeroDeEstados = this.vTS.getcM().getmD().size();
    int numeroDeCaractares = this.vTS.getcM().getmD().getListaDeLetrasColumnas().size();

    int espinerEstados = (int) this.vTS.getSpinnerEstados().getValue();
    int espinerCaracteres = (int) this.vTS.getSpinnerCaracteres().getValue();

    if (numeroDeEstados == 0 || numeroDeCaractares == 0) {

      MatrizDinamica nuevaMatrizDinamica = new MatrizDinamica(0, 0, 100, 65, espinerEstados, espinerCaracteres);
      nuevaMatrizDinamica.inicializarMatriz();

    } else {

      if (espinerEstados < numeroDeEstados) {

        int diferencias = numeroDeEstados - espinerEstados;
        for (int i = 0; i < diferencias; i++) {
          this.vTS.getcM().getmD().quitarElUltimoEstado();

        }

      } else if (espinerEstados > numeroDeEstados) {

        int diferencias = espinerEstados - numeroDeEstados;

        for (int i = 0; i < diferencias; i++) {
          this.vTS.getcM().getmD().insertarNuevoEstado();

        }

      }

      if (espinerCaracteres < numeroDeCaractares) {

        int diferencias = numeroDeCaractares - espinerCaracteres;

        for (int i = 0; i < diferencias; i++) {
          this.vTS.getcM().getmD().eliminarUnCaracter();

        }

      } else if (espinerCaracteres > numeroDeCaractares) {

        int diferencias = espinerCaracteres - numeroDeCaractares;

        for (int i = 0; i < diferencias; i++) {
          this.vTS.getcM().getmD().insertarUnNuevoCaracter();

        }

      }

      this.vTS.getcM().getmD().reacondicionarMatriz(0, 0, 100, 65);

    }
    this.vTS.getPanelDibujo().repaint();
  }

  public boolean isEsUnaMaquinaDeTuringEstandar() {
    return esUnaMaquinaDeTuringEstandar;
  }

  public void setEsUnaMaquinaDeTuringEstandar(boolean esUnaMaquinaDeTuringEstandar) {
    this.esUnaMaquinaDeTuringEstandar = esUnaMaquinaDeTuringEstandar;
  }

  private class HiloMatrizCinta extends Thread {

    private ArrayList<Almacen> almacen;
    private MatrizDinamica mapa;

    private HiloMatrizCinta(ArrayList<Almacen> almacen, MatrizDinamica mapa) {
      this.almacen = almacen;
      this.mapa = mapa;
       vTS.getBotonAceptar().setEnabled(false);
       vTS.getBotonAceptar().setVisible(false);
    }

    @Override
    public void run() {
     
      vTS.getContenedorCinta().getCintaTuring().minimo = 0;
      for (int i = 0; i < this.almacen.size(); i = i + 1) {
        Almacen a = this.almacen.get(i);

        if (a.getAccion().getAccion() == MTContext.Accion.EditarSimbolo) {

          int estadoActual = a.getAccion().getInfo().get(0).getValor();
          Simbolo simboloLeido = a.getAccion().getInfo().get(1).getValor();

          int estadoAIr = a.getAccion().getInfo().get(2).getValor();
          Simbolo simboloEscrito = a.getAccion().getInfo().get(3).getValor();
          MT.Direccion direccionCinta = a.getAccion().getInfo().get(4).getValor();

          var estado = vTS.getcM().getmD().direccionDeMemoriaDelEstadoEn(estadoActual);
          estado.setColor(Color.ORANGE);

          char caracterLeido = simboloLeido.toString().charAt(1);
          var listaLetras = vTS.getcM().getmD().getListaDeLetrasColumnas();

          if (simboloLeido.esBlanco()) {
            caracterLeido = '\0';
          }

//          vTS.getPanelInteligente().estado = "q" + estadoActual;
//          vTS.getPanelInteligente().leer = String.valueOf(caracterLeido);
//
//          vTS.getPanelInteligente().ir = "q" + estadoAIr;
//          vTS.getPanelInteligente().movimiento = String.valueOf(simboloEscrito.toString().charAt(1));
//          vTS.getPanelInteligente().operacion = "I";
//
//          if (direccionCinta.toString().contains("Derecha")) {
//            vTS.getPanelInteligente().operacion = "D";
//          }
//
//          vTS.getPanelInteligente().repaint();
          int posX = 0;
          for (Letra letra : listaLetras) {

            if (letra.getLetra().charAt(0) == caracterLeido) {

              var transicionDireccionDeMemoria = mapa.get(estado).get(posX);

              transicionDireccionDeMemoria.setColor(Color.CYAN);

              break;

            }
            posX = posX + 1;

          }

          ArrayList<String> cadenas = new ArrayList<>();
          var listaSimbolosCinta = a.getAccion().getMT().getNuevo().getCinta();
          var cabezal = a.getAccion().getMT().getViejo().getCinta().getPosicion();

          for (Simbolo simbolo : listaSimbolosCinta) {
            System.out.println("simbolos " + simbolo.toString());

            if (simbolo.esBlanco()) {

              cadenas.add(String.valueOf('\0'));
              System.out.println("hay blanco ");

            } else {
              cadenas.add(String.valueOf(simbolo.toString().charAt(1)));
            }
          }

          vTS.getContenedorCinta().getCintaTuring().cambiarCadena(cadenas, cabezal, 0, esAceptada);
          vTS.getcM().repaint();

          try {
            Thread.sleep(1000);
          } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
          }

          for (Map.Entry<Estado, ArrayList<Transicion>> entrada : mapa.entrySet()) {

            entrada.getKey().setColor(Color.WHITE);
            for (Transicion tr : entrada.getValue()) {
              tr.setColor(Color.WHITE);
            }

          }
          vTS.getcM().repaint();

          try {
            Thread.sleep(2000);
          } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
          }
          System.out.println("************");
          System.out.println("Actual " + estadoActual);
          System.out.println("simbolo leido " + simboloLeido);
          System.out.println("estadoAIr " + estadoAIr);
          System.out.println("simbolo escrito " + simboloEscrito);
          System.out.println("direccion cinta " + direccionCinta);

          System.out.println("Cinta " + a.getAccion().getMT().getNuevo().getCinta());
          System.out.println("Cabezal " + a.getAccion().getMT().getViejo().getCinta().getPosicion());

          System.out.println("****************++");

        } else if (a.getAccion().getAccion() == MTContext.Accion.Rechazar) {

          ArrayList<String> cadenas = new ArrayList<>();
          var listaSimbolosCinta = a.getAccion().getMT().getNuevo().getCinta();
          var cabezal = a.getAccion().getMT().getViejo().getCinta().getPosicion();

          for (Simbolo simbolo : listaSimbolosCinta) {
            System.out.println("simbolos " + simbolo.toString());

            if (simbolo.esBlanco()) {

              cadenas.add(String.valueOf('\0'));
              System.out.println("hay blanco ");

            } else {
              cadenas.add(String.valueOf(simbolo.toString().charAt(1)));
            }
          }

          vTS.getContenedorCinta().getCintaTuring().cambiarCadena(cadenas, cabezal, 0, esAceptada);
          vTS.getcM().repaint();

          try {
            Thread.sleep(1000);
          } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
          }

          for (Map.Entry<Estado, ArrayList<Transicion>> entrada : mapa.entrySet()) {

            entrada.getKey().setColor(Color.WHITE);
            for (Transicion tr : entrada.getValue()) {
              tr.setColor(Color.WHITE);
            }

          }
          vTS.getcM().repaint();

          System.out.println("Cinta " + a.getAccion().getMT().getNuevo().getCinta());
          System.out.println("Cabezal " + a.getAccion().getMT().getViejo().getCinta().getPosicion());

          System.out.println("****************++");

          JOptionPane.showMessageDialog(null, "Cadena rechazada");

        } else if (a.getAccion().getAccion() == MTContext.Accion.Aceptar) {
          System.out.println("lllleggueeee aun estado finalll");
          ArrayList<String> cadenas = new ArrayList<>();
          var listaSimbolosCinta = a.getAccion().getMT().getNuevo().getCinta();
          var cabezal = a.getAccion().getMT().getViejo().getCinta().getPosicion();

          for (Simbolo simbolo : listaSimbolosCinta) {
            System.out.println("simbolos " + simbolo.toString());

            if (simbolo.esBlanco()) {

              cadenas.add(String.valueOf('\0'));
              System.out.println("hay blanco ");

            } else {
              cadenas.add(String.valueOf(simbolo.toString().charAt(1)));
            }
          }

          vTS.getContenedorCinta().getCintaTuring().cambiarCadena(cadenas, cabezal, 0, esAceptada);
          vTS.getcM().repaint();

          try {
            Thread.sleep(1000);
          } catch (InterruptedException ex) {
            System.out.println(ex.getMessage());
          }

          for (Map.Entry<Estado, ArrayList<Transicion>> entrada : mapa.entrySet()) {

            entrada.getKey().setColor(Color.WHITE);
            for (Transicion tr : entrada.getValue()) {
              tr.setColor(Color.WHITE);
            }

          }
          vTS.getcM().repaint();

          System.out.println("Cinta " + a.getAccion().getMT().getNuevo().getCinta());
          System.out.println("Cabezal " + a.getAccion().getMT().getViejo().getCinta().getPosicion());

          System.out.println("****************++");

          JOptionPane.showMessageDialog(null, "Cadena aceptada");

        }

      }
      esAceptada = false;
      
      vTS.getBotonAceptar().setEnabled(true);

    }

  }

}
