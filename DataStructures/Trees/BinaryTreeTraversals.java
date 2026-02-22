package DataStructures.Trees;

/*
===========================================================
Binary Tree Traversal Operations in Java
===========================================================

1) Tree Node Definition
2) Insert Node (Level Order - for testing)
3) Recursive Traversals
   - Preorder
   - Inorder
   - Postorder
4) Iterative Traversals
   - Preorder (Using Stack)
   - Inorder (Using Stack)
   - Postorder (Using Two Stacks)
5) Level Order Traversal (BFS)
6) Testing Examples
===========================================================
*/

import java.util.*;

public class BinaryTreeTraversals {

    TreeNode root;

    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // Insert node (level order) - builds a complete binary tree
    public void insert(int val) {
        TreeNode newNode = new TreeNode(val);

        if(root == null) {
            root = newNode;
            return;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();

            if(temp.left == null) {
                temp.left = newNode;
                return;
            } else queue.offer(temp.left);

            if(temp.right == null) {
                temp.right = newNode;
                return;
            } else queue.offer(temp.right);
        }
    }

    // ================= RECURSIVE TRAVERSALS =================

    public void preorderRecursive(TreeNode node) {
        if(node == null) return;
        System.out.print(node.val + " ");
        preorderRecursive(node.left);
        preorderRecursive(node.right);
    }

    public void inorderRecursive(TreeNode node) {
        if(node == null) return;
        inorderRecursive(node.left);
        System.out.print(node.val + " ");
        inorderRecursive(node.right);
    }

    public void postorderRecursive(TreeNode node) {
        if(node == null) return;
        postorderRecursive(node.left);
        postorderRecursive(node.right);
        System.out.print(node.val + " ");
    }

    // ================= ITERATIVE TRAVERSALS =================

    // Preorder Iterative (Root, Left, Right)
    public void preorderIterative() {
        if(root == null) return;

        Stack<TreeNode> stack = new Stack<>();
        stack.push(root);

        while(!stack.isEmpty()) {
            TreeNode node = stack.pop();
            System.out.print(node.val + " ");

            if(node.right != null) stack.push(node.right);
            if(node.left != null) stack.push(node.left);
        }
    }

    // Inorder Iterative (Left, Root, Right)
    public void inorderIterative() {
        Stack<TreeNode> stack = new Stack<>();
        TreeNode curr = root;

        while(curr != null || !stack.isEmpty()) {
            while(curr != null) {
                stack.push(curr);
                curr = curr.left;
            }

            curr = stack.pop();
            System.out.print(curr.val + " ");
            curr = curr.right;
        }
    }

    // Postorder Iterative (Left, Right, Root)
    // Using Two Stacks
    public void postorderIterative() {
        if(root == null) return;

        Stack<TreeNode> s1 = new Stack<>();
        Stack<TreeNode> s2 = new Stack<>();

        s1.push(root);

        while(!s1.isEmpty()) {
            TreeNode node = s1.pop();
            s2.push(node);

            if(node.left != null) s1.push(node.left);
            if(node.right != null) s1.push(node.right);
        }

        while(!s2.isEmpty()) {
            System.out.print(s2.pop().val + " ");
        }
    }

    // ================= LEVEL ORDER (BFS) =================

    public void levelOrder() {
        if(root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode node = queue.poll();
            System.out.print(node.val + " ");

            if(node.left != null) queue.offer(node.left);
            if(node.right != null) queue.offer(node.right);
        }
    }

    // ================= MAIN =================

    public static void main(String[] args) {
        BinaryTreeTraversals tree = new BinaryTreeTraversals();

        tree.insert(10);
        tree.insert(20);
        tree.insert(30);
        tree.insert(40);
        tree.insert(50);

        System.out.println("Preorder Recursive:");
        tree.preorderRecursive(tree.root);
        System.out.println();

        System.out.println("Preorder Iterative:");
        tree.preorderIterative();
        System.out.println();

        System.out.println("Inorder Recursive:");
        tree.inorderRecursive(tree.root);
        System.out.println();

        System.out.println("Inorder Iterative:");
        tree.inorderIterative();
        System.out.println();

        System.out.println("Postorder Recursive:");
        tree.postorderRecursive(tree.root);
        System.out.println();

        System.out.println("Postorder Iterative:");
        tree.postorderIterative();
        System.out.println();

        System.out.println("Level Order:");
        tree.levelOrder();
        System.out.println();
    }
}

/*
===========================================================
Complexity Analysis
===========================================================

Recursive DFS (Pre/In/Post):
Time  : O(n)
Space : O(h)  (h = tree height)
Worst case: O(n) (skewed tree)
Best case : O(log n)

Iterative DFS:
Time  : O(n)
Space : O(n) (explicit stack)

Level Order (BFS):
Time  : O(n)
Space : O(n) (queue)

===========================================================
*/