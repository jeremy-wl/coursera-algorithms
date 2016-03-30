import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.RectHV;

/**
 * Created by Jeremy on 3/29/16.
 */
public class KdTree {
    private Node root;
    private int N;
    private Queue<Point2D> q;

    private static class Node {
        private Point2D p;      // the point
        private RectHV rect;    // the axis-aligned rectangle corresponding to this node
        private Node lb;        // the left/bottom subtree
        private Node rt;        // the right/top subtree

        public Node(Point2D thatP) {
            this.p = thatP;
        }

    }

    public KdTree() {                              // construct an empty set of points
        N = 0;
        q = new Queue<>();
    }

    public boolean isEmpty() {                     // is the set empty?
        return root == null;
    }

    public int size() {                        // number of points in the set
        return N;
    }

    public void insert(Point2D p) {             // add the point to the set (if it is not already in the set)
        if (contains(p))
            return;
        root = insert(root, p, 0, 0, 1, 0, 1);
        N++;
    }

    private Node insert(Node n, Point2D p, int level, double b, double t, double l, double r) {
        if (n == null) {
            n = new Node(p);
            n.rect = new RectHV(l, b, r, t);
            return n;
        }
        if (level % 2 == 0) {         // even level
            if (n.p.x() >= p.x())
                n.lb = insert(n.lb, p, level+1, b, t, l, n.p.x());
            else
                n.rt = insert(n.rt, p, level+1, b, t, n.p.x(), r);
        }
        else {                        // odd level
            if (n.p.y() >= p.y())
                n.lb = insert(n.lb, p, level+1, b, n.p.y(), l, r);
            else
                n.rt = insert(n.rt, p, level+1, n.p.y(), t, l, r);
        }

        return n;
    }

    public boolean contains(Point2D p) {           // does the set contain point p?
        int level = 0;
        Node n = root;

        while (true) {
            if (n == null)
                return false;
            if (p.x() == n.p.x() && p.y() == n.p.y())
                return true;

            if (level % 2 == 0) {         // even level
                if (n.p.x() >= p.x())
                    n = n.lb;
                else
                    n = n.rt;
            }
            else {                        // odd level
                if (n.p.y() >= p.y())
                    n = n.lb;
                else
                    n = n.rt;
            }

            level++;
        }
    }

    public void draw() {                        // draw all points to standard draw
        inorder(root);
        for (Point2D p : q)
            p.draw();
    }

    private void inorder(Node root) {
        if (root.lb != null)
            inorder(root.lb);

        System.out.println(root.p);
        q.enqueue(root.p);

        if (root.rt != null)
            inorder(root.rt);
    }

    public Iterable<Point2D> range(RectHV rect) {            // all points that are inside the rectangle
        Queue<Point2D> q = new Queue<>();
        getPoints(root, rect, q);
        return q;
    }

    private void getPoints(Node root, RectHV rect, Queue<Point2D> q) {
        if (root == null || !root.rect.intersects(rect))
            return;
        if (isInRectangle(root.p, rect)) {
            q.enqueue(root.p);
        }
        if (root.rect.intersects(rect)) {
            getPoints(root.lb, rect, q);
            getPoints(root.rt, rect, q);
        }
    }

    private boolean isInRectangle(Point2D p, RectHV r) {
        if (p.x() >= r.xmin() && p.x() <= r.xmax()
                && p.y() >= r.ymin() && p.y() <= r.ymax())
            return true;
        else
            return false;
    }

    public Point2D nearest(Point2D p) {            // a nearest neighbor in the set to point p; null if the set is empty
        if (root == null)
            return null;
        Node nearest = nearest(root, p, root, 0);
        return nearest.p;
    }

    /**
     *
     * The effectiveness of the pruning rule depends on quickly finding a nearby point.
     *
     * To do this, organize your recursive method so that when there are two possible subtrees to go down,
     * you always choose the subtree that is on the same side of the splitting line as the query point as the first subtree
     * to explore—the closest point found while exploring the first subtree may enable pruning of the second subtree.
     *
     * **/

    private Node nearest(Node root, Point2D p, Node nearest, int level) {
        double nearestDist = nearest.p.distanceTo(p);
        if (root == null || nearestDist < root.rect.distanceTo(p))
            return nearest;
        else if (nearestDist > root.p.distanceTo(p))
            nearest = root;

        if (level % 2 == 0) {
            if (p.x() <= root.p.x()) {
                nearest = nearest(root.lb, p, nearest, level+1);
                nearest = nearest(root.rt, p, nearest, level+1);
            }
            else {
                nearest = nearest(root.rt, p, nearest, level+1);
                nearest = nearest(root.lb, p, nearest, level+1);
            }
        }
        else {
            if (p.y() <= root.p.y()) {
                nearest = nearest(root.lb, p, nearest, level+1);
                nearest = nearest(root.rt, p, nearest, level+1);
            }
            else {
                nearest = nearest(root.rt, p, nearest, level+1);
                nearest = nearest(root.lb, p, nearest, level+1);
            }
        }

        return nearest;
    }

    public static void main(String[] args) {

        KdTree t = new KdTree();


        t.insert(new Point2D(0.975528, 0.654508));
        t.insert(new Point2D(0.206107, 0.095492));
        t.insert(new Point2D(0.500000, 0.000000)); //1
        t.insert(new Point2D(0.793893, 0.095492)); //2

//        System.out.println(t.nearest(new Point2D(0.3, 0.401)));

        for (Point2D p : t.range(new RectHV(0.49, 0, 0.8, 0.1)))
            System.out.println(p);

    }

}
