package DataStructures.Tries;

/*
===========================================================
Trie (Prefix Tree) - Basic Implementation
===========================================================

A Trie stores strings character by character.
Each node has up to 26 children (for lowercase English letters).

Operations:
1) insert(word)         - Insert word into trie
2) search(word)         - Return true if exact word exists
3) startsWith(prefix)   - Return true if any word starts with prefix
4) delete(word)         - Delete word from trie
   - Recursive
   - Iterative

Applications:
- Autocomplete
- Spell checker
- Prefix matching
- Word count by prefix

===========================================================
*/

public class TrieBasic {

    // =========================
    // Trie Node Definition
    // =========================
    static class TrieNode {
        TrieNode[] children;
        boolean isEndOfWord;
        int wordCount; // how many words pass through this node

        TrieNode() {
            children = new TrieNode[26];
            isEndOfWord = false;
            wordCount = 0;
        }
    }

    // =========================
    // Trie Class
    // =========================
    static class Trie {
        private TrieNode root;

        Trie() { root = new TrieNode(); }

        // =====================================================
        // 1️⃣ INSERT - O(m) where m = word length
        // =====================================================
        public void insert(String word) {
            TrieNode current = root;
            for(char c : word.toCharArray()) {
                int idx = c - 'a';
                if(current.children[idx] == null) {
                    current.children[idx] = new TrieNode();
                }
                current = current.children[idx];
                current.wordCount++;
            }
            current.isEndOfWord = true;
        }

        // =====================================================
        // 2️⃣ SEARCH - O(m)
        // =====================================================
        public boolean search(String word) {
            TrieNode node = getNode(word);
            return node != null && node.isEndOfWord;
        }

        // =====================================================
        // 3️⃣ STARTS WITH (prefix check) - O(m)
        // =====================================================
        public boolean startsWith(String prefix) {
            return getNode(prefix) != null;
        }

        // =====================================================
        // HELPER: traverse to end of word/prefix
        // =====================================================
        private TrieNode getNode(String key) {
            TrieNode current = root;
            for(char c : key.toCharArray()) {
                int idx = c - 'a';
                if(current.children[idx] == null) return null;
                current = current.children[idx];
            }
            return current;
        }

        // =====================================================
        // 4️⃣ DELETE - RECURSIVE - O(m)
        // =====================================================
        public void delete(String word) {
            deleteHelper(root, word, 0);
        }

        private boolean deleteHelper(TrieNode node, String word, int depth) {
            if(node == null) return false;

            if(depth == word.length()) {
                if(!node.isEndOfWord) return false;
                node.isEndOfWord = false;
                return isEmpty(node);
            }

            int idx = word.charAt(depth) - 'a';
            boolean shouldDeleteChild = deleteHelper(node.children[idx], word, depth + 1);

            if(shouldDeleteChild) {
                node.children[idx] = null;
                return !node.isEndOfWord && isEmpty(node);
            }
            return false;
        }

        private boolean isEmpty(TrieNode node) {
            for(TrieNode child : node.children) {
                if(child != null) return false;
            }
            return true;
        }

        // =====================================================
        // 5️⃣ DELETE - ITERATIVE - O(m)
        // =====================================================
        public void deleteIterative(String word) {
            if(!search(word)) return;

            TrieNode current = root;
            // Find the node and clear isEndOfWord
            for(char c : word.toCharArray()) {
                current = current.children[c - 'a'];
                current.wordCount--;
            }
            current.isEndOfWord = false;
        }

        // =====================================================
        // 6️⃣ COUNT WORDS WITH PREFIX - O(m)
        // =====================================================
        public int countWordsWithPrefix(String prefix) {
            TrieNode node = getNode(prefix);
            return node == null ? 0 : node.wordCount;
        }

        // =====================================================
        // 7️⃣ PRINT ALL WORDS (DFS)
        // =====================================================
        public void printAllWords() {
            System.out.print("All words: ");
            dfsWords(root, new StringBuilder());
            System.out.println();
        }

        private void dfsWords(TrieNode node, StringBuilder current) {
            if(node.isEndOfWord) System.out.print("[" + current + "] ");
            for(int i = 0; i < 26; i++) {
                if(node.children[i] != null) {
                    current.append((char)('a' + i));
                    dfsWords(node.children[i], current);
                    current.deleteCharAt(current.length() - 1);
                }
            }
        }

        // =====================================================
        // 8️⃣ LONGEST COMMON PREFIX
        // =====================================================
        public String longestCommonPrefix() {
            StringBuilder lcp = new StringBuilder();
            TrieNode current = root;

            while(true) {
                int childCount = 0;
                int nextIdx = -1;
                for(int i = 0; i < 26; i++) {
                    if(current.children[i] != null) {
                        childCount++;
                        nextIdx = i;
                    }
                }
                if(childCount != 1 || current.isEndOfWord) break;
                lcp.append((char)('a' + nextIdx));
                current = current.children[nextIdx];
            }
            return lcp.toString();
        }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        Trie trie = new Trie();

        // Insert
        String[] words = {"apple", "app", "application", "apply", "banana", "band", "bandana"};
        for(String w : words) trie.insert(w);

        System.out.println("=== Trie Basic Operations ===");
        trie.printAllWords();

        // Search
        System.out.println("search('apple'): " + trie.search("apple"));
        System.out.println("search('app'):   " + trie.search("app"));
        System.out.println("search('ap'):    " + trie.search("ap"));

        // StartsWith
        System.out.println("startsWith('app'): " + trie.startsWith("app"));
        System.out.println("startsWith('xyz'): " + trie.startsWith("xyz"));

        // Count words with prefix
        System.out.println("Words with 'app': " + trie.countWordsWithPrefix("app"));
        System.out.println("Words with 'ban': " + trie.countWordsWithPrefix("ban"));

        // Delete recursive
        trie.delete("app");
        System.out.println("\nAfter delete('app'):");
        System.out.println("search('app'):   " + trie.search("app"));
        System.out.println("search('apple'): " + trie.search("apple"));
        trie.printAllWords();

        // Delete iterative
        trie.deleteIterative("apple");
        System.out.println("\nAfter deleteIterative('apple'):");
        System.out.println("search('apple'): " + trie.search("apple"));
        trie.printAllWords();

        // Longest common prefix
        Trie trie2 = new Trie();
        String[] lcpWords = {"flower", "flow", "flight"};
        for(String w : lcpWords) trie2.insert(w);
        System.out.println("\nLongest common prefix of flower/flow/flight: '" + trie2.longestCommonPrefix() + "'");
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

insert:             Time O(m),  Space O(m) for new nodes
search:             Time O(m),  Space O(1)
startsWith:         Time O(m),  Space O(1)
delete (recursive): Time O(m),  Space O(m) call stack
delete (iterative): Time O(m),  Space O(1)
countWithPrefix:    Time O(m),  Space O(1)
printAllWords:      Time O(n*m),Space O(m) stack
longestCommonPrefix:Time O(n*m),Space O(m)

m = word/prefix length
n = number of words
Space for Trie: O(ALPHABET * n * m) worst case

===========================================================
*/
