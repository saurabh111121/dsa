package com.dataStructures;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.Queue;

class Optimised {
    public List<List<Integer>> levelOrder(Node root) {
        List<List<Integer>> res = new ArrayList<>();
        Queue<Node> q = new LinkedList<>();
        if(root == null) return res;
        q.add(root);
        while(!q.isEmpty()){
            int size = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i=0; i<size; i++){
                Node node = q.poll();
                if(node.left != null) q.add(node.left);
                if(node.right != null) q.add(node.right);
                level.add(node.data);
            }
            res.add(level);
        }
        return res;
    }

    static void printLevelOrder(Node root) {
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //poll
            Node curr = queue.poll();
            System.out.println(curr.data + " ");

            if(curr.left != null) {
                queue.add(curr.left);
            }
            if(curr.right != null) {
                queue.add(curr.right);
            }
        }
    }

    static void reverseTraversal(Node root) {
        Stack<Node> stack = new Stack<>();
        Queue<Node> queue = new LinkedList<Node>();
        queue.add(root);
        while (!queue.isEmpty()) {
            //poll
            Node curr = queue.poll();
            stack.push(curr);

            if(curr.right != null) {
                queue.add(curr.right);
            }
            if(curr.left != null) {
                queue.add(curr.left);
            }
        }

        // Pop all items from stack one by one and print them
        while (!stack.isEmpty()) {
            Node curr = stack.pop();
            System.out.print(curr.data + " ");
        }
    }
}

public class TreesBFS {
    public static void main(String[] args) {
        Node root = new Node(3);
        root.left = new Node(9);
        root.right = new Node(20);
        root.right.left = new Node(15);
        root.right.right = new Node(7);
        Optimised optimised = new Optimised();
        List<List<Integer>> ans = optimised.levelOrder(root);
        System.out.println(ans);

        optimised.reverseTraversal(root);

    }
}
