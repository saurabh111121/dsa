class Solution {
    /*
      there should ne no cycle .
      like if [0,3] exist of ex2 its not possible.

    */
    
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = getPreqrequisitesMap(prerequisites);

        boolean[] taken = new boolean[numCourses];
        boolean[] visiting = new boolean[numCourses];
        List<Integer> ans = new ArrayList<>();

        for (int course = 0; course < numCourses; course++) {
            if (!taken[course]) {
                if (dfs(course, taken, visiting, map, ans)) {
                    return new int[]{};
                }
            }
        }

        return toIntArray(ans);
    }

    boolean dfs(int course, boolean[] taken, boolean[] visiting,
                Map<Integer, Set<Integer>> map, List<Integer> ans) {

        if (visiting[course]) return true;
        if (taken[course]) return false;

        visiting[course] = true;

        for (int prereq : map.getOrDefault(course, Collections.emptySet())) {
            if (dfs(prereq, taken, visiting, map, ans)) {
                return true;
            }
        }

        visiting[course] = false;
        taken[course] = true;
        ans.add(course);

        return false;
    }

    int[] toIntArray(List<Integer> list) {
        int[] array = new int[list.size()];
        for (int i = 0; i < array.length; i++) {
            array[i] = list.get(i);
        }
        return array;
    }

    Map<Integer, Set<Integer>> getPreqrequisitesMap(int[][] prerequisites) {
        Map<Integer, Set<Integer>> map = new HashMap<>();
        for (int[] p : prerequisites) {
            map.computeIfAbsent(p[0], k -> new HashSet<>()).add(p[1]);
        }
        return map;
    }

}
