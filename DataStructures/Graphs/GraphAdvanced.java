package DataStructures.Graphs;

import java.util.*;

/*
===========================================================
Advanced Graph Algorithms
===========================================================

1) Union-Find (Disjoint Set Union) - with path compression + rank
2) Kruskal's MST (Minimum Spanning Tree)
3) Prim's MST
4) Number of Islands (DFS & BFS)
5) Bipartite Check (2-coloring with BFS & DFS)

===========================================================
*/

public class GraphAdvanced {

    // =====================================================
    // 1️⃣ UNION-FIND (DSU)
    // =====================================================
    static class UnionFind {
        private int[] parent, rank;
        private int components;

        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            components = n;
            for(int i = 0; i < n; i++) parent[i] = i;
        }

        // Find with path compression
        public int find(int x) {
            if(parent[x] != x) parent[x] = find(parent[x]);
            return parent[x];
        }

        // Union by rank
        public boolean union(int x, int y) {
            int px = find(x), py = find(y);
            if(px == py) return false; // already same component
            if(rank[px] < rank[py]) { int t = px; px = py; py = t; }
            parent[py] = px;
            if(rank[px] == rank[py]) rank[px]++;
            components--;
            return true;
        }

        public boolean connected(int x, int y) { return find(x) == find(y); }
        public int components() { return components; }
    }

    // =====================================================
    // 2️⃣ KRUSKAL'S MST - O(E log E)
    // =====================================================
    public static int[][] kruskalMST(int n, int[][] edges) {
        // Sort edges by weight
        Arrays.sort(edges, (a, b) -> a[2] - b[2]);
        UnionFind uf = new UnionFind(n);
        List<int[]> mst = new ArrayList<>();

        for(int[] edge : edges) {
            if(uf.union(edge[0], edge[1])) {
                mst.add(edge);
                if(mst.size() == n - 1) break;
            }
        }
        return mst.toArray(new int[0][]);
    }

    // =====================================================
    // 3️⃣ PRIM'S MST - O((V+E) log V)
    // =====================================================
    public static int primMSTCost(int n, List<List<int[]>> graph) {
        boolean[] visited = new boolean[n];
        // [weight, node]
        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> a[0] - b[0]);
        pq.offer(new int[]{0, 0});
        int totalCost = 0;

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            int w = curr[0], u = curr[1];
            if(visited[u]) continue;
            visited[u] = true;
            totalCost += w;
            for(int[] neighbor : graph.get(u)) {
                if(!visited[neighbor[0]]) pq.offer(new int[]{neighbor[1], neighbor[0]});
            }
        }
        return totalCost;
    }

    // =====================================================
    // 4️⃣ NUMBER OF ISLANDS - DFS
    // =====================================================
    public static int numIslandsDFS(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length, count = 0;

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    dfsIsland(grid, i, j);
                }
            }
        }
        return count;
    }

    private static void dfsIsland(char[][] grid, int i, int j) {
        if(i < 0 || i >= grid.length || j < 0 || j >= grid[0].length || grid[i][j] != '1') return;
        grid[i][j] = '#'; // mark visited
        dfsIsland(grid, i+1, j); dfsIsland(grid, i-1, j);
        dfsIsland(grid, i, j+1); dfsIsland(grid, i, j-1);
    }

    // =====================================================
    // 5️⃣ NUMBER OF ISLANDS - BFS
    // =====================================================
    public static int numIslandsBFS(char[][] grid) {
        if(grid == null || grid.length == 0) return 0;
        int rows = grid.length, cols = grid[0].length, count = 0;
        int[][] dirs = {{1,0},{-1,0},{0,1},{0,-1}};

        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < cols; j++) {
                if(grid[i][j] == '1') {
                    count++;
                    Queue<int[]> queue = new LinkedList<>();
                    queue.offer(new int[]{i, j});
                    grid[i][j] = '#';
                    while(!queue.isEmpty()) {
                        int[] pos = queue.poll();
                        for(int[] d : dirs) {
                            int ni = pos[0]+d[0], nj = pos[1]+d[1];
                            if(ni>=0 && ni<rows && nj>=0 && nj<cols && grid[ni][nj]=='1') {
                                grid[ni][nj] = '#';
                                queue.offer(new int[]{ni, nj});
                            }
                        }
                    }
                }
            }
        }
        return count;
    }

    // =====================================================
    // 6️⃣ BIPARTITE CHECK - BFS (2-coloring)
    // =====================================================
    public static boolean isBipartiteBFS(List<List<Integer>> graph) {
        int n = graph.size();
        int[] color = new int[n]; // 0=uncolored, 1=red, -1=blue
        for(int start = 0; start < n; start++) {
            if(color[start] != 0) continue;
            Queue<Integer> queue = new LinkedList<>();
            queue.offer(start);
            color[start] = 1;
            while(!queue.isEmpty()) {
                int node = queue.poll();
                for(int nb : graph.get(node)) {
                    if(color[nb] == 0) { color[nb] = -color[node]; queue.offer(nb); }
                    else if(color[nb] == color[node]) return false;
                }
            }
        }
        return true;
    }

    // =====================================================
    // 7️⃣ BIPARTITE CHECK - DFS
    // =====================================================
    public static boolean isBipartiteDFS(List<List<Integer>> graph) {
        int[] color = new int[graph.size()];
        for(int i = 0; i < graph.size(); i++) {
            if(color[i] == 0 && !dfsBipartite(graph, i, 1, color)) return false;
        }
        return true;
    }

    private static boolean dfsBipartite(List<List<Integer>> graph, int node, int c, int[] color) {
        color[node] = c;
        for(int nb : graph.get(node)) {
            if(color[nb] == 0 && !dfsBipartite(graph, nb, -c, color)) return false;
            else if(color[nb] == c) return false;
        }
        return true;
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        // Union-Find
        System.out.println("=== Union-Find ===");
        UnionFind uf = new UnionFind(5);
        uf.union(0,1); uf.union(1,2); uf.union(3,4);
        System.out.println("Components: " + uf.components());
        System.out.println("0 and 2 connected: " + uf.connected(0, 2));
        System.out.println("0 and 3 connected: " + uf.connected(0, 3));

        // Kruskal
        System.out.println("\n=== Kruskal MST ===");
        int[][] edges = {{0,1,1},{0,2,4},{1,2,2},{1,3,5},{2,3,1}};
        int[][] mst = kruskalMST(4, edges);
        int totalW = 0;
        System.out.println("MST edges:");
        for(int[] e : mst) { System.out.println("  "+e[0]+"--"+e[2]+"--"+e[1]); totalW+=e[2]; }
        System.out.println("Total weight: " + totalW);

        // Prim
        System.out.println("\n=== Prim MST ===");
        List<List<int[]>> g = new ArrayList<>();
        for(int i = 0; i < 4; i++) g.add(new ArrayList<>());
        g.get(0).add(new int[]{1,1}); g.get(1).add(new int[]{0,1});
        g.get(0).add(new int[]{2,4}); g.get(2).add(new int[]{0,4});
        g.get(1).add(new int[]{2,2}); g.get(2).add(new int[]{1,2});
        g.get(1).add(new int[]{3,5}); g.get(3).add(new int[]{1,5});
        g.get(2).add(new int[]{3,1}); g.get(3).add(new int[]{2,1});
        System.out.println("Prim MST cost: " + primMSTCost(4, g));

        // Number of Islands
        System.out.println("\n=== Number of Islands ===");
        char[][] grid = {{'1','1','0','0'},{'1','1','0','0'},{'0','0','1','0'},{'0','0','0','1'}};
        System.out.println("Islands (DFS): " + numIslandsDFS(grid));
        char[][] grid2 = {{'1','1','0','0'},{'1','1','0','0'},{'0','0','1','0'},{'0','0','0','1'}};
        System.out.println("Islands (BFS): " + numIslandsBFS(grid2));

        // Bipartite
        System.out.println("\n=== Bipartite Check ===");
        List<List<Integer>> bg = new ArrayList<>();
        for(int i = 0; i < 4; i++) bg.add(new ArrayList<>());
        bg.get(0).add(1); bg.get(1).add(0); bg.get(0).add(3); bg.get(3).add(0);
        bg.get(1).add(2); bg.get(2).add(1); bg.get(2).add(3); bg.get(3).add(2);
        System.out.println("Is bipartite (BFS): " + isBipartiteBFS(bg));
        System.out.println("Is bipartite (DFS): " + isBipartiteDFS(bg));
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

Union-Find find/union:   Time O(α(n)) ≈ O(1), Space O(n)
Kruskal MST:             Time O(E log E),      Space O(V)
Prim MST:                Time O((V+E) log V),  Space O(V)
Number of Islands DFS:   Time O(M*N),          Space O(M*N)
Number of Islands BFS:   Time O(M*N),          Space O(min(M,N))
Bipartite BFS:           Time O(V+E),          Space O(V)
Bipartite DFS:           Time O(V+E),          Space O(V)

===========================================================
*/
