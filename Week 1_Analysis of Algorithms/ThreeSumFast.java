import java.util.Arrays;

/**
 * Created by Jeremy on 1/24/16.
 */
public class ThreeSumFast {

    public static boolean containsDuplicates(int[] a) {
        for (int i = 1; i < a.length; i++) {
            if (a[i-1] == a[i])
                return true;
        }
        return false;
    }

    public static void print(int[] a) {
        int N = a.length;
        Arrays.sort(a);
        if (containsDuplicates(a)) throw new IllegalArgumentException("array contains duplicate integers");
        for (int i = 0; i < N; i++) {
            for (int j = i + 1; j < N; j++) {
                int val = Arrays.binarySearch(a, j+1, N, -(a[i] + a[j]));
                if (val > 0)
                    System.out.printf("%d %d %d \n", a[i], a[j], a[val]);
            }
        }
    }

    public static void main(String[] args) {
        int[] arr = {-10,-3,-2,0,4,5,8,9,13,14,15};
        ThreeSumFast.print(arr);

        /***
         * Outputs
         * -10 -3 13
         * -3 -2 5
         */

    }
}
