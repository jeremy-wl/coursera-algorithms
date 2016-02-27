import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jeremy on 2/2/16.
 */
public class ResizingArrayStack<Item> implements Iterable<Item> {

    private int N;
    private Item[] items;

    public ResizingArrayStack() {
        N = 0;
        items = (Item[]) new Object[2];
    }

    public void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        for (int i = 0; i < N; i++) {
            copy[i] = items[i];
        }
        items = copy;
    }

    public int size() {
        return N;
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void push(Item item) {
        if (items.length == N)  resize(N * 2);
        items[N++] = item;
    }

    public Item pop() {
        if (isEmpty()) throw new NoSuchElementException("Stack underflow.");
        Item item = items[--N];
        items[N] = null;    // avoid loitering
        if (N > 0 && N < items.length / 4)
            resize(N >> 1);
        return item;
    }

    public Iterator<Item> iterator() {
        return new ReverseArrayIterator();
    }

    public Item peek() {
        if (isEmpty())  throw new NoSuchElementException();
        return items[N-1];
    }

    private class ReverseArrayIterator implements Iterator<Item> {
        private int i;

        public ReverseArrayIterator() {
            i = N;
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            return items[--i];
        }

        public boolean hasNext() {
            return i > 0;
        }

        public void remove() {
            throw new UnsupportedOperationException();
        }
    }
}
