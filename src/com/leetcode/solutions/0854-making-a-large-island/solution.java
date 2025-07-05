class Solution {
    public int largestIsland(int[][] grid) {
        int n = grid.length;
        int maxIsland = 0;
        int index = 2;
        Map<Integer, Integer> areaMap = new HashMap<>();
        areaMap.put(0, 0);

        // Step 1: Use DFS to mark islands and calculate their sizes
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 1) {
                    int area = dfs(grid, i, j, index);
                    areaMap.put(index, area);
                    maxIsland = Math.max(maxIsland, area);
                    index++;
                }
            }
        }

        // Step 2: Try flipping each 0 and compute the possible largest island
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                if (grid[i][j] == 0) {
                    Set<Integer> seen = new HashSet<>();
                    int newSize = 1; // The flipped cell itself
                    
                    for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
                        int ni = i + dir[0], nj = j + dir[1];
                        if (ni >= 0 && ni < n && nj >= 0 && nj < n && grid[ni][nj] > 1) {
                            seen.add(grid[ni][nj]);
                        }
                    }
                    
                    for (int id : seen) {
                        newSize += areaMap.get(id);
                    }
                    maxIsland = Math.max(maxIsland, newSize);
                }
            }
        }
        
        return maxIsland;
    }
    
    private int dfs(int[][] grid, int i, int j, int index) {
        if (i < 0 || j < 0 || i >= grid.length || j >= grid[0].length || grid[i][j] != 1) {
            return 0;
        }
        grid[i][j] = index;
        int area = 1;
        for (int[] dir : new int[][]{{0, 1}, {1, 0}, {0, -1}, {-1, 0}}) {
            area += dfs(grid, i + dir[0], j + dir[1], index);
        }
        return area;
    }
}
