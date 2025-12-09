class Solution {
    public int specialTriplets(int[] nums) {
        int max = Integer.MIN_VALUE;
        for(int i : nums) max=Math.max(max,i);
        int[] rightFreq = new int[max+1];
        int[] leftFreq = new int[max+1];
        long count = 0;
        for(int i : nums){
            rightFreq[i]++;
        }

        for(int i : nums){
            rightFreq[i]--;
            int req = i*2;
            
            if(i*2 <= max) {
                long leftCount = leftFreq[i*2];
                long rightCount = rightFreq[i*2];

                count = ( count + (leftCount*rightCount) ) % 1000000007;
            }
            leftFreq[i]++;
        }
        return (int)count;
    }
}
