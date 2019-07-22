import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

public class NBody {

	public static double readRadius(String fname) {
		try {
			Scanner in = new Scanner(new File(fname)); 
			int numBodies = in.nextInt();
			double radius = in.nextDouble(); 
			in.close(); 
			return radius; 
		}
		catch (FileNotFoundException e) {
			System.out.println("The file is not found");
			System.exit(1);
			return 0; 
		}
		
	}
	public static Body[] readBodies(String fname) {
		try {
			Scanner in = new Scanner(new File(fname)); 
			int numBodies = in.nextInt(); 
			double radius = in.nextDouble(); 
			Body[] allBodies = new Body[numBodies];
			for (int i=0; i<numBodies; i++) {
				allBodies[i] = new Body (in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble(), in.nextDouble(), in.next()); 
			
			}
			in.close();
			return allBodies; 
			 
		}
		catch (FileNotFoundException e) {
			System.out.println("The file is not found");
			System.exit(1);
			Body[] allBodies = new Body[1];
			return allBodies; 
			
		}
	}
	public static void main(String[] args) {

		double totalTime = 157788000.0;
		//double totalTime = 1000000000; 
		double dt = 25000.0;
		//double dt = 1000000; 
		String pfile = "data/planets.txt";
		if (args.length > 2) {
			totalTime = Double.parseDouble(args[0]);
			dt = Double.parseDouble(args[1]);
			pfile = args[2];
		
		}
		// Read simulation data from file
		Body[] allBodies = NBody.readBodies("./data/planets.txt");// readBodies(pfile);
		double radius = 0.0; // readRadius(pfile);

		// TODO Draw the background
		radius = NBody.readRadius("./data/planets.txt");
		StdDraw.setScale(-radius, radius);
		StdDraw.picture(0, 0, "images/starfield.jpg");
		for (int i=0; i<allBodies.length; i++) {
			allBodies[i].draw(allBodies[i].myXPos, allBodies[i].myYPos, allBodies[i].myFileName); 
		}
		
		// TODO Animate the simulation from time 0 until totalTime
		for (double time = 0; time<totalTime; time+=dt) {
			double[] xForces = new double[allBodies.length]; 
			double[] yForces = new double[allBodies.length]; 
			for (int i=0; i<allBodies.length; i++) {
				xForces[i] = allBodies[i].calcNetForceExertedByX(allBodies); 
				yForces[i] = allBodies[i].calcNetForceExertedByY(allBodies); 
			}
			for (int i=0; i<allBodies.length; i++) {
				allBodies[i].update(dt, xForces[i], yForces[i]);
			}
			radius = NBody.readRadius("./data/planets.txt");
			StdDraw.setScale(-radius, radius);
			StdDraw.picture(0, 0, "images/starfield.jpg");
			for (int i=0; i<allBodies.length; i++) {
				allBodies[i].draw(allBodies[i].myXPos, allBodies[i].myYPos, allBodies[i].myFileName); 
			}
			StdDraw.show(1);
			
		}
		// Print final positions of planets
		System.out.printf("%d\n", allBodies.length);
		System.out.printf("%.2e\n", radius);
		for (int i = 0; i < allBodies.length; i++) {
			System.out.printf("%11.4e %11.4e %11.4e %11.4e %11.4e %12s\n", 
							  allBodies[i].myXPos, allBodies[i].myYPos,
							  allBodies[i].myXVel, allBodies[i].myYVel, 
							  allBodies[i].myMass, allBodies[i].myFileName);
		}
	}
}

