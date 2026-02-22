package DataStructures.Trees;

import java.util.*;

/*
===========================================================
Zigzag / Spiral Level Order Traversal – Special BFS
===========================================================

1) Tree Node Definition
2) Binary Tree Level Order Insertion
3) Zigzag / Spiral Traversal (Alternating Levels)
4) Inorder Traversal (for verification)
5) Testing Examples
===========================================================
*/

public class ZigZagTraversal {

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
        if (root == null) return newNode;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            if (temp.left == null) {
                temp.left = newNode;
                return root;
            } else queue.offer(temp.left);

            if (temp.right == null) {
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
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // Zigzag / Spiral Level Order Traversal
    // =====================================================
    public static List<List<Integer>> zigzagTraversal(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;

        Deque<TreeNode> deque = new LinkedList<>();
        deque.offer(root);
        boolean leftToRight = true;

        while (!deque.isEmpty()) {
            int size = deque.size();
            List<Integer> level = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                if (leftToRight) {
                    TreeNode node = deque.pollFirst();
                    level.add(node.val);
                    if (node.left != null) deque.offerLast(node.left);
                    if (node.right != null) deque.offerLast(node.right);
                } else {
                    TreeNode node = deque.pollLast();
                    level.add(node.val);
                    if (node.right != null) deque.offerFirst(node.right);
                    if (node.left != null) deque.offerFirst(node.left);
                }
            }

            result.add(level);
            leftToRight = !leftToRight;
        }

        return result;
    }

    // =====================================================
    // MAIN METHOD (TESTING)
    // =====================================================
    public static void main(String[] args) {
        TreeNode root = null;
        int[] values = {1, 2, 3, 4, 5, 6, 7};

        for (int val : values) root = insertLevelOrder(root, val);

        System.out.println("Inorder Traversal:");
        inorder(root);
        System.out.println();

        System.out.println("\nZigzag / Spiral Level Order Traversal:");
        List<List<Integer>> zigzag = zigzagTraversal(root);
        for (List<Integer> level : zigzag) {
            System.out.println(level);
        }
    }
}

/*
===========================================================
Time & Space Complexity
===========================================================

Zigzag / Spiral Traversal:
    Time: O(n) - visits each node once
    Space: O(w) - deque stores nodes at max width of tree

===========================================================
*/