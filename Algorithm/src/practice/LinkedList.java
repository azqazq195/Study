package practice;

public class LinkedList {
    public static void main(String[] args) {
        MyLinkedList<Integer> linkedList = new MyLinkedList<>();
        linkedList.addNode(2);
        linkedList.addNode(4);
        linkedList.addNode(8);
        linkedList.insertToData(8, 7);
        linkedList.printAll();
    }
}

class MyLinkedList<T> {
    public Node<T> head = null;
    public Node<T> tail = null;

    public static class Node<T> {
        T data;
        Node<T> prev = null;
        Node<T> next = null;

        public Node(T data) {
            this.data = data;
        }
    }

    public void addNode(T data) {
        if (this.head == null) {
            this.head = new Node<>(data);
            this.tail = this.head;
        } else {
            Node<T> node = this.head;
            while (node.next != null) {
                node = node.next;
            }
            node.next = new Node<>(data);
            node.next.prev = node;
            this.tail = node.next;
        }
    }

    public void printAll() {
        StringBuilder sb = new StringBuilder();
        if (this.head != null) {
            Node<T> node = this.head;
            while (node != null) {
                sb.append(node.data);
                sb.append(" ");
                node = node.next;
            }
            System.out.println(sb.toString());
        }
    }

    public boolean searchFromHead(T searchData) {
        if (this.head != null) {
            Node<T> node = this.head;
            while (node != null) {
                if (node.data == searchData) return true;
                else node = node.next;
            }
        }
        return false;
    }

    public boolean searchFromTail(T searchData) {
        if (this.head != null) {
            Node<T> node = this.tail;
            while (node != null) {
                if (node.data == searchData) return true;
                else node = node.prev;
            }
        }
        return false;
    }

    public void insertToData(T existingData, T addData) {
        if (this.head == null) {
            this.head = new Node<>(addData);
            this.tail = this.head;
            return;
        }

        if (this.head.data == existingData) {
            Node<T> newHead = new Node<>(addData);
            newHead.next = this.head;
            this.head = newHead;
            return;
        }

        Node<T> node = this.head;
        while (node != null) {
            if (node.data == existingData) {
                Node<T> newNode = new Node<>(addData);
                newNode.prev = node.prev;
                newNode.next = node;

                node.prev.next = newNode;
                node.prev = newNode;
                return;
            } else {
                node = node.next;
            }
        }
    }
}