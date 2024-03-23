package linkedList.easy;

import linkedList.ListNode;

public class LC206ReverseLinkedList {
    
    public static void main(String[] args) {
        ListNode head = new ListNode(0, new ListNode(1, new ListNode(2)));
        head = reverseList(head);
        
        ListNode curr = head;
        while (curr != null) {
            System.out.println(curr.val);
            curr = curr.next;
        }
    }
    
    // Set prev = null & curr = head.
    // Save curr.next before reversing the link.
    // Reverse the link. Advance prev & curr pointers.
    // Stop if curr is null. Return prev as new head.
    public static ListNode reverseList(ListNode head) {
        ListNode prev = null;
        ListNode curr = head;
        
        while (curr != null) {
            ListNode temp = curr.next;
            curr.next = prev;
            
            prev = curr;
            curr = temp;
            
            /*
            curr = temp;
            prev = curr;  // curr changed at above line. Advance prev first, so not to skip what was curr.
            */

        }
        
        return prev;
    }
}
