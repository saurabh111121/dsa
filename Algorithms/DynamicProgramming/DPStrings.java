package Algorithms.DynamicProgramming;

/**
 * DP on Strings
 * =============
 * Topics Covered:
 *  1. Edit Distance (Levenshtein)
 *  2. Wildcard Pattern Matching
 *  3. Regular Expression Matching
 *  4. Interleaving Strings
 *  5. Distinct Subsequences
 *  6. Scramble String
 *
 * Style: Memoization → Tabulation → Space-Optimized where applicable
 */
public class DPStrings {

    // ─────────────────────────────────────────────────────────────
    // 1️⃣  EDIT DISTANCE  (LeetCode 72)
    // ─────────────────────────────────────────────────────────────
    // Min operations (insert, delete, replace) to convert word1 → word2

    /** Memoization */
    public static int editDistanceMemo(String a, String b) {
        int[][] dp = new int[a.length()][b.length()];
        for(int[] row : dp) java.util.Arrays.fill(row, -1);
        return edHelper(a, b, a.length() - 1, b.length() - 1, dp);
    }

    private static int edHelper(String a, String b, int i, int j, int[][] dp) {
        if(i < 0) return j + 1;   // insert all remaining of b
        if(j < 0) return i + 1;   // delete all remaining of a
        if(dp[i][j] != -1) return dp[i][j];
        if(a.charAt(i) == b.charAt(j))
            return dp[i][j] = edHelper(a, b, i - 1, j - 1, dp);
        return dp[i][j] = 1 + Math.min(
                edHelper(a, b, i - 1, j - 1, dp),  // replace
                Math.min(
                    edHelper(a, b, i - 1, j, dp),  // delete from a
                    edHelper(a, b, i, j - 1, dp)   // insert into a
                ));
    }

