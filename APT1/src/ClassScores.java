import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ClassScores {
	public int[] findMode(int[] scores) {
		int[] allScores = new int[101]; 
		int modeCount = 0; 
		int maxOccur = 0; 
		for (int element:scores) {
			allScores[element] += 1; 
			if (allScores[element] > maxOccur) {
				maxOccur = allScores[element]; 
				modeCount = 1; 
			}
			else if (allScores[element] == maxOccur) {
				modeCount += 1; 
			}
		}
		int[] modes = new int[modeCount]; 
		int index = 0; 
		for (int i=0; i<101; i++) {
			if (allScores[i] == maxOccur) {
				modes[index] = i; 
				index ++; 
			}
		}
		return modes; 
    }
}
