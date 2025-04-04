/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    static class Pair {
        int depth;
        TreeNode node;
        Pair(int depth, TreeNode node) {
            this.depth = depth;
            this.node = node;
        }
    }

    private static Pair findLCA(TreeNode root) {
        if (root == null) return new Pair(0, null);
        Pair left = findLCA(root.left), right = findLCA(root.right);
        if (left.depth == right.depth) return new Pair(left.depth + 1, root);
        return left.depth > right.depth ? new Pair(left.depth + 1, left.node) : new Pair(right.depth + 1, right.node);
    }

    public static TreeNode lcaDeepestLeaves(TreeNode root) {
        return findLCA(root).node;
    }
}
