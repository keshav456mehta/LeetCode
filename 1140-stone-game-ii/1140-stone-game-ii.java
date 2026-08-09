class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = piles[n - 1];
        
        // Calculate suffix sums to quickly evaluate remaining stones from index i
        for (int i = n - 2; i >= 0; i--) {
            suffixSum[i] = suffixSum[i + 1] + piles[i];
        }

        // Memoization table: dp[i][M] stores max stones player can get starting at index i with parameter M
        int[][] dp = new int[n][n + 1];

        return solve(0, 1, piles, suffixSum, dp);
    }

    private int solve(int i, int M, int[] piles, int[] suffixSum, int[][] dp) {
        if (i >= piles.length) return 0;
        
        // If remaining piles <= 2 * M, player can take all remaining stones
        if (i + 2 * M >= piles.length) return suffixSum[i];
        
        if (dp[i][M] != 0) return dp[i][M];

        int maxStones = 0;
        
        // Try taking X piles where 1 <= X <= 2 * M
        for (int X = 1; X <= 2 * M; X++) {
            int nextM = Math.max(M, X);
            // Current player gets (total remaining stones) minus (what next player gets)
            int stones = suffixSum[i] - solve(i + X, nextM, piles, suffixSum, dp);
            maxStones = Math.max(maxStones, stones);
        }

        dp[i][M] = maxStones;
        return maxStones;
    }
}