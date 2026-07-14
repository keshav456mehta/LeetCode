class Solution {
    private static final int MOD = 1_000_000_007;

    public int subsequencePairCount(int[] nums) {
        // dp[g1][g2] stores the count of ways to form two disjoint 
        // subsequences with gcd g1 and g2 respectively.
        // Since nums[i] <= 200, the maximum GCD possible is 200.
        int[][] dp = new int[201][201];
        
        // Base case: 1 way to have both subsequences empty
        dp[0][0] = 1;

        for (int num : nums) {
            int[][] nextDp = new int[201][201];
            
            for (int g1 = 0; g1 <= 200; g1++) {
                for (int g2 = 0; g2 <= 200; g2++) {
                    if (dp[g1][g2] == 0) continue;
                    
                    long currentWays = dp[g1][g2];

                    // Choice 1: Skip the current number
                    nextDp[g1][g2] = (int) ((nextDp[g1][g2] + currentWays) % MOD);

                    // Choice 2: Add to seq1
                    int n1 = gcd(g1, num);
                    nextDp[n1][g2] = (int) ((nextDp[n1][g2] + currentWays) % MOD);

                    // Choice 3: Add to seq2
                    int n2 = gcd(g2, num);
                    nextDp[g1][n2] = (int) ((nextDp[g1][n2] + currentWays) % MOD);
                }
            }
            dp = nextDp;
        }

        // Accumulate all states where g1 == g2 and both are non-empty (g > 0)
        long totalPairs = 0;
        for (int g = 1; g <= 200; g++) {
            totalPairs = (totalPairs + dp[g][g]) % MOD;
        }

        return (int) totalPairs;
    }

    private int gcd(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}