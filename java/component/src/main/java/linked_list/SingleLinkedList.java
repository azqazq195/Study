package linked_list;

public class SingleLinkedList<E> implements LinkedList<E> {

    private Node<E> first;
    private Node<E> last;
    private int size;

    public SingleLinkedList() {
        clear();
    }

    @Override
    public void add(E item) {
        if (size == 0) {
            linkFirst(item);
        } else {
            linkAfter(item, last);
        }
    }

    @Override
    public void add(E item, int index) {
        checkPosition(index);

        if (index == 0) {
            linkFirst(item);
        } else {
            linkAfter(item, node(index - 1));
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void clear() {
        first = null;
        last = null;
        size = 0;
    }

    @Override
    public E get(int index) {
        checkPosition(index);

        return node(index).item;
    }

    @Override
    public void remove() {
        unlinkLast();
    }

    @Override
    public void remove(int index) {
        Node<E> node = node(index);
        if (node == first) {
            unlinkFirst();
        } else if (node == last) {
            unlinkLast();
        } else {
            unlink(node);
        }
    }

    @Override
    public void remove(E e) {
        remove(indexOf(e));
    }

    @Override
    public int indexOf(E item) {
        int index = 0;
        for (Node<E> x = first; x != null; x = x.next, index++) {
            if (item.equals(x.item)) {
                return index;
            }
        }
        return -1;
    }

    @Override
    public boolean contains(E item) {
        return indexOf(item) >= 0;
    }

    @Override
    public String toString() {
        if (size == 0) {
            return "null";
        }

        Node<E> current = first;
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        while (current != null) {
            sb.append(current.item);
            if (current.next != null) {
                sb.append(", ");
            }
            current = current.next;
        }
        sb.append("]");
        return sb.toString();
    }

    private void linkFirst(E item) {
        Node<E> prevFirst = first;
        Node<E> newNode = new Node<>(item, prevFirst);
        first = newNode;

        if (newNode.next == null) {
            last = newNode;
        }
        size++;
    }

    private void linkAfter(E item, Node<E> node) {
        Node<E> prevNext = node.next;
        Node<E> newNode = new Node<>(item, prevNext);
        node.next = newNode;

        if (newNode.next == null) {
            last = newNode;
        }
        size++;
    }


    private void unlinkFirst() {
        if (first == last) {
            clear();
        } else {
            first = first.next;
            size--;
        }
    }

    private void unlinkLast() {
        if (first == last) {
            clear();
        } else {
            Node<E> prevNode = first;
            while (prevNode.next != last) {
                prevNode = prevNode.next;
            }
            last = prevNode;
            last.next = null;
            size--;
        }
    }

    private void unlink(Node<E> node) {
        Node<E> prevNode = first;
        while (prevNode.next != null && prevNode.next != node) {
            prevNode = prevNode.next;
        }
        prevNode.next = node.next;
        size--;
    }

    private void checkPosition(int index) {
        if (index < 0 || size <= index) {
            throw new IndexOutOfBoundsException();
        }
    }

    private Node<E> node(int index) {
        if (index == size - 1) {
            return last;
        }

        Node<E> node = first;
        while (index-- > 0) {
            node = node.next;
        }
        return node;
    }


    private static class Node<E> {

        E item;
        Node<E> next;

        Node(E item, Node<E> next) {
            this.item = item;
            this.next = next;
        }
    }
}
