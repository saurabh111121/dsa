package Patterns.GraphPatterns;

import java.util.*;

/**
 * Union-Find (DSU) & Minimum Spanning Tree Patterns
 * ==================================================
 * Topics Covered:
 *  1. Union-Find (DSU) – Union by Rank + Path Compression
 *  2. Kruskal's MST Algorithm
 *  3. Prim's MST Algorithm
 *  4. Number of Provinces (LeetCode 547)
 *  5. Redundant Connection (LeetCode 684)
 *  6. Accounts Merge (LeetCode 721)
 *  7. Swim in Rising Water (LeetCode 778)
 */
public class UnionFindMST {

    // ─────────────────────────────────────────────────────────────
    // 1️⃣  UNION-FIND (DSU)
    // ─────────────────────────────────────────────────────────────
    static class DSU {
        int[] parent, rank;
        int components;

        DSU(int n) {
            parent = new int[n]; rank = new int[n]; components = n;
            for(int i = 0; i < n; i++) parent[i] = i;
        }

        public int find(int x) {
            if(parent[x] != x) parent[x] = find(parent[x]); // path compression
            return parent[x];
        }

        public boolean union(int a, int b) {
            int ra = find(a), rb = find(b);
            if(ra == rb) return false; // already connected → cycle
            if(rank[ra] < rank[rb]) { int tmp = ra; ra = rb; rb = tmp; }
            parent[rb] = ra;
            if(rank[ra] == rank[rb]) rank[ra]++;
            components--;
            return true;
        }

        public boolean connected(int a, int b) { return find(a) == find(b); }
    }

    // ─────────────────────────────────────────────────────────────
    // 2️⃣  KRUSKAL'S MST  (sort edges by weight, union-find)
    // ─────────────────────────────────────────────────────────────
    // edges: [u, v, weight]
    public static int kruskalMST(int n, int[][] edges) {
        Arrays.sort(edges, Comparator.comparingInt(e -> e[2]));
        DSU dsu = new DSU(n);
        int totalWeight = 0, edgesUsed = 0;
        for(int[] e : edges) {
            if(dsu.union(e[0], e[1])) {
                totalWeight += e[2];
                if(++edgesUsed == n - 1) break;
            }
        }
        return edgesUsed == n - 1 ? totalWeight : -1; // -1 if not connected
    }

