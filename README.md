# DSA - Data Structure & Algorithms

This repository contains implementations of various data structures and algorithms in Java, as well as solutions to problems from coding platforms like LeetCode.

## Folder Structure Dependency Graph

```mermaid
flowchart LR
    src --> com
    com --> Algo
    com --> dataStructures
    com --> interview
    com --> leetcode
    interview --> EPAM
    interview --> Samsung
    leetcode --> DailyChallenge
    
    %% Styling
    classDef rootNode fill:#f96,stroke:#333,stroke-width:2px;
    classDef folderNode fill:#b8d4ff,stroke:#333,stroke-width:1px;
    classDef subfolderNode fill:#d8e8ff,stroke:#333,stroke-width:1px;
    
    class src rootNode;
    class com,Algo,dataStructures,interview,leetcode folderNode;
    class EPAM,Samsung,DailyChallenge subfolderNode;
```

## Resources

- Samsung Interview Guide: [https://app.simplenote.com/p/mJ65BH](https://app.simplenote.com/p/mJ65BH)
- EPAM Interview Guide: [EPAM Interview Guide](https://github.com/saurabh111121/DSA/blob/main/src/com/EPAM/EPAM_Interview_Guide.md) (Updated till Feb 2025)
