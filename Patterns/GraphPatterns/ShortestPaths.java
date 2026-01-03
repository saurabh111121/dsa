package Patterns.GraphPatterns;

import java.util.*;

/**
 * Shortest Path Patterns
 * ======================
 * Topics Covered:
 *  1. BFS Shortest Path (unweighted)
 *  2. Dijkstra's Algorithm (weighted, no negative edges)
 *  3. Bellman-Ford (handles negative edges, detects negative cycle)
 *  4. Floyd-Warshall (all-pairs shortest path)
 *  5. 0-1 BFS (edges weight 0 or 1)
 *  6. Word Ladder (BFS on strings – LeetCode 127)
 */
public class ShortestPaths {

    // ─────────────────────────────────────────────────────────────
    // 1️⃣  BFS SHORTEST PATH (unweighted)
    // ─────────────────────────────────────────────────────────────
    public static int[] bfsShortestPath(List<List<Integer>> adj, int src, int n) {
        int[] dist = new int[n]; Arrays.fill(dist, -1);
        dist[src] = 0;
        Queue<Integer> q = new LinkedList<>(); q.add(src);
        while (!q.isEmpty()) {
            int u = q.poll();
            for (int v : adj.get(u)) if (dist[v] == -1) { dist[v] = dist[u] + 1; q.add(v); }
        }
        return dist;
    }

