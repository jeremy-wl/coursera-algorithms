package Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Jeremy on 1/28/16.
 */
public class Percolation {
    private boolean[][] grid;
    private boolean[] last;
    private int N;
    private int count = 0;
    private int virtualTop;
    private WeightedQuickUnionUF uf;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        if (N <= 0)
            throw new IllegalArgumentException("N must be positive.");
        this.N = N;
        virtualTop = N * N;
        grid = new boolean[N][N];
        last = new boolean[N];
        uf = new WeightedQuickUnionUF(N*N+2);
    }

    // open site (row i, column j) if it is not open already
    public void open(int row, int col) {

        if (row <= 0 || col <=0 || row > N || col > N)
            throw new IndexOutOfBoundsException("Index out of bounds, row: " + row + ", col: " + col);

        int idx = coordinateToArrayIndex(row, col);
        grid[row-1][col-1] = true; // opens the site

        if (N == 1) {
            uf.union(virtualTop, idx);
//            uf.union(virtualBtm, idx);
            return;
        }

        if (col-1 >= 1 && isOpen(row, col-1))       // left site exists and is open
            uf.union(idx-1, idx);       // Union the site to the left
        if (col+1 <= N && isOpen(row, col+1))       // right site exists and is open
            uf.union(idx+1, idx);       // Union the site to the right

        if (row == 1) {
            uf.union(virtualTop, idx);  // Union the virtual top site
            if (isOpen(row+1, col))
                uf.union(idx+N, idx);   // Union the site below
            return;
        }
        if (row == N) {
            last[col-1] = true;
            if (isOpen(row-1, col))
                uf.union(idx-N, idx);   // Union the site above
            return;
        }

        if (isOpen(row-1, col))
            uf.union(idx-N, idx); // Union the site above
        if (isOpen(row+1, col))
            uf.union(idx+N, idx);   // Union the site below

    }

    public boolean isOpen(int row, int col) {

        if (row <= 0 || col <=0 || row > N || col > N)
            throw new IndexOutOfBoundsException("Index out of bounds, row: " + row + ", col: " + col);
        return grid[row-1][col-1];

    }

    /**
     * " A full site is an open site that can be connected to an open site in the top row
     *          via a chain of neighboring (left, right, up, down) open sites. "
     * **/

    public boolean isFull(int row, int col) {

        if (row <= 0 || col <= 0 || row > N || col > N)
            throw new IndexOutOfBoundsException("Index out of bounds, row: " + row + ", col: " + col);

        int idx = coordinateToArrayIndex(row, col);

        return uf.connected(virtualTop, idx);

    }

    public boolean percolates() {

        /** open a virtual top site and a virtual bottom site,
         and check if they are connected.  **/

        for (int i = 0; i < N; i++) {
            if (!last[i]) continue;
            if (!uf.connected(virtualTop, coordinateToArrayIndex(N-1, i+1))) continue;
            return true;
        }
        return false;
    }

    private int coordinateToArrayIndex(int x, int y) {
        return (x-1) * N + (y-1);
    }

    private double getPercolationThreshold() {

        while (!this.percolates()) {
            int i = StdRandom.uniform(N) + 1;
            int j = StdRandom.uniform(N) + 1;
            if (this.isOpen(i, j)) continue;
            this.open(i, j);
//            System.out.printf("Opened (%d, %d) \n", i, j);
            count++;
        }

        double N2 = (double)(N);
        double C = (double)(this.count);

        return C / ( N2 * N2 );
    }

    // test client (optional)
    public static void main(String[] args) {

        int N = 100;
        Percolation per = new Percolation(N);
        double p = per.getPercolationThreshold();
        System.out.printf("Opened %d sites, out of %d. \n\n", per.count, N*N);
        System.out.println("p = " + p);

    }
}