    /** Tabulation */
    public static int editDistanceTab(String a, String b) {
        int m = a.length(), n = b.length();
        int[][] dp = new int[m + 1][n + 1];
        for(int i = 0; i <= m; i++) dp[i][0] = i;
        for(int j = 0; j <= n; j++) dp[0][j] = j;
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1))
                    dp[i][j] = dp[i - 1][j - 1];
                else
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1],
                                   Math.min(dp[i - 1][j], dp[i][j - 1]));
            }
        }
        return dp[m][n];
    }

    /** Space-Optimized O(n) */
    public static int editDistanceOpt(String a, String b) {
        int m = a.length(), n = b.length();
        int[] prev = new int[n + 1], curr = new int[n + 1];
        for(int j = 0; j <= n; j++) prev[j] = j;
        for(int i = 1; i <= m; i++) {
            curr[0] = i;
            for(int j = 1; j <= n; j++) {
                if(a.charAt(i - 1) == b.charAt(j - 1))
                    curr[j] = prev[j - 1];
                else
                    curr[j] = 1 + Math.min(prev[j - 1], Math.min(prev[j], curr[j - 1]));
            }
            int[] tmp = prev; prev = curr; curr = tmp;
        }
        return prev[n];
    }

    // ─────────────────────────────────────────────────────────────
    // 2️⃣  WILDCARD PATTERN MATCHING  (LeetCode 44)
    // ─────────────────────────────────────────────────────────────
    // '?' matches any single char; '*' matches any sequence (including empty)

    /** Memoization */
    public static boolean wildcardMemo(String s, String p) {
        int m = s.length(), n = p.length();
        Boolean[][] dp = new Boolean[m + 1][n + 1];
        return wcHelper(s, p, m, n, dp);
    }

    private static boolean wcHelper(String s, String p, int i, int j, Boolean[][] dp) {
        if(i == 0 && j == 0) return true;
        if(j == 0) return false;
        if(i == 0) {
            for(int k = 1; k <= j; k++) if(p.charAt(k - 1) != '*') return false;
            return true;
        }
        if(dp[i][j] != null) return dp[i][j];
        char pc = p.charAt(j - 1), sc = s.charAt(i - 1);
        boolean res;
        if(pc == '*')
            res = wcHelper(s, p, i - 1, j, dp) || wcHelper(s, p, i, j - 1, dp);
        else if(pc == '?' || pc == sc)
            res = wcHelper(s, p, i - 1, j - 1, dp);
        else
            res = false;
        return dp[i][j] = res;
    }

    /** Tabulation */
    public static boolean wildcardTab(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int j = 1; j <= n; j++) dp[0][j] = dp[0][j - 1] && p.charAt(j - 1) == '*';
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1);
                if(pc == '*')
                    dp[i][j] = dp[i - 1][j] || dp[i][j - 1];
                else if(pc == '?' || pc == s.charAt(i - 1))
                    dp[i][j] = dp[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }

    // ─────────────────────────────────────────────────────────────
    // 3️⃣  REGULAR EXPRESSION MATCHING  (LeetCode 10)
    // ─────────────────────────────────────────────────────────────
    // '.' matches any single char; 'a*' matches zero or more 'a'

    /** Tabulation */
    public static boolean regexMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        // patterns like a*, a*b*, a*b*c* can match empty string
        for(int j = 2; j <= n; j += 2)
            if(p.charAt(j - 1) == '*') dp[0][j] = dp[0][j - 2];
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                char pc = p.charAt(j - 1), sc = s.charAt(i - 1);
                if(pc == '*') {
                    char prev = p.charAt(j - 2);
                    // zero occurrences of prev
                    dp[i][j] = dp[i][j - 2];
                    // one or more occurrences
                    if(prev == '.' || prev == sc)
                        dp[i][j] = dp[i][j] || dp[i - 1][j];
                } else if(pc == '.' || pc == sc) {
                    dp[i][j] = dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    // ─────────────────────────────────────────────────────────────
    // 4️⃣  INTERLEAVING STRINGS  (LeetCode 97)
    // ─────────────────────────────────────────────────────────────
    // Is s3 formed by interleaving s1 and s2?

    /** Tabulation */
    public static boolean isInterleave(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if(m + n != s3.length()) return false;
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for(int i = 1; i <= m; i++) dp[i][0] = dp[i - 1][0] && s1.charAt(i - 1) == s3.charAt(i - 1);
        for(int j = 1; j <= n; j++) dp[0][j] = dp[0][j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                char c = s3.charAt(i + j - 1);
                dp[i][j] = (dp[i - 1][j] && s1.charAt(i - 1) == c)
                         || (dp[i][j - 1] && s2.charAt(j - 1) == c);
            }
        }
        return dp[m][n];
    }

    /** Space-Optimized O(n) */
    public static boolean isInterleaveOpt(String s1, String s2, String s3) {
        int m = s1.length(), n = s2.length();
        if(m + n != s3.length()) return false;
        boolean[] dp = new boolean[n + 1];
        dp[0] = true;
        for(int j = 1; j <= n; j++) dp[j] = dp[j - 1] && s2.charAt(j - 1) == s3.charAt(j - 1);
        for(int i = 1; i <= m; i++) {
            dp[0] = dp[0] && s1.charAt(i - 1) == s3.charAt(i - 1);
            for(int j = 1; j <= n; j++) {
                char c = s3.charAt(i + j - 1);
                dp[j] = (dp[j] && s1.charAt(i - 1) == c)
                      || (dp[j - 1] && s2.charAt(j - 1) == c);
            }
        }
        return dp[n];
    }

    // ─────────────────────────────────────────────────────────────
    // 5️⃣  DISTINCT SUBSEQUENCES  (LeetCode 115)
    // ─────────────────────────────────────────────────────────────
    // Count ways t appears as subsequence in s

    /** Tabulation */
    public static long distinctSubseq(String s, String t) {
        int m = s.length(), n = t.length();
        long[][] dp = new long[m + 1][n + 1];
        for(int i = 0; i <= m; i++) dp[i][0] = 1; // empty t matches any prefix
        for(int i = 1; i <= m; i++) {
            for(int j = 1; j <= n; j++) {
                dp[i][j] = dp[i - 1][j]; // skip s[i]
                if(s.charAt(i - 1) == t.charAt(j - 1))
                    dp[i][j] += dp[i - 1][j - 1];
            }
        }
        return dp[m][n];
    }

    /** Space-Optimized */
    public static long distinctSubseqOpt(String s, String t) {
        int n = t.length();
        long[] dp = new long[n + 1];
        dp[0] = 1;
        for(char sc : s.toCharArray()) {
            for(int j = n; j >= 1; j--) {
                if(sc == t.charAt(j - 1)) dp[j] += dp[j - 1];
            }
        }
        return dp[n];
    }

    // ─────────────────────────────────────────────────────────────
    // 6️⃣  SCRAMBLE STRING  (LeetCode 87)
    // ─────────────────────────────────────────────────────────────
    // s1 can be scrambled to s2 via recursive split and optional swap

    private static java.util.Map<String, Boolean> scrMemo = new java.util.HashMap<>();

    public static boolean isScramble(String s1, String s2) {
        if(s1.equals(s2)) return true;
        if(s1.length() != s2.length()) return false;
        // prune: same char frequency
        int[] freq = new int[26];
        for(char c : s1.toCharArray()) freq[c - 'a']++;
        for(char c : s2.toCharArray()) freq[c - 'a']--;
        for(int f : freq) if(f != 0) return false;

        String key = s1 + "#" + s2;
        if(scrMemo.containsKey(key)) return scrMemo.get(key);

        int n = s1.length();
        for(int len = 1; len < n; len++) {
            // no swap
            if(isScramble(s1.substring(0, len), s2.substring(0, len)) &&
                isScramble(s1.substring(len), s2.substring(len))) {
                scrMemo.put(key, true); return true;
            }
            // swap
            if(isScramble(s1.substring(0, len), s2.substring(n - len)) &&
                isScramble(s1.substring(len), s2.substring(0, n - len))) {
                scrMemo.put(key, true); return true;
            }
        }
        scrMemo.put(key, false);
        return false;
    }

    /** 3-D Tabulation dp[len][i][j] = is s1[i..i+len-1] scramble of s2[j..j+len-1] */
    public static boolean isScrambleTab(String s1, String s2) {
        int n = s1.length();
        if(n != s2.length()) return false;
        boolean[][][] dp = new boolean[n + 1][n][n];
        // base: length 1
        for(int i = 0; i < n; i++)
            for(int j = 0; j < n; j++)
                dp[1][i][j] = s1.charAt(i) == s2.charAt(j);
        for(int len = 2; len <= n; len++) {
            for(int i = 0; i <= n - len; i++) {
                for(int j = 0; j <= n - len; j++) {
                    for(int k = 1; k < len; k++) {
                        // no swap
                        if(dp[k][i][j] && dp[len - k][i + k][j + k]) { dp[len][i][j] = true; break; }
                        // swap
                        if(dp[k][i][j + len - k] && dp[len - k][i + k][j]) { dp[len][i][j] = true; break; }
                    }
                }
            }
        }
        return dp[n][0][0];
    }

    // ─────────────────────────────────────────────────────────────
    // MAIN DEMO
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        // Edit Distance
        System.out.println("=== Edit Distance ===");
        System.out.println(editDistanceMemo("horse", "ros"));  // 3
        System.out.println(editDistanceTab("horse", "ros"));   // 3
        System.out.println(editDistanceOpt("intention", "execution")); // 5

        // Wildcard
        System.out.println("\n=== Wildcard Matching ===");
        System.out.println(wildcardTab("adceb", "*a*b")); // true
        System.out.println(wildcardTab("acdcb", "a*c?b")); // false

        // Regex
        System.out.println("\n=== Regex Matching ===");
        System.out.println(regexMatch("aa", "a*"));   // true
        System.out.println(regexMatch("ab", ".*"));   // true
        System.out.println(regexMatch("aab", "c*a*b")); // true

        // Interleaving
        System.out.println("\n=== Interleaving Strings ===");
        System.out.println(isInterleave("aabcc", "dbbca", "aadbbcbcac")); // true
        System.out.println(isInterleaveOpt("aabcc", "dbbca", "aadbbbaccc")); // false

        // Distinct Subsequences
        System.out.println("\n=== Distinct Subsequences ===");
        System.out.println(distinctSubseq("rabbbit", "rabbit")); // 3
        System.out.println(distinctSubseqOpt("babgbag", "bag")); // 5

        // Scramble
        System.out.println("\n=== Scramble String ===");
        System.out.println(isScramble("great", "rgeat")); // true
        System.out.println(isScrambleTab("great", "rgeat")); // true
        System.out.println(isScramble("abcde", "caebd")); // false
    }
}

/*
 * ┌──────────────────────────────────┬────────────┬────────────┐
 * │ Algorithm                        │ Time       │ Space      │
 * ├──────────────────────────────────┼────────────┼────────────┤
 * │ Edit Distance (tab)              │ O(m·n)     │ O(m·n)     │
 * │ Edit Distance (opt)              │ O(m·n)     │ O(n)       │
 * │ Wildcard (tab)                   │ O(m·n)     │ O(m·n)     │
 * │ Regex Match (tab)                │ O(m·n)     │ O(m·n)     │
 * │ Interleaving (tab)               │ O(m·n)     │ O(m·n)     │
 * │ Interleaving (opt)               │ O(m·n)     │ O(n)       │
 * │ Distinct Subsequences (tab)      │ O(m·n)     │ O(m·n)     │
 * │ Distinct Subsequences (opt)      │ O(m·n)     │ O(n)       │
 * │ Scramble (memo)                  │ O(n^4)     │ O(n^3)     │
 * │ Scramble (3D tab)                │ O(n^4)     │ O(n^3)     │
 * └──────────────────────────────────┴────────────┴────────────┘
 */
