package tree;

public class Main {

    public static void main(String[] args) {
        BinarySearchTree<Integer> bst = new BinarySearchTree<>();
        bst.add(10);
        bst.add(5);
        bst.add(12);
        bst.add(11);
        bst.add(20);
        bst.add(1);
        bst.add(3);
        bst.add(6);
        
        System.out.println(bst);
        System.out.println(bst.levelOrder());
        System.out.println(bst.preOrder());
        System.out.println(bst.inOrder());
        System.out.println(bst.postOrder());
    }
}
