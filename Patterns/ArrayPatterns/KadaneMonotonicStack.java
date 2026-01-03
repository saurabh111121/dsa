package Patterns.ArrayPatterns;

import java.util.*;

/**
 * Kadane's Algorithm & Monotonic Stack Pattern
 * =============================================
 * Topics Covered:
 *  1. Kadane's Algorithm (max subarray sum)
 *  2. Kadane's Variant – Max Product Subarray
 *  3. Monotonic Stack – Next Greater Element
 *  4. Monotonic Stack – Next Smaller Element
 *  5. Largest Rectangle in Histogram
 *  6. Maximal Rectangle in Binary Matrix
 *  7. Sum of Subarray Minimums
 *  8. Stock Span Problem
 */
public class KadaneMonotonicStack {

    // ─────────────────────────────────────────────────────────────
    // 1️⃣  KADANE'S ALGORITHM  (LeetCode 53)
    // ─────────────────────────────────────────────────────────────
    public static int maxSubarraySum(int[] arr) {
        int maxSum = arr[0], cur = arr[0];
        for (int i = 1; i < arr.length; i++) {
            cur = Math.max(arr[i], cur + arr[i]);
            maxSum = Math.max(maxSum, cur);
        }
        return maxSum;
    }

    /** Also return [start, end] indices of the subarray */
    public static int[] maxSubarrayWithIndices(int[] arr) {
        int maxSum = arr[0], cur = arr[0];
        int start = 0, end = 0, tempStart = 0;
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > cur + arr[i]) { cur = arr[i]; tempStart = i; }
            else cur += arr[i];
            if (cur > maxSum) { maxSum = cur; start = tempStart; end = i; }
        }
        return new int[]{maxSum, start, end};
    }

    // ─────────────────────────────────────────────────────────────
    // 2️⃣  MAX PRODUCT SUBARRAY  (LeetCode 152)
    // ─────────────────────────────────────────────────────────────
    public static int maxProductSubarray(int[] arr) {
        int maxP = arr[0], minP = arr[0], res = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] < 0) { int tmp = maxP; maxP = minP; minP = tmp; }
            maxP = Math.max(arr[i], maxP * arr[i]);
            minP = Math.min(arr[i], minP * arr[i]);
            res  = Math.max(res, maxP);
        }
        return res;
    }

    // ─────────────────────────────────────────────────────────────
    // 3️⃣  NEXT GREATER ELEMENT  (monotonic stack – decreasing)
    // ─────────────────────────────────────────────────────────────
    public static int[] nextGreater(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] < arr[i])
                res[stack.pop()] = arr[i];
            stack.push(i);
        }
        return res;
    }

    /** Circular version (LeetCode 503) */
    public static int[] nextGreaterCircular(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < 2 * n; i++) {
            int idx = i % n;
            while (!stack.isEmpty() && arr[stack.peek()] < arr[idx])
                res[stack.pop()] = arr[idx];
            if (i < n) stack.push(idx);
        }
        return res;
    }

    // ─────────────────────────────────────────────────────────────
    // 4️⃣  NEXT SMALLER ELEMENT  (monotonic stack – increasing)
    // ─────────────────────────────────────────────────────────────
    public static int[] nextSmaller(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        Arrays.fill(res, -1);
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i])
                res[stack.pop()] = arr[i];
            stack.push(i);
        }
        return res;
    }

    // ─────────────────────────────────────────────────────────────
    // 5️⃣  LARGEST RECTANGLE IN HISTOGRAM  (LeetCode 84)
    // ─────────────────────────────────────────────────────────────
    public static int largestRectangleHistogram(int[] heights) {
        int n = heights.length, maxArea = 0;
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i <= n; i++) {
            int h = (i == n) ? 0 : heights[i];
            while (!stack.isEmpty() && heights[stack.peek()] > h) {
                int height = heights[stack.pop()];
                int width  = stack.isEmpty() ? i : i - stack.peek() - 1;
                maxArea = Math.max(maxArea, height * width);
            }
            stack.push(i);
        }
        return maxArea;
    }

    // ─────────────────────────────────────────────────────────────
    // 6️⃣  MAXIMAL RECTANGLE IN BINARY MATRIX  (LeetCode 85)
    // ─────────────────────────────────────────────────────────────
    public static int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int n = matrix[0].length, maxArea = 0;
        int[] heights = new int[n];
        for (char[] row : matrix) {
            for (int j = 0; j < n; j++)
                heights[j] = row[j] == '0' ? 0 : heights[j] + 1;
            maxArea = Math.max(maxArea, largestRectangleHistogram(heights));
        }
        return maxArea;
    }

    // ─────────────────────────────────────────────────────────────
    // 7️⃣  SUM OF SUBARRAY MINIMUMS  (LeetCode 907)
    // ─────────────────────────────────────────────────────────────
    public static int sumSubarrayMins(int[] arr) {
        long MOD = 1_000_000_007L, res = 0;
        int n = arr.length;
        int[] left = new int[n], right = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        // distance to previous less element
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) stack.pop();
            left[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        stack.clear();
        // distance to next less or equal element
        for (int i = n - 1; i >= 0; i--) {
            while (!stack.isEmpty() && arr[stack.peek()] > arr[i]) stack.pop();
            right[i] = stack.isEmpty() ? n - i : stack.peek() - i;
            stack.push(i);
        }
        for (int i = 0; i < n; i++)
            res = (res + (long) arr[i] * left[i] * right[i]) % MOD;
        return (int) res;
    }

    // ─────────────────────────────────────────────────────────────
    // 8️⃣  STOCK SPAN PROBLEM
    // ─────────────────────────────────────────────────────────────
    public static int[] stockSpan(int[] prices) {
        int n = prices.length;
        int[] span = new int[n];
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i = 0; i < n; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] <= prices[i]) stack.pop();
            span[i] = stack.isEmpty() ? i + 1 : i - stack.peek();
            stack.push(i);
        }
        return span;
    }

    // ─────────────────────────────────────────────────────────────
    // MAIN DEMO
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("=== Kadane ===");
        System.out.println(maxSubarraySum(new int[]{-2,1,-3,4,-1,2,1,-5,4})); // 6
        System.out.println(Arrays.toString(maxSubarrayWithIndices(new int[]{-2,1,-3,4,-1,2,1,-5,4})));

        System.out.println("\n=== Max Product Subarray ===");
        System.out.println(maxProductSubarray(new int[]{2,3,-2,4})); // 6

        System.out.println("\n=== Next Greater Element ===");
        System.out.println(Arrays.toString(nextGreater(new int[]{4,5,2,10,8}))); // [5,10,10,-1,-1]
        System.out.println(Arrays.toString(nextGreaterCircular(new int[]{1,2,1}))); // [2,-1,2]

        System.out.println("\n=== Next Smaller Element ===");
        System.out.println(Arrays.toString(nextSmaller(new int[]{4,5,2,10,8}))); // [2,2,-1,-1,-1] wait: [2,2,-1,8,-1]

        System.out.println("\n=== Largest Rectangle in Histogram ===");
        System.out.println(largestRectangleHistogram(new int[]{2,1,5,6,2,3})); // 10

        System.out.println("\n=== Maximal Rectangle ===");
        char[][] mat = {{'1','0','1','0','0'},{'1','0','1','1','1'},{'1','1','1','1','1'},{'1','0','0','1','0'}};
        System.out.println(maximalRectangle(mat)); // 6

        System.out.println("\n=== Sum Subarray Mins ===");
        System.out.println(sumSubarrayMins(new int[]{3,1,2,4})); // 17

        System.out.println("\n=== Stock Span ===");
        System.out.println(Arrays.toString(stockSpan(new int[]{100,80,60,70,60,75,85}))); // [1,1,1,2,1,4,6]
    }
}

/*
 * ┌──────────────────────────────────────┬──────────┬──────────┐
 * │ Algorithm                            │ Time     │ Space    │
 * ├──────────────────────────────────────┼──────────┼──────────┤
 * │ Kadane                               │ O(n)     │ O(1)     │
 * │ Max Product Subarray                 │ O(n)     │ O(1)     │
 * │ Next Greater / Smaller               │ O(n)     │ O(n)     │
 * │ Largest Rectangle Histogram          │ O(n)     │ O(n)     │
 * │ Maximal Rectangle                    │ O(m·n)   │ O(n)     │
 * │ Sum Subarray Mins                    │ O(n)     │ O(n)     │
 * │ Stock Span                           │ O(n)     │ O(n)     │
 * └──────────────────────────────────────┴──────────┴──────────┘
 */
