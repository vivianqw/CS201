import java.util.*;
import java.util.Arrays;
import java.util.Set;
import java.util.TreeSet;

public class MemberCheck {
	public String[] whosDishonest(String[] club1, String[] club2, String[] club3) {
		ArrayList<String> clubOne = new ArrayList<String>(Arrays.asList(club1)); 
		ArrayList<String> clubTwo = new ArrayList<String>(Arrays.asList(club2)); 
		ArrayList<String> clubThree = new ArrayList<String>(Arrays.asList(club3)); 
		Set<String> result = new TreeSet<>();
		
	   	result.addAll(intersection(clubOne, clubTwo));
	   	result.addAll(intersection(clubOne, clubThree));
	   	result.addAll(intersection(clubTwo, clubThree));
	   	
	   	return result.toArray(new String[0]);
	}
	public ArrayList<String> intersection(ArrayList<String> list1, ArrayList<String> list2) {
		ArrayList<String> intersection = new ArrayList<String>(); 
		for (String names:list1) {
			if (list2.contains(names)) {
				intersection.add(names);
			}
		}
		return intersection; 
	}
}

