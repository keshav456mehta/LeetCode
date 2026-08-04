class Solution {
    public int[][] merge(int[][] intervals) {

        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        int[][] ans = new int[intervals.length][2];
        int k = 0;

        ans[0] = intervals[0];

        for (int i = 1; i < intervals.length; i++) {

            if (intervals[i][0] <= ans[k][1])
                ans[k][1] = Math.max(ans[k][1], intervals[i][1]);
            else
                ans[++k] = intervals[i];
        }

        return Arrays.copyOf(ans, k + 1);
    }
}