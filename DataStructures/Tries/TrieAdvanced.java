package DataStructures.Tries;

/**
 * TrieAdvanced.java
 * ==================
 * Topics Covered:
 *  1. Word Search II (2D board DFS + Trie)
 *  2. Word Break (DP + Trie)
 *  3. Replace Words (prefix replacement using Trie)
 *  4. Magic Dictionary (wildcard / one-char mismatch matching)
 *  5. Autocomplete System (top-3 suggestions by frequency)
 *
 * All solutions include iterative or DP variants where applicable.
 */

import java.util.*;

public class TrieAdvanced {

    // ─────────────────────────────────────────────────────────────────────────
    // Shared TrieNode used across all sections
    // ─────────────────────────────────────────────────────────────────────────
    static class TrieNode {
        TrieNode[] children = new TrieNode[26];
        String word = null;          // non-null marks end of a word
        int freq = 0;                // used by autocomplete
        String sentence = null;     // full sentence for autocomplete
    }

    // =========================================================================
    // 1️⃣  WORD SEARCH II  –  Find all words from a dictionary on a 2D board
    // =========================================================================
    static class WordSearchII {

        /** Build trie from dictionary, then DFS every cell on board. O(M*N*4^L) */
        public List<String> findWords(char[][] board, String[] words) {
            TrieNode root = new TrieNode();
            for (String w : words) insertWord(root, w);

            List<String> result = new ArrayList<>();
            int rows = board.length, cols = board[0].length;
            for (int r = 0; r < rows; r++)
                for (int c = 0; c < cols; c++)
                    dfs(board, r, c, root, result);
            return result;
        }

