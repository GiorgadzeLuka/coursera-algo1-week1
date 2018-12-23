import edu.princeton.cs.algs4.StdRandom;
import edu.princeton.cs.algs4.StdStats;
import edu.princeton.cs.algs4.Stopwatch;

public class PercolationStats {
    private static int n;
    private static int trials;
    private static double[] results;

    public PercolationStats(int n, int trials) {  // perform trials independent experiments on an n-by-n grid
        if (n <= 0 || trials <= 0) {
            throw new IllegalArgumentException();
        }
        PercolationStats.n = n;
        PercolationStats.trials = trials;
        results = new double[n];
    }

    public double mean() { // sample mean of percolation threshold
        return StdStats.mean(results);
    }

    public double stddev() { // sample standard deviation of percolation threshold
        return StdStats.stddev(results);
    }

    public double confidenceLo() { // low  endpoint of 95% confidence interval
        return StdStats.min(results);
    }

    public double confidenceHi() { // high endpoint of 95% confidence interval
        return StdStats.max(results);
    }

    public static void main(String[] args) { // test client (described below)
        int n = Integer.parseInt(args[0]);
        int trials = Integer.parseInt(args[1]);

        results = new double[n];
        for (int i = 0; i < trials; i++) {
            Stopwatch stopwatch = new Stopwatch();
            Percolation percolation = new Percolation(n);
            while (!percolation.percolates()) {
                int x = StdRandom.uniform(1, n + 1);
                int y = StdRandom.uniform(1, n + 1);
                if (!percolation.isOpen(x, y)) {
                    percolation.open(x, y);
                }
            }
            results[i] = stopwatch.elapsedTime();
        }
        System.out.println("mead: " + StdStats.mean(results));
        System.out.println("stddev: " +  StdStats.stddev(results));
        System.out.println("95% confidence interval: [" + StdStats.min(results) + ", " + StdStats.max(results) + "]");
    }
}
