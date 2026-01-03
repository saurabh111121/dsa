package Algorithms.Recursion;

/*
===========================================================
Recursion Basics & Patterns in Java
===========================================================
Topics Covered:
 1) Factorial (recursive + iterative)
 2) Fibonacci (naive, memoized, iterative)
 3) Power / Fast Exponentiation
 4) Sum of digits
 5) Reverse a string recursively
 6) Check palindrome recursively
 7) Tower of Hanoi
 8) Print all subsets (Power Set)
 9) Count ways to climb stairs
10) GCD (Euclidean algorithm)
11) Flatten nested list
12) Print all permutations of a string
===========================================================
*/

import java.util.*;

public class RecursionBasics {

    // =========================================================================
    // 1️⃣  FACTORIAL
    // =========================================================================

    public static long factorialRecursive(int n) {
        if (n <= 1) return 1;
        return n * factorialRecursive(n - 1);
    }

    public static long factorialIterative(int n) {
        long result = 1;
        for (int i = 2; i <= n; i++) result *= i;
        return result;
    }

    // =========================================================================
    // 2️⃣  FIBONACCI
    // =========================================================================

    /** Naive O(2^n) */
    public static long fibNaive(int n) {
        if (n <= 1) return n;
        return fibNaive(n - 1) + fibNaive(n - 2);
    }

    /** Memoized O(n) */
    public static long fibMemo(int n, long[] memo) {
        if (n <= 1) return n;
        if (memo[n] != 0) return memo[n];
        return memo[n] = fibMemo(n - 1, memo) + fibMemo(n - 2, memo);
    }

    /** Iterative O(n) O(1) space */
    public static long fibIterative(int n) {
        if (n <= 1) return n;
        long a = 0, b = 1;
        for (int i = 2; i <= n; i++) { long c = a + b; a = b; b = c; }
        return b;
    }

    // =========================================================================
    // 3️⃣  FAST EXPONENTIATION (Binary Exponentiation)
    // =========================================================================

    /** Recursive O(log n) */
    public static long power(long base, int exp) {
        if (exp == 0) return 1;
        if (exp % 2 == 0) { long half = power(base, exp / 2); return half * half; }
        return base * power(base, exp - 1);
    }

    /** Iterative O(log n) */
    public static long powerIterative(long base, int exp) {
        long result = 1;
        while (exp > 0) {
            if ((exp & 1) == 1) result *= base;
            base *= base;
            exp >>= 1;
        }
        return result;
    }

    // =========================================================================
    // 4️⃣  SUM OF DIGITS
    // =========================================================================

    public static int sumOfDigits(int n) {
        if (n < 10) return n;
        return n % 10 + sumOfDigits(n / 10);
    }

    // =========================================================================
    // 5️⃣  REVERSE A STRING RECURSIVELY
    // =========================================================================

    public static String reverseString(String s) {
        if (s.length() <= 1) return s;
        return reverseString(s.substring(1)) + s.charAt(0);
    }

    // =========================================================================
    // 6️⃣  CHECK PALINDROME RECURSIVELY
    // =========================================================================

    public static boolean isPalindrome(String s, int lo, int hi) {
        if (lo >= hi) return true;
        if (s.charAt(lo) != s.charAt(hi)) return false;
        return isPalindrome(s, lo + 1, hi - 1);
    }

    // =========================================================================
    // 7️⃣  TOWER OF HANOI
    // =========================================================================

    public static void hanoi(int n, char from, char to, char aux) {
        if (n == 0) return;
        hanoi(n - 1, from, aux, to);
        System.out.println("Move disk " + n + " from " + from + " to " + to);
        hanoi(n - 1, aux, to, from);
    }

    // =========================================================================
    // 8️⃣  PRINT ALL SUBSETS (Power Set)
    // =========================================================================

