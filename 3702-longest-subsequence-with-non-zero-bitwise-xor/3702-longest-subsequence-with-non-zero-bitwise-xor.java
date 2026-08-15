class Solution {
    public int longestSubsequence(int[] nums) {
        int totalXor = 0;
        boolean hasNonZero = false;

        for (int num : nums) {
            totalXor ^= num;
            if (num != 0) {
                hasNonZero = true;
            }
        }

        // Case 1: Entire array already has a non-zero XOR
        if (totalXor != 0) {
            return nums.length;
        }

        // Case 2: Entire array is composed of zeros
        if (!hasNonZero) {
            return 0;
        }

        // Case 3: Total XOR is 0, but removing one non-zero element makes XOR non-zero
        return nums.length - 1;
    }
}