package Grafico.vistaturing.utilidades.modelos;

/**
 * Clase que almacena el valor viejo y nuevo de un valor tipo {@link T}.
 *
 * @param <T>
 */
public final class Cambio<T> {

    /**
     * Crea un objeto de {@link Cambio}
     *
     * @param viejo Valor viejo tipo {@link T}.
     * @param nuevo Valor nuevo tipo {@link T}.
     * @param <T>
     * @return Objeto {@link Cambio}
     */
    public static <T> Cambio<T> of(T viejo, T nuevo) {
        return new Cambio<T>(viejo, nuevo);
    }

    /**
     * Crea un objeto de {@link Cambio}.
     * <p>
     * En este caso no hay cambio, el valor viejo y nuevo es el mismo.
     *
     * @param e   Valor tipo {@link T}.
     * @param <T>
     * @return
     */
    public static <T> Cambio<T> of(T e) {
        return new Cambio<T>(e, e);
    }

    private final T _viejo;
    private final T _nuevo;

    private Cambio(T viejo, T nuevo) {
        _viejo = viejo;
        _nuevo = nuevo;
    }

    /**
     * Obtiene el valor nuevo {@link T}
     *
     * @return Valor nuevo
     */
    public T getNuevo() {
        return _nuevo;
    }

    /**
     * Obtiene el valor viejo {@link T}
     *
     * @return Valor viejo
     */
    public T getViejo() {
        return _viejo;
    }

}
