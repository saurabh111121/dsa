# Data Structures

This folder contains implementations of fundamental data structures in Java.

---

## Structure

| Section | Topics |
|---------|--------|
| [Arrays](Arrays/) | Insertion, Deletion, Search, Update, Traversal, Advanced, Matrix, Subarray, Merge/Split, Dynamic Array |
| [LinkedLists](LinkedLists/) | Singly, Doubly, Circular |
| [Stacks](Stacks/) | Array-based, Linked list-based, Applications |
| [Queues](Queues/) | Simple, Circular, Priority, Deque |
| [Trees](Trees/) | Binary Tree, BST, AVL, Traversals, Views, Serialization |
| [Graphs](Graphs/) | BFS, DFS, Directed, Undirected, Weighted |
| [Heaps](Heaps/) | Min/Max Heap, Priority Queue, Heap Sort |
| [HashTables](HashTables/) | HashMap, HashSet, Collision Handling |
| [Tries](Tries/) | Prefix Tree, Autocomplete |
| [Sets](Sets/) | Set operations, Union-Find |
| [Maps](Maps/) | TreeMap, HashMap |

---

## 📦 Arrays

> 📂 All files: [`DataStructures/Arrays/`](https://github.com/saurabh111121/dsa/tree/main/DataStructures/Arrays)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Category | Key Operations |
|---|------|----------|----------------|
| 1 | [ArrayAdvancedOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayAdvancedOperations.java) | Advanced | Reverse, Rotate (L/R), Max, Min, Sum |
| 2 | [ArrayDeletionOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayDeletionOperations.java) | Deletion | Delete from end/start/index, Delete multiple |
| 3 | [ArrayInsertionOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayInsertionOperations.java) | Insertion | Insert at end/start/index, Insert multiple |
| 4 | [ArrayMergeSplit.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayMergeSplit.java) | Merge & Split | Merge two arrays, Split at index |
| 5 | [ArraySearchOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArraySearchOperations.java) | Search | Linear Search, Binary Search |
| 6 | [ArrayTraversalOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayTraversalOperations.java) | Traversal | For loop, Enhanced for-each loop |
| 7 | [ArrayUpdateOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayUpdateOperations.java) | Update | Update element at index |
| 8 | [DynamicArray.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/DynamicArray.java) | Dynamic Array | add(), get(), remove(), resize (auto-doubling) |
| 9 | [MatrixAdvancedOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/MatrixAdvancedOperations.java) | Matrix – Advanced | Transpose, Diagonal Sums, Spiral Traversal |
| 10 | [MatrixOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/MatrixOperations.java) | Matrix | Traversal, Sum, 90° Clockwise Rotation |
| 11 | [SubarrayOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/SubarrayOperations.java) | Subarray | Kadane's Algorithm (Max Subarray Sum) |

</details>

---

<details>
<summary><b>1. Insertion</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayInsertionOperations.java">ArrayInsertionOperations.java</a></summary>

| Operation | Time | Space |
|-----------|------|-------|
| Insert at end | O(n) | O(n) |
| Insert at beginning | O(n) | O(n) |
| Insert at specific index | O(n) | O(n) |
| Insert multiple at index | O(n) | O(n) |

> All operations create a new array (Java arrays are fixed-size).

</details>

<details>
<summary><b>2. Deletion</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayDeletionOperations.java">ArrayDeletionOperations.java</a></summary>

| Operation | Time | Space |
|-----------|------|-------|
| Delete from end | O(n) | O(n) |
| Delete from beginning | O(n) | O(n) |
| Delete at specific index | O(n) | O(n) |
| Delete multiple from index | O(n) | O(n) |

</details>

<details>
<summary><b>3. Search</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArraySearchOperations.java">ArraySearchOperations.java</a></summary>

| Operation | Requirement | Time | Space |
|-----------|-------------|------|-------|
| Linear Search | Unsorted array | O(n) | O(1) |
| Binary Search | **Sorted array** | O(log n) | O(1) |

</details>

<details>
<summary><b>4. Update</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayUpdateOperations.java">ArrayUpdateOperations.java</a></summary>

| Operation | Time | Space |
|-----------|------|-------|
| Update element at index | O(1) | O(1) |

</details>

<details>
<summary><b>5. Traversal</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayTraversalOperations.java">ArrayTraversalOperations.java</a></summary>

| Operation | Time | Space |
|-----------|------|-------|
| for loop traversal | O(n) | O(1) |
| Enhanced for-each traversal | O(n) | O(1) |

</details>

<details>
<summary><b>6. Advanced Operations</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayAdvancedOperations.java">ArrayAdvancedOperations.java</a></summary>

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| Reverse | Two-pointer swap in-place | O(n) | O(1) |
| Rotate Right by k | Reverse 3-step approach | O(n) | O(1) |
| Rotate Left by k | Reverse 3-step approach | O(n) | O(1) |
| Find Max | Linear scan | O(n) | O(1) |
| Find Min | Linear scan | O(n) | O(1) |
| Sum of elements | Linear scan | O(n) | O(1) |

</details>

<details>
<summary><b>7. Merge & Split</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayMergeSplit.java">ArrayMergeSplit.java</a></summary>

| Operation | Time | Space |
|-----------|------|-------|
| Merge two arrays | O(n + m) | O(n + m) |
| Split array at index | O(n) | O(n) |

</details>

<details>
<summary><b>8. Subarray (Kadane's Algorithm)</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/SubarrayOperations.java">SubarrayOperations.java</a></summary>

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| Max Subarray Sum | Kadane's Algorithm | O(n) | O(1) |
| Get Max Subarray | Kadane's with index tracking | O(n) | O(k) |

</details>

<details>
<summary><b>9. Dynamic Array</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/DynamicArray.java">DynamicArray.java</a></summary>

Simulates an `ArrayList` with auto-resizing (doubles capacity on overflow).

| Operation | Time | Space |
|-----------|------|-------|
| `add(value)` | O(1) amortized | O(n) on resize |
| `get(index)` | O(1) | O(1) |
| `remove(index)` | O(n) | O(1) |
| `resize()` (internal) | O(n) | O(n) |

</details>

<details>
<summary><b>10. Matrix Operations</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/MatrixOperations.java">MatrixOperations.java</a></summary>

| Operation | Time | Space |
|-----------|------|-------|
| Row-wise traversal | O(n×m) | O(1) |
| Sum of all elements | O(n×m) | O(1) |
| 90° Clockwise Rotation | O(n²) | O(n²) |

</details>

<details>
<summary><b>11. Matrix Advanced Operations</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/MatrixAdvancedOperations.java">MatrixAdvancedOperations.java</a></summary>

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| Transpose | Swap `[i][j]` ↔ `[j][i]` into new matrix | O(n×m) | O(n×m) |
| Main Diagonal Sum | Sum `matrix[i][i]` | O(n) | O(1) |
| Secondary Diagonal Sum | Sum `matrix[i][n-1-i]` | O(n) | O(1) |
| Spiral Traversal | Layer-by-layer boundary traversal | O(n×m) | O(1) |

</details>

---

## 🌳 Trees

> 📂 All files: [`DataStructures/Trees/`](https://github.com/saurabh111121/dsa/tree/main/DataStructures/Trees)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Category | Key Operations |
|---|------|----------|----------------|
| 1 | [AdvancedBSTOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/AdvancedBSTOperations.java) | BST – Advanced | Successor, Predecessor, Floor, Ceiling, Range Query |
| 2 | [AdvancedPathQueries.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/AdvancedPathQueries.java) | Paths | Root-to-leaf & anywhere paths with target sum |
| 3 | [AVLTree.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/AVLTree.java) | Balanced BST | Insert, Delete, LL/RR/LR/RL Rotations |
| 4 | [BinaryTreeAllDeletions.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeAllDeletions.java) | Deletion | BST (rec/itr), Level Order, Special Deletions |
| 5 | [BinaryTreeAllInsertions.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeAllInsertions.java) | Insertion | BST (rec/itr), Level Order, Special Insertions |
| 6 | [BinaryTreeAllSearches.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeAllSearches.java) | Search | BST (rec/itr), Min/Max, DFS, BFS, Parent, Level |
| 7 | [BinaryTreeAllTraversals.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeAllTraversals.java) | Traversal | Inorder, Preorder, Postorder, Level Order |
| 8 | [BinaryTreeDiameter.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeDiameter.java) | Metrics | Diameter – Naive O(n²), Optimized O(n) |
| 9 | [BinaryTreeFileSerialization.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeFileSerialization.java) | Serialization | Serialize/Deserialize to/from file |
| 10 | [BinaryTreeHeightDepth.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeHeightDepth.java) | Metrics | Height (rec/itr), Node Depth, Max/Min Depth |
| 11 | [BinaryTreeLCA.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeLCA.java) | LCA | LCA in Binary Tree & BST |
| 12 | [BinaryTreeLeetCodeStyle.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeLeetCodeStyle.java) | Serialization | Build from list, Serialize to list (LeetCode style) |
| 13 | [BinaryTreeMirror.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeMirror.java) | Transform | Mirror/Invert – Recursive & Iterative |
| 14 | [BinaryTreeNodeCounts.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeNodeCounts.java) | Metrics | Total, Leaf, Non-Leaf node counts |
| 15 | [BinaryTreePaths.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreePaths.java) | Paths | Root-to-leaf paths, Path sum, Max path sum |
| 16 | [BinaryTreeProperties.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeProperties.java) | Properties | Balanced, Complete, Full, Perfect checks |
| 17 | [BinaryTreeSerialization.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeSerialization.java) | Serialization | Serialize/Deserialize to/from string |
| 18 | [BinaryTreeViews.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeViews.java) | Views | Top, Bottom, Left, Right, Diagonal views |
| 19 | [BSTIterator.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BSTIterator.java) | BST – Iterator | `next()`, `hasNext()` – lazy stack inorder |
| 20 | [BSTKthElement.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BSTKthElement.java) | BST – Queries | Kth Smallest, Kth Largest |
| 21 | [BSTValidation.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BSTValidation.java) | BST – Validation | Validate BST – Recursive & Iterative Inorder |
| 22 | [SameTree.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/SameTree.java) | Comparison | LeetCode #100 – `isSameTree(p, q)` |
| 23 | [ThreadedBSTMorris.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/ThreadedBSTMorris.java) | Traversal | Morris Inorder Traversal – O(1) space |
| 24 | [TreeLevelSums.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/TreeLevelSums.java) | Sums | Horizontal (level) sum, Vertical sum |
| 25 | [TreeNode.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/TreeNode.java) | Foundation | Base `TreeNode` class |
| 26 | [ZigZagTraversal.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/ZigZagTraversal.java) | Traversal | Zigzag / Spiral level order traversal |

</details>

---

<details>
<summary><b>1. Insertions</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeAllInsertions.java">BinaryTreeAllInsertions.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| BST Insert | Recursive | O(h) | O(h) |
| BST Insert | Iterative | O(h) | O(1) |
| Normal BT Insert | Level Order BFS | O(n) | O(n) |
| Insert as Left/Right Child | Recursive | O(n) | O(h) |
| Insert at Root | Direct | O(1) | O(1) |
| Insert at Specific Level | Recursive | O(n) | O(h) |

</details>

<details>
<summary><b>2. Deletions</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeAllDeletions.java">BinaryTreeAllDeletions.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| BST Delete | Recursive | O(h) | O(h) |
| BST Delete | Iterative | O(h) | O(1) |
| Normal BT Delete | Level Order BFS | O(n) | O(n) |
| Delete Left/Right Child | Recursive | O(n) | O(h) |
| Delete Root | Inorder Successor | O(h) | O(h) |
| Delete at Specific Level | Recursive | O(n) | O(h) |

</details>

<details>
<summary><b>3. Searches</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeAllSearches.java">BinaryTreeAllSearches.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| BST Search | Recursive | O(h) | O(h) |
| BST Search | Iterative | O(h) | O(1) |
| Find Min / Max | Iterative | O(h) | O(1) |
| Normal BT Search | DFS Recursive | O(n) | O(h) |
| Normal BT Search | BFS Level Order | O(n) | O(n) |
| Find Parent of Node | Recursive | O(n) | O(h) |
| Find Level of Node | Recursive | O(n) | O(h) |

</details>

<details>
<summary><b>4. Traversals</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeAllTraversals.java">BinaryTreeAllTraversals.java</a></summary>

| Traversal | Order | Type |
|-----------|-------|------|
| Inorder | Left → Root → Right | DFS Recursive |
| Preorder | Root → Left → Right | DFS Recursive |
| Postorder | Left → Right → Root | DFS Recursive |
| Level Order | Level by Level | BFS Iterative |

</details>

<details>
<summary><b>5. Height & Depth</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeHeightDepth.java">BinaryTreeHeightDepth.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| Tree Height | Recursive | O(n) | O(h) |
| Tree Height | Iterative (Level Order) | O(n) | O(n) |
| Node Depth | Recursive | O(n) | O(h) |
| Max Depth | Recursive | O(n) | O(h) |
| Min Depth | Recursive | O(n) | O(h) |

</details>

<details>
<summary><b>6. Node Counts</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeNodeCounts.java">BinaryTreeNodeCounts.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| Count Total Nodes | Recursive / Iterative | O(n) | O(h) / O(n) |
| Count Leaf Nodes | Recursive / Iterative | O(n) | O(h) / O(n) |
| Count Non-Leaf Nodes | Recursive / Iterative | O(n) | O(h) / O(n) |

</details>

<details>
<summary><b>7. Tree Properties</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeProperties.java">BinaryTreeProperties.java</a></summary>

| Check | Description | Time | Space |
|-------|-------------|------|-------|
| `isBalanced` | Height diff of subtrees ≤ 1 for every node | O(n) | O(h) |
| `isComplete` | All levels full except last (filled left→right) | O(n) | O(n) |
| `isFull` | Every node has 0 or 2 children | O(n) | O(h) |
| `isPerfect` | Full + all leaves at same level | O(n) | O(h) |

</details>

<details>
<summary><b>8. Diameter</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeDiameter.java">BinaryTreeDiameter.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| Diameter (max distance between 2 nodes) | Naive Recursive | O(n²) | O(h) |
| Diameter (max distance between 2 nodes) | Optimized (height + diameter together) | O(n) | O(h) |

</details>

<details>
<summary><b>9. Mirror / Invert</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeMirror.java">BinaryTreeMirror.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| Mirror / Invert Tree | Recursive | O(n) | O(h) |
| Mirror / Invert Tree | Iterative BFS | O(n) | O(n) |

</details>

<details>
<summary><b>10. Paths</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreePaths.java">BinaryTreePaths.java</a></summary>

| Operation | Time | Space |
|-----------|------|-------|
| Print All Root-to-Leaf Paths | O(n) | O(h) |
| Check Path Sum Exists | O(n) | O(h) |
| All Root-to-Leaf Paths as List | O(n) | O(h) |
| Maximum Path Sum (any path) | O(n) | O(h) |

</details>

<details>
<summary><b>11. Lowest Common Ancestor</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeLCA.java">BinaryTreeLCA.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| LCA in Binary Tree | Recursive | O(n) | O(h) |
| LCA in BST | Optimized Recursive (BST property) | O(h) | O(h) |

</details>

<details>
<summary><b>12. Tree Views</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeViews.java">BinaryTreeViews.java</a></summary>

| View | Description | Time | Space |
|------|-------------|------|-------|
| Top View | First visible node per horizontal distance | O(n) | O(n) |
| Bottom View | Last visible node per horizontal distance | O(n) | O(n) |
| Left View | First node at each level | O(n) | O(n) |
| Right View | Last node at each level | O(n) | O(n) |
| Diagonal View | Nodes grouped by diagonal slope | O(n) | O(n) |

</details>

<details>
<summary><b>13. Level Sums</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/TreeLevelSums.java">TreeLevelSums.java</a></summary>

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| Horizontal (Level) Sum | Sum of nodes at each level | O(n) | O(w) |
| Vertical Sum | Sum of nodes per vertical column | O(n) | O(n) |

</details>

<details>
<summary><b>14. Zigzag / Spiral Traversal</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/ZigZagTraversal.java">ZigZagTraversal.java</a></summary>

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| Zigzag Level Order | Alternates L→R and R→L per level using Deque | O(n) | O(w) |

</details>

<details>
<summary><b>15. Serialization (String)</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeSerialization.java">BinaryTreeSerialization.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| Serialize to String | Preorder with `null` markers | O(n) | O(n) |
| Deserialize from String | Preorder via Queue | O(n) | O(n) |

</details>

<details>
<summary><b>16. Serialization (File)</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeFileSerialization.java">BinaryTreeFileSerialization.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| Serialize to File | Preorder with `#` markers via `BufferedWriter` | O(n) | O(h) |
| Deserialize from File | Preorder via `BufferedReader` + Queue | O(n) | O(n) |

</details>

<details>
<summary><b>17. LeetCode Style I/O</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeLeetCodeStyle.java">BinaryTreeLeetCodeStyle.java</a></summary>

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| Build Tree from List | Constructs from `[1, 2, null, 3]` style input | O(n) | O(n) |
| Serialize to List | Produces LeetCode-style list (nulls trimmed) | O(n) | O(n) |

</details>

<details>
<summary><b>18. Same Tree</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/SameTree.java">SameTree.java</a></summary>

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| `isSameTree(p, q)` | LeetCode #100 – checks if two trees are identical | O(n) | O(h) |

</details>

<details>
<summary><b>19. BST Validation</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BSTValidation.java">BSTValidation.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| Validate BST | Recursive (min/max range) | O(n) | O(h) |
| Validate BST | Iterative Inorder (sorted check) | O(n) | O(h) |

</details>

<details>
<summary><b>20. BST Kth Element</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BSTKthElement.java">BSTKthElement.java</a></summary>

| Operation | Approach | Time | Space |
|-----------|----------|------|-------|
| Kth Smallest | Iterative Inorder (stack) | O(h + k) | O(h) |
| Kth Largest | Iterative Reverse Inorder (stack) | O(h + k) | O(h) |

</details>

<details>
<summary><b>21. BST Iterator</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BSTIterator.java">BSTIterator.java</a></summary>

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| `next()` | Returns next smallest element | O(1) amortized | O(h) |
| `hasNext()` | Returns true if more elements exist | O(1) | O(1) |

> Lazy stack-based inorder – each node pushed/popped exactly once.

</details>

<details>
<summary><b>22. Advanced BST Operations</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/AdvancedBSTOperations.java">AdvancedBSTOperations.java</a></summary>

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| Inorder Successor | Smallest node > key | O(h) | O(1) |
| Inorder Predecessor | Largest node < key | O(h) | O(1) |
| Floor | Largest value ≤ key | O(h) | O(1) |
| Ceiling | Smallest value ≥ key | O(h) | O(1) |
| Range Query | All nodes in `[low, high]` | O(k + h) | O(k + h) |

</details>

<details>
<summary><b>23. Advanced Path Queries</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/AdvancedPathQueries.java">AdvancedPathQueries.java</a></summary>

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| Root-to-Leaf Paths with Sum | DFS + backtracking | O(n) | O(h) |
| Paths Anywhere with Sum | DFS + check all ending sub-paths | O(n²) worst | O(h) |

</details>

<details>
<summary><b>24. AVL Tree</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/AVLTree.java">AVLTree.java</a></summary>

Self-balancing BST – maintains balance factor (−1, 0, +1) at every node.

| Operation | Time | Space |
|-----------|------|-------|
| Insert (with rotations) | O(log n) | O(log n) |
| Delete (with rotations) | O(log n) | O(log n) |
| LL / RR / LR / RL Rotation | O(1) | O(1) |

</details>

<details>
<summary><b>25. Morris Traversal (Threaded BST)</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/ThreadedBSTMorris.java">ThreadedBSTMorris.java</a></summary>

| Operation | Description | Time | Space |
|-----------|-------------|------|-------|
| Morris Inorder | Space-optimized via temporary right-thread pointers | O(n) | **O(1)** |

> No recursion stack or auxiliary data structure used. Threads are created and restored.

</details>

---

## Learning Path

1. Arrays → LinkedLists → Stacks & Queues → Trees → Graphs → Heaps → HashTables → Tries / Sets / Maps

### Trees – Suggested Order
`TreeNode` → `Insertions` → `Traversals` → `Height/Depth` → `Node Counts` → `Properties` → `Searches` → `Deletions` → `Paths` → `LCA` → `Mirror` → `Diameter` → `Views` → `LevelSums` → `Zigzag` → `Serialization` → `LeetCode Style` → `SameTree` → `BST Validation` → `Kth Element` → `BST Iterator` → `Advanced BST Ops` → `Advanced Path Queries` → `Morris Traversal` → `AVL Tree`
