import java.util.*;

public class Internet {
	public int articulationPoints(String[] routers) {
		int points = 0; 
		ArrayList<String> routersList = new ArrayList<>(Arrays.asList(routers)); 
		for (int i=0; i<routersList.size(); i++) {
			ArrayList<String> removed = routersList; 
			removed.remove(i); 
			TreeSet<String> index = new TreeSet<>(); 
			for (int j = 0; j<removed.size(); j++) {
				String[] connect = removed.get(j).split("\\s+"); 
				for (int k=0; k<connect.length; i++) {
					index.add(connect[k]); 
				}
			}
			if (index.size() == routersList.size()) {
				points++; 
			}
		}
		return 0; 
	}
}
