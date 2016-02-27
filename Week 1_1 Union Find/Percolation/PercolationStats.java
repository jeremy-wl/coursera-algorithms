package Percolation;

import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;

/**
 * Created by Jeremy on 1/29/16.
 */
public class PercolationStats {
    private int T;
    private double[] stats;
    private int N;

    // perform T independent experiments on an N-by-N grid
    public PercolationStats(int N, int T) {
        if (N <= 0 || T <= 0)
            throw new IllegalArgumentException();
        this.T = T;
        this.N = N;
        stats = new double[T];
        for (int i = 0; i < T; i++) {
            Percolation per = new Percolation(N);
            stats[i] = this.getPercolationThreshold(per);
        }
    }

    // sample mean of percolation threshold
    public double mean() {
        return StdStats.mean(stats);
    }

    // sample standard deviation of percolation threshold
    public double stddev() {
        return StdStats.stddev(stats);
    }

    // low  endpoint of 95% confidence interval
    public double confidenceLo() {
        return this.mean() - 1.96 * this.stddev() / Math.sqrt(this.T);
    }

    // high endpoint of 95% confidence interval
    public double confidenceHi() {
        return this.mean() + 1.96 * this.stddev() / Math.sqrt(this.T);
    }

    private double getPercolationThreshold(Percolation per) {

        int count = 0;

        while (!per.percolates()) {
            int i = StdRandom.uniform(N) + 1;
            int j = StdRandom.uniform(N) + 1;
            if (per.isOpen(i, j)) continue;
            per.open(i, j);
//            System.out.printf("Opened (%d, %d) \n", i, j);
            count++;
        }

        double N2 = (double)(N);
        double C = (double)(count);

        return C / ( N2 * N2 );
    }

    public static void main(String[] args) {
        PercolationStats ps = new PercolationStats(2000, 5);
        System.out.println("mean => " + ps.mean());
        System.out.println("standard deviation => " + ps.stddev());
        System.out.printf("95%% confidence interval => (%f, %f) \n", ps.confidenceLo(), ps.confidenceHi());
    }
}
