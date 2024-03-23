package linkedList.easy;

import linkedList.ListNode;

public class LC21MergeTwoSortedLists {
    
    // Create dummy node. Will return dummy.next as new head.
    // Create curr node to advance, pointing to dummy. (Advancing dummy will loose original dummy)
    // Compare list1 & list2, curr.next = smaller. Advance pointers.
    // Edge case: curr.next = leftover from list1 or list2
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode dummy = new ListNode();
        ListNode curr = dummy;  // curr pointer to advance
        
        while (list1 != null && list2 != null) {  // until one of two lists gets to end
            if (list1.val <= list2.val) {
                curr.next = list1;
                list1 = list1.next;
            }
            else {
                curr.next = list2;
                list2 = list2.next;
            }
            
            curr = curr.next;
        }
        
        if (list1 != null) {  // leftover from one of lists
            curr.next = list1;
        }
        
        if (list2 != null) {
            curr.next = list2;
        }
        
        return dummy.next;
    }
}
