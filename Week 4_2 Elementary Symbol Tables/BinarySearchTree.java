import edu.princeton.cs.algs4.Queue;

/**
 * Created by Jeremy on 3/15/16.
 */
public class BinarySearchTree<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int count;

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node root, Key key, Value val) {
        if (root == null) {
            Node x = new Node(key, val);
            x.count = 1;
            return x;
        }

        int cmp = key.compareTo(root.key);

        if (cmp < 0)
            root.left = put(root.left, key, val);
        else if (cmp > 0)
            root.right = put(root.right, key, val);
        else
            root.val = val;
        root.count = 1 + size(root.left) + size(root.right);

        return root;
    }

    public Value get(Key key) {
        while (root != null) {
            int cmp = key.compareTo(root.key);
            if (cmp < 0)
                root = root.left;
            else if (cmp > 0)
                root = root.right;
            else if (cmp == 0)
                return root.val;
        }
        return null;
    }

    public int size() {
        return size(root);
    }

    private int size(Node x) {
        if (x == null)
            return 0;
        return x.count;
    }

    public Iterable<Key> keys() {
        Queue<Key> q = new Queue<Key>();
        inorder(root, q);
        return q;
    }

    private void inorder(Node root, Queue<Key> q) {
        if (root == null)
            return;

        inorder(root.left, q);
        q.enqueue(root.key);
        inorder(root.right, q);
    }

    public void delMin() {
        root = delMin(root);
    }


    private Node delMin(Node root) {

        // if the node does not have left child, return its right child to delete the current node
        if (root.left == null)
            return root.right;

        root.left = delMin(root.left);
        root.count = 1 + size(root.left) + size(root.right);

        return root;
    }

    public void delete(Key key) {
        root = delete(root, key);
    }

    public boolean isEmpty() {
        return size() == 0;
    }

    public Key min() {
        if (isEmpty())
            return null;
        return min(root).key;
    }

    private Node min(Node root) {
        if (root.left == null)
            return root;
        else
            return min(root.left);
    }

    public Key max() {
        if (isEmpty())
            return null;
        return max(root).key;
    }

    private Node max(Node root) {
        if (root.right == null)
            return root;
        else
            return max(root.right);
    }

    private Node delete(Node root, Key key) {
        if (root == null)
            return null;

        int cmp = key.compareTo(root.key);

        if (cmp < 0)
            root.left = delete(root.left, key);
        else if (cmp > 0)
            root.right = delete(root.right, key);
        else {
            if (root.right == null)
                return root.left;

            Node t = root;
            root = min(t.right);
            root.right = delMin(t.right);
            root.left = t.left;
        }

        root.count = 1 + size(root.left) + size(root.right);
        return root;
    }

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.put("E", 1);
        bst.put("R", 1);
        bst.put("F", 1);
        bst.put("B", 1);
        bst.put("L", 1);
        bst.put("P", 1);

        for (Object a : bst.keys()) {
            System.out.println(a);
        }
    }
}
