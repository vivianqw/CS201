import javax.swing.tree.TreeNode;
import java.util.*;

public class LeafTrails {
	TreeMap<Integer, String> map; 
	 public String[] trails(TreeNode tree) {
         helper(tree, ""); 
         String[] ret = new String[map.size()]; 
         int index = 0; 
         for (int key:map.keySet()){
        	 ret[index] = map.get(key); 
        	 index++; 
         }
         return ret; 
     }
	 public void helper(TreeNode tree, String path) {
		 if (tree == null)
			 return;
		 if (tree.left == null && tree.right == null) {
			 map.put(tree.info, path); 
		 }
		 helper(tree.left, path+"0"); 
		 helper(tree.right, path+"1"); 
	 }
}
