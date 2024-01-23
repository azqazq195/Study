package hash_table;

public interface HashTable<K, V> {

    int hash(Object key);

    void put(K key, V value);

    void remove(K key);

    V get(K key);

    boolean isEmpty();

    @Override
    String toString();
}
