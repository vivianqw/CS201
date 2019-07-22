import java.util.*;

public class FriendScore { 
	public int highestScore(String[] friends) {
		TreeSet<Integer> friend = new TreeSet<>(); 
		int count = 0; 
		for (int i=0; i<friends.length; i++) {
			for (int j=0; j<friends[i].length(); j++) {
				if (friends[i].charAt(j) == 'Y') {
					friend.add(j); 
					for (int k=0; k<friends[j].length(); k++) {
						if (friends[j].charAt(k) == 'Y' && (i != k)) {
							friend.add(k); 
						}
					}
				}
			}
			if (friend.size() > count) {
				count = friend.size(); 
			}
			friend.clear();
		}
		return count; 
	}
}