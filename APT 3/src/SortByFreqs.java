import java.util.*;

public class SortByFreqs {
	static class Compare implements Comparator<String>{
		Map<String, Integer> myMap; 
		public Compare(TreeMap<String, Integer> map) {
			this.myMap = map; 
		}
		public int compare(String a, String b) {
			if (myMap.get(a) != myMap.get(b)) {
				return myMap.get(b) - myMap.get(a); 
			}
			return a.compareTo(b); 
		}
	}
	 public String[] sort(String[] data) {
         TreeMap<String, Integer> map = new TreeMap<>(); 
         for (int i=0; i<data.length; i++) {
        	 if (!map.containsKey(data[i])) {
        		 map.put(data[i], 1); 
        	 }
        	 else {
        		 map.put(data[i], map.get(data[i])+1); 
        	 }
         }
         Compare toSort = new Compare(map); 
         TreeMap<String, Integer> sorted = new TreeMap<String, Integer>(toSort);
         sorted.putAll(map);
         Set<String> keySet = sorted.keySet(); 
         String[] sort = keySet.toArray(new String[keySet.size()]); 
         return sort; 
     }
}
