package DataStructures.Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
===========================================================
Binary Tree Property Checks Implementations
===========================================================

1) Check if Balanced Tree (Height Balanced)
2) Check if Complete Binary Tree
3) Check if Full Binary Tree
4) Check if Perfect Binary Tree

===========================================================
*/

public class BinaryTreeProperties {

    // =========================
    // Tree Node Definition
    // =========================
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // =====================================================
    // 1️⃣ CHECK BALANCED TREE (HEIGHT BALANCED)
    // =====================================================
    // A tree is balanced if for every node, height(left) - height(right) <= 1
    static class Height {
        int h;
    }

    public static boolean isBalanced(TreeNode root) {
        return checkBalanced(root, new Height()) != -1;
    }

    private static int checkBalanced(TreeNode root, Height height) {
        if (root == null) {
            height.h = 0;
            return 0;
        }

        Height leftHeight = new Height();
        Height rightHeight = new Height();

        int left = checkBalanced(root.left, leftHeight);
        int right = checkBalanced(root.right, rightHeight);

        height.h = Math.max(leftHeight.h, rightHeight.h) + 1;

        if (left == -1 || right == -1) return -1;
        if (Math.abs(leftHeight.h - rightHeight.h) > 1) return -1;

        return 1; // balanced
    }

    // =====================================================
    // 2️⃣ CHECK COMPLETE BINARY TREE
    // =====================================================
    public static boolean isComplete(TreeNode root) {
        if (root == null) return true;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        boolean end = false;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                end = true;
            } else {
                if (end) return false;
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        return true;
    }

    // =====================================================
    // 3️⃣ CHECK FULL BINARY TREE
    // =====================================================
    // Every node has either 0 or 2 children
    public static boolean isFull(TreeNode root) {
        if (root == null) return true;
        if ((root.left == null && root.right != null) || (root.left != null && root.right == null))
            return false;

        return isFull(root.left) && isFull(root.right);
    }

    // =====================================================
    // 4️⃣ CHECK PERFECT BINARY TREE
    // =====================================================
    // Full + all leaves at same level
    public static boolean isPerfect(TreeNode root) {
        int d = findDepth(root);
        return isPerfectUtil(root, d, 0);
    }

    private static int findDepth(TreeNode node) {
        int depth = 0;
        while (node != null) {
            depth++;
            node = node.left;
        }
        return depth;
    }

    private static boolean isPerfectUtil(TreeNode root, int depth, int level) {
        if (root == null) return true;
        if (root.left == null && root.right == null) return (depth == level + 1);
        if (root.left == null || root.right == null) return false;
        return isPerfectUtil(root.left, depth, level + 1) &&
               isPerfectUtil(root.right, depth, level + 1);
    }

    // =====================================================
    // INORDER (for testing tree structure)
    // =====================================================
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // MAIN METHOD (TESTING TREE PROPERTIES)
    // =====================================================
    public static void main(String[] args) {
        // Example tree
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Inorder Traversal:");
        inorder(root);
        System.out.println("\n");

        System.out.println("Is Balanced: " + isBalanced(root));
        System.out.println("Is Complete: " + isComplete(root));
        System.out.println("Is Full: " + isFull(root));
        System.out.println("Is Perfect: " + isPerfect(root));

        // Example unbalanced tree
        TreeNode unbalanced = new TreeNode(1);
        unbalanced.left = new TreeNode(2);
        unbalanced.left.left = new TreeNode(3);

        System.out.println("\nUnbalanced Tree Properties:");
        System.out.println("Is Balanced: " + isBalanced(unbalanced));
        System.out.println("Is Complete: " + isComplete(unbalanced));
        System.out.println("Is Full: " + isFull(unbalanced));
        System.out.println("Is Perfect: " + isPerfect(unbalanced));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Check Balanced:
   - Time:  O(n)
   - Space: O(h) recursion stack

2) Check Complete:
   - Time:  O(n)
   - Space: O(n) (queue)

3) Check Full:
   - Time:  O(n)
   - Space: O(h) recursion stack

4) Check Perfect:
   - Time:  O(n)
   - Space: O(h) recursion stack
===========================================================
*/