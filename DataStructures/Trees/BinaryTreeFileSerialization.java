package DataStructures.Trees;

import java.io.*;
import java.util.*;

/*
===========================================================
Binary Tree Serialization / Deserialization to/from File
===========================================================

1) Tree Node Definition
2) Binary Tree Insertion (Level Order)
3) Serialization to File (Preorder with null markers)
4) Deserialization from File
5) Inorder Traversal (for verification)
6) Testing Examples
===========================================================
*/

public class BinaryTreeFileSerialization {

    // =========================
    // Tree Node Definition
    // =========================
    static class TreeNode {
        int val;
        TreeNode left, right;

        TreeNode(int val) { this.val = val; }
    }

    // =====================================================
    // Binary Tree Level Order Insertion
    // =====================================================
    public static TreeNode insertLevelOrder(TreeNode root, int val) {
        TreeNode newNode = new TreeNode(val);
        if(root == null) return newNode;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if(temp.left == null) {
                temp.left = newNode;
                return root;
            } else queue.offer(temp.left);

            if(temp.right == null) {
                temp.right = newNode;
                return root;
            } else queue.offer(temp.right);
        }
        return root;
    }

    // =====================================================
    // Serialization to File (Preorder Traversal)
    // =====================================================
    public static void serialize(TreeNode root, String fileName) throws IOException {
        BufferedWriter writer = new BufferedWriter(new FileWriter(fileName));
        serializeHelper(root, writer);
        writer.close();
    }

    private static void serializeHelper(TreeNode node, BufferedWriter writer) throws IOException {
        if(node == null) {
            writer.write("# ");
            return;
        }
        writer.write(node.val + " ");
        serializeHelper(node.left, writer);
        serializeHelper(node.right, writer);
    }

    // =====================================================
    // Deserialization from File
    // =====================================================
    public static TreeNode deserialize(String fileName) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(fileName));
        String data = reader.readLine();
        reader.close();

        if(data == null || data.isEmpty()) return null;

        String[] nodes = data.split("\\s+");
        Queue<String> queue = new LinkedList<>(Arrays.asList(nodes));

        return deserializeHelper(queue);
    }

    private static TreeNode deserializeHelper(Queue<String> queue) {
        if(queue.isEmpty()) return null;

        String val = queue.poll();
        if(val.equals("#")) return null;

        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);

        return node;
    }

    // =====================================================
    // Inorder Traversal (for verification)
    // =====================================================
    public static void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // MAIN METHOD (TESTING SERIALIZATION / DESERIALIZATION)
    // =====================================================
    public static void main(String[] args) {
        try {
            TreeNode root = null;
            int[] values = {1, 2, 3, 4, 5, 6, 7};

            for(int val : values) root = insertLevelOrder(root, val);

            System.out.println("Original Tree (Inorder):");
            inorder(root);
            System.out.println();

            // Serialize to file
            String fileName = "tree_data.txt";
            serialize(root, fileName);
            System.out.println("Tree serialized to " + fileName);

            // Deserialize from file
            TreeNode deserializedRoot = deserialize(fileName);
            System.out.println("Deserialized Tree (Inorder):");
            inorder(deserializedRoot);
            System.out.println();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

/*
===========================================================
Time & Space Complexity
===========================================================

Serialization:
    Time: O(n) - visits each node once
    Space: O(h) - recursion stack, h = height of tree

Deserialization:
    Time: O(n) - reads all nodes
    Space: O(n) - queue for splitting, recursion stack O(h)

===========================================================
*/