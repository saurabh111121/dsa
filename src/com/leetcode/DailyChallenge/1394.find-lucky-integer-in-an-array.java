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
        HashMap<Integer, Integer> map = new HashMap<>();
        for (int i : arr) {
            map.put(i, map.getOrDefault(i, 0) + 1);
        }
        int ans = -1;
        for (int key : map.keySet()) {
            if(key == map.get(key)) {
                if(key > ans) ans = key;
            }
        }
        return ans;
    }
}
// @lc code=end

