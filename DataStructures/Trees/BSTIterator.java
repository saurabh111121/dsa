package DataStructures.Trees;

import java.util.Stack;

/*
===========================================================
BST Iterator – next() / hasNext()
===========================================================

1) Tree Node Definition
2) BST Insert (Recursive)
3) BST Iterator Class (Stack-based Inorder)
4) Testing Examples
===========================================================
*/

public class BSTIterator {

    // =========================
    // Tree Node Definition
    // =========================
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) { this.val = val; }
    }

    // =====================================================
    // BST Insert - Recursive
    // =====================================================
    public static TreeNode insertBSTRecursive(TreeNode root, int val) {
        if(root == null) return new TreeNode(val);
        if(val < root.val) root.left = insertBSTRecursive(root.left, val);
        else if(val > root.val) root.right = insertBSTRecursive(root.right, val);
        return root;
    }

    // =====================================================
    // BST Iterator Class
    // =====================================================
    static class Iterator {
        private Stack<TreeNode> stack = new Stack<>();

        public Iterator(TreeNode root) {
            pushAllLeft(root);
        }

        // Push all left children of the node to the stack
        private void pushAllLeft(TreeNode node) {
            while(node != null) {
                stack.push(node);
                node = node.left;
            }
        }

        // Returns the next smallest element
        public int next() {
            TreeNode node = stack.pop();
            if(node.right != null) pushAllLeft(node.right);
            return node.val;
        }

        // Returns true if there is a next element
        public boolean hasNext() {
            return !stack.isEmpty();
        }
    }

    // =====================================================
    // MAIN METHOD (TESTING BSTIterator)
    // =====================================================
    public static void main(String[] args) {
        int[] values = {7, 3, 15, 9, 20};
        TreeNode root = null;
        for(int val : values) root = insertBSTRecursive(root, val);

        System.out.println("BST Iterator Traversal (Next Elements):");
        Iterator iterator = new Iterator(root);
        while(iterator.hasNext()) {
            System.out.print(iterator.next() + " ");
        }
        System.out.println();
    }
}

/*
===========================================================
Time & Space Complexity
===========================================================

BST Iterator:
    next():
        Time: O(1) amortized (each node is pushed/popped once)
        Space: O(h) stack, h = height of BST
    hasNext():
        Time: O(1)
        Space: O(1)

===========================================================
*/