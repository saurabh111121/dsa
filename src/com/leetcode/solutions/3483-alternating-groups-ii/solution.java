class Solution {
    public int numberOfAlternatingGroups(int[] colors, int k) {
        int[] extendedColors = new int[colors.length + k - 1];
        System.arraycopy(colors, 0, extendedColors, 0, colors.length);
        System.arraycopy(colors, 0, extendedColors, colors.length, k - 1);
        
        int result = 0, left = 0;
        for (int right = 1; right < extendedColors.length; right++) {
            if (extendedColors[right] == extendedColors[right - 1]) left = right;
            if (right - left + 1 >= k) result++;
        }
        return result;
        
    }
}
