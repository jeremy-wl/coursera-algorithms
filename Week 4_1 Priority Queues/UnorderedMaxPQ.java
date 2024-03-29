/**
 * Created by Jeremy on 3/7/16.
 */
public class UnorderedMaxPQ<Key extends Comparable<Key>> {
    private Key[] pq;
    private int N;

    public UnorderedMaxPQ(int capacity) {
        pq = (Key[]) new Comparable[capacity];
    }

    public boolean isEmpty() {
        return N == 0;
    }

    public void insert(Key item) {
        pq[N++] = item;
    }

    public int size() {
        return N;
    }

    public Key delMax() {
        int max = 0;
        for (int i = 0; i < N; i++)
            if (less(max, i))
                max = i;
        exchange(max, N-1);
        return pq[--N];
    }

    private boolean less(int i, int j) {
        return pq[i].compareTo(pq[j]) < 0;
    }

    private void exchange(int i, int j) {
        Key temp = pq[i];
        pq[i] = pq[j];
        pq[j] = temp;
    }

    public static void main(String[] args) {
        int N = 20;
        int MAX = 5;
        UnorderedMaxPQ<Transaction> pq = new UnorderedMaxPQ<>(N);

        while (StdIn.hasNextLine()) {
            String line = StdIn.readLine();
            Transaction item = new Transaction(line);
            pq.insert(item);

            if (pq.size() > MAX) {
                pq.delMax();
            }
        }
    }

}
