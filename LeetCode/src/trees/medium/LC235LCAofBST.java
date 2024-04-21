package trees.medium;

import trees.TreeNode;

// Lowest Common Ancestor of a Binary Search Tree.
// Given a binary search tree (BST), find the lowest common ancestor (LCA) node of two given nodes in the BST.
// The number of nodes in the tree >= 2.
// All node.val are unique.
// p and q exists in the BST and p != q.
public class LC235LCAofBST {
    
    // Use the fact that it is a binary search tree.
    // Traverse down the tree. Where they first split, or never split before but the node is equal to one of them, then the node is the LCA.
    public TreeNode lowestCommonAncestorRecursion(TreeNode root, TreeNode p, TreeNode q) {
        
        if (p.val < root.val && root.val < q.val) {  // If p & q split here, then this node is the LCA.
            return root;
        }
        else if (q.val < root.val && root.val < p.val) {  // If p & q split here, then this node is the LCA.
            return root;
        }
        else if (p.val < root.val && q.val < root.val) {  // If p & q are both smaller, then go left.
            return lowestCommonAncestorRecursion(root.left, p, q);
        }
        else if (p.val > root.val && q.val > root.val) {  // If p & q are both larger, then go right.
            return lowestCommonAncestorRecursion(root.right, p, q);
        }
        else if (p.val == root.val) {  // If all above are not true, but p == root, then return p.
            return p;
        }
        else {  // q.val == root.val  // If all above are not true, but q == root, then return q.
            return q;
        }
    }
    
    // Use the fact that it is a binary search tree.
    // Above method can be refined as this method.
    // If p & q are not both smaller or not both larger then the node,
    // then they either split or one of them are equal to the node. Then this node is LCA.
    // Time: O(logn), Memory: O(1)
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        while (root != null) {  // Traverse tree until p & q split or one of them are equal to the node.
            if (p.val < root.val && q.val < root.val) {  // If p & q are both smaller, go left.
                root = root.left;
            }
            else if (p.val > root.val && q.val > root.val) {  // If p & q are both larger, go right.
                root = root.right;
            }
            else {  // Note that this else statement only executes if above two conditions are not met.
                return root;
            }
        }
        return null;  // never returns null since the answer (p & q) exists for the problem.
    }
    
}
