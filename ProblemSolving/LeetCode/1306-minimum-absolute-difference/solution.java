class Solution {
    public List<List<Integer>> minimumAbsDifference(int[] arr) {
        int n = arr.length;
        Arrays.sort(arr);
        List<List<Integer>> list = new ArrayList<>();
        int min_diff = Integer.MAX_VALUE;
        for(int i=1;i<n;i++) {
            min_diff = Math.min(min_diff, arr[i] - arr[i-1]);
        }
        for(int i=1;i<n;i++){
            if(arr[i] - arr[i-1] == min_diff ){
                list.add(Arrays.asList(arr[i-1], arr[i]));
            }
        }
        return list;
    }
}
/*
1 4 6 8 9
<8,9>
*/
