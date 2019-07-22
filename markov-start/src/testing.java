import java.util.*;

public class testing {
	public static void main(String[] args) {
		String text = "This is the test I am using for efficient word markov"; 
		EfficientWordMarkov test = new EfficientWordMarkov(4); 
		WordGram wg = new WordGram(text.split("\\s+"), 0, 4);
		String[] want = wg.returnWordgram();
		for (int i=0; i<want.length; i++) {
			System.out.print(want[i]);
		}
		test.setTraining(text);
		ArrayList<String> out = test.getFollows(wg); 
		for (int i=0; i<out.size(); i++) {
			System.out.println(out.get(i));
		}
		Map <WordGram, ArrayList<String>> output = test.returnMap(); 
		for (WordGram a: output.keySet()) {
			String[] str = a.returnWordgram(); 
			for (String s:str) {
				System.out.print(s);
			}
			System.out.println(output.get(a));
		}
		
	}
	
}
