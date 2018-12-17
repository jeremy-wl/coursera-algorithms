/**
 * Created by Jeremy on 3/2/16.
 */


import java.util.*;

/**
 *
 * Repeat until i and j pointers cross.
    - Scan i from left to right so long as (A[i] < A[lo]).
    - Scan j from right to left so long as (A[j] > A[lo]).
    - Exchange A[i] with A[j].

 * When pointers cross.
    - Exchange A[lo] with A[j].

 *
 * **/

public class Quicksort {

    public static int partition(int[] A, int L, int R) {
        int i = L, j = R+1;
        while (true) {
            while (++i < R && A[i] < A[L]);  // 选取 A[L] 为 pivot, 左指针一直往右移直到 A[i] >= A[L]
            while (--j > L && A[j] > A[L]);  // ++i 是为了跳过第一个指针 L
            if (i >= j) break;               // [2,2] 会出现 (i > j) 的情况
            swap(A, i, j);
        }
        swap(A, L, j);  // 和 j swap (而不是 i) 的原因是: 上面循环 break 出来之后 j 一定在 i 左边, 如下图
        return j;       //   l     j  i         显然 l 与 j 交换后即可保证 A[L] 左边都比他小, 右边都比他大
    }                   //   5 1 3 2  8 7 9

    public static void sort(int[] A) {
//        StdRandom.shuffle(A);  // shuffling needed for performance guarantee
        sort(A, 0, A.length-1);
    }

    public static void sort(int[] A, int lo, int hi) {
        if (lo >= hi)
            return;
        int j = partition(A, lo, hi);
        sort(A, lo, j-1);
        sort(A, j+1, hi);
    }

    private static void swap(int[] A, int i, int j) {
        int tmp = A[i];
        A[i] = A[j];
        A[j] = tmp;
    }

    public static void main(String[] args) {
        int[] A = new int[] {1,4,2,6,7,9,4,2,2,5,7,190,43,12,-34};
        sort(A);
        System.out.println(Arrays.toString(A));
    }
}
