/**
 * Created by Jeremy on 3/3/16.
 */


/**
 *
 *  Let v be partitioning item A[lo].

    Scan i from left to right.
     – (A[i] < v): exchange A[lt] with A[i]; increment both lt and i
     – (A[i] > v): exchange A[gt] with A[i]; decrement gt
     – (A[i] == v): increment i

 *
 * */


public class ThreeWayQuicksort {

    public static void sort(int[] A, int lo, int hi) {

        if (lo >= hi)
            return;
        int lt = lo, gt = hi;
        int v = A[lo];  // v = A[lo] as the partitioning element
        int i = lo;

        while (i <= gt) {

            int cmp = Integer.compare(A[i], v);

            if (cmp < 0)
                swap(A, lt++, i++);
            else if (cmp > 0)
                swap(A, gt--, i);
            else
                i++;

        }

        sort(A, lo, lt-1);
        sort(A, gt+1, hi);

    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void sort(int[] A) {
        sort(A, 0, A.length-1);
    }

}
