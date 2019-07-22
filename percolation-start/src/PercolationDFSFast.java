//Vivian Qi
public class PercolationDFSFast extends PercolationDFS{
	private int size; 
	public PercolationDFSFast(int n) {
		super(n);
		size = n; 
	}

	@Override
	protected void updateOnOpen(int row, int col){
		if (! inBounds(row, col)) {
			throw new IndexOutOfBoundsException("Out of bound updateOnOpen"); 
		}
		if (row == 0 && col == 0) {//the newly opened cell is in first row
			if (isFull(row, col) || isFull(row+1, col) || isFull(row, col+1)) {
				myGrid[row][col] = FULL;
				dfs(row+1, col); 
				dfs(row, col+1);
			}
		}
		else if (row == 0 && col == size-1) {
			if (isFull(row, col) || isFull(row+1, col) || isFull(row, col-1)) {
				myGrid[row][col] = FULL;
				dfs(row+1, col); 
				dfs(row, col-1);
			}
		}
		else if (row == 0 && col != 0 && col != size -1) {
			if (isFull(row, col) || isFull(row+1, col) || isFull(row, col+1)|| isFull(row, col-1)) {
				myGrid[row][col] = FULL;
				dfs(row+1, col); 
				dfs(row, col+1);
				dfs(row, col-1);
			}
		}
		else if (row == size - 1 && col ==0 ) {
			if (isFull(row, col) || isFull(row-1, col) || isFull(row, col+1)) {
				myGrid[row][col] = FULL;
				dfs(row-1, col); 
				dfs(row, col+1);
			}
		}
		else if (row == size - 1 && col == size - 1) {
			if (isFull(row, col) || isFull(row-1, col) || isFull(row, col-1)) {
				myGrid[row][col] = FULL;
				dfs(row-1, col); 
				dfs(row, col-1);
			}
		}
		else if (row == size - 1 && col != 0 && col != size - 1) {
			if (isFull(row, col) || isFull(row-1, col) || isFull(row, col+1)|| isFull(row, col-1)) {
				myGrid[row][col] = FULL;
				dfs(row-1, col); 
				dfs(row, col+1);
				dfs(row, col-1);
			}
		}
		else if (col == 0 && row != 0 && row != size - 1) {//the newly opened cell is in the leftmost column
			if (isFull(row, col) || isFull(row-1, col) || isFull(row+1, col) || isFull(row, col+1)) {
				myGrid[row][col] = FULL;
				dfs(row-1, col); 
				dfs(row+1, col);
				dfs(row, col+1); 
			}
		}
		else if (col == size-1 && row != 0 && row != size - 1) {//the newly opened cell is in the rightmost column
			if (isFull(row, col) || isFull(row-1, col) || isFull(row+1, col) || isFull(row, col-1)) {
				myGrid[row][col] = FULL;
				dfs(row-1, col); 
				dfs(row+1, col);
				dfs(row, col-1); 
			}
		}
		else {//the newly opened cell is in the middle of the grid
			if (isFull(row-1, col) || isFull(row+1, col) || isFull(row, col-1)|| isFull(row, col+1)) {
				myGrid[row][col] = FULL;
				dfs(row-1, col); 
				dfs(row+1, col); 
				dfs(row, col-1); 
				dfs(row, col+1); 
			}
		}
	}


	@Override
	public boolean isOpen(int row, int col) {//the 3 methods below throws IndexOutOfBoundsException, and then does what the parent class does
		if (! inBounds(row, col)) {
			throw new IndexOutOfBoundsException("Out of bound isOpen"); 
		}
		return super.isOpen(row, col); 
	}
	@Override
	public boolean isFull(int row, int col) {
		if (! inBounds(row, col)) {
			throw new IndexOutOfBoundsException("Out of bound isFull"); 
		}
		return super.isFull(row, col); 
	}
	@Override
	public void open(int row, int col) {
		if (! inBounds(row, col)) {
			throw new IndexOutOfBoundsException("Out of bound open"); 
		}
		super.open(row, col);
	}

}
