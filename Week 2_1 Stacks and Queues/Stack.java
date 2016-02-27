import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jeremy on 2/1/16.
 */
public class Stack<Item> implements Iterable<Item> {

    private Node<Item> first;
    private int N;

    private static class Node<Item> {
        private Item item;
        private Node<Item> next;
    }

    public Stack() {
        first = null;
        N = 0;
    }

    public void push(Item item) {
        Node<Item> oldFirst = first;
        first = new Node<Item>();
        first.item = item;
        first.next = oldFirst;
        N++;
    }

    public Item pop() {
        if (isEmpty())  throw new NoSuchElementException("Stack underflow.");
        Item item = first.item;
        first = first.next;
        N--;
        return item;
    }

    public boolean isEmpty() {
        return first == null;
    }

    public int size() {
        return N;
    }

    public Item peek() {
        if (isEmpty())  throw new NoSuchElementException("Stack underflow.");
        return first.item;
    }

    public Iterator<Item> iterator() {
        return new LinkedIterator<Item>(first);
    }

    private class LinkedIterator<Item> implements Iterator<Item> {

        private Node<Item> current;

        public LinkedIterator(Node<Item> first) {
            current = first;
        }

        public boolean hasNext() {
            return current != null;
        }

        // returns current item and moves the pointer to next
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

}
