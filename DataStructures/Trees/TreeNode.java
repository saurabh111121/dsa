package DataStructures.Trees;

public class TreeNode {
    int val;
    TreeNode left;
    TreeNode right;

    TreeNode() {};

    TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }
}


Optional / Advanced Topics (Pending / Not yet implemented)
Threaded BST / Morris Traversal – space-optimized inorder traversal
AVL / Red-Black Tree operations – self-balancing trees
Kth Smallest / Largest in BST
BST Validation – check if a tree is a valid BST (recursive & iterative)
Serialize/Deserialize to/from file – persistent storage
BST Iterator – next()/hasNext() like LeetCode BSTIterator
Advanced Path Queries – like path sum equals target with multiple paths
Vertical / Horizontal Sum – sum of nodes per vertical/horizontal level
Zigzag / Spiral Traversal – special BFS traversals