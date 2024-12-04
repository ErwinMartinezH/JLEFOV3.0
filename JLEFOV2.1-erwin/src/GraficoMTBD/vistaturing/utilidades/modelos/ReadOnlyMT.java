package GraficoMTBD.vistaturing.utilidades.modelos;

import java.util.*;

/**
 * Máquina de Turing de solo lectura
 *
 * @see MT#toReadOnly()
 */
public final class ReadOnlyMT {

    /**
     * @deprecated
     */
    private final int _estadoCount;
    private final int _estadoInicial;
    private final Set<Integer> _estados;
    private final Set<Integer> _estadosAceptacion;
    private final Map<Tuple2<Integer, Simbolo>, Tuple3<Integer, Simbolo, MT.Direccion>> _transiciones;
    private final ReadOnlyCinta _cinta;

    public ReadOnlyMT(MT mt) {

        _estadoCount = mt._estadoCount;
        _estadoInicial = mt._estadoInicial;
        _estados = Set.copyOf(mt._estados);
        _estadosAceptacion = Set.copyOf(mt._estadosAceptacion);
        _transiciones = Collections.unmodifiableMap(new TreeMap<>(mt._transiciones));
        _cinta = mt._cinta.toReadOnly();
    }

    /**
     * Obtiene la cantidad de estados.
     *
     * @return Cantidad de estados
     */
    public int getEstadoCount() {
        return _estados.size();
    }

    /**
     * Obtiene el estado inicial
     *
     * @return Estado inicial
     */
    public int getEstadoInicial() {
        return _estadoInicial;
    }

    /**
     * Obtiene el conjunto de estados de la Máquina de Turing.
     *
     * @return Conjunto de estados
     */
    public Set<Integer> getEstados() {
        return _estados;
    }

    /**
     * Obtiene el conjunto de estados de aceptación
     *
     * @return Estados de aceptación
     */
    public Set<Integer> getEstadosAceptacion() {
        return _estadosAceptacion;
    }

    /**
     * Obtiene las transiciones de la Máquina de Turing.
     *
     * @return Transiciones
     */
    public Map<Tuple2<Integer, Simbolo>, Tuple3<Integer, Simbolo, MT.Direccion>> getTransiciones() {
        return _transiciones;
    }

    /**
     * Obtiene la tupla {@code (p, X, D)} de una {@code transición}.
     *
     * @param from Par ordenado {@code (q, s)} de una {@code transición}
     * @return Tupla {@code (p, X, D)} de una {@code transición}, {@code null} si la transición no existe.
     * @see Tuple2
     * @see Tuple3
     *
     */
    public Tuple3<Integer, Simbolo, MT.Direccion> getTransicion(Tuple2<Integer, Simbolo> from) {
        return _transiciones.getOrDefault(from, null);
    }

    /**
     * Obtiene la tupla {@code (p, X, D)} de una {@code transición}.
     *
     * @param estadoFrom  Es {@code q} del par ordenado {@code (q, s)} de una {@code transición}
     * @param simboloFrom Es {@code s} del par ordenado {@code (q, s)} de una {@code transición}
     * @return Tupla {@code (p, X, D)} de una {@code transición}, {@code null} si no existe la transición.
     * @see Tuple3
     */
    public Tuple3<Integer, Simbolo, MT.Direccion> getTransicion(int estadoFrom, char simboloFrom) {
        return getTransicion(new Tuple2<>(estadoFrom, Simbolo.fromChar(simboloFrom)));
    }

    /**
     * Obtiene la cinta de solo lectura.
     *
     * @return Cinta de solo lectura
     */
    public ReadOnlyCinta getCinta() {
        return _cinta;
    }
}
