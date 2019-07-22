
public class Body {

	double myXPos; 
	double myYPos; 
	double myXVel; 
	double myYVel; 
	double myMass; 
	String myFileName;

	public Body(double xp, double yp, double xv, double yv, double mass, String filename) {
		myXPos = xp; 
		myYPos = yp; 
		myXVel = xv; 
		myYVel = yv; 
		myMass = mass; 
		myFileName = filename; 
	}

	public Body(Body p) {
		myXPos = p.myXPos; 
		myYPos = p.myYPos; 
		myXVel = p.myXVel; 
		myYVel = p.myYVel; 
		myMass = p.myMass; 
		myFileName = p.myFileName; 
	}

	public double calcDistance(Body p) {
		double pXPos = p.myXPos; 
		double pYPos = p.myYPos; 
		double dx = pXPos - myXPos; 
		double dy = pYPos - myYPos; 
		double distance = Math.sqrt(dx*dx+dy*dy); 
		return distance;
	}

	public double calcForceExertedBy(Body p) {
		double pMass = p.myMass; 
		double distance = calcDistance(p);
		double G = 6.67 * Math.pow(10,-11);
		double force = G * pMass * myMass / Math.pow(distance, 2); 
		return force; 
	}
	public double calcForceExertedByX(Body p) {
		double dx = p.myXPos - myXPos; 
		double force = calcForceExertedBy(p); 
		double distance = calcDistance(p); 
		double Fx = force * dx / distance; 
		return Fx; 
	}
	public double calcForceExertedByY(Body p) {
		double dy = p.myYPos - myYPos; 
		double force = calcForceExertedBy(p); 
		double distance = calcDistance(p); 
		double Fy = force * dy / distance; 
		return Fy; 
	}
	public double calcNetForceExertedByX(Body p[]) {
		double netForceX = 0; 
		for (int i=0; i<p.length; i++) {
			if(p[i].myXPos == myXPos && p[i].myYPos == myYPos) {
				netForceX += 0;			
			}
			else {
				netForceX += calcForceExertedByX(p[i]); 
			}
		}
		return netForceX; 
	}
	public double calcNetForceExertedByY(Body p[]) {
		double netForceY = 0; 
		for (int i=0; i<p.length; i++) {
			if(p[i].equals(this)) {
				netForceY += 0;			
			}
			else {
				netForceY += calcForceExertedByY(p[i]); 
			}
		}
		return netForceY; 
	}
	public void update(double seconds, double xforce, double yforce) {
		double accX = xforce/myMass; 
		double accY = yforce/myMass; 
		myXVel += seconds * accX; 
		myYVel += seconds * accY; 
		myXPos += seconds * myXVel; 
		myYPos += seconds * myYVel; 
	}
	public void draw(double myXPos, double myYPos, String myFileName) {
		StdDraw.picture(myXPos,  myYPos,  "images/"+myFileName);
	}
}
