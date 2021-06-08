package max_sk;

import java.util.Stack;

public class TreeMy<E extends Comparable<? super E>> implements Tree<E> {

    private int size;
    private Node<E> root;

    private class NodeAndParent {
        private Node<E> current;
        private Node<E> parent;

        public NodeAndParent(Node<E> current, Node<E> parent) {
            this.current = current;
            this.parent = parent;
        }
    }

    public Node<E> getRoot() {
        return root;
    }

    public int getSize() {
        return size;
    }

    @Override
    public boolean isEmpty() {
        return false;
    }

    @Override
    public boolean add(E value) {

        Node<E> node = new Node(value);
        NodeAndParent nodeAndParent = doFaind(value);

        if (nodeAndParent.current != null) {
            return false;
        }

        Node<E> previous = nodeAndParent.parent;
        if (previous == null) {
            root = node;
        } else if (previous.isLeftChild(value)) {
            previous.setLeftChild(node);
        } else {
            previous.setRightChild(node);
        }

        size++;
        return true;

    }


    @Override
    public boolean contains(E value) {
        NodeAndParent nodeAndParent = doFaind(value);
        size++;
        return nodeAndParent.current != null;
    }

    private NodeAndParent doFaind(E value) {
        int glubina = 1;
        Node<E> current = root;
        Node<E> previous = null;

        while (current != null) {
            if (current.getValue().equals(value)) {
                glubina++;
                return new NodeAndParent(current, previous);
            }
            previous = current;
            if (current.isLeftChild(value)) {
                current = current.getLeftChild();
                glubina++;
            } else {
                glubina++;
                current = current.getRightChild();
            }
            if (glubina > 4) {
                return new NodeAndParent(previous, null);
            }
        }
        return new NodeAndParent(null, previous);
    }


    @Override
    public void display() {
        Stack<Node<E>> globalStack = new Stack<>();
        globalStack.push(root);
        int nBlanks = 64;

        boolean isRowEmpty = false;
        System.out.println("................................................................");

        while (!isRowEmpty) {
            Stack<Node<E>> localStack = new Stack<>();

            isRowEmpty = true;
            for (int i = 0; i < nBlanks; i++) {
                System.out.print(" ");
            }

            while (!globalStack.isEmpty()) {
                Node<E> tempNode = globalStack.pop();
                if (tempNode != null) {
                    System.out.print(tempNode.getValue());
                    localStack.push(tempNode.getLeftChild());
                    localStack.push(tempNode.getRightChild());
                    if (tempNode.getLeftChild() != null || tempNode.getRightChild() != null) {
                        isRowEmpty = false;
                    }
                } else {
                    System.out.print("--");
                    localStack.push(null);
                    localStack.push(null);
                }
                for (int j = 0; j < nBlanks * 2 - 2; j++) {
                    System.out.print(" ");
                }
            }

            System.out.println();

            while (!localStack.isEmpty()) {
                globalStack.push(localStack.pop());
            }

            nBlanks /= 2;
        }
        System.out.println("................................................................");

    }


    public boolean isBalanced(Node<E> node) {
        int lh;
        int rh;

        if (node == null) {
            return true;
        }

        lh = height(node.getLeftChild());
        rh = height(node.getRightChild());

        if (Math.abs(lh - rh) <= 1 && isBalanced(node.getLeftChild()) && isBalanced(node.getRightChild())) {
            return true;
        }
        return false;

    }

    private int height(Node<E> node) {
        if (node == null) {
            return 0;
        }

        return 1 + Math.max(height(node.getLeftChild()), height(node.getRightChild()));
    }
}
