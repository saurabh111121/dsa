package Patterns.GraphPatterns;

import java.util.*;

/**
 * Graph BFS & DFS Patterns
 * ========================
 * Topics Covered:
 *  1. BFS – Level-order traversal template
 *  2. DFS – Recursive & Iterative templates
 *  3. Number of Connected Components (undirected)
 *  4. Cycle Detection – Undirected (BFS + DFS)
 *  5. Cycle Detection – Directed (DFS coloring)
 *  6. Topological Sort (BFS Kahn's + DFS)
 *  7. Bipartite Check (BFS + DFS)
 *  8. Multi-source BFS (0-1 Matrix / Rotten Oranges)
 *  9. Islands count (DFS flood-fill)
 */
public class GraphBFSDFS {

    // ─────────────────────────────────────────────────────────────
    // 1️⃣  BFS TEMPLATE
    // ─────────────────────────────────────────────────────────────
    public static List<List<Integer>> bfsLevels(List<List<Integer>> adj, int src, int n) {
        List<List<Integer>> levels = new ArrayList<>();
        boolean[] visited = new boolean[n];
        Queue<Integer> q = new LinkedList<>();
        q.add(src); visited[src] = true;
        while(!q.isEmpty()) {
            int sz = q.size();
            List<Integer> level = new ArrayList<>();
            for(int i = 0; i < sz; i++) {
                int u = q.poll(); level.add(u);
                for(int v : adj.get(u)) if(!visited[v]) { visited[v] = true; q.add(v); }
            }
            levels.add(level);
        }
        return levels;
    }

    // ─────────────────────────────────────────────────────────────
    // 2️⃣  DFS TEMPLATES
    // ─────────────────────────────────────────────────────────────
    public static void dfsRecursive(List<List<Integer>> adj, int u, boolean[] visited, List<Integer> order) {
        visited[u] = true; order.add(u);
        for(int v : adj.get(u)) if(!visited[v]) dfsRecursive(adj, v, visited, order);
    }

    public static List<Integer> dfsIterative(List<List<Integer>> adj, int src, int n) {
        List<Integer> order = new ArrayList<>();
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        stack.push(src);
        while(!stack.isEmpty()) {
            int u = stack.pop();
            if(visited[u]) continue;
            visited[u] = true; order.add(u);
            for(int v : adj.get(u)) if(!visited[v]) stack.push(v);
        }
        return order;
    }

    // ─────────────────────────────────────────────────────────────
    // 3️⃣  CONNECTED COMPONENTS COUNT
    // ─────────────────────────────────────────────────────────────
    public static int countComponents(List<List<Integer>> adj, int n) {
        boolean[] visited = new boolean[n];
        int count = 0;
        for(int i = 0; i < n; i++) {
            if(!visited[i]) {
                count++;
                List<Integer> dummy = new ArrayList<>();
                dfsRecursive(adj, i, visited, dummy);
            }
        }
        return count;
    }

