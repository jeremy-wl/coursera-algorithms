/**
 * Created by Jeremy on 3/19/16.
 */
public class tt {

    public static int getTN(int num) {
        if (num == 1)
            return 1;
        int a =  num + getTN(--num);
        System.out.printf("Returned %d", a);
        return a;
    }

    public static void main(String[] args) {
        System.out.println(tt.getTN(3));
    }

}
