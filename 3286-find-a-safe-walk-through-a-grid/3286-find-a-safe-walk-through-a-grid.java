import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

class Solution {
    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();
        
        int[][] maxHealth = new int[m][n];
        for (int i = 0; i < m; i++) {
            java.util.Arrays.fill(maxHealth[i], -1);
        }
        
        Deque<int[]> deque = new ArrayDeque<>();
        
        int startCost = grid.get(0).get(0);
        maxHealth[0][0] = health - startCost;
        
        if (maxHealth[0][0] <= 0) {
            return false;
        }
        
        deque.offerFirst(new int[]{0, 0});
        
        int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
        
        while (!deque.isEmpty()) {
            int[] curr = deque.pollFirst();
            int r = curr[0];
            int c = curr[1];
            int currH = maxHealth[r][c];
            
            if (r == m - 1 && c == n - 1) {
                return currH >= 1;
            }
            
            for (int[] dir : dirs) {
                int nr = r + dir[0];
                int nc = c + dir[1];
                if (nr >= 0 && nr < m && nc >= 0 && nc < n) {
                    int nextH = currH - grid.get(nr).get(nc);
                    
                    if (nextH > 0 && nextH > maxHealth[nr][nc]) {
                        maxHealth[nr][nc] = nextH;
                        
                        if (grid.get(nr).get(nc) == 0) {
                            deque.offerFirst(new int[]{nr, nc});
                        } else {
                            deque.offerLast(new int[]{nr, nc});
                        }
                    }
                }
            }
        }
        
        return false;
    }
}