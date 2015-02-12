/**
 * Created by jeremy on 2/12/15.
 */

public class WeightedQuickUnionUF {

    private int[] id;
    private int[] sz;
    private int count;
    public WeightedQuickUnionUF(int N) {
        this.count = N;
        id = new int[N];
        sz = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
            sz[i] = 1;
        }
    }

    public int count(){
        return this.count;
    }


    // chase parent pointers until reach root
    public int find(int p) {
        while(p != id[p])
            p = id[p];
        return p;
    }

    // check if p and q have the same root
    public boolean connected(int p, int q) {
        return find(p) == find(q);
    }

    // link root of smaller tree to root of larger tree
    public void union(int p, int q) {
        if (this.connected(p ,q)) return;
        int rootP = find(p);
        int rootQ = find(q);
        if (sz[rootP] > sz[rootQ]) {
            id[rootQ] = rootP;
            sz[rootP] += sz[rootQ];
        } else {
            id[rootP] = rootQ;
            sz[rootQ] += sz[rootP];
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
            StdOut.println(p + " " + q);
            StdOut.println(uf.count() + " components");
        }
    }

}
