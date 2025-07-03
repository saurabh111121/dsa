# DSA - Data Structure & Algorithms

This repository contains implementations of various data structures and algorithms in Java, as well as solutions to problems from coding platforms like LeetCode.

## Repository Structure

The repository is organized into the following main categories:

- **Data Structures**: Implementation of common data structures (LinkedList, Queue, Stack, Trees)
- **Algorithms**: Implementation of algorithms like Dijkstra's algorithm
- **LeetCode**: Solutions to LeetCode problems, including daily challenges
- **Interview Preparation**: Company-specific interview preparation materials
  - Samsung
  - EPAM

## File Structure Flowchart

```mermaid
graph TD
    A[src] --> B[com]
    B --> C[Algo]
    B --> D[dataStructures]
    B --> E[EPAM]
    B --> F[interview]
    B --> G[leetcode]
    
    C --> C1[GraphsDijkstraAlgo.java]
    C --> C2[GraphsDijkstraAlgoOptimised.java]
    
    D --> D1[CircularLinkedList.java]
    D --> D2[DoublyLinkedList.java]
    D --> D3[Node.java]
    D --> D4[Queue.java]
    D --> D5[SinglyLinkedList.java]
    D --> D6[Stacks.java]
    D --> D7[Trees.java]
    D --> D8[TreesBFS.java]
    D --> D9[TreesDFS.java]
    
    E --> E1[Employee.java]
    E --> E2[EPAM_Interview_Guide.md]
    E --> E3[StreamAPI.java]
    
    F --> F1[Samsung]
    F1 --> F11[Wormholes.java]
    
    G --> G1[1.two-sum.java]
    G --> G2[DailyChallenge]
    G2 --> G21[3304.find-the-k-th-character-in-string-game-i.java]
    G2 --> G22[3330.find-the-original-typed-string-i.java]
    G2 --> G23[NumberOfSubsequencesThatSatisfyTheGivenSumCondition.java]
```

## Key Components

### Data Structures
- **LinkedList**: Implementations of Singly, Doubly, and Circular LinkedLists
- **Queue**: Queue implementation
- **Stack**: Stack implementation
- **Trees**: Basic tree implementation along with BFS and DFS traversal algorithms

### Algorithms
- **Dijkstra's Algorithm**: Implementation of graph shortest path algorithm and its optimized version

### LeetCode Solutions
- Solutions to various LeetCode problems
- Daily challenge solutions

### Interview Preparation
- **Samsung**: Problem solutions specific to Samsung interviews
- **EPAM**: Interview guide and solutions specific to EPAM interviews

## Resources

- Samsung Interview Guide: [https://app.simplenote.com/p/mJ65BH](https://app.simplenote.com/p/mJ65BH)
- EPAM Interview Guide: [EPAM Interview Guide](https://github.com/saurabh111121/DSA/blob/main/src/com/EPAM/EPAM_Interview_Guide.md) (Updated till Feb 2025)
