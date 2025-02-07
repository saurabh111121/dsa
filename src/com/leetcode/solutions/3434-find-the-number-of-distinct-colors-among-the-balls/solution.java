class Solution {
    public int[] queryResults(int limit, int[][] queries) {
        HashMap<Integer, Integer> ballColorMap = new HashMap<>();
        Map<Integer, Integer> colorFrequencyMap = new HashMap<>();
        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int ball = queries[i][0];
            int color = queries[i][1];

            // If the ball already has a color, decrement its count
            if (ballColorMap.containsKey(ball)) {
                int oldColor = ballColorMap.get(ball);
                colorFrequencyMap.put(oldColor, colorFrequencyMap.get(oldColor) - 1);
                
                // Remove from colorFrequencyMap if the count reaches zero
                if (colorFrequencyMap.get(oldColor) == 0) {
                    colorFrequencyMap.remove(oldColor);
                }
            }

            // Assign new color to the ball
            ballColorMap.put(ball, color);
            
            // Increase frequency of the new color
            colorFrequencyMap.put(color, colorFrequencyMap.getOrDefault(color, 0) + 1);

            // Store the number of distinct colors
            result[i] = colorFrequencyMap.size();
        }
        return result;
        
    }
}
