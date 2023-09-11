package linked_list;

public interface LinkedList<E> {

    void add(E item);

    void add(E item, int index);

    int size();

    void clear();

    E get(int index);

    void remove();

    void remove(int index);

    void remove(E item);

    int indexOf(E item);

    boolean contains(E item);
}
