package DataStructures.Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
===========================================================
Binary Tree Insertion Implementations Included in File
===========================================================

1) BST Insertion
   - Recursive
   - Iterative

2) Normal Binary Tree
   - Level Order Insertion (Complete Tree Style)

3) Special Binary Tree Insertions
   - Insert as Left Child of a Given Node
   - Insert as Right Child of a Given Node
   - Insert at Root (Old Tree Becomes Left Child)
   - Insert at Specific Level (Recursive)

===========================================================
*/

public class BinaryTreeAllInsertions {

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
    // 1️⃣ BST INSERTION - RECURSIVE
    // =====================================================
    public static TreeNode insertBSTRecursive(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);

        if (val < root.val)
            root.left = insertBSTRecursive(root.left, val);
        else if (val > root.val)
            root.right = insertBSTRecursive(root.right, val);

        return root;
    }

    // =====================================================
    // 2️⃣ BST INSERTION - ITERATIVE
    // =====================================================
    public static TreeNode insertBSTIterative(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);

        if (root == null) return newNode;

        TreeNode current = root;
        TreeNode parent = null;

        while (current != null) {
            parent = current;
            if (val < current.val)
                current = current.left;
            else if (val > current.val)
                current = current.right;
            else
                return root; // ignore duplicates
        }

        if (val < parent.val)
            parent.left = newNode;
        else
            parent.right = newNode;

        return root;
    }

    // =====================================================
    // 3️⃣ NORMAL BINARY TREE INSERT (LEVEL ORDER)
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
    // 4️⃣ INSERT AS LEFT CHILD OF GIVEN NODE
    // =====================================================
    public static boolean insertLeft(TreeNode root, int parentVal, int newVal) {
        if (root == null) return false;

        if (root.val == parentVal) {
            TreeNode newNode = new TreeNode(newVal);
            newNode.left = root.left;
            root.left = newNode;
            return true;
        }

        return insertLeft(root.left, parentVal, newVal) ||
               insertLeft(root.right, parentVal, newVal);
    }

    // =====================================================
    // 5️⃣ INSERT AS RIGHT CHILD OF GIVEN NODE
    // =====================================================
    public static boolean insertRight(TreeNode root, int parentVal, int newVal) {
        if (root == null) return false;

        if (root.val == parentVal) {
            TreeNode newNode = new TreeNode(newVal);
            newNode.right = root.right;
            root.right = newNode;
            return true;
        }

        return insertRight(root.left, parentVal, newVal) ||
               insertRight(root.right, parentVal, newVal);
    }

    // =====================================================
    // 6️⃣ INSERT NEW ROOT (OLD TREE BECOMES LEFT CHILD)
    // =====================================================
    public static TreeNode insertAtRoot(TreeNode root, int val) {
        TreeNode newRoot = new TreeNode(val);
        newRoot.left = root;
        return newRoot;
    }

    // =====================================================
    // 7️⃣ INSERT AT SPECIFIC LEVEL (RECURSIVE)
    // =====================================================
    public static void insertAtLevel(TreeNode root, int val, int level) {
        if (root == null) return;

        if (level == 1) {
            if (root.left == null)
                root.left = new TreeNode(val);
            else if (root.right == null)
                root.right = new TreeNode(val);
        } else {
            insertAtLevel(root.left, val, level - 1);
            insertAtLevel(root.right, val, level - 1);
        }
    }

    // =====================================================
    // INORDER TRAVERSAL
    // =====================================================
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // LEVEL ORDER TRAVERSAL
    // =====================================================
    public static void levelOrder(TreeNode root) {
        if (root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            System.out.print(temp.val + " ");
            if (temp.left != null) queue.offer(temp.left);
            if (temp.right != null) queue.offer(temp.right);
        }
    }

    // =====================================================
    // MAIN METHOD (TESTING ALL TYPES)
    // =====================================================
    public static void main(String[] args) {

        // ======================
        // BST Recursive Test
        // ======================
        TreeNode bstRec = null;
        int[] bstValues = {50, 30, 70, 20, 40, 60, 80};

        for (int val : bstValues)
            bstRec = insertBSTRecursive(bstRec, val);

        System.out.println("BST Recursive (Inorder):");
        inorder(bstRec);
        System.out.println("\n");

        // ======================
        // BST Iterative Test
        // ======================
        TreeNode bstItr = null;
        for (int val : bstValues)
            bstItr = insertBSTIterative(bstItr, val);

        System.out.println("BST Iterative (Inorder):");
        inorder(bstItr);
        System.out.println("\n");

        // ======================
        // Normal Binary Tree Test
        // ======================
        TreeNode normalTree = null;
        int[] values = {1, 2, 3, 4, 5};

        for (int val : values)
            normalTree = insertLevelOrder(normalTree, val);

        System.out.println("Normal Binary Tree (Level Order):");
        levelOrder(normalTree);
        System.out.println("\n");

        // ======================
        // Insert Left / Right
        // ======================
        insertLeft(normalTree, 2, 99);
        insertRight(normalTree, 3, 88);

        System.out.println("After Left & Right Insert:");
        levelOrder(normalTree);
        System.out.println("\n");

        // ======================
        // Insert at Root
        // ======================
        normalTree = insertAtRoot(normalTree, 100);
        System.out.println("After Root Insert:");
        levelOrder(normalTree);
        System.out.println("\n");

        // ======================
        // Insert at Specific Level
        // ======================
        insertAtLevel(normalTree, 111, 2);
        System.out.println("After Insert at Level 2:");
        levelOrder(normalTree);
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) BST Insertion
   - Recursive:
       Time:  O(h)   (h = height of tree, O(log n) average, O(n) worst)
       Space: O(h)   (recursion stack)
   - Iterative:
       Time:  O(h)
       Space: O(1)

2) Normal Binary Tree (Level Order Insert):
       Time:  O(n)   (needs to traverse nodes to find empty spot)
       Space: O(n)   (queue for level-order traversal)

3) Special Binary Tree Insertions:
   - Insert as Left/Right Child:
       Time:  O(n)   (may traverse entire tree)
       Space: O(h)   (recursion stack)
   - Insert at Root:
       Time:  O(1)
       Space: O(1)
   - Insert at Specific Level:
       Time:  O(n)   (traverses nodes at given level)
       Space: O(h)   (recursion stack)

===========================================================
*/