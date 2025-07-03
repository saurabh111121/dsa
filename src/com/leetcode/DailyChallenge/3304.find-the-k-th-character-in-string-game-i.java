package DailyChallenge;
/*
 * @lc app=leetcode id=3304 lang=java
 *
 * [3304] Find the K-th Character in String Game I
 */

// @lc code=start
class Solution {
    public char kthCharacter(int k) {
         /* 
        int ans = 0, t;
        while(k != 1) {
            t = 31 - Integer.numberOfLeadingZeros(k);
            if((1 << t) == k) t--;
            k = k - (1 << t);
            ans++;
        }
        return (char) ('a' + ans);
        */
        return (char)('a' + Integer.bitCount(k - 1));
    }
}
// @lc code=end

