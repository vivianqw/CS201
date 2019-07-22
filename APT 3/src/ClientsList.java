import java.util.*;

public class ClientsList {
	static class Compare implements Comparator<String>{
		public int compare(String a, String b) {
			String[] aWords = a.split("\\s+"); 
			String[] bWords = b.split("\\s+"); 
			if (!aWords[1].equals(bWords[1])) {
				return aWords[1].compareTo(bWords[1]); 
			}
			return aWords[0].compareTo(bWords[0]); 
		}
	}
	
	 public String[] dataCleanup(String[] names) {
         for (int i=0; i<names.length; i++) {
        	 names[i] = this.goodFormat(names[i]); 
         }
         Arrays.sort(names, new Compare());
         return names;
    }
	 public String goodFormat(String name) {
		 String out = ""; 
		 if (name.contains(",")) {
			 String[] split = name.split(", "); 
			 out = out + split[1] + " "+ split[0]; 
			 return out; 
		 }
		 return name; 
	 }
}
