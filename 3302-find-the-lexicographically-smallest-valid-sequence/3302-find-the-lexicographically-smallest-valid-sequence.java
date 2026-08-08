class Solution {
    public int[] validSequence(String word1, String word2) {
        int n = word1.length();
        int m = word2.length();

        // last[j] stores the maximum index in word1 from where word2[j...] can be matched
        int[] last = new int[m + 1];
        last[m] = n;

        int p1 = n - 1;
        for (int p2 = m - 1; p2 >= 0; p2--) {
            while (p1 >= 0 && word1.charAt(p1) != word2.charAt(p2)) {
                p1--;
            }
            last[p2] = p1;
            if (p1 >= 0) {
                p1--; // move to the next candidate character in word1
            }
        }

        int[] ans = new int[m];
        boolean changed = false;
        int idx2 = 0;

        for (int idx1 = 0; idx1 < n && idx2 < m; idx1++) {
            boolean isMatch = word1.charAt(idx1) == word2.charAt(idx2);
            
            // Check if we can change word1[idx1] to match word2[idx2]
            boolean canChange = !changed && (last[idx2 + 1] > idx1);

            if (isMatch || canChange) {
                if (!isMatch) {
                    changed = true; // Use up our single allowed modification
                }
                ans[idx2] = idx1;
                idx2++;
            }
        }

        return idx2 == m ? ans : new int[0];
    }
}