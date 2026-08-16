class Solution {
    public int characterReplacement(String s, int k) {

        int[] freq = new int[26];

        int l = 0;
        int maxFreq = 0;
        int ans = 0;

        for (int r = 0; r < s.length(); r++) {

            char ch = s.charAt(r);
            freq[ch - 'A']++;

            maxFreq = Math.max(maxFreq, freq[ch - 'A']);

            while ((r - l + 1) - maxFreq > k) {
                freq[s.charAt(l) - 'A']--;
                l++;
            }

            ans = Math.max(ans, r - l + 1);
        }

        return ans;
    }
}