    // ─────────────────────────────────────────────────────────────
    // 4️⃣  CYCLE DETECTION – UNDIRECTED (BFS)
    // ─────────────────────────────────────────────────────────────
    public static boolean hasCycleUndirectedBFS(List<List<Integer>> adj, int n) {
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++) {
            if(visited[i]) continue;
            Queue<int[]> q = new LinkedList<>(); // [node, parent]
            q.add(new int[]{i, -1}); visited[i] = true;
            while(!q.isEmpty()) {
                int[] cur = q.poll(); int u = cur[0], par = cur[1];
                for(int v : adj.get(u)) {
                    if(!visited[v]) { visited[v] = true; q.add(new int[]{v, u}); }
                    else if(v != par) return true;
                }
            }
        }
        return false;
    }

    /** DFS version */
    public static boolean hasCycleUndirectedDFS(List<List<Integer>> adj, int n) {
        boolean[] visited = new boolean[n];
        for(int i = 0; i < n; i++)
            if(!visited[i] && cycleUndirDFS(adj, i, -1, visited)) return true;
        return false;
    }

    private static boolean cycleUndirDFS(List<List<Integer>> adj, int u, int par, boolean[] vis) {
        vis[u] = true;
        for(int v : adj.get(u)) {
            if(!vis[v]) { if(cycleUndirDFS(adj, v, u, vis)) return true; }
            else if(v != par) return true;
        }
        return false;
    }

    // ─────────────────────────────────────────────────────────────
    // 5️⃣  CYCLE DETECTION – DIRECTED (DFS 3-coloring)
    // ─────────────────────────────────────────────────────────────
    // 0=white(unvisited), 1=gray(in-stack), 2=black(done)
    public static boolean hasCycleDirected(List<List<Integer>> adj, int n) {
        int[] color = new int[n];
        for(int i = 0; i < n; i++)
            if(color[i] == 0 && cycleDirDFS(adj, i, color)) return true;
        return false;
    }

    private static boolean cycleDirDFS(List<List<Integer>> adj, int u, int[] color) {
        color[u] = 1;
        for(int v : adj.get(u)) {
            if(color[v] == 1) return true;
            if(color[v] == 0 && cycleDirDFS(adj, v, color)) return true;
        }
        color[u] = 2; return false;
    }

    // ─────────────────────────────────────────────────────────────
    // 6️⃣  TOPOLOGICAL SORT
    // ─────────────────────────────────────────────────────────────
    /** Kahn's BFS (indegree) */
    public static int[] topoSortBFS(List<List<Integer>> adj, int n) {
        int[] indegree = new int[n];
        for(int u = 0; u < n; u++) for(int v : adj.get(u)) indegree[v]++;
        Queue<Integer> q = new LinkedList<>();
        for(int i = 0; i < n; i++) if(indegree[i] == 0) q.add(i);
        int[] order = new int[n]; int idx = 0;
        while(!q.isEmpty()) {
            int u = q.poll(); order[idx++] = u;
            for(int v : adj.get(u)) if(--indegree[v] == 0) q.add(v);
        }
        return idx == n ? order : new int[]{}; // empty if cycle
    }

    /** DFS post-order */
    public static int[] topoSortDFS(List<List<Integer>> adj, int n) {
        boolean[] visited = new boolean[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for(int i = 0; i < n; i++) if(!visited[i]) topoDFS(adj, i, visited, stack);
        int[] order = new int[n]; int idx = 0;
        while(!stack.isEmpty()) order[idx++] = stack.pop();
        return order;
    }

    private static void topoDFS(List<List<Integer>> adj, int u, boolean[] vis, Deque<Integer> stack) {
        vis[u] = true;
        for(int v : adj.get(u)) if(!vis[v]) topoDFS(adj, v, vis, stack);
        stack.push(u);
    }

    // ─────────────────────────────────────────────────────────────
    // 7️⃣  BIPARTITE CHECK  (LeetCode 785)
    // ─────────────────────────────────────────────────────────────
    public static boolean isBipartiteBFS(List<List<Integer>> adj, int n) {
        int[] color = new int[n]; Arrays.fill(color, -1);
        for(int i = 0; i < n; i++) {
            if(color[i] != -1) continue;
            Queue<Integer> q = new LinkedList<>();
            q.add(i); color[i] = 0;
            while(!q.isEmpty()) {
                int u = q.poll();
                for(int v : adj.get(u)) {
                    if(color[v] == -1) { color[v] = 1 - color[u]; q.add(v); }
                    else if(color[v] == color[u]) return false;
                }
            }
        }
        return true;
    }

    // ─────────────────────────────────────────────────────────────
    // 8️⃣  MULTI-SOURCE BFS  (Rotten Oranges – LeetCode 994)
    // ─────────────────────────────────────────────────────────────
    public static int orangesRotting(int[][] grid) {
        int m = grid.length, n = grid[0].length, fresh = 0, time = 0;
        Queue<int[]> q = new LinkedList<>();
        for(int i = 0; i < m; i++) for(int j = 0; j < n; j++) {
            if(grid[i][j] == 2) q.add(new int[]{i, j});
            else if(grid[i][j] == 1) fresh++;
        }
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        while(!q.isEmpty() && fresh > 0) {
            time++;
            for(int sz = q.size(); sz > 0; sz--) {
                int[] cur = q.poll();
                for(int[] d : dirs) {
                    int r = cur[0]+d[0], c = cur[1]+d[1];
                    if(r>=0&&r<m&&c>=0&&c<n&&grid[r][c]==1) { grid[r][c]=2; fresh--; q.add(new int[]{r,c}); }
                }
            }
        }
        return fresh == 0 ? time : -1;
    }

    // ─────────────────────────────────────────────────────────────
    // 9️⃣  NUMBER OF ISLANDS  (LeetCode 200)
    // ─────────────────────────────────────────────────────────────
    public static int numIslands(char[][] grid) {
        int m = grid.length, n = grid[0].length, count = 0;
        for(int i = 0; i < m; i++) for(int j = 0; j < n; j++) {
            if(grid[i][j] == '1') { floodFill(grid, i, j, m, n); count++; }
        }
        return count;
    }

    private static void floodFill(char[][] g, int r, int c, int m, int n) {
        if(r<0||r>=m||c<0||c>=n||g[r][c]!='1') return;
        g[r][c] = '0';
        floodFill(g, r+1, c, m, n); floodFill(g, r-1, c, m, n);
        floodFill(g, r, c+1, m, n); floodFill(g, r, c-1, m, n);
    }

    // ─────────────────────────────────────────────────────────────
    // MAIN DEMO
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // Build simple undirected graph: 0-1-2-3, 0-2
        int n = 5;
        List<List<Integer>> adj = new ArrayList<>();
        for(int i = 0; i < n; i++) adj.add(new ArrayList<>());
        adj.get(0).add(1); adj.get(1).add(0);
        adj.get(1).add(2); adj.get(2).add(1);
        adj.get(2).add(3); adj.get(3).add(2);
        adj.get(0).add(2); adj.get(2).add(0);

        System.out.println("BFS levels from 0: " + bfsLevels(adj, 0, n));
        System.out.println("DFS iterative from 0: " + dfsIterative(adj, 0, n));
        System.out.println("Components: " + countComponents(adj, n));
        System.out.println("Cycle undirected (BFS): " + hasCycleUndirectedBFS(adj, n));

        // Directed DAG for topo sort: 5→0, 5→2, 4→0, 4→1, 2→3, 3→1
        List<List<Integer>> dag = new ArrayList<>();
        for(int i = 0; i < 6; i++) dag.add(new ArrayList<>());
        dag.get(5).add(0); dag.get(5).add(2); dag.get(4).add(0); dag.get(4).add(1);
        dag.get(2).add(3); dag.get(3).add(1);
        System.out.println("Topo BFS: " + Arrays.toString(topoSortBFS(dag, 6)));
        System.out.println("Topo DFS: " + Arrays.toString(topoSortDFS(dag, 6)));
        System.out.println("Directed cycle: " + hasCycleDirected(dag, 6));

        // Rotten oranges
        int[][] grid = {{2,1,1},{1,1,0},{0,1,1}};
        System.out.println("Rotten oranges time: " + orangesRotting(grid)); // 4

        // Islands
        char[][] islandGrid = {{'1','1','0','0'},{'1','1','0','0'},{'0','0','1','0'},{'0','0','0','1'}};
        System.out.println("Islands: " + numIslands(islandGrid)); // 3
    }
}

/*
 * ┌──────────────────────────────────┬──────────────┬──────────┐
 * │ Algorithm                        │ Time         │ Space    │
 * ├──────────────────────────────────┼──────────────┼──────────┤
 * │ BFS / DFS                        │ O(V+E)       │ O(V)     │
 * │ Connected Components             │ O(V+E)       │ O(V)     │
 * │ Cycle Detection (undirected)     │ O(V+E)       │ O(V)     │
 * │ Cycle Detection (directed)       │ O(V+E)       │ O(V)     │
 * │ Topological Sort                 │ O(V+E)       │ O(V)     │
 * │ Bipartite Check                  │ O(V+E)       │ O(V)     │
 * │ Multi-source BFS                 │ O(m·n)       │ O(m·n)   │
 * │ Islands (flood-fill)             │ O(m·n)       │ O(m·n)   │
 * └──────────────────────────────────┴──────────────┴──────────┘
 */
