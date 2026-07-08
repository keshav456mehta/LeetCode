import java.util.*;

class Solution {
    public int[] sumAndMultiply(String s, int[][] queries) {
        int m = s.length();
        long MOD = 1000000007;

        // 1. Extract non-zero digits and keep track of their original indices
        List<Integer> digits = new ArrayList<>();
        List<Integer> origIndices = new ArrayList<>();
        
        for (int i = 0; i < m; i++) {
            char ch = s.charAt(i);
            if (ch != '0') {
                digits.add(ch - '0');
                origIndices.add(i);
            }
        }

        int n = digits.size();
        
        // If there are no non-zero digits at all, all queries will result in 0
        if (n == 0) {
            return new int[queries.length];
        }

        // 2. Precompute next and previous non-zero mapped indices
        int[] nextNonZero = new int[m];
        int[] prevNonZero = new int[m];
        
        int ptr = 0;
        for (int i = 0; i < m; i++) {
            while (ptr < n && origIndices.get(ptr) < i) {
                ptr++;
            }
            nextNonZero[i] = ptr; // first non-zero index >= i
        }

        ptr = n - 1;
        for (int i = m - 1; i >= 0; i--) {
            while (ptr >= 0 && origIndices.get(ptr) > i) {
                ptr--;
            }
            prevNonZero[i] = ptr; // last non-zero index <= i
        }

        // 3. Precompute prefix sums and prefix concatenated values
        long[] prefSum = new long[n + 1];
        long[] prefX = new long[n + 1];
        long[] pow10 = new long[n + 1];
        
        pow10[0] = 1;
        for (int i = 0; i < n; i++) {
            prefSum[i + 1] = prefSum[i] + digits.get(i);
            prefX[i + 1] = (prefX[i] * 10 + digits.get(i)) % MOD;
            pow10[i + 1] = (pow10[i] * 10) % MOD;
        }

        // 4. Process each query
        int[] answer = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int ql = queries[i][0];
            int qr = queries[i][1];

            // Map original substring boundaries to our filtered non-zero array boundaries
            int L = nextNonZero[ql];
            int R = prevNonZero[qr];

            // If no non-zero elements exist within the [ql, qr] window
            if (L > R || L >= n || R < 0) {
                answer[i] = 0;
                continue;
            }

            // Calculate sum of digits in the range
            long sum = prefSum[R + 1] - prefSum[L];

            // Calculate x % MOD using prefix values
            long len = R - L + 1;
            long x = (prefX[R + 1] - (prefX[L] * pow10[(int) len]) % MOD + MOD) % MOD;

            // Final answer calculation
            answer[i] = (int) ((x * (sum % MOD)) % MOD);
        }

        return answer;
    }
}