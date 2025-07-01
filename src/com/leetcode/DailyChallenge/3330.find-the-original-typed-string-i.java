package DailyChallenge;
/*
 * @lc app=leetcode id=3330 lang=java
 *
 * [3330] Find the Original Typed String I
 */

// @lc code=start
class Solution {
    public int possibleStringCount(String word) {
        int n = word.length();
        int ans = 1;
        for(int i=1;i<n;++i) {
            if(word.charAt(i) == word.charAt(i-1)) {
                ++ans;
            }
        }
        return ans;
    }
}
// @lc code=end

