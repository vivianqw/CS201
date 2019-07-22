import java.util.*; 
public class EfficientWordMarkov extends WordMarkovModel{

	Map <WordGram, ArrayList<String>> myMap;   
	String myText; 

	public EfficientWordMarkov(int order) {
		super(order); 
		myMap = new HashMap<WordGram, ArrayList<String>>(); 
	}
	
	public void setTraining(String text) {
		super.setTraining(text);
		myMap.clear(); 
		myText = text; 
		for (int i=0; i<myWords.length-myOrder; i++) {
			WordGram wg = new WordGram(myWords, i, myOrder); 
			String follow = myWords[i+myOrder]; 
			ArrayList<String> value = new ArrayList<String>(); 
			if (!myMap.keySet().contains(wg)) {
				value.add(follow); 
				myMap.put(wg, value); 
			}
			else {
				value = myMap.get(wg); 
				value.add(follow); 
				myMap.put(wg, value); 
			}
		}
	}

	
	public ArrayList<String> getFollows(WordGram kGram){

		ArrayList<String> follows = new ArrayList<String>(); 
		if (!myMap.containsKey(kGram)) {
			System.out.println("Key is not found"); 
		}
		else {
			follows = myMap.get(kGram);  
		}
		return follows; 
	}
	public Map<WordGram, ArrayList<String>> returnMap(){
		return myMap; 
	}
}
