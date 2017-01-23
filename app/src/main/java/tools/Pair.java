package tools;

import java.util.Map;


public class Pair<L, R> implements Map.Entry<L, R> {


    L key;
    R value;

    public Pair(L key, R value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public L getKey() {
        return key;
    }

    @Override
    public R getValue() {
        return value;
    }

    @Override
    public R setValue(R object) {
        throw new UnsupportedOperationException();
    }

    public static <K, V> Pair<K, V> of(K key, V value) {
        return new Pair<K, V>(key, value);
    }
}
