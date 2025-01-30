
class Solution {
    class DSU {
        int[] parent, rank;
        
        DSU(int n) {
            parent = new int[n + 1];
            rank = new int[n + 1];
            for (int i = 1; i <= n; i++) {
                parent[i] = i;
            }
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if (rootX != rootY) {
                if (rank[rootX] > rank[rootY]) {
                    parent[rootY] = rootX;
                } else if (rank[rootX] < rank[rootY]) {
                    parent[rootX] = rootY;
                } else {
                    parent[rootY] = rootX;
                    rank[rootX]++;
                }
            }
        }
    }
    
    public int magnificentSets(int n, int[][] edges) {
        // Step 1: Build Graph
        List<Integer>[] graph = new ArrayList[n + 1];
        for (int i = 1; i <= n; i++) graph[i] = new ArrayList<>();
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }
        
        // Step 2: Find Connected Components Using Union-Find
        DSU dsu = new DSU(n);
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }
        
        // Step 3: Check Bipartiteness and Find Max Depth
        int maxGroups = 0;
        boolean[] visited = new boolean[n + 1];
        
        for (int i = 1; i <= n; i++) {
            if (!visited[i]) {
                // Get all nodes in the current component
                List<Integer> component = new ArrayList<>();
                Queue<Integer> queue = new LinkedList<>();
                queue.add(i);
                visited[i] = true;
                
                while (!queue.isEmpty()) {
                    int node = queue.poll();
                    component.add(node);
                    for (int neighbor : graph[node]) {
                        if (!visited[neighbor]) {
                            visited[neighbor] = true;
                            queue.add(neighbor);
                        }
                    }
                }
                
                // Step 4: Run BFS from every node in the component to get max depth
                int maxDepth = 0;
                for (int node : component) {
                    int depth = bfsMaxDepth(graph, node);
                    if (depth == -1) return -1; // Odd-length cycle detected
                    maxDepth = Math.max(maxDepth, depth);
                }
                
                maxGroups += maxDepth;
            }
        }
        
        return maxGroups;
    }
    
    private int bfsMaxDepth(List<Integer>[] graph, int start) {
        Queue<Integer> queue = new LinkedList<>();
        Map<Integer, Integer> distance = new HashMap<>();
        
        queue.add(start);
        distance.put(start, 1); // Group index starts from 1
        int maxDepth = 1;
        
        while (!queue.isEmpty()) {
            int node = queue.poll();
            int currDepth = distance.get(node);
            
            for (int neighbor : graph[node]) {
                if (!distance.containsKey(neighbor)) {
                    distance.put(neighbor, currDepth + 1);
                    maxDepth = Math.max(maxDepth, currDepth + 1);
                    queue.add(neighbor);
                } else if (Math.abs(distance.get(neighbor) - currDepth) != 1) {
                    return -1; // Odd-length cycle detected
                }
            }
        }
        
        return maxDepth;
    }
}

