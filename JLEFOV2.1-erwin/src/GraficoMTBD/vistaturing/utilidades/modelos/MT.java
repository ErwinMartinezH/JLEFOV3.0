package GraficoMTBD.vistaturing.utilidades.modelos;

import java.util.*;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * Representación de una Máquina de Turing Estándar.
 *
 * @see Cinta
 */
public final class MT {

    /**
     * Dirección que una Máquina de Turing puede mover el cabezal de la cinta
     * <p>
     * Posibles valores:
     * <ul>
     *   <li>Izquierda.</li>
     *   <li>Derecha.</li>
     * </ul>
     */
    public enum Direccion {
        Izquierda, Derecha
    }

    // (q, s) => (p, x, D) donde:
    //  q: int= estado acutal
    //  s: Simbolo= simbolo actual
    //  p: int= estado a transicionar
    //  x: Simbolo= simbolo a sobre escribir por el simbolo actual
    //  D: Direccion= direccion que se va a aplicar a la cinta
    /**
     * @deprecated
     */
    int _estadoCount;
    int _estadoInicial;
    final HashSet<Integer> _estados;
    final HashSet<Integer> _estadosAceptacion;
    final TreeMap<Tuple2<Integer, Simbolo>, Tuple3<Integer, Simbolo, Direccion>> _transiciones;
    final Cinta _cinta;

    public MT() {
        _estadoCount = 0;
        _estados = new HashSet<>();
        _estadosAceptacion = new HashSet<>();
        _transiciones = new TreeMap<>();
        _cinta = new Cinta();
    }

    /**
     * Agrega un estado a la Máquina de Turing.
     * <p>
     * El número del estado se calcula automaticamente.
     *
     * @deprecated Usar {@link #addEstado(int)}
     */
    public void addEstado() {
    }


    /**
     * Agrega un estado a la Máquina de Turing.
     *
     * @param estado Identificador del estado a agregar a la Maquina de Turing
     * @return {@code true} si el estado no existia ya en el conjunto de estados.
     * {@code false} si el estado ya existia.
     */
    public boolean addEstado(int estado) {
        return _estados.add(estado);
    }

    /**
     * Quita un estado a la Máquina de Turing.
     *
     * @param estado Identificador del estado a quitar a la Maquina de Turing
     * @return {@code true} si se pudo quitar el estado, sino {@code false}.
     */
    public boolean removeEstado(int estado) {
        return _estados.remove(estado);
    }

    /**
     * Agrega la cantidad de estados requiridos.
     *
     * @param cantidad Número de estados.
     * @deprecated Usar {@link #addEstados(Integer...)}
     */
    public void addEstados(int cantidad) {
    }

    /**
     * Agrega los estados especificados a la Maquina de Turing.
     *
     * @param estados Estados a agregar a la Maquina de Turing
     */
    public void addEstados(Integer... estados) {
        _estados.addAll(List.of(estados));
    }

    /**
     * Quita los estados especificados a la Maquina de Turing.
     *
     * @param estados Estados a quitar de la Maquina de Turing
     */
    public void removeEstados(Integer... estados) {
        List.of(estados).forEach(_estados::remove);
    }

    /**
     * Establece un estado antes agregado por {@link #addEstado(int)} o {@link #addEstados(Integer...)}
     * como un estado de aceptación.
     *
     * @param estado Id del estado.
     * @return {@code true} si el estado se agrego, sino {@code false}.
     */
    public boolean addEstadoAceptacion(int estado) {
        return _estadosAceptacion.add(estado);
    }

    /**
     * Quita el estado del conjunto de Estados de Aceptación de la Maquina de Turing.
     *
     * @param estado Id del estado a quitar.
     * @return {@code true} si el estado se quitó, sino {@code false}.
     */
    public boolean removeEstadoAceptacion(int estado) {
        return _estadosAceptacion.remove(estado);
    }

    /**
     * Establece multiples estados antes agregados por {@link #addEstado(int)} o {@link #addEstados(Integer...)}
     * como estados de aceptación.
     *
     * @param estados Ids de los estados.
     */
    public void addEstadosAceptacion(Integer... estados) {

        var es = Arrays.stream(estados).filter(_estados::contains).toList();

        _estadosAceptacion.addAll(es);
    }

