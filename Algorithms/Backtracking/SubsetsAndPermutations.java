package Algorithms.Backtracking;

/*
===========================================================
Backtracking – Subsets & Permutations
===========================================================
Topics Covered:
 1) Subsets (no duplicates)
 2) Subsets II (with duplicates)
 3) Permutations (no duplicates)
 4) Permutations II (with duplicates)
 5) Combination Sum I (unlimited use)
 6) Combination Sum II (each element once)
 7) Combination Sum III (k numbers summing to n)
===========================================================
*/

import java.util.*;

public class SubsetsAndPermutations {

    // =========================================================================
    // 1️⃣  SUBSETS (no duplicates)
    // =========================================================================

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums, int start,
                                   List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for(int i = start; i < nums.length; i++) {
            current.add(nums[i]);
            backtrack(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    /** Iterative bit-masking approach */
    public static List<List<Integer>> subsetsIterative(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        int total = 1 << nums.length;
        for(int mask = 0; mask < total; mask++) {
            List<Integer> subset = new ArrayList<>();
            for(int i = 0; i < nums.length; i++)
                if((mask >> i & 1) == 1) subset.add(nums[i]);
            result.add(subset);
        }
        return result;
    }

    // =========================================================================
    // 2️⃣  SUBSETS II (array may contain duplicates)
    // =========================================================================

    public static List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        backtrackII(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void backtrackII(int[] nums, int start,
                                     List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for(int i = start; i < nums.length; i++) {
            if(i > start && nums[i] == nums[i - 1]) continue;  // skip dup
            current.add(nums[i]);
            backtrackII(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    // =========================================================================
    // 3️⃣  PERMUTATIONS (no duplicates)
    // =========================================================================

    public static List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        permuteHelper(nums, 0, result);
        return result;
    }

    private static void permuteHelper(int[] nums, int start, List<List<Integer>> result) {
        if(start == nums.length) {
            List<Integer> perm = new ArrayList<>();
            for(int n : nums) perm.add(n);
            result.add(perm);
            return;
        }
        for(int i = start; i < nums.length; i++) {
            swap(nums, start, i);
            permuteHelper(nums, start + 1, result);
            swap(nums, start, i);
        }
    }

    /** Using visited array – avoids modifying input */
    public static List<List<Integer>> permuteVisited(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permuteVisitedHelper(nums, visited, new ArrayList<>(), result);
        return result;
    }

    private static void permuteVisitedHelper(int[] nums, boolean[] visited,
                                              List<Integer> current, List<List<Integer>> result) {
        if(current.size() == nums.length) { result.add(new ArrayList<>(current)); return; }
        for(int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;
            visited[i] = true;
            current.add(nums[i]);
            permuteVisitedHelper(nums, visited, current, result);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

    // =========================================================================
    // 4️⃣  PERMUTATIONS II (with duplicates)
    // =========================================================================

    public static List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> result = new ArrayList<>();
        boolean[] visited = new boolean[nums.length];
        permuteUniqueHelper(nums, visited, new ArrayList<>(), result);
        return result;
    }

    private static void permuteUniqueHelper(int[] nums, boolean[] visited,
                                             List<Integer> current, List<List<Integer>> result) {
        if(current.size() == nums.length) { result.add(new ArrayList<>(current)); return; }
        for(int i = 0; i < nums.length; i++) {
            if(visited[i]) continue;
            if(i > 0 && nums[i] == nums[i-1] && !visited[i-1]) continue;
            visited[i] = true;
            current.add(nums[i]);
            permuteUniqueHelper(nums, visited, current, result);
            current.remove(current.size() - 1);
            visited[i] = false;
        }
    }

    // =========================================================================
    // 5️⃣  COMBINATION SUM I (unlimited use of each number)
    // =========================================================================

    public static List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        combHelper(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void combHelper(int[] cands, int remain, int start,
                                    List<Integer> current, List<List<Integer>> result) {
        if(remain == 0) { result.add(new ArrayList<>(current)); return; }
        for(int i = start; i < cands.length; i++) {
            if(cands[i] > remain) break;
            current.add(cands[i]);
            combHelper(cands, remain - cands[i], i, current, result);  // i not i+1 (reuse)
            current.remove(current.size() - 1);
        }
    }

    // =========================================================================
    // 6️⃣  COMBINATION SUM II (each element used once, duplicates in input)
    // =========================================================================

    public static List<List<Integer>> combinationSum2(int[] candidates, int target) {
        Arrays.sort(candidates);
        List<List<Integer>> result = new ArrayList<>();
        combHelper2(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    private static void combHelper2(int[] cands, int remain, int start,
                                     List<Integer> current, List<List<Integer>> result) {
        if(remain == 0) { result.add(new ArrayList<>(current)); return; }
        for(int i = start; i < cands.length; i++) {
            if(cands[i] > remain) break;
            if(i > start && cands[i] == cands[i-1]) continue;  // skip dup
            current.add(cands[i]);
            combHelper2(cands, remain - cands[i], i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    // =========================================================================
    // 7️⃣  COMBINATION SUM III (k numbers 1-9 summing to n)
    // =========================================================================

    public static List<List<Integer>> combinationSum3(int k, int n) {
        List<List<Integer>> result = new ArrayList<>();
        combHelper3(k, n, 1, new ArrayList<>(), result);
        return result;
    }

    private static void combHelper3(int k, int remain, int start,
                                     List<Integer> current, List<List<Integer>> result) {
        if(k == 0 && remain == 0) { result.add(new ArrayList<>(current)); return; }
        if(k == 0 || remain <= 0) return;
        for(int i = start; i <= 9; i++) {
            current.add(i);
            combHelper3(k - 1, remain - i, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    private static void swap(int[] a, int i, int j) { int t=a[i]; a[i]=a[j]; a[j]=t; }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("Subsets {1,2,3}:          " + subsets(new int[]{1,2,3}));
        System.out.println("Subsets iterative:        " + subsetsIterative(new int[]{1,2,3}));
        System.out.println("Subsets with dups {1,2,2}:" + subsetsWithDup(new int[]{1,2,2}));
        System.out.println("Permutations {1,2,3}:     " + permute(new int[]{1,2,3}));
        System.out.println("Permutations unique{1,1,2}:" + permuteUnique(new int[]{1,1,2}));
        System.out.println("CombSum I target=7:       " + combinationSum(new int[]{2,3,6,7}, 7));
        System.out.println("CombSum II target=8:      " + combinationSum2(new int[]{10,1,2,7,6,1,5}, 8));
        System.out.println("CombSum III k=3,n=7:      " + combinationSum3(3, 7));
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Operation            | Time         | Space
---------------------|--------------|------
Subsets              | O(2^n * n)   | O(n)
Permutations         | O(n! * n)    | O(n)
Combination Sum I    | O(k * 2^n)   | O(n) k=avg combos
Combination Sum II   | O(2^n * n)   | O(n)
===========================================================
*/
