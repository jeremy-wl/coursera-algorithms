public class MyQuickFindUF{

	private int count;
	private int[] id;

	public MyQuickFindUF(int N){
		count = N;
		id = new int[N];
		for (int i=0; i<N; i++){
			id[i] = i;
		}
	}

	public boolean connected(int p, int q){
		return id[p] == id[q];
	}

	public void union(int p, int q){
		if (connected(p, q)) return;
		int pid = id[p];
		for (int i = 0; i<id.length; i++){
			if (id[i] == pid)	id[i] = id[q];
		}
		count--;
	}

	public int count(){
		return count;
	}

	public static void main(String args[]){
		int N = StdIn.readInt();
		MyQuickFindUF uf = new MyQuickFindUF(N);
		while(!StdIn.isEmpty()){
			int p = StdIn.readInt();
			int q = StdIn.readInt();
			if (uf.connected(p,q))	continue;
			uf.union(p,q);
			StdOut.println(p + " " + q);
		}
        StdOut.println(uf.count() + " components");		
	}
}
