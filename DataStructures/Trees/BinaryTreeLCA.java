package DataStructures.Trees;

/*
===========================================================
Binary Tree Lowest Common Ancestor (LCA) Implementations
===========================================================

1) LCA in Binary Tree (Recursive)
2) LCA in Binary Search Tree (Optimized Recursive)
3) Testing example trees

===========================================================
*/

public class BinaryTreeLCA {

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
    // 1️⃣ LCA IN BINARY TREE (RECURSIVE)
    // =====================================================
    public static TreeNode lcaBinaryTree(TreeNode root, int n1, int n2) {
        if(root == null) return null;

        if(root.val == n1 || root.val == n2) return root;

        TreeNode leftLCA = lcaBinaryTree(root.left, n1, n2);
        TreeNode rightLCA = lcaBinaryTree(root.right, n1, n2);

        if(leftLCA != null && rightLCA != null) return root;

        return (leftLCA != null) ? leftLCA : rightLCA;
    }

    // =====================================================
    // 2️⃣ LCA IN BST (RECURSIVE, OPTIMIZED)
    // =====================================================
    public static TreeNode lcaBST(TreeNode root, int n1, int n2) {
        if(root == null) return null;

        if(n1 < root.val && n2 < root.val)
            return lcaBST(root.left, n1, n2);
        else if(n1 > root.val && n2 > root.val)
            return lcaBST(root.right, n1, n2);
        else
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
    // MAIN METHOD (TESTING LCA)
    // =====================================================
    public static void main(String[] args) {
        // Binary Tree Example
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

        int n1 = 4, n2 = 5;
        TreeNode lcaBT = lcaBinaryTree(root, n1, n2);
        System.out.println("LCA of " + n1 + " and " + n2 + " in Binary Tree: " + (lcaBT != null ? lcaBT.val : "null"));

        n1 = 4; n2 = 6;
        lcaBT = lcaBinaryTree(root, n1, n2);
        System.out.println("LCA of " + n1 + " and " + n2 + " in Binary Tree: " + (lcaBT != null ? lcaBT.val : "null"));

        // BST Example
        TreeNode bstRoot = new TreeNode(20);
        bstRoot.left = new TreeNode(10);
        bstRoot.right = new TreeNode(30);
        bstRoot.left.left = new TreeNode(5);
        bstRoot.left.right = new TreeNode(15);
        bstRoot.right.left = new TreeNode(25);
        bstRoot.right.right = new TreeNode(35);

        System.out.println("\nBST Inorder Traversal:");
        inorder(bstRoot);
        System.out.println("\n");

        n1 = 5; n2 = 15;
        TreeNode lcaBSTNode = lcaBST(bstRoot, n1, n2);
        System.out.println("LCA of " + n1 + " and " + n2 + " in BST: " + (lcaBSTNode != null ? lcaBSTNode.val : "null"));

        n1 = 5; n2 = 30;
        lcaBSTNode = lcaBST(bstRoot, n1, n2);
        System.out.println("LCA of " + n1 + " and " + n2 + " in BST: " + (lcaBSTNode != null ? lcaBSTNode.val : "null"));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) LCA in Binary Tree:
   - Time:  O(n)   (traverse all nodes)
   - Space: O(h)   (recursion stack)

2) LCA in BST (Optimized):
   - Time:  O(h)   (only traverse path from root to LCA)
   - Space: O(h)   (recursion stack)

===========================================================
*/