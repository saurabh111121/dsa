package DataStructures.Trees;

import java.util.LinkedList;
import java.util.Queue;

/*
===========================================================
Binary Tree Deletion Implementations Included in File
===========================================================

1) BST Deletion
   - Recursive
   - Iterative

2) Normal Binary Tree
   - Level Order Deletion (Delete first matching node)

3) Special Binary Tree Deletions
   - Delete Left Child of a Given Node
   - Delete Right Child of a Given Node
   - Delete Root
   - Delete at Specific Level

===========================================================
*/

public class BinaryTreeAllDeletions {

    // =========================
    // Tree Node Definition
    // =========================
    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int val) {
            this.val = val;
        }
    }

    // =====================================================
    // 1️⃣ BST DELETION - RECURSIVE
    // =====================================================
    public static TreeNode deleteBSTRecursive(TreeNode root, int val) {
        if(root == null) return null;

        if(val < root.val) {
            root.left = deleteBSTRecursive(root.left, val);
        } else if(val > root.val) {
            root.right = deleteBSTRecursive(root.right, val);
        } else {
            // Node found
            if(root.left == null) return root.right;
            if(root.right == null) return root.left;

            // Node with 2 children: get inorder successor
            TreeNode successor = findMin(root.right);
            root.val = successor.val;
            root.right = deleteBSTRecursive(root.right, successor.val);
        }
        return root;
    }

    private static TreeNode findMin(TreeNode root) {
        while(root.left != null) root = root.left;
        return root;
    }

    // =====================================================
    // 2️⃣ BST DELETION - ITERATIVE
    // =====================================================
    public static TreeNode deleteBSTIterative(TreeNode root, int val) {
        TreeNode parent = null;
        TreeNode current = root;

        // Find node to delete
        while(current != null && current.val != val) {
            parent = current;
            if(val < current.val) current = current.left;
            else current = current.right;
        }

        if(current == null) return root; // Not found

        // Node with two children
        if(current.left != null && current.right != null) {
            TreeNode successorParent = current;
            TreeNode successor = current.right;
            while(successor.left != null) {
                successorParent = successor;
                successor = successor.left;
            }
            current.val = successor.val;
            current = successor;
            parent = successorParent;
        }

        // Node with 0 or 1 child
        TreeNode child = (current.left != null) ? current.left : current.right;

        if(parent == null) {
            return child; // deleting root
        } else if(parent.left == current) {
            parent.left = child;
        } else {
            parent.right = child;
        }

        return root;
    }

    // =====================================================
    // 3️⃣ NORMAL BINARY TREE DELETION (LEVEL ORDER)
    // =====================================================
    public static TreeNode deleteLevelOrder(TreeNode root, int val) {
        if(root == null) return null;
        if(root.left == null && root.right == null) {
            return (root.val == val) ? null : root;
        }

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        TreeNode keyNode = null;
        TreeNode last = null;

        while(!queue.isEmpty()) {
            last = queue.poll();

            if(last.val == val) keyNode = last;

            if(last.left != null) queue.offer(last.left);
            if(last.right != null) queue.offer(last.right);
        }

        if(keyNode != null) {
            keyNode.val = last.val; // Replace with last node
            deleteDeepest(root, last);
        }

        return root;
    }

    private static void deleteDeepest(TreeNode root, TreeNode delNode) {
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            if(temp.left != null) {
                if(temp.left == delNode) {
                    temp.left = null;
                    return;
                } else queue.offer(temp.left);
            }
            if(temp.right != null) {
                if(temp.right == delNode) {
                    temp.right = null;
                    return;
                } else queue.offer(temp.right);
            }
        }
    }

    // =====================================================
    // 4️⃣ DELETE LEFT CHILD OF GIVEN NODE
    // =====================================================
    public static boolean deleteLeft(TreeNode root, int parentVal) {
        if(root == null) return false;

        if(root.val == parentVal && root.left != null) {
            root.left = null;
            return true;
        }

        return deleteLeft(root.left, parentVal) || deleteLeft(root.right, parentVal);
    }

    // =====================================================
    // 5️⃣ DELETE RIGHT CHILD OF GIVEN NODE
    // =====================================================
    public static boolean deleteRight(TreeNode root, int parentVal) {
        if(root == null) return false;

        if(root.val == parentVal && root.right != null) {
            root.right = null;
            return true;
        }

        return deleteRight(root.left, parentVal) || deleteRight(root.right, parentVal);
    }

    // =====================================================
    // 6️⃣ DELETE ROOT
    // =====================================================
    public static TreeNode deleteRoot(TreeNode root) {
        if(root == null) return null;
        if(root.left == null) return root.right;
        if(root.right == null) return root.left;

        // Replace root with inorder successor
        TreeNode successor = findMin(root.right);
        root.val = successor.val;
        root.right = deleteBSTRecursive(root.right, successor.val);
        return root;
    }

    // =====================================================
    // 7️⃣ DELETE AT SPECIFIC LEVEL
    // =====================================================
    public static void deleteAtLevel(TreeNode root, int level) {
        if(root == null || level < 1) return;

        if(level == 1) {
            if(root.left != null) root.left = null;
            if(root.right != null) root.right = null;
        } else {
            deleteAtLevel(root.left, level - 1);
            deleteAtLevel(root.right, level - 1);
        }
    }

    // =====================================================
    // INORDER TRAVERSAL
    // =====================================================
    public static void inorder(TreeNode root) {
        if(root == null) return;
        inorder(root.left);
        System.out.print(root.val + " ");
        inorder(root.right);
    }

    // =====================================================
    // LEVEL ORDER TRAVERSAL
    // =====================================================
    public static void levelOrder(TreeNode root) {
        if(root == null) return;

        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);

        while(!queue.isEmpty()) {
            TreeNode temp = queue.poll();
            System.out.print(temp.val + " ");
            if(temp.left != null) queue.offer(temp.left);
            if(temp.right != null) queue.offer(temp.right);
        }
    }

    // =====================================================
    // MAIN METHOD (TESTING ALL TYPES)
    // =====================================================
    public static void main(String[] args) {
        // ====== BST Recursive Deletion ======
        TreeNode bstRec = null;
        int[] bstValues = {50, 30, 70, 20, 40, 60, 80};
        for(int val : bstValues) bstRec = new TreeNode(val);
        for(int val : bstValues) bstRec = deleteBSTRecursive(bstRec, val); // test deletion

        System.out.println("BST Recursive Deletion (Inorder):");
        inorder(bstRec);
        System.out.println("\n");

        // ====== Normal Binary Tree Deletion ======
        TreeNode normalTree = null;
        int[] values = {1,2,3,4,5};
        for(int val : values) normalTree = new TreeNode(val);

        normalTree = deleteLevelOrder(normalTree, 3);
        System.out.println("Normal Binary Tree After Level Order Deletion:");
        levelOrder(normalTree);
        System.out.println("\n");

        // ====== Special Deletions ======
        deleteLeft(normalTree, 1);
        deleteRight(normalTree, 2);
        normalTree = deleteRoot(normalTree);
        deleteAtLevel(normalTree, 2);

        System.out.println("After Special Deletions:");
        levelOrder(normalTree);
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

1) BST Deletion
   - Recursive:
       Time:  O(h)   (h = height of tree, O(log n) average, O(n) worst)
       Space: O(h)   (recursion stack)
   - Iterative:
       Time:  O(h)
       Space: O(1)

2) Normal Binary Tree Deletion (Level Order):
       Time:  O(n)   (traverses nodes to find and delete)
       Space: O(n)   (queue for level order)

3) Special Binary Tree Deletions:
   - Delete Left/Right Child:
       Time:  O(n)   (may traverse entire tree)
       Space: O(h)   (recursion stack)
   - Delete Root:
       Time:  O(h)   (find inorder successor if 2 children)
       Space: O(h)   (if recursive)
   - Delete At Specific Level:
       Time:  O(n)   (traverse level)
       Space: O(h)   (recursion stack)

===========================================================
*/