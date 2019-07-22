import java.util.Arrays;

public class SandwichBar {
	public int whichOrder(String[] available, String[] orders){
		int number = -1; 
		for(int i=0; i<orders.length; i++) {
			String order = orders[i]; 
			String[] words = order.split("\\s+"); 
			if (Arrays.asList(available).containsAll(Arrays.asList(words))) {
				number = i;
				break; 
			}
		}
		return number; 
	}

}
