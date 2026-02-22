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
    long totalSum = 0;
    long maxProduct = 0;
    int MOD = 1_000_000_007;
    
    public int maxProduct(TreeNode root) {
        totalSum = getTotalSum(root);
        computeMaxProduct(root);
        return (int) (maxProduct % MOD);
    }

    long getTotalSum(TreeNode node) {
        if (node == null) return 0;
        return node.val + getTotalSum(node.left) + getTotalSum(node.right);
    }

    long computeMaxProduct(TreeNode node) {
        if (node == null) return 0;
        long leftSum = computeMaxProduct(node.left);
        long rightSum = computeMaxProduct(node.right);
        long subTreeSum = node.val + leftSum + rightSum;
        maxProduct = Math.max(maxProduct, subTreeSum * (totalSum - subTreeSum));

        return subTreeSum;
    }
}
