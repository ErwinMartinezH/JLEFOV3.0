package Grafico.vistaturing.utilidades.modelos;

import java.util.Objects;

public final class Tuple2<T1 extends Comparable<T1>, T2 extends Comparable<T2>> implements Comparable<Tuple2<T1, T2>> {
    private final int _hash;
    private final T1 _item1;
    private final T2 _item2;


    public Tuple2(T1 item1, T2 item2) {
        _hash = Objects.hash(item1, item2);
        _item1 = item1;
        _item2 = item2;
    }

    public T1 getItem1() {
        return _item1;
    }

    public T2 getItem2() {
        return _item2;
    }

    @Override
    public int hashCode() {
        return _hash;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s)", _item1, _item2);
    }

    @Override
    public int compareTo(Tuple2<T1, T2> o) {

        var compare = _item1.compareTo(o._item1);

        if (compare != 0) return compare;

        compare = _item2.compareTo(o._item2);

        return compare;
    }
}