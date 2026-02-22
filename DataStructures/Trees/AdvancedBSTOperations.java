package DataStructures.Trees;

import java.util.*;

/*
===========================================================
Advanced BST Operations - Successor/Predecessor, Floor/Ceiling, Range Queries
===========================================================

1) Find Inorder Successor
2) Find Inorder Predecessor
3) Find Floor and Ceiling
4) Range Query (all nodes in range)
5) Testing Example BST

===========================================================
*/

public class AdvancedBSTOperations {

    // =========================
    // Tree Node Definition
    // =========================
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) { this.val = val; }
    }

    // =====================================================
    // INSERT BST (RECURSIVE)
    // =====================================================
    public static TreeNode insertBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.val) root.left = insertBST(root.left, val);
        else if (val > root.val) root.right = insertBST(root.right, val);

        return root;
    }

    // =====================================================
    // INORDER (for testing)
    // =====================================================
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // 1️⃣ INORDER SUCCESSOR
    // =====================================================
    public static TreeNode inorderSuccessor(TreeNode root, int key) {
        TreeNode succ = null;
        while (root != null) {
            if (key < root.val) {
                succ = root;
                root = root.left;
            } else {
                root = root.right;
            }
        }
        return succ;
    }

    // =====================================================
    // 2️⃣ INORDER PREDECESSOR
    // =====================================================
    public static TreeNode inorderPredecessor(TreeNode root, int key) {
        TreeNode pred = null;
        while (root != null) {
            if (key > root.val) {
                pred = root;
                root = root.right;
            } else {
                root = root.left;
            }
        }
        return pred;
    }

    // =====================================================
    // 3️⃣ FLOOR (largest value ≤ key)
    // =====================================================
    public static TreeNode floor(TreeNode root, int key) {
        TreeNode floor = null;
        while (root != null) {
            if (root.val == key) return root;
            if (root.val > key) root = root.left;
            else {
                floor = root;
                root = root.right;
            }
        }
        return floor;
    }

    // =====================================================
    // 4️⃣ CEILING (smallest value ≥ key)
    // =====================================================
    public static TreeNode ceiling(TreeNode root, int key) {
        TreeNode ceil = null;
        while (root != null) {
            if (root.val == key) return root;
            if (root.val < key) root = root.right;
            else {
                ceil = root;
                root = root.left;
            }
        }
        return ceil;
    }

    // =====================================================
    // 5️⃣ RANGE QUERY (all nodes in [low, high])
    // =====================================================
    public static List<Integer> rangeQuery(TreeNode root, int low, int high) {
        List<Integer> result = new ArrayList<>();
        rangeQueryHelper(root, low, high, result);
        return result;
    }

    private static void rangeQueryHelper(TreeNode node, int low, int high, List<Integer> result) {
        if (node == null) return;

        if (node.val > low) rangeQueryHelper(node.left, low, high, result);
        if (node.val >= low && node.val <= high) result.add(node.val);
        if (node.val < high) rangeQueryHelper(node.right, low, high, result);
    }

    // =====================================================
    // MAIN METHOD (TESTING ADVANCED BST OPERATIONS)
    // =====================================================
    public static void main(String[] args) {
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        TreeNode bst = null;
        for (int val : values) bst = insertBST(bst, val);

        System.out.println("BST Inorder:");
        inorder(bst);
        System.out.println("\n");

        int key = 15;
        TreeNode succ = inorderSuccessor(bst, key);
        TreeNode pred = inorderPredecessor(bst, key);
        System.out.println("Key: " + key);
        System.out.println("Inorder Successor: " + (succ != null ? succ.val : "null"));
        System.out.println("Inorder Predecessor: " + (pred != null ? pred.val : "null"));

        key = 17;
        TreeNode f = floor(bst, key);
        TreeNode c = ceiling(bst, key);
        System.out.println("Floor of " + key + ": " + (f != null ? f.val : "null"));
        System.out.println("Ceiling of " + key + ": " + (c != null ? c.val : "null"));

        int low = 10, high = 30;
        List<Integer> range = rangeQuery(bst, low, high);
        System.out.println("Range Query [" + low + ", " + high + "]: " + range);
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Inorder Successor / Predecessor:
   - Time:  O(h)  (h = height of BST)
   - Space: O(1)

2) Floor / Ceiling:
   - Time:  O(h)
   - Space: O(1)

3) Range Query:
   - Time:  O(n) worst case, O(k + h) average (k = #nodes in range)
   - Space: O(k + h) recursion stack

===========================================================
*/