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
    public TreeNode constructFromPrePost(int[] pre, int[] post) {
        return construct(pre, post, 0, 0, pre.length);
    }
    
    private TreeNode construct(int[] pre, int[] post, int preStart, int postStart, int len) {
        if (len == 0) return null;
        
        TreeNode root = new TreeNode(pre[preStart]);
        if (len == 1) return root;
        
        int leftLen = 1;
        for (int i = postStart; i < postStart + len - 1; i++) {
            if (post[i] == pre[preStart + 1]) {
                leftLen = i - postStart + 1;
                break;
            }
        }
        
        root.left = construct(pre, post, preStart + 1, postStart, leftLen);
        root.right = construct(pre, post, preStart + leftLen + 1, postStart + leftLen, len - leftLen - 1);
        
        return root;
}
}
