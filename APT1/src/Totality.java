
public class Totality {
	public int sum(int[] a, String stype) {
		int sum = 0; 
		if (stype.equals("odd")){
			int i=1; 
			while(i<a.length) {
				sum += a[i]; 
				i += 2; 
			}
		}
		else if (stype.equals("even")) {
			int i=0; 
			while(i<a.length) {
				sum += a[i]; 
				i += 2; 
			}
		}
		else if (stype.equals("all")) {
			int i=0; 
			while(i<a.length) {
				sum += a[i]; 
				i += 1; 
			}
		}
		return sum;
	}
		
	}
