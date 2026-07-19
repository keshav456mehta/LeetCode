class Solution {
    public String smallestSubsequence(String s) {
        int[] lastIndex = new int[26];
        for (int i = 0; i < s.length(); i++) {
            lastIndex[s.charAt(i) - 'a'] = i; // Store the last seen index of each character
        }
        
        boolean[] seen = new boolean[26]; // Track if char is already in the stack
        StringBuilder sb = new StringBuilder(); // Using StringBuilder as our stack
        
        for (int i = 0; i < s.length(); i++) {
            char c = s.charAt(i);
            int idx = c - 'a';
            
            // If we have already included this character, skip it
            if (seen[idx]) continue;
            
            // Maintain the monotonic property: pop larger characters if they appear later
            while (sb.length() > 0 && sb.charAt(sb.length() - 1) > c && lastIndex[sb.charAt(sb.length() - 1) - 'a'] > i) {
                seen[sb.charAt(sb.length() - 1) - 'a'] = false;
                sb.deleteCharAt(sb.length() - 1);
            }
            
            sb.append(c);
            seen[idx] = true;
        }
        
        return sb.toString();
    }
}