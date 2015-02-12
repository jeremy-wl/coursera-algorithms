/**
 * Created by jeremy on 2/12/15.
 */

public class QuickUnionUF {

    private int[] id;
    private int count;
    public QuickUnionUF(int N) {
        this.count = N;
        id = new int[N];
        for(int i = 0; i < N; i++) {
            id[i] = i;
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

    // change root of p to point to root of q
    public void union(int p, int q) {
        if (this.connected(p,q)) return;
        int rootP = find(p);
        int rootQ = find(q);
        id[rootP] = rootQ;
        count--;
    }


    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickUnionUF uf = new QuickUnionUF(N);
        while (!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }

}
