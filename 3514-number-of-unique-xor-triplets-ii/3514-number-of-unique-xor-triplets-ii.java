class Solution {
    public int uniqueXorTriplets(int[] nums) {
        boolean[] hasPair = new boolean[2048];
        boolean[] hasTriplet = new boolean[2048];
        int n = nums.length;

        for (int k = 0; k < n; k++) {
            // Update available pairs using nums[k]
            for (int i = 0; i <= k; i++) {
                hasPair[nums[i] ^ nums[k]] = true;
            }

            // Combine all existing pairs with nums[k] to form triplets
            for (int x = 0; x < 2048; x++) {
                if (hasPair[x]) {
                    hasTriplet[x ^ nums[k]] = true;
                }
            }
        }

        int count = 0;
        for (boolean present : hasTriplet) {
            if (present) {
                count++;
            }
        }

        return count;
    }
}