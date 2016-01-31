/**
 * Created by Jeremy on 1/31/16.
 */
public class LinkedQueueOfStrings {

    private Node first, last;

    private class Node {
        String item;
        Node next;
    }

    public void enqueue(String item) {

        Node oldLast = last;
        last = new Node();
        last.item = item;
        last.next = null;

        if (isEmpty())
            first = last;
        else
            oldLast.next = last;
    }

    public String dequeue() {
        String item = first.item;
        first = first.next;
        if (isEmpty())
            last = null;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }
}
