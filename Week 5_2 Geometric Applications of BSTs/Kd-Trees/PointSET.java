import edu.princeton.cs.algs4.Point2D;
import edu.princeton.cs.algs4.RectHV;
import edu.princeton.cs.algs4.Queue;

import java.util.TreeSet;

/**
 * Created by Jeremy on 3/29/16.
 */
public class PointSET {
    private TreeSet<Point2D> set;

    public PointSET() {                              // construct an empty set of points
        set = new TreeSet<>();
    }

    public boolean isEmpty() {                     // is the set empty?
        return this.isEmpty();
    }

    public int size() {                        // number of points in the set
        return this.size();
    }

    public void insert(Point2D p) {             // add the point to the set (if it is not already in the set)
        if (p == null)
            throw new java.lang.NullPointerException();
        this.set.add(p);
    }

    public boolean contains(Point2D p) {           // does the set contain point p?
        if (p == null)
            throw new java.lang.NullPointerException();
        return this.set.contains(p);
    }

    public void draw() {                        // draw all points to standard draw
        for (Point2D p : this.set)
            p.draw();
    }


    public Iterable<Point2D> range(RectHV rect) {            // all points that are inside the rectangle
        if (rect == null)
            throw new java.lang.NullPointerException();
        Queue<Point2D> q = new Queue<>();
        for (Point2D p : this.set) {
            if (!(p.x() < rect.xmin()) && !(p.x() > rect.xmax()
                    && !(p.y() < rect.ymin() && !(p.y() > rect.ymax()))))
                q.enqueue(p);
        }
        return q;
    }

    public Point2D nearest(Point2D p) {            // a nearest neighbor in the set to point p; null if the set is empty
        if (p == null)
            throw new java.lang.NullPointerException();
        double distMin = Double.POSITIVE_INFINITY;
        Point2D nearestP = null;
        for (Point2D thatP : this.set) {
            double dist = p.distanceTo(thatP);
            if (dist < distMin) {
                distMin = dist;
                nearestP = thatP;
            }
        }
        return nearestP;
    }


    public static void main(String[] args) {                 // unit testing of the methods (optional)

        PointSET t = new PointSET();

        t.insert(new Point2D(0.8, 0.8));
        t.insert(new Point2D(0.5, 0.7));
        t.insert(new Point2D(0.78, 0.8));
        t.insert(new Point2D(0.6, 0.68));
        t.insert(new Point2D(0.4, 0.68));
        t.insert(new Point2D(0.6, 0.68));

//        System.out.println(t.nearest(new Point2D(0.3, 0.401)));

        for (Point2D p : t.range(new RectHV(0.5,0.5,0.8,0.8)))
            System.out.println(p);

    }

}