        private void insertWord(TrieNode root, String word) {
            TrieNode cur = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null) cur.children[idx] = new TrieNode();
                cur = cur.children[idx];
            }
            cur.word = word;
        }

        private void dfs(char[][] board, int r, int c, TrieNode node, List<String> res) {
            if (r < 0 || r >= board.length || c < 0 || c >= board[0].length) return;
            char ch = board[r][c];
            if (ch == '#' || node.children[ch - 'a'] == null) return;

            TrieNode next = node.children[ch - 'a'];
            if (next.word != null) {
                res.add(next.word);
                next.word = null;          // deduplicate
            }
            board[r][c] = '#';             // mark visited
            dfs(board, r + 1, c, next, res);
            dfs(board, r - 1, c, next, res);
            dfs(board, r, c + 1, next, res);
            dfs(board, r, c - 1, next, res);
            board[r][c] = ch;              // restore
        }
    }

    // =========================================================================
    // 2️⃣  WORD BREAK  –  Check if string can be segmented using dictionary
    // =========================================================================
    static class WordBreak {

        // ── 2a. DP + HashSet  O(n²) ──────────────────────────────────────────
        public boolean wordBreakDP(String s, List<String> wordDict) {
            Set<String> dict = new HashSet<>(wordDict);
            int n = s.length();
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 1; i <= n; i++)
                for (int j = 0; j < i; j++)
                    if (dp[j] && dict.contains(s.substring(j, i))) { dp[i] = true; break; }
            return dp[n];
        }

        // ── 2b. DP + Trie  O(n²) ─────────────────────────────────────────────
        public boolean wordBreakTrie(String s, List<String> wordDict) {
            TrieNode root = new TrieNode();
            for (String w : wordDict) {
                TrieNode cur = root;
                for (char ch : w.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null) cur.children[idx] = new TrieNode();
                    cur = cur.children[idx];
                }
                cur.word = w;
            }
            int n = s.length();
            boolean[] dp = new boolean[n + 1];
            dp[0] = true;
            for (int i = 0; i < n; i++) {
                if (!dp[i]) continue;
                TrieNode cur = root;
                for (int j = i; j < n; j++) {
                    int idx = s.charAt(j) - 'a';
                    if (cur.children[idx] == null) break;
                    cur = cur.children[idx];
                    if (cur.word != null) dp[j + 1] = true;
                }
            }
            return dp[n];
        }

        // ── 2c. Return all valid sentences (backtracking)  ────────────────────
        public List<String> wordBreakAll(String s, List<String> wordDict) {
            Set<String> dict = new HashSet<>(wordDict);
            List<String> result = new ArrayList<>();
            backtrack(s, 0, dict, new StringBuilder(), result);
            return result;
        }

        private void backtrack(String s, int start, Set<String> dict,
                                StringBuilder path, List<String> result) {
            if (start == s.length()) { result.add(path.toString().trim()); return; }
            for (int end = start + 1; end <= s.length(); end++) {
                String word = s.substring(start, end);
                if (!dict.contains(word)) continue;
                int len = path.length();
                path.append(word).append(' ');
                backtrack(s, end, dict, path, result);
                path.setLength(len);
            }
        }
    }

    // =========================================================================
    // 3️⃣  REPLACE WORDS  –  Replace each word with its shortest root prefix
    // =========================================================================
    static class ReplaceWords {

        // ── 3a. Trie-based approach  O(total chars) ───────────────────────────
        public String replaceWordsTrie(List<String> dictionary, String sentence) {
            TrieNode root = new TrieNode();
            for (String root2 : dictionary) {
                TrieNode cur = root;
                for (char ch : root2.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null) cur.children[idx] = new TrieNode();
                    cur = cur.children[idx];
                    if (cur.word != null) break;   // shorter prefix already exists
                }
                cur.word = root2;
            }
            StringBuilder sb = new StringBuilder();
            for (String token : sentence.split(" ")) {
                if (sb.length() > 0) sb.append(' ');
                sb.append(findShortestRoot(root, token));
            }
            return sb.toString();
        }

        private String findShortestRoot(TrieNode root, String word) {
            TrieNode cur = root;
            for (char ch : word.toCharArray()) {
                int idx = ch - 'a';
                if (cur.children[idx] == null) return word;
                cur = cur.children[idx];
                if (cur.word != null) return cur.word;
            }
            return word;
        }

        // ── 3b. HashSet approach  O(total chars) ─────────────────────────────
        public String replaceWordsHashSet(List<String> dictionary, String sentence) {
            Set<String> dict = new HashSet<>(dictionary);
            StringBuilder sb = new StringBuilder();
            for (String token : sentence.split(" ")) {
                if (sb.length() > 0) sb.append(' ');
                String found = token;
                for (int i = 1; i <= token.length(); i++) {
                    String prefix = token.substring(0, i);
                    if (dict.contains(prefix)) { found = prefix; break; }
                }
                sb.append(found);
            }
            return sb.toString();
        }
    }

    // =========================================================================
    // 4️⃣  MAGIC DICTIONARY  –  Search with exactly one character changed
    // =========================================================================
    static class MagicDictionary {

        private TrieNode root = new TrieNode();

        public void buildDict(String[] dictionary) {
            for (String w : dictionary) {
                TrieNode cur = root;
                for (char ch : w.toCharArray()) {
                    int idx = ch - 'a';
                    if (cur.children[idx] == null) cur.children[idx] = new TrieNode();
                    cur = cur.children[idx];
                }
                cur.word = w;
            }
        }

        /** Returns true if there exists a word in dict that differs by exactly 1 char. */
        public boolean search(String searchWord) {
            return dfs(root, searchWord, 0, false);
        }

        private boolean dfs(TrieNode node, String word, int idx, boolean changed) {
            if (idx == word.length()) return changed && node.word != null;
            int target = word.charAt(idx) - 'a';
            for (int i = 0; i < 26; i++) {
                if (node.children[i] == null) continue;
                boolean newChange = (i != target);
                if (newChange && changed) continue;   // already used one change
                if (dfs(node.children[i], word, idx + 1, changed || newChange)) return true;
            }
            return false;
        }

        // ── Iterative BFS variant ─────────────────────────────────────────────
        /** BFS state: (trieNode, charIndex, mismatchCount) */
        public boolean searchBFS(String searchWord) {
            // Each state: [trieNode, position, mismatches]
            Queue<Object[]> queue = new LinkedList<>();
            queue.add(new Object[]{root, 0, 0});
            while (!queue.isEmpty()) {
                Object[] state = queue.poll();
                TrieNode node = (TrieNode) state[0];
                int pos = (int) state[1];
                int mis = (int) state[2];
                if (mis > 1) continue;
                if (pos == searchWord.length()) {
                    if (mis == 1 && node.word != null) return true;
                    continue;
                }
                int target = searchWord.charAt(pos) - 'a';
                for (int i = 0; i < 26; i++) {
                    if (node.children[i] == null) continue;
                    int newMis = mis + (i != target ? 1 : 0);
                    if (newMis <= 1) queue.add(new Object[]{node.children[i], pos + 1, newMis});
                }
            }
            return false;
        }
    }

    // =========================================================================
    // 5️⃣  AUTOCOMPLETE SYSTEM  –  Top-3 suggestions by historical frequency
    // =========================================================================
    static class AutocompleteSystem {

        // TrieNode extended with frequency map
        static class ACNode {
            ACNode[] children = new ACNode[128]; // ASCII
            Map<String, Integer> counts = new HashMap<>();
        }

        private ACNode root = new ACNode();
        private StringBuilder current = new StringBuilder();

        public AutocompleteSystem(String[] sentences, int[] times) {
            for (int i = 0; i < sentences.length; i++)
                insert(sentences[i], times[i]);
        }

        private void insert(String sentence, int count) {
            ACNode cur = root;
            for (char ch : sentence.toCharArray()) {
                if (cur.children[ch] == null) cur.children[ch] = new ACNode();
                cur = cur.children[ch];
                cur.counts.merge(sentence, count, Integer::sum);
            }
        }

        /**
         * Feed character c; '#' means end of input (save + reset).
         * Returns top-3 matching sentences sorted by frequency (desc), then lexicographically.
         */
        public List<String> input(char c) {
            if (c == '#') {
                insert(current.toString(), 1);
                current.setLength(0);
                return new ArrayList<>();
            }
            current.append(c);
            String prefix = current.toString();
            // Navigate to prefix node
            ACNode cur = root;
            for (char ch : prefix.toCharArray()) {
                if (cur.children[ch] == null) return new ArrayList<>();
                cur = cur.children[ch];
            }
            // Sort candidates
            List<Map.Entry<String, Integer>> entries = new ArrayList<>(cur.counts.entrySet());
            entries.sort((a, b) -> {
                if (!b.getValue().equals(a.getValue())) return b.getValue() - a.getValue();
                return a.getKey().compareTo(b.getKey());
            });
            List<String> result = new ArrayList<>();
            for (int i = 0; i < Math.min(3, entries.size()); i++)
                result.add(entries.get(i).getKey());
            return result;
        }
    }

    // =========================================================================
    // MAIN – Demo all advanced Trie operations
    // =========================================================================
    public static void main(String[] args) {

        System.out.println("=== 1️⃣  Word Search II ===");
        WordSearchII ws = new WordSearchII();
        char[][] board = {
            {'o','a','a','n'},
            {'e','t','a','e'},
            {'i','h','k','r'},
            {'i','f','l','v'}
        };
        String[] words = {"oath","pea","eat","rain"};
        System.out.println("Found words: " + ws.findWords(board, words));
        // Expected: [eat, oath]

        System.out.println("\n=== 2️⃣  Word Break ===");
        WordBreak wb = new WordBreak();
        List<String> dict = Arrays.asList("leet","code","le","et");
        System.out.println("DP approach:   " + wb.wordBreakDP("leetcode", dict));
        System.out.println("Trie approach: " + wb.wordBreakTrie("leetcode", dict));
        System.out.println("All sentences: " + wb.wordBreakAll("catsanddog",
                Arrays.asList("cat","cats","and","sand","dog")));

        System.out.println("\n=== 3️⃣  Replace Words ===");
        ReplaceWords rw = new ReplaceWords();
        List<String> roots = Arrays.asList("cat","bat","rat");
        String sentence = "the cattle was rattled by the battery";
        System.out.println("Trie:    " + rw.replaceWordsTrie(roots, sentence));
        System.out.println("HashSet: " + rw.replaceWordsHashSet(roots, sentence));

        System.out.println("\n=== 4️⃣  Magic Dictionary ===");
        MagicDictionary md = new MagicDictionary();
        md.buildDict(new String[]{"hello","leetcode"});
        System.out.println("search('hello')  → " + md.search("hello"));    // false (exact)
        System.out.println("search('hhllo')  → " + md.search("hhllo"));    // true  (h→e)
        System.out.println("search('hell')   → " + md.search("hell"));     // false (length)
        System.out.println("search('leetcod')→ " + md.search("leetcod"));  // false
        System.out.println("BFS search('hhllo') → " + md.searchBFS("hhllo"));

        System.out.println("\n=== 5️⃣  Autocomplete System ===");
        AutocompleteSystem ac = new AutocompleteSystem(
            new String[]{"i love you","island","iroman","i love leetcode"},
            new int[]{5,3,2,2}
        );
        System.out.println("input('i') → " + ac.input('i'));
        System.out.println("input(' ') → " + ac.input(' '));
        System.out.println("input('a') → " + ac.input('a'));
        System.out.println("input('#') → " + ac.input('#'));
    }
}

