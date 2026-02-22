package DataStructures.Trees;

import java.util.*;

/*
===========================================================
Binary Tree Store / Load (LeetCode Style)
===========================================================

1) Construct Tree from Level-order List (with nulls)
2) Serialize Tree to Level-order List (with nulls)
3) Inorder Traversal for Verification
4) Testing Examples

===========================================================
*/

public class BinaryTreeLeetCodeStyle {

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
    // 1️⃣ BUILD TREE FROM LEETCODE-STYLE LIST (LEVEL ORDER)
    // =====================================================
    public static TreeNode buildTreeFromList(List<Integer> list) {
        if (list == null || list.isEmpty() || list.get(0) == null) return null;

        TreeNode root = new TreeNode(list.get(0));
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        int i = 1;
        while (i < list.size()) {
            TreeNode current = queue.poll();

            if (i < list.size() && list.get(i) != null) {
                current.left = new TreeNode(list.get(i));
                queue.offer(current.left);
            }
            i++;

            if (i < list.size() && list.get(i) != null) {
                current.right = new TreeNode(list.get(i));
                queue.offer(current.right);
            }
            i++;
        }

        return root;
    }

    // =====================================================
    // 2️⃣ SERIALIZE TREE TO LEETCODE-STYLE LIST (LEVEL ORDER)
    // =====================================================
    public static List<Integer> serializeToList(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if (root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while (!queue.isEmpty()) {
            TreeNode node = queue.poll();
            if (node == null) {
                result.add(null);
            } else {
                result.add(node.val);
                queue.offer(node.left);
                queue.offer(node.right);
            }
        }

        // Remove trailing nulls for clean representation
        int lastIndex = result.size() - 1;
        while (lastIndex >= 0 && result.get(lastIndex) == null) {
            result.remove(lastIndex);
            lastIndex--;
        }

        return result;
    }

    // =====================================================
    // INORDER TRAVERSAL FOR VERIFICATION
    // =====================================================
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // MAIN METHOD (TESTING STORE / LOAD)
    // =====================================================
    public static void main(String[] args) {
        List<Integer> inputList = Arrays.asList(1, 2, 3, null, 4, 5, 6);

        System.out.println("Input List (LeetCode Style): " + inputList);

        TreeNode root = buildTreeFromList(inputList);
        System.out.println("Inorder Traversal of Constructed Tree:");
        inorder(root);
        System.out.println("\n");

        List<Integer> serializedList = serializeToList(root);
        System.out.println("Serialized Tree to List:");
        System.out.println(serializedList);
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Build Tree from List:
   - Time:  O(n)  (visit each node once)
   - Space: O(n)  (queue + tree nodes)

2) Serialize Tree to List:
   - Time:  O(n)
   - Space: O(n)  (queue + list)

===========================================================
*/