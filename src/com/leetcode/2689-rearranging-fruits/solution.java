class Solution {
    public long minCost(int[] basket1, int[] basket2) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int min = Integer.MAX_VALUE;
        for(int i=0; i<basket1.length; i++){
            map.put(basket1[i],map.getOrDefault(basket1[i],0)+1);
            min = Math.min(min,basket1[i]);
        }

        for(int j=0; j<basket2.length; j++){
            map.put(basket2[j],map.getOrDefault(basket2[j],0)-1);
            min = Math.min(min,basket2[j]);
        }

        List<Integer> list = new ArrayList<>();
        for(int key : map.keySet()){
            int count = map.get(key);
            if(count%2!=0) return -1;
            for(int i=0; i<Math.abs(count/2); i++){
                list.add(key);
            }
        }
        Collections.sort(list);
        long res = 0;
        for(int i=0; i<list.size()/2; i++){
            res+= Math.min(2*min , list.get(i));
        }
        return res;
    }
}
