public class ex2{
	public static void main(String args[]){
		MyWeighedQuickUnion x = new MyWeighedQuickUnion(10);
		x.union(1,6);
		x.union(3,5);
		x.union(7,8);
		x.union(3,9);
		x.union(5,4);
		x.union(6,7);
		x.union(2,0);
		x.union(4,0);
		x.union(7,2);
		for (int i = 0; i < x.id.length; i++){
			System.out.print(x.id[i] + " ");
		}
		System.out.println();
	}
}