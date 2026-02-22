class Solution {
    public boolean isRobotBounded(String instructions) {
        int x = 0;
        int y = 0;
        int dir = 0; // 0-N, 1-E, 2-S, 3-W

        int[][] moves = {
            {0,1}, // N
            {1,0}, // E
            {0,-1}, // S
            {-1,0} // W
        };

        for(char c : instructions.toCharArray()) {
            if(c == 'G'){
                x += moves[dir][0];
                y += moves[dir][1];
            }else if(c == 'L'){
                dir = (dir + 3) % 4;
            }else {
                dir = (dir + 1) % 4;
            }
        }
        return (x ==0 && y ==0) || dir !=0;
    }
}
