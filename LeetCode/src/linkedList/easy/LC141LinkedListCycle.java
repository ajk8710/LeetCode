package linkedList.easy;

import java.util.HashSet;

import linkedList.ListNode;

// Given the head of a linked list, determine if it has a cycle. Return true if there is a cycle.
public class LC141LinkedListCycle {
    
    // Method using HashSet.
    // Go through the nodes, marking each node visited.
    // Mark by adding it to the visited list.
    // When going through the the nodes, check if the node is already in the visited list.
    // Worst case N^2 complexity using a list. Near N complexity if using a set.
    public boolean hasCycle(ListNode head) {
        
        // ArrayList<ListNode> visitedNodes = new ArrayList<>();  // visited nodes
        HashSet<ListNode> visitedNodes = new HashSet<>();

        ListNode curr = head;  // curr pointer to advance
        while (curr != null) {  // Advance till end
            /*
            for (ListNode node : visitedNodes) {
                if (curr == node) {  // If current node is visited node (points to same node as visited node), return true.
                    return true;
                }
            }
            */
            if (visitedNodes.contains(curr)) {  // If current node is visited node (points to same node as visited node), return true.
                return true;
            }

            visitedNodes.add(curr);  // Add current node to visited.
            curr = curr.next;
        }

        return false;
    }
    
    
    // Method using no extra memory.
    // Solving by fast & slow pointer technique (Floyd's Tortoise and Hare).
    // Starting at head, advance fast pointer by 2. Advance slow pointer by 1.
    // If there is a cycle, the gap for fast (ahead of slow) to catch back slow is n at max.
    // Each time, gap reduces by 1. (Slow advances by 1. Fast advances 2 to catch the behind of slow).
    // Therefore the worst time complexity is N. No extra memory used, so constant memory complexity.
    public boolean hasCycleNoExtraMomory(ListNode head) {
        ListNode fast = head, slow = head;
        
        while (fast != null && fast.next != null) {  // Linked list with zero or a single node do not have a cycle.
            fast = fast.next.next;                   // Also, nodes will never reach to null if there is a cycle. (Reaching to null means no cycle)
            slow = slow.next;
            if (fast == slow) {
                return true;
            }
        }
        
        return false;
    }
    
}
