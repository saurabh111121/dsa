class Solution {
    public long maxSum(int[][] grid, int[] limits, int k) {
        List<Integer> values = new ArrayList<>();
        for (int i = 0; i < grid.length; i++) {
            int take = Math.min(limits[i], grid[i].length);
            List<Integer> row = new ArrayList<>();
            for (int num : grid[i]) row.add(num);
            row.sort(Collections.reverseOrder());
            values.addAll(row.subList(0, take));
        }
        values.sort(Collections.reverseOrder());
        long sum = 0;
        for (int i = 0; i < Math.min(k, values.size()); i++) sum += values.get(i);
        return sum;
    }    
}
