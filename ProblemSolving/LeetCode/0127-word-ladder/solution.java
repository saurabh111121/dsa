class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> set = new HashSet<>(wordList);
        if(!set.contains(endWord)) return 0;

        Map<String, List<String>> patternMap = buildPatternMap(set, beginWord.length());
        return bfsShortestPath(beginWord, endWord, patternMap);
    }

    Map<String, List<String>> buildPatternMap(Set<String> set, int L){
        Map<String, List<String>> patternMap = new HashMap<>();
        for(String word : set) {
            for(int i=0;i<L;i++) {
                String pattern = createPattern(word,i);
                patternMap.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
            }
        }
        return patternMap;
    }

    String createPattern(String word, int index){
        return word.substring(0, index) + "*" + word.substring(index+1);
    }


    int bfsShortestPath(String beginWord, String endWord, Map<String, List<String>> patternMap) {
        Queue<String> queue = new LinkedList<>();
        queue.offer(beginWord);

        Set<String> visited = new HashSet<>();
        visited.add(beginWord);
        int level =1;
        int L = beginWord.length();

        while(!queue.isEmpty()){
            int size = queue.size();
            for(int i =0;i<size;i++) {
                String word = queue.poll();
                for(int j=0;j<L;j++) {
                    String pattern = createPattern(word, j);
                    for(String neighbor : patternMap.getOrDefault(pattern, Collections.emptyList())){
                        if(neighbor.equals(endWord)) return level+1;

                        if(visited.add(neighbor)){
                            queue.offer(neighbor);
                        }
                    }
                }
            }
            level++;
        }
        return 0;
    }
}
/*
["hot","dot","dog","lot","log","cog"]
hit -> hot -> dot -> dog -> cog 
    hit
     |
    hot
   /   \
 dot   lot
  | \   / |
  |  dog  |
  |   |    |
  |   |    |
  |   log   |
   \   |    /
       cog


*/
