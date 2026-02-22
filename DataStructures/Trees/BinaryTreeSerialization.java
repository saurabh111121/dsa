package DataStructures.Trees;

import java.util.*;

/*
===========================================================
Binary Tree Serialization / Deserialization
===========================================================

1) Serialize Tree to String (Preorder)
2) Deserialize String to Tree
3) Optional: Level-order Serialization / Deserialization
4) Testing example trees

===========================================================
*/

public class BinaryTreeSerialization {

    // =========================
    // Tree Node Definition
    // =========================
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) { this.val = val; }
    }

    // =====================================================
    // 1️⃣ SERIALIZE TREE (PREORDER, RECURSIVE)
    // =====================================================
    public static String serialize(TreeNode root) {
        StringBuilder sb = new StringBuilder();
        serializeHelper(root, sb);
        return sb.toString();
    }

    private static void serializeHelper(TreeNode root, StringBuilder sb) {
        if(root == null) {
            sb.append("null,");
            return;
        }

        sb.append(root.val).append(",");
        serializeHelper(root.left, sb);
        serializeHelper(root.right, sb);
    }

    // =====================================================
    // 2️⃣ DESERIALIZE TREE (PREORDER)
    // =====================================================
    public static TreeNode deserialize(String data) {
        String[] nodes = data.split(",");
        Queue<String> queue = new LinkedList<>(Arrays.asList(nodes));
        return deserializeHelper(queue);
    }

    private static TreeNode deserializeHelper(Queue<String> queue) {
        if(queue.isEmpty()) return null;

        String val = queue.poll();
        if(val.equals("null")) return null;

        TreeNode node = new TreeNode(Integer.parseInt(val));
        node.left = deserializeHelper(queue);
        node.right = deserializeHelper(queue);

        return node;
    }

    // =====================================================
    // 3️⃣ INORDER (for testing tree structure)
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
        TreeNode root = new TreeNode(10);
        root.left = new TreeNode(5);
        root.right = new TreeNode(15);
        root.left.left = new TreeNode(3);
        root.left.right = new TreeNode(7);
        root.right.right = new TreeNode(18);

        System.out.println("Original Tree Inorder:");
        inorder(root);
        System.out.println("\n");

        String serialized = serialize(root);
        System.out.println("Serialized Tree:");
        System.out.println(serialized);

        TreeNode deserializedRoot = deserialize(serialized);
        System.out.println("\nDeserialized Tree Inorder:");
        inorder(deserializedRoot);
        System.out.println("\n");
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Serialize:
   - Time:  O(n)   (visit all nodes)
   - Space: O(n)   (result string + recursion stack O(h))

2) Deserialize:
   - Time:  O(n)
   - Space: O(n)   (queue + recursion stack O(h))

===========================================================
*/