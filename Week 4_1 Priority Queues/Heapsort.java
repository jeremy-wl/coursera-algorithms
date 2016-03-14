import java.util.Arrays;
import java.util.Comparator;

/**
 * Created by Jeremy on 3/14/16.
 */
public class Heapsort {

    public static void sort(Comparable[] pq) {
        int N = pq.length;

        // building heap from the bottom up
        for (int k = N/2; k >= 1; k--)
            sink(pq, k, N);

        // sortdown by placing the largest item in the end and rebuilding the heap with remaining items
        while (N > 1) {
            exchange(pq, 1, N--);
            sink(pq, 1, N);
        }
    }

    public static void sink(Comparable[] pq, int k, int N) {
        while (2*k <= N) {
            int j = 2*k;
            if (j < N && less(pq, j, j+1))
                j++;
            if (!less(pq, k, j))
                break;
            exchange(pq, k, j);
            k = j;
        }
    }


    // less() and exchange() needs to be converted from 1-based indexing to 0-based indexing

    private static boolean less(Comparable[] pq, int i, int j) {
        return pq[i-1].compareTo(pq[j-1]) < 0;
    }

    private static void exchange(Comparable[] pq, int i, int j) {
        Comparable temp = pq[i-1];
        pq[i-1] = pq[j-1];
        pq[j-1] = temp;
    }

}
