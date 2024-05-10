package linkedList.medium;

import java.util.ArrayDeque;

import linkedList.ListNode;

// You are given the head of a singly linked-list. The list can be represented as:
// L0 > L1 > ... > Ln-1 > Ln
// Reorder the list to be on the following form:
// L0 > Ln > L1 > Ln-1 > L2 > Ln-2 → ...
// You may not modify the values in the list's nodes. Only nodes themselves may be changed.
// It's a void function: Make sure to modify the list in place, not returning a modified list.
public class LC143ReorderList {
    
    // Use deque that can poll from both ends. Continue until deque is empty.
    // Poll first then let last.next = first. (Prepare a dummy node 'last' beforehand.)
    // Poll last then let first.next = last.
    // Make sure to not create circular list: When ending with last, let last.next = null.
    //                                        When ending with first, let first.next = null.
    public void reorderList(ListNode head) {
        
        // Add nodes to the deq.
        ArrayDeque<ListNode> deq = new ArrayDeque<>();
        ListNode curr = head;
        while (curr != null) {
            deq.addLast(curr);
            curr = curr.next;
        }
        
        ListNode last = new ListNode();  // 'last' starts from dummy node: Dummy node will point head. (Dangling dummy node)
        // ListNode ptr = last;  // Can have a pointer to remove dangling dummy node at the end by setting ptr.next = null.
        
        while (!deq.isEmpty()) {  // Poll nodes front then back until empty. Continue to set last.next=first then first.next=last.
            ListNode first = deq.pollFirst();
            last.next = first;  // Let last point to first.
            
            if (!deq.isEmpty()) {
                last = deq.pollLast();
                first.next = last;  // Let first to point last.

                if (deq.isEmpty()) {  // If loop will end here, make sure to remove cycle.
                    last.next = null;  // Remove cycle when the list ends with 'last' node.
                }
            }
            else {  // If loop will end here, make sure to remove cycle.
                first.next = null;  // Remove cycle when the list ends with 'first' node.
            }
        }
    }
    
}
