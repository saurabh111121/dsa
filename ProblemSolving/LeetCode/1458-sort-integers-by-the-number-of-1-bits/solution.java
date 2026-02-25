class Solution {
    public int[] sortByBits(int[] arr) {
        int n = arr.length;
        Integer[] nums = new Integer[n];
        for(int i = 0; i < n; i++) {
            nums[i] = arr[i];
        }

        Arrays.sort(nums, new Comparator<Integer>() {
            public int compare(Integer a, Integer b) {
                int bitA = Integer.bitCount(a);
                int bitB = Integer.bitCount(b);
                if (bitA != bitB) {
                    return bitA - bitB;
                } else {
                    return a - b;
                }
            }
        });

        for(int i = 0; i < n; i++) {
            arr[i] = nums[i];
        }

        return arr;
    }
}
