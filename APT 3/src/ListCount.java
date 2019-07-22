
public class ListCount {
	public int count (ListNode list) {
		int count = 0; 
		while(list != null) {
			count ++; 
			list = list.next; 
		}
			return count;
	}
}
