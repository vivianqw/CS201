import java.util.*;

public class Dirsort {
	class APTComp implements Comparator<String>{
		public int compare(String a, String b) {
			String[] aArray = a.split("/"); 
			String[] bArray = b.split("/"); 
			if (aArray.length != bArray.length) {
				return aArray.length - bArray.length; 
			}
			for (int i=0; i < aArray.length; i++) {
				if (!aArray[i].equals(bArray[i])) {
					return aArray[i].compareTo(bArray[i]); 
				}
			}
			return 0; 
		}
	}
	public String[] sort(String[] dirs) {
		Arrays.sort(dirs, new APTComp());
		return dirs;
	}
}




