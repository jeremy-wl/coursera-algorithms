/**
 * Created by Jeremy on 1/22/16.
 */
public class WeightedQuickUnionUF {

    private int count;
    private int[] id;
    private int[] sz;

    public WeightedQuickUnionUF(int N) {
        count = N;
        id = new int[N];
        sz = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count() {
        return count;
    }

    public int findRoot(int i) {
        while (i != id[i])
            i = id[i];
        return i;
    }

    public boolean connected(int p, int q) {
        return findRoot(p) == findRoot(q);
    }

    public void union(int p, int q) {

        int rootP = findRoot(p);
        int rootQ = findRoot(q);

        if (sz[rootP] < sz[rootQ]) {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
        }
        else {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        }
        count--;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        WeightedQuickUnionUF uf = new WeightedQuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            System.out.println(p + " " + q);
        }
        System.out.println(uf.count() + " components");
    }
}
