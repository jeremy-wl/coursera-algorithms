/**
 * Created by Jeremy on 3/3/16.
 */


/**
 *
 * Partition array so that:
    - Entry A[j] is in place.
    - No larger entry to the left of j.
    - No smaller entry to the right of j.

 * Repeat in one subarray, depending on j; finished when j equals k.

 *
 * **/

public class Selection {  // find the (k)th largest element

    public static Comparable select(int[] A, int k) {

//        StdRandom.shuffle(A);
        int lo = 0, hi = A.length - 1;

        while (hi > lo) {

            int j = Quicksort.partition(A, lo, hi);

            if (j > k)
                hi = j - 1;
            else if (j < k)
                lo = j + 1;
            else
                return A[k];

        }

        return A[k];
    }

}
