import java.util.*;

public class SimpleWordGame {
	public int points(String[] player, String[] dictionary) {
		Set<String> players = new HashSet<String>(Arrays.asList(player)); 
		Set<String> dictionaries = new HashSet<String>(Arrays.asList(dictionary)); 
		Set<String> correct = new HashSet<String>(); 
		correct.addAll(players); 
		correct.retainAll(dictionaries); 
		String[] answers = correct.toArray(new String[correct.size()]); 
		int score = 0; 
		for (String word:answers) {
			score += word.length()*word.length(); 
		}
		return score; 
}
}
