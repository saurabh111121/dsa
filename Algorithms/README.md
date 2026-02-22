# Algorithms

This folder contains implementations of fundamental algorithms in Java, organized by technique.

---

## Structure

| Section | Topics |
|---------|--------|
| [Sorting](Sorting/) | Bubble, Selection, Insertion, Shell, Counting, Radix, Bucket, Merge, Quick, Heap, Tim, Cycle |
| [Searching](Searching/) | Linear, Sentinel, Jump, Interpolation, Exponential, Fibonacci, Ternary |
| [BinarySearch](BinarySearch/) | Classic, First/Last Occurrence, Rotated Array, 2D Matrix, BS on Answer |
| [TwoPointers](TwoPointers/) | Two/Three/Four Sum, Container Water, Rain Water, Dutch National Flag |
| [SlidingWindow](SlidingWindow/) | Fixed Window, Variable Window, Deque-based Max, Min Window Substring |
| [Recursion](Recursion/) | Factorial, Fibonacci, Tower of Hanoi, Power Set, Permutations, GCD |
| [Backtracking](Backtracking/) | Subsets, Permutations, N-Queens, Sudoku, Word Search, Palindrome Partition |
| [DivideAndConquer](DivideAndConquer/) | Merge Sort, Count Inversions, Closest Pair, Max Subarray, Majority Element |
| [Greedy](Greedy/) | Activity Selection, Intervals, Fractional Knapsack, Huffman, Task Scheduler |
| [DynamicProgramming](DynamicProgramming/) | 1D DP, 2D DP, Knapsack, LCS/LIS, DP on Strings |

---

## 🔢 Sorting

