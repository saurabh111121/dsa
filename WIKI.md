# DSA Wiki – Data Structures & Algorithms

> **GitHub Repository:** [saurabh111121/dsa](https://github.com/saurabh111121/dsa)
> **Language:** Java · **IDE:** VS Code

---

## Table of Contents

1. [Overview](#overview)
2. [Data Structures](#data-structures)
3. [Algorithms](#algorithms)
4. [Patterns](#patterns)
5. [Problem Solving](#problem-solving)
6. [Learning Path](#learning-path)

---

## Overview

<!-- SECTION:OVERVIEW -->
A comprehensive Java repository covering every major Data Structure and Algorithm topic — from basic array manipulation to AVL trees, graph shortest-paths, DP, and advanced coding patterns.

### Repository Stats
| Category | Count |
|----------|-------|
| Data Structure categories | 11 |
| Algorithm categories | 10 |
| Pattern categories | 5 |
| Total Java source files | 60+ |

### Folder Map
```
dsa/
├── DataStructures/   → Arrays, LinkedLists, Stacks, Queues, Trees, Graphs,
│                       Heaps, HashTables, Tries, Sets, Maps
├── Algorithms/       → Sorting, Searching, BinarySearch, TwoPointers,
│                       SlidingWindow, Recursion, Backtracking,
│                       DivideAndConquer, Greedy, DynamicProgramming
├── Patterns/         → ArrayPatterns, StringPatterns, TreePatterns,
│                       GraphPatterns, DPPatterns
└── ProblemSolving/   → LeetCode, Interviews (EPAM, Samsung), CompanySpecific
```
<!-- END:OVERVIEW -->

---

## Data Structures

<!-- SECTION:DS -->
### 📦 Arrays
| File | Key Topics |
|------|-----------|
| [ArrayInsertionOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayInsertionOperations.java) | Insert at end/start/index |
| [ArrayDeletionOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayDeletionOperations.java) | Delete from end/start/index |
| [ArraySearchOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArraySearchOperations.java) | Linear Search, Binary Search |
| [ArrayUpdateOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayUpdateOperations.java) | Update at index O(1) |
| [ArrayTraversalOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayTraversalOperations.java) | for loop, for-each |
| [ArrayAdvancedOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayAdvancedOperations.java) | Reverse, Rotate L/R, Max/Min/Sum |
| [ArrayMergeSplit.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/ArrayMergeSplit.java) | Merge two arrays, Split at index |
| [SubarrayOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/SubarrayOperations.java) | Kadane's Algorithm |
| [MatrixOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/MatrixOperations.java) | Traversal, Sum, 90° Rotation |
| [MatrixAdvancedOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/MatrixAdvancedOperations.java) | Transpose, Diagonal Sums, Spiral |
| [DynamicArray.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Arrays/DynamicArray.java) | Auto-resizing ArrayList simulation |

---

### 🔗 LinkedLists
| File | Key Topics |
|------|-----------|
| [Node.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/LinkedLists/Node.java) | Base node (val, next, prev) |
| [SinglyLinkedList.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/LinkedLists/SinglyLinkedList.java) | Insert/Delete/Search/Reverse/Middle/NthFromEnd |
| [DoublyLinkedList.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/LinkedLists/DoublyLinkedList.java) | Bidirectional, O(1) delete given node |
| [CircularLinkedList.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/LinkedLists/CircularLinkedList.java) | Floyd's cycle detection |
| [LinkedListAdvancedOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/LinkedLists/LinkedListAdvancedOperations.java) | Merge, Rotate, Palindrome, Sort |

---

### 📚 Stacks
| File | Key Topics |
|------|-----------|
| [StackUsingArray.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Stacks/StackUsingArray.java) | FixedStack, DynamicStack |
| [StackUsingLinkedList.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Stacks/StackUsingLinkedList.java) | Node-backed O(1) push/pop |
| [StackApplications.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Stacks/StackApplications.java) | Balanced parens, NGE, MinStack, Postfix |

---

### 📬 Queues
| File | Key Topics |
|------|-----------|
| [QueueUsingArray.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Queues/QueueUsingArray.java) | SimpleQueue, CircularQueue |
| [QueueUsingLinkedList.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Queues/QueueUsingLinkedList.java) | All ops O(1) |
| [QueueUsingTwoStacks.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Queues/QueueUsingTwoStacks.java) | Lazy/Eager queue, StackUsingQueues |
| [PriorityQueueOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Queues/PriorityQueueOperations.java) | Min/Max PQ, Kth Largest, Merge K sorted |
| [DequeOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Queues/DequeOperations.java) | Sliding Window Max, Palindrome |

---

### 🌳 Trees
| File | Key Topics |
|------|-----------|
| [TreeNode.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/TreeNode.java) | Base TreeNode class |
| [BinaryTreeAllInsertions.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeAllInsertions.java) | BST rec/itr, Level Order, Special |
| [BinaryTreeAllDeletions.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeAllDeletions.java) | BST rec/itr, Level Order Delete |
| [BinaryTreeAllSearches.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeAllSearches.java) | BST, DFS, BFS, Parent, Level |
| [BinaryTreeTraversals.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeTraversals.java) | Inorder, Preorder, Postorder, Level Order |
| [BinaryTreeHeightDepth.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeHeightDepth.java) | Height rec/itr, Depth, Max/Min Depth |
| [BinaryTreeNodeCounts.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeNodeCounts.java) | Total, Leaf, Non-Leaf counts |
| [BinaryTreeProperties.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeProperties.java) | Balanced, Complete, Full, Perfect |
| [BinaryTreeDiameter.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeDiameter.java) | Diameter naive O(n²) & optimized O(n) |
| [BinaryTreeMirror.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeMirror.java) | Mirror/Invert rec & BFS |
| [BinaryTreePaths.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreePaths.java) | Root-to-leaf, Path sum, Max path sum |
| [BinaryTreeLCA.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeLCA.java) | LCA in Binary Tree & BST |
| [BinaryTreeViews.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeViews.java) | Top/Bottom/Left/Right/Diagonal views |
| [TreeLevelSums.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/TreeLevelSums.java) | Horizontal (level) & Vertical sums |
| [ZigZagTraversal.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/ZigZagTraversal.java) | Zigzag level order using Deque |
| [BinaryTreeSerialization.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeSerialization.java) | Serialize/Deserialize to/from string |
| [BinaryTreeFileSerialization.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeFileSerialization.java) | Serialize/Deserialize to/from file |
| [BinaryTreeLeetCodeStyle.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BinaryTreeLeetCodeStyle.java) | Build from list, Serialize to list |
| [SameTree.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/SameTree.java) | LeetCode #100 isSameTree |
| [BSTValidation.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BSTValidation.java) | Validate BST rec & iterative inorder |
| [BSTKthElement.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BSTKthElement.java) | Kth Smallest & Kth Largest |
| [BSTIterator.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/BSTIterator.java) | next() / hasNext() – lazy stack inorder |
| [AdvancedBSTOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/AdvancedBSTOperations.java) | Successor, Predecessor, Floor, Ceiling, Range |
| [AdvancedPathQueries.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/AdvancedPathQueries.java) | Root-to-leaf & anywhere paths with sum |
| [ThreadedBSTMorris.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/ThreadedBSTMorris.java) | Morris inorder O(1) space |
| [AVLTree.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Trees/AVLTree.java) | Insert/Delete with LL/RR/LR/RL rotations |

---

### 📊 Graphs
| File | Key Topics |
|------|-----------|
| [GraphRepresentations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Graphs/GraphRepresentations.java) | AdjMatrix, AdjList, WeightedAdjList, EdgeList |
| [GraphTraversals.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Graphs/GraphTraversals.java) | BFS, DFS, Cycle Detection |
| [GraphShortestPath.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Graphs/GraphShortestPath.java) | Dijkstra, Bellman-Ford, Topo Sort |
| [GraphAdvanced.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Graphs/GraphAdvanced.java) | Union-Find, Kruskal/Prim MST, Islands, Bipartite |

---

### 🏔️ Heaps · #️⃣ HashTables · 🌿 Tries · 🗺️ Maps · 🔷 Sets
| File | Key Topics |
|------|-----------|
| [MinHeap.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Heaps/MinHeap.java) | insert, extractMin, buildHeap O(n), heapSort |
| [MaxHeap.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Heaps/MaxHeap.java) | insert, extractMax, in-place heapSort |
| [HeapApplications.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Heaps/HeapApplications.java) | MedianFinder, K Closest, Task Scheduler |
| [HashMapImplementation.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/HashTables/HashMapImplementation.java) | Custom HashMap, Separate Chaining |
| [HashSetImplementation.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/HashTables/HashSetImplementation.java) | Custom HashSet, Linear Probing |
| [HashCollisionStrategies.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/HashTables/HashCollisionStrategies.java) | Chaining, Linear/Quadratic/Double Hashing |
| [TrieBasic.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Tries/TrieBasic.java) | insert, search, startsWith, LCP |
| [TrieAdvanced.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Tries/TrieAdvanced.java) | Word Search II, Word Break, Autocomplete |
| [MapOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Maps/MapOperations.java) | HashMap, LinkedHashMap, TreeMap, LRU Cache |
| [SetOperations.java](https://github.com/saurabh111121/dsa/blob/main/DataStructures/Sets/SetOperations.java) | HashSet, TreeSet, Union/Intersection/Diff |
<!-- END:DS -->

---

## Algorithms

<!-- SECTION:ALGO -->
### 🔢 Sorting
| File | Algorithms |
|------|-----------|
| [BasicSorting.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Sorting/BasicSorting.java) | Bubble, Selection, Insertion, Shell, Counting, Radix, Bucket |
| [AdvancedSorting.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Sorting/AdvancedSorting.java) | Merge, Quick (4 variants), Heap, Tim, Cycle |

### 🔍 Searching · 🎯 Binary Search
| File | Algorithms |
|------|-----------|
| [SearchingAlgorithms.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Searching/SearchingAlgorithms.java) | Linear, Sentinel, Jump, Interpolation, Exponential, Fibonacci, Ternary |
| [BinarySearchBasic.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/BinarySearch/BinarySearchBasic.java) | Classic BS, Occurrences, Rotated Array, 2D Matrix, Peak, Sqrt |
| [BinarySearchAdvanced.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/BinarySearch/BinarySearchAdvanced.java) | BS on Answer – Koko, Bouquets, Books, Cows, Median, Packages |

### 👈👉 Two Pointers · 🪟 Sliding Window
| File | Problems |
|------|---------|
| [TwoPointers.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/TwoPointers/TwoPointers.java) | Two/Three/Four Sum, Container Water, Rain Water, Dutch Flag |
| [SlidingWindow.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/SlidingWindow/SlidingWindow.java) | Max sum fixed, Sliding Max (deque), Longest no-repeat, Min Window Substring |

### 🔄 Recursion · 🔙 Backtracking · ➗ Divide & Conquer
| File | Problems |
|------|---------|
| [RecursionBasics.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Recursion/RecursionBasics.java) | Factorial, Fibonacci, Hanoi, Power Set, Permutations, GCD |
| [SubsetsAndPermutations.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Backtracking/SubsetsAndPermutations.java) | Subsets I/II, Permutations I/II, Combination Sum I/II/III |
| [NQueens.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Backtracking/NQueens.java) | N-Queens, N-Queens II, Sudoku Solver |
| [WordSearchPalindromePartition.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Backtracking/WordSearchPalindromePartition.java) | Word Search, Palindrome Partition, Rat in Maze, Letter Combos |
| [DivideAndConquer.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/DivideAndConquer/DivideAndConquer.java) | Merge Sort, Count Inversions, Closest Pair, Max Subarray, Majority Element |

### 💰 Greedy · 📊 Dynamic Programming
| File | Problems |
|------|---------|
| [ActivitySelectionIntervals.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Greedy/ActivitySelectionIntervals.java) | Activity Selection, Merge Intervals, Meeting Rooms, Jump Game, Gas Station |
| [GreedyClassics.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Greedy/GreedyClassics.java) | Fractional Knapsack, Huffman, Min Coins, Task Scheduler, Candy |
| [DP1D.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/DP1D.java) | Climbing Stairs, House Robber I/II, Decode Ways, Coin Change, Word Break |
| [DP2D.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/DP2D.java) | Unique Paths, Min Path Sum, Dungeon, Triangle, Maximal Square |
| [Knapsack.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/Knapsack.java) | 0/1 Knapsack, Unbounded, Subset Sum, Equal Partition, Rod Cutting |
| [LCS_LIS.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/LCS_LIS.java) | LCS, SCS, LIS O(n log n), Bitonic, Russian Doll Envelopes |
| [DPStrings.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/DPStrings.java) | Edit Distance, Wildcard, Regex, Interleaving, Distinct Subsequences |
<!-- END:ALGO -->

---

## Patterns

<!-- SECTION:PATTERNS -->
| File | Key Patterns |
|------|-------------|
| [PrefixSum.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/ArrayPatterns/PrefixSum.java) | 1-D/2-D Prefix Sum, Subarray Sum=K, Divisible by K, Product Except Self |
| [KadaneMonotonicStack.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/ArrayPatterns/KadaneMonotonicStack.java) | Kadane's, Max Product, NGE/NSE, Histogram, Maximal Rectangle |
| [StringPatterns.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/StringPatterns/StringPatterns.java) | KMP, Rabin-Karp, Z-Algorithm, Manacher's, Trie, Anagram Window |
| [TreePatterns.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/TreePatterns/TreePatterns.java) | DFS/BFS templates, Path Sum, Tree DP, LCA, Serialize/Deserialize |
| [GraphBFSDFS.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/GraphPatterns/GraphBFSDFS.java) | BFS/DFS templates, Cycle Detection, Topo Sort, Bipartite, Islands |
| [ShortestPaths.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/GraphPatterns/ShortestPaths.java) | BFS, Dijkstra, Bellman-Ford, Floyd-Warshall, 0-1 BFS, Word Ladder |
| [UnionFindMST.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/GraphPatterns/UnionFindMST.java) | DSU (rank+path compression), Kruskal, Prim, Accounts Merge |
| [DPPatterns.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/DPPatterns/DPPatterns.java) | Memo/Tab templates, Interval DP, Tree DP, Bitmask DP, Digit DP, Sliding Window DP |
<!-- END:PATTERNS -->

---

## Problem Solving

<!-- SECTION:PS -->
| File | Description |
|------|------------|
| [Solution.java](https://github.com/saurabh111121/dsa/blob/main/ProblemSolving/LeetCode/Solution.java) | LeetCode solutions |
| [Employee.java](https://github.com/saurabh111121/dsa/blob/main/ProblemSolving/Interviews/EPAM/Employee.java) | EPAM – Employee model |
| [StreamAPI.java](https://github.com/saurabh111121/dsa/blob/main/ProblemSolving/Interviews/EPAM/StreamAPI.java) | EPAM – Java Stream API problems |
| [EPAM_Interview_Guide.md](https://github.com/saurabh111121/dsa/blob/main/ProblemSolving/Interviews/EPAM/EPAM_Interview_Guide.md) | EPAM Interview Guide (Feb 2025) |
| [Wormholes.java](https://github.com/saurabh111121/dsa/blob/main/ProblemSolving/Interviews/Samsung/Wormholes.java) | Samsung – Wormholes problem |

### External Interview Resources
- [Samsung Interview Guide](https://app.simplenote.com/p/mJ65BH)
- [EPAM Interview Guide (GitHub)](https://github.com/saurabh111121/DSA/blob/main/src/com/interview/EPAM/EPAM_Interview_Guide.md)
<!-- END:PS -->

---

## Learning Path

<!-- SECTION:PATH -->
### Recommended Order

#### Beginner
1. **Arrays** – Traversal → Search → Update → Insertion → Deletion → Advanced → Merge/Split → Kadane's → Matrix → Dynamic Array
2. **LinkedLists** – Node → Singly → Doubly → Circular → Advanced
3. **Stacks** – Array-based → LinkedList-based → Applications
4. **Queues** – Array → LinkedList → Two-Stacks → Deque → PriorityQueue

#### Intermediate
5. **Trees** – TreeNode → Insert → Traversals → Height → Counts → Properties → Search → Delete → Paths → LCA → Mirror → Diameter → Views → Sums → ZigZag → Serialization → SameTree → BST Validation → Kth Element → BST Iterator → Advanced BST → Morris → AVL
6. **Recursion** → **Backtracking** – RecursionBasics → Subsets/Permutations → NQueens → WordSearch/Palindrome
7. **Binary Search** – Basic → Advanced (BS on Answer)
8. **Two Pointers & Sliding Window**

#### Advanced
9. **Graphs** – Representations → Traversals → Shortest Paths → Advanced (MST, Union-Find)
10. **Heaps** – MinHeap → MaxHeap → Applications
11. **HashTables** – HashMap → Collision Strategies → HashSet
12. **Tries** – Basic → Advanced
13. **Greedy** – Activity Selection/Intervals → Greedy Classics
14. **Dynamic Programming** – 1D → 2D → Knapsack → LCS/LIS → DP Strings
15. **Patterns** – Array → Tree → Graph → DP → String

### Complexity Quick Reference
| Operation | Best DS |
|-----------|---------|
| O(1) lookup | HashMap / HashSet |
| O(log n) insert/search | BST / AVL / TreeMap |
| O(1) push/pop | Stack (Array-backed) |
| O(log n) min/max | Heap |
| O(h) BST operations | AVL (h = log n guaranteed) |
| O(m) string prefix ops | Trie |
<!-- END:PATH -->
