package GraficoMTBD.vistaturing.utilidades.modelos;

/**
 * Representa los simbolos en una cinta.
 *
 * @see Cinta
 */
public final class Simbolo implements Comparable<Simbolo> {

    /**
     * Caracter especial blanco
     */
    public static final Character BlancoChar = '\0';
    /**
     * Simbolo especial blanco
     */
    public static final Simbolo Blanco = new Simbolo(BlancoChar);

    /**
     * Convierte un caracter en un simbolo
     *
     * @param ch Caracter a convertir
     * @return Simbolo que representa a {@code ch}
     */
    public static Simbolo fromChar(char ch) {

        if (ch == BlancoChar) return Blanco;

        return new Simbolo(ch);
    }

    private final int _hash;
    private final char _sim;

    private Simbolo(char sim) {
        _hash = Character.hashCode(sim);
        _sim = sim;
    }

    /**
     * @return {@code true} si el simbolo es el simbolo especial blanco, sino {@code false}
     */
    public boolean esBlanco() {
        return _sim == '\0';
    }

    @Override
    public int compareTo(Simbolo o) {

        if (esBlanco() && o.esBlanco()) return 0;

        return Character.compare(_sim, o._sim);
    }

    @Override
    public int hashCode() {
        return _hash;
    }

    @Override
    public String toString() {

        if (esBlanco()) return "Blanco";

        return String.format("'%s'", _sim);
    }
}
