class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;

        // Reverse graph representation
        List<List<Integer>> reversedGraph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            reversedGraph.add(new ArrayList<>());
        }
        int[] inDegree = new int[n]; // In-degree of each node

        // Build reversed graph and compute in-degree
        for (int i = 0; i < n; i++) {
            for (int neighbor : graph[i]) {
                reversedGraph.get(neighbor).add(i);
                inDegree[i]++;
            }
        }

        // Start with terminal nodes (nodes with in-degree 0)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        boolean[] safe = new boolean[n];
        while (!queue.isEmpty()) {
            int node = queue.poll();
            safe[node] = true; // Mark node as safe
            for (int neighbor : reversedGraph.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Collect all safe nodes
        List<Integer> result = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (safe[i]) {
                result.add(i);
            }
        }
        return result;
        
    }
}
