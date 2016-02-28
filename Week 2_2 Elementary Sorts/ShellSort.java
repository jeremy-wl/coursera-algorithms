import java.util.Arrays;

/**
 * Created by Jeremy on 2/28/16.
 */
public class ShellSort {

    /** Using 3x + 1 increment sequence

     1, 4, 13, 40, 121, 364, ...

     **/

    public static void sort(Comparable[] a) {

        int h = 1;

        while (h * 3 < a.length)
            h = h * 3 + 1;

        while (h >= 1) {

            for (int i = h; i < a.length; i++) {
                for (int j = i; j >= h && Utils.less(a[j], a[j-h]); j-= h) {
                    Utils.exchange(a, j, j-h);
                }
            }

            h /= 3;
        }

    }

}
