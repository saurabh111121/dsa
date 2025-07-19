
public class Solution {
    public List<String> removeSubfolders(String[] folder) {
        Arrays.sort(folder);
        List<String> res = new ArrayList<>();
        String prev = "";
        for (String f : folder) {
            if (prev.isEmpty() || !f.startsWith(prev + "/")) {
                res.add(f);
                prev = f;
            }
        }
        return res;
    }
}

