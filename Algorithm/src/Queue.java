import java.util.ArrayList;

public class Queue {


    public static void main(String[] args) {
        MyQueue<Integer> myQueue = new MyQueue<>();
        myQueue.enqueue(1);
        myQueue.enqueue(2);
        myQueue.enqueue(3);
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
        System.out.println(myQueue.dequeue());
    }
}

class MyQueue<T> {
    private final ArrayList<T> queue = new ArrayList<T>();

    public void enqueue(T item) {
        queue.add(item);
    }

    public Object dequeue() {
        if(queue.isEmpty()) {
            return null;
        }
        return queue.remove(0);
    }

    public boolean isEmpty() {
        return queue.isEmpty();
    }
}