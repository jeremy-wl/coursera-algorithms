import edu.princeton.cs.algs4.StdRandom;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * Created by Jeremy on 2/19/16.
 */
public class RandomizedQueue<Item> implements Iterable<Item> {

    private Item[] items;
    private int N;    // Position of the pointer
    private int num;  // Number of items in the array

    public RandomizedQueue() {
        N = 0;
        num = 0;
        items = (Item[]) new Object[2];
    }

    public boolean isEmpty() {
        return num == 0;
    }

    public int size() {
        return num;
    }

    private void resize(int capacity) {
        Item[] copy = (Item[]) new Object[capacity];
        int j = 0;
        for (int i = 0; i < N; i++) {
            if (items[i] == null)
                continue;
            copy[j] = items[i];
            j++;
        }
        N = num;
        items = copy;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException("Item cannot be null");
        if (N == items.length && num > 0)
            resize(num << 1);
        if (num == 0)
            resize(2);
        items[N++] = item;
        num++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int randomInt = StdRandom.uniform(N);
        while (items[randomInt] == null)
            randomInt = StdRandom.uniform(N);
        Item item = items[randomInt];
        items[randomInt] = null;
        num--;

        if (num == N/4 && num > 0)
            resize(N >> 1);

        return item;
    }
    public Item sample() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int randomInt = StdRandom.uniform(N);
        while (items[randomInt] == null)
            randomInt = StdRandom.uniform(N);
        return items[randomInt];
    }

    public Iterator<Item> iterator() {
        return new RandomizedQueueIterator();
    }

    private class RandomizedQueueIterator<Item> implements Iterator<Item> {

        private int pt = 0;
        private int[] indexes = new int[N];

        public RandomizedQueueIterator() {
            for (int pt = 0; pt < N; pt++)
                indexes[pt] = pt;
            StdRandom.shuffle(indexes);
        }

        public Item next() {
            if (!hasNext()) throw new NoSuchElementException();
            int index = indexes[pt++];
            return (Item) items[index];
        }

        public boolean hasNext() {
            for (int i = pt; i < N; i++) {
                if (items[indexes[i]] == null)
                    continue;
                pt = i;
                return true;
            }
            return false;
        }

        public void remove() {
            throw new java.lang.UnsupportedOperationException();
        }

    }

    public static void main(String[] args) {
        RandomizedQueue rq = new RandomizedQueue();
        rq.enqueue(10);
        rq.size();
        rq.enqueue(10);
        rq.enqueue(10);
        rq.enqueue(10);
        rq.enqueue(10);
        rq.enqueue(10);
        rq.enqueue(10);
        rq.enqueue(10);
        rq.enqueue(10);

        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
        rq.dequeue();
    }
}
