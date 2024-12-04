package Grafico.vistaturing.utilidades.modelos;

import java.util.Iterator;
import java.util.List;

/**
 * Cinta de solo lectura.
 *
 * @see Cinta#toReadOnly()
 */
public final class ReadOnlyCinta implements Iterable<Simbolo> {

    private final int _pos;
    private final List<Simbolo> _simbolos;

    public ReadOnlyCinta(Cinta cinta) {
        _pos = cinta._pos;
        _simbolos = List.copyOf(cinta._simbolos);
    }

    /**
     * Obtiene la longitud de la cinta.
     *
     * @return Tamaño de la cinta
     */
    public int length() {
        return _simbolos.size();
    }

    /**
     * Obtiene el simbolo en la posición actual
     *
     * @return Simbolo actual
     */
    public Simbolo getSimbolo() {

        if (_pos >= length()) return Simbolo.Blanco;

        return _simbolos.get(_pos);
    }

    /**
     * Obtiene los simbolos en la cinta.
     *
     * @return Lista de simbolos
     */
    public List<Simbolo> getSimbolos() {
        return _simbolos;
    }

    /**
     * Obtiene la posición actual.
     *
     * @return Posición actual
     */
    public int getPosicion() {
        return _pos;
    }

    @Override
    public Iterator<Simbolo> iterator() {
        return _simbolos.iterator();
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        sb.append('[');

        for (int i = 0; i < _simbolos.size(); ++i) {


            if (i != 0) sb.append(", ");

            sb.append(_simbolos.get(i));

        }

        sb.append(']');

        return sb.toString();
    }
}
