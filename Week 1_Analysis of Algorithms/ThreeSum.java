/**
 * Created by Jeremy on 1/23/16.
 */


public class ThreeSum {

    public static void print(int[] a) {
        int count = 0;
        int N = a.length;
        for (int i = 0; i <= N; i++) {
            for (int j = i + 1; j < N; j++) {
                for(int k = j + 1; k < N; k++) {
                    if (a[i] + a[j] + a[k] != 0)  continue;
                    System.out.printf("%d: %d %d %d\n", count+1, a[i], a[j], a[k]);
                    count++;
                }
            }
        }
        System.out.printf("Found %d pair(s).\n\n", count);
    }

    public static void main(String[] args) {
        int[] arr = {-1,0,1,2,-1,-4};
        ThreeSum.print(arr);
    }
}
