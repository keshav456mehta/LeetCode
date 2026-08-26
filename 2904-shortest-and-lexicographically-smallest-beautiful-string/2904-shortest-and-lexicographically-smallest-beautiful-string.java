class Solution {
    public String shortestBeautifulSubstring(String s, int k) {
        int n = s.length();
        String result = "";
        int minLen = n + 1;
        
        int count = 0;
        int left = 0;
        
        for (int right = 0; right < n; right++) {
            if (s.charAt(right) == '1') {
                count++;
            }
            
            while (count == k) {
                // Shrink leading zeros to get the shortest substring starting at left
                while (s.charAt(left) == '0') {
                    left++;
                }
                
                int len = right - left + 1;
                String sub = s.substring(left, right + 1);
                
                if (len < minLen || (len == minLen && sub.compareTo(result) < 0)) {
                    minLen = len;
                    result = sub;
                }
                
                // Move left past the first '1' to find next window
                count--;
                left++;
            }
        }
        
        return result;
    }
}