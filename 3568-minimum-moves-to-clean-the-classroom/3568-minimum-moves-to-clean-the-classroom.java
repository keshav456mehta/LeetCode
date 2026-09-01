import java.util.*;

class Solution {
    public int minMoves(String[] classroom, int energy) {
        int m = classroom.length;
        int n = classroom[0].length();
        
        int startRow = -1, startCol = -1;
        Map<String, Integer> litterMap = new HashMap<>();
        int litterCount = 0;

        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                char ch = classroom[r].charAt(c);
                if (ch == 'S') {
                    startRow = r;
                    startCol = c;
                } else if (ch == 'L') {
                    litterMap.put(r + "," + c, litterCount++);
                }
            }
        }

        // Target mask when all litter items are collected
        int targetMask = (1 << litterCount) - 1;
        if (targetMask == 0) {
            return 0; // No litter to collect
        }

        // maxEnergy[r][c][mask] stores the maximum remaining energy seen for state (r, c, mask)
        int[][][] maxEnergy = new int[m][n][1 << litterCount];
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                Arrays.fill(maxEnergy[i][j], -1);
            }
        }

        // Queue holds arrays of {row, col, mask, currentEnergy}
        Queue<int[]> queue = new LinkedList<>();
        queue.offer(new int[]{startRow, startCol, 0, energy});
        maxEnergy[startRow][startCol][0] = energy;

        int moves = 0;
        int[][] dirs = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        while (!queue.isEmpty()) {
            int size = queue.size();
            moves++;

            for (int k = 0; k < size; k++) {
                int[] curr = queue.poll();
                int r = curr[0];
                int c = curr[1];
                int mask = curr[2];
                int e = curr[3];

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    // Out of bounds or obstacle
                    if (nr < 0 || nr >= m || nc < 0 || nc >= n || classroom[nr].charAt(nc) == 'X') {
                        continue;
                    }

                    int nextEnergy = e - 1;
                    if (nextEnergy < 0) {
                        continue; // Cannot move without energy
                    }

                    char cell = classroom[nr].charAt(nc);
                    int nextMask = mask;

                    // Handle Reset area
                    if (cell == 'R') {
                        nextEnergy = energy;
                    } 
                    // Handle Litter item
                    else if (cell == 'L') {
                        int litterIdx = litterMap.get(nr + "," + nc);
                        nextMask |= (1 << litterIdx);
                    }

                    // Check if all litters are collected
                    if (nextMask == targetMask) {
                        return moves;
                    }

                    // Prune state if we've reached (nr, nc, nextMask) with >= energy before
                    if (nextEnergy > maxEnergy[nr][nc][nextMask]) {
                        maxEnergy[nr][nc][nextMask] = nextEnergy;
                        queue.offer(new int[]{nr, nc, nextMask, nextEnergy});
                    }
                }
            }
        }

        return -1; // Impossible to collect all litter
    }
}