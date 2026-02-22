package DataStructures.Trees;

import java.util.*;

public class BTInsertion {

    // -------------------------
    // Node Definition
    // -------------------------
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // ------------------------------------------------
    // 1️⃣ BST INSERTION
    // ------------------------------------------------
    public static TreeNode insertBST(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.val) {
            root.left = insertBST(root.left, val);
        } else {
            root.right = insertBST(root.right, val);
        }

        return root;
    }

    // ------------------------------------------------
    // 2️⃣ LEVEL ORDER INSERTION (Normal Binary Tree)
    // ------------------------------------------------
    public static TreeNode insertLevelOrder(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();

            if (curr.left == null) {
                curr.left = new TreeNode(val);
                return root;
            } else {
                queue.add(curr.left);
            }

            if (curr.right == null) {
                curr.right = new TreeNode(val);
                return root;
            } else {
                queue.add(curr.right);
            }
        }

        return root;
    }

    // ------------------------------------------------
    // 3️⃣ INSERT AS LEFT CHILD OF A GIVEN NODE
    // ------------------------------------------------
    public static boolean insertAsLeft(TreeNode root, int parentVal, int val) {
        TreeNode parent = search(root, parentVal);
        if (parent == null) return false;

        if (parent.left == null) {
            parent.left = new TreeNode(val);
        } else {
            TreeNode newNode = new TreeNode(val);
            newNode.left = parent.left;
            parent.left = newNode;
        }

        return true;
    }

    // ------------------------------------------------
    // 4️⃣ INSERT AS RIGHT CHILD OF A GIVEN NODE
    // ------------------------------------------------
    public static boolean insertAsRight(TreeNode root, int parentVal, int val) {
        TreeNode parent = search(root, parentVal);
        if (parent == null) return false;

        if (parent.right == null) {
            parent.right = new TreeNode(val);
        } else {
            TreeNode newNode = new TreeNode(val);
            newNode.right = parent.right;
            parent.right = newNode;
        }

        return true;
    }

    // ------------------------------------------------
    // SEARCH NODE (BFS)
    // ------------------------------------------------
    public static TreeNode search(TreeNode root, int val) {
        if (root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            if (curr.val == val) return curr;

            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }

        return null;
    }

    // ------------------------------------------------
    // INORDER TRAVERSAL
    // ------------------------------------------------
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // ------------------------------------------------
    // LEVEL ORDER TRAVERSAL
    // ------------------------------------------------
    public static void levelOrder(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.add(root);

        while (!queue.isEmpty()) {
            TreeNode curr = queue.poll();
            System.out.print(curr.val + " ");

            if (curr.left != null) queue.add(curr.left);
            if (curr.right != null) queue.add(curr.right);
        }
    }

    // ------------------------------------------------
    // MAIN METHOD FOR TESTING
    // ------------------------------------------------
    public static void main(String[] args) {

        TreeNode root = null;

        // -------- BST INSERTION --------
        int[] bstValues = {50, 30, 70, 20, 40, 60, 80};
        for (int val : bstValues) {
            root = insertBST(root, val);
        }

        System.out.println("BST Inorder (Sorted):");
        inorder(root);
        System.out.println("\n");

        // -------- LEVEL ORDER INSERTION --------
        root = insertLevelOrder(root, 90);
        root = insertLevelOrder(root, 100);

        System.out.println("After Level Order Insert:");
        levelOrder(root);
        System.out.println("\n");

        // -------- INSERT AS LEFT CHILD --------
        insertAsLeft(root, 30, 25);

        System.out.println("After Insert As Left of 30:");
        levelOrder(root);
        System.out.println("\n");

        // -------- INSERT AS RIGHT CHILD --------
        insertAsRight(root, 70, 75);

        System.out.println("After Insert As Right of 70:");
        levelOrder(root);
        System.out.println();
    }
}
    // ------------------------------------------------
    // Complexity
    // ------------------------------------------------
    /*
    BST Insert → O(h)
    Level Order Insert → O(n)
    Search (BFS) → O(n)
    */