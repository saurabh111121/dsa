package DataStructures.Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
===========================================================
Binary Tree Height / Depth Calculation Implementations
===========================================================

1) Recursive Height Calculation
2) Iterative Height Calculation (Level Order)
3) Node Depth Calculation (Distance from root)
4) Max Depth / Min Depth

===========================================================
*/

public class BinaryTreeHeightDepth {

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
    // 1️⃣ HEIGHT OF TREE - RECURSIVE
    // =====================================================
    public static int heightRecursive(TreeNode root) {
        if (root == null) return 0;
        int leftHeight = heightRecursive(root.left);
        int rightHeight = heightRecursive(root.right);
        return Math.max(leftHeight, rightHeight) + 1;
    }

    // =====================================================
    // 2️⃣ HEIGHT OF TREE - ITERATIVE (LEVEL ORDER)
    // =====================================================
    public static int heightIterative(TreeNode root) {
        if (root == null) return 0;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        int height = 0;

        while (!queue.isEmpty()) {
            int levelSize = queue.size();
            height++;

            for (int i = 0; i < levelSize; i++) {
                TreeNode node = queue.poll();
                if (node.left != null) queue.offer(node.left);
                if (node.right != null) queue.offer(node.right);
            }
        }
        return height;
    }

    // =====================================================
    // 3️⃣ NODE DEPTH (DISTANCE FROM ROOT)
    // =====================================================
    public static int nodeDepth(TreeNode root, int val, int depth) {
        if (root == null) return -1;
        if (root.val == val) return depth;

        int left = nodeDepth(root.left, val, depth + 1);
        if (left != -1) return left;

        return nodeDepth(root.right, val, depth + 1);
    }

    // =====================================================
    // 4️⃣ MAX DEPTH / MIN DEPTH
    // =====================================================
    public static int maxDepth(TreeNode root) {
        return heightRecursive(root); // same as recursive height
    }

    public static int minDepth(TreeNode root) {
        if (root == null) return 0;
        if (root.left == null) return minDepth(root.right) + 1;
        if (root.right == null) return minDepth(root.left) + 1;
        return Math.min(minDepth(root.left), minDepth(root.right)) + 1;
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
    // MAIN METHOD (TESTING HEIGHT / DEPTH)
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

        System.out.println("Height Recursive: " + heightRecursive(root));
        System.out.println("Height Iterative: " + heightIterative(root));

        System.out.println("Depth of Node 5: " + nodeDepth(root, 5, 1));
        System.out.println("Depth of Node 7: " + nodeDepth(root, 7, 1));

        System.out.println("Max Depth: " + maxDepth(root));
        System.out.println("Min Depth: " + minDepth(root));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Recursive Height / Max Depth:
   - Time:  O(n)
   - Space: O(h) recursion stack

2) Iterative Height (Level Order):
   - Time:  O(n)
   - Space: O(n) (queue)

3) Node Depth:
   - Time:  O(n)
   - Space: O(h) recursion stack

4) Min Depth:
   - Time:  O(n)
   - Space: O(h) recursion stack
===========================================================
*/