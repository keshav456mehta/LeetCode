class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;
        if (n <= 2) return n;

        int minIdx = 0;
        int maxIdx = 0;

        for (int k = 0; k < n; k++) {
            if (nums[k] < nums[minIdx]) {
                minIdx = k;
            }
            if (nums[k] > nums[maxIdx]) {
                maxIdx = k;
            }
        }

        int i = Math.min(minIdx, maxIdx);
        int j = Math.max(minIdx, maxIdx);

        // Option 1: Both from front
        int removeBothFront = j + 1;

        // Option 2: Both from back
        int removeBothBack = n - i;

        // Option 3: One from front, one from back
        int removeFrontAndBack = (i + 1) + (n - j);

        return Math.min(removeBothFront, Math.min(removeBothBack, removeFrontAndBack));
    }
}