import java.util.Iterator;

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

    public static void main(String[] args) {
        BinarySearchTree bst = new BinarySearchTree();
        bst.put("E", 1);
        bst.put("R", 1);
        bst.put("F", 1);
        bst.put("B", 1);
        bst.put("L", 1);
        bst.put("P", 1);

        System.out.println(bst.get("P"));
    }
}
