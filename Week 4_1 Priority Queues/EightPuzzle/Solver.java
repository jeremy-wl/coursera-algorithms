package EightPuzzle;

import edu.princeton.cs.algs4.MinPQ;
import edu.princeton.cs.algs4.Stack;

import java.util.Comparator;

/**
 * Created by Jeremy on 3/24/16.
 */
public class Solver {
    private SearchNode goal;

    private final Comparator<SearchNode> BY_PRIORITY = PriorityOrder();

    private class SearchNode {
        private int moves;
        private Board board;
        private SearchNode previous;

        public SearchNode(Board b) {
            this.board = b;
            this.moves = 0;
            previous = null;
        }
    }

    private Comparator<SearchNode> PriorityOrder() {
        return new ByPriority();
    }

    private class ByPriority implements Comparator<SearchNode> {
        public int compare(SearchNode sn1, SearchNode sn2) {
            int p1 = sn1.board.manhattan() + sn1.moves;
            int p2 = sn2.board.manhattan() + sn2.moves;
            if (p1 > p2)
                return 1;
            if (p1 < p2)
                return -1;
            return 0;
        }
    }

    public Solver(Board initial) {            // find a solution to the initial board (using the A* algorithm)
        if (initial == null)
            throw new java.lang.NullPointerException();

        SearchNode sn = new SearchNode(initial);
        SearchNode snTwin = new SearchNode(initial.twin());
        MinPQ<SearchNode> pq = new MinPQ<>(BY_PRIORITY);
        MinPQ<SearchNode> pqTwin = new MinPQ<>(BY_PRIORITY);

        while (!sn.board.isGoal() && !snTwin.board.isGoal()) {

            for (Board b: sn.board.neighbors()) {
                if (sn.previous == null || !sn.previous.board.equals(b)) {  // avoid enqueueing the previous node
                    SearchNode s = new SearchNode(b);
                    s.moves = sn.moves + 1;
                    pq.insert(s);
                }
            }

            for (Board b: snTwin.board.neighbors()) {
                if (snTwin.previous == null || !snTwin.previous.board.equals(b)) {  // avoid enqueueing the previous node
                    SearchNode s = new SearchNode(b);
                    s.moves = snTwin.moves + 1;
                    pqTwin.insert(s);
                }
            }

            SearchNode prev = sn;
            sn = pq.delMin();
            sn.previous = prev;

            SearchNode prev2 = snTwin;
            snTwin = pqTwin.delMin();
            snTwin.previous = prev2;
        }
        if (sn.board.isGoal())
            goal = sn;
        else if (snTwin.board.isGoal())
            goal = null;
    }

    public boolean isSolvable() {             // is the initial board solvable?
        return goal != null;
    }

    public int moves() {                      // min number of moves to solve initial board; -1 if unsolvable
        if (!isSolvable())
            return -1;
        return goal.moves;
    }

    public Iterable<Board> solution() {       // sequence of boards in a shortest solution; null if unsolvable
        if (!isSolvable())
            return null;
        Stack<Board> s = new Stack<>();
        for (Board b = goal.board; goal.previous != null; goal = goal.previous)
            s.push(b);
        return s;
    }

    public static void main(String[] args) {  // solve a slider puzzle (given below)
//        int[][] a = {{2,1,3}, {4,5,6}, {8,7,0}};
//        int[][] a = {{3,6,5},{0,1,4},{7,8,2}};
        int[][] a = {{7,13,5,8}, {4,2,3,0}, {15,11,12,14}, {9,1,6,10}};
        Board b = new Board(a);

        Solver s = new Solver(b);
        System.out.println(s.isSolvable());
        System.out.println(s.moves());
        System.out.println(b);
    }
}
