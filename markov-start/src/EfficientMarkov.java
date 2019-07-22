import java.util.*;

public class EfficientMarkov extends MarkovModel {
	
	Map<String, ArrayList<String>> myMap;	
	
	public EfficientMarkov(int order) {
		super(order); 
		myMap = new HashMap<String, ArrayList<String>>();
	}

	public void setTraining(String text) { 
		super.setTraining(text);
		myMap.clear();
//first read the n to n+order-1 element of the string. This is the key. Create new if key does not exist before. 
//Then read n+order as a value to the key. Store. 
		for (int i = 0; i<text.length()-myOrder; i++) {
			String key = text.substring(i, i + myOrder); 
			String value = Character.toString(text.charAt(i+myOrder)); 
			ArrayList<String> valueList = new ArrayList<String>();
			if (!myMap.containsKey(key)) {
				valueList.add(value); 
				myMap.put(key, valueList); 
			}
			else {
				valueList = myMap.get(key);
				valueList.add(value); 
				myMap.put(key, valueList); 
			}
		}
	}
	
	public ArrayList<String> getFollows(String key){
		ArrayList<String> follows = new ArrayList<String>(); 
		if (!myMap.containsKey(key)) {
			System.out.println("Key is not found"); 
		}
		else {
			follows = myMap.get(key); 
		}
		return follows; 
	}
}
