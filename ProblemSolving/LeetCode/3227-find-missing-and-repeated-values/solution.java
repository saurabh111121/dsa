class Solution {
    public int[] findMissingAndRepeatedValues(int[][] grid) {
        int n = grid.length;
        int missing = -1;
        int repeat = -1;
        Map<Integer,Integer> freq = new HashMap<>();
        for(int[] row:grid){
            for(int num:row){
                freq.put(num, freq.getOrDefault(num,0) + 1);
            }
        }

        for(int num = 1; num<=n*n ; num++){
            if(!freq.containsKey(num)){
                missing = num;
            } else if(freq.get(num) == 2) {
                repeat = num;
            }
        }
        return new int[] {repeat, missing};
    }
}
