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
    int postIndex;
    Map<Integer, Integer> inorderMap;
    public TreeNode buildTree(int[] inorder, int[] postorder) {
        postIndex = postorder.length - 1;
        inorderMap = new HashMap<>();

        for(int i=0;i<inorder.length;i++) {
            inorderMap.put(inorder[i],i);
        }
        return h(inorder,postorder,0,inorder.length -1);
    }

    private TreeNode h(int[] inorder,int[] postorder,int left,int right){
        if(left>right) return null;
        int rootVal = postorder[postIndex--];
        TreeNode root = new TreeNode(rootVal);
        int inorderIndex = inorderMap.get(rootVal);
        root.right = h(inorder,postorder,inorderIndex+1,right);
        root.left = h(inorder,postorder,left,inorderIndex-1);
        return root;
    }
}
