/**
 * Created by Jeremy on 1/31/16.
 */
public class FixedCapacityStackOfStrings {
    private int N = 0;
    private String[] s;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        s[N++] = item;
    }

    public String pop() {
        String item = s[--N];
        s[N] = null;
        // Avoids loitering
        // Reclaims memory if no outstanding references
        return item;
    }

}
