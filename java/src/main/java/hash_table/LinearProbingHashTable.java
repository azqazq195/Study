package hash_table;

public class LinearProbingHashTable<K, V> implements HashTable<K, V> {

    private int maxSize, currentSize;
    private K[] keys;
    private V[] values;
    private final double DEFAULT_LOAD_FACTOR = 0.75;

    public LinearProbingHashTable(int capacity) {
        this.maxSize = capacity;
        this.currentSize = 0;
        //noinspection unchecked
        this.keys = (K[]) new Object[maxSize];
        //noinspection unchecked
        this.values = (V[]) new Object[maxSize];
    }

    @Override
    public int hash(Object key) {
        int hashcode = 0;
        for (char c : key.toString().toCharArray()) {
            hashcode += c;
        }

        return hashcode % maxSize;
    }

    @Override
    public void put(K key, V value) {
        int hashKey = hash(key);
        int i = hashKey;

        do {
            if (keys[i] == null) {
                keys[i] = key;
                values[i] = value;
                currentSize++;
                return;
            }

            if (keys[i].equals(key)) {
                values[i] = value;
                return;
            }
            System.out.println("hash collision. key: " + key + ", index: " + i);
            i = (i + 1) % maxSize;
        }

        while (i != hashKey);
    }

    @Override
    public void remove(K key) {
        if (isEmpty()) {
            throw new RuntimeException("Hash Table is Empty");
        }

        int i = hash(key);
        while (!key.equals(keys[i])) {
            i = (i + 1) % maxSize;
        }
        keys[i] = null;
        values[i] = null;
        currentSize--;

        for (i = (i + 1) % maxSize; keys[i] != null; i = (i + 1) % maxSize) {
            K tmpKey = keys[i];
            V tmpValue = values[i];

            keys[i] = null;
            values[i] = null;
            currentSize--;

            put(tmpKey, tmpValue);
        }
    }

    @Override
    public V get(Object key) {
        int i = hash(key);
        while (keys[i] != null) {
            if (keys[i].equals(key)) {
                return values[i];
            }
            i = (i + 1) % maxSize;
        }
        return null;
    }

    @Override
    public boolean isEmpty() {
        return currentSize == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < maxSize; i++) {
            sb.append("i: ")
                .append(i)
                .append(", key: ")
                .append(keys[i])
                .append(", value: ")
                .append(values[i])
                .append("\n");
        }

        return sb.toString();
    }
}
