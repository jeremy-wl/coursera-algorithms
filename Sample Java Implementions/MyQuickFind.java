public class MyQuickFind{
	int count;
	int[] id;
	public MyQuickFind(int N){
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
		return id[p] == id[q];
	}

	public void union(int p, int q){
		if (id[p] == id[q])	return;

		int pid = id[p];
		for (int i = 0; i < id.length; i++){
			if (id[i] == pid)	id[i] = id[q];
		}

		count--;
	}

	public static void main(String args[]){
		int N = StdIn.readInt();
		MyQuickFind uf = new MyQuickFind(N);
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