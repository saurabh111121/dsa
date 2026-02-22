package DataStructures.Trees;

import java.util.*;

/*
===========================================================
Binary Tree Views
===========================================================

1) Top View
2) Bottom View
3) Left View
4) Right View
5) Diagonal View
6) Testing Examples

===========================================================
*/

public class BinaryTreeViews {

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
    // INORDER (for testing)
    // =====================================================
    public static void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // 1️⃣ TOP VIEW
    // =====================================================
    public static List<Integer> topView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Map<Integer, Integer> hdNodeMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while(!queue.isEmpty()) {
            Pair p = queue.poll();
            if(!hdNodeMap.containsKey(p.hd)) {
                hdNodeMap.put(p.hd, p.node.val);
            }

            if(p.node.left != null) queue.offer(new Pair(p.node.left, p.hd - 1));
            if(p.node.right != null) queue.offer(new Pair(p.node.right, p.hd + 1));
        }

        result.addAll(hdNodeMap.values());
        return result;
    }

    // =====================================================
    // 2️⃣ BOTTOM VIEW
    // =====================================================
    public static List<Integer> bottomView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Map<Integer, Integer> hdNodeMap = new TreeMap<>();
        Queue<Pair> queue = new LinkedList<>();
        queue.offer(new Pair(root, 0));

        while(!queue.isEmpty()) {
            Pair p = queue.poll();
            hdNodeMap.put(p.hd, p.node.val);

            if(p.node.left != null) queue.offer(new Pair(p.node.left, p.hd - 1));
            if(p.node.right != null) queue.offer(new Pair(p.node.right, p.hd + 1));
        }

        result.addAll(hdNodeMap.values());
        return result;
    }

    // =====================================================
    // 3️⃣ LEFT VIEW
    // =====================================================
    public static List<Integer> leftView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(i == 0) result.add(node.val);

                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }

    // =====================================================
    // 4️⃣ RIGHT VIEW
    // =====================================================
    public static List<Integer> rightView(TreeNode root) {
        List<Integer> result = new ArrayList<>();
        if(root == null) return result;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            int size = queue.size();
            for(int i = 0; i < size; i++) {
                TreeNode node = queue.poll();
                if(i == size - 1) result.add(node.val);

                if(node.left != null) queue.offer(node.left);
                if(node.right != null) queue.offer(node.right);
            }
        }
        return result;
    }

    // =====================================================
    // 5️⃣ DIAGONAL VIEW
    // =====================================================
    public static List<List<Integer>> diagonalView(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if(root == null) return result;

        Map<Integer, List<Integer>> diagonalMap = new TreeMap<>();
        diagonalHelper(root, 0, diagonalMap);

        for(List<Integer> list : diagonalMap.values()) {
            result.add(list);
        }
        return result;
    }

    private static void diagonalHelper(TreeNode node, int d, Map<Integer, List<Integer>> map) {
        if(node == null) return;

        map.computeIfAbsent(d, k -> new ArrayList<>()).add(node.val);

        diagonalHelper(node.left, d + 1, map);
        diagonalHelper(node.right, d, map);
    }

    // =====================================================
    // PAIR CLASS FOR HD BFS
    // =====================================================
    static class Pair {
        TreeNode node;
        int hd;
        Pair(TreeNode node, int hd) { this.node = node; this.hd = hd; }
    }

    // =====================================================
    // MAIN METHOD (TESTING ALL VIEWS)
    // =====================================================
    public static void main(String[] args) {
        TreeNode root = new TreeNode(1);
        root.left = new TreeNode(2);
        root.right = new TreeNode(3);
        root.left.left = new TreeNode(4);
        root.left.right = new TreeNode(5);
        root.right.left = new TreeNode(6);
        root.right.right = new TreeNode(7);
        root.left.right.left = new TreeNode(8);
        root.left.right.right = new TreeNode(9);

        System.out.println("Inorder Traversal:");
        inorder(root);
        System.out.println("\n");

        System.out.println("Top View: " + topView(root));
        System.out.println("Bottom View: " + bottomView(root));
        System.out.println("Left View: " + leftView(root));
        System.out.println("Right View: " + rightView(root));
        System.out.println("Diagonal View: " + diagonalView(root));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) Top / Bottom View:
   - Time:  O(n)
   - Space: O(n) (queue + map)

2) Left / Right View:
   - Time:  O(n)
   - Space: O(n) (queue)

3) Diagonal View:
   - Time:  O(n)
   - Space: O(n) (map + recursion stack)

===========================================================
*/