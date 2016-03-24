package EightPuzzle;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdOut;

/**
 * Created by Jeremy on 3/24/16.
 */
public class Board {

    /** construct a board from an N-by-N array of blocks
     *   (where blocks[i][j] = block in row i, column j)
     **/
    public Board(int[][] blocks) {

    }
    public int dimension() {                 // board dimension N
    }
    public int hamming() {                   // number of blocks out of place
    }
    public int manhattan() {                 // sum of Manhattan distances between blocks and goal
    }
    public boolean isGoal() {                // is this board the goal board?
    }
    public Board twin() {                    // a board that is obtained by exchanging any pair of blocks
    }
    public boolean equals(Object y) {        // does this board equal y?
    }
    public Iterable<Board> neighbors() {     // all neighboring boards
    }
    public String toString() {               // string representation of this board (in the output format specified below)
    }

    public static void main(String[] args) { // unit tests (not graded)
        // create initial board from file
        In in = new In(args[0]);
        int N = in.readInt();
        int[][] blocks = new int[N][N];
        for (int i = 0; i < N; i++)
            for (int j = 0; j < N; j++)
                blocks[i][j] = in.readInt();
        Board initial = new Board(blocks);

        // solve the puzzle
        Solver solver = new Solver(initial);

        // print solution to standard output
        if (!solver.isSolvable())
            StdOut.println("No solution possible");
        else {
            StdOut.println("Minimum number of moves = " + solver.moves());
            for (Board board : solver.solution())
                StdOut.println(board);
        }
    }
}
