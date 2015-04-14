public class BinarySearch {

    public static int BinarySearch(int array[], int key) {
        int lo = 0, hi = array.length-1;

        while (lo <= hi) {

            int mid = (lo + hi) / 2;

            if (key > array[mid])
                lo = mid + 1;
            else if (key < array[mid])
                hi = mid - 1;
            else
                return mid;

        }
        return -1; // Key not found.
    }

    public static void main(String[] args) {
        int a[] = new int[] {1,2,5,8,9,13,15,18};
        System.out.print(BinarySearch(a, 14));
    }
}
