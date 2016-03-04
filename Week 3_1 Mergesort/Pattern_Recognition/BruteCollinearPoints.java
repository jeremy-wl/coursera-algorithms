package Pattern_Recognition;

import java.util.Arrays;

/**
 * Created by Jeremy on 3/4/16.
 */
public class BruteCollinearPoints {
    int segments;
    Point[] points;
    LineSegment[] lineSegments;

    public BruteCollinearPoints(Point[] points) {   // finds all line segments containing 4 points
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

    public int numberOfSegments() {   // the number of line segments
        return segments;
    }

    public LineSegment[] segments() {   // the line segments
        int N = points.length;

        for (int i = 0; i < N; i++) {
            for (int j = i+1; j < N; j++) {
                for (int p = j+1; p < N; p++) {
                    for (int q = p+1; q < N; q++) {

                        if (points[i].slopeTo(points[j]) == (points[j].slopeTo(points[p])))
                            if (points[j].slopeTo(points[p]) == (points[p].slopeTo(points[q]))) {

                                Point[] points2 = {points[i], points[j], points[p], points[q]};
                                Arrays.sort(points2, Point.slopeOrder());

                                lineSegments[++segments-1] = new LineSegment(points2[0], points2[3]);

                                if (segments == lineSegments.length)
                                    resize(segments << 1);

                            }
                    }
                }
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
