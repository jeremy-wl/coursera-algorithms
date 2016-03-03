/**
 * Created by Jeremy on 3/3/16.
 */


/**
 *
 * Partition array so that:
    - Entry a[j] is in place.
    - No larger entry to the left of j.
    - No smaller entry to the right of j.

 * Repeat in one subarray, depending on j; finished when j equals k.

 *
 * **/

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
