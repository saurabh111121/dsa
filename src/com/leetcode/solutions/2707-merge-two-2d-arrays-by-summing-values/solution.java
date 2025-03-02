class Solution {
    public int[][] mergeArrays(int[][] nums1, int[][] nums2) {
        int i = 0, j = 0;
        List<int[]> ans = new ArrayList<>();
        while (i < nums1.length && j < nums2.length) {
            if (nums1[i][0] == nums2[j][0]) {
                ans.add(new int[]{nums1[i][0], nums1[i++][1] + nums2[j++][1]});
            } else if (nums1[i][0] < nums2[j][0]) {
                ans.add(new int[]{nums1[i][0], nums1[i++][1]});
            } else {
                ans.add(new int[]{nums2[j][0], nums2[j++][1]});
            }
        }
        while (i < nums1.length) {
            ans.add(new int[]{nums1[i][0], nums1[i++][1]});
        }
        while (j < nums2.length) {
            ans.add(new int[]{nums2[j][0], nums2[j++][1]});
        }
        return ans.toArray(new int[ans.size()][]);
    }
}
