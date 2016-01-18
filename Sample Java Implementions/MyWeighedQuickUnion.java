public class MyWeighedQuickUnion{
	int count;	// These three should be PRIVATE but in order for them to be able to be called outside, PRIVATE is eliminated here.
	int[] id;
	int[] sz;
	public MyWeighedQuickUnion(int N){
		id = new int[N];
		sz = new int[N];
		count = N;
		for (int i = 0; i < N; i++){
			id[i] = i;
			sz[i] = 1;
		}
	}

	public int count(){
		return count;
	}

	public boolean connected(int p, int q){
		return root(p) == root(q);
	}

	public void union(int p, int q){
		int rootP = root(p);
		int rootQ = root(q);
		if (rootP == rootQ)	return;
		if (sz[rootP] < sz[rootQ]){
			id[rootP] = rootQ;	// make smaller root point to larger ROOT
			sz[rootQ] += sz[rootP];
		}
		else{
			id[rootQ] = rootP;
			sz[rootP] += sz[rootQ];
		}
		count--;
	}

	public int root(int p){
		while (id[p] != p){
			p = id[p];
		}
		return p;
	}

	public static void main(String args[]){
		int N = StdIn.readInt();
		MyWeighedQuickUnion uf = new MyWeighedQuickUnion(N);
		while(!StdIn.isEmpty()){
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p,q))	continue;
			uf.union(p,q);
			System.out.println(p + " " + q);
		}
		System.out.println(uf.count() + " components");
	}
}
