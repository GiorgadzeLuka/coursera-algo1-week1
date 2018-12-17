import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int[][] grid;
    private WeightedQuickUnionUF union;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        union = new WeightedQuickUnionUF(n ^ 2);
        grid = new int[n][n];
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                grid[i][j] = 1;
            }
        }
    }

    public void open(int row, int col) {  // open site (row, col) if it is not open already
        verifyInputIsInAllowedRange(row);
        verifyInputIsInAllowedRange(col);

    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        verifyInputIsInAllowedRange(row);
        verifyInputIsInAllowedRange(col);

        return false;
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        verifyInputIsInAllowedRange(row);
        verifyInputIsInAllowedRange(col);

        return false;
    }

    public int numberOfOpenSites() { // number of open sites
        return 0;
    }

    public boolean percolates() { // does the system percolate?
        return false;
    }

    public static void main(String[] args) {

    }

    private void verifyInputIsInAllowedRange(int dimension) {
        if (dimension > n || dimension < 1) {
            throw new IllegalArgumentException();
        }
    }
}
