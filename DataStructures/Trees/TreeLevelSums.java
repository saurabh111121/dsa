package DataStructures.Trees;

import java.util.*;

/*
===========================================================
Vertical / Horizontal Sum of Binary Tree Nodes
===========================================================

1) Tree Node Definition
2) Binary Tree Level Order Insertion
3) Inorder Traversal (for verification)
4) Horizontal (Level) Sum
5) Vertical Sum
6) Testing Examples
===========================================================
*/

public class TreeLevelSums {

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
    // Horizontal / Level Sum
    // =====================================================
    public static List<Integer> horizontalSum(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            int sum = 0;

            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                sum += node.val;

                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }

            result.add(sum);
        }

        return result;
    }

    // =====================================================
    // Vertical Sum
    // =====================================================
    public static Map<Integer, Integer> verticalSum(TreeNode root) {
        Map<Integer, Integer> map = new TreeMap<>();
        verticalSumHelper(root, 0, map);
        return map;
    }

    private static void verticalSumHelper(TreeNode node, int hd, Map<Integer, Integer> map) {
        if(node == null) return;

        map.put(hd, map.getOrDefault(hd, 0) + node.val);
        verticalSumHelper(node.left, hd - 1, map);
        verticalSumHelper(node.right, hd + 1, map);
    }

    // =====================================================
    // MAIN METHOD (TESTING)
    // =====================================================
    public static void main(String[] args) {
        TreeNode root = null;
        int[] values = {1, 2, 3, 4, 5, 6, 7};

        for(int val : values) root = insertLevelOrder(root, val);

        System.out.println("Inorder Traversal:");
        inorder(root);
        System.out.println();

        System.out.println("\nHorizontal (Level) Sum:");
        List<Integer> hSum = horizontalSum(root);
        System.out.println(hSum);

        System.out.println("\nVertical Sum:");
        Map<Integer, Integer> vSum = verticalSum(root);
        for(Map.Entry<Integer, Integer> entry : vSum.entrySet()) {
            System.out.println("HD " + entry.getKey() + " : " + entry.getValue());
        }
    }
}

/*
===========================================================
Time & Space Complexity
===========================================================

Horizontal (Level) Sum:
    Time: O(n) - visits each node once
    Space: O(w) - queue, w = max width of tree

Vertical Sum:
    Time: O(n) - visits each node once
    Space: O(h + v) - recursion stack O(h), TreeMap stores v vertical lines

===========================================================
*/