class Solution {
    class DSU {
        int[] parent, size;
        public DSU(int n) {
            parent = new int[n];
            size = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                size[i] = 1;
            }
        }
        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]);
            }
            return parent[x];
        }
        public void union(int x, int y) {
            int rootX = find(x), rootY = find(y);
            if (rootX != rootY) {
                if (size[rootX] > size[rootY]) {
                    parent[rootY] = rootX;
                    size[rootX] += size[rootY];
                } else {
                    parent[rootX] = rootY;
                    size[rootY] += size[rootX];
                }
            }
        }
    }

    public int countCompleteComponents(int n, int[][] edges) {
        DSU dsu = new DSU(n);
        Map<Integer, Integer> componentSize = new HashMap<>();
        Map<Integer, Integer> edgeCount = new HashMap<>();
        
        for (int[] edge : edges) {
            dsu.union(edge[0], edge[1]);
        }
        
        for (int i = 0; i < n; i++) {
            int root = dsu.find(i);
            componentSize.put(root, componentSize.getOrDefault(root, 0) + 1);
        }
        
        for (int[] edge : edges) {
            int root = dsu.find(edge[0]);
            edgeCount.put(root, edgeCount.getOrDefault(root, 0) + 1);
        }
        
        int completeCount = 0;
        for (int root : componentSize.keySet()) {
            int size = componentSize.get(root);
            if (edgeCount.getOrDefault(root, 0) == size * (size - 1) / 2) {
                completeCount++;
            }
        }
        
        return completeCount;
        
    }
}
