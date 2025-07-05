class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        UnionFind uf = new UnionFind(n + 1);  // Using 1-based indexing
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            if (!uf.union(u, v)) {
                return edge;  // Edge that causes a cycle
            }
        }
        
        return new int[0];  // Should not reach here as per problem constraints
    }
}

class UnionFind {
    private int[] parent;
    private int[] rank;
    
    public UnionFind(int size) {
        parent = new int[size];
        rank = new int[size];
        
        for (int i = 0; i < size; i++) {
            parent[i] = i;  // Each node is its own parent initially
            rank[i] = 1;    // Rank starts at 1
        }
    }
    
    public int find(int x) {
        if (parent[x] != x) {
            parent[x] = find(parent[x]);  // Path compression
        }
        return parent[x];
    }
    
    public boolean union(int x, int y) {
        int rootX = find(x);
        int rootY = find(y);
        
        if (rootX == rootY) {
            return false;  // Cycle detected
        }
        
        // Union by rank
        if (rank[rootX] > rank[rootY]) {
            parent[rootY] = rootX;
        } else if (rank[rootX] < rank[rootY]) {
            parent[rootX] = rootY;
        } else {
            parent[rootY] = rootX;
            rank[rootX]++;
        }
        
        return true;
    }
}
