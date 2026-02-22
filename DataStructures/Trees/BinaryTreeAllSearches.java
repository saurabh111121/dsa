package DataStructures.Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
===========================================================
Binary Tree Search Implementations Included in File
===========================================================

1) BST Search
   - Recursive
   - Iterative
   - Find Min / Max

2) Normal Binary Tree Search
   - DFS (Recursive)
   - Level Order (BFS)

3) Special Searches
   - Find Parent of a Node
   - Find Level of a Node

===========================================================
*/

public class BinaryTreeAllSearches {

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
    // 1️⃣ BST SEARCH - RECURSIVE
    // =====================================================
    public static boolean searchBSTRecursive(TreeNode root, int val) {
        if(root == null) return false;

        if(val == root.val) return true;
        else if(val < root.val) return searchBSTRecursive(root.left, val);
        else return searchBSTRecursive(root.right, val);
    }

    // =====================================================
    // 2️⃣ BST SEARCH - ITERATIVE
    // =====================================================
    public static boolean searchBSTIterative(TreeNode root, int val) {
        TreeNode current = root;

        while(current != null) {
            if(val == current.val) return true;
            else if(val < current.val) current = current.left;
            else current = current.right;
        }

        return false;
    }

    // =====================================================
    // 3️⃣ FIND MIN / MAX IN BST
    // =====================================================
    public static int findMinBST(TreeNode root) {
        if(root == null) throw new IllegalArgumentException("Tree is empty");
        while(root.left != null) root = root.left;
        return root.val;
    }

    public static int findMaxBST(TreeNode root) {
        if(root == null) throw new IllegalArgumentException("Tree is empty");
        while(root.right != null) root = root.right;
        return root.val;
    }

    // =====================================================
    // 4️⃣ NORMAL BINARY TREE SEARCH - DFS (RECURSIVE)
    // =====================================================
    public static boolean searchDFS(TreeNode root, int val) {
        if(root == null) return false;
        if(root.val == val) return true;
        return searchDFS(root.left, val) || searchDFS(root.right, val);
    }

    // =====================================================
    // 5️⃣ NORMAL BINARY TREE SEARCH - LEVEL ORDER (BFS)
    // =====================================================
    public static boolean searchBFS(TreeNode root, int val) {
        if(root == null) return false;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if(node.val == val) return true;
            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }

        return false;
    }

    // =====================================================
    // 6️⃣ FIND PARENT OF A NODE
    // =====================================================
    public static TreeNode findParent(TreeNode root, int val) {
        if(root == null || root.val == val) return null;

        if((root.left != null && root.left.val == val) ||
            (root.right != null && root.right.val == val)) return root;

        TreeNode leftParent = findParent(root.left, val);
        if(leftParent != null) return leftParent;

        return findParent(root.right, val);
    }

    // =====================================================
    // 7️⃣ FIND LEVEL OF NODE
    // =====================================================
    public static int findLevel(TreeNode root, int val, int level) {
        if(root == null) return -1;
        if(root.val == val) return level;

        int left = findLevel(root.left, val, level + 1);
        if(left != -1) return left;

        return findLevel(root.right, val, level + 1);
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
    // MAIN METHOD (TESTING ALL SEARCHES)
    // =====================================================
    public static void main(String[] args) {
        TreeNode bstRoot = new TreeNode(50);
        bstRoot.left = new TreeNode(30);
        bstRoot.right = new TreeNode(70);
        bstRoot.left.left = new TreeNode(20);
        bstRoot.left.right = new TreeNode(40);
        bstRoot.right.left = new TreeNode(60);
        bstRoot.right.right = new TreeNode(80);

        System.out.println("BST Inorder:");
        inorder(bstRoot);
        System.out.println("\n");

        // BST Search
        System.out.println("BST Recursive Search 40: " + searchBSTRecursive(bstRoot, 40));
        System.out.println("BST Iterative Search 90: " + searchBSTIterative(bstRoot, 90));
        System.out.println("BST Min: " + findMinBST(bstRoot));
        System.out.println("BST Max: " + findMaxBST(bstRoot));

        // Normal Tree Search
        TreeNode normalTree = new TreeNode(1);
        normalTree.left = new TreeNode(2);
        normalTree.right = new TreeNode(3);
        normalTree.left.left = new TreeNode(4);
        normalTree.left.right = new TreeNode(5);

        System.out.println("Normal Tree DFS Search 5: " + searchDFS(normalTree, 5));
        System.out.println("Normal Tree BFS Search 6: " + searchBFS(normalTree, 6));

        // Special Searches
        TreeNode parent = findParent(normalTree, 5);
        System.out.println("Parent of 5: " + (parent != null ? parent.val : "null"));

        int level = findLevel(normalTree, 5, 1);
        System.out.println("Level of 5: " + level);
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) BST Search:
   - Recursive / Iterative:
       Time:  O(h)   (h = tree height, O(log n) avg, O(n) worst)
       Space: O(h) recursive, O(1) iterative
   - Find Min / Max:
       Time:  O(h)
       Space: O(1)

2) Normal Binary Tree Search:
   - DFS Recursive:
       Time:  O(n)
       Space: O(h)
   - BFS Level Order:
       Time:  O(n)
       Space: O(n)

3) Special Searches:
   - Find Parent:
       Time:  O(n)
       Space: O(h)
   - Find Level:
       Time:  O(n)
       Space: O(h)

===========================================================
*/