package com.dataStructures;

class Node {
    int data;
    Node left;
    Node right;
    Node() {}
    Node(int val) { this.data = val; }
    Node(int val, Node left, Node right) {
        this.data = val;
        this.left = left;
        this.right = right;
    }
}


class Trees {

    static void morrisTraversal(Node root){
        while (root != null) {

            // If left child is null, print the current node
            // data. Move to right child.
            if (root.left == null) {
                System.out.print(root.data + " ");
                root = root.right;
            }
            else {

                // Find inorder predecessor
                Node current = root.left;
                while (current.right != null && current.right != root){
                    current = current.right;
                }

                // If the right child of inorder predecessor
                // already points to this node
                if (current.right == root) {
                    current.right = null;
                    root = root.right;
                }

                // If right child doesn't point to this
                // node, then print this node and make right
                // child point to this node
                else {
                    System.out.print(root.data + " ");
                    current.right = root;
                    root = root.left;
                }
            }
        }

//        static void heightOfaBinaryTree(Node root) {
//            if(root == null) {
//                return 0;
//            }
//            int leftHeight = heightOfaBinaryTree(root.left);
//            int rightHeight = heightOfaBinaryTree(root.right);
//
//            return Math.max(leftHeight, rightHeight) + 1;
//        }
    }

    public static void main(String[] args) {
        Node root = new Node(1);
        root.left = new Node(2);
        root.right = new Node(3);
        root.left.left = new Node(4);
        root.left.right = new Node(5);
        root.right.left = new Node(6);
        root.right.right = new Node(7);
        morrisTraversal(root);
    }
}

