/**
 * Created by Jeremy on 2/29/16.
 */
public class Mergesort {

    public static void merge(int[] A, int aux[], int lo, int mid, int hi) {

        // precondition: A[lo .. mid] and A[mid+1 .. hi] are sorted subarrays
        assert Utils.isSorted(A, lo, mid);
        assert Utils.isSorted(A, mid+1, hi);

        // copy to aux[]
        for (int i = 0; i < A.length; i++) {
            aux[i] = A[i];
        }


        // merge back to A[]
        int i = lo, j = mid + 1;

        for (int k = lo; k <= hi; k++) {
            if (i > mid)
                A[k] = aux[j++];
            else if (j > hi)
                A[k] = aux[i++];
            else if (aux[i] < aux[j])
                A[k] = aux[i++];
            else
                A[k] = aux[j++];
        }

        // post condition: A[lo .. hi] is sorted
        assert Utils.isSorted(A, lo, hi);

    }

    public static void sort(int[] A, int[] aux, int lo, int hi) {
        if (hi <= lo)  // same as Binary Search
            return;
        int mid = lo + (hi - lo) / 2;  // same as Binary Search
        sort(A, aux, lo, mid);
        sort(A, aux, mid+1, hi);
        merge(A, aux, lo, mid, hi);
    }

    public static void sort(int[] A) {
        int[] aux = new int[A.length];
        sort(A, aux, 0, A.length-1);
    }

}
