import java.util.List;

class Solution {
    public int[] pathsWithMaxScore(List<String> board) {
        int n = board.size();
        int MOD = 1_000_000_007;

        int[][] maxScore = new int[n][n];
        int[][] paths = new int[n][n];

        // Base case: Start at 'E' (0, 0)
        paths[0][0] = 1; 

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                // Skip the start cell or obstacles
                if ((i == 0 && j == 0) || board.get(i).charAt(j) == 'X') {
                    continue;
                }

                int maxPrevScore = -1;
                int pathCount = 0;

                // 3 Possible directions to arrive at (i, j): Top, Left, Diagonal Top-Left
                int[][] directions = {{i - 1, j}, {i, j - 1}, {i - 1, j - 1}};

                for (int[] dir : directions) {
                    int r = dir[0], c = dir[1];
                    
                    // Check boundaries and if the cell is reachable
                    if (r >= 0 && c >= 0 && paths[r][c] > 0) {
                        if (maxScore[r][c] > maxPrevScore) {
                            maxPrevScore = maxScore[r][c];
                            pathCount = paths[r][c];
                        } else if (maxScore[r][c] == maxPrevScore) {
                            pathCount = (pathCount + paths[r][c]) % MOD;
                        }
                    }
                }

                // If this cell is reachable from at least one valid path
                if (maxPrevScore != -1) {
                    char currChar = board.get(i).charAt(j);
                    int currVal = (currChar == 'S') ? 0 : (currChar - '0');
                    
                    maxScore[i][j] = maxPrevScore + currVal;
                    paths[i][j] = pathCount;
                }
            }
        }

        // Return results at 'S' which is at (n-1, n-1)
        return new int[]{maxScore[n - 1][n - 1], paths[n - 1][n - 1]};
    }
}