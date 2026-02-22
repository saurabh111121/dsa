public class Solution {
    public int minJumps(int[] nums) {
        int n = nums.length;
        if (n <= 1) return 0;

        Map<Integer, List<Integer>> valueToIndices = new HashMap<>();
        int maxVal = 0;
        for (int i = 0; i < n; i++) {
            valueToIndices.computeIfAbsent(nums[i], k -> new ArrayList<>()).add(i);
            if (nums[i] > maxVal) maxVal = nums[i];
        }

        Set<Integer> usedPrimes = new HashSet<>();
        boolean[] visited = new boolean[n];
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(0);
        visited[0] = true;
        int jumps = 0;

        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int k = 0; k < size; k++) {
                int curr = queue.poll();
                if (curr == n - 1) return jumps;
                int[] moves = {curr - 1, curr + 1};
                for (int next : moves) {
                    if (next >= 0 && next < n && !visited[next]) {
                        queue.offer(next);
                        visited[next] = true;
                    }
                }
                int val = nums[curr];
                if (isPrime(val) && usedPrimes.add(val)) {
                    for (int mult = val; mult <= maxVal; mult += val) {
                        List<Integer> teleportTargets = valueToIndices.get(mult);
                        if (teleportTargets != null) {
                            for (int idx : teleportTargets) {
                                if (!visited[idx]) {
                                    queue.offer(idx);
                                    visited[idx] = true;
                                }
                            }
                            teleportTargets.clear();
                        }
                    }
                }
            }
            jumps++;
        }
        return -1;
    }
    private boolean isPrime(int num) {
        if (num < 2) return false;
        if (num == 2) return true;
        if (num % 2 == 0) return false;
        for (int i = 3; i * i <= num; i += 2) {
            if (num % i == 0) return false;
        }
        return true;
    }
}

