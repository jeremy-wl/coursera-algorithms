public class PathCompression{
	private int count;
	private int[] id;
	public PathCompression(int N){
		id = new int[N];
		count = N;
		for (int i = 0; i < N; i++){
			id[i] = i;
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
		id[rootP] = rootQ;
		count--;
	}

	public int root(int p){
		while (id[p] != p){
			id[p] = id[id[p]];
			p = id[p];
		}
		return p;

	}

	public static void main(String args[]){
		int N = StdIn.readInt();
		PathCompression uf = new PathCompression(N);
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
