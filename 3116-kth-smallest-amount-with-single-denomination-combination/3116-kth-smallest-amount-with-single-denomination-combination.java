class Solution {
    public long findKthSmallest(int[] coins, int k) {
        int n = coins.length;
        int numSubsets = 1 << n;
        
        // Precompute LCMs and subset sizes to optimize binary search checks
        long[] lcms = new long[numSubsets];
        int[] signs = new int[numSubsets];
        
        for (int mask = 1; mask < numSubsets; mask++) {
            long currentLcm = 1;
            int count = 0;
            for (int i = 0; i < n; i++) {
                if ((mask & (1 << i)) != 0) {
                    currentLcm = lcm(currentLcm, coins[i]);
                    count++;
                }
            }
            lcms[mask] = currentLcm;
            signs[mask] = (count % 2 == 1) ? 1 : -1;
        }

        // Binary search bounds
        long minCoin = coins[0];
        for (int coin : coins) {
            minCoin = Math.min(minCoin, coin);
        }
        
        long left = 1;
        long right = minCoin * k;
        long ans = right;

        while (left <= right) {
            long mid = left + (right - left) / 2;
            
            if (countAmounts(mid, numSubsets, lcms, signs) >= k) {
                ans = mid;
                right = mid - 1; // Try to find a smaller valid amount
            } else {
                left = mid + 1;
            }
        }

        return ans;
    }

    private long countAmounts(long m, int numSubsets, long[] lcms, int[] signs) {
        long total = 0;
        for (int mask = 1; mask < numSubsets; mask++) {
            total += signs[mask] * (m / lcms[mask]);
        }
        return total;
    }

    private long gcd(long a, long b) {
        while (b != 0) {
            long temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }

    private long lcm(long a, long b) {
        return (a / gcd(a, b)) * b;
    }
}