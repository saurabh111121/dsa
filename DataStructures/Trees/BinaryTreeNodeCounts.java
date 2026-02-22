package DataStructures.Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
===========================================================
Binary Tree Node Counting Implementations
===========================================================

1) Count Total Nodes
   - Recursive
   - Iterative (Level Order)

2) Count Leaf Nodes
   - Recursive
   - Iterative (Level Order)

3) Count Non-Leaf (Internal) Nodes
   - Recursive
   - Iterative (Level Order)

===========================================================
*/

public class BinaryTreeNodeCounts {

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
    // 1️⃣ COUNT TOTAL NODES - RECURSIVE
    // =====================================================
    public static int countNodesRecursive(TreeNode root) {
        if (root == null) return 0;
        return 1 + countNodesRecursive(root.left) + countNodesRecursive(root.right);
    }

    // =====================================================
    // 2️⃣ COUNT TOTAL NODES - ITERATIVE (LEVEL ORDER)
    // =====================================================
    public static int countNodesIterative(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            count++;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return count;
    }

    // =====================================================
    // 3️⃣ COUNT LEAF NODES - RECURSIVE
    // =====================================================
    public static int countLeafNodesRecursive(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null && root.right == null) return 1;
        return countLeafNodesRecursive(root.left) + countLeafNodesRecursive(root.right);
    }

    // =====================================================
    // 4️⃣ COUNT LEAF NODES - ITERATIVE
    // =====================================================
    public static int countLeafNodesIterative(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left == null && node.right == null) count++;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return count;
    }

    // =====================================================
    // 5️⃣ COUNT NON-LEAF / INTERNAL NODES - RECURSIVE
    // =====================================================
    public static int countNonLeafNodesRecursive(TreeNode root) {
        if (root == null || (root.left == null && root.right == null)) return 0;
        return 1 + countNonLeafNodesRecursive(root.left) + countNonLeafNodesRecursive(root.right);
    }

    // =====================================================
    // 6️⃣ COUNT NON-LEAF / INTERNAL NODES - ITERATIVE
    // =====================================================
    public static int countNonLeafNodesIterative(TreeNode root) {
        if (root == null) return 0;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int count = 0;

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node.left != null || node.right != null) count++;
            if (node.left != null) queue.offer(node.left);
            if (node.right != null) queue.offer(node.right);
        }

        return count;
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
    // MAIN METHOD (TESTING ALL COUNTS)
    // =====================================================
    public static void main(String[] args) {
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

        System.out.println("Total Nodes Recursive: " + countNodesRecursive(root));
        System.out.println("Total Nodes Iterative: " + countNodesIterative(root));

        System.out.println("Leaf Nodes Recursive: " + countLeafNodesRecursive(root));
        System.out.println("Leaf Nodes Iterative: " + countLeafNodesIterative(root));

        System.out.println("Non-Leaf Nodes Recursive: " + countNonLeafNodesRecursive(root));
        System.out.println("Non-Leaf Nodes Iterative: " + countNonLeafNodesIterative(root));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Total Nodes:
   - Recursive:
       Time:  O(n)
       Space: O(h) recursion stack
   - Iterative:
       Time:  O(n)
       Space: O(n) (queue)

2) Leaf Nodes:
   - Recursive:
       Time:  O(n)
       Space: O(h)
   - Iterative:
       Time:  O(n)
       Space: O(n)

3) Non-Leaf Nodes:
   - Recursive:
       Time:  O(n)
       Space: O(h)
   - Iterative:
       Time:  O(n)
       Space: O(n)
===========================================================
*/