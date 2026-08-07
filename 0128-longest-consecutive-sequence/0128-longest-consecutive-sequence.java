class Solution {
    public int longestConsecutive(int[] nums) {

        HashSet<Integer> set = new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            set.add(nums[i]);
        }

        int longest = 0;

        for (int x : set) {

            if (!set.contains(x - 1)) {

                int count = 1;

                while (set.contains(x + count)) {
                    count++;
                }

                longest = Math.max(longest, count);
            }
        }

        return longest;
    }
}