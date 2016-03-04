package Pattern_Recognition;

/******************************************************************************
 *  Compilation:  javac Point.java
 *  Execution:    java Point
 *  Dependencies: none
 *
 *  An immutable data type for points in the plane.
 *  For use on Coursera, Algorithms Part I programming assignment.
 *
 ******************************************************************************/

import java.util.Comparator;
import edu.princeton.cs.algs4.StdDraw;
import edu.princeton.cs.algs4.StdRandom;

public class Point implements Comparable<Point> {

    private final int x;     // x-coordinate of this point
    private final int y;     // y-coordinate of this point

    /**
     * Initializes a new point.
     *
     * @param  x the <em>x</em>-coordinate of the point
     * @param  y the <em>y</em>-coordinate of the point
     */
    public Point(int x, int y) {
        /* DO NOT MODIFY */
        this.x = x;
        this.y = y;
    }

    /**
     * Draws this point to standard draw.
     */
    public void draw() {
        /* DO NOT MODIFY */
        StdDraw.point(x, y);
    }

    /**
     * Draws the line segment between this point and the specified point
     * to standard draw.
     *
     * @param that the other point
     */
    public void drawTo(Point that) {
        /* DO NOT MODIFY */
        StdDraw.line(this.x, this.y, that.x, that.y);
    }

    /**
     * Returns the slope between this point and the specified point.
     * Formally, if the two points are (x0, y0) and (x1, y1), then the slope
     * is (y1 - y0) / (x1 - x0). For completeness, the slope is defined to be
     * +0.0 if the line segment connecting the two points is horizontal;
     * Double.POSITIVE_INFINITY if the line segment is vertical;
     * and Double.NEGATIVE_INFINITY if (x0, y0) and (x1, y1) are equal.
     *
     * @param  that the other point
     * @return the slope between this point and the specified point
     */
    public double slopeTo(Point that) {

        int x0 = this.x, y0 = this.y;
        int x1 = that.x, y1 = that.y;

        if (x0 == x1 && y0 != y1)
            return Double.POSITIVE_INFINITY;
        else if (x0 == x1 && y0 == y1)
            return Double.NEGATIVE_INFINITY;
        else
            return (double) (y0-y1) / (x0-x1);
    }

    /**
     * Compares two points by y-coordinate, breaking ties by x-coordinate.
     * Formally, the invoking point (x0, y0) is less than the argument point
     * (x1, y1) if and only if either y0 < y1 or if y0 = y1 and x0 < x1.
     *
     * @param  that the other point
     * @return the value <tt>0</tt> if this point is equal to the argument
     *         point (x0 = x1 and y0 = y1);
     *         a negative integer if this point is less than the argument
     *         point; and a positive integer if this point is greater than the
     *         argument point
     */
    public int compareTo(Point that) {

        int x0 = this.x, y0 = this.y;
        int x1 = that.x, y1 = that.y;

        if (x0 == x1 && y0 == y1)
            return 0;
        else if (y0 < y1 || y0 == y1 && x0 < x1)
            return -1;
        else
            return 1;
    }

    /**
     * Compares two points by the slope they make with this point.
     * The slope is defined as in the slopeTo() method.
     *
     * @return the Comparator that defines this ordering on points
     */
    public static Comparator<Point> slopeOrder() {
        return new BySlope();
    }

    private static class BySlope implements Comparator<Point> {

        public int compare(Point p0, Point p1) {
            return p0.compareTo(p1);
        }

    }


    /**
     * Returns a string representation of this point.
     * This method is provide for debugging;
     * your program should not rely on the format of the string representation.
     *
     * @return a string representation of this point
     */
    public String toString() {
        /* DO NOT MODIFY */
        return "(" + x + ", " + y + ")";
    }

    /**
     * Unit tests the Point data type.
     */
    public static void main(String[] args) {

        // TODO main

        Point[] points = new Point[200];
        for (int i = 0; i < 200; i++) {
            int r1 = StdRandom.uniform(200);
            int r2 = StdRandom.uniform(200);
            points[i] = new Point(r1, r2);
        }

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 200);
        StdDraw.setYscale(0, 200);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.01);

        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        BruteCollinearPoints collinear = new BruteCollinearPoints(points);

        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.setPenRadius(0.001);

        for (LineSegment segment : collinear.segments()) {

            if (segment == null)
                break;

            System.out.println(segment);

            segment.draw();
        }

    }
}
