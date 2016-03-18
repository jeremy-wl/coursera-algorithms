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

        public Node(Key key, Value val) {
            this.key = key;
            this.val = val;
        }
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
    }

    private Node put(Node root, Key key, Value val) {
        if (root == null)
            return new Node(key, val);

        int cmp = key.compareTo(root.key);

        if (cmp < 0)
            root.left = put(root.left, key, val);
        else if (cmp > 0)
            root.right = put(root.right, key, val);
        else
            root.val = val;

        return root;
    }

    public Value get(Key key) {
        while (root != null) {
            int cmp = key.compareTo(root.key);
            if (cmp < 0)
                root = root.left;
            if (cmp > 0)
                root = root.right;
            if (cmp == 0)
                return root.val;
        }
        return null;
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
