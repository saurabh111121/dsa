class Solution {
    public int[] lexicographicallySmallestArray(int[] nums, int limit) {
        int n = nums.length;

        // Step 1: Sort indices based on nums[i]
        Integer[] indices = new Integer[n];
        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }
        Arrays.sort(indices, Comparator.comparingInt(i -> nums[i]));

        // Step 2: Use Union-Find to group indices
        UnionFind uf = new UnionFind(n);
        for (int i = 1; i < n; i++) {
            if (Math.abs(nums[indices[i]] - nums[indices[i - 1]]) <= limit) {
                uf.union(indices[i], indices[i - 1]);
            }
        }

        // Step 3: Group connected components and sort each group
        Map<Integer, List<Integer>> groups = new HashMap<>();
        for (int i = 0; i < n; i++) {
            int root = uf.find(i);
            groups.computeIfAbsent(root, k -> new ArrayList<>()).add(i);
        }

        for (List<Integer> group : groups.values()) {
            List<Integer> values = new ArrayList<>();
            for (int index : group) {
                values.add(nums[index]);
            }

            // Sort indices and values
            Collections.sort(group);
            Collections.sort(values);

            // Assign sorted values back to the sorted indices
            for (int i = 0; i < group.size(); i++) {
                nums[group.get(i)] = values.get(i);
            }
        }

        return nums;
        
    }

    // Union-Find Implementation
    static class UnionFind {
        private final int[] parent;
        private final int[] rank;

        public UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 1;
            }
        }

        public int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }

        public void union(int x, int y) {
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
}
