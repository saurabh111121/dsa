package DataStructures.Trees;

/*
===========================================================
AVL Tree Operations - Self-Balancing BST
===========================================================

1) Node Definition
2) Insert (with rotations)
3) Delete (with rotations)
4) Inorder Traversal
5) Get Height & Balance Factor
6) Testing Examples
===========================================================
*/

public class AVLTree {

    // =========================
    // Tree Node Definition
    // =========================
    static class TreeNode {
        int val;
        int height;
        TreeNode left, right;

        TreeNode(int val) {
            this.val = val;
            this.height = 1;
        }
    }

    // =====================================================
    // HEIGHT
    // =====================================================
    private static int height(TreeNode node) {
        return node == null ? 0 : node.height;
    }

    // =====================================================
    // BALANCE FACTOR
    // =====================================================
    private static int getBalance(TreeNode node) {
        return node == null ? 0 : height(node.left) - height(node.right);
    }

    // =====================================================
    // RIGHT ROTATION
    // =====================================================
    private static TreeNode rightRotate(TreeNode y) {
        TreeNode x = y.left;
        TreeNode T2 = x.right;

        x.right = y;
        y.left = T2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    // =====================================================
    // LEFT ROTATION
    // =====================================================
    private static TreeNode leftRotate(TreeNode x) {
        TreeNode y = x.right;
        TreeNode T2 = y.left;

        y.left = x;
        x.right = T2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    // =====================================================
    // INSERT NODE
    // =====================================================
    public static TreeNode insert(TreeNode node, int val) {
        if (node == null) return new TreeNode(val);

        if (val < node.val) node.left = insert(node.left, val);
        else if (val > node.val) node.right = insert(node.right, val);
        else return node; // no duplicates

        node.height = 1 + Math.max(height(node.left), height(node.right));
        int balance = getBalance(node);

        // Left Left Case
        if (balance > 1 && val < node.left.val) return rightRotate(node);

        // Right Right Case
        if (balance < -1 && val > node.right.val) return leftRotate(node);

        // Left Right Case
        if (balance > 1 && val > node.left.val) {
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // Right Left Case
        if (balance < -1 && val < node.right.val) {
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    // =====================================================
    // MIN VALUE NODE
    // =====================================================
    private static TreeNode minValueNode(TreeNode node) {
        TreeNode current = node;
        while (current.left != null) current = current.left;
        return current;
    }

    // =====================================================
    // DELETE NODE
    // =====================================================
    public static TreeNode delete(TreeNode root, int val) {
        if (root == null) return root;

        if (val < root.val) root.left = delete(root.left, val);
        else if (val > root.val) root.right = delete(root.right, val);
        else {
            if (root.left == null || root.right == null) {
                TreeNode temp = (root.left != null) ? root.left : root.right;
                if (temp == null) return null;
                else root = temp;
            } else {
                TreeNode temp = minValueNode(root.right);
                root.val = temp.val;
                root.right = delete(root.right, temp.val);
            }
        }

        root.height = Math.max(height(root.left), height(root.right)) + 1;
        int balance = getBalance(root);

        // Left Left Case
        if (balance > 1 && getBalance(root.left) >= 0) return rightRotate(root);

        // Left Right Case
        if (balance > 1 && getBalance(root.left) < 0) {
            root.left = leftRotate(root.left);
            return rightRotate(root);
        }

        // Right Right Case
        if (balance < -1 && getBalance(root.right) <= 0) return leftRotate(root);

        // Right Left Case
        if (balance < -1 && getBalance(root.right) > 0) {
            root.right = rightRotate(root.right);
            return leftRotate(root);
        }

        return root;
    }

    // =====================================================
    // INORDER TRAVERSAL
    // =====================================================
    public static void inorder(TreeNode root) {
        if (root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // MAIN METHOD (TESTING AVL TREE)
    // =====================================================
    public static void main(String[] args) {
        TreeNode root = null;
        int[] values = {10, 20, 30, 40, 50, 25};

        for (int val : values) root = insert(root, val);

        System.out.println("Inorder Traversal of AVL Tree:");
        inorder(root);
        System.out.println();

        root = delete(root, 40);
        System.out.println("After deleting 40:");
        inorder(root);
        System.out.println();

        root = delete(root, 25);
        System.out.println("After deleting 25:");
        inorder(root);
        System.out.println();
    }
}

/*
===========================================================
Time & Space Complexity
===========================================================

Insert/Delete: 
   Time:  O(log n)   (height of AVL tree is O(log n))
   Space: O(log n)   (recursion stack)

Rotations:
   Time: O(1)
   Space: O(1)

Inorder Traversal:
   Time: O(n)
   Space: O(h) recursion stack

===========================================================
*/