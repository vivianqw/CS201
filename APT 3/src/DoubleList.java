
public class DoubleList {
    public ListNode bigify(ListNode list) {
        ListNode first = list; 
        ListNode last = first; 
        while(last != null) {
        	last.next = new ListNode(last.info, last.next); 
        	last = last.next.next; 
        }
        return first;
    }

}
