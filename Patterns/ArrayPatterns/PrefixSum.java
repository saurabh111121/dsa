package Patterns.ArrayPatterns;

/**
 * Prefix Sum Pattern
 * ==================
 * Topics Covered:
 *  1. 1-D Prefix Sum (range queries)
 *  2. 2-D Prefix Sum (submatrix queries)
 *  3. Subarray Sum Equals K
 *  4. Number of Subarrays with Sum Divisible by K
 *  5. Equilibrium Index
 *  6. Product Array (without division)
 *  7. XOR Prefix (range XOR)
 */
public class PrefixSum {

    // ─────────────────────────────────────────────────────────────
    // 1️⃣  1-D PREFIX SUM
    // ─────────────────────────────────────────────────────────────
    /** Build prefix array: prefix[i] = sum of arr[0..i-1] */
    public static int[] buildPrefix(int[] arr) {
        int n = arr.length;
        int[] prefix = new int[n + 1];
        for(int i = 0; i < n; i++) prefix[i + 1] = prefix[i] + arr[i];
        return prefix;
    }

    /** O(1) range sum query [l, r] (0-indexed, inclusive) */
    public static int rangeSum(int[] prefix, int l, int r) {
        return prefix[r + 1] - prefix[l];
    }

    // ─────────────────────────────────────────────────────────────
    // 2️⃣  2-D PREFIX SUM
    // ─────────────────────────────────────────────────────────────
    public static int[][] build2DPrefix(int[][] mat) {
        int m = mat.length, n = mat[0].length;
        int[][] p = new int[m + 1][n + 1];
        for(int i = 1; i <= m; i++)
            for(int j = 1; j <= n; j++)
                p[i][j] = mat[i-1][j-1] + p[i-1][j] + p[i][j-1] - p[i-1][j-1];
        return p;
    }

    /** Sum of submatrix from (r1,c1) to (r2,c2) 0-indexed */
    public static int subMatrixSum(int[][] p, int r1, int c1, int r2, int c2) {
        return p[r2+1][c2+1] - p[r1][c2+1] - p[r2+1][c1] + p[r1][c1];
    }

    // ─────────────────────────────────────────────────────────────
    // 3️⃣  SUBARRAY SUM EQUALS K  (LeetCode 560)
    // ─────────────────────────────────────────────────────────────
    /** Count subarrays with sum = k using prefix sum + HashMap O(n) */
    public static int subarraySumK(int[] arr, int k) {
        java.util.Map<Integer, Integer> map = new java.util.HashMap<>();
        map.put(0, 1);
        int sum = 0, count = 0;
        for(int x : arr) {
            sum += x;
            count += map.getOrDefault(sum - k, 0);
            map.merge(sum, 1, Integer::sum);
        }
        return count;
    }

    // ─────────────────────────────────────────────────────────────
    // 4️⃣  SUBARRAYS DIVISIBLE BY K  (LeetCode 974)
    // ─────────────────────────────────────────────────────────────
    public static int subarraysDivByK(int[] arr, int k) {
        int[] count = new int[k];
        count[0] = 1;
        int sum = 0, result = 0;
        for(int x : arr) {
            sum = ((sum + x) % k + k) % k;  // handle negatives
            result += count[sum];
            count[sum]++;
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // 5️⃣  EQUILIBRIUM INDEX
    // ─────────────────────────────────────────────────────────────
    /** Index where left sum == right sum */
    public static int equilibriumIndex(int[] arr) {
        int total = 0;
        for(int x : arr) total += x;
        int left = 0;
        for(int i = 0; i < arr.length; i++) {
            total -= arr[i];      // right sum
            if(left == total) return i;
            left += arr[i];
        }
        return -1;
    }

    // ─────────────────────────────────────────────────────────────
    // 6️⃣  PRODUCT ARRAY WITHOUT DIVISION  (LeetCode 238)
    // ─────────────────────────────────────────────────────────────
    public static int[] productExceptSelf(int[] arr) {
        int n = arr.length;
        int[] res = new int[n];
        res[0] = 1;
        for(int i = 1; i < n; i++) res[i] = res[i - 1] * arr[i - 1];   // prefix products
        int right = 1;
        for(int i = n - 1; i >= 0; i--) {
            res[i] *= right;
            right *= arr[i];
        }
        return res;
    }

    // ─────────────────────────────────────────────────────────────
    // 7️⃣  XOR PREFIX  (range XOR in O(1) after O(n) build)
    // ─────────────────────────────────────────────────────────────
    public static int[] buildXorPrefix(int[] arr) {
        int n = arr.length;
        int[] xp = new int[n + 1];
        for(int i = 0; i < n; i++) xp[i + 1] = xp[i] ^ arr[i];
        return xp;
    }

    public static int rangeXor(int[] xp, int l, int r) {
        return xp[r + 1] ^ xp[l];
    }

    // ─────────────────────────────────────────────────────────────
    // MAIN DEMO
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 5};
        int[] pre = buildPrefix(arr);

        System.out.println("Range sum [1,3] = " + rangeSum(pre, 1, 3)); // 9

        int[][] mat = {{1,2,3},{4,5,6},{7,8,9}};
        int[][] p2d = build2DPrefix(mat);
        System.out.println("Submatrix (0,0)-(1,1) = " + subMatrixSum(p2d, 0, 0, 1, 1)); // 12

        int[] arr2 = {1,1,1};
        System.out.println("Subarray sum=2 count = " + subarraySumK(arr2, 2)); // 2

        int[] arr3 = {4,5,0,-2,-3,1};
        System.out.println("Subarrays div by 5 = " + subarraysDivByK(arr3, 5)); // 7

        int[] arr4 = {-7,1,5,2,-4,3,0};
        System.out.println("Equilibrium index = " + equilibriumIndex(arr4)); // 3

        int[] arr5 = {1,2,3,4,5};
        System.out.println("Product except self = " + java.util.Arrays.toString(productExceptSelf(arr5)));

        int[] xp = buildXorPrefix(arr);
        System.out.println("Range XOR [1,3] = " + rangeXor(xp, 1, 3)); // 2^3^4 = 5
    }
}

/*
 * ┌───────────────────────────────────┬──────────┬──────────┐
 * │ Operation                         │ Time     │ Space    │
 * ├───────────────────────────────────┼──────────┼──────────┤
 * │ Build 1-D prefix                  │ O(n)     │ O(n)     │
 * │ Range sum query                   │ O(1)     │ O(1)     │
 * │ Build 2-D prefix                  │ O(m·n)   │ O(m·n)   │
 * │ Submatrix query                   │ O(1)     │ O(1)     │
 * │ Subarray sum = k                  │ O(n)     │ O(n)     │
 * │ Subarrays div by k                │ O(n)     │ O(k)     │
 * │ Equilibrium index                 │ O(n)     │ O(1)     │
 * │ Product except self               │ O(n)     │ O(1)*    │
 * │ Range XOR                         │ O(1)     │ O(1)     │
 * └───────────────────────────────────┴──────────┴──────────┘
 */
