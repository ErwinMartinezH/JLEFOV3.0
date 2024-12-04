package GraficoMTBD.vistaturing.utilidades.modelos;

import java.util.Iterator;
import java.util.List;

/**
 * Cinta de solo lectura.
 *
 * @see Cinta#toReadOnly()
 */
public final class ReadOnlyCinta implements Iterable<Simbolo> {

  private final int _startPos;
  private final int _pos;
  private final List<Simbolo> _simbolos;

  private final String _toString;

  public ReadOnlyCinta(Cinta cinta) {
    _pos = cinta._pos;
    _startPos = cinta._startPos;
    _simbolos = List.copyOf(cinta._simbolos);
    _toString = cinta.toString();
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

    if (_pos >= length()) {
      return Simbolo.Blanco;
    }

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
    return _pos - _startPos;
  }

  public int getStartPos() {
    return _startPos;
  }

  public int getPos() {
    return _pos;
  }

  @Override
  public Iterator<Simbolo> iterator() {
    return _simbolos.iterator();
  }

  @Override
  public String toString() {
    return _toString;
  }
}
