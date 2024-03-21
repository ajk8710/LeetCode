package trees.easy;

import trees.TreeNode;

// Given the roots of two binary trees p and q, write a function to check if they are the same or not.
public class LC100SameTree {
    
    // Compare values. Continue to traverse & compare values of left & right until they reach to null.
    // Return true if both nodes reach to null (compared to leaf nodes).
    // If at any point values are different, return false.
    public boolean isSameTree(TreeNode p, TreeNode q) {
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
