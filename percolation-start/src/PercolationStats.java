import java.awt.Point;
import java.util.*;

/**
 * Compute statistics on Percolation after performing T independent experiments on an N-by-N grid.
 * Compute 95% confidence interval for the percolation threshold, and  mean and std. deviation
 * Compute and print timings
 * 
 * @author Kevin Wayne
 * @author Jeff Forbes
 * @author Josh Hug
 */

public class PercolationStats {
	public static int RANDOM_SEED = 1234;
	public static Random ourRandom = new Random(RANDOM_SEED);
	private int size; 
	private int times; 
	private IPercolate perc; 
	private double[] prob; 
	public PercolationStats(int N, int T) {
		if (N <= 0 || T <= 0) {
			throw new IllegalArgumentException("Invalid values of N and T"); 
		}
		 prob = new double[T]; 
		 size = N; 
		 times = T; 
		 ArrayList<Point> cells = new ArrayList<Point>(); 
		 for (int i = 0; i < N; i++) {
			 for (int j=0; j<N; j++) {
				 cells.add(new Point(i, j)); 
			 }
		 }
		 
		 for (int i=0; i<T; i++) {
			 Collections.shuffle(cells, ourRandom);
			 int count = 0; 
			 perc = new PercolationUF(N, new QuickUWPC()); 
			 while(!perc.percolates()) {
				 perc.open(cells.get(count).x, cells.get(count).y);
				 count += 1; 
			 }
			 prob[i] = (double) count/(N*N); 
		 }	 
	}
	/*public double mean(double[] openProb) {
		return StdStats.mean(openProb); 
	}
	public double stddev(double[] openProb) {
		return StdStats.stddev(openProb);
	}
	public double confidenceLow(double[] openProb) {
		return this.mean(openProb) - 1.96 * this.stddev(openProb) / Math.sqrt(times); 
	}
	public double confidenceHigh(double[] openProb) {
		return this.mean(openProb) + 1.96 * this.stddev(openProb) / Math.sqrt(times); 
	}*/
	public double mean() {
		return StdStats.mean(this.prob); 
	}
	public double stddev() {
		return StdStats.stddev(this.prob);
	}
	public double confidenceLow() {
		return this.mean() - 1.96 * this.stddev() / Math.sqrt(times); 
	}
	public double confidenceHigh() {
		return this.mean() + 1.96 * this.stddev() / Math.sqrt(times); 
	}
	public static void main(String[] args) {
		double total =0; 
		for (int i=0; i<10; i++) {
		double start =  System.nanoTime();
		PercolationStats ps = new PercolationStats(20,400);
		double end =  System.nanoTime();
		double time =  (end-start)/1e9;
		total += time; 
		//System.out.printf("time: %1.4f\n",time);
		}
		System.out.println(total);
	}
}
