/**
 * Created by Jeremy on 2/27/16.
 */
public class SelectionSort {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            int min = i;
            for (int j = i+1; j < a.length; j++) {
                if (Utils.less(a[j], a[min]))
                    min = j;
            }
            Utils.exchange(a, min, i);
        }
    }
}
