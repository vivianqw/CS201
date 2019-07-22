import java.util.*;

public class Anonymous {
	public int howMany(String[] headlines, String[] messages) {
		TreeMap<Character, Integer> occur = new TreeMap<Character, Integer>(); 
		TreeMap<Character, Integer> need = new TreeMap<Character, Integer>(); 
		String headline; 
		String message; 
		List<String> hdList = new ArrayList<>(); 
		List<String> msList = new ArrayList<>(); 
		int count = 0; 
		int finalCount = 0; 
		Set<Character> needSet = null;
		for (int i=0; i<headlines.length; i++) {
			headline = headlines[i].toLowerCase(); 
			hdList = Arrays.asList(headline.split("//s")); 
			for (String word: hdList) {
				for (int k=0; k<word.length(); k++) {
					char letter = word.charAt(k);
					if (occur.containsKey(letter)) {
						occur.put(letter, occur.get(letter)+1); 
					}
					else {
						occur.put(letter, 1); 
					}		 
				}
			}
		}//correct occur
		occur.remove(' '); 

		Set<Character> keySet = occur.keySet(); //correct keySet


		for (int j=0; j<messages.length; j++) { 
			need.clear(); 
			count = 0; 
			message = messages[j].toLowerCase(); 
			msList = Arrays.asList(message.split("//s")); 
			for (String word2: msList) {
				for (int q=0; q<word2.length(); q++) {
					char needLetter = word2.charAt(q);
					if (need.containsKey(needLetter)) {
						int old = need.get(needLetter);
						need.put(needLetter, old+1); 
					}
					else {
						need.put(needLetter, 1); 
					}		 
				}
			}		
			need.remove(' '); 
			needSet = need.keySet(); 
			for (Character c: needSet) {
				if (!keySet.contains(c) || occur.get(c) < need.get(c)) {
					break; 
				}
				else if (occur.get(c) >= need.get(c)) {
					count++; 
				}
			}
			if (needSet.size()==0 ||count == needSet.size() ) {
				finalCount++; 
			}

		}
		return finalCount; 
	}
}

