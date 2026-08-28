class Solution {
    public String lexPalindromicPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];
        for (int i = 0; i < n; i++) {
            count[s.charAt(i) - 'a']++;
        }

        int oddCount = 0;
        char mid = 0;
        int[] halfCount = new int[26];

        for (int i = 0; i < 26; i++) {
            if (count[i] % 2 != 0) {
                oddCount++;
                mid = (char) ('a' + i);
            }
            halfCount[i] = count[i] / 2;
        }

        if (oddCount > 1) {
            return "";
        }

        int m = n / 2;
        String best = null;

        // Try prefix lengths L from m down to 0
        for (int L = m; L >= 0; L--) {
            int[] curHalf = halfCount.clone();
            boolean valid = true;
            StringBuilder prefix = new StringBuilder();

            for (int i = 0; i < L; i++) {
                int charIdx = target.charAt(i) - 'a';
                if (curHalf[charIdx] > 0) {
                    curHalf[charIdx]--;
                    prefix.append(target.charAt(i));
                } else {
                    valid = false;
                    break;
                }
            }

            if (!valid) continue;

            // Case 1: Pick a strictly larger character at index L (if L < m)
            if (L < m) {
                int startChar = target.charAt(L) - 'a' + 1;
                for (int c = startChar; c < 26; c++) {
                    if (curHalf[c] > 0) {
                        int[] tempHalf = curHalf.clone();
                        tempHalf[c]--;

                        StringBuilder firstHalf = new StringBuilder(prefix);
                        firstHalf.append((char) ('a' + c));

                        for (int ch = 0; ch < 26; ch++) {
                            while (tempHalf[ch] > 0) {
                                firstHalf.append((char) ('a' + ch));
                                tempHalf[ch]--;
                            }
                        }

                        StringBuilder secondHalf = new StringBuilder(firstHalf.substring(0, m)).reverse();
                        StringBuilder full = new StringBuilder(firstHalf);
                        if (n % 2 != 0) {
                            full.append(mid);
                        }
                        full.append(secondHalf);

                        String candidate = full.toString();
                        if (candidate.compareTo(target) > 0) {
                            if (best == null || candidate.compareTo(best) < 0) {
                                best = candidate;
                            }
                        }
                    }
                }
            } 
            // Case 2: Full prefix of length m matches
            else if (L == m) {
                StringBuilder firstHalf = new StringBuilder(prefix);
                StringBuilder secondHalf = new StringBuilder(firstHalf).reverse();
                StringBuilder full = new StringBuilder(firstHalf);
                if (n % 2 != 0) {
                    full.append(mid);
                }
                full.append(secondHalf);

                String candidate = full.toString();
                if (candidate.compareTo(target) > 0) {
                    if (best == null || candidate.compareTo(best) < 0) {
                        best = candidate;
                    }
                }
            }
        }

        return best == null ? "" : best;
    }
}