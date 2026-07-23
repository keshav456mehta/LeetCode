class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        if (n <= 2) {
            return n;
        }
        // Integer.highestOneBit(n) gives 2^(k-1), so shift left by 1 to get 2^k
        return Integer.highestOneBit(n) << 1;
    }
}