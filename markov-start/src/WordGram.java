
import java.util.*;

public class WordGram implements Comparable<WordGram>{

	private int myHash;
	private String[] myWords;  

	public WordGram(String[] words, int index, int size) {
		myWords = new String[size]; 
		for(int i=0; i<size; i++) {
			myWords[i] = words[index+i]; 
		}
	}

	@Override
	public int hashCode() {
		if (myHash == 0) {
			for(int i=0; i<myWords.length; i++) {
				myHash += myWords[i].hashCode()*Math.pow(5, i); 
			}		
		}
		return myHash;
	}

	@Override
	public String toString() {
		String output = String.join(" ", myWords); 
		return output;
	}

	@Override
	public boolean equals(Object other) {
		if (other == null || ! (other instanceof WordGram)) {
			return false;
		}
		WordGram wg = (WordGram) other; 
		if (this.length() != wg.length()) {
			return false; 
		}
		for (int i=0; i<myWords.length; i++){
			if (!this.myWords[i].equals(wg.myWords[i])){
				return false; 
			}
		}
		return true; 
	}

	@Override
	public int compareTo(WordGram wg) {
		for (int i=0; i < this.myWords.length; i++) {
			if (wg.myWords.length < i+1) {
				return 1; 
			}
			int output = this.myWords[i].compareTo(wg.myWords[i]);
			if (output != 0) {
				return output; 
			}
		}
		if (wg.myWords.length > this.myWords.length) {
			return -1; 
		}
		return 0; 
	}

	public int length() {
		return this.myWords.length; 
	}

	public WordGram shiftAdd(String last) {
		ArrayList<String> newList = new ArrayList<String>(Arrays.asList(this.myWords)); 
		newList.add(last); 
		newList.remove(newList.get(0)); 
		String[] newArray = new String[newList.size()]; 
		newList.toArray(newArray); 
		return new WordGram(newArray, 0, newArray.length);
	}
	public String[] returnWordgram() {
		return myWords; 
	}
}
