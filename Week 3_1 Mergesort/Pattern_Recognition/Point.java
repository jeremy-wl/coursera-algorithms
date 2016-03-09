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
    public Comparator<Point> slopeOrder() {
        return new BySlope();
    }

    private class BySlope implements Comparator<Point> {
        public int compare(Point p1, Point p2) {

            double diff = p1.slopeTo(Point.this) - p2.slopeTo(Point.this);

            if (diff > 0)
                return 1;
            else if (diff < 0)
                return -1;
            else
                return 0;
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

        Point[] points = new Point[100];

        points[0] = new Point(29000, 8000);
        points[1] = new Point(5000, 22000);
        points[2] = new Point(3000, 29000);
        points[3] = new Point(18000, 21000);
        points[4] = new Point(22000, 25000);
        points[5] = new Point(3000, 11000);
        points[6] = new Point(2000, 13000);
        points[7] = new Point(19000, 31000);
        points[8] = new Point(19000, 6000);
        points[9] = new Point(21000, 0);
        points[10] = new Point(11000, 26000);
        points[11] = new Point(17000, 0);
        points[12] = new Point(9000, 25000);
        points[13] = new Point(5000, 0);
        points[14] = new Point(21000, 5000);
        points[15] = new Point(26000, 10000);
        points[16] = new Point(17000, 27000);
        points[17] = new Point(15000, 3000);
        points[18] = new Point(23000, 30000);
        points[19] = new Point(12000, 29000);
        points[20] = new Point(16000, 16000);
        points[21] = new Point(14000, 29000);
        points[22] = new Point(25000, 20000);
        points[23] = new Point(26000, 5000);
        points[24] = new Point(10000, 31000);
        points[25] = new Point(4000, 12000);
        points[26] = new Point(30000, 21000);
        points[27] = new Point(1000, 0);
        points[28] = new Point(8000, 13000);
        points[29] = new Point(9000, 13000);
        points[30] = new Point(1000, 29000);
        points[31] = new Point(21000, 11000);
        points[32] = new Point(1000, 27000);
        points[33] = new Point(13000, 27000);
        points[34] = new Point(30000, 19000);
        points[35] = new Point(23000, 5000);
        points[36] = new Point(12000, 5000);
        points[37] = new Point(12000, 20000);
        points[38] = new Point(10000, 5000);
        points[39] = new Point(12000, 31000);
        points[40] = new Point(0, 8000);
        points[41] = new Point(29000, 16000);
        points[42] = new Point(22000, 29000);
        points[43] = new Point(15000, 16000);
        points[44] = new Point(29000, 10000);
        points[45] = new Point(7000, 25000);
        points[46] = new Point(31000, 11000);
        points[47] = new Point(25000, 24000);
        points[48] = new Point(12000, 4000);
        points[49] = new Point(31000, 26000);
        points[50] = new Point(29000, 29000);
        points[51] = new Point(13000, 2000);
        points[52] = new Point(11000, 7000);
        points[53] = new Point(22000, 20000);
        points[54] = new Point(9000, 9000);
        points[55] = new Point(11000, 11000);
        points[56] = new Point(17000, 14000);
        points[57] = new Point(22000, 9000);
        points[58] = new Point(16000, 19000);
        points[59] = new Point(3000, 1000);
        points[60] = new Point(1000, 1000);
        points[61] = new Point(26000, 14000);
        points[62] = new Point(3000, 2000);
        points[63] = new Point(11000, 12000);
        points[64] = new Point(24000, 29000);
        points[65] = new Point(9000, 3000);
        points[66] = new Point(24000, 23000);
        points[67] = new Point(17000, 1000);
        points[68] = new Point(17000, 20000);
        points[69] = new Point(0, 18000);
        points[70] = new Point(6000, 0);
        points[71] = new Point(3000, 23000);
        points[72] = new Point(1000, 16000);
        points[73] = new Point(20000, 30000);
        points[74] = new Point(15000, 5000);
        points[75] = new Point(23000, 31000);
        points[76] = new Point(28000, 31000);
        points[77] = new Point(12000, 18000);
        points[78] = new Point(22000, 21000);
        points[79] = new Point(13000, 7000);
        points[80] = new Point(5000, 18000);
        points[81] = new Point(6000, 8000);
        points[82] = new Point(9000, 11000);
        points[83] = new Point(30000, 8000);
        points[84] = new Point(25000, 9000);
        points[85] = new Point(29000, 25000);
        points[86] = new Point(3000, 26000);
        points[87] = new Point(15000, 2000);
        points[88] = new Point(21000, 14000);
        points[89] = new Point(12000, 24000);
        points[90] = new Point(6000, 22000);
        points[91] = new Point(24000, 5000);
        points[92] = new Point(8000, 24000);
        points[93] = new Point(17000, 31000);
        points[94] = new Point(24000, 10000);
        points[95] = new Point(13000, 9000);
        points[96] = new Point(15000, 18000);
        points[97] = new Point(21000, 15000);
        points[98] = new Point(19000, 10000);
        points[99] = new Point(26000, 6000);

        // draw the points
        StdDraw.show(0);
        StdDraw.setXscale(0, 32678);
        StdDraw.setYscale(0, 32678);
        StdDraw.setPenColor(StdDraw.RED);
        StdDraw.setPenRadius(0.01);

        for (Point p : points) {
            p.draw();
        }
        StdDraw.show();

        FastCollinearPoints collinear = new FastCollinearPoints(points);

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
