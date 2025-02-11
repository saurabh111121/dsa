class Solution {
    public String removeOccurrences(String s, String part) {
        StringBuilder sb = new StringBuilder(s);

        while (true) {
            int index = sb.indexOf(part);
            if (index == -1) break;
            sb.delete(index, index + part.length());
        }

        return sb.toString();
    
        
    }
}