    /**
     * Quita multiples estados de aceptación.
     *
     * @param estados Ids de los estados.
     */
    public void removeEstadosAceptacion(Integer... estados) {
        List.of(estados).forEach(_estadosAceptacion::remove);
    }

    /**
     * Establece un estado antes agregado por {@link #addEstado(int)} o {@link #addEstados(Integer...)}
     * como inicial.
     * <p>
     * Por defecto el estado inicial es el 0.
     *
     * @param estado Id del estado
     */
    public void setEstadoInicial(int estado) {

        if (!_estados.contains(estado)) return;

        _estadoInicial = estado;
    }

    /**
     * Obtiene el estado inicial de la Máquina de Turing
     *
     * @return Id del estado inicial
     */
    public int getEstadoInicial() {
        return _estadoInicial;
    }

    /**
     * La {@code transición} en la Máquina de Turing tiene la siguiente estructura:
     * <p>
     * {@code (q, s) -> (p, X, D)} donde,
     * <ul>
     *   <li><b>q</b> es el estado actual.</li>
     *   <li><b>s</b> es el símbolo a leer.</li>
     *   <li><b>p</b> es el estado a transicionar.</li>
     *   <li><b>X</b> es el símbolo a escribir.</li>
     *   <li><b>D</b> es la dirección a la cuál mover el cabezal.</li>
     * </ul>
     * <p>
     * Agrega una {@code transición} a la Máquina de Turing, si y solo si:
     * <ul>
     *   <li><b>q</b> pertenece a los estados de la Máquina de Turing.</li>
     *   <li><b>p</b> pertenece a los estados de la Máquina de Turing.</li>
     *   <li><b>q</b> no es un estado de aceptación.</li>
     *   <li>El par ordenado {@code (q, s)} no está en la lista de transiciones.</li>
     * </ul>
     *
     * @param from Par ordenado {@code (q, s)} de una {@code transición}
     * @param to   Tupla {@code (p, X, D)} de una {@code transición}
     * @return Máquina de Turing que llama el metodo (Builder Pattern)
     * @see Tuple2
     * @see Tuple3
     */
    public MT addTransicion(Tuple2<Integer, Simbolo> from, Tuple3<Integer, Simbolo, Direccion> to) {

        if (!_estados.contains(from.getItem1())) return this;

        if (!_estados.contains(to.getItem1())) return this;

        if (_estadosAceptacion.contains(from.getItem1())) return this;

        if (_transiciones.containsKey(from)) return this;

        _transiciones.put(from, to);
        return this;
    }

    /**
     * Quita una transicion de la Maquina de Turing
     *
     * @param from Par ordenado {@code (q, s)} de una {@code transición}
     * @return Máquina de Turing que llama el metodo (Builder Pattern)
     */
    public MT removeTransicion(Tuple2<Integer, Simbolo> from) {
        _transiciones.remove(from);
        return this;
    }

    /**
     * La {@code transición} en la Máquina de Turing tiene la siguiente estructura:
     * <p>
     * {@code (q, s) -> (p, X, D)} donde,
     * <ul>
     *   <li><b>q</b> es el estado actual.</li>
     *   <li><b>s</b> es el símbolo a leer.</li>
     *   <li><b>p</b> es el estado a transicionar.</li>
     *   <li><b>X</b> es el símbolo a escribir.</li>
     *   <li><b>D</b> es la dirección a la cuál mover el cabezal.</li>
     * </ul>
     * <p>
     * Agrega una {@code transición} a la Máquina de Turing, si y solo si:
     * <ul>
     *   <li><b>q</b> pertenece a los estados de la Máquina de Turing.</li>
     *   <li><b>p</b> pertenece a los estados de la Máquina de Turing.</li>
     *   <li><b>q</b> no es un estado de aceptación.</li>
     *   <li>El par ordenado {@code (q, s)} no está en la lista de transiciones.</li>
     * </ul>
     *
     * @param estadoFrom  Es {@code q} del par ordenado {@code (q, s)} de una {@code transición}
     * @param simboloFrom Es {@code s} del par ordenado {@code (q, s)} de una {@code transición}
     * @param estadoTo    Es {@code p} de la tupla {@code (p, X, D)} de una {@code transición}
     * @param simboloTo   Es {@code X} de la tupla {@code (p, X, D)} de una {@code transición}
     * @param direccion   Es {@code D} de la tupla {@code (p, X, D)} de una {@code transición}
     * @return Máquina de Turing que llama el metodo (Builder Pattern)
     */
    public MT addTransicion(int estadoFrom, char simboloFrom, int estadoTo, char simboloTo, Direccion direccion) {
        return addTransicion(new Tuple2<>(estadoFrom, Simbolo.fromChar(simboloFrom)), new Tuple3<>(estadoTo, Simbolo.fromChar(simboloTo), direccion));
    }

