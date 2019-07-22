import java.util.*;

public class testing {
	public static void main(String[] args) {
		String[] headlines ={"Programming is fun "}; 
		String[] messages = {"program ", " programmer ", " gaming ", " sing ", " NO FUN "}; 
		Anonymous test = new Anonymous(); 
		int output = test.howMany(headlines, messages); 
		System.out.println(output);
		/*TreeMap<Character, Integer> output = test.howMany(headlines, messages); 
		for (Character c: output.keySet()) {
			System.out.print(c);
			System.out.println(output.get(c));
		}*/
		}
	}

