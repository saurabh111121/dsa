class Solution {
    public int trapRainWater(int[][] heightMap) {
        if (heightMap == null || heightMap.length == 0 || heightMap[0].length == 0) return 0;

        int m = heightMap.length, n = heightMap[0].length;
        boolean[][] visited = new boolean[m][n];
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a, b) -> a[0] - b[0]);

        // Add boundary cells to the heap
        for (int i = 0; i < m; i++) {
            minHeap.offer(new int[] {heightMap[i][0], i, 0});
            minHeap.offer(new int[] {heightMap[i][n - 1], i, n - 1});
            visited[i][0] = true;
            visited[i][n - 1] = true;
        }
        for (int j = 1; j < n - 1; j++) {
            minHeap.offer(new int[] {heightMap[0][j], 0, j});
            minHeap.offer(new int[] {heightMap[m - 1][j], m - 1, j});
            visited[0][j] = true;
            visited[m - 1][j] = true;
        }

        // Directions for moving in the grid
        int[][] directions = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        int waterTrapped = 0;

        // Process the heap
        while (!minHeap.isEmpty()) {
            int[] cell = minHeap.poll();
            int height = cell[0], row = cell[1], col = cell[2];

            for (int[] dir : directions) {
                int newRow = row + dir[0];
                int newCol = col + dir[1];

                // Check bounds and if the cell is already visited
                if (newRow >= 0 && newRow < m && newCol >= 0 && newCol < n && !visited[newRow][newCol]) {
                    // Calculate water trapped at this cell
                    waterTrapped += Math.max(0, height - heightMap[newRow][newCol]);

                    // Update the neighbor's height and add it to the heap
                    minHeap.offer(new int[] {Math.max(height, heightMap[newRow][newCol]), newRow, newCol});
                    visited[newRow][newCol] = true;
                }
            }
        }

        return waterTrapped;
        
    }
}
