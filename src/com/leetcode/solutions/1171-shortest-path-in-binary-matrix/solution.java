class Solution {
    static class Cell{
        int row;
        int col;
        int distance;
        Cell(int row, int col, int distance) {
            this.row = row;
            this.col = col;
            this.distance = distance;
        }
    }


    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        if(grid[0][0] == 1 || grid[n-1][n-1] == 1) {
            return -1;
        }

        // Directions for 8-directional movement
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},         {0, 1},
            {1, -1}, {1, 0}, {1, 1}
        };

        Queue<Cell> queue = new LinkedList<>();
        queue.add(new Cell(0, 0, 1));
        grid[0][0] = 1; //visited

        while(!queue.isEmpty()) {
            Cell current = queue.poll();

            if(current.row == n - 1 && current.col == n -1 ){
                return current.distance;
            }

            for(int[] dir: directions) {
                int newRow = current.row + dir[0];
                int newCol = current.col + dir[1];

                if(newRow >=0 && newRow < n && newCol >=0 && newCol < n && grid[newRow][newCol] == 0) {
                    queue.add(new Cell(newRow, newCol, current.distance + 1));
                    grid[newRow][newCol] = 1; // visited
                }
            }
        }
        // if no path was found
        return -1;
    }
}
