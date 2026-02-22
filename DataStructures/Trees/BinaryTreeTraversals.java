package DataStructures.Trees;

/*
===========================================================
Binary Tree Traversal Operations in Java
===========================================================

1) Tree Node Definition
2) Insert Node (for testing)
3) Preorder Traversal (Root, Left, Right)
4) Inorder Traversal (Left, Root, Right)
5) Postorder Traversal (Left, Right, Root)
6) Level Order Traversal (Breadth First)
7) Testing Examples
===========================================================
*/

import java.util.LinkedList;
import java.util.Queue;

class TreeNode {
    int val;
    TreeNode left, right;

    TreeNode(int val) {
        this.val = val;
        left = right = null;
    }
}

public class BinaryTreeTraversals {

    TreeNode root;

    public BinaryTreeTraversals() {
        root = null;
    }

    // Insert node (level order) for testing
    public void insert(int val) {
        TreeNode newNode = new TreeNode(val);
        if (root == null) {
            root = newNode;
            return;
        }
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            if (temp.left == null) {
                temp.left = newNode;
                return;
            } else q.add(temp.left);
            if (temp.right == null) {
                temp.right = newNode;
                return;
            } else q.add(temp.right);
        }
    }

    // Preorder Traversal: Root, Left, Right
    public void preorder(TreeNode node) {
        if (node != null) {
            System.out.print(node.val + " ");
            preorder(node.left);
            preorder(node.right);
        }
    }

    // Inorder Traversal: Left, Root, Right
    public void inorder(TreeNode node) {
        if (node != null) {
            inorder(node.left);
            System.out.print(node.val + " ");
            inorder(node.right);
        }
    }

    // Postorder Traversal: Left, Right, Root
    public void postorder(TreeNode node) {
        if (node != null) {
            postorder(node.left);
            postorder(node.right);
            System.out.print(node.val + " ");
        }
    }

    // Level Order Traversal (Breadth First)
    public void levelOrder() {
        if (root == null) return;
        Queue<TreeNode> q = new LinkedList<>();
        q.add(root);
        while (!q.isEmpty()) {
            TreeNode temp = q.poll();
            System.out.print(temp.val + " ");
            if (temp.left != null) q.add(temp.left);
            if (temp.right != null) q.add(temp.right);
        }
    }

    public static void main(String[] args) {
        BinaryTreeTraversals tree = new BinaryTreeTraversals();
        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);

        System.out.println("Preorder Traversal:");
        tree.preorder(tree.root);
        System.out.println();

        System.out.println("Inorder Traversal:");
        tree.inorder(tree.root);
        System.out.println();

        System.out.println("Postorder Traversal:");
        tree.postorder(tree.root);
        System.out.println();

        System.out.println("Level Order Traversal:");
        tree.levelOrder();
        System.out.println();
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Preorder, Inorder, Postorder (DFS): O(n) time, O(h) space for recursion (h = tree height)
Level Order (BFS): O(n) time, O(n) space (queue)
===========================================================
*/
