/**
 * Created by Jeremy on 2/27/16.
 */
public class InsertionSort {

    public static void sort(Comparable[] a) {
        for (int i = 0; i < a.length; i++) {
            for (int j = i; j > 0; j--) {
                if (Utils.less(a[j], a[j-1]))
                    Utils.exchange(a, j, j-1);
                else break;  // comparing needs to stop once the item gets inserted in the right spot
            }
        }
    }

}
