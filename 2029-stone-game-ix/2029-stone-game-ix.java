class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] cnt = new int[3];
        for (int stone : stones) {
            cnt[stone % 3]++;
        }

        if (cnt[0] % 2 == 0) {
            // Even number of 0s: Alice needs at least one 1 and one 2 to pick a winning path
            return cnt[1] >= 1 && cnt[2] >= 1;
        } else {
            // Odd number of 0s: Alice needs a sufficient gap between the counts of 1s and 2s
            return Math.abs(cnt[1] - cnt[2]) > 2;
        }
    }
}