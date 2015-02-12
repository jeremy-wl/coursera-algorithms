public class PathCompressionPC {
    private int count;
    private int[] id;
    public PathCompressionPC(int N){
        id = new int[N];
        count = N;
        for (int i = 0; i < N; i++){
            id[i] = i;
        }
    }


    public int count(){
        return this.count;
    }


    // make every other node in path point to its grandparent
    public int root(int p) {
        while(p != id[p])
            id[p] = id[id[p]];
            p = id[p];
        return p;
    }

    // check if p and q have the same root
    public boolean connected(int p, int q) {
        return root(p) == root(q);
    }

    // link root of smaller tree to root of larger tree
    public void union(int p, int q) {
        if (this.connected(p ,q)) return;
        int rootP = root(p);
        int rootQ = root(q);
        id[rootP] = rootQ;
        count--;
    }

    public static void main(String args[]){
        int N = StdIn.readInt();
        PathCompressionPC uf = new PathCompressionPC(N);
        while(!StdIn.isEmpty()){
            int p = StdIn.readInt();
            int q = StdIn.readInt();
            if (uf.connected(p,q))	continue;
            uf.union(p,q);
            System.out.println(p + " " + q);
            System.out.println(uf.count() + " components");
        }
    }
}