    // ─────────────────────────────────────────────────────────────
    // 2️⃣  DIJKSTRA'S  (adjacency list with PriorityQueue)
    // ─────────────────────────────────────────────────────────────
    // adj stores [neighbor, weight] pairs
    public static int[] dijkstra(List<List<int[]>> adj, int src, int n) {
        int[] dist = new int[n]; Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, src});
        while (!pq.isEmpty()) {
            int[] cur = pq.poll(); int d = cur[0], u = cur[1];
            if (d > dist[u]) continue;
            for (int[] edge : adj.get(u)) {
                int v = edge[0], w = edge[1];
                if (dist[u] + w < dist[v]) { dist[v] = dist[u] + w; pq.offer(new int[]{dist[v], v}); }
            }
        }
        return dist;
    }

    // ─────────────────────────────────────────────────────────────
    // 3️⃣  BELLMAN-FORD
    // ─────────────────────────────────────────────────────────────
    // edges: [u, v, weight]
    public static int[] bellmanFord(int n, int[][] edges, int src) {
        int[] dist = new int[n]; Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        for (int i = 0; i < n - 1; i++) {
            boolean updated = false;
            for (int[] e : edges) {
                int u = e[0], v = e[1], w = e[2];
                if (dist[u] != Integer.MAX_VALUE && dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w; updated = true;
                }
            }
            if (!updated) break; // early exit
        }
        // Check for negative cycle
        for (int[] e : edges)
            if (dist[e[0]] != Integer.MAX_VALUE && dist[e[0]] + e[2] < dist[e[1]])
                return null; // negative cycle detected
        return dist;
    }

    // ─────────────────────────────────────────────────────────────
    // 4️⃣  FLOYD-WARSHALL  (all-pairs)
    // ─────────────────────────────────────────────────────────────
    public static int[][] floydWarshall(int[][] dist) {
        int n = dist.length;
        int[][] d = new int[n][n];
        for (int i = 0; i < n; i++) d[i] = dist[i].clone();
        for (int k = 0; k < n; k++)
            for (int i = 0; i < n; i++)
                for (int j = 0; j < n; j++)
                    if (d[i][k] != Integer.MAX_VALUE / 2 && d[k][j] != Integer.MAX_VALUE / 2)
                        d[i][j] = Math.min(d[i][j], d[i][k] + d[k][j]);
        return d;
    }

    // ─────────────────────────────────────────────────────────────
    // 5️⃣  0-1 BFS  (deque – 0-weight edges go to front)
    // ─────────────────────────────────────────────────────────────
    // adj: [neighbor, weight] where weight in {0,1}
    public static int[] zerooneBFS(List<List<int[]>> adj, int src, int n) {
        int[] dist = new int[n]; Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;
        Deque<Integer> dq = new ArrayDeque<>(); dq.addFirst(src);
        while (!dq.isEmpty()) {
            int u = dq.pollFirst();
            for (int[] edge : adj.get(u)) {
                int v = edge[0], w = edge[1];
                if (dist[u] + w < dist[v]) {
                    dist[v] = dist[u] + w;
                    if (w == 0) dq.addFirst(v); else dq.addLast(v);
                }
            }
        }
        return dist;
    }

    // ─────────────────────────────────────────────────────────────
    // 6️⃣  WORD LADDER  (LeetCode 127)
    // ─────────────────────────────────────────────────────────────
    public static int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);
        if (!wordSet.contains(endWord)) return 0;
        Queue<String> q = new LinkedList<>();
        q.add(beginWord); int steps = 1;
        while (!q.isEmpty()) {
            for (int sz = q.size(); sz > 0; sz--) {
                char[] cur = q.poll().toCharArray();
                for (int i = 0; i < cur.length; i++) {
                    char orig = cur[i];
                    for (char c = 'a'; c <= 'z'; c++) {
                        cur[i] = c; String next = new String(cur);
                        if (next.equals(endWord)) return steps + 1;
                        if (wordSet.remove(next)) q.add(next);
                    }
                    cur[i] = orig;
                }
            }
            steps++;
        }
        return 0;
    }

    // ─────────────────────────────────────────────────────────────
    // MAIN DEMO
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // BFS Shortest path
        int n = 5;
        List<List<Integer>> uAdj = new ArrayList<>();
        for (int i = 0; i < n; i++) uAdj.add(new ArrayList<>());
        uAdj.get(0).add(1); uAdj.get(1).add(0);
        uAdj.get(0).add(2); uAdj.get(2).add(0);
        uAdj.get(1).add(3); uAdj.get(3).add(1);
        uAdj.get(2).add(4); uAdj.get(4).add(2);
        System.out.println("BFS dist from 0: " + Arrays.toString(bfsShortestPath(uAdj, 0, n)));

        // Dijkstra
        List<List<int[]>> wAdj = new ArrayList<>();
        for (int i = 0; i < 5; i++) wAdj.add(new ArrayList<>());
        wAdj.get(0).add(new int[]{1,4}); wAdj.get(0).add(new int[]{2,1});
        wAdj.get(2).add(new int[]{1,2}); wAdj.get(1).add(new int[]{3,1});
        wAdj.get(2).add(new int[]{3,5}); wAdj.get(3).add(new int[]{4,3});
        System.out.println("Dijkstra from 0: " + Arrays.toString(dijkstra(wAdj, 0, 5)));

        // Bellman-Ford
        int[][] edges = {{0,1,4},{0,2,1},{2,1,2},{1,3,1},{2,3,5},{3,4,3}};
        System.out.println("Bellman-Ford from 0: " + Arrays.toString(bellmanFord(5, edges, 0)));

        // Floyd-Warshall
        int INF = Integer.MAX_VALUE / 2;
        int[][] d = {{0,3,INF,7},{8,0,2,INF},{5,INF,0,1},{2,INF,INF,0}};
        int[][] fw = floydWarshall(d);
        System.out.println("Floyd-Warshall [0][3] = " + fw[0][3]); // 6

        // Word Ladder
        System.out.println("Word Ladder: " + ladderLength("hit", "cog",
            Arrays.asList("hot","dot","dog","lot","log","cog"))); // 5
    }
}

/*
 * ┌──────────────────────────────┬────────────────────┬──────────────┐
 * │ Algorithm                    │ Time               │ Space        │
 * ├──────────────────────────────┼────────────────────┼──────────────┤
 * │ BFS (unweighted)             │ O(V+E)             │ O(V)         │
 * │ Dijkstra (PQ)                │ O((V+E) log V)     │ O(V)         │
 * │ Bellman-Ford                 │ O(V·E)             │ O(V)         │
 * │ Floyd-Warshall               │ O(V^3)             │ O(V^2)       │
 * │ 0-1 BFS                      │ O(V+E)             │ O(V)         │
 * │ Word Ladder                  │ O(N·L^2)           │ O(N·L)       │
 * └──────────────────────────────┴────────────────────┴──────────────┘
 */
