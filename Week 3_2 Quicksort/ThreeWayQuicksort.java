import java.util.Arrays;

/**
 * Created by Jeremy on 3/3/16.
 */
public class ThreeWayQuicksort {

    public static void sort(Comparable[] a, int lo, int hi) {

        if (lo >= hi)
            return;
        int lt = lo, gt = hi;
        Comparable v = a[lo];  // v = a[lo] as the partitioning element
        int i = lo;

        while (i <= gt) {

            int cmp = a[i].compareTo(v);

            if (cmp < 0)
                Utils.exchange(a, lt++, i++);
            else if (cmp > 0)
                Utils.exchange(a, gt--, i);
            else
                i++;

        }

        sort(a, lo, lt-1);
        sort(a, gt+1, hi);

    }

    public static void sort(Comparable[] a) {
        sort(a, 0, a.length-1);
    }

}
