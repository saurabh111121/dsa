package DataStructures.Trees;

import java.util.ArrayList;
import java.util.List;

/*
===========================================================
Binary Tree Path-related Operations
===========================================================

1) Print All Root-to-Leaf Paths
2) Check Path Sum Exists (Root-to-Leaf)
3) Return All Root-to-Leaf Paths as List
4) Maximum Path Sum (Any Path in Tree)

===========================================================
*/

public class BinaryTreePaths {

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
    // 1️⃣ PRINT ALL ROOT-TO-LEAF PATHS
    // =====================================================
    public static void printRootToLeaf(TreeNode root) {
        List<Integer> path = new ArrayList<>();
        printPathsHelper(root, path);
    }

    private static void printPathsHelper(TreeNode node, List<Integer> path) {
        if(node == null) return;

        path.add(node.val);

        if(node.left == null && node.right == null) {
            System.out.println(path);
        } else {
            printPathsHelper(node.left, path);
            printPathsHelper(node.right, path);
        }

        path.remove(path.size() - 1); // backtrack
    }

    // =====================================================
    // 2️⃣ CHECK IF PATH SUM EXISTS (ROOT TO LEAF)
    // =====================================================
    public static boolean hasPathSum(TreeNode root, int sum) {
        if(root == null) return false;

        if(root.left == null && root.right == null) return root.val == sum;

        return hasPathSum(root.left, sum - root.val) || hasPathSum(root.right, sum - root.val);
    }

    // =====================================================
    // 3️⃣ RETURN ALL ROOT-TO-LEAF PATHS AS LIST
    // =====================================================
    public static List<List<Integer>> allRootToLeafPaths(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        allPathsHelper(root, new ArrayList<>(), result);
        return result;
    }

    private static void allPathsHelper(TreeNode node, List<Integer> path, List<List<Integer>> result) {
        if(node == null) return;

        path.add(node.val);

        if(node.left == null && node.right == null) {
            result.add(new ArrayList<>(path));
        } else {
            allPathsHelper(node.left, path, result);
            allPathsHelper(node.right, path, result);
        }

        path.remove(path.size() - 1); // backtrack
    }

    // =====================================================
    // 4️⃣ MAXIMUM PATH SUM (ANY PATH)
    // =====================================================
    static class MaxSum {
        int val;
    }

    public static int maxPathSum(TreeNode root) {
        MaxSum maxSum = new MaxSum();
        maxSum.val = Integer.MIN_VALUE;
        maxPathSumHelper(root, maxSum);
        return maxSum.val;
    }

    private static int maxPathSumHelper(TreeNode node, MaxSum maxSum) {
        if(node == null) return 0;

        int left = Math.max(0, maxPathSumHelper(node.left, maxSum));
        int right = Math.max(0, maxPathSumHelper(node.right, maxSum));

        maxSum.val = Math.max(maxSum.val, node.val + left + right);

        return node.val + Math.max(left, right);
    }

    // =====================================================
    // INORDER (for testing tree structure)
    // =====================================================
    public static void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // MAIN METHOD (TESTING PATH OPERATIONS)
    // =====================================================
    public static void main(String[] args) {
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(-3);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(2);
        root.right.right = new TreeNode(11);
        root.left.left.left = new TreeNode(3);
        root.left.left.right = new TreeNode(-2);
        root.left.right.right = new TreeNode(1);

        System.out.println("Inorder Traversal:");
        inorder(root);
        System.out.println("\n");

        System.out.println("All Root-to-Leaf Paths:");
        printRootToLeaf(root);

        int sum = 18;
        System.out.println("Has Path Sum " + sum + ": " + hasPathSum(root, sum));

        System.out.println("All Root-to-Leaf Paths as List:");
        List<List<Integer>> paths = allRootToLeafPaths(root);
        for(List<Integer> p : paths) {
            System.out.println(p);
        }

        System.out.println("Maximum Path Sum (any path): " + maxPathSum(root));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Print All Root-to-Leaf Paths:
   - Time:  O(n) for traversal, + O(n) per path for printing
   - Space: O(h) recursion stack + O(h) path list

2) Path Sum Exists:
   - Time:  O(n)
   - Space: O(h)

3) All Root-to-Leaf Paths as List:
   - Time:  O(n) traversal + O(n) per path copy
   - Space: O(h) recursion stack + O(paths)

4) Maximum Path Sum:
   - Time:  O(n)
   - Space: O(h) recursion stack
===========================================================
*/