    /**
     * Quita una transicion de la Maquina de Turing
     *
     * @param estadoFrom  Es {@code q} del par ordenado {@code (q, s)} de una {@code transición}
     * @param simboloFrom Es {@code s} del par ordenado {@code (q, s)} de una {@code transición}
     * @return Máquina de Turing que llama el metodo (Builder Pattern)
     */
    public MT removeTransicion(int estadoFrom, char simboloFrom) {
        return removeTransicion(new Tuple2<>(estadoFrom, Simbolo.fromChar(simboloFrom)));
    }

    /**
     * Obtiene la tupla {@code (p, X, D)} de una {@code transición}.
     *
     * @param from Par ordenado {@code (q, s)} de una {@code transición}
     * @return Tupla {@code (p, X, D)} de una {@code transición}
     * @see Tuple2
     * @see Tuple3
     * @see #addTransicion(Tuple2, Tuple3)
     * @see #addTransicion(int, char, int, char, Direccion)
     */
    public Tuple3<Integer, Simbolo, Direccion> getTransicion(Tuple2<Integer, Simbolo> from) {
        return _transiciones.getOrDefault(from, null);
    }

    /**
     * Obtiene la tupla {@code (p, X, D)} de una {@code transición}.
     *
     * @param estadoFrom  Es {@code q} del par ordenado {@code (q, s)} de una {@code transición}
     * @param simboloFrom Es {@code s} del par ordenado {@code (q, s)} de una {@code transición}
     * @return Tupla {@code (p, X, D)} de una {@code transición}, {@code null} si no existe la transición.
     * @see Tuple2
     * @see Tuple3
     * @see #addTransicion(Tuple2, Tuple3)
     * @see #addTransicion(int, char, int, char, Direccion)
     */
    public Tuple3<Integer, Simbolo, Direccion> getTransicion(int estadoFrom, char simboloFrom) {
        return getTransicion(new Tuple2<>(estadoFrom, Simbolo.fromChar(simboloFrom)));
    }

    /**
     * Ejecuta y evalua una cadena de entrada.
     * <p>
     * Antes de llamar este metodo hay que agregar los estados necesarios con {@link #addEstado(int)} o {@link #addEstados(Integer...)},
     * seleccionar los estados de aceptación con {@link #addEstadoAceptacion(int)} o {@link #addEstadosAceptacion(Integer...)},
     * establecer el estado inicial con {@link #setEstadoInicial(int)} y agregar las transiciones necesarias con {@link #addTransicion(Tuple2, Tuple3)}
     * o {@link #addTransicion(int, char, int, char, Direccion)}
     *
     * @param cadena Cadena a evaluar.
     * @return {@code true} si un estado de aceptación es alcanzado;
     * {@code false} si se quiso transicionar con un par ordenado {@code (q, s)} inexistente
     */
    public boolean ejecutar(String cadena) {

        _cinta.cargarCadena(cadena);

        int estadoActual = _estadoInicial;
        Simbolo simboloActual = _cinta.getSimbolo();

        while (!_estadosAceptacion.contains(estadoActual)) {

            var from = new Tuple2<>(estadoActual, simboloActual);

            if (!_transiciones.containsKey(from)) return false;

            var to = _transiciones.get(from);

            _cinta.setSimbolo(to.getItem2(), to.getItem3());

            estadoActual = to.getItem1();
            simboloActual = _cinta.getSimbolo();
        }

        return true;
    }

