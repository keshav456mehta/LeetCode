class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int sumL = 0, sumR = 0;
        int qL = 0, qR = 0;

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                qL++;
            } else {
                sumL += num.charAt(i) - '0';
            }
        }
        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                qR++;
            } else {
                sumR += num.charAt(i) - '0';
            }
        }

        // Alice wins if the total number of '?' is odd
        if ((qL + qR) % 2 != 0) {
            return true;
        }

        // Bob wins iff the difference in sums can be balanced by pairs of '?' contributing 9 each
        return (sumL - sumR) * 2 != (qR - qL) * 9;
    }
}