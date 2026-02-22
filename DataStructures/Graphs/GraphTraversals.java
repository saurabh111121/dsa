package DataStructures.Graphs;

import java.util.*;

/*
===========================================================
Graph Traversals
===========================================================

1) BFS - Breadth First Search (Iterative)
2) DFS - Depth First Search
   - Recursive
   - Iterative (using Stack)
3) BFS - Level Order (returns levels separately)
4) Detect Cycle in Undirected Graph (BFS & DFS)
5) Detect Cycle in Directed Graph (DFS with color)

===========================================================
*/

public class GraphTraversals {

    // =====================================================
    // 1️⃣ BFS - ITERATIVE
    // =====================================================
    public static List<Integer> bfs(List<List<Integer>> graph, int start) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        List<Integer> result = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int node = queue.poll();
            result.add(node);

            for (int neighbor : graph.get(node)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }
        return result;
    }

    // =====================================================
    // 2️⃣ DFS - RECURSIVE
    // =====================================================
    public static List<Integer> dfsRecursive(List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        List<Integer> result = new ArrayList<>();
        dfsHelper(graph, start, visited, result);
        return result;
    }

    private static void dfsHelper(List<List<Integer>> graph, int node, boolean[] visited, List<Integer> result) {
        visited[node] = true;
        result.add(node);
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) dfsHelper(graph, neighbor, visited, result);
        }
    }

    // =====================================================
    // 3️⃣ DFS - ITERATIVE (using Stack)
    // =====================================================
    public static List<Integer> dfsIterative(List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        List<Integer> result = new ArrayList<>();
        Stack<Integer> stack = new Stack<>();

        stack.push(start);

        while (!stack.isEmpty()) {
            int node = stack.pop();
            if (!visited[node]) {
                visited[node] = true;
                result.add(node);
                // Push neighbors in reverse so left is processed first
                List<Integer> neighbors = graph.get(node);
                for (int i = neighbors.size() - 1; i >= 0; i--) {
                    if (!visited[neighbors.get(i)]) stack.push(neighbors.get(i));
                }
            }
        }
        return result;
    }

    // =====================================================
    // 4️⃣ BFS - LEVEL ORDER (returns list of levels)
    // =====================================================
    public static List<List<Integer>> bfsLevelOrder(List<List<Integer>> graph, int start) {
        boolean[] visited = new boolean[graph.size()];
        List<List<Integer>> levels = new ArrayList<>();
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.offer(start);

        while (!queue.isEmpty()) {
            int size = queue.size();
            List<Integer> level = new ArrayList<>();
            for (int i = 0; i < size; i++) {
                int node = queue.poll();
                level.add(node);
                for (int neighbor : graph.get(node)) {
                    if (!visited[neighbor]) {
                        visited[neighbor] = true;
                        queue.offer(neighbor);
                    }
                }
            }
            levels.add(level);
        }
        return levels;
    }

    // =====================================================
    // 5️⃣ CYCLE DETECTION - UNDIRECTED (BFS)
    // =====================================================
    public static boolean hasCycleUndirectedBFS(List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] visited = new boolean[n];

        for (int start = 0; start < n; start++) {
            if (!visited[start]) {
                Queue<int[]> queue = new LinkedList<>(); // [node, parent]
                visited[start] = true;
                queue.offer(new int[]{start, -1});

                while (!queue.isEmpty()) {
                    int[] curr = queue.poll();
                    int node = curr[0], parent = curr[1];

                    for (int neighbor : graph.get(node)) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.offer(new int[]{neighbor, node});
                        } else if (neighbor != parent) {
                            return true; // back edge found
                        }
                    }
                }
            }
        }
        return false;
    }

    // =====================================================
    // 6️⃣ CYCLE DETECTION - UNDIRECTED (DFS)
    // =====================================================
    public static boolean hasCycleUndirectedDFS(List<List<Integer>> graph) {
        boolean[] visited = new boolean[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (!visited[i] && dfsCycleUndirected(graph, i, -1, visited)) return true;
        }
        return false;
    }

    private static boolean dfsCycleUndirected(List<List<Integer>> graph, int node, int parent, boolean[] visited) {
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor]) {
                if (dfsCycleUndirected(graph, neighbor, node, visited)) return true;
            } else if (neighbor != parent) {
                return true;
            }
        }
        return false;
    }

    // =====================================================
    // 7️⃣ CYCLE DETECTION - DIRECTED (DFS with 3 colors)
    // 0=white(unvisited), 1=gray(in stack), 2=black(done)
    // =====================================================
    public static boolean hasCycleDirected(List<List<Integer>> graph) {
        int[] color = new int[graph.size()];
        for (int i = 0; i < graph.size(); i++) {
            if (color[i] == 0 && dfsCycleDirected(graph, i, color)) return true;
        }
        return false;
    }

    private static boolean dfsCycleDirected(List<List<Integer>> graph, int node, int[] color) {
        color[node] = 1; // gray
        for (int neighbor : graph.get(node)) {
            if (color[neighbor] == 1) return true; // back edge
            if (color[neighbor] == 0 && dfsCycleDirected(graph, neighbor, color)) return true;
        }
        color[node] = 2; // black
        return false;
    }

    // =====================================================
    // UTILITY: Build undirected graph
    // =====================================================
    public static List<List<Integer>> buildGraph(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] e : edges) {
            graph.get(e[0]).add(e[1]);
            graph.get(e[1]).add(e[0]);
        }
        return graph;
    }

    public static List<List<Integer>> buildDirected(int n, int[][] edges) {
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) graph.add(new ArrayList<>());
        for (int[] e : edges) graph.get(e[0]).add(e[1]);
        return graph;
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        int[][] edges = {{0,1},{0,2},{1,3},{1,4},{2,5}};
        List<List<Integer>> graph = buildGraph(6, edges);

        System.out.println("BFS from 0:        " + bfs(graph, 0));
        System.out.println("DFS Recursive fr 0:" + dfsRecursive(graph, 0));
        System.out.println("DFS Iterative fr 0:" + dfsIterative(graph, 0));
        System.out.println("BFS Level Order:    " + bfsLevelOrder(graph, 0));

        System.out.println("\nCycle in undirected (no cycle): " + hasCycleUndirectedBFS(graph));
        // Add cycle edge
        graph.get(3).add(5); graph.get(5).add(3);
        System.out.println("Cycle in undirected (with cycle, BFS): " + hasCycleUndirectedBFS(graph));
        System.out.println("Cycle in undirected (with cycle, DFS): " + hasCycleUndirectedDFS(graph));

        // Directed cycle detection
        int[][] dirEdges = {{0,1},{1,2},{2,0},{3,4}};
        List<List<Integer>> directed = buildDirected(5, dirEdges);
        System.out.println("Cycle in directed (with cycle): " + hasCycleDirected(directed));

        int[][] noCycleEdges = {{0,1},{1,2},{2,3}};
        System.out.println("Cycle in directed (no cycle):   " + hasCycleDirected(buildDirected(4, noCycleEdges)));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

BFS:                 Time O(V+E), Space O(V)
DFS Recursive:       Time O(V+E), Space O(V) call stack
DFS Iterative:       Time O(V+E), Space O(V)
Level Order BFS:     Time O(V+E), Space O(V)
Cycle Undirected:    Time O(V+E), Space O(V)
Cycle Directed:      Time O(V+E), Space O(V)

===========================================================
*/
