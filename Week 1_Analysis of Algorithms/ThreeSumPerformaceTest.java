/**
 * Created by Jeremy on 1/24/16.
 */
public class ThreeSumPerformaceTest {

    public static void main(String[] args) {

        In in = new In(args[0]);
        int[] a = in.readAllInts();
        double slower, faster;

        Stopwatch timer1 = new Stopwatch();
        ThreeSum.print(a);
        slower = timer1.elapsedTime();

        Stopwatch timer2 = new Stopwatch();
        ThreeSumFast.print(a);
        faster = timer2.elapsedTime();

        System.out.printf("Three Sum with triple loop takes %f seconds. \n", slower);
        System.out.printf("Three Sum with Binary Search takes %f seconds. \n", faster);

    }
}




class Stopwatch {

    private final long start;

    /**
     * Initialize a stopwatch object.
     */

    public Stopwatch() {
        start = System.currentTimeMillis();
    }

    /**
     * Returns the elapsed time (in seconds) since this object was created.
     */

    public double elapsedTime() {
        long now = System.currentTimeMillis();
        return (now - start) / 1000.0;
    }

}
