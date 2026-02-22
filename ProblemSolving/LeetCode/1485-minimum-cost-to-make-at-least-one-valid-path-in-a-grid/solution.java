class Solution {
    private static final int[][] DIRECTIONS = {{0, 1}, {0, -1}, {1, 0}, {-1, 0}};

    public int minCost(int[][] grid) {
        int m = grid.length;
        int n = grid[0].length;
        int[][] cost = new int[m][n]; // Cost to reach each cell
        for (int[] row : cost) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }
        cost[0][0] = 0; // Start with no cost at (0, 0)

        // PriorityQueue to prioritize cells with the lowest cost
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[2]));
        pq.offer(new int[]{0, 0, 0}); // {row, col, cost}

        while (!pq.isEmpty()) {
            int[] current = pq.poll();
            int x = current[0], y = current[1], currCost = current[2];

            // If we reach the bottom-right cell, return the cost
            if (x == m - 1 && y == n - 1) {
                return currCost;
            }

            // Skip processing if a better cost has already been found for this cell
            if (currCost > cost[x][y]) {
                continue;
            }

            // Explore all 4 directions
            for (int dir = 0; dir < 4; dir++) {
                int newX = x + DIRECTIONS[dir][0];
                int newY = y + DIRECTIONS[dir][1];
                int newCost = currCost + (dir + 1 == grid[x][y] ? 0 : 1); // No cost if following the arrow

                if (newX >= 0 && newX < m && newY >= 0 && newY < n && newCost < cost[newX][newY]) {
                    cost[newX][newY] = newCost;
                    pq.offer(new int[]{newX, newY, newCost});
                }
            }
        }

        return -1; // If no valid path exists, return -1 (shouldn't happen as per problem constraints)
    
        
    }
}
