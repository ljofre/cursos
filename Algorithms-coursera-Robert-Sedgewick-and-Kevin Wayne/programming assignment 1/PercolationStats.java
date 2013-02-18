
public class PercolationStats {

	private double[] prob;
	private Percolation per;
	private int T;

	public PercolationStats(int N, int T) // perform T independent computational
											// experiments on an N-by-N grid
	{
		this.T = T;
		prob = new double[T];
		int p, q;
		int count;
		for (int k = 0; k < T; k++) {
			count = 0;
			per = new Percolation(N);
			while (!per.percolates()) {

				p = (int) Math.round(Math.random() * N) - 1;
				q = (int) Math.round(Math.random() * N) - 1;

				per.open(p, q);
				count += 1;
			}
			prob[k] = 1.0 * count / (N * N);
		}
	}

	public double mean() // sample mean of percolation threshold
	{
		double sum = 0;
		for (int i = 0; i < this.prob.length; i++) {
			sum += this.prob[i];
		}
		return (sum / this.prob.length);
	}

	public double stddev() // sample standard deviation of percolation threshold
	{
		double mu = this.mean();
		double sigma = 0;
		for (int i = 0; i < this.prob.length; i++) {

			sigma += Math.pow(mu - this.prob[i], 2);
		}
		sigma /= (this.prob.length - 1);
		sigma = Math.sqrt(sigma);
		return (sigma);
	}

	public double confidenceLo() // returns lower bound of the 95% confidence
									// interval
	{
		return (this.mean() - 1.96 * this.stddev() / this.T);
	}

	public double confidenceHi() // returns upper bound of the 95% confidence
									// interval
	{
		return (this.mean() + 1.96 * this.stddev() / this.T);
	}

	public static void main(String[] args) // test client, described below
	{
		int N = 5, T = 100;
		// int N = Integer.parseInt(args[0]), T = Integer.parseInt(args[1]);
		PercolationStats p = new PercolationStats(N, T);
		System.out.println("mean = " + p.mean());
		System.out.println("stddev = " + p.stddev());
		System.out.println("95% confidence interval = " + p.confidenceLo()
				+ ", " + p.confidenceHi());
	}
}