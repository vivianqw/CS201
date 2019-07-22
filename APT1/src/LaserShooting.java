
public class LaserShooting {
	public double numberOfHits(int[] x, int[] y1, int[] y2) {
		double probability = 0; 
		for (int i=0; i<x.length; i++) {
			int xPos = x[i]; 
			int y1Pos = y1[i]; 
			int y2Pos = y2[i]; 
			double a = Math.sqrt(xPos*xPos+y2Pos*y2Pos);
			double b = Math.sqrt(xPos*xPos+y1Pos*y1Pos); 
			double c = y2Pos - y1Pos; 
			double cosTheta = (a*a+b*b-c*c)/(2*a*b); 
			double theta = Math.acos(cosTheta); 
			probability += theta/Math.PI;        	 
		}
		return probability; 
	}
}
