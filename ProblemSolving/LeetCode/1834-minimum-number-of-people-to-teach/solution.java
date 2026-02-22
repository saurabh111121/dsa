class Solution {
    public int minimumTeachings(int n, int[][] languages, int[][] friendships) {
        int m = languages.length;
        List<Set<Integer>> langSets = new ArrayList<>();
        for (int[] lang : languages) {
            Set<Integer> set = new HashSet<>();
            for (int l : lang) set.add(l);
            langSets.add(set);
        }
        Set<Integer> problematicUsers = new HashSet<>();
        for (int[] f : friendships) {
            int u = f[0] - 1, v = f[1] - 1;
            Set<Integer> s1 = langSets.get(u), s2 = langSets.get(v);
            boolean canCommunicate = false;
            for (int l : s1) {
                if (s2.contains(l)) {
                    canCommunicate = true;
                    break;
                }
            }
            if (!canCommunicate) {
                problematicUsers.add(u);
                problematicUsers.add(v);
            }
        }
        int ans = Integer.MAX_VALUE;
        for (int l = 1; l <= n; l++) {
            int count = 0;
            for (int user : problematicUsers) {
                if (!langSets.get(user).contains(l)) {
                    count++;
                }
            }
        ans = Math.min(ans, count);
        }
        return ans == Integer.MAX_VALUE ? 0 : ans;
    }
}
