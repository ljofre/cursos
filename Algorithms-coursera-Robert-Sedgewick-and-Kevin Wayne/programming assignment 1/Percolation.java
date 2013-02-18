/*
 * Deliverables. Submit only Percolation.java (using the weighted quick-union 
 * algorithm as implemented in the WeightedQuickUnionUF class) and PercolationStats.java. 
 * We will supply stdlib.jar and WeightedQuickUnionUF. Your submission may not call any library 
 * functions other than those in java.lang, stdlib.jar, and WeightedQuickUnionUF.
 */

public class Percolation {

	private WeightedQuickUnionUF unionFind;
	private boolean[][] grid;
	private int N;

	public Percolation(int N) // create N-by-N grid, with all sites blocked
	{
		this.grid = new boolean[N][N];
		for (int i = 0; i < N; i++)
			for (int j = 0; j < N; j++)
				this.grid[i][j] = false;

		this.unionFind = new WeightedQuickUnionUF(N * N);
		this.N = N;
	}

	public void open(int i, int j) // open site (row i, column j) if it is not
									// already
	{
		this.grid[i][j] = true;

		int p = i * j + i + j, q;

		if (this.grid[i + 1][j]) {
			q = (i + 1) * j + (i + 1) + j;
			unionFind.union(p, q);
		}

		if (this.grid[i - 1][j]) {
			q = (i - 1) * j + (i - 1) + j;
			unionFind.union(p, q);
		}

		if (this.grid[i][j + 1]) {
			q = i * (j + 1) + i + (j + 1);
			unionFind.union(p, q);
		}
		if (this.grid[i][j - 1]) {
			q = i * (j - 1) + i + (j - 1);
			unionFind.union(p, q);
		}
	}

	public boolean isOpen(int i, int j) // is site (row i, column j) open?
	{
		return (this.grid[i][j]);
	}

	public boolean isFull(int i, int j) // is site (row i, column j) full?
	{
		for (int iter = 0; iter < this.N; iter++)
			if (unionFind.connected(i * j + i + j, iter))
				return (true);
		return (false);
	}

	public boolean percolates() {
		for (int iter = 0; iter < this.N; iter++) {
			if (this.isFull(N - 1, iter))
				return (true);
		}
		return (false);
	}
}
