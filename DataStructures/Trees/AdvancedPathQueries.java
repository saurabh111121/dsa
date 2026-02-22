package DataStructures.Trees;

import java.util.*;

/*
===========================================================
Advanced Path Queries – Find All Paths with Target Sum
===========================================================

1) Tree Node Definition
2) Binary Tree Level Order Insertion
3) Find All Paths Sum Equals Target
4) Inorder Traversal (for verification)
5) Testing Examples
===========================================================
*/

public class AdvancedPathQueries {

    // =========================
    // Tree Node Definition
    // =========================
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) { this.val = val; }
    }

    // =====================================================
    // Binary Tree Level Order Insertion
    // =====================================================
    public static TreeNode insertLevelOrder(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if(root == null) return newNode;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            if(temp.left == null) {
                temp.left = newNode;
                return root;
            } else queue.offer(temp.left);

            if(temp.right == null) {
                temp.right = newNode;
                return root;
            } else queue.offer(temp.right);
        }

        return root;
    }

    // =====================================================
    // Inorder Traversal (for verification)
    // =====================================================
    public static void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // Find All Paths with Target Sum (Root-to-Leaf)
    // =====================================================
    public static List<List<Integer>> findPathsWithSum(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        dfs(root, target, new ArrayList<>(), result);
        return result;
    }

    private static void dfs(TreeNode node, int target, List<Integer> path, List<List<Integer>> result) {
        if(node == null) return;

        path.add(node.val);
        target -= node.val;

        // Check if leaf and sum matches
        if(node.left == null && node.right == null && target == 0) {
            result.add(new ArrayList<>(path));
        } else {
            dfs(node.left, target, path, result);
            dfs(node.right, target, path, result);
        }

        path.remove(path.size() - 1); // backtrack
    }

    // =====================================================
    // Find All Paths With Sum Anywhere (not only root-to-leaf)
    // =====================================================
    public static List<List<Integer>> findPathsAnywhere(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        findPathsAnywhereHelper(root, target, new ArrayList<>(), result);
        return result;
    }

    private static void findPathsAnywhereHelper(TreeNode node, int target, List<Integer> path, List<List<Integer>> result) {
        if(node == null) return;

        path.add(node.val);

        int sum = 0;
        // Check all sub-paths ending at current node
        for(int i = path.size() - 1; i >= 0; i--) {
            sum += path.get(i);
            if(sum == target) {
                result.add(new ArrayList<>(path.subList(i, path.size())));
            }
        }

        findPathsAnywhereHelper(node.left, target, path, result);
        findPathsAnywhereHelper(node.right, target, path, result);

        path.remove(path.size() - 1); // backtrack
    }

    // =====================================================
    // MAIN METHOD (TESTING)
    // =====================================================
    public static void main(String[] args) {
        TreeNode root = null;
        int[] values = {10, 5, -3, 3, 2, 11, 3, -2, 1};

        for(int val : values) root = insertLevelOrder(root, val);

        System.out.println("Inorder Traversal of Tree:");
        inorder(root);
        System.out.println("\n");

        int target = 8;

        System.out.println("Root-to-Leaf Paths with Sum " + target + ":");
        List<List<Integer>> paths = findPathsWithSum(root, target);
        for(List<Integer> path : paths) {
            System.out.println(path);
        }

        System.out.println("\nPaths Anywhere with Sum " + target + ":");
        List<List<Integer>> pathsAny = findPathsAnywhere(root, target);
        for(List<Integer> path : pathsAny) {
            System.out.println(path);
        }
    }
}

/*
===========================================================
Time & Space Complexity
===========================================================

Root-to-Leaf Paths:
    Time: O(n) each node visited once, O(n) per valid path in copying
    Space: O(h) recursion stack, O(n) for path list

Paths Anywhere:
    Time: O(n^2) in worst-case (for each node, check all sub-paths)
    Space: O(h) recursion stack, O(h) for current path

===========================================================
*/