    /**
     * Ejecuta y evalua una cadena de entrada y reporta algun cambio por medio de un {@code callback}.
     * <p>
     * Antes de llamar este metodo hay que agregar los estados necesarios con {@link #addEstado(int)} o {@link #addEstados(Integer...)},
     * seleccionar los estados de aceptación con {@link #addEstadoAceptacion(int)} o {@link #addEstadosAceptacion(Integer...)},
     * establecer el estado inicial con {@link #setEstadoInicial(int)} y agregar las transiciones necesarias con {@link #addTransicion(Tuple2, Tuple3)}
     * o {@link #addTransicion(int, char, int, char, Direccion)}
     *
     * @param cadena   Cadena a evaluar.
     * @param callback Se usa para recibir notificaciones de cambios en la Máquina de Turing
     * @return {@code true} si un estado de aceptación es alcanzado;
     * {@code false} si se quiso transicionar con un par ordenado {@code (q, s)} inexistente
     * @see MTContext
     * @see Informacion
     */
    public boolean ejecutar(String cadena, Consumer<MTContext> callback) {

        if (callback == null) return ejecutar(cadena);

        ArrayList<Informacion> info = new ArrayList<>();

        ReadOnlyMT viejo = toReadOnly();
        _cinta.cargarCadena(cadena);
        info.add(Informacion.of("Cadena cargada en la cinta", cadena));
        ReadOnlyMT nuevo = toReadOnly();

        callback.accept(new MTContext(MTContext.Accion.CargarCadena, Cambio.of(viejo, nuevo), info));

        int estadoActual = _estadoInicial;
        Simbolo simboloActual = _cinta.getSimbolo();

        while (!_estadosAceptacion.contains(estadoActual)) {

            var from = new Tuple2<>(estadoActual, simboloActual);

            info.add(Informacion.of("Estado actual", from.getItem1()));
            info.add(Informacion.of("Simbolo actual", from.getItem2()));

            if (!_transiciones.containsKey(from)) {

                info.add(Informacion.of("Cadena rechazada", cadena));
                callback.accept(new MTContext(MTContext.Accion.Rechazar, Cambio.of(toReadOnly()), info));
                return false;
            }

            var to = _transiciones.get(from);

            info.add(Informacion.of("Estado a transicionar", to.getItem1()));
            info.add(Informacion.of("Simbolo a escribir", to.getItem2()));
            info.add(Informacion.of("Direccion a mover", to.getItem3()));

            viejo = toReadOnly();

            callback.accept(new MTContext(MTContext.Accion.CambiarEstado, Cambio.of(viejo), info));

            _cinta.setSimbolo(to.getItem2(), to.getItem3());

            nuevo = toReadOnly();

            info.add(Informacion.of("Estado anterior", from.getItem1()));
            info.add(Informacion.of("Simbolo leido", from.getItem2()));
            info.add(Informacion.of("Estado actual", to.getItem1()));
            info.add(Informacion.of("Simbolo escrito", to.getItem2()));
            info.add(Informacion.of("Direccion", to.getItem3()));
            callback.accept(new MTContext(MTContext.Accion.EditarSimbolo, Cambio.of(viejo, nuevo), info));

            estadoActual = to.getItem1();
            simboloActual = _cinta.getSimbolo();
        }

        info.add(Informacion.of("Cadena aceptada", cadena));
        callback.accept(new MTContext(MTContext.Accion.Aceptar, Cambio.of(toReadOnly()), info));
        return true;
    }

    /**
     * Reinicia la Máquina de Turing, eliminado los estado, estados de aceptación y transiciones.
     *
     * @see Cinta#reset()
     */
    public void reset() {
        _estadoCount = 0;
        _estadoInicial = 0;
        _estados.clear();
        _estadosAceptacion.clear();
        _transiciones.clear();
        _cinta.reset();
    }

    /**
     * Convierte la Máquina de Turing y todos sus componentes en solo lectura.
     *
     * @return Máquina de Turing de solo lectura
     */
    public ReadOnlyMT toReadOnly() {
        return new ReadOnlyMT(this);
    }

}
