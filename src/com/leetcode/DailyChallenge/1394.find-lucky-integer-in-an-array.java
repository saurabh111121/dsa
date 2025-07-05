package DailyChallenge;
/*
 * @lc app=leetcode id=1394 lang=java
 *
 * [1394] Find Lucky Integer in an Array
 */

// @lc code=start

import java.util.HashMap;

class Solution {
    public int findLucky(int[] arr) {
        int[] freq = new int[501];
        for(int num : arr) {
            freq[num]++;
        }
        for(int i=500; i>0; i--) {
            if(freq[i] == i) {
                return i;
            }
        }
        return -1;
    }
}
// @lc code=end

