class Solution {
    public int distinctSubseqII(String s) {
        int MOD = 1_000_000_007;
        // last[i] stores the number of distinct subsequences ending with character ('a' + i)
        long[] last = new long[26];

        for (char c : s.toCharArray()) {
            int charIdx = c - 'a';
            
            // Calculate sum of all existing distinct subsequences
            long currentSum = 0;
            for (int i = 0; i < 26; i++) {
                currentSum = (currentSum + last[i]) % MOD;
            }
            
            // New subsequences ending with 'c' = 1 (for "c" itself) + currentSum
            last[charIdx] = (currentSum + 1) % MOD;
        }

        // Sum up all distinct subsequences ending with any character
        long total = 0;
        for (int i = 0; i < 26; i++) {
            total = (total + last[i]) % MOD;
        }

        return (int) total;
    }
}