package Grafico.vistaturing.utilidades.modelos;

import java.util.Objects;

public final class Tuple3<T1, T2, T3> {

    private final int _hash;
    private final T1 _item1;
    private final T2 _item2;
    private final T3 _item3;

    public Tuple3(T1 item1, T2 item2, T3 item3) {

        _hash = Objects.hash(item1, item2, item3);
        _item1 = item1;
        _item2 = item2;
        _item3 = item3;
    }

    public T1 getItem1() {
        return _item1;
    }

    public T2 getItem2() {
        return _item2;
    }

    public T3 getItem3() {
        return _item3;
    }

    @Override
    public int hashCode() {
        return _hash;
    }

    @Override
    public String toString() {
        return String.format("(%s, %s, %s)", _item1, _item2, _item3);
    }
}
