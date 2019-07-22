import java.util.*;

public class SpreadingNews {
	private int[] mySups; 
	private ArrayList<ArrayList<Integer>> mySubs; 
	public int minTime(int[] supervisors) {
		mySups = supervisors; 
		mySubs = new ArrayList<>(); 
		for(int k=0; k<supervisors.length; k++) {
			mySubs.add(new ArrayList<Integer>()); 
		}
		for (int k=0; k<supervisors.length; k++) {
			if (mySups[k] != -1) {
				mySubs.get(mySups[k]).add(k); 
			}
		}
		return minTime(0); 
	}
	public int minTime(int bossIndex) {
		if (mySubs.get(bossIndex).size() == 0) {
			return 0; 
		}
		ArrayList<Integer> subTimes = new ArrayList<>(); 
		for(int i:mySubs.get(bossIndex)) {
			subTimes.add(minTime(i)); 
		}
		Collections.sort(subTimes, Collections.reverseOrder());
		for (int i=0; i<subTimes.size(); i++) {
			subTimes.set(i, subTimes.get(i)+1+i); 
		}
		Collections.sort(subTimes, Collections.reverseOrder());
		return subTimes.get(0); 
	}
	/*public static void main(String[] args) {
		int[] supervisor = {-1, 0, 1, 2, 3, 3, 3}; 
		SpreadingNews test = new SpreadingNews(); 
		int out = test.minTime(supervisor); 
		System.out.println(out);
	}*/
}
