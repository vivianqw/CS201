
public class VoteRigging {
	 public int minimumVotes(int[] votes) { 
		 int buy = 0; 
		 int maxVote = 0; 
		 int maxIndex = 0; 
		 for (int i=0; i<votes.length; i++) {
			 if (votes[i] >= maxVote) {
				 maxVote = votes[i]; 
				 maxIndex = i; 
			 }
		 }
		 if (maxIndex == 0) {
			 return 0; 
		 }
		 buy ++; 
		 votes[maxIndex] --; 
		 votes[0] ++; 
		 buy = buy + minimumVotes(votes); 
		 return buy;  
     }
}
