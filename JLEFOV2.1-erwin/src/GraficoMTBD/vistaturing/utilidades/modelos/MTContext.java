package GraficoMTBD.vistaturing.utilidades.modelos;

import java.util.List;
import java.util.function.Consumer;

/**
 * Se usa para dar contexto a la hora de reportar un cambio en la Máquina de Turing.
 *
 * @see MT#ejecutar(String, Consumer)
 * @see Cambio
 * @see ReadOnlyMT
 * @see Informacion
 */
public final class MTContext {

    /**
     * Acción que se hizo en la Máquina de Turing.
     */
    public enum Accion {
        CargarCadena,
        CambiarEstado,
        EditarSimbolo,
        Aceptar,
        Rechazar
    }

    private final Accion _accion;
    private final Cambio<ReadOnlyMT> _mt;
    private final List<Informacion> _info;

    public MTContext(Accion accion, Cambio<ReadOnlyMT> mt, List<Informacion> info) {
        _accion = accion;
        _mt = mt;

        if (info != null) {
            _info = List.copyOf(info);
            info.clear();
        } else
            _info = List.of();
    }

    /**
     * Obtiene la acción realizada.
     *
     * @return Acción
     */
    public Accion getAccion() {
        return _accion;
    }

    /**
     * Obtiene la Máquina de Turing de solo lectura.
     *
     * @return Máquina de Turing de solo lectura.
     */
    public Cambio<ReadOnlyMT> getMT() {
        return _mt;
    }

    /**
     * Obtiene la información extra brindada.
     *
     * @return Lista de info
     */
    public List<Informacion> getInfo() {
        return _info;
    }

}
