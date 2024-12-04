package GraficoMTBD.vistaturing.controladores;

import GraficoMTBD.vistaturing.recursos2D.RenderizadorTabla;
import GraficoMTBD.vistaturing.utilidades.Almacen;
import GraficoMTBD.vistaturing.utilidades.PasoAPaso;
import GraficoMTBD.vistaturing.utilidades.modelos.MT;
import GraficoMTBD.vistaturing.utilidades.modelos.MTContext;
import GraficoMTBD.vistaturing.utilidades.modelos.Simbolo;
import java.awt.Color;
import GraficoMTBD.vistaturing.estructurasdedatos.MatrizDinamica;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Map;
import javax.swing.JOptionPane;
import javax.swing.JSpinner;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import vista.TuringBidireccional;
import GraficoMTBD.vistaturing.recursos2D.Estado;
import GraficoMTBD.vistaturing.recursos2D.Letra;
import GraficoMTBD.vistaturing.recursos2D.Transicion;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

/**
 *
 * @author Palacios López Javier Alberto
 */
public class ControladorSwing implements ActionListener, ChangeListener {

  private TuringBidireccional vTS;
  private ArrayList<Almacen> almacen;
  private ArrayList<PasoAPaso> listaMovimientos;
  private int contadorPasoAPaso = 0;

  private boolean esUnaMaquinaDeTuringEstandar;

  private boolean esAceptada = false;

  private int velocidadMS = 500;

  private MouseListener respaldoEventoListenerMousePanel[];
  private MouseMotionListener respaldoMouseMotionListenerPanel[];

