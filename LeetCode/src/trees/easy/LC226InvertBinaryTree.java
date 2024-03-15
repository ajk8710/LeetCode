package trees.easy;

import java.util.ArrayDeque;

import trees.TreeNode;

// Given the root of a binary tree, invert the tree, and return its root. (Invert left & right of every node.)
public class LC226InvertBinaryTree {
    
    /* 
    Recursive Solution: DFS (pre-order). Swap my left & right. Do the same for my left then my right. Return original root.
    
    Iterative Solution: BFS (Level Order Traversal) using queue.
    Add root to queue. While queue is not empty, poll first node, add its non-null children to que. Swap node's left & right.
    Continue until queue becomes empty. Return original root.
    */
    
    
    // Recursive Solution. Depth First Search (In-Order, Pre-Order, Post-Order). This one is Pre-Order: me, left, right.
    // DFS: Go deep into the end (null node), return from end. May use return value or not. (In this case, not.)
    // ex. left, left, left, return to immediate parent (a step back), go it's right, repeat.
    public TreeNode invertTreeDFS(TreeNode root) {
        if (root == null) {  // If null node, do not proceed following but return.
            return null;
        }
        
        TreeNode left = root.left;  // Swap my left & right.
        root.left = root.right;
        root.right = left;
        
        invertTreeDFS(root.left);  // Do the same for left & right nodes.
        invertTreeDFS(root.right);  // They eventually return null, but no need to use return values.
        
        return root;  // Return original root.
    }
    
    
    // Iterative Solution. Breadth First Search (Level Order).
    // Solve from immediate neighbors (left & right). Then go deeper into grand children.
    public TreeNode invertTreeBFS(TreeNode root) {
        if (root == null) {
            return null;
        }
        
        ArrayDeque<TreeNode> que = new ArrayDeque<>();  // que for nodes to be added in level order.
        que.addLast(root);
        
        while (!que.isEmpty()) {
            TreeNode node = que.pollFirst();  // get me
            
            if (node.left != null) {  // append my left & right to queue if they are not null nodes
                que.addLast(node.left);
            }
            if (node.right != null) {
                que.addLast(node.right);
            }
            
            TreeNode left = node.left;  // Swap my left & right.
            node.left = node.right;
            node.right = left;
        }
        
        return root;
    }
}
