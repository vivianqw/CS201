import java.util.*;

public class TheBestName {
	static class Compare implements Comparator<String>{
		public int compare(String a, String b) {
			if (a.equals("JOHN")) {
				return -1; 
			}
			else if (b.equals("JOHN")) {
				return 1; 
			}
			int aValue = 0; 
			int bValue = 0; 
			for (int i=0; i<a.length(); i++) {
				aValue += (int) a.charAt(i) -64; 
			}
			for (int j=0; j<b.length(); j++) {
				bValue += (int) b.charAt(j) - 64; 
			}
			if (aValue != bValue) {
				return bValue - aValue; 
			}
			return a.compareTo(b); 
		}
	}
	 public String[] sort(String[] names) {
         Arrays.sort(names, new Compare()); 
         return names; 
  }
}
