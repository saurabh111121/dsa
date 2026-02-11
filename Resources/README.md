# Resources

This folder contains helpful reference materials, templates, and learning aids.

## Structure

### CheatSheets
Quick reference guides for:
- **Data Structures Cheat Sheet**: Time/space complexities, operations
- **Algorithms Cheat Sheet**: Common algorithms with complexity
- **Pattern Recognition Guide**: Problem patterns and when to use them
- **Complexity Analysis**: Big O reference
- **Java/Python/C++ Syntax**: Language-specific quick references
- **Common Mistakes**: Pitfalls to avoid
- **Interview Tips**: Quick preparation guide

### Templates
Reusable code templates:
- **Binary Search Template**: Different variations
- **DFS/BFS Templates**: Tree and graph traversal
- **Dynamic Programming Template**: Top-down and bottom-up
- **Sliding Window Template**: Fixed and variable size
- **Two Pointers Template**: Different patterns
- **Union Find Template**: Disjoint set implementation
- **Trie Template**: Basic trie structure
- **Graph Templates**: Adjacency list/matrix representations

### Notes
Personal learning notes:
- Study notes from courses
- Problem-solving strategies
- Algorithm insights
- Pattern observations
- Interview experiences
- Lessons learned
- Quick tips and tricks

## Cheat Sheet Contents

### Time Complexity Cheat Sheet

**Data Structure Operations**
```
Array
- Access: O(1)
- Search: O(n)
- Insertion: O(n)
- Deletion: O(n)

Linked List
- Access: O(n)
- Search: O(n)
- Insertion: O(1)
- Deletion: O(1)

Stack/Queue
- Access: O(n)
- Search: O(n)
- Insertion: O(1)
- Deletion: O(1)

Hash Table
- Access: N/A
- Search: O(1) avg, O(n) worst
- Insertion: O(1) avg, O(n) worst
- Deletion: O(1) avg, O(n) worst

Binary Search Tree
- Access: O(log n) avg, O(n) worst
- Search: O(log n) avg, O(n) worst
- Insertion: O(log n) avg, O(n) worst
- Deletion: O(log n) avg, O(n) worst

Heap
- Access: O(n)
- Search: O(n)
- Insertion: O(log n)
- Deletion: O(log n)
```

**Sorting Algorithms**
```
Bubble Sort: O(n²) time, O(1) space
Selection Sort: O(n²) time, O(1) space
Insertion Sort: O(n²) time, O(1) space
Merge Sort: O(n log n) time, O(n) space
Quick Sort: O(n log n) avg time, O(log n) space
Heap Sort: O(n log n) time, O(1) space
```

### Pattern Recognition Cheat Sheet

**Problem Keywords → Pattern**
- "contiguous subarray" → Sliding Window
- "pairs/triplets in sorted array" → Two Pointers
- "find cycle" → Fast & Slow Pointers
- "top/minimum/maximum K elements" → Heap
- "common characters/elements" → Hash Map
- "shortest path" → BFS or Dijkstra
- "connected components" → Union Find or DFS
- "permutations/combinations" → Backtracking
- "optimal solution with subproblems" → Dynamic Programming
- "binary tree level order" → BFS
- "graph cycle detection" → DFS + visited set

## Template Examples

### Binary Search Template
```java
int binarySearch(int[] arr, int target) {
    int left = 0, right = arr.length - 1;
    while (left <= right) {
        int mid = left + (right - left) / 2;
        if (arr[mid] == target) return mid;
        if (arr[mid] < target) left = mid + 1;
        else right = mid - 1;
    }
    return -1;
}
```

### Sliding Window Template
```java
int slidingWindow(int[] arr, int k) {
    int left = 0, right = 0;
    int result = 0;
    while (right < arr.length) {
        // Expand window
        // Update result
        
        while (/* window condition violated */) {
            // Shrink window from left
            left++;
        }
        right++;
    }
    return result;
}
```

## How to Use Resources

### Cheat Sheets
- Review before practice sessions
- Keep handy during problem solving
- Update with new learnings
- Use for quick revision before interviews

### Templates
- Study and understand the logic
- Customize for your coding style
- Practice implementing from memory
- Use as starting point, not final solution

### Notes
- Document learnings after each problem
- Review periodically
- Share insights with study groups
- Build your personal knowledge base

## Recommended External Resources

### Books
- "Introduction to Algorithms" by CLRS
- "Cracking the Coding Interview" by Gayle McDowell
- "Algorithm Design Manual" by Steven Skiena
- "Elements of Programming Interviews"

### Websites
- LeetCode
- HackerRank
- CodeForces
- GeeksforGeeks
- AlgoExpert
- NeetCode

### YouTube Channels
- Abdul Bari
- Tushar Roy
- Back To Back SWE
- NeetCode
- William Fiset

### Courses
- MIT 6.006 - Introduction to Algorithms
- Princeton - Algorithms Parts I & II
- Stanford - Algorithms Specialization

## Study Schedule Template

### Weekly Plan
```
Monday: Arrays & Strings
Tuesday: LinkedLists & Stacks
Wednesday: Trees & Graphs
Thursday: Dynamic Programming
Friday: Mixed Practice
Saturday: Contest/Mock Interview
Sunday: Review & Learning
```

### Pre-Interview Checklist
- [ ] Review all patterns
- [ ] Practice 5 problems of each type
- [ ] Review time/space complexities
- [ ] Practice explaining solutions
- [ ] Mock interview practice
- [ ] Review company-specific problems
- [ ] Rest and confidence building