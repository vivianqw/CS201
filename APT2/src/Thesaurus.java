
import java.util.*;

public class Thesaurus {
	
	public Set<String> sToSet(String s) {
		return new TreeSet<String>(Arrays.asList(s.split(" "))); //split the string to an array of words, and convert array to set
	}

	public String collToS(Collection<String> elems) {
		String result = ""; 
		if (elems == null || elems.size() == 0) {
			return result; 
		}
		else {
			for (String word:elems) {
				result +=  word + " "; 
			}
		}

		return result.substring(0, result.length()-1);
	}

	public int numInCommon(Set<String> a, Set<String> b) {
		TreeSet<String> common = new TreeSet<>(a); 
		common.retainAll(b); 
		return common.size();
	}

	public TreeSet<String> union(Set<String> a, Set<String> b) {
		TreeSet<String> results = new TreeSet<String>(a);
		results.addAll(b);
		return results;
	}

	public String[] edit(String[] entry) {
		ArrayList<String> entries = new ArrayList<String>(Arrays.asList(entry)); 
		Thesaurus dictionary = new Thesaurus(); 
		int common = 0;  
		String stringUnion; 
		TreeSet<String> union = new TreeSet<String>(); 
		int i; 
		int j; 
		String[] output; 
		for (i = 0; i<entries.size(); i++) {
			TreeSet<String> seti = new TreeSet<String>(dictionary.sToSet(entries.get(i))); 
			for (j = i+1; j<entries.size(); j++) {
				TreeSet<String> setj = new TreeSet<String>(dictionary.sToSet(entries.get(j)));
				common = dictionary.numInCommon(seti, setj); 
				if (common >= 2) {
					union = dictionary.union(seti, setj); 
					stringUnion = dictionary.collToS(union); 
					entries.set(i, stringUnion);
					entries.remove(j); 
					i = -1; 
					j = 0; 
					break; 
				}
				else {
					entries.set(i, dictionary.collToS(seti)); 
					entries.set(j, dictionary.collToS(setj));
				}
			}
		}
		Collections.sort(entries);
		output = entries.toArray(new String[entries.size()]);
		return output; 
	}

}
