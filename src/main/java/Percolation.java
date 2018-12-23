import edu.princeton.cs.algs4.WeightedQuickUnionUF;

public class Percolation {
    private int n;
    private int[][] grid;
    private WeightedQuickUnionUF union;
    private int numberOfOpenSites;

    public Percolation(int n) { // create n-by-n grid, with all sites blocked
        if (n <= 0) {
            throw new IllegalArgumentException();
        }
        this.n = n;
        union = new WeightedQuickUnionUF(n * n);
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
        grid[row - 1][col - 1] = 0;
        uniteOpenSites(row, col);
        numberOfOpenSites++;
    }

    private void uniteOpenSites(int x, int y) {
        x = x - 1;
        y = y - 1;
        if (!(x - 1 < 0)) {
            if (isOpenWithoutOffset(x - 1, y)) {
                union.union(xyTo1d(x, y), xyTo1d(x - 1, y));
            }
        }
        if (!(y - 1 < 0)) {
            if (isOpenWithoutOffset(x, y - 1)) {
                union.union(xyTo1d(x, y), xyTo1d(x, y - 1));
            }
        }
        if (!(x + 1 >= n)) {
            if (isOpenWithoutOffset(x + 1, y)) {
                union.union(xyTo1d(x, y), xyTo1d(x + 1, y));
            }
        }
        if (!(y + 1 >= n)) {
            if (isOpenWithoutOffset(x, y + 1)) {
                union.union(xyTo1d(x, y), xyTo1d(x, y + 1));
            }
        }
    }

    private boolean isOpenWithoutOffset(int row, int col) { // is site (row, col) open?
        return grid[row][col] == 0;
    }

    public boolean isOpen(int row, int col) { // is site (row, col) open?
        verifyInputIsInAllowedRange(row);
        verifyInputIsInAllowedRange(col);
        return grid[row - 1][col - 1] == 0;
    }

    public boolean isFull(int row, int col) { // is site (row, col) full?
        verifyInputIsInAllowedRange(row);
        verifyInputIsInAllowedRange(col);
        boolean isFull = false;
        for (int i = 0; i < n; i++) {
            if (union.connected(xyTo1d(row - 1, col - 1), xyTo1d(0, i))) {
                isFull = true;
                break;
            }
        }
        return isFull;
    }

    public int numberOfOpenSites() { // number of open sites
        return numberOfOpenSites;
    }

    public boolean percolates() { // does the system percolate?
        boolean percolates = false;
        for (int i = 1; i <= n; i++) {
            if (isFull(n, i)) {
                percolates = true;
                break;
            }
        }
        return percolates;
    }

    private void verifyInputIsInAllowedRange(int dimension) {
        if (dimension > n || dimension < 1) {
            throw new IllegalArgumentException();
        }
    }

    private int xyTo1d(int x, int y) {
        return x * 10 + y;
    }
}
