package Grafico.vistaturing.utilidades.modelos;

/**
 * Representa la información que puede servir en {@link MTContext}.
 */
public final class Informacion {

    /**
     * Crea una información.
     *
     * @param descripcion Descripción util para {@code valor}
     * @param valor       Valor
     * @return Información
     */
    public static Informacion of(String descripcion, Object valor) {
        return new Informacion(descripcion, valor);
    }

    private final String _descripcion;
    private final Object _valor;
    private final Class<?> _tipo;

    private Informacion(String descripcion, Object valor) {
        _descripcion = descripcion;
        _valor = valor;
        _tipo = valor.getClass();
    }

    /**
     * Obtiene la descripción
     *
     * @return Descripción
     */
    public String getDescripcion() {
        return _descripcion;
    }

    /**
     * Obtiene el valor como un {@link Object}.
     *
     * @return Valor como {@link Object}
     */
    public Object getValorObj() {
        return _valor;
    }

    /**
     * Obtiene el valor y lo castea como un tipo {@link T}.
     *
     * @param <T> Tipo a castear el valor.
     * @return Valor casteado a tipo {@link T}.
     */
    public <T> T getValor() {
        return (T) _valor;
    }

    /**
     * Obtiene el tipo del valor
     *
     * @return Tipo del valor
     */
    public Class<?> getTipo() {
        return _tipo;
    }

    @Override
    public String toString() {
        return String.format("%s: (%s) {%s}", _descripcion, _tipo.getSimpleName(), _valor);
    }
}
