package DataStructures.Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
===========================================================
Binary Tree Mirror / Invert Implementations
===========================================================

1) Mirror / Invert Tree - Recursive
2) Mirror / Invert Tree - Iterative (Level Order)
3) Testing example trees

===========================================================
*/

public class BinaryTreeMirror {

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
    // 1️⃣ MIRROR / INVERT TREE - RECURSIVE
    // =====================================================
    public static TreeNode mirrorRecursive(TreeNode root) {
        if(root == null) return null;

        TreeNode left = mirrorRecursive(root.left);
        TreeNode right = mirrorRecursive(root.right);

        root.left = right;
        root.right = left;

        return root;
    }

    // =====================================================
    // 2️⃣ MIRROR / INVERT TREE - ITERATIVE (LEVEL ORDER)
    // =====================================================
    public static TreeNode mirrorIterative(TreeNode root) {
        if(root == null) return null;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();

            // Swap children
            TreeNode temp = node.left;
            node.left = node.right;
            node.right = temp;

            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }

        return root;
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
    // MAIN METHOD (TESTING MIRROR / INVERT)
    // =====================================================
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);

        System.out.println("Original Tree Inorder:");
        inorder(root);
        System.out.println("\n");

        TreeNode mirroredRec = mirrorRecursive(root);
        System.out.println("Mirrored Tree (Recursive) Inorder:");
        inorder(mirroredRec);
        System.out.println("\n");

        // Mirror again using iterative to restore original
        TreeNode mirroredItr = mirrorIterative(mirroredRec);
        System.out.println("Mirrored Tree (Iterative) Inorder:");
        inorder(mirroredItr);
        System.out.println("\n");
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Mirror / Invert Tree - Recursive:
   - Time:  O(n)
   - Space: O(h) recursion stack

2) Mirror / Invert Tree - Iterative (Level Order):
   - Time:  O(n)
   - Space: O(n) (queue)
===========================================================
*/