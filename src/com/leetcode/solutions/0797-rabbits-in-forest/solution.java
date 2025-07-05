class Solution {
    public int numRabbits(int[] answers) {
        Map<Integer, Integer> map = new HashMap<>();
        int result = 0;
        for(int ans: answers) {
            map.put(ans, map.getOrDefault(ans, 0) + 1);

        }

        for(int ans: map.keySet()) {
            int gSize = ans+1;
            int rabit = map.get(ans);
            int groups = (rabit + gSize -1)/ gSize;
            result += groups * gSize;
        }
        return result;
    }
}
