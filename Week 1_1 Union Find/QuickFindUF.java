/**
 * Created by Jeremy on 1/18/16.
 */

public class QuickFindUF {

    private int count;
    private int[] id;

    public QuickFindUF(int N) {
        id = new int[N];
        count = N;
        for(int i = 0; i < N; i++) {
            id[i] = i;
        }
    }

    public boolean connected(int p, int q) {
        return id[p] == id[q];
    }

    public void union(int p, int q) {

        // The id[q] needs to be taken out first so that it won't get changed in the loop.
        int pid = id[p];

        for (int i = 0; i < id.length; i++) {
            if (id[i] == pid)
                id[i] = id[q];
        }
        count--;
    }

    public int count() {
        return count;
    }

    public static void main(String[] args) {
        int N = StdIn.readInt();
        QuickFindUF uf = new QuickFindUF(N);
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
