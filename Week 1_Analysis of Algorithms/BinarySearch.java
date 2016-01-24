/**
 * Created by Jeremy on 1/24/16.
 */

/**
 *
   Check out a fixed BS bug @

   http://googleresearch.blogspot.com/2006/06/extra-extra-read-all-about-it-nearly.html
 *
 * */

public class BinarySearch {

    public static int binarySearch(int[] a, int key) {
        int lo = 0, hi = a.length - 1;
        while(lo <= hi) {
            int mid = lo + (hi - lo) / 2;
            if (key < a[mid])
                hi = mid - 1;
            else if (key > a[mid])
                lo = mid + 1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] arr = {0, 4, 9, 13, 34, 56, 67, 69, 73, 78, 84, 89, 92, 134, 135};
        System.out.println(BinarySearch.binarySearch(arr, 34));
    }
}
