/**
 * Created by Jeremy on 1/31/16.
 */
public class ResizingArrayStackOfStrings {
    private int N = 0;
    private String[] s;

    public ResizingArrayStackOfStrings() {
        s = new String[1];
    }

    private void resize(int capacity) {
        String[] copy = new String[capacity];
        for (int i = 0; i < N; i++)
            copy[i] = s[i];
        s = copy;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(String item) {
        if (N == s.length)  // Double the array size when it's full
            resize(N * 2);
        s[N++] = item;
    }

    public String pop() {
        String item = s[--N];
        s[N] = null;  // Avoids loitering; Reclaims memory if no outstanding references
        if (N > 0 && N == s.length/4)
            resize(N >> 1); // Resize the array by half when it's only 1/4 full
        return item;
    }

}
