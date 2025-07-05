class Solution {

        public boolean isPrefixAndSuffix(String str1, String str2) {
        int len1 = str1.length();
        int len2 = str2.length();

        if (len1 > len2) {
            return false;
        }

        for (int i = 0; i < len1; i++) {
            if (str1.charAt(i) != str2.charAt(i)) {
                return false;
            }
        }

        for (int i = 0; i < len1; i++) {
            if (str1.charAt(len1 - i - 1) != str2.charAt(len2 - i - 1)) {
                return false;
            }
        }

        return true;
    }

    public int countPrefixSuffixPairs(String[] words) {
        int n = words.length;
        int count = 0;
        for(int i=0;i<n;i++) {
            for(int j=i+1;j<n;j++) {
                if(isPrefixAndSuffix(words[i], words[j])){
                    count++;
                }
            }
        }
        return count;

    }
}
