package linked_list;

public class Main {

    public static void main(String[] args) {
        SingleLinkedList<String> list = new SingleLinkedList<>();
        list.add("1");
        list.add("2");
        list.add("3");
        System.out.println(list + ", size: " + list.size());

        list.add("4", 0);
        list.add("5", 0);
        System.out.println(list + ", size: " + list.size());

        list.add("6", 4);
        list.add("7", 5);
        System.out.println(list + ", size: " + list.size());

        list.remove(2);
        list.remove();
        System.out.println(list + ", size: " + list.size());
    }
    
}
