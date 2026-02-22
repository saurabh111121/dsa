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

## 🔗 LinkedLists

> 📂 All files: [`DataStructures/LinkedLists/`](https://github.com/saurabh111121/dsa/tree/main/DataStructures/LinkedLists)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Key Operations |
|---|------|----------------|
| 1 | [Node.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/LinkedLists/Node.java) | Base node: val, next, prev |
| 2 | [SinglyLinkedList.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/LinkedLists/SinglyLinkedList.java) | Insert/Delete/Search/Reverse/Middle/NthFromEnd |
| 3 | [DoublyLinkedList.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/LinkedLists/DoublyLinkedList.java) | Bidirectional traversal, O(1) delete given node |
| 4 | [CircularLinkedList.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/LinkedLists/CircularLinkedList.java) | Insert/Delete/Search, Floyd's cycle detection |
| 5 | [LinkedListAdvancedOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/LinkedLists/LinkedListAdvancedOperations.java) | Merge, Rotate, Palindrome, Intersection, Sort |

</details>

<details>
<summary><b>1. Node</b> – Base Node Structure</summary>

| Operation | Time | Space |
|-----------|------|-------|
| Access val | O(1) | O(1) |

- Fields: `val`, `next`, `prev`
- Used by all linked list variants
</details>

<details>
<summary><b>2. SinglyLinkedList</b> – One-directional linked list</summary>

| Operation | Time | Space |
|-----------|------|-------|
| Insert at Head | O(1) | O(1) |
| Insert at Tail | O(n) | O(1) |
| Insert at Index | O(n) | O(1) |
| Delete Head | O(1) | O(1) |
| Delete Tail | O(n) | O(1) |
| Delete by Value | O(n) | O(1) |
| Search | O(n) | O(1) |
| Find Middle (slow-fast) | O(n) | O(1) |
| Find Nth from End | O(n) | O(1) |
| Reverse iterative | O(n) | O(1) |
| Reverse recursive | O(n) | O(n) |

</details>

<details>
<summary><b>3. DoublyLinkedList</b> – Bidirectional linked list</summary>

| Operation | Time | Space |
|-----------|------|-------|
| Insert/Delete Head or Tail | O(1) | O(1) |
| Insert/Delete at Index | O(n) | O(1) |
| Delete given node reference | O(1) | O(1) |
| Forward/Backward traversal | O(n) | O(1) |
| Reverse | O(n) | O(1) |

</details>

<details>
<summary><b>4. CircularLinkedList</b> – Circular structure with cycle utilities</summary>

| Operation | Time | Space |
|-----------|------|-------|
| Insert/Delete | O(n) | O(1) |
| Search | O(n) | O(1) |
| Detect Cycle (Floyd's) | O(n) | O(1) |
| Find Cycle Start | O(n) | O(1) |

</details>

<details>
<summary><b>5. LinkedListAdvancedOperations</b> – Complex list problems</summary>

| Operation | Time | Space |
|-----------|------|-------|
| Merge Sorted (recursive) | O(n+m) | O(n+m) |
| Merge Sorted (iterative) | O(n+m) | O(1) |
| Remove Duplicates (sorted) | O(n) | O(1) |
| Remove Duplicates (unsorted) | O(n) | O(n) |
| Partition | O(n) | O(1) |
| Rotate Right | O(n) | O(1) |
| Is Palindrome | O(n) | O(1) |
| Intersection (length-diff) | O(n+m) | O(1) |
| Add Two Numbers | O(max(n,m)) | O(max(n,m)) |
| Swap Pairs | O(n) | O(1) |
| Merge Sort | O(n log n) | O(log n) |

</details>

---

## 📚 Stacks

> 📂 All files: [`DataStructures/Stacks/`](https://github.com/saurabh111121/dsa/tree/main/DataStructures/Stacks)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Key Operations |
|---|------|----------------|
| 1 | [StackUsingArray.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Stacks/StackUsingArray.java) | FixedStack, DynamicStack (amortized O(1) push) |
| 2 | [StackUsingLinkedList.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Stacks/StackUsingLinkedList.java) | push/pop/peek O(1), no size limit |
| 3 | [StackApplications.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Stacks/StackApplications.java) | Balanced parens, Postfix eval, NGE, MinStack, Decode |

</details>

<details>
<summary><b>1. StackUsingArray</b> – Array-backed stack</summary>

| Operation | Time | Space |
|-----------|------|-------|
| push | O(1) amortized | O(1) |
| pop | O(1) | O(1) |
| peek | O(1) | O(1) |
| search | O(n) | O(1) |

- `FixedStack`: static capacity; `DynamicStack`: doubles when full
</details>

<details>
<summary><b>2. StackUsingLinkedList</b> – Node-backed stack</summary>

| Operation | Time | Space |
|-----------|------|-------|
| push / pop / peek | O(1) | O(1) |
| search | O(n) | O(1) |

- No fixed size limit; slight extra pointer overhead vs array
</details>

<details>
<summary><b>3. StackApplications</b> – Classic stack problems</summary>

| Problem | Time | Space |
|---------|------|-------|
| isBalanced (parens) | O(n) | O(n) |
| reverseString | O(n) | O(n) |
| evaluatePostfix | O(n) | O(n) |
| infixToPostfix | O(n) | O(n) |
| nextGreaterElement | O(n) | O(n) |
| prevGreaterElement | O(n) | O(n) |
| stockSpan | O(n) | O(n) |
| MinStack (push/pop/getMin) | O(1) | O(n) |
| sortStack | O(n²) | O(n) |
| decodeString | O(n·k) | O(n) |

</details>

---

## 📬 Queues

> 📂 All files: [`DataStructures/Queues/`](https://github.com/saurabh111121/dsa/tree/main/DataStructures/Queues)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Key Operations |
|---|------|----------------|
| 1 | [QueueUsingArray.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Queues/QueueUsingArray.java) | SimpleQueue O(n) dequeue; CircularQueue O(1) all ops |
| 2 | [QueueUsingLinkedList.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Queues/QueueUsingLinkedList.java) | All ops O(1) |
| 3 | [QueueUsingTwoStacks.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Queues/QueueUsingTwoStacks.java) | Lazy/Eager queue, StackUsingQueues |
| 4 | [PriorityQueueOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Queues/PriorityQueueOperations.java) | Min/Max PQ, Kth Largest/Smallest, Merge K sorted |
| 5 | [DequeOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Queues/DequeOperations.java) | All ends O(1), Sliding Window Max, Palindrome |

</details>

<details>
<summary><b>1. QueueUsingArray</b> – Array-backed queue variants</summary>

| Variant | enqueue | dequeue |
|---------|---------|---------|
| SimpleQueue | O(1) | O(n) shifting |
| CircularQueue | O(1) | O(1) ✅ |

</details>

<details>
<summary><b>2. QueueUsingLinkedList</b> – Node-backed queue</summary>

| Operation | Time |
|-----------|------|
| enqueue / dequeue / peek / size | O(1) |

</details>

<details>
<summary><b>3. QueueUsingTwoStacks</b> – Dual-stack implementations</summary>

| Variant | enqueue | dequeue |
|---------|---------|---------|
| QueueLazy | O(1) | O(1) amortized |
| QueueEager | O(n) | O(1) |
| StackUsingQueues | push O(n) | pop O(1) |

</details>

<details>
<summary><b>4. PriorityQueueOperations</b> – Heap-based priority queue</summary>

| Operation | Time |
|-----------|------|
| offer / poll | O(log n) |
| peek | O(1) |
| Kth Largest / Smallest | O(n log k) |
| Merge K Sorted Lists | O(n log k) |
| Top K Frequent | O(n log k) |

</details>

<details>
<summary><b>5. DequeOperations</b> – Double-ended queue</summary>

| Operation | Time |
|-----------|------|
| addFirst/addLast/removeFirst/removeLast/peek | O(1) |
| Sliding Window Maximum | O(n) |
| isPalindromeDeque | O(n) |

</details>

---

## 📊 Graphs

> 📂 All files: [`DataStructures/Graphs/`](https://github.com/saurabh111121/dsa/tree/main/DataStructures/Graphs)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Key Operations |
|---|------|----------------|
| 1 | [GraphRepresentations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Graphs/GraphRepresentations.java) | AdjMatrix, AdjList, WeightedAdjList, EdgeList |
| 2 | [GraphTraversals.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Graphs/GraphTraversals.java) | BFS, DFS (recursive+iterative), Cycle detection |
| 3 | [GraphShortestPath.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Graphs/GraphShortestPath.java) | Dijkstra, Bellman-Ford, BFS shortest, Topo Sort |
| 4 | [GraphAdvanced.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Graphs/GraphAdvanced.java) | Union-Find, Kruskal MST, Prim MST, Islands, Bipartite |

</details>

<details>
<summary><b>1. GraphRepresentations</b> – Graph storage structures</summary>

| Representation | Space | addEdge | hasEdge | getNeighbors |
|----------------|-------|---------|---------|--------------|
| AdjacencyMatrix | O(V²) | O(1) | O(1) | O(V) |
| AdjacencyList | O(V+E) | O(1) | O(deg) | O(1) |
| WeightedAdjList | O(V+E) | O(1) | O(deg) | O(1) |
| EdgeList | O(E) | O(1) | O(E) | O(E) |

</details>

<details>
<summary><b>2. GraphTraversals</b> – BFS/DFS and cycle detection</summary>

| Algorithm | Time | Space |
|-----------|------|-------|
| BFS iterative | O(V+E) | O(V) |
| DFS recursive | O(V+E) | O(V) |
| DFS iterative | O(V+E) | O(V) |
| BFS Level Order | O(V+E) | O(V) |
| Cycle Detection Undirected (BFS+DFS) | O(V+E) | O(V) |
| Cycle Detection Directed (DFS 3-color) | O(V+E) | O(V) |

</details>

<details>
<summary><b>3. GraphShortestPath</b> – Shortest path algorithms</summary>

| Algorithm | Time | Space | Notes |
|-----------|------|-------|-------|
| Dijkstra | O((V+E) log V) | O(V) | No negative edges |
| Bellman-Ford | O(V·E) | O(V) | Handles negatives |
| BFS Shortest Path | O(V+E) | O(V) | Unweighted only |
| Topological Sort (Kahn's BFS) | O(V+E) | O(V) | DAG only |
| Topological Sort (DFS) | O(V+E) | O(V) | DAG only |

</details>

<details>
<summary><b>4. GraphAdvanced</b> – MST, Union-Find, advanced problems</summary>

| Algorithm | Time | Space |
|-----------|------|-------|
| Union-Find (path compression) | O(α(n)) | O(V) |
| Kruskal MST | O(E log E) | O(V) |
| Prim MST | O((V+E) log V) | O(V) |
| Number of Islands (DFS/BFS) | O(M·N) | O(M·N) |
| Bipartite Check (BFS/DFS) | O(V+E) | O(V) |

</details>

---

## #️⃣ HashTables

> 📂 All files: [`DataStructures/HashTables/`](https://github.com/saurabh111121/dsa/tree/main/DataStructures/HashTables)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Key Operations |
|---|------|----------------|
| 1 | [HashMapImplementation.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/HashTables/HashMapImplementation.java) | Custom HashMap, Separate Chaining, resize at 0.75 LF |
| 2 | [HashSetImplementation.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/HashTables/HashSetImplementation.java) | Custom HashSet, Linear Probing, common Set problems |
| 3 | [HashCollisionStrategies.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/HashTables/HashCollisionStrategies.java) | Chaining, Linear/Quadratic Probing, Double Hashing |

</details>

<details>
<summary><b>1. HashMapImplementation</b> – Custom HashMap with Separate Chaining</summary>

| Operation | Avg | Worst |
|-----------|-----|-------|
| put / get / remove | O(1) | O(n) |
| resize (at 0.75 load) | O(n) | O(n) |
| keys() / values() | O(n+cap) | O(n+cap) |

</details>

<details>
<summary><b>2. HashSetImplementation</b> – Custom HashSet with Linear Probing</summary>

| Operation | Avg | Worst |
|-----------|-----|-------|
| add / contains / remove | O(1) | O(n) |
| removeDuplicates / twoSum / findDuplicates / intersection / longestConsecutive | O(n) | O(n) |

</details>

<details>
<summary><b>3. HashCollisionStrategies</b> – Collision resolution techniques</summary>

| Strategy | Avg | Worst | Notes |
|----------|-----|-------|-------|
| Separate Chaining | O(1) | O(n) | Linked list buckets |
| Linear Probing | O(1) | O(n) | Sequential scan |
| Quadratic Probing | O(1) | O(n) | i² step jumps |
| Double Hashing | O(1) | O(n) | Two hash functions |

</details>

---

## 🏔️ Heaps

> 📂 All files: [`DataStructures/Heaps/`](https://github.com/saurabh111121/dsa/tree/main/DataStructures/Heaps)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Key Operations |
|---|------|----------------|
| 1 | [MinHeap.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Heaps/MinHeap.java) | insert, extractMin, buildHeap O(n), heapSort, decreaseKey |
| 2 | [MaxHeap.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Heaps/MaxHeap.java) | insert, extractMax, buildHeap O(n), in-place heapSort, increaseKey |
| 3 | [HeapApplications.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Heaps/HeapApplications.java) | MedianFinder, K Closest Points, Reorganize String, Task Scheduler |

</details>

<details>
<summary><b>1. MinHeap</b> – Min-heap implementation</summary>

| Operation | Time |
|-----------|------|
| insert | O(log n) |
| extractMin | O(log n) |
| peekMin | O(1) |
| buildHeap | O(n) |
| heapSort | O(n log n) |
| decreaseKey | O(log n) |
| deleteAt | O(log n) |
| contains | O(n) |

</details>

<details>
<summary><b>2. MaxHeap</b> – Max-heap implementation</summary>

| Operation | Time |
|-----------|------|
| insert | O(log n) |
| extractMax | O(log n) |
| peekMax | O(1) |
| buildHeap | O(n) |
| heapSort (in-place) | O(n log n) |
| increaseKey | O(log n) |

- Both iterative and recursive heapifyUp/Down variants
</details>

<details>
<summary><b>3. HeapApplications</b> – Heap-based problem solving</summary>

| Problem | Time | Space |
|---------|------|-------|
| MedianFinder – addNum | O(log n) | O(n) |
| MedianFinder – findMedian | O(1) | — |
| K Closest Points | O(n log k) | O(k) |
| Reorganize String | O(n log n) | O(n) |
| Task Scheduler | O(n) | O(1) |
| K Pairs Smallest Sums | O(k log k) | O(k) |

</details>

---

## 🗺️ Maps

> 📂 All files: [`DataStructures/Maps/`](https://github.com/saurabh111121/dsa/tree/main/DataStructures/Maps)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Key Operations |
|---|------|----------------|
| 1 | [MapOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Maps/MapOperations.java) | HashMap, LinkedHashMap, TreeMap, LRU Cache, Applications |

</details>

<details>
<summary><b>1. MapOperations</b> – Map variants and applications</summary>

| Operation | HashMap | LinkedHashMap | TreeMap |
|-----------|---------|---------------|---------|
| put / get / remove | O(1) avg | O(1) avg | O(log n) |
| Ordering | None | Insertion order | Sorted |
| firstKey / lastKey | — | — | O(log n) |
| floorKey / ceilingKey | — | — | O(log n) |
| headMap / tailMap | — | — | O(log n) |

**Applications:** `frequencyCount`, `groupAnagrams`, `wordCount` — all O(n)

**LRU Cache** (LinkedHashMap): `get` O(1), `put` O(1)
</details>

---

## 🔷 Sets

> 📂 All files: [`DataStructures/Sets/`](https://github.com/saurabh111121/dsa/tree/main/DataStructures/Sets)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Key Operations |
|---|------|----------------|
| 1 | [SetOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Sets/SetOperations.java) | HashSet, LinkedHashSet, TreeSet, Set operations, Applications |

</details>

<details>
<summary><b>1. SetOperations</b> – Set variants and applications</summary>

| Operation | HashSet | LinkedHashSet | TreeSet |
|-----------|---------|---------------|---------|
| add / remove / contains | O(1) avg | O(1) avg | O(log n) |
| Ordering | None | Insertion order | Sorted |
| floor / ceiling | — | — | O(log n) |
| headSet / tailSet | — | — | O(log n) |

| Set Problem | Time | Space |
|-------------|------|-------|
| Union / Intersection / Difference | O(n) | O(n) |
| SymmetricDifference | O(n) | O(n) |
| isSubset | O(n) | O(1) |
| isAnagram | O(n) | O(1) |
| firstUniqueChar | O(n) | O(1) |
| distinctInWindow | O(n) | O(k) |

</details>

---

## 🌿 Tries

> 📂 All files: [`DataStructures/Tries/`](https://github.com/saurabh111121/dsa/tree/main/DataStructures/Tries)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Key Operations |
|---|------|----------------|
| 1 | [TrieBasic.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Tries/TrieBasic.java) | insert, search, startsWith, delete, printAll, LCP |
| 2 | [TrieAdvanced.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Tries/TrieAdvanced.java) | WordSearchII, WordBreak, ReplaceWords, MagicDictionary, Autocomplete |

</details>

<details>
<summary><b>1. TrieBasic</b> – Core Trie operations</summary>

| Operation | Time | Space |
|-----------|------|-------|
| insert | O(m) | O(m) |
| search | O(m) | O(1) |
| startsWith | O(m) | O(1) |
| delete (recursive) | O(m) | O(m) |
| delete (iterative) | O(m) | O(1) |
| countWordsWithPrefix | O(m) | O(1) |
| printAllWords | O(n·m) | O(n·m) |
| longestCommonPrefix | O(n·m) | O(1) |

*m = word length, n = number of words. Space: O(26·n·m) worst case*
</details>

<details>
<summary><b>2. TrieAdvanced</b> – Advanced Trie problems</summary>

| Problem | Time | Space |
|---------|------|-------|
| Word Search II | O(M·N·4^L) | O(W·L) |
| Word Break (DP + Trie) | O(n²) | O(n) |
| Replace Words | O(total chars) | O(total chars) |
| Magic Dictionary (build) | O(W·L) | O(W·L) |
| Magic Dictionary (search DFS) | O(26·L) | O(L) |
| Autocomplete System (insert) | O(L·unique) | O(L) |
| Autocomplete System (query) | O(L + k log k) | O(results) |

</details>

---

## 🗺️ Learning Path

> Recommended overall order: **Arrays → LinkedLists → Stacks → Queues → Trees → Graphs → Heaps → HashTables → Tries → Sets → Maps**

---

### 📦 Arrays – Suggested Order
`Traversal` → `Search` → `Update` → `Insertion` → `Deletion` → `Advanced Ops` → `Merge & Split` → `Subarray (Kadane's)` → `Matrix` → `Matrix Advanced` → `Dynamic Array`

---

### 🔗 LinkedLists – Suggested Order
`Node` → `SinglyLinkedList` → `DoublyLinkedList` → `CircularLinkedList` → `Advanced Operations`

---

### 📚 Stacks – Suggested Order
`StackUsingArray` → `StackUsingLinkedList` → `StackApplications`

> Focus order in Applications: Balanced Parens → Reverse → Postfix Eval → Infix to Postfix → NGE → Prev Greater → Stock Span → MinStack → Sort Stack → Decode String

---

### 📬 Queues – Suggested Order
`QueueUsingArray` → `QueueUsingLinkedList` → `QueueUsingTwoStacks` → `DequeOperations` → `PriorityQueueOperations`

> Focus order in PQ: Min/Max basics → Kth Largest → Kth Smallest → Merge K Sorted → Top K Frequent

---

### 🌳 Trees – Suggested Order
`TreeNode` → `Insertions` → `Traversals` → `Height/Depth` → `Node Counts` → `Properties` → `Searches` → `Deletions` → `Paths` → `LCA` → `Mirror` → `Diameter` → `Views` → `LevelSums` → `Zigzag` → `Serialization` → `LeetCode Style` → `SameTree` → `BST Validation` → `Kth Element` → `BST Iterator` → `Advanced BST Ops` → `Advanced Path Queries` → `Morris Traversal` → `AVL Tree`

---

### 📊 Graphs – Suggested Order
`GraphRepresentations` → `GraphTraversals` → `GraphShortestPath` → `GraphAdvanced`

> Focus order in Traversals: BFS → DFS Recursive → DFS Iterative → Cycle Detection Undirected → Cycle Detection Directed
> Focus order in ShortestPath: BFS Unweighted → Dijkstra → Bellman-Ford → Topo Sort (Kahn's) → Topo Sort (DFS)
> Focus order in Advanced: Union-Find → Kruskal MST → Prim MST → Number of Islands → Bipartite Check

---

### 🏔️ Heaps – Suggested Order
`MinHeap` → `MaxHeap` → `HeapApplications`

> Focus order in Applications: MedianFinder → K Closest Points → Reorganize String → Task Scheduler → K Pairs Smallest Sums

---

### #️⃣ HashTables – Suggested Order
`HashMapImplementation` → `HashCollisionStrategies` → `HashSetImplementation`

> Focus order in Collision Strategies: Separate Chaining → Linear Probing → Quadratic Probing → Double Hashing

---

### 🌿 Tries – Suggested Order
`TrieBasic` → `TrieAdvanced`

> Focus order in Advanced: Replace Words → Word Break → Magic Dictionary → Word Search II → Autocomplete System

---

### 🗺️ Maps – Suggested Order
`MapOperations` — start with HashMap basics → LRU Cache → LinkedHashMap → TreeMap range ops → Applications

---

### 🔷 Sets – Suggested Order
`SetOperations` — start with HashSet basics → Set math (Union/Intersection/Diff) → LinkedHashSet → TreeSet range ops → Applications
