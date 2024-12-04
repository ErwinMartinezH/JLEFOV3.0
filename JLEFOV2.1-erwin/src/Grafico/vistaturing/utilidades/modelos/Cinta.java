package Grafico.vistaturing.utilidades.modelos;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Representa la cinta de una Máquina de Turing.
 *
 * @see Simbolo
 */
public final class Cinta implements Iterable<Simbolo> {

    int _pos;
    final List<Simbolo> _simbolos;

    public Cinta() {
        _simbolos = new ArrayList<>();
    }

    /**
     * Devuelve la longitud de la cinta
     *
     * @return Tamaño de la cinta
     */
    public int length() {
        return _simbolos.size();
    }

    /**
     * Sobre escribe cualquier simbolo en la posición actual de la cinta y lo reemplaza por {@code simbolo}
     * y luego se mueve a {@code direccion}.
     *
     * @param simbolo   Simbolo a escribir
     * @param direccion Direccion a moverse
     */
    public void setSimbolo(Simbolo simbolo, MT.Direccion direccion) {

        if (_pos < 0) return;

        if (_pos == length()) _simbolos.add(simbolo);
        else _simbolos.set(_pos, simbolo);

        if (direccion == MT.Direccion.Derecha) ++_pos;
        else if (direccion == MT.Direccion.Izquierda) --_pos;

    }

    /**
     * Carga una cadena en la cinta.
     *
     * @param cadena Cadena a cargar
     */
    public void cargarCadena(String cadena) {

        var sims = cadena.chars().mapToObj(c -> Simbolo.fromChar((char) c)).toList();

        _simbolos.addAll(sims);

    }

    /**
     * Quita todos los simbolos de la cinta.
     */
    public void reset() {
        _simbolos.clear();
    }

    /**
     * Obtiene el simbolo actual.
     *
     * @return Simbolo en la posición actual
     */
    public Simbolo getSimbolo() {

        if (_pos >= length()) return Simbolo.Blanco;

        return _simbolos.get(_pos);
    }

    /**
     * Obtiene el simbolo de acuerdo a su posición en la cinta.
     *
     * @param index Indice del simbolo
     * @return Simbolo en el indice {@code index}
     */
    public Simbolo simboloAt(int index) {

        if (index < 0) return null;
        if (index >= length()) return Simbolo.Blanco;

        return _simbolos.get(index);
    }

    /**
     * Obtiene la posición actual.
     *
     * @return posicion actual
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

    /**
     * Converte la cinta y todos sus componentes en solo lectura.
     *
     * @return Cinta de solo lectura.
     * @see ReadOnlyCinta
     */
    public ReadOnlyCinta toReadOnly() {
        return new ReadOnlyCinta(this);
    }
}
