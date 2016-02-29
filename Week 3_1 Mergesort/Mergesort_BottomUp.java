/**
 * Created by Jeremy on 2/29/16.
 */
public class Mergesort_BottomUp {

    public static void merge(Comparable[] a, Comparable aux[], int lo, int mid, int hi) {

        for (int i = 0; i < a.length; i++) {
            aux[i] = a[i];
        }

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

    }

    public static void sort(Comparable[] a) {

        int N = a.length;
        Comparable[] aux = new Comparable[N];

        for (int sz = 1; sz < N; sz = sz * 2) {
            for (int lo = 0; lo+sz < N; lo += sz*2) {
                merge(a, aux, lo, lo+sz-1, Math.min(lo+2*sz-1, N-1)); // N-1 is used in the end when sz > N
            }
        }

    }

}
