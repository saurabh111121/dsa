package DataStructures.Trees;

import java.util.*;

/*
===========================================================
BST Validation - Check if a tree is a valid BST
===========================================================

1) BST Insert (Recursive)
2) Recursive BST Validation
3) Iterative BST Validation (Inorder Traversal)
4) Inorder Traversal (for verification)
5) Testing Examples

===========================================================
*/

public class BSTValidation {

    // =========================
    // Tree Node Definition
    // =========================
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) { this.val = val; }
    }

    // =====================================================
    // 1️⃣ BST INSERT - RECURSIVE
    // =====================================================
    public static TreeNode insertBSTRecursive(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if(val < root.val) root.left = insertBSTRecursive(root.left, val);
        else if(val > root.val) root.right = insertBSTRecursive(root.right, val);
        return root;
    }

    // =====================================================
    // 2️⃣ INORDER TRAVERSAL
    // =====================================================
    public static void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // 3️⃣ BST VALIDATION - RECURSIVE
    // =====================================================
    public static boolean isValidBSTRecursive(TreeNode root) {
        return validate(root, null, null);
    }

    private static boolean validate(TreeNode node, Integer min, Integer max) {
        if(node == null) return true;

        if((min != null && node.val <= min) || (max != null && node.val >= max)) return false;

        return validate(node.left, min, node.val) && validate(node.right, node.val, max);
    }

    // =====================================================
    // 4️⃣ BST VALIDATION - ITERATIVE (INORDER)
    // =====================================================
    public static boolean isValidBSTIterative(TreeNode root) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        Integer prev = null;

        while(!stack.isEmpty() || current != null) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            if(prev != null && current.val <= prev) return false;
            prev = current.val;
            current = current.right;
        }

        return true;
    }

    // =====================================================
    // MAIN METHOD (TESTING)
    // =====================================================
    public static void main(String[] args) {
        // Create a valid BST
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        TreeNode bst = null;
        for(int val : values) bst = insertBSTRecursive(bst, val);

        System.out.println("Inorder Traversal of BST:");
        inorder(bst);
        System.out.println();

        System.out.println("BST Recursive Validation: " + isValidBSTRecursive(bst));
        System.out.println("BST Iterative Validation: " + isValidBSTIterative(bst));

        // Create an invalid BST manually
        TreeNode invalid = new TreeNode(10);
        invalid.left = new TreeNode(15);  // invalid
        invalid.right = new TreeNode(5);  // invalid

        System.out.println("\nInorder Traversal of Invalid BST:");
        inorder(invalid);
        System.out.println();

        System.out.println("Invalid BST Recursive Validation: " + isValidBSTRecursive(invalid));
        System.out.println("Invalid BST Iterative Validation: " + isValidBSTIterative(invalid));
    }
}

/*
===========================================================
Time & Space Complexity
===========================================================

BST Insert (Recursive):
   Time: O(h), Space: O(h) recursion stack
   h = height of BST

BST Validation Recursive:
   Time: O(n)
   Space: O(h) recursion stack

BST Validation Iterative (Inorder):
   Time: O(n)
   Space: O(h) for stack

===========================================================
*/