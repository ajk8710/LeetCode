package trees.easy;

import java.util.ArrayDeque;

import trees.TreeNode;

// Given the root of a binary tree, return its maximum depth.
public class LC104MaxDepthOfBinaryTree {
    
    // Recursive DFS.
    // Go until node is null. Return 0 if null.
    // When returned back, get max of left & right, add 1 then return.
    public int maxDepthDFS(TreeNode root) {
        if (root == null) {
            return 0;  // can be -1 if you want to count from depth 0.
        }
        
        return Math.max(maxDepthDFS(root.left), maxDepthDFS(root.right)) + 1;
    }
    
    
    // Iterative BFS (Level Order)
    // Usual BFS: Add root to queue. While queue is not empty, poll first node, add its non-null children to que.
    // Level by Level BFS: Add root to queue. Capture the current size of queue. Work for the captured size (nodes in the Level).
    //     For captured size (for-loop), poll first node, add its non-null children to que.
    //     Increment depth after for-loop.
    // Continue while queue is not empty (Recapture the current queue size).
    public int maxDepthBFS(TreeNode root) {
        if (root == null) {  // Case if given root is null
            return 0;
        }
        
        int maxDepth = 0;  // can be -1 if you want to count from depth 0.
        
        ArrayDeque<TreeNode> que = new ArrayDeque<>();
        que.addLast(root);
        
        while (!que.isEmpty()) {
            
            // Having que.size() within for-loop produces wrong answer because size changes after polling.
            int size = que.size();  // Capture the current size (Number of nodes in the level).
            
            for (int i = 0; i < size; i++) {  // Level by Level Approach: Nodes within For-Loop are in a level.
                TreeNode node = que.pollFirst();
                
                if (node.left != null) {
                    que.addLast(node.left);
                }
                if (node.right != null) {
                    que.addLast(node.right);
                }
            }
            
            maxDepth += 1;  // Once a level is cleared, increment maxDepth.
        }    
        
        return maxDepth;
    }
}
