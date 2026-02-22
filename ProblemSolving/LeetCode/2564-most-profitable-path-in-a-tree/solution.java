
public class Solution {
    public int mostProfitablePath(int[][] edges, int bob, int[] amount) {
        int n = amount.length;
        
        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            graph.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            graph.get(edge[0]).add(edge[1]);
            graph.get(edge[1]).add(edge[0]);
        }

        int[] bobTime = new int[n];
        Arrays.fill(bobTime, Integer.MAX_VALUE);
        findBobPath(bob, 0, graph, bobTime, new boolean[n], 0);

        return dfsAlice(0, -1, 0, graph, bobTime, amount);
    }

    private boolean findBobPath(int node, int time, List<List<Integer>> graph, int[] bobTime, boolean[] visited, int target) {
        if (node == target) {
            bobTime[node] = time;
            return true;
        }
        visited[node] = true;
        for (int neighbor : graph.get(node)) {
            if (!visited[neighbor] && findBobPath(neighbor, time + 1, graph, bobTime, visited, target)) {
                bobTime[node] = time;
                return true;
            }
        }
        return false;
    }

    private int dfsAlice(int node, int parent, int time, List<List<Integer>> graph, int[] bobTime, int[] amount) {
        int aliceProfit = 0;
        
        if (time < bobTime[node]) {
            aliceProfit = amount[node];
        } else if (time == bobTime[node]) {
            aliceProfit = amount[node] / 2;
        }

        int maxSubtreeProfit = Integer.MIN_VALUE;
        boolean isLeaf = true;
        
        for (int neighbor : graph.get(node)) {
            if (neighbor != parent) {
                isLeaf = false;
                maxSubtreeProfit = Math.max(maxSubtreeProfit, dfsAlice(neighbor, node, time + 1, graph, bobTime, amount));
            }
        }
        
        return aliceProfit + (isLeaf ? 0 : maxSubtreeProfit);
    }
}
