import java.util.*;


public class LeafTrails {
	TreeMap<Integer, String> myMap = new TreeMap<>(); 
	 public String[] trails(TreeNode tree) {
         this.makeMap(tree, "");
         Collection<String> val = myMap.values(); 
         ArrayList<String> valList = new ArrayList<String>(val); 
         String[] valArray = new String[valList.size()]; 
         for (int i=0; i<valArray.length; i++) {
        	 valArray[i] = valList.get(i); 
         }
         return valArray; 
     }
	 public void makeMap(TreeNode tree, String path) {
		 TreeNode current = tree; 
		 if (current == null)
			 return; 
		 if (current.left == null && current.right == null) {
			 myMap.put(current.info, path); 
			 return; 
		 }
		this.makeMap(current.left, path + "0");
		this.makeMap(current.right, path + "1");
	 }
}
