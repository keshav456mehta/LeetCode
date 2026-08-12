class Solution {
    public int numberOfSubstrings(String s) {

        int[] freq = new int[3];
        int left = 0;
        int ans = 0;

        for(int i = 0; i < s.length(); i++) {

            if(s.charAt(i) == 'a') {
                freq[0]++;
            }
            else if(s.charAt(i) == 'b') {
                freq[1]++;
            }
            else {
                freq[2]++;
            }

            while(freq[0] > 0 && freq[1] > 0 && freq[2] > 0) {

                if(s.charAt(left) == 'a') {
                    freq[0]--;
                }
                else if(s.charAt(left) == 'b') {
                    freq[1]--;
                }
                else {
                    freq[2]--;
                }

                left++;
            }

            ans += left;
        }

        return ans;
    }
}