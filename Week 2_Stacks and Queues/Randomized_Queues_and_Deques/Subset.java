import edu.princeton.cs.algs4.StdIn;

/**
 *
 *  A client program that takes a command-line integer k;
 *  reads in a sequence of N strings from standard input using StdIn.readString();
 *  and prints out exactly k of them, uniformly at random.
 *
 *  Each item from the sequence can be printed out at most once.
 *
 *   % echo A B C D E F G H I | java Subset 3
 *   C
 *   G
 *   A
 *
 *   % echo A B C D E F G H I | java Subset 3
 *   E
 *   F
 *   G
 *
 * *
 * */



/**
 * Created by Jeremy on 2/25/16.
 */
public class Subset {
    public static void main(String[] args) {

        int k = Integer.parseInt(args[0]);
        RandomizedQueue<String> rq = new RandomizedQueue<>();

        while (!StdIn.isEmpty()) {
            rq.enqueue(StdIn.readString());
        }

        for (int i = 0; i < k; i++) {
            System.out.println(rq.dequeue());
        }

    }
}
