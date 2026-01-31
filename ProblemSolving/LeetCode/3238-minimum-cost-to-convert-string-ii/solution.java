class Solution {
    public long minimumCost(String src, String tar, String[] org, String[] chg, int[] cst) {
        int n = src.length(), m = org.length;
        Trie trie = new Trie();
        int id = 0;

        for (String s : org) if (trie.getId(s) == -1) trie.insert(s, id++);
        for (String s : chg) if (trie.getId(s) == -1) trie.insert(s, id++);

        long[][] cost = new long[id][id];
        for (long[] row : cost) Arrays.fill(row, -1);

        for (int i = 0; i < m; i++) {
            int u = trie.getId(org[i]);
            int v = trie.getId(chg[i]);
            if (cost[u][v] == -1 || cost[u][v] > cst[i]) cost[u][v] = cst[i];
        }

        for (int k = 0; k < id; k++)
            for (int i = 0; i < id; i++)
                for (int j = 0; j < id; j++)
                    if (cost[i][k] != -1 && cost[k][j] != -1) {
                        long val = cost[i][k] + cost[k][j];
                        if (cost[i][j] == -1 || cost[i][j] > val)
                            cost[i][j] = val;
                    }

        long[] dp = new long[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;

        Trie.Node root = trie.root;
        for (int i = 1; i <= n; i++) {
            if (src.charAt(i - 1) == tar.charAt(i - 1)) dp[i] = dp[i - 1];
            Trie.Node sNode = root, tNode = root;

            for (int j = i; j > 0; j--) {
                int a = src.charAt(j - 1) - 'a';
                int b = tar.charAt(j - 1) - 'a';
                if (sNode.child[a] == null || tNode.child[b] == null) break;
                sNode = sNode.child[a];
                tNode = tNode.child[b];
                if (sNode.end && tNode.end && dp[j - 1] != -1) {
                    long c = cost[sNode.id][tNode.id];
                    if (c != -1 && (dp[i] == -1 || dp[i] > dp[j - 1] + c))
                        dp[i] = dp[j - 1] + c;
                }
            }
        }
        return dp[n];
    }

    static class Trie {
        static class Node {
            Node[] child = new Node[26];
            boolean end;
            int id = -1;
        }
        Node root = new Node();

        void insert(String s, int id) {
            Node cur = root;
            for (int i = s.length() - 1; i >= 0; i--) {
                int idx = s.charAt(i) - 'a';
                if (cur.child[idx] == null) cur.child[idx] = new Node();
                cur = cur.child[idx];
            }
            cur.end = true;
            cur.id = id;
        }

        int getId(String s) {
            Node cur = root;
            for (int i = s.length() - 1; i >= 0; i--) {
                int idx = s.charAt(i) - 'a';
                if (cur.child[idx] == null) return -1;
                cur = cur.child[idx];
            }
            return cur.id;
        }
    }
}