> 📂 All files: [`Algorithms/Sorting/`](https://github.com/saurabh111121/dsa/tree/main/Algorithms/Sorting)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Algorithms |
|---|------|-----------|
| 1 | [BasicSorting.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Sorting/BasicSorting.java) | Bubble, Selection, Insertion (iterative+recursive), Shell, Counting, Radix, Bucket |
| 2 | [AdvancedSorting.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Sorting/AdvancedSorting.java) | Merge (recursive+iterative), Quick (4 variants), Heap Sort, Tim Sort, Cycle Sort |

</details>

<details>
<summary><b>1. BasicSorting</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/Sorting/BasicSorting.java">BasicSorting.java</a></summary>

| Algorithm | Best | Average | Worst | Space | Stable |
|-----------|------|---------|-------|-------|--------|
| Bubble Sort (optimized) | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| Selection Sort | O(n²) | O(n²) | O(n²) | O(1) | ❌ |
| Insertion Sort (iterative) | O(n) | O(n²) | O(n²) | O(1) | ✅ |
| Insertion Sort (recursive) | O(n) | O(n²) | O(n²) | O(n) | ✅ |
| Shell Sort | O(n log n) | O(n^1.5) | O(n²) | O(1) | ❌ |
| Counting Sort | O(n+k) | O(n+k) | O(n+k) | O(k) | ✅ |
| Radix Sort (LSD) | O(d·n) | O(d·n) | O(d·n) | O(n+k) | ✅ |
| Bucket Sort | O(n+k) | O(n+k) | O(n²) | O(n) | ✅ |

</details>

<details>
<summary><b>2. AdvancedSorting</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/Sorting/AdvancedSorting.java">AdvancedSorting.java</a></summary>

| Algorithm | Best | Average | Worst | Space | Stable |
|-----------|------|---------|-------|-------|--------|
| Merge Sort (recursive) | O(n log n) | O(n log n) | O(n log n) | O(n) | ✅ |
| Merge Sort (iterative bottom-up) | O(n log n) | O(n log n) | O(n log n) | O(n) | ✅ |
| Quick Sort (last pivot) | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ |
| Quick Sort (random pivot) | O(n log n) | O(n log n) | O(n log n) | O(log n) | ❌ |
| Quick Sort (3-way / DNF) | O(n) | O(n log n) | O(n²) | O(log n) | ❌ |
| Quick Sort (iterative) | O(n log n) | O(n log n) | O(n²) | O(log n) | ❌ |
| Heap Sort | O(n log n) | O(n log n) | O(n log n) | O(1) | ❌ |
| Tim Sort | O(n) | O(n log n) | O(n log n) | O(n) | ✅ |
| Cycle Sort | O(n²) | O(n²) | O(n²) | O(1) | ❌ |

</details>

---

## 🔍 Searching

> 📂 [`Algorithms/Searching/`](https://github.com/saurabh111121/dsa/tree/main/Algorithms/Searching)

<details>
<summary><b>SearchingAlgorithms</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/Searching/SearchingAlgorithms.java">SearchingAlgorithms.java</a></summary>

| Algorithm | Requirement | Time | Space |
|-----------|-------------|------|-------|
| Linear Search (iterative) | Unsorted | O(n) | O(1) |
| Linear Search (recursive) | Unsorted | O(n) | O(n) |
| Sentinel Search | Unsorted | O(n) | O(1) |
| Jump Search | Sorted | O(√n) | O(1) |
| Interpolation Search | Uniformly distributed sorted | O(log log n) | O(1) |
| Exponential Search | Sorted | O(log n) | O(1) |
| Fibonacci Search | Sorted | O(log n) | O(1) |
| Ternary Search (iterative) | Sorted | O(log₃ n) | O(1) |
| Ternary Search (recursive) | Sorted | O(log₃ n) | O(log n) |

</details>

---

## 🎯 Binary Search

> 📂 [`Algorithms/BinarySearch/`](https://github.com/saurabh111121/dsa/tree/main/Algorithms/BinarySearch)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Topics |
|---|------|--------|
| 1 | [BinarySearchBasic.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/BinarySearch/BinarySearchBasic.java) | Classic BS, Occurrences, Rotated Array, 2D Matrix, Peak, Sqrt |
| 2 | [BinarySearchAdvanced.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/BinarySearch/BinarySearchAdvanced.java) | BS on Answer – Koko, Bouquets, Books, Cows, Median, Packages |

</details>

<details>
<summary><b>1. BinarySearchBasic</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/BinarySearch/BinarySearchBasic.java">BinarySearchBasic.java</a></summary>

| Operation | Time | Space |
|-----------|------|-------|
| Classic BS (iterative + recursive) | O(log n) | O(1) / O(log n) |
| First / Last occurrence | O(log n) | O(1) |
| Count occurrences | O(log n) | O(1) |
| Search rotated array (no dups) | O(log n) | O(1) |
| Search rotated array (with dups) | O(log n) | O(1) |
| Find min in rotated array | O(log n) | O(1) |
| Search 2D matrix | O(log(m·n)) | O(1) |
| Peak element | O(log n) | O(1) |
| sqrt(x) integer | O(log x) | O(1) |

</details>

<details>
<summary><b>2. BinarySearchAdvanced</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/BinarySearch/BinarySearchAdvanced.java">BinarySearchAdvanced.java</a></summary>

| Problem | Time | Space |
|---------|------|-------|
| BS on Answer template | O(n log(max-min)) | O(1) |
| Koko Eating Bananas | O(n log(max)) | O(1) |
| Min Days to Make Bouquets | O(n log(days)) | O(1) |
| Book Allocation (min max) | O(n log(sum)) | O(1) |
| Aggressive Cows | O(n log(range)) | O(1) |
| Median of Two Sorted Arrays | O(log(min(m,n))) | O(1) |
| Smallest Divisor | O(n log(max)) | O(1) |
| Ship Packages in D Days | O(n log(sum)) | O(1) |

</details>

---

## 👈👉 Two Pointers

> 📂 [`Algorithms/TwoPointers/`](https://github.com/saurabh111121/dsa/tree/main/Algorithms/TwoPointers)

<details>
<summary><b>TwoPointers</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/TwoPointers/TwoPointers.java">TwoPointers.java</a></summary>

| Problem | Time | Space |
|---------|------|-------|
| Two Sum (sorted) | O(n) | O(1) |
| Three Sum | O(n²) | O(1) |
| Four Sum | O(n³) | O(1) |
| Remove Duplicates (sorted) | O(n) | O(1) |
| Move Zeros | O(n) | O(1) |
| Container with Most Water | O(n) | O(1) |
| Trapping Rain Water | O(n) | O(1) |
| Valid Palindrome | O(n) | O(1) |
| Sort Colors (Dutch National Flag) | O(n) | O(1) |
| Merge Sorted Arrays In-Place | O(m+n) | O(1) |

</details>

---

## 🪟 Sliding Window

> 📂 [`Algorithms/SlidingWindow/`](https://github.com/saurabh111121/dsa/tree/main/Algorithms/SlidingWindow)

<details>
<summary><b>SlidingWindow</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/SlidingWindow/SlidingWindow.java">SlidingWindow.java</a></summary>

| Problem | Window | Time | Space |
|---------|--------|------|-------|
| Max sum fixed window | Fixed | O(n) | O(1) |
| Sliding Window Maximum (deque) | Fixed | O(n) | O(k) |
| Longest substring no-repeat | Variable | O(n) | O(k) |
| Minimum Window Substring | Variable | O(n) | O(k) |
| Longest subarray sum ≤ k | Variable | O(n) | O(1) |
| Fruits Into Baskets | Variable | O(n) | O(1) |
| K-distinct characters substring | Variable | O(n) | O(k) |
| Product < k subarrays count | Variable | O(n) | O(1) |
| Minimum subarray sum ≥ target | Variable | O(n) | O(1) |
| Find All Anagrams | Fixed | O(n) | O(1) |

</details>

---

## 🔄 Recursion

> 📂 [`Algorithms/Recursion/`](https://github.com/saurabh111121/dsa/tree/main/Algorithms/Recursion)

<details>
<summary><b>RecursionBasics</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/Recursion/RecursionBasics.java">RecursionBasics.java</a></summary>

| Problem | Approach | Time | Space |
|---------|----------|------|-------|
| Factorial | Recursive / Iterative | O(n) | O(n) / O(1) |
| Fibonacci (naive) | Recursive | O(2^n) | O(n) |
| Fibonacci (memoized) | Recursive + cache | O(n) | O(n) |
| Fibonacci (iterative) | Loop | O(n) | O(1) |
| Fast Exponentiation | Recursive | O(log n) | O(log n) |
| Sum of digits | Recursive | O(d) | O(d) |
| Reverse string | Recursive | O(n) | O(n) |
| Palindrome check | Recursive | O(n) | O(n) |
| Tower of Hanoi | Recursive | O(2^n) | O(n) |
| Power Set | Recursive | O(2^n) | O(n) |
| Climbing Stairs | Recursive + memo | O(n) | O(n) |
| GCD (Euclidean) | Recursive | O(log n) | O(log n) |
| Flatten nested list | Recursive | O(n) | O(n) |
| Generate Permutations | Recursive | O(n·n!) | O(n) |

</details>

---

## 🔙 Backtracking

> 📂 [`Algorithms/Backtracking/`](https://github.com/saurabh111121/dsa/tree/main/Algorithms/Backtracking)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Topics |
|---|------|--------|
| 1 | [SubsetsAndPermutations.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Backtracking/SubsetsAndPermutations.java) | Subsets I/II, Permutations I/II, Combination Sum I/II/III |
| 2 | [NQueens.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Backtracking/NQueens.java) | N-Queens, N-Queens II, Sudoku Solver |
| 3 | [WordSearchPalindromePartition.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Backtracking/WordSearchPalindromePartition.java) | Word Search, Palindrome Partition I/II, Rat in Maze, Letter Combos |

</details>

<details>
<summary><b>1. SubsetsAndPermutations</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/Backtracking/SubsetsAndPermutations.java">SubsetsAndPermutations.java</a></summary>

| Problem | Time | Space |
|---------|------|-------|
| Subsets I (backtrack) | O(2^n) | O(n) |
| Subsets I (bitmask) | O(n·2^n) | O(2^n) |
| Subsets II (with dups) | O(2^n) | O(n) |
| Permutations I | O(n·n!) | O(n) |
| Permutations II (with dups) | O(n·n!) | O(n) |
| Combination Sum I | O(2^target) | O(target) |
| Combination Sum II | O(2^n) | O(n) |
| Combination Sum III | O(C(9,k)) | O(k) |

</details>

<details>
<summary><b>2. NQueens</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/Backtracking/NQueens.java">NQueens.java</a></summary>

| Problem | Time | Space |
|---------|------|-------|
| N-Queens (all solutions) | O(n!) | O(n²) |
| N-Queens (bitmask) | O(n!) | O(n) |
| N-Queens II (count only) | O(n!) | O(n) |
| Sudoku Solver | O(9^81) worst | O(81) |

</details>

<details>
<summary><b>3. WordSearchPalindromePartition</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/Backtracking/WordSearchPalindromePartition.java">WordSearchPalindromePartition.java</a></summary>

| Problem | Time | Space |
|---------|------|-------|
| Word Search I | O(M·N·4^L) | O(L) |
| Palindrome Partition I (all) | O(n·2^n) | O(n²) |
| Palindrome Partition II (min cuts DP) | O(n²) | O(n²) |
| Rat in Maze | O(4^(n²)) | O(n²) |
| Letter Combinations Phone | O(4^n) | O(n) |

</details>

---

## ➗ Divide and Conquer

> 📂 [`Algorithms/DivideAndConquer/`](https://github.com/saurabh111121/dsa/tree/main/Algorithms/DivideAndConquer)

<details>
<summary><b>DivideAndConquer</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/DivideAndConquer/DivideAndConquer.java">DivideAndConquer.java</a></summary>

| Problem | Time | Space |
|---------|------|-------|
| Merge Sort | O(n log n) | O(n) |
| Count Inversions | O(n log n) | O(n) |
| Max Subarray (D&C) | O(n log n) | O(log n) |
| Max Subarray (Kadane's) | O(n) | O(1) |
| Closest Pair of Points | O(n log n) | O(n) |
| Majority Element (D&C) | O(n log n) | O(log n) |
| Majority Element (Boyer-Moore) | O(n) | O(1) |

</details>

---

## 💰 Greedy

> 📂 [`Algorithms/Greedy/`](https://github.com/saurabh111121/dsa/tree/main/Algorithms/Greedy)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Topics |
|---|------|--------|
| 1 | [ActivitySelectionIntervals.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Greedy/ActivitySelectionIntervals.java) | Activity Selection, Merge Intervals, Meeting Rooms, Jump Game, Gas Station |
| 2 | [GreedyClassics.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/Greedy/GreedyClassics.java) | Fractional Knapsack, Huffman Encoding, Min Coins, Task Scheduler, Candy |

</details>

<details>
<summary><b>1. ActivitySelectionIntervals</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/Greedy/ActivitySelectionIntervals.java">ActivitySelectionIntervals.java</a></summary>

| Problem | Time | Space |
|---------|------|-------|
| Activity Selection | O(n log n) | O(1) |
| Merge Intervals | O(n log n) | O(n) |
| Meeting Rooms I (can attend) | O(n log n) | O(1) |
| Meeting Rooms II (min rooms) | O(n log n) | O(n) |
| Non-Overlapping Intervals | O(n log n) | O(1) |
| Jump Game I (can reach) | O(n) | O(1) |
| Jump Game II (min jumps) | O(n) | O(1) |
| Gas Station | O(n) | O(1) |

</details>

<details>
<summary><b>2. GreedyClassics</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/Greedy/GreedyClassics.java">GreedyClassics.java</a></summary>

| Problem | Time | Space |
|---------|------|-------|
| Fractional Knapsack | O(n log n) | O(1) |
| Huffman Encoding | O(n log n) | O(n) |
| Min Coins (greedy, canonical) | O(n log n) | O(1) |
| Task Scheduler | O(n) | O(1) |
| Assign Cookies | O(n log n) | O(1) |
| Hand of Straights | O(n log n) | O(n) |
| Partition Labels | O(n) | O(1) |
| Candy | O(n) | O(n) |

</details>

---

## 📊 Dynamic Programming

> 📂 [`Algorithms/DynamicProgramming/`](https://github.com/saurabh111121/dsa/tree/main/Algorithms/DynamicProgramming)

<details>
<summary><b>📋 All Files – Quick Reference (click to expand)</b></summary>

| # | File | Topics |
|---|------|--------|
| 1 | [DP1D.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/DP1D.java) | Climbing Stairs, House Robber I/II, Decode Ways, Coin Change, Word Break |
| 2 | [DP2D.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/DP2D.java) | Unique Paths I/II, Min Path Sum, Dungeon Game, Triangle, Maximal Square |
| 3 | [Knapsack.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/Knapsack.java) | 0/1 Knapsack, Unbounded Knapsack, Subset Sum, Equal Partition, Rod Cutting |
| 4 | [LCS_LIS.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/LCS_LIS.java) | LCS, SCS, LIS O(n log n), Longest Bitonic, Russian Doll Envelopes |
| 5 | [DPStrings.java](https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/DPStrings.java) | Edit Distance, Wildcard, Regex, Interleaving, Distinct Subseq, Scramble |

</details>

<details>
<summary><b>1. DP1D</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/DP1D.java">DP1D.java</a></summary>

| Problem | Time | Space (Opt) |
|---------|------|-------------|
| Climbing Stairs | O(n) | O(1) |
| House Robber I | O(n) | O(1) |
| House Robber II (circular) | O(n) | O(1) |
| Min Cost Climbing Stairs | O(n) | O(1) |
| Decode Ways | O(n) | O(1) |
| Coin Change (min coins) | O(n·amount) | O(amount) |
| Coin Change II (ways) | O(n·amount) | O(amount) |
| Perfect Squares | O(n·√n) | O(n) |
| Word Break | O(n²) | O(n) |

</details>

<details>
<summary><b>2. DP2D</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/DP2D.java">DP2D.java</a></summary>

| Problem | Time | Space (Opt) |
|---------|------|-------------|
| Unique Paths I | O(m·n) | O(n) |
| Unique Paths II (with obstacles) | O(m·n) | O(n) |
| Minimum Path Sum | O(m·n) | O(n) |
| Dungeon Game | O(m·n) | O(n) |
| Triangle (min path) | O(n²) | O(n) |
| Maximal Square | O(m·n) | O(n) |
| Count Squares | O(m·n) | O(m·n) |

</details>

<details>
<summary><b>3. Knapsack</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/Knapsack.java">Knapsack.java</a></summary>

| Problem | Time | Space (Opt) |
|---------|------|-------------|
| 0/1 Knapsack | O(n·W) | O(W) |
| Unbounded Knapsack | O(n·W) | O(W) |
| Subset Sum | O(n·sum) | O(sum) |
| Equal Partition | O(n·sum/2) | O(sum/2) |
| Count Subsets with Sum | O(n·sum) | O(sum) |
| Min Subset Difference | O(n·sum) | O(sum) |
| Rod Cutting | O(n²) | O(n) |
| Target Sum (±assign) | O(n·sum) | O(sum) |

</details>

<details>
<summary><b>4. LCS_LIS</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/LCS_LIS.java">LCS_LIS.java</a></summary>

| Problem | Time | Space |
|---------|------|-------|
| LCS (tabulation) | O(m·n) | O(m·n) |
| LCS (space-optimized) | O(m·n) | O(n) |
| Print LCS | O(m·n) | O(m·n) |
| Shortest Common Supersequence | O(m·n) | O(m·n) |
| LIS O(n²) | O(n²) | O(n) |
| LIS O(n log n) (patience sort) | O(n log n) | O(n) |
| Number of LIS | O(n²) | O(n) |
| Russian Doll Envelopes | O(n log n) | O(n) |
| Longest Bitonic Subsequence | O(n²) | O(n) |

</details>

<details>
<summary><b>5. DPStrings</b> – <a href="https://github.com/saurabh111121/dsa/blob/main/Algorithms/DynamicProgramming/DPStrings.java">DPStrings.java</a></summary>

| Problem | Time | Space (Opt) |
|---------|------|-------------|
| Edit Distance | O(m·n) | O(n) |
| Wildcard Matching | O(m·n) | O(m·n) |
| Regular Expression Matching | O(m·n) | O(m·n) |
| Interleaving Strings | O(m·n) | O(n) |
| Distinct Subsequences | O(m·n) | O(n) |
| Scramble String | O(n⁴) | O(n³) |

</details>

---

## 🗺️ Learning Path

> Recommended order: **Sorting → Searching → BinarySearch → TwoPointers → SlidingWindow → Recursion → Backtracking → DivideAndConquer → Greedy → DynamicProgramming**

### 🔢 Sorting
`BasicSorting` (Bubble→Insertion→Selection→Shell→Counting→Radix→Bucket) → `AdvancedSorting` (MergeSort→QuickSort→HeapSort→TimSort→CycleSort)

### 🎯 Binary Search
`BinarySearchBasic` → `BinarySearchAdvanced` (BS on Answer template)

### 👈👉 Two Pointers & 🪟 Sliding Window
`TwoPointers` → `SlidingWindow` (fixed window → variable window → deque-based)

### 🔄 Recursion & 🔙 Backtracking
`RecursionBasics` → `SubsetsAndPermutations` → `NQueens` → `WordSearchPalindromePartition`

### 📊 Dynamic Programming
`DP1D` → `DP2D` → `Knapsack` → `LCS_LIS` → `DPStrings`
