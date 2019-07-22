
public class ListLastFirst {
    public ListNode move(ListNode list) {
        ListNode first = list; 
        ListNode last = first; 
        if (first == null || first.next == null) {
        	return list; 
        }
        while (last.next.next != null) {
        	last = last.next; 
        }
        ListNode nowFirst = last.next; 
        last.next = null; 
        nowFirst.next = first; 
        return nowFirst; 
}
}
