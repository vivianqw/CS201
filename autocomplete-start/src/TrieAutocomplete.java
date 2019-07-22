import java.util.*;

/**
 * General trie/priority queue algorithm for implementing Autocompletor
 * 
 * @author Austin Lu
 * @author Jeff Forbes
 */
public class TrieAutocomplete implements Autocompletor {

	/**
	 * Root of entire trie
	 */
	protected Node myRoot;

	/**
	 * Constructor method for TrieAutocomplete. Should initialize the trie rooted at
	 * myRoot, as well as add all nodes necessary to represent the words in terms.
	 * 
	 * @param terms
	 *            - The words we will autocomplete from
	 * @param weights
	 *            - Their weights, such that terms[i] has weight weights[i].
	 * @throws NullPointerException
	 *             if either argument is null
	 * @throws IllegalArgumentException
	 *             if terms and weights are different length
	 */
	public TrieAutocomplete(String[] terms, double[] weights) {
		if (terms == null || weights == null) {
			throw new NullPointerException("One or more arguments null");
		}
		if (terms.length != weights.length) {
			throw new IllegalArgumentException("Terms and weights are different length"); 
		}
		TreeSet<String> ts = new TreeSet<String>(); 
		for (int i = 0; i < terms.length; i++) {
			ts.add(terms[i]);
			if (weights[i] < 0) {
				throw new IllegalArgumentException("Negative weight");
			}
		}
		if (ts.size() != terms.length) {
			throw new IllegalArgumentException("Duplicate words");
		}

		// Represent the root as a dummy/placeholder node
		myRoot = new Node('-', null, 0);

		for (int i = 0; i < terms.length; i++) {
			add(terms[i], weights[i]);
		}
	}

	/**
	 * Add the word with given weight to the trie. If word already exists in the
	 * trie, no new nodes should be created, but the weight of word should be
	 * updated.
	 * 
	 * In adding a word, this method should do the following: Create any necessary
	 * intermediate nodes if they do not exist. Update the subtreeMaxWeight of all
	 * nodes in the path from root to the node representing word. Set the value of
	 * myWord, myWeight, isWord, and mySubtreeMaxWeight of the node corresponding to
	 * the added word to the correct values
	 * 
	 * @throws a
	 *             NullPointerException if word is null
	 * @throws an
	 *             IllegalArgumentException if weight is negative.
	 */
	private void add(String word, double weight) {
		// TODO: Implement add
		if (word == null) {
			throw new NullPointerException("Word is null"); 
		}
		if (weight < 0) {
			throw new IllegalArgumentException("Weight is negative"); 
		}
		Node current = myRoot;
		for(int k=0; k < word.length(); k++){
			char ch = word.charAt(k);
			if (current.children.get(ch) == null) {
				current.children.put(ch,new Node(ch, current, weight));
			}
			if (current.mySubtreeMaxWeight < weight) {
				current.mySubtreeMaxWeight = weight; 
			}
			current = current.children.get(ch);
		}
		current.isWord = true; 
		current.setWeight(weight);
		current.setWord(word);
	}

	/**
	 * Required by the Autocompletor interface. Returns an array containing the k
	 * words in the trie with the largest weight which match the given prefix, in
	 * descending weight order. If less than k words exist matching the given prefix
	 * (including if no words exist), then the array instead contains all those
	 * words. e.g. If terms is {air:3, bat:2, bell:4, boy:1}, then topKMatches("b",
	 * 2) should return {"bell", "bat"}, but topKMatches("a", 2) should return
	 * {"air"}
	 * 
	 * @param prefix
	 *            - A prefix which all returned words must start with
	 * @param k
	 *            - The (maximum) number of words to be returned
	 * @return An Iterable of the k words with the largest weights among all words
	 *         starting with prefix, in descending weight order. If less than k such
	 *         words exist, return all those words. If no such words exist, return
	 *         an empty Iterable
	 * @throws a
	 *             NullPointerException if prefix is null
	 */
	public Iterable<String> topMatches(String prefix, int k) {
		// TODO: Implement topKMatches
		if (prefix == null) {
			throw new NullPointerException("Prefix is null"); 
		}
		
		Node current = myRoot; 
		for (int i=0; i<prefix.length(); i++) {
			if (current.children.containsKey(prefix.charAt(i))) {
				current = current.children.get(prefix.charAt(i)); 
			}
			else {
				return new ArrayList<String>(); 
			}
		}
		
		PriorityQueue<Node> queue = new PriorityQueue<Node>(new Node.ReverseSubtreeMaxWeightComparator());
		queue.add(current); 
		ArrayList<String> finalList = new ArrayList<String>(); 
		while (queue.size() > 0) {
			if (finalList.size() >= k) {
				break; 
			}
			current = queue.poll(); 
			if (current.isWord){
				finalList.add(current.myWord); 
				if (finalList.size() >= k) {
					break; 
				}
			}
			for (Node a: current.children.values()) {
				queue.add(a); 
			}
		}
		if (finalList.size() > k) {
			return finalList.subList(0, k); 
		}
		return finalList; 
	}

	/**
	 * Given a prefix, returns the largest-weight word in the trie starting with
	 * that prefix.
	 * 
	 * @param prefix
	 *            - the prefix the returned word should start with
	 * @return The word from with the largest weight starting with prefix, or an
	 *         empty string if none exists
	 * @throws a
	 *             NullPointerException if the prefix is null
	 */
	public String topMatch(String prefix) {
		// TODO: Implement topMatch
		if (prefix == null) {
			throw new NullPointerException("Prefix is null"); 
		}
		Node current = myRoot; 
		for (int i=0; i<prefix.length(); i++) {
			if (current.children.containsKey(prefix.charAt(i))) {
				current = current.getChild(prefix.charAt(i)); 
			}
			else {
				return ""; 
			}
		}
		double maxWeight = current.mySubtreeMaxWeight;
		if (current.myWeight == maxWeight) {
			return current.myWord; 
		}
		while(current.myWeight != maxWeight) {
			for (Node a: current.children.values()) {
				double childMaxWeight = a.mySubtreeMaxWeight; 
				if (childMaxWeight == maxWeight) {
					current = a; 
					break; 
				}
			}
		}
		return current.getWord();
	}

	/**
	 * Return the weight of a given term. If term is not in the dictionary, return
	 * 0.0
	 */
	public double weightOf(String term) {
		// TODO complete weightOf
		Node current = myRoot; 
		for (int i=0; i<term.length(); i++) {
			if (current.children.containsKey(term.charAt(i))) {
				current = current.getChild(term.charAt(i)); 
			}
			else {
				return 0; 
			}
		}
		return current.myWeight; 
	}
}
