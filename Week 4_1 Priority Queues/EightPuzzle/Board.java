package EightPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.Queue;
import edu.princeton.cs.algs4.StdOut;

import java.util.Arrays;

/**
 * Created by Jeremy on 3/24/16.
 */
public class Board {

    private int N;
    private int[] blocks;

    /** construct a board from an N-by-N array of blocks
     *   (where blocks[i][j] = block in row i, column j)
     **/
    public Board(int[][] blocks) {
        if (blocks == null)
            throw new java.lang.NullPointerException();
        N = blocks[0].length;
        this.blocks = new int[N*N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                this.blocks[i*N+j] = blocks[i][j];

    }

    private Board(int[] blocks) {
        if (blocks == null)
            throw new java.lang.NullPointerException();
        int N = blocks.length;
        this.N = (int) Math.sqrt(N);
        this.blocks = new int[N];
        System.arraycopy(blocks, 0, this.blocks, 0, N);
    }

    private Board exchange(Board b, int i, int j) {
        Board b2 = new Board(b.blocks);

        int temp = b2.blocks[i];
        b2.blocks[i] = b2.blocks[j];
        b2.blocks[j] = temp;

        return b2;
    }

    private Board slideLeft(Board b, int zero) {
        if ((zero+1) % N == 0)  // right corner
            return null;
        return exchange(b, zero, zero+1);
    }

    private Board slideRight(Board b, int zero) {
        if (zero % N == 0)   // left corner
            return null;
        return exchange(b, zero, zero-1);
    }

    private Board slideUp(Board b, int zero) {
        if (zero >= N*(N-1))    // bottom row
            return null;
        return exchange(b, zero, zero+N);
    }

    private Board slideDown(Board b, int zero) {
        if (zero < N)    // top row
            return null;
        return exchange(b, zero, zero-N);
    }

    public int dimension() {                 // board dimension N
        return N;
    }

    public int hamming() {                   // number of blocks out of place
        int count = 0;
        for (int i = 0; i < blocks.length; i++)
            if (blocks[i] != i+1 && blocks[i] != 0)
                count++;
        return count;
    }

    public int manhattan() {                 // sum of Manhattan distances between blocks and goal
        int dist = 0;
        for (int i = 0; i < N*N; i++) {
            if (blocks[i] != 0)
                dist += Math.abs(rowIndex(i+1) - rowIndex(blocks[i])) + Math.abs(colIndex(i+1) - colIndex(blocks[i]));
        }
        return dist;
    }

    private int rowIndex(int i) {
        return (i-1) / N;
    }

    private int colIndex(int i) {
        return (i-1) % N;
    }

    public boolean isGoal() {                // is this board the goal board?
        for (int i = 0; i < N*N; i++) {
            if (blocks[i] == 0)
                continue;
            if (blocks[i] - 1 != i)
                return false;
        }
        return true;
    }

    public Board twin() {                    // a board that is obtained by exchanging any pair of blocks
        if (blocks[0] * blocks[1] != 0)
            return exchange(this, 0, 1);
        return exchange(this, N-2, N-1);
    }

    public boolean equals(Object y) {        // does this board equal y?

        if (y == this) return true;
        if (y == null) return false;
        if (y.getClass() != this.getClass()) return false;

        Board that = (Board) y;
        return Arrays.equals(this.blocks, that.blocks);
    }

    public Iterable<Board> neighbors() {     // all neighboring boards
        int zero = 0;
        Queue<Board> q = new Queue<>();

        for (int i = 0; i < this.blocks.length; i++) {
            if (this.blocks[i] == 0) {
                zero = i;
                break;
            }
        }

        Board b1 = this.slideLeft(this, zero);
        Board b2 = this.slideRight(this, zero);
        Board b3 = this.slideUp(this, zero);
        Board b4 = this.slideDown(this, zero);

        if (b1 != null)
            q.enqueue(b1);
        if (b2 != null)
            q.enqueue(b2);
        if (b3 != null)
            q.enqueue(b3);
        if (b4 != null)
            q.enqueue(b4);

        return q;
    }

    public String toString() {               // string representation of this board (in the output format specified below)
        return "";
    }

    public static void main(String[] args) { // unit tests (not graded)
        // create initial board from file
//        In in = new In(args[0]);
//        int N = in.readInt();
//        int[][] blocks = new int[N][N];
//        for (int i = 0; i < N; i++)
//            for (int j = 0; j < N; j++)
//                blocks[i][j] = in.readInt();
//        Board initial = new Board(blocks);
//
//        // solve the puzzle
//        Solver solver = new Solver(initial);
//
//        // print solution to standard output
//        if (!solver.isSolvable())
//            StdOut.println("No solution possible");
//        else {
//            StdOut.println("Minimum number of moves = " + solver.moves());
//            for (Board board : solver.solution())
//                StdOut.println(board);
//        }
        int[][] a = {{1,2,3}, {4,5,6}, {7,8,9}};
        Board b = new Board(a);
        System.out.println(b.isGoal());
    }
}
