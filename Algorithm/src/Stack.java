import java.util.ArrayList;

public class Stack {
    public static void main(String[] args) {
        MyStack<Integer> myStack = new MyStack<>();
        myStack.push(1);
        myStack.push(2);
        System.out.println(myStack.pop());
        myStack.push(3);
        System.out.println(myStack.pop());
        System.out.println(myStack.pop());
    }
}

class MyStack<T> {
    private final ArrayList<T> stack = new ArrayList<>();

    public void push(T item) {
        stack.add(item);
    }

    public T pop() {
        if(stack.isEmpty()) {
            return null;
        }
        return stack.remove(stack.size() - 1);
    }

    public boolean isEmpty() {
        return stack.isEmpty();
    }

}