import java.util.Arrays;

public class RatRoute {
	private char[][] myGrid;
	private int myRows, myCols;
	private int myCheeseRow, myCheeseCol;
	int sum = 0; 

	/**
	 * Returns the number of possible routes the rat can take to get to
	 * the cheese without ever increasing the distance between itself
	 * and the cheese at any point on its path
	 * @param enc each entry represents one row of the maze
	 */
	public int numRoutes(String[] enc) {
		// initialize instance vars
		myRows = enc.length;
		myCols = enc[0].length();
		myGrid = new char[myRows][myCols];

		int ratRow=0,ratCol=0;
		for(int r=0; r < myRows; r++){
			Arrays.fill(myGrid[r], '.');
			for(int c=0; c < myCols; c++){
				char ch = enc[r].charAt(c);
				// TODO initi

				if (ch == 'R') {
					ratRow = r;
					ratCol = c; 
				}
				else if (ch == 'C') {
					myCheeseRow = r;
					myCheeseCol = c;
					myGrid[r][c] = 'C'; 
				}
				else if (ch == 'X') {
					myGrid[r][c] = 'X'; 
				}
			}
		}
		// find current distance and count # routes
		double currentDistance = cheeseDistance(ratRow,ratCol);
		return routeCount(ratRow, ratCol, currentDistance);
	}

	private double cheeseDistance(int row, int col) {
		// TODO: complete cheeseDistance
		double distance = Math.sqrt(Math.pow(row-myCheeseRow, 2)+Math.pow(col-myCheeseCol, 2));
		return distance;
	}

	private int routeCount(int row, int col, double dist) {
		// TODO: deal with base cases
		// 1. out of bounds
		// 2. on X
		// 3. wrong direction
		// 4. at cheese
		if (row<0 || row>=myRows || col<0 || col>=myCols) {
			return 0; 
		}
		if (myGrid[row][col] == 'X') {
			return 0; 
		}
		if (cheeseDistance(row, col) > dist) {
			return 0; 
		}
		if(row == myCheeseRow && col == myCheeseCol) {
			return 1; 
		}
		// get routes in other directions

		double currentDistance = cheeseDistance(row,col);

		// go up, left, right, down
		int routes = 0; 
		int[] deltaR = {-1, 0, 0, 1};
		int[] deltaC = {0, -1, 1, 0};
		for (int k =0; k < deltaR.length; k++) {
			routes += routeCount(row + deltaR[k], col + deltaC[k], currentDistance);
		}
		// TODO complete routeCount
		return routes;
	}
/*public static void main(String[] args) {
	String[] testString = {"X..X.X.", "XX.C.X.", ".......", "..X.X..", ".......", "R.XX..." }; 
	RatRoute test = new RatRoute(); 
	int out = test.numRoutes(testString); 
	char[][] outGrid = test.myGrid; 
	for(int i=0; i<outGrid.length; i++) {
		for (int j=0; j<outGrid[0].length; j++) {
			System.out.print(outGrid[i][j]);
		}
		System.out.println(); 
	}
	System.out.println(out);
	
}*/
}
