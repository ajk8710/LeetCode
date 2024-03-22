package trees.easy;

import java.util.ArrayDeque;
import java.util.ArrayList;

import trees.TreeNode;

// Given the roots of two binary trees: root and subRoot (they are not null nodes),
// return true if there is a subtree of root with the same structure and node values of subRoot.

// A subtree of a binary tree is a tree that consists of a node in tree and all of this node's descendants,
// & non of its non-descendants.
public class LC572SubtreeOfAnotherTree {
    
    // For each root-tree's node, compare if it's same tree as subRoot-tree. Return true if same tree found.
    // This works because a same tree but having extra descendant is not considered as a sub-tree.
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        
        // Create a list of every nodes of root-tree.
        ArrayList<TreeNode> allNodes = new ArrayList<>();
        
        // Using BFS to traverse tree.
        ArrayDeque<TreeNode> que = new ArrayDeque<>();
        que.addLast(root);
        while (!que.isEmpty()) {
            TreeNode node = que.pollFirst();
            allNodes.add(node);
            if (node.left != null) {
                que.addLast(node.left);
            }
            if (node.right != null) {
                que.addLast(node.right);
            }
        }
        
        // Return true if same tree found.
        for (TreeNode node : allNodes) {
            if (isSameTree(node, subRoot)) {
                return true;
            }
        }
        return false;
    }
    
    
    // isSameTree method.
    // Compare values. Continue to traverse & compare values of left & right until they reach to null.
    // Return true if both nodes reach to null (compared to leaf nodes).
    // If at any point values are different, return false.
    public static boolean isSameTree(TreeNode p, TreeNode q) {
        if (p == null && q == null) {  // If both are null, return true.
            return true;
        }
        
        if (p == null && q != null) {  // If one of them is null and the other is not, return false.
            return false;
        }
        else if (p != null && q == null) {
            return false;
        }
        else if (p.val != q.val) {  // At this point, both are not null. Compare values.
            return false;
        }
        
        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }
    
}
