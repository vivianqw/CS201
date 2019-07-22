import java.util.Arrays;
//Vivian Qi
/**
 * Simulate a system to see its Percolation Threshold, but use a UnionFind
 * implementation to determine whether simulation occurs. The main idea is that
 * initially all cells of a simulated grid are each part of their own set so
 * that there will be n^2 sets in an nxn simulated grid. Finding an open cell
 * will connect the cell being marked to its neighbors --- this means that the
 * set in which the open cell is 'found' will be unioned with the sets of each
 * neighboring cell. The union/find implementation supports the 'find' and
 * 'union' typical of UF algorithms.
 * <P>
 * 
 * @author Owen Astrachan
 * @author Jeff Forbes
 *
 */

public class PercolationUF implements IPercolate {
	private final int OUT_BOUNDS = -1;
	private boolean[][] myGrid; 
	private int openSites; 
	private IUnionFind myFinder; 
	private final int vTop; 
	private final int vBottom; 
	private int n; 
	/**
	 * Constructs a Percolation object for a nxn grid that that creates
	 * a IUnionFind object to determine whether cells are full
	 */
	public PercolationUF(int size, IUnionFind finder) {//construct the PercolationUF object with size of matrix
		n = size; 
		myGrid = new boolean[size][size]; 
		for (boolean[] row:myGrid) {
			Arrays.fill(row, false);
		}
		int finderSize = size * size + 2; 
		myFinder = finder; 
		myFinder.initialize(finderSize);
		vTop = size * size ; 
		vBottom = size * size + 1; //top and bottom "index" is specified
	}

	/**
	 * Return an index that uniquely identifies (row,col), typically an index
	 * based on row-major ordering of cells in a two-dimensional grid. However,
	 * if (row,col) is out-of-bounds, return OUT_BOUNDS.
	 */
	private int getIndex(int row, int col) {//convert a coordinate to a number
		if (row<0 || row >=n || col<0 || col>= n) {
			return OUT_BOUNDS; 
		}
		int output = row * n + col; 
		return output;
	}
	@Override
	public void open(int i, int j) {//opens up a cell. Checks for out of bounds first. 
		if (! inBounds(i, j)) {
			throw new IndexOutOfBoundsException("Out of bound open"); 
		}
		if (myGrid[i][j])
			return;
		openSites += 1;
		myGrid[i][j] = true;
		connect(i,j);//update on whether the cell is filled after opening it up
	}
	@Override
	public boolean isOpen(int i, int j) {//check if a certain grid is open
		if (! inBounds(i, j)) {
			throw new IndexOutOfBoundsException("Out of bound isOpen"); 
		}
		return myGrid[i][j];
	}
	@Override
	public boolean isFull(int i, int j) {//full is defined as the grid is somehow connected to the top
		if (! inBounds(i, j)) {
			throw new IndexOutOfBoundsException("Out of bound isFull"); 
		}
		// TODO complete isFull
		return myFinder.connected(this.getIndex(i, j), vTop); 
	}
	@Override
	public int numberOfOpenSites() {
		return openSites; 
	}
	@Override
	public boolean percolates() {//percolates is defined as the top is connected to the bottom
		// TODO complete percolates
		return myFinder.connected(vTop, vBottom);
	}

	/**
	 * Connect new site (row, col) to all adjacent open sites
	 */
	private void connect(int row, int col) {
		// TODO complete connect
		if (! inBounds(row, col)) {
			throw new IndexOutOfBoundsException("Out of bound connect"); 
		}
		if (row == 0) {//first row; automatic union this with vTop
			myFinder.union(this.getIndex(row, col), vTop);
		}
		if (row == n-1) {//last row; automatic union this with vBottom
			myFinder.union(this.getIndex(row, col), vBottom);
		}
		if (inBounds(row, col-1) && myGrid[row][col-1]) {//if the left cell is full, then the open cell is unioned with the left cell
			myFinder.union(this.getIndex(row, col), this.getIndex(row, col-1));
		}
		if (inBounds(row, col+1) && myGrid[row][col+1]) {//same, but checks if the right cell is full
			myFinder.union(this.getIndex(row, col), this.getIndex(row, col+1));
		}
		if (inBounds(row-1, col) && myGrid[row-1][col]) {//same, but checks if the top cell is full
			myFinder.union(this.getIndex(row, col), this.getIndex(row-1, col));
		}
		if (inBounds(row+1, col) && myGrid[row+1][col]) {//same, but checks if the bottom cell is full
			myFinder.union(this.getIndex(row, col), this.getIndex(row+1, col));
		}
	}
	private boolean inBounds(int row, int col) {//used to throw IndexOutOfBoundException. Check if a cell is in bound. 
		if (row < 0 || row >= myGrid.length) return false;
		if (col < 0 || col >= myGrid[0].length) return false;
		return true;
	}

}
