package Patterns.TreePatterns;

import java.util.*;

/**
 * Tree Patterns – DFS & BFS Templates
 * =====================================
 * Topics Covered:
 *  1. DFS on Tree – Preorder / Inorder / Postorder (recursive + iterative)
 *  2. BFS on Tree – Level-order (standard + zigzag)
 *  3. Path Sum patterns (root-to-leaf, any path, max path sum)
 *  4. Tree DP – Diameter / Longest Univalue Path
 *  5. LCA (Binary Lifting for O(log n) queries)
 *  6. Subtree / Mirror / Symmetry checks
 *  7. Serialize / Deserialize (BFS string)
 *  8. Flatten Binary Tree to Linked List
 */
public class TreePatterns {

    // ─────────────────────────────────────────────────────────────
    // TreeNode definition
    // ─────────────────────────────────────────────────────────────
    static class TreeNode {
        int val; TreeNode left, right;
        TreeNode(int v) { val = v; }
        TreeNode(int v, TreeNode l, TreeNode r) { val = v; left = l; right = r; }
    }

    // ─────────────────────────────────────────────────────────────
    // 1️⃣  DFS TRAVERSALS (recursive)
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> preorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        preorderHelper(root, res); return res;
    }
    private static void preorderHelper(TreeNode n, List<Integer> res) {
        if (n == null) return;
        res.add(n.val); preorderHelper(n.left, res); preorderHelper(n.right, res);
    }

    public static List<Integer> inorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        inorderHelper(root, res); return res;
    }
    private static void inorderHelper(TreeNode n, List<Integer> res) {
        if (n == null) return;
        inorderHelper(n.left, res); res.add(n.val); inorderHelper(n.right, res);
    }

    public static List<Integer> postorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        postorderHelper(root, res); return res;
    }
    private static void postorderHelper(TreeNode n, List<Integer> res) {
        if (n == null) return;
        postorderHelper(n.left, res); postorderHelper(n.right, res); res.add(n.val);
    }

    /** Iterative inorder using Morris traversal (O(1) space) */
    public static List<Integer> morrisInorder(TreeNode root) {
        List<Integer> res = new ArrayList<>();
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left == null) { res.add(cur.val); cur = cur.right; }
            else {
                TreeNode pred = cur.left;
                while (pred.right != null && pred.right != cur) pred = pred.right;
                if (pred.right == null) { pred.right = cur; cur = cur.left; }
                else { pred.right = null; res.add(cur.val); cur = cur.right; }
            }
        }
        return res;
    }

    // ─────────────────────────────────────────────────────────────
    // 2️⃣  BFS – LEVEL ORDER
    // ─────────────────────────────────────────────────────────────
    public static List<List<Integer>> levelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>(); q.add(root);
        while (!q.isEmpty()) {
            List<Integer> level = new ArrayList<>();
            for (int sz = q.size(); sz > 0; sz--) {
                TreeNode n = q.poll(); level.add(n.val);
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
            result.add(level);
        }
        return result;
    }

    /** Zigzag level order */
    public static List<List<Integer>> zigzagLevelOrder(TreeNode root) {
        List<List<Integer>> result = new ArrayList<>();
        if (root == null) return result;
        Queue<TreeNode> q = new LinkedList<>(); q.add(root); boolean leftToRight = true;
        while (!q.isEmpty()) {
            int sz = q.size(); int[] level = new int[sz];
            for (int i = 0; i < sz; i++) {
                TreeNode n = q.poll();
                level[leftToRight ? i : sz - 1 - i] = n.val;
                if (n.left != null) q.add(n.left);
                if (n.right != null) q.add(n.right);
            }
            List<Integer> levelList = new ArrayList<>();
            for (int v : level) levelList.add(v);
            result.add(levelList); leftToRight = !leftToRight;
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // 3️⃣  PATH SUM PATTERNS
    // ─────────────────────────────────────────────────────────────
    /** Has root-to-leaf path summing to target? */
    public static boolean hasPathSum(TreeNode root, int target) {
        if (root == null) return false;
        if (root.left == null && root.right == null) return root.val == target;
        return hasPathSum(root.left, target - root.val) || hasPathSum(root.right, target - root.val);
    }

    /** All root-to-leaf paths with target sum */
    public static List<List<Integer>> pathSumAll(TreeNode root, int target) {
        List<List<Integer>> result = new ArrayList<>();
        pathDfs(root, target, new ArrayList<>(), result);
        return result;
    }
    private static void pathDfs(TreeNode n, int rem, List<Integer> path, List<List<Integer>> res) {
        if (n == null) return;
        path.add(n.val);
        if (n.left == null && n.right == null && rem == n.val) res.add(new ArrayList<>(path));
        pathDfs(n.left, rem - n.val, path, res);
        pathDfs(n.right, rem - n.val, path, res);
        path.remove(path.size() - 1);
    }

    /** Maximum path sum (any path, not necessarily root-to-leaf) – LeetCode 124 */
    private static int maxPathAns;
    public static int maxPathSum(TreeNode root) {
        maxPathAns = Integer.MIN_VALUE;
        maxPathGain(root);
        return maxPathAns;
    }
    private static int maxPathGain(TreeNode n) {
        if (n == null) return 0;
        int left  = Math.max(maxPathGain(n.left),  0);
        int right = Math.max(maxPathGain(n.right), 0);
        maxPathAns = Math.max(maxPathAns, n.val + left + right);
        return n.val + Math.max(left, right);
    }

    // ─────────────────────────────────────────────────────────────
    // 4️⃣  TREE DP – DIAMETER & LONGEST UNIVALUE PATH
    // ─────────────────────────────────────────────────────────────
    private static int diameterAns;
    public static int diameter(TreeNode root) {
        diameterAns = 0; diameterDfs(root); return diameterAns;
    }
    private static int diameterDfs(TreeNode n) {
        if (n == null) return 0;
        int l = diameterDfs(n.left), r = diameterDfs(n.right);
        diameterAns = Math.max(diameterAns, l + r);
        return 1 + Math.max(l, r);
    }

    private static int univalAns;
    public static int longestUnivaluePath(TreeNode root) {
        univalAns = 0; univalDfs(root); return univalAns;
    }
    private static int univalDfs(TreeNode n) {
        if (n == null) return 0;
        int l = univalDfs(n.left), r = univalDfs(n.right);
        int pl = (n.left  != null && n.left.val  == n.val) ? l + 1 : 0;
        int pr = (n.right != null && n.right.val == n.val) ? r + 1 : 0;
        univalAns = Math.max(univalAns, pl + pr);
        return Math.max(pl, pr);
    }

    // ─────────────────────────────────────────────────────────────
    // 5️⃣  LCA (lowest common ancestor) – O(n) per query
    // ─────────────────────────────────────────────────────────────
    public static TreeNode lca(TreeNode root, TreeNode p, TreeNode q) {
        if (root == null || root == p || root == q) return root;
        TreeNode left  = lca(root.left,  p, q);
        TreeNode right = lca(root.right, p, q);
        if (left != null && right != null) return root;
        return left != null ? left : right;
    }

    // ─────────────────────────────────────────────────────────────
    // 6️⃣  SYMMETRY / SUBTREE CHECKS
    // ─────────────────────────────────────────────────────────────
    public static boolean isSymmetric(TreeNode root) {
        return root == null || symCheck(root.left, root.right);
    }
    private static boolean symCheck(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val && symCheck(a.left, b.right) && symCheck(a.right, b.left);
    }

    public static boolean isSubtree(TreeNode root, TreeNode sub) {
        if (root == null) return false;
        if (isSameTree(root, sub)) return true;
        return isSubtree(root.left, sub) || isSubtree(root.right, sub);
    }
    private static boolean isSameTree(TreeNode a, TreeNode b) {
        if (a == null && b == null) return true;
        if (a == null || b == null) return false;
        return a.val == b.val && isSameTree(a.left, b.left) && isSameTree(a.right, b.right);
    }

    // ─────────────────────────────────────────────────────────────
    // 7️⃣  SERIALIZE / DESERIALIZE (BFS)
    // ─────────────────────────────────────────────────────────────
    public static String serialize(TreeNode root) {
        if (root == null) return "null";
        StringBuilder sb = new StringBuilder();
        Queue<TreeNode> q = new LinkedList<>(); q.add(root);
        while (!q.isEmpty()) {
            TreeNode n = q.poll();
            if (n == null) { sb.append("null,"); continue; }
            sb.append(n.val).append(",");
            q.add(n.left); q.add(n.right);
        }
        return sb.toString();
    }

    public static TreeNode deserialize(String data) {
        String[] vals = data.split(",");
        if (vals[0].equals("null")) return null;
        TreeNode root = new TreeNode(Integer.parseInt(vals[0]));
        Queue<TreeNode> q = new LinkedList<>(); q.add(root);
        int i = 1;
        while (!q.isEmpty() && i < vals.length) {
            TreeNode n = q.poll();
            if (!vals[i].equals("null")) { n.left = new TreeNode(Integer.parseInt(vals[i])); q.add(n.left); }
            i++;
            if (i < vals.length && !vals[i].equals("null")) { n.right = new TreeNode(Integer.parseInt(vals[i])); q.add(n.right); }
            i++;
        }
        return root;
    }

    // ─────────────────────────────────────────────────────────────
    // 8️⃣  FLATTEN TO LINKED LIST  (LeetCode 114, in-place)
    // ─────────────────────────────────────────────────────────────
    public static void flatten(TreeNode root) {
        TreeNode cur = root;
        while (cur != null) {
            if (cur.left != null) {
                TreeNode pred = cur.left;
                while (pred.right != null) pred = pred.right;
                pred.right = cur.right;
                cur.right = cur.left;
                cur.left = null;
            }
            cur = cur.right;
        }
    }

    // ─────────────────────────────────────────────────────────────
    // MAIN DEMO
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        //        1
        //       / \
        //      2   3
        //     / \   \
        //    4   5   6
        TreeNode root = new TreeNode(1,
            new TreeNode(2, new TreeNode(4), new TreeNode(5)),
            new TreeNode(3, null, new TreeNode(6)));

        System.out.println("Preorder:  " + preorder(root));   // [1,2,4,5,3,6]
        System.out.println("Inorder:   " + inorder(root));    // [4,2,5,1,3,6]
        System.out.println("Postorder: " + postorder(root));  // [4,5,2,6,3,1]
        System.out.println("Morris Inorder: " + morrisInorder(root));

        System.out.println("\nLevel order: " + levelOrder(root));
        System.out.println("Zigzag:      " + zigzagLevelOrder(root));

        System.out.println("\nHas path sum 7? " + hasPathSum(root, 7));  // 1+2+4=7 true
        System.out.println("All paths sum=7: " + pathSumAll(root, 7));
        System.out.println("Max path sum: " + maxPathSum(root));         // 16: 4+2+5+1+3+6? No, 1+3+6=10 or 2+5=7.. 4+2+1+3+6=16

        System.out.println("\nDiameter: " + diameter(root));             // 4 (4-2-1-3-6)

        System.out.println("\nIs symmetric: " + isSymmetric(root));      // false
        TreeNode sym = new TreeNode(1,
            new TreeNode(2, new TreeNode(3), new TreeNode(4)),
            new TreeNode(2, new TreeNode(4), new TreeNode(3)));
        System.out.println("Is symmetric (mirror): " + isSymmetric(sym)); // true

        System.out.println("\nSerialize: " + serialize(root));
        TreeNode restored = deserialize(serialize(root));
        System.out.println("Restored inorder: " + inorder(restored));

        System.out.println("\nLCA(4,5) = " + lca(root, root.left.left, root.left.right).val); // 2
    }
}

/*
 * ┌──────────────────────────────────┬──────────┬──────────┐
 * │ Pattern                          │ Time     │ Space    │
 * ├──────────────────────────────────┼──────────┼──────────┤
 * │ DFS traversals (recursive)       │ O(n)     │ O(h)     │
 * │ Morris Inorder                   │ O(n)     │ O(1)     │
 * │ BFS Level Order                  │ O(n)     │ O(w)     │
 * │ Path Sum                         │ O(n)     │ O(h)     │
 * │ Max Path Sum                     │ O(n)     │ O(h)     │
 * │ Diameter / Univalue Path         │ O(n)     │ O(h)     │
 * │ LCA                              │ O(n)     │ O(h)     │
 * │ Symmetric / Subtree              │ O(n)     │ O(h)     │
 * │ Serialize / Deserialize          │ O(n)     │ O(n)     │
 * │ Flatten to Linked List           │ O(n)     │ O(1)     │
 * └──────────────────────────────────┴──────────┴──────────┘
 */
