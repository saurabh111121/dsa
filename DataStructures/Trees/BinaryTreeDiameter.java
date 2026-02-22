package DataStructures.Trees;


/*
===========================================================
Binary Tree Diameter Calculation Implementations
===========================================================

1) Diameter - Recursive (Naive)
2) Diameter - Optimized Recursive (Height + Diameter)
3) Testing with example tree

===========================================================
*/

public class BinaryTreeDiameter {

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
    // 1️⃣ DIAMETER - RECURSIVE (NAIVE)
    // =====================================================
    // Diameter = max distance between any two nodes
    public static int diameterRecursive(TreeNode root) {
        if (root == null) return 0;

        int leftHeight = height(root.left);
        int rightHeight = height(root.right);
        int rootDiameter = leftHeight + rightHeight + 1;

        int leftDiameter = diameterRecursive(root.left);
        int rightDiameter = diameterRecursive(root.right);

        return Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
    }

    private static int height(TreeNode root) {
        if (root == null) return 0;
        return Math.max(height(root.left), height(root.right)) + 1;
    }

    // =====================================================
    // 2️⃣ DIAMETER - OPTIMIZED RECURSIVE
    // =====================================================
    static class Height {
        int h;
    }

    public static int diameterOptimized(TreeNode root) {
        return diameterOptHelper(root, new Height());
    }

    private static int diameterOptHelper(TreeNode root, Height height) {
        if (root == null) {
            height.h = 0;
            return 0;
        }

        Height leftHeight = new Height();
        Height rightHeight = new Height();

        int leftDiameter = diameterOptHelper(root.left, leftHeight);
        int rightDiameter = diameterOptHelper(root.right, rightHeight);

        height.h = Math.max(leftHeight.h, rightHeight.h) + 1;

        int rootDiameter = leftHeight.h + rightHeight.h + 1;

        return Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
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
    // MAIN METHOD (TESTING DIAMETER)
    // =====================================================
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.right = new TreeNode(6);
        root.left.left.left = new TreeNode(7);

        System.out.println("Inorder Traversal:");
        inorder(root);
        System.out.println("\n");

        System.out.println("Diameter (Naive Recursive): " + diameterRecursive(root));
        System.out.println("Diameter (Optimized Recursive): " + diameterOptimized(root));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Diameter - Naive Recursive:
   - Time:  O(n^2)  (computes height at each node)
   - Space: O(h) recursion stack

2) Diameter - Optimized Recursive:
   - Time:  O(n)    (height computed alongside diameter)
   - Space: O(h) recursion stack
===========================================================
*/