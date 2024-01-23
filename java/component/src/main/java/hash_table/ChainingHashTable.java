package hash_table;

import java.util.LinkedList;

public class ChainingHashTable<K, V> implements HashTable<K, V> {

    private LinkedList<Entry<K, V>>[] table;
    private int numberOfItems;
    private int capacity;


    public ChainingHashTable(int capacity) {
        this.capacity = capacity;
        table = new LinkedList[this.capacity];
        numberOfItems = 0;
        for (int i = 0; i < capacity; i++) {
            table[i] = new LinkedList<>();
        }
    }

    @Override
    public int hash(Object key) {
        int hashcode = 0;
        for (char c : key.toString().toCharArray()) {
            hashcode += c;
        }

        return hashcode & capacity;
    }

    @Override
    public void put(K key, V value) {
        int indexOfBucket = this.hash(key);
        LinkedList<Entry<K, V>> bucket = table[indexOfBucket];

        bucket.add(0, new Entry<>(key, value));
        numberOfItems++;
    }


    @Override
    public void remove(K key) {
        if (isEmpty()) {
            throw new RuntimeException("Hash Table is Empty");
        }

        int indexOfBucket = this.hash(key);
        LinkedList<Entry<K, V>> bucket = table[indexOfBucket];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                bucket.remove(entry);
                numberOfItems--;
            }
        }
    }

    @Override
    public V get(K key) {
        int indexOfBucket = this.hash(key);
        LinkedList<Entry<K, V>> bucket = table[indexOfBucket];

        for (Entry<K, V> entry : bucket) {
            if (entry.getKey().equals(key)) {
                return entry.getValue();
            }
        }

        return null;

    }

    @Override
    public boolean isEmpty() {
        return numberOfItems == 0;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < table.length; i++) {
            sb.append("i: ")
                .append(i)
                .append(", values: ")
                .append(table[i]).append("\n");
        }
        return sb.toString();
    }
}
