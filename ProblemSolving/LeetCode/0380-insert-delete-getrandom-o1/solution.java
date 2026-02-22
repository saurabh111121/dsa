class RandomizedSet {
    private List<Integer> arr;
    private HashMap<Integer,Integer> map;
    private Random random;

    public RandomizedSet() {
        arr = new ArrayList<>();
        map = new HashMap<>();
        random = new Random();
        
    }
    
    public boolean insert(int val) {
        if(map.containsKey(val)){
            return false;
        }
        map.put(val, arr.size() );
        arr.add(val);
        return true;
    }
    
    public boolean remove(int val) {
        if(!map.containsKey(val)){
            return false;
        }
        int key = map.get(val);
        int temp = arr.get(arr.size()-1);

        arr.set(key, temp);
        map.put(temp, key);

        arr.remove(arr.size()-1);
        map.remove(val);
        return true;
    }
    
    public int getRandom() {
        int randomIndex = random.nextInt(arr.size());
        return arr.get(randomIndex);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
