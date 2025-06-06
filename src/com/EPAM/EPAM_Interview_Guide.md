# EPAM Interview Guide

A comprehensive collection of topics, questions, and programming challenges commonly featured in EPAM technical interviews.

## Table of Contents

- [Java Core Concepts](#java-core-concepts)
- [Java 8 Features](#java-8-features)
- [Object-Oriented Programming](#object-oriented-programming)
- [Collections Framework](#collections-framework)
- [Spring & Hibernate](#spring--hibernate)
- [Microservices Architecture](#microservices-architecture)
- [Data Structures and Algorithms](#data-structures-and-algorithms)
- [Database Concepts](#database-concepts)
- [REST API Best Practices](#rest-api-best-practices)
- [CI/CD and DevOps](#cicd-and-devops)
- [Automation Testing](#automation-testing)
- [Example Interview Formats](#example-interview-formats)

## Java Core Concepts

### Core Concepts
- Exception hierarchy in Java
- Difference between final, finally, and finalize
- Method overloading vs overriding
- Access modifiers (private, public, protected)
- Internal working of JVM and garbage collection
- Immutable classes and how to create them
- Records in Java

### Multithreading
- Multithreading concepts
- Callable interface
- Volatile and synchronized keywords
- Implementing concurrent login system

## Java 8 Features

### Functional Interfaces
- Types of functional interfaces introduced in Java 8
- Implementing and using functional interfaces

### Lambda Expressions
- Uses and examples of lambda expressions
- Benefits of lambda expressions

### Stream API
- Stream operations (map, filter, reduce, etc.)
- Terminal vs intermediate operations
- Common Stream API use cases:
  - Finding maximum element in collection
  - Filtering elements based on condition
  - Sorting elements
  - Collecting results using Collectors
  - Grouping and counting elements

### Optional
- Difference between Optional.of() and Optional.ofNullable()
- Proper use of Optional

### Code Examples

```java
// Finding employees with salary above threshold
employees.stream()
    .filter(emp -> emp.getSalary() > salaryThreshold)
    .map(Employee::getName)
    .forEach(System.out::println);

// Creating a new list with salary increase
List<Employee> increasedSalaryEmployees = employees.stream()
    .map(emp -> new Employee(emp.getId(), emp.getName(), emp.getSalary() * 1.10))
    .collect(Collectors.toList());

// Finding employee with highest salary
Optional<Employee> highestPaidEmployee = employees.stream()
    .max(Comparator.comparingDouble(Employee::getSalary));

// Sorting numbers in descending order
List<Integer> sortedDesc = numbers.stream()
    .sorted(Comparator.reverseOrder())
    .collect(Collectors.toList());

// Finding odd numbers and sorting them
List<Integer> sortedOddNumbers = numbers.stream()
    .filter(n -> n % 2 != 0)
    .sorted()
    .collect(Collectors.toList());

// Count frequency of words in a string
Map<String, Long> wordFrequency = Arrays.stream(s.split(" "))
    .collect(Collectors.groupingBy(String::toLowerCase, Collectors.counting()));

// Finding words starting with '@'
Set<String> atWords = Arrays.stream(str)
    .map(s -> s.split(" "))
    .flatMap(Arrays::stream)
    .filter(s -> s.startsWith("@"))
    .collect(Collectors.toSet());

// Finding most frequent letters in a string
Map<Character, Long> letterFrequency = str.chars()
    .mapToObj(c -> (char) c)
    .collect(Collectors.groupingBy(c -> c, Collectors.counting()));
```

## Object-Oriented Programming

### SOLID Principles
- Single Responsibility Principle
- Open/Closed Principle
- Liskov Substitution Principle
- Interface Segregation Principle
- Dependency Inversion Principle

### Design Patterns
- Singleton Pattern
- Observer Pattern
- Factory Pattern
- Strategy Pattern
- Adapter Pattern

### OOP Concepts
- Inheritance
- Polymorphism (types and how they work)
- Encapsulation
- Abstraction
- Abstract classes vs interfaces
- Why abstract classes can have constructors

### Clean Code Principles
- DRY (Don't Repeat Yourself)
- YAGNI (You Aren't Gonna Need It)
- KISS (Keep It Simple, Stupid)
- Code review practices

## Collections Framework

### Collections Hierarchy
- Collection interfaces and implementations
- List, Set, Map, Queue interfaces

### Map Implementations
- HashMap internal working
- ConcurrentHashMap vs Synchronized HashMap
- HashSet vs HashMap
- HashTable vs HashSet

### Comparators
- Comparable vs Comparator interfaces
- Implementing custom sorting

## Spring & Hibernate

### Spring Framework
- Spring Bean lifecycle
- Spring Bean scopes
- IOC containers types
- Dependency injection (Constructor vs Setter injection)
- Spring Boot annotations
- @Qualifier vs @Primary annotations

### Hibernate
- JPA vs Hibernate differences
- Lazy loading in Hibernate

### Transaction Management
- @Transactional annotation (internal working)
- Transaction isolation levels

## Microservices Architecture

### Architecture Components
- Service Registry (heartbeat message, configuration)
- API Gateway (internal working, routing)
- Config Server
- Service Discovery

### Microservice Patterns
- Two-Phase Commit (2PC)
- SAGA Pattern
- CQRS Pattern
- CAP Theorem

### Communication Between Microservices
- Synchronous vs Asynchronous
- RESTTemplate vs FeignClient

### Fault Tolerance & Monitoring
- Monitoring techniques
- Log aggregation (ELK)
- Fault tolerance strategies

### Advantages Over Monolithic Architecture

## Data Structures and Algorithms

### Common DSA Topics
- Trees and tree traversal
- Graphs and graph algorithms
- Hash Maps implementation
- Red-Black Trees

### Example Coding Problems
- Group Anagrams
  - Input: strs = ["eat","tea","tan","ate","nat","bat"]
  - Output: [["bat"],["nat","tan"],["ate","eat","tea"]]

- Finding unique words with specific pattern
  - Example: Words starting with '@' in sentences

- Longest substring without repeating characters
  - Example: Input: "abcabcbb", Output: 3 (The answer is "abc")

- Edit Distance
  - Finding minimum operations to transform one string to another

- Trapping Rain Water
  - Calculating trapped water between heights

- Partition Equal Subset Sum
  - Determining if array can be partitioned into equal-sum subsets

- Search in Rotated Sorted Array
  - Modified binary search approach

- Finding pairs with target sum
  - Finding all element pairs whose sum equals a given number

## Database Concepts

### SQL
- Joins (types and usage)
- Normalization (why normalize, types)

### NoSQL
- DynamoDB and other NoSQL databases

## REST API Best Practices

- Best practices for REST API design
- Status codes (e.g., difference between 401 and 403)
- Authentication (OAuth 1.0 vs OAuth 2.0)
- Content types

## CI/CD and DevOps

- CI/CD pipeline for Java applications
- Jenkins (parameterized pipeline jobs)
- Docker
- Git commands (rebase vs merge)

## Automation Testing

### Testing Frameworks
- JUnit vs TestNG
- RestAssured library
- Mockito

### Testing Concepts
- Unit testing
- Different testing types
- Test plans
- Bug/defect logging
- Priority vs Severity

### Selenium
- Handling multiple windows
- Capturing screenshots
- Synchronization in automation scripts
- Fluent wait syntax

## Example Interview Formats

### First Round (DSA + Technical): 1.5 hours
- Java features (Java 8, 11)
- Functional interfaces and lambda expressions
- DSA problems
- Stream API problems

### Second Round (DSA + Technical): 1.5 hours
- Introduction (skills, tech-stack, projects)
- Java 8 features
- Architecture patterns (CAP theorem, SAGA)
- Spring annotations
- Database normalization
- Design problems
- DSA problems

### Third Round (Techno-Managerial): 1 hour
- Project architecture discussion
- Microservices components in depth
- Communication patterns
- Monitoring and logging
- CI/CD pipeline
- Implementation challenges
