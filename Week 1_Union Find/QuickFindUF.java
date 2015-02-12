/**
 * Created by jeremy on 2/11/15.
 */
public class QuickFindUF {

    private int count;
    private int[] id;

    public QuickFindUF(int N) {
        count = N;
        id = new int[N];
        for (int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public int count() {
        return this.count;
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {
        if (this.connected(p, q)) return;
        for(int i = 0; i < id.length; i++) {
            if (id[i] == id[q]) {
                id[i] = id[p];
            }
        }
        count--;
    }

    public static void main(String[] args) {
        QuickFindUF uf = new QuickFindUF(StdIn.readInt());
        while(!StdIn.isEmpty()) {
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p, q)) continue;
            uf.union(p, q);
            StdOut.println(p + " " + q);
        }
        StdOut.println(uf.count() + " components");
    }
}
