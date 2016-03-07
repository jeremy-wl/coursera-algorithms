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
            if (Utils.less(pq[max], pq[i]))
                max = i;
        Utils.exchange(pq, max, N-1);
        return pq[--N];
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
