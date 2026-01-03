package Patterns.StringPatterns;

import java.util.*;

/**
 * String Algorithm Patterns
 * =========================
 * Topics Covered:
 *  1. KMP (Knuth-Morris-Pratt) Pattern Matching
 *  2. Rabin-Karp Rolling Hash
 *  3. Z-Algorithm
 *  4. Manacher's Algorithm (Longest Palindromic Substring)
 *  5. Trie-based Prefix Search
 *  6. Anagram / Sliding Window on Strings
 */
public class StringPatterns {

    // ─────────────────────────────────────────────────────────────
    // 1️⃣  KMP – O(n + m) exact pattern matching
    // ─────────────────────────────────────────────────────────────
    /** Build failure (partial match) table */
    public static int[] kmpFailure(String pat) {
        int m = pat.length();
        int[] fail = new int[m];
        for (int i = 1, j = 0; i < m; i++) {
            while (j > 0 && pat.charAt(i) != pat.charAt(j)) j = fail[j - 1];
            if (pat.charAt(i) == pat.charAt(j)) j++;
            fail[i] = j;
        }
        return fail;
    }

    /** Return all start indices (0-based) where pat occurs in text */
    public static List<Integer> kmpSearch(String text, String pat) {
        List<Integer> result = new ArrayList<>();
        int[] fail = kmpFailure(pat);
        int n = text.length(), m = pat.length();
        for (int i = 0, j = 0; i < n; i++) {
            while (j > 0 && text.charAt(i) != pat.charAt(j)) j = fail[j - 1];
            if (text.charAt(i) == pat.charAt(j)) j++;
            if (j == m) { result.add(i - m + 1); j = fail[j - 1]; }
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // 2️⃣  RABIN-KARP – Rolling Hash pattern matching
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> rabinKarp(String text, String pat) {
        List<Integer> result = new ArrayList<>();
        int n = text.length(), m = pat.length();
        if (m > n) return result;
        final long MOD = 1_000_000_007L, BASE = 31L;
        long patHash = 0, textHash = 0, power = 1;
        for (int i = 0; i < m - 1; i++) power = power * BASE % MOD;
        for (int i = 0; i < m; i++) {
            patHash  = (patHash  * BASE + (pat.charAt(i)  - 'a' + 1)) % MOD;
            textHash = (textHash * BASE + (text.charAt(i) - 'a' + 1)) % MOD;
        }
        for (int i = 0; i <= n - m; i++) {
            if (patHash == textHash && text.substring(i, i + m).equals(pat))
                result.add(i);
            if (i < n - m) {
                textHash = (textHash - (text.charAt(i) - 'a' + 1) * power % MOD + MOD) % MOD;
                textHash = (textHash * BASE + (text.charAt(i + m) - 'a' + 1)) % MOD;
            }
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // 3️⃣  Z-ALGORITHM – z[i] = length of longest substring starting at i matching a prefix
    // ─────────────────────────────────────────────────────────────
    public static int[] zFunction(String s) {
        int n = s.length();
        int[] z = new int[n];
        z[0] = n;
        for (int i = 1, l = 0, r = 0; i < n; i++) {
            if (i < r) z[i] = Math.min(r - i, z[i - l]);
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) z[i]++;
            if (i + z[i] > r) { l = i; r = i + z[i]; }
        }
        return z;
    }

    /** Find all occurrences of pat in text using Z-function */
    public static List<Integer> zSearch(String text, String pat) {
        String concat = pat + "$" + text;
        int[] z = zFunction(concat);
        List<Integer> result = new ArrayList<>();
        int m = pat.length();
        for (int i = m + 1; i < concat.length(); i++)
            if (z[i] == m) result.add(i - m - 1);
        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // 4️⃣  MANACHER'S ALGORITHM – O(n) longest palindromic substring
    // ─────────────────────────────────────────────────────────────
    public static String longestPalindrome(String s) {
        // Transform: "abc" → "#a#b#c#"
        StringBuilder sb = new StringBuilder("#");
        for (char c : s.toCharArray()) { sb.append(c); sb.append('#'); }
        String t = sb.toString();
        int n = t.length();
        int[] p = new int[n];
        int center = 0, right = 0;
        for (int i = 0; i < n; i++) {
            if (i < right) p[i] = Math.min(right - i, p[2 * center - i]);
            while (i - p[i] - 1 >= 0 && i + p[i] + 1 < n && t.charAt(i - p[i] - 1) == t.charAt(i + p[i] + 1))
                p[i]++;
            if (i + p[i] > right) { center = i; right = i + p[i]; }
        }
        int maxLen = 0, bestCenter = 0;
        for (int i = 0; i < n; i++) if (p[i] > maxLen) { maxLen = p[i]; bestCenter = i; }
        int start = (bestCenter - maxLen) / 2;
        return s.substring(start, start + maxLen);
    }

    /** Count all distinct palindromic substrings (Manacher variant) */
    public static int countPalindromicSubstrings(String s) {
        StringBuilder sb = new StringBuilder("#");
        for (char c : s.toCharArray()) { sb.append(c); sb.append('#'); }
        String t = sb.toString();
        int n = t.length(), count = 0;
        int[] p = new int[n];
        int center = 0, right = 0;
        for (int i = 0; i < n; i++) {
            if (i < right) p[i] = Math.min(right - i, p[2 * center - i]);
            while (i - p[i] - 1 >= 0 && i + p[i] + 1 < n && t.charAt(i - p[i] - 1) == t.charAt(i + p[i] + 1))
                p[i]++;
            if (i + p[i] > right) { center = i; right = i + p[i]; }
            count += (p[i] + 1) / 2;
        }
        return count;
    }

    // ─────────────────────────────────────────────────────────────
    // 5️⃣  TRIE – Prefix Search
    // ─────────────────────────────────────────────────────────────
    static class Trie {
        private final TrieNode root = new TrieNode();

        static class TrieNode {
            TrieNode[] children = new TrieNode[26];
            boolean isEnd = false;
        }

        public void insert(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) cur.children[idx] = new TrieNode();
                cur = cur.children[idx];
            }
            cur.isEnd = true;
        }

        public boolean search(String word) {
            TrieNode cur = root;
            for (char c : word.toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) return false;
                cur = cur.children[idx];
            }
            return cur.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) return false;
                cur = cur.children[idx];
            }
            return true;
        }

