/**
 * Created by Jeremy on 3/2/16.
 */
public class Quicksort {

    public static int partition(Comparable[] a, int lo, int hi) {

        int i = lo + 1, j = hi;

        while (true) {

            while (Utils.less(a[i], a[lo])) { // find the left item to swap
                i++;
                if (i == hi)
                    break;
            }
            while (Utils.less(a[lo], a[j])) { // find the right item to swap
                j--;
                if (j == lo)
                    break;
            }

            if (j <= i)
                break;

            Utils.exchange(a, i, j);
        }
        Utils.exchange(a, lo, j);
        return j;
    }

    public static void sort(Comparable[] a) {
        StdRandom.shuffle(a);  // shuffling needed for performance guarantee
        sort(a, 0, a.length-1);
    }

    public static void sort(Comparable[] a, int lo, int hi) {
        if (lo >= hi)
            return;
        int j = partition(a, lo, hi);
        sort(a, lo, j-1);
        sort(a, j+1, hi);
    }

}
