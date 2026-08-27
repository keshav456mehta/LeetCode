class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        // Try to match target as far as possible
        int matchLen = 0;
        int[] countCopy = count.clone();
        
        while (matchLen < n) {
            char tChar = target.charAt(matchLen);
            if (countCopy[tChar - 'a'] > 0) {
                countCopy[tChar - 'a']--;
                matchLen++;
            } else {
                break;
            }
        }

        // Backtrack from matchLen down to 0 to find the first place we can bump up target[i]
        for (int i = matchLen; i >= 0; i--) {
            // Restore frequency counts for indices > i
            int[] currentCount = count.clone();
            for (int k = 0; k < i; k++) {
                currentCount[target.charAt(k) - 'a']--;
            }

            // Look for the smallest character strictly greater than target[i]
            char tChar = (i < n) ? target.charAt(i) : ' ';
            int nextChar = -1;

            if (i < n) {
                for (int c = (tChar - 'a') + 1; c < 26; c++) {
                    if (currentCount[c] > 0) {
                        nextChar = c;
                        break;
                    }
                }
            }

            // If a strictly greater character is available at position i
            if (nextChar != -1) {
                StringBuilder sb = new StringBuilder();
                sb.append(target, 0, i);
                sb.append((char) ('a' + nextChar));
                currentCount[nextChar]--;

                // Append remaining characters in ascending order
                for (int c = 0; c < 26; c++) {
                    while (currentCount[c] > 0) {
                        sb.append((char) ('a' + c));
                        currentCount[c]--;
                    }
                }
                return sb.toString();
            }
        }

        return "";
    }
}