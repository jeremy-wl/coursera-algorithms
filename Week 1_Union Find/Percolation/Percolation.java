package Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.WeightedQuickUnionUF;

/**
 * Created by Jeremy on 1/28/16.
 */
public class Percolation {
    private int[][] grid;
    private int N;
    private int count = 0;
    private int virtualTop;
    private int virtualBtm;
    WeightedQuickUnionUF uf;

    // create N-by-N grid, with all sites blocked
    public Percolation(int N) {
        this.N = N;
        virtualTop = N * N;
        virtualBtm = N * N + 1;
        grid = new int[N][N];
        uf = new WeightedQuickUnionUF(N*N+2);
    }

    // open site (row i, column j) if it is not open already
    public void open(int row, int col) {

        int idx = coordinateToArrayIndex(row, col);
        grid[row-1][col-1] = idx + 1; // opens the site

        if (isOpen(row, col-1))             // left site is open
            uf.union(idx-1, idx);       // Union the site to the left
        if (isOpen(row, col+1)) {           // right site is open
            uf.union(idx+1, idx);       // Union the site to the right
        }

        if (row == 1) {
            uf.union(virtualTop, idx);  // Union the virtual top site
            if (isOpen(row+1, col))
                uf.union(idx+N, idx);   // Union the site below
            count++;
            return;
        }
        if (row == N) {
            uf.union(virtualBtm, idx);  // Union the virtual bottom site
            if (isOpen(row-1, col))
                uf.union(idx-N, idx);   // Union the site above
            count++;
            return;
        }

        if (isOpen(row-1, col))
            uf.union(idx-N, idx); // Union the site above
        if (isOpen(row+1, col))
            uf.union(idx+N, idx);   // Union the site below

        count++;
    }

    public boolean isOpen(int row, int col) {

        try {
            if (row <= 0 || col <=0 || row > N || col > N)
                throw new IndexOutOfBoundsException("Index out of bounds, row: " + row + ", col: " + col);

            if (grid[row-1][col-1] == 0)
                return false;
            else
                return true;
        }

        catch (Exception e) {
//            e.printStackTrace();
        }

        return false;

    }

    public boolean isFull(int i, int j) {
        int idx= coordinateToArrayIndex(i, j);
        if ( uf.connected(virtualTop, idx) || (uf.connected(virtualBtm, idx)) )
            return true;
        else
            return false;

    }

    public boolean percolates() {

        /** open a virtual top site and a virtual bottom site,
         and check if they are connected.  **/

        if (uf.connected(virtualTop, virtualBtm))
            return true;
        else
            return false;
    }

    public int coordinateToArrayIndex(int x, int y) {
        return (x-1) * N + (y-1);
    }

    // test client (optional)
    public static void main(String[] args) {

            int N = 100;
            Percolation per = new Percolation(N);

            while (!per.percolates()) {
                int i = StdRandom.uniform(N) + 1;
                int j = StdRandom.uniform(N) + 1;
                if (per.isOpen(i, j)) continue;
                per.open(i, j);

                System.out.printf("Opened (%d, %d) \n", i, j);
            }
            System.out.printf("Opened %d sites, out of %d. \n\n", per.count, N*N);

            double N2 = (double)(N);
            double C = (double)(per.count);
        System.out.println("p = " + (C / (N2*N2)) );

    }

}
