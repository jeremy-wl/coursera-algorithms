package Pattern_Recognition;

import java.util.Arrays;
import java.util.Stack;

/**
 * Created by Jeremy on 3/4/16.
 */
public class FastCollinearPoints {
    int segments;
    Point[] points;
    LineSegment[] lineSegments;

    public FastCollinearPoints(Point[] points) {    // finds all line segments containing 4 or more points
        if (points == null)
            throw new java.lang.NullPointerException("Point[] cannot be null.");

        for (int i = 0; i < points.length; i++) {
            if (points[i] == null)
                throw new java.lang.NullPointerException("Point cannot be null.");
            for (int j = i+1; j < points.length; j++) {
                if (points[i].compareTo(points[j]) == 0)
                    throw new java.lang.IllegalArgumentException("No duplicate points are allowed here.");
            }
        }

        this.points = points;
        segments = 0;
        lineSegments = new LineSegment[2];
    }

    public int numberOfSegments() {       // the number of line segments
        return segments;
    }

    public LineSegment[] segments() {               // the line segments
        int N = points.length;

        Stack<Point> s = new Stack<>();
        for (Point p: points)
            s.push(p);

        while (!s.isEmpty()) {

            Point p = s.pop();
//            Arrays.sort(points);

            Arrays.sort(points, p.slopeOrder());
            int count = 1;

            for (int i = 1; i < N; i++) {
                Point p0 = points[i-1];
                double k0 = p0.slopeTo(p);

                while (p.slopeTo(points[i++]) == k0) {
                    count++;
                    if (i >= N)
                        break;
                }

                if (count >= 4) {

                    lineSegments[segments++] = new LineSegment(p, points[i-1]);

                    if (segments == lineSegments.length)
                        resize(segments << 1);

                }

                count = 1;
            }

        }

        return lineSegments;
    }

    private void resize(int capacity) {
        LineSegment[] temp = lineSegments;
        lineSegments = new LineSegment[capacity];

        for (int i = 0; i < temp.length; i++) {
            lineSegments[i] = temp[i];
        }
    }

}
