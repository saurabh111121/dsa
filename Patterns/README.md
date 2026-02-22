# Patterns

This folder contains reusable coding patterns and templates in Java, organized by topic.  
Each file focuses on a single pattern family with multiple variants and worked examples.

---

## Structure

| Section | Topics |
|---------|--------|
| [ArrayPatterns](ArrayPatterns/) | Prefix Sum, 2D Prefix, Kadane's, Monotonic Stack |
| [DPPatterns](DPPatterns/) | Memo/Tab templates, Interval DP, Tree DP, Bitmask DP, Digit DP, Sliding Window DP |
| [GraphPatterns](GraphPatterns/) | BFS/DFS, Topological Sort, Shortest Paths, Union-Find, MST |
| [StringPatterns](StringPatterns/) | KMP, Rabin-Karp, Z-Algorithm, Manacher's, Trie, Anagram Window |
| [TreePatterns](TreePatterns/) | DFS/BFS templates, Path Sum, Tree DP, LCA, Serialize/Deserialize |

---

## 📦 Array Patterns

> 📂 All files: [`Patterns/ArrayPatterns/`](https://github.com/saurabh111121/dsa/tree/main/Patterns/ArrayPatterns)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Key Patterns |
|---|------|-------------|
| 1 | [PrefixSum.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/ArrayPatterns/PrefixSum.java) | 1-D / 2-D Prefix Sum, Subarray Sum = K, Divisible by K, Product Except Self |
| 2 | [KadaneMonotonicStack.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/ArrayPatterns/KadaneMonotonicStack.java) | Kadane's, Max Product, NGE, NSE, Histogram, Maximal Rectangle, Subarray Mins |

</details>

<details>
<summary><b>1. PrefixSum</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Patterns/ArrayPatterns/PrefixSum.java">PrefixSum.java</a></summary>

| Pattern | Time | Space |
|---------|------|-------|
| Build 1-D prefix | O(n) | O(n) |
| Range sum query [l, r] | O(1) | O(1) |
| Build 2-D prefix | O(m·n) | O(m·n) |
| Submatrix query | O(1) | O(1) |
| Subarray sum = k (HashMap) | O(n) | O(n) |
| Subarrays divisible by k | O(n) | O(k) |
| Equilibrium index | O(n) | O(1) |
| Product except self | O(n) | O(1) |
| Range XOR | O(1) | O(1) |

</details>

<details>
<summary><b>2. KadaneMonotonicStack</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Patterns/ArrayPatterns/KadaneMonotonicStack.java">KadaneMonotonicStack.java</a></summary>

| Pattern | Time | Space |
|---------|------|-------|
| Kadane's (max subarray sum) | O(n) | O(1) |
| Max Product Subarray | O(n) | O(1) |
| Next Greater Element | O(n) | O(n) |
| Next Greater (circular) | O(n) | O(n) |
| Next Smaller Element | O(n) | O(n) |
| Largest Rectangle in Histogram | O(n) | O(n) |
| Maximal Rectangle (binary matrix) | O(m·n) | O(n) |
| Sum of Subarray Minimums | O(n) | O(n) |
| Stock Span Problem | O(n) | O(n) |

</details>

---

## 📊 DP Patterns

> 📂 All files: [`Patterns/DPPatterns/`](https://github.com/saurabh111121/dsa/tree/main/Patterns/DPPatterns)

<details>
<summary><b>DPPatterns</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Patterns/DPPatterns/DPPatterns.java">DPPatterns.java</a></summary>

| Pattern | Example | Time | Space |
|---------|---------|------|-------|
| Top-Down Memoization template | Min cost grid | O(m·n) | O(m·n) |
| Bottom-Up Tabulation template | Min cost grid | O(m·n) | O(m·n) |
| Space Optimization (rolling array) | Min cost grid | O(m·n) | O(n) |
| DP on Intervals | Burst Balloons | O(n³) | O(n²) |
| DP on Trees (Max Independent Set) | Tree MIS | O(n) | O(n) |
| Bitmask DP | Travelling Salesman | O(2^n · n²) | O(2^n · n) |
| Digit DP | Count divisible digit sums | O(len · k · 2) | O(len · k · 2) |
| Sliding Window DP (deque) | Jump Game VI | O(n) | O(n) |

</details>

---

## 📊 Graph Patterns

> 📂 All files: [`Patterns/GraphPatterns/`](https://github.com/saurabh111121/dsa/tree/main/Patterns/GraphPatterns)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Key Patterns |
|---|------|-------------|
| 1 | [GraphBFSDFS.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/GraphPatterns/GraphBFSDFS.java) | BFS/DFS templates, Cycle Detection, Topo Sort, Bipartite, Multi-source BFS, Islands |
| 2 | [ShortestPaths.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/GraphPatterns/ShortestPaths.java) | BFS, Dijkstra, Bellman-Ford, Floyd-Warshall, 0-1 BFS, Word Ladder |
| 3 | [UnionFindMST.java](https://github.com/saurabh111121/dsa/blob/main/Patterns/GraphPatterns/UnionFindMST.java) | DSU (rank+path compression), Kruskal, Prim, Provinces, Accounts Merge |

</details>

<details>
<summary><b>1. GraphBFSDFS</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Patterns/GraphPatterns/GraphBFSDFS.java">GraphBFSDFS.java</a></summary>

| Pattern | Time | Space |
|---------|------|-------|
| BFS level-order template | O(V+E) | O(V) |
| DFS recursive / iterative | O(V+E) | O(V) |
| Connected components count | O(V+E) | O(V) |
| Cycle detection undirected (BFS+DFS) | O(V+E) | O(V) |
| Cycle detection directed (3-coloring) | O(V+E) | O(V) |
| Topological sort (Kahn's BFS) | O(V+E) | O(V) |
| Topological sort (DFS postorder) | O(V+E) | O(V) |
| Bipartite check | O(V+E) | O(V) |
| Multi-source BFS (Rotten Oranges) | O(m·n) | O(m·n) |
| Islands / Flood-fill | O(m·n) | O(m·n) |

</details>

<details>
<summary><b>2. ShortestPaths</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Patterns/GraphPatterns/ShortestPaths.java">ShortestPaths.java</a></summary>

| Algorithm | Time | Space | Notes |
|-----------|------|-------|-------|
| BFS (unweighted) | O(V+E) | O(V) | — |
| Dijkstra (PQ) | O((V+E) log V) | O(V) | No negative edges |
| Bellman-Ford | O(V·E) | O(V) | Handles negatives, detects negative cycle |
| Floyd-Warshall | O(V³) | O(V²) | All-pairs |
| 0-1 BFS (deque) | O(V+E) | O(V) | Edge weights ∈ {0,1} |
| Word Ladder (string BFS) | O(N·L²) | O(N·L) | — |

</details>

<details>
<summary><b>3. UnionFindMST</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Patterns/GraphPatterns/UnionFindMST.java">UnionFindMST.java</a></summary>

| Algorithm | Time | Space |
|-----------|------|-------|
| DSU find/union (rank + path compression) | O(α(n)) ≈ O(1) | O(n) |
| Kruskal MST | O(E log E) | O(V) |
| Prim MST (min-heap) | O((V+E) log V) | O(V) |
| Number of Provinces | O(n² · α(n)) | O(n) |
| Redundant Connection | O(n · α(n)) | O(n) |
| Accounts Merge | O(N·K log(N·K)) | O(N·K) |
| Swim in Rising Water | O(n² log n) | O(n²) |

</details>

---

## 🔤 String Patterns

> 📂 All files: [`Patterns/StringPatterns/`](https://github.com/saurabh111121/dsa/tree/main/Patterns/StringPatterns)

<details>
<summary><b>StringPatterns</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Patterns/StringPatterns/StringPatterns.java">StringPatterns.java</a></summary>

| Pattern | Time | Space |
|---------|------|-------|
| KMP failure table + search | O(n + m) | O(m) |
| Rabin-Karp rolling hash | O(n + m) avg | O(1) |
| Z-function + search | O(n + m) | O(n + m) |
| Manacher's (longest palindrome) | O(n) | O(n) |
| Manacher's (count palindromic substrings) | O(n) | O(n) |
| Trie insert / search / startsWith | O(L) | O(N·L) |
| Trie autocomplete | O(L + results) | O(N·L) |
| Anagram sliding window | O(n) | O(1) |

</details>

---

## 🌳 Tree Patterns

> 📂 All files: [`Patterns/TreePatterns/`](https://github.com/saurabh111121/dsa/tree/main/Patterns/TreePatterns)

<details>
<summary><b>TreePatterns</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Patterns/TreePatterns/TreePatterns.java">TreePatterns.java</a></summary>

| Pattern | Time | Space |
|---------|------|-------|
| DFS – Preorder / Inorder / Postorder (recursive) | O(n) | O(h) |
| Morris Inorder (O(1) space) | O(n) | O(1) |
| BFS Level Order | O(n) | O(w) |
| BFS Zigzag Level Order | O(n) | O(w) |
| Path Sum – root-to-leaf | O(n) | O(h) |
| Path Sum – all paths | O(n) | O(h) |
| Maximum Path Sum (any path) | O(n) | O(h) |
| Tree DP – Diameter | O(n) | O(h) |
| Tree DP – Longest Univalue Path | O(n) | O(h) |
| LCA (lowest common ancestor) | O(n) | O(h) |
| Symmetric / Subtree check | O(n) | O(h) |
| Serialize / Deserialize (BFS) | O(n) | O(n) |
| Flatten to Linked List (in-place) | O(n) | O(1) |

</details>

---

## 🗺️ Learning Path

> Recommended order: **ArrayPatterns → TreePatterns → GraphPatterns → DPPatterns → StringPatterns**

### 📦 Array Patterns
`PrefixSum` (1-D → 2-D → HashMap trick) → `KadaneMonotonicStack` (Kadane's → NGE/NSE → Histogram → Maximal Rectangle)

### 🌳 Tree Patterns
`DFS templates` → `BFS templates` → `Path Sum` → `Tree DP (Diameter)` → `LCA` → `Serialize/Deserialize`

### 📊 Graph Patterns
`GraphBFSDFS` (BFS → DFS → Cycle → Topo Sort → Bipartite → Islands) → `ShortestPaths` (BFS → Dijkstra → Bellman-Ford → Floyd-Warshall) → `UnionFindMST` (DSU → Kruskal → Prim)

### 📊 DP Patterns
`Memo template` → `Tabulation template` → `Space Optimization` → `Interval DP` → `Tree DP` → `Bitmask DP` → `Digit DP` → `Sliding Window DP`

### 🔤 String Patterns
`KMP` → `Rabin-Karp` → `Z-Algorithm` → `Manacher's` → `Trie` → `Anagram Window`
