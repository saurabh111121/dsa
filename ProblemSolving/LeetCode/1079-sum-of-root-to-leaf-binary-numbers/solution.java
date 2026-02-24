// Iterative (using Stack)
class Solution {
    public int sumRootToLeaf(TreeNode root) {
        if(root == null) return 0;
        int totalSum = 0;
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 0));

        while(!stack.isEmpty()){
            Pair<TreeNode, Integer> pair = stack.pop();
            TreeNode node = pair.getKey();
            int currentValue = pair.getValue();
            currentValue = (currentValue << 1) | node.val;
            if(node.left == null && node.right == null) {
                totalSum += currentValue;
            }
            if(node.right != null) {
                stack.push(new Pair<>(node.right, currentValue));
            }
            if(node.left != null) {
                stack.push(new Pair<>(node.left, currentValue));
            }
        }
        return totalSum;
    }
}
/* // Recursion
class Solution {
    int dfs(TreeNode node, int x) { // x is currentValue
        if(node == null) return 0;
        x = (x << 1) | node.val;
        if(node.left == null && node.right == null) return x;
        return dfs(node.left, x) + dfs(node.right, x);
    }

    public int sumRootToLeaf(TreeNode root) {
        return dfs(root, 0);    
    }
}
*/

/*
Approach	Time	Space	Risk
Recursive	O(n)	O(h)	Stack overflow in deep tree
Iterative	O(n)	O(h)	No stack overflow 
*/
