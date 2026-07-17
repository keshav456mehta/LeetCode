import java.util.Arrays;

class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }
        
        // Step 1: Count frequency of each number
        int[] freq = new int[maxVal + 1];
        for (int num : nums) {
            freq[num]++;
        }
        
        // Step 2: Count how many elements are multiples of each i
        long[] countDivisible = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            for (int j = i; j <= maxVal; j += i) {
                countDivisible[i] += freq[j];
            }
        }
        
        // Step 3: Compute exact number of pairs with GCD equal to i using inclusion-exclusion
        long[] gcdPairsCount = new long[maxVal + 1];
        for (int i = maxVal; i >= 1; i--) {
            long totalPairs = (countDivisible[i] * (countDivisible[i] - 1)) / 2;
            // Subtract multiples to get exact GCD match
            for (int j = 2 * i; j <= maxVal; j += i) {
                totalPairs -= gcdPairsCount[j];
            }
            gcdPairsCount[i] = totalPairs;
        }
        
        // Step 4: Compute prefix sums of the counts
        long[] prefixSum = new long[maxVal + 1];
        for (int i = 1; i <= maxVal; i++) {
            prefixSum[i] = prefixSum[i - 1] + gcdPairsCount[i];
        }
        
        // Step 5: Answer each query using binary search
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            long target = queries[i];
            
            // Binary search to find the smallest GCD value where prefixSum[gcd] > target
            int low = 1, high = maxVal, res = maxVal;
            while (low <= high) {
                int mid = low + (high - low) / 2;
                if (prefixSum[mid] > target) {
                    res = mid;
                    high = mid - 1; // Try finding a smaller valid GCD
                } else {
                    low = mid + 1;
                }
            }
            answer[i] = res;
        }
        
        return answer;
    }
}