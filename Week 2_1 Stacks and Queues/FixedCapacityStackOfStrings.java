public class FixedCapacityStackOfStrings {

    private String[] s;
    private int top = 0;

    public FixedCapacityStackOfStrings(int capacity) {
        s = new String[capacity];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public void push(String item) {
        if (top == s.length) {
            resize(2 * s.length);
        }
        s[top++] = item;
    }

    public String pop() {

        /* return s[--top];
           This recurs loitering.
         */

        // This avoids loitering.
        String item = s[--top];
        s[top] = null;
        if (top > 0 && top == s.length / 4) resize(s.length / 2);
        return item;
    }

    public void resize(int capacity) {
        String[] copy = new String[capacity];
        for(int i = 0; i < top; i++) {
            copy[i] = s[i];
        }
        s = copy;
    }

}
