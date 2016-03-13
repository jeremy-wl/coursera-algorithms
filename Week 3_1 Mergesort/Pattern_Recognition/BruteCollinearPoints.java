package Pattern_Recognition;

import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Jeremy on 3/4/16.
 */
public class BruteCollinearPoints {
    private int segments;
    private Point[] points;
    private LineSegment[] lineSegments;

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

        int N = points.length;

        for (int i = 0; i < N; i++)
            for (int j = i+1; j < N; j++)
                for (int p = j+1; p < N; p++)
                    for (int q = p+1; q < N; q++)
                        if (points[i].slopeTo(points[j]) == (points[j].slopeTo(points[p])))
                            if (points[j].slopeTo(points[p]) == (points[p].slopeTo(points[q]))) {

                                Point[] points2 = {points[i], points[j], points[p], points[q]};
                                Arrays.sort(points2, new Comparator<Point>() {
                                    @Override
                                    public int compare(Point o1, Point o2) {
                                        return o1.compareTo(o2);
                                    }
                                });

                                lineSegments[segments++] = new LineSegment(points2[0], points2[3]);

                                if (segments == lineSegments.length)
                                    resize(segments << 1);

                            }

        if (segments != lineSegments.length)
            resize(segments);
    }

    public int numberOfSegments() {   // the number of line segments
        return segments;
    }

    public LineSegment[] segments() {   // the line segments
        return lineSegments == null ? null : lineSegments.clone();
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