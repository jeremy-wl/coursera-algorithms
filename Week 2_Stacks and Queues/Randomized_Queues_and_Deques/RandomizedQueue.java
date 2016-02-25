package Randomized_Queues_and_Deques;

import com.sun.scenario.effect.impl.sw.sse.SSEBlend_SRC_OUTPeer;
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
        for (int i = 0; i < N; i++) {
            if (items[i] == null)
                continue;
            copy[i] = items[i];
        }
        items = copy;
    }

    public void enqueue(Item item) {
        if (item == null)
            throw new java.lang.NullPointerException("Item cannot be null");
        if (N == items.length)
            resize(num << 1);
        items[num++] = item;
        N++;
    }

    public Item dequeue() {
        if (isEmpty()) throw new java.util.NoSuchElementException();
        int randomInt = StdRandom.uniform(N);
        while (items[randomInt] == null)
            randomInt = StdRandom.uniform(N);
        Item item = items[randomInt];
        items[randomInt] = null;
        num--;

        if (num > 0 && N == num/4)
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
        RandomizedQueue<Integer> rq = new RandomizedQueue<>();

        rq.enqueue(1);
        rq.enqueue(2);
        rq.enqueue(3);
        rq.enqueue(4);
        rq.enqueue(5);
        rq.enqueue(6);


        System.out.println();System.out.println();

        System.out.println("Dequeued: " + rq.dequeue());
        System.out.println("Dequeued: " + rq.dequeue());


        for (Integer a: rq) {
            System.out.println(a);
        }

        System.out.println();System.out.println();

    }
}
