/**
 * Created by Jeremy on 2/27/16.
 */
public class Selection {

    public static void sort(Comparable[] a) {
        for (int pt = 0; pt < a.length; pt++) {
            int min = pt;
            for (int i = pt+1; i < a.length; i++) {
                if (Utils.less(a[i], a[min]))
                    min = i;
            }
            Utils.exchange(a, min, pt);
        }
    }
}
