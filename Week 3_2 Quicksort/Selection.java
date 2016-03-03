/**
 * Created by Jeremy on 3/3/16.
 */

public class Selection {  // find the (k)th largest element

    public static Comparable select(Comparable[] a, int k) {

        StdRandom.shuffle(a);
        int lo = 0, hi = a.length - 1;

        while (hi > lo) {

            int j = Quicksort.partition(a, lo, hi);

            if (j > k)
                hi = j - 1;
            else if (j < k)
                lo = j + 1;
            else
                return a[k];

        }

        return a[k];
    }

}
