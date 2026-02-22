class Solution {
    public List<String> buildArray(int[] target, int n) {
        List<String> stack = new ArrayList<>();
        int index = 0;
        for(int i=1 ; i <= n && index < target.length ; i++ ) {
            stack.add("Push");

            if(target[index] == i) {
                index++;
            }else{
                stack.add("Pop");
            }

        }
        return stack;
        
    }
}
