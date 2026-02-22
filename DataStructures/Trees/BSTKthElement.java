package DataStructures.Trees;

import java.util.*;

/*
===========================================================
BST Kth Smallest / Largest Elements
===========================================================

1) BST Insert (Recursive & Iterative)
2) Inorder Traversal
3) Kth Smallest Element
4) Kth Largest Element
5) Testing Examples
===========================================================
*/

public class BSTKthElement {

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
    // 2️⃣ BST INSERT - ITERATIVE
    // =====================================================
    public static TreeNode insertBSTIterative(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if(root == null) return newNode;

        TreeNode current = root, parent = null;
        while(current != null) {
            parent = current;
            if(val < current.val) current = current.left;
            else if(val > current.val) current = current.right;
            else return root; // ignore duplicates
        }

        if(val < parent.val) parent.left = newNode;
        else parent.right = newNode;

        return root;
    }

    // =====================================================
    // 3️⃣ INORDER TRAVERSAL
    // =====================================================
    public static void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // 4️⃣ KTH SMALLEST ELEMENT
    // =====================================================
    public static int kthSmallest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int count = 0;

        while(!stack.isEmpty() || current != null) {
            while(current != null) {
                stack.push(current);
                current = current.left;
            }

            current = stack.pop();
            count++;
            if(count == k) return current.val;
            current = current.right;
        }

        throw new IllegalArgumentException("k is larger than the number of nodes in BST");
    }

    // =====================================================
    // 5️⃣ KTH LARGEST ELEMENT
    // =====================================================
    public static int kthLargest(TreeNode root, int k) {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode current = root;
        int count = 0;

        while(!stack.isEmpty() || current != null) {
            while(current != null) {
                stack.push(current);
                current = current.right;
            }

            current = stack.pop();
            count++;
            if(count == k) return current.val;
            current = current.left;
        }

        throw new IllegalArgumentException("k is larger than the number of nodes in BST");
    }

    // =====================================================
    // MAIN METHOD (TESTING)
    // =====================================================
    public static void main(String[] args) {
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        TreeNode bst = null;

        for(int val : values) bst = insertBSTRecursive(bst, val);

        System.out.println("BST Inorder Traversal:");
        inorder(bst);
        System.out.println();

        int k1 = 3;
        System.out.println(k1 + "th Smallest: " + kthSmallest(bst, k1));

        int k2 = 2;
        System.out.println(k2 + "th Largest: " + kthLargest(bst, k2));
    }
}

/*
===========================================================
Time & Space Complexity
===========================================================

BST Insert (Recursive / Iterative):
   Time: O(h), Space: O(h) recursion stack or O(1) for iterative
   h = height of BST

Kth Smallest / Largest (Iterative Inorder):
   Time: O(h + k), Space: O(h) for stack
   - In worst-case (unbalanced BST) h = n
   - For balanced BST h = log n

===========================================================
*/