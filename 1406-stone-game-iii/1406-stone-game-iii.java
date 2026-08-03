class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[4]; // Stores dp[i+1], dp[i+2], dp[i+3]
        
        for (int i = n - 1; i >= 0; i--) {
            int maxDiff = Integer.MIN_VALUE;
            int currentSum = 0;
            
            for (int k = 0; k < 3 && i + k < n; k++) {
                currentSum += stoneValue[i + k];
                maxDiff = Math.max(maxDiff, currentSum - dp[(k + 1) % 4]);
            }
            
            dp[0] = maxDiff;
            // Shift values for the next iteration
            dp[3] = dp[2];
            dp[2] = dp[1];
            dp[1] = dp[0];
        }
        
        int result = dp[1];
        if (result > 0) {
            return "Alice";
        } else if (result < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}