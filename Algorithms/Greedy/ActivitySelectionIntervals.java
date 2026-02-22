package Algorithms.Greedy;

/*
===========================================================
Greedy – Activity Selection & Interval Problems
===========================================================
Topics Covered:
 1) Activity Selection (max non-overlapping activities)
 2) Interval Scheduling / Merge Intervals
 3) Meeting Rooms I (can attend all?)
 4) Meeting Rooms II (min rooms needed)
 5) Non-overlapping Intervals (min removals)
 6) Jump Game I (can reach end?)
 7) Jump Game II (min jumps to reach end)
 8) Gas Station (circular route)
===========================================================
*/

import java.util.*;

public class ActivitySelectionIntervals {

    // =========================================================================
    // 1️⃣  ACTIVITY SELECTION  –  max activities with no overlap
    // =========================================================================

    /** Sort by end time, greedily pick earliest ending */
    public static int maxActivities(int[] start, int[] end) {
        int n = start.length;
        Integer[] idx = new Integer[n];
        for(int i = 0; i < n; i++) idx[i] = i;
        Arrays.sort(idx, (a, b) -> end[a] - end[b]);

        int count = 1, lastEnd = end[idx[0]];
        for(int i = 1; i < n; i++) {
            if(start[idx[i]] >= lastEnd) { count++; lastEnd = end[idx[i]]; }
        }
        return count;
    }

    // =========================================================================
    // 2️⃣  MERGE INTERVALS
    // =========================================================================

    public static int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        List<int[]> merged = new ArrayList<>();
        merged.add(intervals[0]);
        for(int i = 1; i < intervals.length; i++) {
            int[] last = merged.get(merged.size() - 1);
            if(intervals[i][0] <= last[1]) last[1] = Math.max(last[1], intervals[i][1]);
            else merged.add(intervals[i]);
        }
        return merged.toArray(new int[0][]);
    }

    // =========================================================================
    // 3️⃣  MEETING ROOMS I  –  can one person attend all?
    // =========================================================================

    public static boolean canAttendMeetings(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);
        for(int i = 1; i < intervals.length; i++)
            if(intervals[i][0] < intervals[i-1][1]) return false;
        return true;
    }

    // =========================================================================
    // 4️⃣  MEETING ROOMS II  –  minimum rooms required
    // =========================================================================

    public static int minMeetingRooms(int[][] intervals) {
        int[] starts = new int[intervals.length];
        int[] ends   = new int[intervals.length];
        for(int i = 0; i < intervals.length; i++) { starts[i] = intervals[i][0]; ends[i] = intervals[i][1]; }
        Arrays.sort(starts); Arrays.sort(ends);
        int rooms = 0, endPtr = 0;
        for(int s : starts) {
            if(s < ends[endPtr]) rooms++;
            else endPtr++;
        }
        return rooms;
    }

    // =========================================================================
    // 5️⃣  NON-OVERLAPPING INTERVALS  –  min intervals to remove
    // =========================================================================

    public static int eraseOverlapIntervals(int[][] intervals) {
        Arrays.sort(intervals, (a, b) -> a[1] - b[1]);
        int count = 0, lastEnd = Integer.MIN_VALUE;
        for(int[] iv : intervals) {
            if(iv[0] >= lastEnd) lastEnd = iv[1];
            else count++;
        }
        return count;
    }

    // =========================================================================
    // 6️⃣  JUMP GAME I  –  can you reach the last index?
    // =========================================================================

    public static boolean canJump(int[] nums) {
        int maxReach = 0;
        for(int i = 0; i < nums.length; i++) {
            if(i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        return true;
    }

    // =========================================================================
    // 7️⃣  JUMP GAME II  –  min jumps to reach last index
    // =========================================================================

    public static int jump(int[] nums) {
        int jumps = 0, curEnd = 0, farthest = 0;
        for(int i = 0; i < nums.length - 1; i++) {
            farthest = Math.max(farthest, i + nums[i]);
            if(i == curEnd) { jumps++; curEnd = farthest; }
        }
        return jumps;
    }

    // =========================================================================
    // 8️⃣  GAS STATION  –  find starting station for circular route
    // =========================================================================

    public static int canCompleteCircuit(int[] gas, int[] cost) {
        int total = 0, tank = 0, start = 0;
        for(int i = 0; i < gas.length; i++) {
            int diff = gas[i] - cost[i];
            total += diff;
            tank  += diff;
            if(tank < 0) { start = i + 1; tank = 0; }
        }
        return total >= 0 ? start : -1;
    }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("Max activities: " + maxActivities(
            new int[]{1,3,0,5,8,5}, new int[]{2,4,6,7,9,9}));  // 4

        int[][] iv = {{1,3},{2,6},{8,10},{15,18}};
        System.out.println("Merge intervals: " + Arrays.deepToString(merge(iv)));

        int[][] mtg = {{0,30},{5,10},{15,20}};
        System.out.println("Can attend all: " + canAttendMeetings(mtg));        // false
        System.out.println("Min rooms:      " + minMeetingRooms(mtg));          // 2

        int[][] ovlp = {{1,2},{2,3},{3,4},{1,3}};
        System.out.println("Min removals:   " + eraseOverlapIntervals(ovlp));   // 1

        System.out.println("Can jump:       " + canJump(new int[]{2,3,1,1,4}));  // true
        System.out.println("Min jumps:      " + jump(new int[]{2,3,1,1,4}));     // 2

        System.out.println("Gas station:    " + canCompleteCircuit(
            new int[]{1,2,3,4,5}, new int[]{3,4,5,1,2}));  // 3
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Operation                  | Time        | Space
---------------------------|-------------|------
Activity Selection         | O(n log n)  | O(1)
Merge Intervals            | O(n log n)  | O(n)
Meeting Rooms I            | O(n log n)  | O(1)
Meeting Rooms II           | O(n log n)  | O(1)
Non-Overlapping Intervals  | O(n log n)  | O(1)
Jump Game I                | O(n)        | O(1)
Jump Game II               | O(n)        | O(1)
Gas Station                | O(n)        | O(1)
===========================================================
*/
