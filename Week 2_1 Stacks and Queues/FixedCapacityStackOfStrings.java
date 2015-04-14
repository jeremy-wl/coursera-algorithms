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
        s[top++] = item;
    }

    public String pop() {

        /* return s[--top];
           This recurs loitering.
         */

        // This avoids loitering.
        String item = s[--top];
        s[top] = null;
        return item;
    }
}
