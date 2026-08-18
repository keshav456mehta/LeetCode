class Solution {
    public int largestInteger(int[] nums, int k) {
        int n = nums.length;
        int[] subarrayCount = new int[51]; // Tracks how many subarrays of size k contain each number

        // Loop 1: Iterate over all possible starting positions for subarrays of size k
        for (int i = 0; i <= n - k; i++) {
            boolean[] seenInSubarray = new boolean[51];
            
            // Loop 2: Iterate through the elements within the current subarray of size k
            for (int j = i; j < i + k; j++) {
                seenInSubarray[nums[j]] = true;
            }
            
            // Count each unique element present in this subarray
            for (int num = 0; num <= 50; num++) {
                if (seenInSubarray[num]) {
                    subarrayCount[num]++;
                }
            }
        }

        // Find the largest number that appears in EXACTLY 1 subarray of size k
        int maxVal = -1;
        for (int num = 0; num <= 50; num++) {
            if (subarrayCount[num] == 1) {
                maxVal = Math.max(maxVal, num);
            }
        }

        return maxVal;
    }
}