        /** Find all words with given prefix */
        public List<String> autocomplete(String prefix) {
            List<String> result = new ArrayList<>();
            TrieNode cur = root;
            for (char c : prefix.toCharArray()) {
                int idx = c - 'a';
                if (cur.children[idx] == null) return result;
                cur = cur.children[idx];
            }
            dfsCollect(cur, new StringBuilder(prefix), result);
            return result;
        }

        private void dfsCollect(TrieNode node, StringBuilder sb, List<String> result) {
            if (node.isEnd) result.add(sb.toString());
            for (int i = 0; i < 26; i++) {
                if (node.children[i] != null) {
                    sb.append((char)('a' + i));
                    dfsCollect(node.children[i], sb, result);
                    sb.deleteCharAt(sb.length() - 1);
                }
            }
        }
    }

    // ─────────────────────────────────────────────────────────────
    // 6️⃣  ANAGRAM SLIDING WINDOW  (Find All Anagrams – LeetCode 438)
    // ─────────────────────────────────────────────────────────────
    public static List<Integer> findAnagrams(String s, String p) {
        List<Integer> result = new ArrayList<>();
        if (s.length() < p.length()) return result;
        int[] pCount = new int[26], wCount = new int[26];
        int m = p.length();
        for (char c : p.toCharArray()) pCount[c - 'a']++;
        for (int i = 0; i < m; i++) wCount[s.charAt(i) - 'a']++;
        if (Arrays.equals(pCount, wCount)) result.add(0);
        for (int i = m; i < s.length(); i++) {
            wCount[s.charAt(i) - 'a']++;
            wCount[s.charAt(i - m) - 'a']--;
            if (Arrays.equals(pCount, wCount)) result.add(i - m + 1);
        }
        return result;
    }

    // ─────────────────────────────────────────────────────────────
    // MAIN DEMO
    // ─────────────────────────────────────────────────────────────
    public static void main(String[] args) {
        System.out.println("=== KMP ===");
        System.out.println(kmpSearch("aaabaaab", "aaab")); // [0, 4]

        System.out.println("\n=== Rabin-Karp ===");
        System.out.println(rabinKarp("aaabaaab", "aaab")); // [0, 4]

        System.out.println("\n=== Z-Algorithm ===");
        System.out.println(zSearch("aaabaaab", "aaab")); // [0, 4]

        System.out.println("\n=== Manacher's ===");
        System.out.println(longestPalindrome("babad")); // "bab" or "aba"
        System.out.println(longestPalindrome("cbbd"));  // "bb"
        System.out.println("Palindromic substrings count: " + countPalindromicSubstrings("aaa")); // 6

        System.out.println("\n=== Trie ===");
        Trie trie = new Trie();
        trie.insert("apple"); trie.insert("app"); trie.insert("application");
        System.out.println(trie.search("app"));        // true
        System.out.println(trie.startsWith("appl"));   // true
        System.out.println(trie.autocomplete("app"));  // [app, apple, application]

        System.out.println("\n=== Anagram Sliding Window ===");
        System.out.println(findAnagrams("cbaebabacd", "abc")); // [0, 6]
    }
}

/*
 * ┌──────────────────────────────────┬─────────────┬──────────┐
 * │ Algorithm                        │ Time        │ Space    │
 * ├──────────────────────────────────┼─────────────┼──────────┤
 * │ KMP                              │ O(n + m)    │ O(m)     │
 * │ Rabin-Karp                       │ O(n + m)    │ O(1)     │
 * │ Z-Algorithm                      │ O(n + m)    │ O(n + m) │
 * │ Manacher's                       │ O(n)        │ O(n)     │
 * │ Trie insert/search               │ O(L)        │ O(N·L)   │
 * │ Anagram Sliding Window           │ O(n)        │ O(1)     │
 * └──────────────────────────────────┴─────────────┴──────────┘
 */
