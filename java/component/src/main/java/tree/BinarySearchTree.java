package tree;

import java.util.LinkedList;
import java.util.Queue;

public class BinarySearchTree<E> {

    private Node<E> root;
    private int size;

    public BinarySearchTree() {
        this.size = 0;
    }

    public void add(E value) {
        size++;

        if (root == null) {
            root = new Node<>(value);
            return;
        }

        Comparable<? super E> key = (Comparable<? super E>) value;

        Node<E> current = this.root;
        Node<E> parent = current;
        int compareValue = 0;

        while (current != null) {
            compareValue = key.compareTo(current.value);
            parent = current;

            if (compareValue > 0) {
                current = current.right;
            } else if (compareValue < 0) {
                current = current.left;
            } else {
                return;
            }
        }

        Node<E> newNode = new Node<>(value);
        if (compareValue > 0) {
            parent.right = newNode;
        } else {
            parent.left = newNode;
        }
    }

    class Node<E> {

        private final E value;
        private Node<E> left;
        private Node<E> right;

        Node(E value) {
            this.value = value;
        }

        @Override
        public String toString() {
            return "Node{" +
                "value=" + value +
                ", left=" + left +
                ", right=" + right +
                '}';
        }

    }

    /**
     * 전위 순회 깊이 우선 순회
     *
     * @return
     */
    public String preOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("PREORDER").append("\n");
        preOrderTraversal(root, sb);
        sb.append("\n");
        return sb.toString();
    }

    private void preOrderTraversal(Node<E> node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        sb.append(node.value).append(" ");
        preOrderTraversal(node.left, sb);
        preOrderTraversal(node.right, sb);
    }

    public String inOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("INORDER").append("\n");
        inOrderTraversal(root, sb);
        sb.append("\n");
        return sb.toString();
    }

    private void inOrderTraversal(Node<E> node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        inOrderTraversal(node.left, sb);
        sb.append(node.value).append(" ");
        inOrderTraversal(node.right, sb);
    }

    public String postOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("POSTORDER").append("\n");
        postOrderTraversal(root, sb);
        sb.append("\n");
        return sb.toString();
    }

    private void postOrderTraversal(Node<E> node, StringBuilder sb) {
        if (node == null) {
            return;
        }

        postOrderTraversal(node.left, sb);
        postOrderTraversal(node.right, sb);
        sb.append(node.value).append(" ");
    }

    /**
     * 레벨 순서 순회, 너비 우선 순회
     *
     * @return
     */
    public String levelOrder() {
        StringBuilder sb = new StringBuilder();
        sb.append("LEVEL ORDER").append("\n");
        Queue<Node<E>> que = new LinkedList<>();
        que.add(this.root);

        while (!que.isEmpty()) {
            Node<E> cur = que.poll();
            if (cur == null) {
                continue;
            }

            sb.append(cur.value).append(" ");
            que.add(cur.left);
            que.add(cur.right);
        }

        sb.append("\n");
        return sb.toString();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        traverseTree(root, 0, sb, "Root: ");
        return sb.toString();
    }

    private void traverseTree(Node<E> node, int level, StringBuilder sb, String prefix) {
        if (node == null) {
            sb.append(" ".repeat(level * 4)).append(prefix).append("null\n");
            return;
        }

        sb.append(" ".repeat(level * 4)).append(prefix).append(node.value).append("\n");
        traverseTree(node.left, level + 1, sb, "Left: ");
        traverseTree(node.right, level + 1, sb, "Right: ");
    }
}