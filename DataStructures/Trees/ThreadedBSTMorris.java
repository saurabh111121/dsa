package DataStructures.Trees;

/*
===========================================================
Threaded BST / Morris Traversal - Space-Optimized Inorder Traversal
===========================================================

1) BST Insert (Recursive & Iterative)
2) Morris Inorder Traversal (O(1) Space)
3) Standard Recursive Inorder Traversal (for verification)
4) Testing Examples

===========================================================
*/

public class ThreadedBSTMorris {

    // =========================
    // Tree Node Definition
    // =========================
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) { this.val = val; }
    }

    // =====================================================
    // 1️⃣ BST INSERT - RECURSIVE
    // =====================================================
    public static TreeNode insertBSTRecursive(TreeNode root, int val) {
        if (root == null) return new TreeNode(val);
        if (val < root.val) root.left = insertBSTRecursive(root.left, val);
        else if (val > root.val) root.right = insertBSTRecursive(root.right, val);
        return root;
    }

    // =====================================================
    // 2️⃣ BST INSERT - ITERATIVE
    // =====================================================
    public static TreeNode insertBSTIterative(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) return newNode;

        TreeNode current = root, parent = null;
        while (current != null) {
            parent = current;
            if (val < current.val) current = current.left;
            else if (val > current.val) current = current.right;
            else return root; // ignore duplicates
        }

        if (val < parent.val) parent.left = newNode;
        else parent.right = newNode;

        return root;
    }

    // =====================================================
    // 3️⃣ MORRIS INORDER TRAVERSAL (SPACE-OPTIMIZED)
    // =====================================================
    public static void morrisInorder(TreeNode root) {
        TreeNode current = root;

        while (current != null) {
            if (current.left == null) {
                System.out.print(current.val + " ");
                current = current.right;
            } else {
                // Find inorder predecessor of current
                TreeNode pred = current.left;
                while (pred.right != null && pred.right != current) {
                    pred = pred.right;
                }

                if (pred.right == null) {
                    // Make thread to current
                    pred.right = current;
                    current = current.left;
                } else {
                    // Thread exists, revert it
                    pred.right = null;
                    System.out.print(current.val + " ");
                    current = current.right;
                }
            }
        }
    }

    // =====================================================
    // 4️⃣ RECURSIVE INORDER (FOR VERIFICATION)
    // =====================================================
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // MAIN METHOD (TESTING)
    // =====================================================
    public static void main(String[] args) {
        int[] values = {20, 10, 30, 5, 15, 25, 35};
        TreeNode bst = null;

        for (int val : values) bst = insertBSTRecursive(bst, val);

        System.out.println("Recursive Inorder Traversal:");
        inorder(bst);
        System.out.println("\n");

        System.out.println("Morris Inorder Traversal (O(1) Space):");
        morrisInorder(bst);
        System.out.println("\n");

        // Test iterative insertion
        TreeNode bst2 = null;
        for (int val : values) bst2 = insertBSTIterative(bst2, val);

        System.out.println("Morris Inorder Traversal on Iteratively Inserted BST:");
        morrisInorder(bst2);
        System.out.println();
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

BST Insert:
   - Recursive: Time O(h), Space O(h) (stack)
   - Iterative: Time O(h), Space O(1)
   - h = height of BST

Morris Inorder Traversal:
   - Time: O(n)
   - Space: O(1) (no recursion stack or extra data structures)
   - Temporary threads are used but restored to original tree

===========================================================
*/