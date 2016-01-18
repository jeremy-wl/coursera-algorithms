public class ex1{
	public static void main(String args[]){
		MyQuickFind x = new MyQuickFind(10);
		x.union(0,5);
		x.union(0,4);
		x.union(9,2);
		x.union(4,6);
		x.union(6,1);
		x.union(7,8);
		for (int i = 0; i < x.id.length; i++){
			System.out.print(x.id[i] + " ");
		}
		System.out.println();
	}
}