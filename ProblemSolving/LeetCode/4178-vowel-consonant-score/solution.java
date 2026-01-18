class Solution {
    public int vowelConsonantScore(String s) {
        int n = s.length();
        int c = 0;
        int v = 0;
        for(int i=0;i<n;i++) {
            int cr = s.charAt(i);
            if(cr =='a' || cr =='e' || cr =='i' || cr =='o' || cr =='u') {
                v++;
            }else if(cr >= 'a' && cr <= 'z' && (cr !='a' || cr !='e' || cr !='i' || cr !='o' || cr !='u')){
                c++;
            }
        }
        if(c ==0 || v == 0) return 0;
        return (int)Math.floor(v/c);
    }
}
