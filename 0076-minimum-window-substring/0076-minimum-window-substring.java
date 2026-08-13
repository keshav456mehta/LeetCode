class Solution {
    public String minWindow(String s, String t) {

        if(t.length() > s.length()) {
            return "";
        }

        Map<Character, Integer> tfreq = new HashMap<>();

        for(int i = 0; i < t.length(); i++) {
            char ch = t.charAt(i);
            tfreq.put(ch, tfreq.getOrDefault(ch, 0) + 1);
        }

        Map<Character, Integer> window = new HashMap<>();

        int left = 0;
        int have = 0;
        int need = tfreq.size();

        int minLen = Integer.MAX_VALUE;
        int start = 0;

        for(int right = 0; right < s.length(); right++) {

            char ch = s.charAt(right);

            window.put(ch, window.getOrDefault(ch, 0) + 1);

            if(tfreq.containsKey(ch) &&
               window.get(ch).intValue() == tfreq.get(ch)) {
                have++;
            }

            while(have == need) {

                if(right - left + 1 < minLen) {
                    minLen = right - left + 1;
                    start = left;
                }

                char leftChar = s.charAt(left);

                window.put(leftChar, window.get(leftChar) - 1);

                if(tfreq.containsKey(leftChar) &&
                   window.get(leftChar) < tfreq.get(leftChar)) {
                    have--;
                }

                left++;
            }
        }

        if(minLen == Integer.MAX_VALUE) {
            return "";
        }

        return s.substring(start, start + minLen);
    }
}