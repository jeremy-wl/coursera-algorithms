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

    public Key delMax() {
        int max = 0;
        for (int i = 0; i < N; i++)
            if (Utils.less(pq[max], pq[i]))
                max = i;
        Utils.exchange(pq, max, N-1);
        return pq[--N];
    }

}
