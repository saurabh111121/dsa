class Solution {
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> mxHeap = new PriorityQueue<>(Collections.reverseOrder());
        for(int s : stones) {
            mxHeap.offer(s);
        }
        while(mxHeap.size() > 1) {
            int max1 = mxHeap.poll();
            int max2 = mxHeap.poll();
            if(max1 != max2){
                mxHeap.offer(max1 - max2);
            }
        }
        return mxHeap.isEmpty() ? 0 : mxHeap.peek();
    }
}
