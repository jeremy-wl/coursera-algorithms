package Pattern_Recognition;

import java.util.Arrays;

/**
 * Created by Jeremy on 3/4/16.
 */
public class FastCollinearPoints {
    private int segments;
    private Point[] points;
    private LineSegment[] lineSegments;

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

        Point[] points2 = new Point[N];
        Point[] points = new Point[N];

        System.arraycopy(this.points, 0, points2, 0, N);
        System.arraycopy(this.points, 0, points, 0, N);

        Arrays.sort(points2);

        for (int i = 0; i < N; i++) {

            int count = 1;
            Point p = points2[i];

            Arrays.sort(points);
            Arrays.sort(points, p.slopeOrder());

            for (int j = 1; j < N; j++) {
                Point p0 = points[j-1];
                double k0 = p0.slopeTo(p);

                while (p.slopeTo(points[j]) == k0 && j < N) {
                    j++;
                    count++;
                    if (j >= N)
                        break;
                }

                if (count >= 3 && p.compareTo(p0) < 0) {  // 4 pts altogether with p; create a new seg only if p is lower than any other pts on the same line

                    lineSegments[segments++] = new LineSegment(p, points[j-1]);

                    if (segments == lineSegments.length)
                        resize(segments << 1);

                }

                count = 1;
            }

        }

        if (segments != lineSegments.length)
            resize(segments);

        return lineSegments;
    }

    private void resize(int capacity) {
        LineSegment[] temp = lineSegments;
        lineSegments = new LineSegment[capacity];

        for (int i = 0; i < temp.length; i++) {
            if (temp[i] == null)
                break;
            lineSegments[i] = temp[i];
        }
    }

}
