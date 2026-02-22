package DataStructures.Graphs;

import java.util.*;

/*
===========================================================
Graph Shortest Path Algorithms
===========================================================

1) Dijkstra's Algorithm (weighted, non-negative)
2) Bellman-Ford Algorithm (handles negative weights)
3) BFS Shortest Path (unweighted graph)
4) Topological Sort (Kahn's BFS & DFS)

===========================================================
*/

public class GraphShortestPath {

    // =====================================================
    // 1️⃣ DIJKSTRA'S ALGORITHM - O((V+E) log V)
    // =====================================================
    public static int[] dijkstra(List<List<int[]>> graph, int src) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // [distance, node]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, src});

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int d = curr[0], u = curr[1];

            if(d > dist[u]) continue; // stale entry

            for(int[] neighbor : graph.get(u)) {
                int v = neighbor[0], w = neighbor[1];
                if(dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    pq.offer(new int[]{dist[v], v});
                }
            }
        }
        return dist;
    }

    // =====================================================
    // 2️⃣ BELLMAN-FORD ALGORITHM - O(V*E)
    // Handles negative weights, detects negative cycles
    // =====================================================
    static class BellmanFordResult {
        int[] dist;
        boolean hasNegCycle;
    }

    public static BellmanFordResult bellmanFord(int n, int[][] edges, int src) {
        int[] dist = new int[n];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        // Relax edges V-1 times
        for(int i = 0; i < n - 1; i++) {
            for(int[] edge : edges) {
                int u = edge[0], v = edge[1], w = edge[2];
                if(dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                }
            }
        }

        // Check for negative cycle (Vth relaxation)
        BellmanFordResult result = new BellmanFordResult();
        result.dist = dist;
        result.hasNegCycle = false;

        for(int[] edge : edges) {
            int u = edge[0], v = edge[1], w = edge[2];
            if(dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                result.hasNegCycle = true;
                break;
            }
        }

        return result;
    }

    // =====================================================
    // 3️⃣ BFS SHORTEST PATH (unweighted)
    // =====================================================
    public static int[] bfsShortestPath(List<List<Integer>> graph, int src) {
        int n = graph.size();
        int[] dist = new int[n];
        Arrays.fill(dist, -1);
        dist[src] = 0;

        Queue<Integer> queue = new LinkedList<>();
        queue.offer(src);

        while(!queue.isEmpty()) {
            int node = queue.poll();
            for(int neighbor : graph.get(node)) {
                if(dist[neighbor] == -1) {
                    dist[neighbor] = dist[node] + 1;
                    queue.offer(neighbor);
                }
            }
        }
        return dist;
    }

    // =====================================================
    // 4️⃣ TOPOLOGICAL SORT - KAHN'S (BFS)
    // =====================================================
    public static List<Integer> topologicalSortBFS(List<List<Integer>> graph) {
        int n = graph.size();
        int[] inDegree = new int[n];

        for(int u = 0; u < n; u++)
            for(int v : graph.get(u)) inDegree[v]++;

        Queue<Integer> queue = new LinkedList<>();
        for(int i = 0; i < n; i++) if(inDegree[i] == 0) queue.offer(i);

        List<Integer> order = new ArrayList<>();
        while(!queue.isEmpty()) {
            int node = queue.poll();
            order.add(node);
            for(int v : graph.get(node)) {
                if(--inDegree[v] == 0) queue.offer(v);
            }
        }
        return order.size() == n ? order : Collections.emptyList(); // empty = has cycle
    }

    // =====================================================
    // 5️⃣ TOPOLOGICAL SORT - DFS
    // =====================================================
    public static List<Integer> topologicalSortDFS(List<List<Integer>> graph) {
        int n = graph.size();
        boolean[] visited = new boolean[n];
        Stack<Integer> stack = new Stack<>();

        for(int i = 0; i < n; i++) {
            if(!visited[i]) dfsTopoHelper(graph, i, visited, stack);
        }

        List<Integer> order = new ArrayList<>();
        while(!stack.isEmpty()) order.add(stack.pop());
        return order;
    }

    private static void dfsTopoHelper(List<List<Integer>> graph, int node, boolean[] visited, Stack<Integer> stack) {
        visited[node] = true;
        for(int neighbor : graph.get(node)) {
            if(!visited[neighbor]) dfsTopoHelper(graph, neighbor, visited, stack);
        }
        stack.push(node);
    }

    // =====================================================
    // UTILITY
    // =====================================================
    public static List<List<int[]>> buildWeighted(int n, int[][] edges) {
        List<List<int[]>> g = new ArrayList<>();
        for(int i = 0; i < n; i++) g.add(new ArrayList<>());
        for(int[] e : edges) {
            g.get(e[0]).add(new int[]{e[1], e[2]});
            g.get(e[1]).add(new int[]{e[0], e[2]});
        }
        return g;
    }

    public static List<List<Integer>> buildDirected(int n, int[][] edges) {
        List<List<Integer>> g = new ArrayList<>();
        for(int i = 0; i < n; i++) g.add(new ArrayList<>());
        for(int[] e : edges) g.get(e[0]).add(e[1]);
        return g;
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        // Dijkstra
        System.out.println("=== Dijkstra ===");
        int[][] wEdges = {{0,1,4},{0,2,1},{2,1,2},{1,3,1},{2,3,5}};
        int[] dijkDist = dijkstra(buildWeighted(4, wEdges), 0);
        System.out.println("Shortest from 0: " + Arrays.toString(dijkDist));

        // Bellman-Ford
        System.out.println("\n=== Bellman-Ford ===");
        int[][] bfEdges = {{0,1,4},{0,2,5},{1,2,-3},{2,3,2}};
        BellmanFordResult bf = bellmanFord(4, bfEdges, 0);
        System.out.println("Shortest from 0: " + Arrays.toString(bf.dist));
        System.out.println("Has negative cycle: " + bf.hasNegCycle);

        // BFS Shortest Path
        System.out.println("\n=== BFS Shortest Path (unweighted) ===");
        int[][] unweighted = {{0,1},{0,2},{1,3},{2,3},{3,4}};
        List<List<Integer>> ug = buildDirected(5, unweighted);
        System.out.println("BFS dist from 0: " + Arrays.toString(bfsShortestPath(ug, 0)));

        // Topological Sort
        System.out.println("\n=== Topological Sort ===");
        int[][] dagEdges = {{5,2},{5,0},{4,0},{4,1},{2,3},{3,1}};
        List<List<Integer>> dag = buildDirected(6, dagEdges);
        System.out.println("Topo BFS: " + topologicalSortBFS(dag));
        System.out.println("Topo DFS: " + topologicalSortDFS(dag));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

Dijkstra:        Time O((V+E) log V), Space O(V)
Bellman-Ford:    Time O(V*E),         Space O(V)
BFS Shortest:    Time O(V+E),         Space O(V)
Topo Sort BFS:   Time O(V+E),         Space O(V)
Topo Sort DFS:   Time O(V+E),         Space O(V)

===========================================================
*/
