import java.util.*;

class Solution {
    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Step 1: Pair values with their original indices and sort them
        int[][] sorted = new int[n][2];
        for (int i = 0; i < n; i++) {
            sorted[i][0] = nums[i];
            sorted[i][1] = i;
        }
        Arrays.sort(sorted, (a, b) -> Integer.compare(a[0], b[0]));

        // Map original index to sorted position for quick lookups
        int[] pos = new int[n];
        for (int i = 0; i < n; i++) {
            pos[sorted[i][1]] = i;
        }

        // Step 2: Build the greedy "parent" array using two pointers
        int LOG = 18; // 2^17 > 10^5
        int[][] up = new int[n][LOG];
        for (int[] row : up) Arrays.fill(row, -1);

        int right = 0;
        for (int left = 0; left < n; left++) {
            while (right < n && sorted[right][0] - sorted[left][0] <= maxDiff) {
                right++;
            }
            // The furthest greedy jump to the right
            int bestJump = right - 1;
            if (bestJump > left) {
                up[left][0] = bestJump;
            }
        }

        // Step 3: Compute binary lifting sparse table
        for (int j = 1; j < LOG; j++) {
            for (int i = 0; i < n; i++) {
                if (up[i][j - 1] != -1) {
                    up[i][j] = up[up[i][j - 1]][j - 1];
                }
            }
        }

        // Step 4: Process queries
        int[] answer = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = pos[queries[q][0]];
            int v = pos[queries[q][1]];

            // Ensure u is always the smaller element value-wise
            if (u > v) {
                int temp = u;
                u = v;
                v = temp;
            }

            if (u == v) {
                answer[q] = 0;
                continue;
            }

            // Count steps using binary lifting
            int steps = 0;
            for (int j = LOG - 1; j >= 0; j--) {
                if (up[u][j] != -1 && up[u][j] < v) {
                    u = up[u][j];
                    steps += (1 << j);
                }
            }

            // One more step if the next greedy jump reaches or overshoots 'v'
            if (up[u][0] >= v) {
                answer[q] = steps + 1;
            } else {
                answer[q] = -1; // Not reachable
            }
        }

        return answer;
    }
}