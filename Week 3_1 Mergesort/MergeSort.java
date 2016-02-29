/**
 * Created by Jeremy on 2/29/16.
 */
public class Mergesort {

    public static void merge(Comparable[] a, Comparable aux[], int lo, int mid, int hi) {

        // precondition: a[lo .. mid] and a[mid+1 .. hi] are sorted subarrays
        assert Utils.isSorted(a, lo, mid);
        assert Utils.isSorted(a, mid+1, hi);

        // copy to aux[]
        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }


        // merge back to a[]
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                a[k] = aux[j++];
            else if (j > hi)
                a[k] = aux[i++];
            else if (Utils.less(aux[i], aux[j]))
                a[k] = aux[i++];
            else
                a[k] = aux[j++];
        }

        // postcondition: a[lo .. hi] is sorted
        assert Utils.isSorted(a, lo, hi);

    }

    public static void sort(Comparable[] a, Comparable[] aux, int lo, int hi) {
        if (hi <= lo)  // same as Binary Search
            return;
        int mid = lo + (hi - lo) / 2;  // same as Binary Search
        sort(a, aux, lo, mid);
        sort(a, aux, mid+1, hi);
        merge(a, aux, lo, mid, hi);
    }

    public static void sort(Comparable[] a) {
        Comparable[] aux = new Comparable[a.length];
        sort(a, aux, 0, a.length-1);
    }

}