/*
 * ┌──────────────────────────────────────────────────────────────────┐
 * │                    COMPLEXITY SUMMARY                            │
 * ├────────────────────────────┬─────────────────┬───────────────────┤
 * │ Operation                  │ Time            │ Space             │
 * ├────────────────────────────┼─────────────────┼───────────────────┤
 * │ Word Search II (build)     │ O(W·L)          │ O(W·L)            │
 * │ Word Search II (search)    │ O(M·N·4^L)      │ O(L) stack        │
 * │ Word Break DP              │ O(n²)           │ O(n + dict)       │
 * │ Word Break Trie            │ O(n²)           │ O(dict chars)     │
 * │ Word Break All sentences   │ O(2^n)          │ O(n) stack        │
 * │ Replace Words (Trie)       │ O(total chars)  │ O(dict chars)     │
 * │ Magic Dictionary build     │ O(W·L)          │ O(W·L)            │
 * │ Magic Dictionary search    │ O(26·L)         │ O(L) stack        │
 * │ Autocomplete insert        │ O(L · unique)   │ O(L · unique)     │
 * │ Autocomplete query         │ O(L + k log k)  │ O(k)              │
 * └────────────────────────────┴─────────────────┴───────────────────┘
 * W = number of words, L = average word length,
 * M×N = board size, n = string length, k = candidates
 */
