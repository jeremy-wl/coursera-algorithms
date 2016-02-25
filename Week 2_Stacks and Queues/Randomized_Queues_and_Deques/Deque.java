import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jeremy on 2/18/16.
 */
public class Deque<Item> implements Iterable<Item> {

    private Node<Item> first;
    private Node<Item> last;

    private int N;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
        private Node<Item> prev;
    }

    public Deque() {
        first = last = null;
        N = 0;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public void addFirst(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException("Item cannot be null");
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        if (oldFirst != null)
            oldFirst.prev = first;
        else
            last = first;
        N++;
    }

    public void addLast(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException("Item cannot be null");
        Node<Item> oldLast = last;
        last = new Node<>();
        last.item = item;
        last.next = null;
        if (isEmpty())
            first = last;
        else
            last.prev = oldLast;
            oldLast.next = last;
        N++;
    }

    public Item removeFirst() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Stack underflow.");
        Item item = first.item;
        first = first.next;
        first.prev = null;
        return item;
    }

    public Item removeLast() {
        if (isEmpty())
            throw new java.util.NoSuchElementException("Stack underflow.");
        Item item = last.item;
        last = last.prev;
        last.next = null;
        return item;
    }

    public Iterator<Item> iterator() {
        return new DequeIterator(first);
    }

    private class DequeIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public DequeIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            Item item = current.item;
            current = current.next;
            return item;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        Deque<Integer> d = new Deque<>();
        d.addFirst(2);
        d.addFirst(1);
        d.addLast(8);
        d.addLast(9);
        System.out.println(d.removeFirst());
        System.out.println(d.removeLast());


        for (Integer a: d) {
            System.out.println(a);
        }
    }
}