    public static List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        generateSubsets(nums, 0, new ArrayList<>(), result);
        return result;
    }

    private static void generateSubsets(int[] nums, int idx,
                                         List<Integer> current, List<List<Integer>> result) {
        result.add(new ArrayList<>(current));
        for (int i = idx; i < nums.length; i++) {
            current.add(nums[i]);
            generateSubsets(nums, i + 1, current, result);
            current.remove(current.size() - 1);
        }
    }

    // =========================================================================
    // 9️⃣  COUNT WAYS TO CLIMB STAIRS (1 or 2 steps)
    // =========================================================================

    public static int climbStairs(int n, int[] dp) {
        if (n <= 1) return 1;
        if (dp[n] != 0) return dp[n];
        return dp[n] = climbStairs(n - 1, dp) + climbStairs(n - 2, dp);
    }

    // =========================================================================
    // 1️⃣0️⃣  GCD – Euclidean Algorithm
    // =========================================================================

    public static int gcd(int a, int b) {
        return b == 0 ? a : gcd(b, a % b);
    }

    public static int gcdIterative(int a, int b) {
        while (b != 0) { int t = b; b = a % b; a = t; }
        return a;
    }

    // =========================================================================
    // 1️⃣1️⃣  FLATTEN NESTED LIST
    // =========================================================================

    public static List<Integer> flatten(Object[] nested) {
        List<Integer> result = new ArrayList<>();
        for (Object o : nested) {
            if (o instanceof Integer) result.add((Integer) o);
            else result.addAll(flatten((Object[]) o));
        }
        return result;
    }

    // =========================================================================
    // 1️⃣2️⃣  ALL PERMUTATIONS OF A STRING
    // =========================================================================

    public static List<String> permutations(String s) {
        List<String> result = new ArrayList<>();
        permuteHelper(s.toCharArray(), 0, result);
        return result;
    }

    private static void permuteHelper(char[] arr, int start, List<String> result) {
        if (start == arr.length) { result.add(new String(arr)); return; }
        for (int i = start; i < arr.length; i++) {
            swap(arr, start, i);
            permuteHelper(arr, start + 1, result);
            swap(arr, start, i);  // backtrack
        }
    }

    private static void swap(char[] arr, int i, int j) { char t=arr[i]; arr[i]=arr[j]; arr[j]=t; }

    // =========================================================================
    // MAIN
    // =========================================================================
    public static void main(String[] args) {
        System.out.println("Factorial(10):     " + factorialRecursive(10));
        System.out.println("Fib(10) naive:     " + fibNaive(10));
        System.out.println("Fib(40) memo:      " + fibMemo(40, new long[41]));
        System.out.println("Fib(40) iter:      " + fibIterative(40));
        System.out.println("2^10 recursive:    " + power(2, 10));
        System.out.println("2^10 iterative:    " + powerIterative(2, 10));
        System.out.println("Sum digits(12345): " + sumOfDigits(12345));
        System.out.println("Reverse 'hello':   " + reverseString("hello"));
        System.out.println("Palindrome 'racecar': " + isPalindrome("racecar", 0, 6));
        System.out.println("GCD(48,18):        " + gcd(48, 18));
        System.out.println("Climb stairs(5):   " + climbStairs(5, new int[6]));
        System.out.println("Subsets {1,2,3}:   " + subsets(new int[]{1,2,3}));
        System.out.println("Perms 'abc':       " + permutations("abc"));
        System.out.println("\n=== Hanoi (3 disks) ===");
        hanoi(3, 'A', 'C', 'B');
    }
}

/*
===========================================================
Complexity Summary
===========================================================
Operation              | Time        | Space
-----------------------|-------------|------
Factorial (recursive)  | O(n)        | O(n) stack
Fibonacci (naive)      | O(2^n)      | O(n) stack
Fibonacci (memo)       | O(n)        | O(n)
Fibonacci (iterative)  | O(n)        | O(1)
Fast Power             | O(log n)    | O(log n) / O(1)
GCD (Euclidean)        | O(log min)  | O(log min) / O(1)
Subsets (Power Set)    | O(2^n)      | O(n)
Permutations           | O(n * n!)   | O(n)
Tower of Hanoi         | O(2^n)      | O(n)
===========================================================
*/