  public ControladorSwing(TuringBidireccional vTS) {
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
    this.vTS.getVelocidadDeSeguimiento().addActionListener(this);

    this.respaldoEventoListenerMousePanel = this.vTS.getPanelDibujo().getMouseListeners();
    this.respaldoMouseMotionListenerPanel = this.vTS.getPanelDibujo().getMouseMotionListeners();
    this.vTS.getcM().direccionDeMemoriaScrollPane = this.vTS.getjScrollPane1();

    this.vTS.getjScrollPane1().getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
      @Override
      public void adjustmentValueChanged(AdjustmentEvent e) {
        if (e.getValueIsAdjusting()) {
          int x = vTS.getcM().getmD().getListaDeLetrasColumnas().get(0).getAncho();
          for (var punteroLetra : vTS.getcM().getmD().getListaDeLetrasColumnas()) {

            punteroLetra.setY(0);
            punteroLetra.setX(x);

            x = x + punteroLetra.getAncho();

          }

        }
      }
    });

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
    this.vTS.getVelocidadDeSeguimiento().removeActionListener(this);
    var f = this.vTS.getjScrollPane1().getVerticalScrollBar().getAdjustmentListeners();
    for(var k : f){
      this.vTS.getjScrollPane1().getVerticalScrollBar().removeAdjustmentListener(k);
    }
    
  }

  private class PasoAPasoA {

    private final MTContext turingContexto;
    public static int CONTADOR = 0;
    private static int saltoOffset = 0;

    private int offset;

    public PasoAPasoA(MTContext turingContexto) {
      this.turingContexto = turingContexto;
    }

    public MTContext getTuringContexto() {
      return turingContexto;
    }

    public void setOffset(int offset) {
      this.offset = offset;
    }

    public int getOffset() {
      return offset;
    }

  }

  private ArrayList<PasoAPasoA> listaDePasos;

  @Override
  public void actionPerformed(ActionEvent e) {

    int xDesplazamiento = (int) this.vTS.getSpinnerCaracteres().getValue() * 100 <= 470 ? 470 - (int) this.vTS.getSpinnerCaracteres().getValue() * 100 : 0;

    if (e.getSource() == this.vTS.getBotonDibujarMatriz()) {

      JSpinner spinerEstados = this.vTS.getSpinnerEstados();
      JSpinner spinnerCaracteres = this.vTS.getSpinnerCaracteres();

      MatrizDinamica nuevaMatrizDinamica = new MatrizDinamica(0, 0, 100, 65, ((int) spinerEstados.getValue()), ((int) spinnerCaracteres.getValue()));
      nuevaMatrizDinamica.inicializarMatriz();
      this.vTS.getcM().setmD(nuevaMatrizDinamica);
      this.vTS.getcM().repaint();

    } else if (e.getSource() == this.vTS.getVelocidadDeSeguimiento()) {
      this.velocidadMS = Integer.parseInt(this.vTS.getVelocidadDeSeguimiento().getSelectedItem().toString());
    } else if (e.getSource() == this.vTS.getBotonAceptar()) {
      System.out.println("funcionando");
      int x = this.vTS.getcM().getmD().getListaDeLetrasColumnas().get(0).getAncho();
      for (var punteroLetra : this.vTS.getcM().getmD().getListaDeLetrasColumnas()) {

        punteroLetra.setY(0);
        punteroLetra.setX(x);

        x = x + punteroLetra.getAncho();

      }

      vTS.getjScrollPane1().getVerticalScrollBar().setValue(0);

      for (var evt : this.respaldoEventoListenerMousePanel) {
        this.vTS.getPanelDibujo().removeMouseListener(evt);
      }

      for (var evt : this.respaldoMouseMotionListenerPanel) {
        this.vTS.getPanelDibujo().removeMouseMotionListener(evt);
      }

      for (var evt : this.respaldoEventoListenerMousePanel) {
        this.vTS.getPanelDibujo().addMouseListener(evt);
      }

      for (var evt : this.respaldoMouseMotionListenerPanel) {
        this.vTS.getPanelDibujo().addMouseMotionListener(evt);
      }

      //this.vTS.getVt().setCadena(this.vTS.getCajaCadena().getText());
      this.vTS.getContenedorCinta().getCintaTuring().cambiarCadena(this.vTS.getCajaCadena().getText());

    } else if (e.getSource() == this.vTS.getBotonAceptar1()) {
      PasoAPasoA.saltoOffset = 0;

      for (var evt : this.respaldoEventoListenerMousePanel) {
        this.vTS.getPanelDibujo().removeMouseListener(evt);
      }

      for (var evt : this.respaldoMouseMotionListenerPanel) {
        this.vTS.getPanelDibujo().removeMouseMotionListener(evt);
      }

      PasoAPasoA.CONTADOR = 0;
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

            if (movimiento.equals("D")) {
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

      listaDePasos = new ArrayList<>();

      mt.ejecutar(cadenaDeSimbolosDeLaLista, ctx -> {

        if (almacen.size() >= heapSpace) {
          JOptionPane.showMessageDialog(this.vTS, "Problema demasiado grande o sin solución");
          throw new RuntimeException("Problema demasiado grande o sin solución");
        }

        if ((ctx.getMT().getViejo().getCinta().getPos()) <= -1) {

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

          PasoAPasoA tempPaso = new PasoAPasoA(ctx);
          System.out.println("EDITAR SIMBOLO " + ctx.getMT().getViejo().getCinta().getPosicion());

          if (ctx.getMT().getViejo().getCinta().getPosicion() < PasoAPasoA.saltoOffset) {
            tempPaso.setOffset(ctx.getMT().getViejo().getCinta().getPosicion());
            PasoAPasoA.saltoOffset = ctx.getMT().getViejo().getCinta().getPosicion();
            System.out.println("cambios " + ctx.getMT().getViejo().getCinta().getPosicion());

          } else {
            tempPaso.setOffset(PasoAPasoA.saltoOffset);
          }

          listaDePasos.add(tempPaso);
        }

        if (ctx.getAccion() == MTContext.Accion.Aceptar) {
          esAceptada = true;
          this.almacen.add(new Almacen(ctx));

          PasoAPasoA tempPaso = new PasoAPasoA(ctx);
          System.out.println("ACEPTAR " + ctx.getMT().getViejo().getCinta().getPosicion());

          if (ctx.getMT().getViejo().getCinta().getPosicion() < PasoAPasoA.saltoOffset) {
            tempPaso.setOffset(ctx.getMT().getViejo().getCinta().getPosicion());
            PasoAPasoA.saltoOffset = ctx.getMT().getViejo().getCinta().getPosicion();
            System.out.println("cambios " + ctx.getMT().getViejo().getCinta().getPosicion());
          } else {
            tempPaso.setOffset(PasoAPasoA.saltoOffset);
          }

          listaDePasos.add(tempPaso);

        }

        if (ctx.getAccion() == MTContext.Accion.Rechazar) {
          this.almacen.add(new Almacen(ctx));
          System.out.println("ACEPTAR " + ctx.getMT().getViejo().getCinta().getPosicion());

          PasoAPasoA tempPaso = new PasoAPasoA(ctx);

          if (ctx.getMT().getViejo().getCinta().getPosicion() < PasoAPasoA.saltoOffset) {
            tempPaso.setOffset(ctx.getMT().getViejo().getCinta().getPosicion());
            PasoAPasoA.saltoOffset = ctx.getMT().getViejo().getCinta().getPosicion();
            System.out.println("cambios " + ctx.getMT().getViejo().getCinta().getPosicion());

          } else {
            tempPaso.setOffset(PasoAPasoA.saltoOffset);
          }

          listaDePasos.add(tempPaso);

        }

      });

      System.out.println("**************************");
      for (Almacen almacen : this.almacen) {
        if (almacen.getAccion().getAccion() == MTContext.Accion.EditarSimbolo) {
          System.out.println(almacen.toString());
        }
      }

      new HiloMatrizCinta(almacen, mapa).start();

    } else if (e.getSource() == this.vTS.getPasoSiguiente()) {

//this.listaDePasos;
      if (this.esUnaMaquinaDeTuringEstandar) {
        PasoAPasoA.CONTADOR = PasoAPasoA.CONTADOR + 1;
        for (Map.Entry<Estado, ArrayList<Transicion>> entrada : this.vTS.getcM().getmD().entrySet()) {
          entrada.getKey().setColor(Color.WHITE);
          for (Transicion tr : entrada.getValue()) {
            tr.setColor(Color.WHITE);
          }

        }

        vTS.getcM().repaint();

        if (this.listaDePasos != null && PasoAPasoA.CONTADOR < this.listaDePasos.size()) {

          for (var evt : this.respaldoEventoListenerMousePanel) {
            this.vTS.getPanelDibujo().removeMouseListener(evt);
          }

          for (var evt : this.respaldoMouseMotionListenerPanel) {
            this.vTS.getPanelDibujo().removeMouseMotionListener(evt);
          }

          System.out.println("Paso siguiente " + PasoAPasoA.CONTADOR);
          this.vTS.getLabelPasoAPaso().setText("Paso :" + PasoAPasoA.CONTADOR);
          var lista = this.listaDePasos.get(PasoAPasoA.CONTADOR);
          ArrayList<String> cadenas = new ArrayList<>();
          var a = lista.turingContexto;
          rutina(a);

          var listaSimbolosCinta = a.getMT().getNuevo().getCinta();
          var cabezal = a.getMT().getViejo().getCinta().getPosicion();
          System.out.println("CABEZALL************* POSITIVO " + cabezal);

          for (Simbolo simbolo : listaSimbolosCinta) {
            System.out.println("simbolos " + simbolo.toString());

            if (simbolo.esBlanco()) {

              cadenas.add(String.valueOf('\0'));
              System.out.println("hay blanco ");

            } else {
              cadenas.add(String.valueOf(simbolo.toString().charAt(1)));
            }
          }
          System.out.println("cadenas positivo " + cadenas.toString());

          vTS.getContenedorCinta().getCintaTuring().cambiarCadena(cadenas, cabezal, a.getMT().getViejo().getCinta().getStartPos(), esAceptada);
          vTS.getcM().repaint();

        } else {
          if (this.listaDePasos != null) {
            System.out.println("tamaño lista " + this.listaDePasos.size());
            PasoAPasoA.CONTADOR = this.listaDePasos.size() - 1;

          }
        }
      } else {
        System.out.println("trabajando bidireccional");

        PasoAPasoA.CONTADOR = PasoAPasoA.CONTADOR + 1;
        for (Map.Entry<Estado, ArrayList<Transicion>> entrada : this.vTS.getcM().getmD().entrySet()) {
          entrada.getKey().setColor(Color.WHITE);
          for (Transicion tr : entrada.getValue()) {
            tr.setColor(Color.WHITE);
          }

        }

        vTS.getcM().repaint();

        if (this.listaDePasos != null && PasoAPasoA.CONTADOR < this.listaDePasos.size()) {

          for (var evt : this.respaldoEventoListenerMousePanel) {
            this.vTS.getPanelDibujo().removeMouseListener(evt);
          }

          for (var evt : this.respaldoMouseMotionListenerPanel) {
            this.vTS.getPanelDibujo().removeMouseMotionListener(evt);
          }

          System.out.println("Paso siguiente " + PasoAPasoA.CONTADOR);
          this.vTS.getLabelPasoAPaso().setText("Paso :" + PasoAPasoA.CONTADOR);
          var lista = this.listaDePasos.get(PasoAPasoA.CONTADOR);
          ArrayList<String> cadenas = new ArrayList<>();
          var a = lista.turingContexto;
          rutina(a);

          var listaSimbolosCinta = a.getMT().getNuevo().getCinta();
          var cabezal = a.getMT().getViejo().getCinta().getPosicion();
          System.out.println("CABEZALL************* POSITIVO " + cabezal);

          for (Simbolo simbolo : listaSimbolosCinta) {
            System.out.println("simbolos " + simbolo.toString());

            if (simbolo.esBlanco()) {

              cadenas.add(String.valueOf('\0'));
              System.out.println("hay blanco ");

            } else {
              cadenas.add(String.valueOf(simbolo.toString().charAt(1)));
            }
          }
          System.out.println("cadenas positivo " + cadenas.toString());

          //vTS.getContenedorCinta().getCintaTuring().cambiarCadena(cadenas, cabezal, a.getMT().getViejo().getCinta().getStartPos(), esAceptada);
          //vTS.getContenedorCinta().getCintaTuring().agregarCadenaConMetodoAux(cadenas, cabezal);
          System.out.println("el offseet de la cinta es " + lista.getOffset());
          System.out.println("NEXT PASOOOO " + a.getMT().getViejo().getCinta().getStartPos());

          System.out.println("POSICION INICIALL");

          this.vTS.getContenedorCinta().getCintaTuring().agregarCadenaConMetodoAux(cadenas, cabezal, Math.abs(lista.getOffset()));
          vTS.getcM().repaint();

        } else {
          if (this.listaDePasos != null) {
            System.out.println("tamaño lista " + this.listaDePasos.size());
            PasoAPasoA.CONTADOR = this.listaDePasos.size() - 1;

          }
        }

      }

    } else if (e.getSource() == this.vTS.getPasoAnterior()) {
      if (esUnaMaquinaDeTuringEstandar) {
        for (Map.Entry<Estado, ArrayList<Transicion>> entrada : this.vTS.getcM().getmD().entrySet()) {
          entrada.getKey().setColor(Color.WHITE);
          for (Transicion tr : entrada.getValue()) {
            tr.setColor(Color.WHITE);
          }
        }
        vTS.getcM().repaint();
        PasoAPasoA.CONTADOR = PasoAPasoA.CONTADOR - 1;

        if (this.listaDePasos != null && PasoAPasoA.CONTADOR >= 0) {
          for (var evt : this.respaldoEventoListenerMousePanel) {
            this.vTS.getPanelDibujo().removeMouseListener(evt);
          }

          for (var evt : this.respaldoMouseMotionListenerPanel) {
            this.vTS.getPanelDibujo().removeMouseMotionListener(evt);
          }
          System.out.println("paso anterior " + PasoAPasoA.CONTADOR);
          this.vTS.getLabelPasoAPaso().setText("Paso :" + PasoAPasoA.CONTADOR);

          var lista = this.listaDePasos.get(PasoAPasoA.CONTADOR);
          System.out.println("en linea decremento");
          ArrayList<String> cadenas = new ArrayList<>();
          var a = lista.turingContexto;
          rutina(a);

          var listaSimbolosCinta = a.getMT().getNuevo().getCinta();
          var cabezal = a.getMT().getViejo().getCinta().getPosicion();

          System.out.println("CABEZALL************* NEGATIVO " + cabezal);

          for (Simbolo simbolo : listaSimbolosCinta) {
            System.out.println("simbolos " + simbolo.toString());

            if (simbolo.esBlanco()) {

              cadenas.add(String.valueOf('\0'));
              System.out.println("hay blanco ");

            } else {
              cadenas.add(String.valueOf(simbolo.toString().charAt(1)));
            }
          }
          System.out.println("cadenas negativo " + cadenas.toString());

          vTS.getContenedorCinta().getCintaTuring().cambiarCadena(cadenas, cabezal, a.getMT().getViejo().getCinta().getStartPos(), esAceptada);
          vTS.getcM().repaint();
        } else {
          PasoAPasoA.CONTADOR = 0;
        }
      } else {
        System.out.println("trabajando bidireccional");

        for (Map.Entry<Estado, ArrayList<Transicion>> entrada : this.vTS.getcM().getmD().entrySet()) {
          entrada.getKey().setColor(Color.WHITE);
          for (Transicion tr : entrada.getValue()) {
            tr.setColor(Color.WHITE);
          }
        }
        vTS.getcM().repaint();
        PasoAPasoA.CONTADOR = PasoAPasoA.CONTADOR - 1;

        if (this.listaDePasos != null && PasoAPasoA.CONTADOR >= 0) {
          for (var evt : this.respaldoEventoListenerMousePanel) {
            this.vTS.getPanelDibujo().removeMouseListener(evt);
          }

          for (var evt : this.respaldoMouseMotionListenerPanel) {
            this.vTS.getPanelDibujo().removeMouseMotionListener(evt);
          }
          System.out.println("paso anterior " + PasoAPasoA.CONTADOR);
          this.vTS.getLabelPasoAPaso().setText("Paso :" + PasoAPasoA.CONTADOR);

          var lista = this.listaDePasos.get(PasoAPasoA.CONTADOR);
          System.out.println("en linea decremento");
          ArrayList<String> cadenas = new ArrayList<>();
          var a = lista.turingContexto;
          rutina(a);

          var listaSimbolosCinta = a.getMT().getNuevo().getCinta();
          var cabezal = a.getMT().getViejo().getCinta().getPosicion();

          System.out.println("CABEZALL************* NEGATIVO " + cabezal);

          for (Simbolo simbolo : listaSimbolosCinta) {
            System.out.println("simbolos " + simbolo.toString());

            if (simbolo.esBlanco()) {

              cadenas.add(String.valueOf('\0'));
              System.out.println("hay blanco ");

            } else {
              cadenas.add(String.valueOf(simbolo.toString().charAt(1)));
            }
          }
          System.out.println("cadenas negativo " + cadenas.toString());
          System.out.println("Previous PASOOOO " + a.getMT().getViejo().getCinta().getStartPos());

          //vTS.getContenedorCinta().getCintaTuring().cambiarCadena(cadenas, cabezal, a.getMT().getViejo().getCinta().getStartPos(), esAceptada);
          //vTS.getContenedorCinta().getCintaTuring().agregarCadenaConMetodoAux(cadenas, cabezal);
          System.out.println("el offseet de la cinta es " + lista.getOffset());

          this.vTS.getContenedorCinta().getCintaTuring().agregarCadenaConMetodoAux(cadenas, cabezal, Math.abs(lista.getOffset()));

          vTS.getcM().repaint();
        } else {
          PasoAPasoA.CONTADOR = 0;
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

  public void rutina(MTContext a) {
    int estadoActual = a.getInfo().get(0).getValor();
    Simbolo simboloLeido = a.getInfo().get(1).getValor();
    int estadoAIr = a.getInfo().get(2).getValor();
    Simbolo simboloEscrito = a.getInfo().get(3).getValor();
    MT.Direccion direccionCinta = a.getInfo().get(4).getValor();

    var estado = vTS.getcM().getmD().direccionDeMemoriaDelEstadoEn(estadoActual);
    estado.setColor(Color.ORANGE);

    char caracterLeido = simboloLeido.toString().charAt(1);
    var listaLetras = vTS.getcM().getmD().getListaDeLetrasColumnas();

    if (simboloLeido.esBlanco()) {
      caracterLeido = '\0';
    }

    vTS.getPanelInteligente().estado = "q" + estadoActual;
    vTS.getPanelInteligente().leer = String.valueOf(caracterLeido);

    vTS.getPanelInteligente().ir = "q" + estadoAIr;
    vTS.getPanelInteligente().movimiento = String.valueOf((simboloEscrito.esBlanco()) ? '\0' : simboloEscrito.toString().charAt(1));
    vTS.getPanelInteligente().operacion = "I";

    if (direccionCinta.toString().contains("D")) {
      vTS.getPanelInteligente().operacion = "D";
    }

    vTS.getPanelInteligente().repaint();

    int posX = 0;
    for (Letra letra : listaLetras) {

      if (letra.getLetra().charAt(0) == caracterLeido) {

        var transicionDireccionDeMemoria = this.vTS.getcM().getmD().get(estado).get(posX);

        transicionDireccionDeMemoria.setColor(Color.CYAN);
        vTS.getjScrollPane1().getVerticalScrollBar().setValue(transicionDireccionDeMemoria.getY() - transicionDireccionDeMemoria.getLargo() * 2);

        desplazamientosDibujos(transicionDireccionDeMemoria.getY() - transicionDireccionDeMemoria.getLargo());
        break;

      }
      posX = posX + 1;

    }
  }

  public void desplazamientosDibujos(int yOffset) {

    boolean bandera = false;

    if (this.vTS.getjScrollPane1() != null) {
      System.out.println("entered");

      int y = yOffset;
      int x = this.vTS.getcM().getmD().getListaDeLetrasColumnas().get(0).getX();

      for (var punteroLetra : this.vTS.getcM().getmD().getListaDeLetrasColumnas()) {

        if (vTS.getjScrollPane1().getVerticalScrollBar().getValue() <= punteroLetra.getLargo() - 10) {
          bandera = true;
          punteroLetra.setY(0);
        } else {
          punteroLetra.setY(y);

        }
        // Sumar la posición del scroll pane a la posición x
        punteroLetra.setX(x);

        x = x + punteroLetra.getAncho();

      }

    }

    vTS.getjScrollPane1().getVerticalScrollBar().setValue(vTS.getjScrollPane1().getVerticalScrollBar().getValue() + this.vTS.getcM().getmD().getListaDeLetrasColumnas().get(0).getLargo());

    if (bandera) {
      vTS.getjScrollPane1().getVerticalScrollBar().setValue(-100);

    }

    this.vTS.getjScrollPane1().revalidate();

    this.vTS.getjScrollPane1().repaint();

    this.vTS.getcM().repaint();
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
    }

    @Override
    public void run() {
      
      vTS.getPasoAnterior().setEnabled(false);
      vTS.getPasoAnterior().setBackground(Color.RED);
      
      vTS.getPasoSiguiente().setEnabled(false);
      vTS.getPasoSiguiente().setBackground(Color.RED);
      
      vTS.getBotonAceptar().setEnabled(false);
      vTS.getBotonAceptar().setBackground(Color.RED);
      
      vTS.getBotonAceptar1().setEnabled(false);
      vTS.getBotonAceptar1().setBackground(Color.RED);
      
      vTS.getBotonDibujarMatriz().setEnabled(false);
      vTS.getBotonDibujarMatriz().setBackground(Color.RED);
      
      vTS.getGuardarDatosEnTabla().setEnabled(false);
      vTS.getGuardarDatosEnTabla().setBackground(Color.RED);

      vTS.getAbrirDatosEnTabla().setEnabled(false);
      vTS.getAbrirDatosEnTabla().setBackground(Color.RED);
      
      vTS.getSpinnerEstados().setEnabled(false);
      vTS.getSpinnerCaracteres().setEnabled(false);

      
      boolean[] ejecutar = {true};
      ActionListener listener = new ActionListener() {
        @Override
        public void actionPerformed(ActionEvent e) {

          for (var evt : respaldoEventoListenerMousePanel) {
            vTS.getPanelDibujo().addMouseListener(evt);
          }

          for (var evt : respaldoMouseMotionListenerPanel) {
            vTS.getPanelDibujo().addMouseMotionListener(evt);
          }

          ejecutar[0] = false;
          throw new RuntimeException("Analisis detenido");
        }
      };

      vTS.getDetenerAnalisis().addActionListener(listener);

      vTS.getContenedorCinta().getCintaTuring().minimo = 0;
      for (int i = 0; i < this.almacen.size() && ejecutar[0]; i = i + 1) {
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

          vTS.getPanelInteligente().estado = "q" + estadoActual;
          vTS.getPanelInteligente().leer = String.valueOf(caracterLeido);

          vTS.getPanelInteligente().ir = "q" + estadoAIr;
          vTS.getPanelInteligente().movimiento = String.valueOf((simboloEscrito.esBlanco()) ? '\0' : simboloEscrito.toString().charAt(1));
          vTS.getPanelInteligente().operacion = "I";

          if (direccionCinta.toString().contains("D")) {
            vTS.getPanelInteligente().operacion = "D";
          }

          vTS.getPanelInteligente().repaint();

          int posX = 0;
          for (Letra letra : listaLetras) {

            if (letra.getLetra().charAt(0) == caracterLeido) {

              var transicionDireccionDeMemoria = mapa.get(estado).get(posX);

              transicionDireccionDeMemoria.setColor(Color.CYAN);

              vTS.getjScrollPane1().getVerticalScrollBar().setValue(transicionDireccionDeMemoria.getY() - transicionDireccionDeMemoria.getLargo() * 2);
              desplazamientosDibujos(transicionDireccionDeMemoria.getY() - transicionDireccionDeMemoria.getLargo());

//desplazamientosDibujos(transicionDireccionDeMemoria.getY() - transicionDireccionDeMemoria.getLargo() * 2);
              //vTS.getjScrollPane1().getHorizontalScrollBar().setValue(letra.getX());
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

          vTS.getContenedorCinta().getCintaTuring().cambiarCadena(cadenas, cabezal, a.getAccion().getMT().getViejo().getCinta().getStartPos(), esAceptada);
          vTS.getcM().repaint();

          try {
            Thread.sleep(velocidadMS);
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
            Thread.sleep(velocidadMS);
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

          vTS.getContenedorCinta().getCintaTuring().cambiarCadena(cadenas, cabezal, a.getAccion().getMT().getViejo().getCinta().getStartPos(), esAceptada);
          vTS.getcM().repaint();

          try {
            Thread.sleep(velocidadMS);
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

          vTS.getContenedorCinta().getCintaTuring().cambiarCadena(cadenas, cabezal, a.getAccion().getMT().getNuevo().getCinta().getStartPos(), esAceptada);
          vTS.getcM().repaint();

          try {
            Thread.sleep(velocidadMS);
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
      vTS.getBotonAceptar().setBackground(new Color(0, 60, 84));

      vTS.getBotonAceptar1().setEnabled(true);
      vTS.getBotonAceptar1().setBackground(new Color(0, 60, 84));
      
      vTS.getPasoAnterior().setEnabled(true);
      vTS.getPasoAnterior().setBackground(new Color(0, 60, 84));
      
      vTS.getPasoSiguiente().setEnabled(true);
      vTS.getPasoSiguiente().setBackground(new Color(0, 60, 84));
      
      vTS.getBotonDibujarMatriz().setEnabled(true);
      vTS.getBotonDibujarMatriz().setBackground(new Color(0, 60, 84));
      
      vTS.getGuardarDatosEnTabla().setEnabled(true);
      vTS.getGuardarDatosEnTabla().setBackground(new Color(0, 60, 84));

      vTS.getAbrirDatosEnTabla().setEnabled(true);
      vTS.getAbrirDatosEnTabla().setBackground(new Color(0, 60, 84));

      vTS.getSpinnerEstados().setEnabled(true);
      vTS.getSpinnerCaracteres().setEnabled(true);

      
      vTS.getDetenerAnalisis().removeActionListener(listener);

    }

  }

}