    // ─────────────────────────────────────────────────────────────
    // 3️⃣  PRIM'S MST  (greedy with min-heap)
    // ─────────────────────────────────────────────────────────────
    // adj: [neighbor, weight] pairs
    public static int primMST(List<List<int[]>> adj, int n) {
        boolean[] inMST = new boolean[n];
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{0, 0}); // {weight, node}
        int totalWeight = 0, edgesUsed = 0;
        while(!pq.isEmpty() && edgesUsed < n) {
            int[] cur = pq.poll(); int w = cur[0], u = cur[1];
            if(inMST[u]) continue;
            inMST[u] = true; totalWeight += w; edgesUsed++;
            for(int[] edge : adj.get(u))
                if(!inMST[edge[0]]) pq.offer(new int[]{edge[1], edge[0]});
        }
        return edgesUsed == n ? totalWeight : -1;
    }

    // ─────────────────────────────────────────────────────────────
    // 4️⃣  NUMBER OF PROVINCES  (LeetCode 547)
    // ─────────────────────────────────────────────────────────────
    public static int findCircleNum(int[][] isConnected) {
        int n = isConnected.length;
        DSU dsu = new DSU(n);
        for(int i = 0; i < n; i++)
            for(int j = i + 1; j < n; j++)
                if(isConnected[i][j] == 1) dsu.union(i, j);
        return dsu.components;
    }

    // ─────────────────────────────────────────────────────────────
    // 5️⃣  REDUNDANT CONNECTION  (LeetCode 684)
    // ─────────────────────────────────────────────────────────────
    public static int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        DSU dsu = new DSU(n + 1);
        for(int[] e : edges)
            if(!dsu.union(e[0], e[1])) return e;
        return new int[]{};
    }

    // ─────────────────────────────────────────────────────────────
    // 6️⃣  ACCOUNTS MERGE  (LeetCode 721)
    // ─────────────────────────────────────────────────────────────
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        Map<String, Integer> emailToId = new HashMap<>();
        Map<String, String> emailToName = new HashMap<>();
        int id = 0;
        for(List<String> acc : accounts) {
            String name = acc.get(0);
            for(int i = 1; i < acc.size(); i++) {
                String email = acc.get(i);
                emailToId.putIfAbsent(email, id++);
                emailToName.put(email, name);
            }
        }
        DSU dsu = new DSU(id);
        for(List<String> acc : accounts)
            for(int i = 2; i < acc.size(); i++)
                dsu.union(emailToId.get(acc.get(1)), emailToId.get(acc.get(i)));
        Map<Integer, List<String>> groups = new HashMap<>();
        for(String email : emailToId.keySet()) {
            int root = dsu.find(emailToId.get(email));
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(email);
        }
        List<List<String>> result = new ArrayList<>();
        for(List<String> emails : groups.values()) {
            Collections.sort(emails);
            List<String> merged = new ArrayList<>();
            merged.add(emailToName.get(emails.get(0)));
            merged.addAll(emails);
            result.add(merged);
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // 7️⃣  SWIM IN RISING WATER  (LeetCode 778) – Binary Search + DSU
    // ─────────────────────────────────────────────────────────────
    public static int swimInWater(int[][] grid) {
        int n = grid.length;
        int[] pos = new int[n * n]; // pos[elevation] = cell index
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                pos[grid[i][j]] = i * n + j;
        int[][] dirs = {{0,1},{0,-1},{1,0},{-1,0}};
        DSU dsu = new DSU(n * n);
        for(int t = 0; t < n * n; t++) {
            int r = pos[t] / n, c = pos[t] % n;
            for(int[] d : dirs) {
                int nr = r + d[0], nc = c + d[1];
                if(nr >= 0 && nr < n && nc >= 0 && nc < n && grid[nr][nc] <= t)
                    dsu.union(pos[t], nr * n + nc);
            }
            if(dsu.connected(0, n * n - 1)) return t;
        }
        return n * n - 1;
    }

    // ─────────────────────────────────────────────────────────────
    // MAIN DEMO
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // Kruskal
        int[][] edges = {{0,1,4},{0,2,3},{1,2,1},{1,3,2},{2,3,4},{3,4,2},{2,4,5}};
        System.out.println("Kruskal MST weight = " + kruskalMST(5, edges)); // 10

        // Prim
        List<List<int[]>> adj = new ArrayList<>();
        for(int i = 0; i < 5; i++) adj.add(new ArrayList<>());
        int[][] edgesP = {{0,1,4},{0,2,3},{1,2,1},{1,3,2},{2,3,4},{3,4,2},{2,4,5}};
        for(int[] e : edgesP) {
            adj.get(e[0]).add(new int[]{e[1], e[2]});
            adj.get(e[1]).add(new int[]{e[0], e[2]});
        }
        System.out.println("Prim MST weight    = " + primMST(adj, 5)); // 10

        // Provinces
        int[][] conn = {{1,1,0},{1,1,0},{0,0,1}};
        System.out.println("Provinces = " + findCircleNum(conn)); // 2

        // Redundant connection
        int[][] redEdges = {{1,2},{1,3},{2,3}};
        System.out.println("Redundant = " + Arrays.toString(findRedundantConnection(redEdges))); // [2,3]

        // Swim in rising water
        int[][] water = {{0,2},{1,3}};
        System.out.println("Swim in water = " + swimInWater(water)); // 3
    }
}

/*
 * ┌──────────────────────────────────┬──────────────────┬──────────┐
 * │ Algorithm                        │ Time             │ Space    │
 * ├──────────────────────────────────┼──────────────────┼──────────┤
 * │ DSU find/union (path+rank)       │ O(α(n)) ≈ O(1)   │ O(n)     │
 * │ Kruskal MST                      │ O(E log E)       │ O(V)     │
 * │ Prim MST (PQ)                    │ O((V+E) log V)   │ O(V)     │
 * │ Number of Provinces              │ O(n^2 · α(n))    │ O(n)     │
 * │ Redundant Connection             │ O(n · α(n))      │ O(n)     │
 * │ Accounts Merge                   │ O(N·K log(N·K))  │ O(N·K)   │
 * │ Swim in Rising Water             │ O(n^2 log n)     │ O(n^2)   │
 * └──────────────────────────────────┴──────────────────┴──────────┘
 */
