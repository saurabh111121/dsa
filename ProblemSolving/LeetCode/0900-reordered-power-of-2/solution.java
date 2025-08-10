class Solution {
     public static String amrit(int number) {
      String numStr = Integer.toString(number);
        char[] charArray = numStr.toCharArray();
        Arrays.sort(charArray);
        String sortedStr = new String(charArray);
        return sortedStr;
    }
    public boolean reorderedPowerOf2(int n) {
       String s=amrit(n);
       for(int i=0;i<31;i++){
        int a=(int)Math.pow(2,i);
         String b=amrit(a);
         if(s.equals(b)) return true;
       }
       return false;
    }
}
