class Solution {
    public int[] assignElements(int[] groups, int[] elements) {
        TreeSet<Integer> uniqueElements = new TreeSet<>();
        Map<Integer, Integer> elementIndex = new HashMap<>();

        for (int i = 0; i < elements.length; i++) {
            uniqueElements.add(elements[i]);
            elementIndex.putIfAbsent(elements[i], i);
        }

        int[] assigned = new int[groups.length];

        for (int i = 0; i < groups.length; i++) {
            assigned[i] = -1;
            for (int j = 1; j * j <= groups[i]; j++) {
                if (groups[i] % j == 0) {
                    Integer ceilElement = uniqueElements.ceiling(j);
                    if (ceilElement != null && groups[i] % ceilElement == 0) {
                        assigned[i] = assigned[i] == -1 ? elementIndex.get(ceilElement) : Math.min(assigned[i], elementIndex.get(ceilElement));
                    }

                    int div = groups[i] / j;
                    if (div != j) {
                        ceilElement = uniqueElements.ceiling(div);
                        if (ceilElement != null && groups[i] % ceilElement == 0) {
                            assigned[i] = assigned[i] == -1 ? elementIndex.get(ceilElement) : Math.min(assigned[i], elementIndex.get(ceilElement));
                        }
                    }
                }
            }
        }
        return assigned;
    }
}
