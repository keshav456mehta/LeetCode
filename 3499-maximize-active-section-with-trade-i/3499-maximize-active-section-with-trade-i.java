class Solution {
    public int maxActiveSectionsAfterTrade(String s) {
        int initialOnes = 0;
        for (char c : s.toCharArray()) {
            if (c == '1') initialOnes++;
        }

        // Augment string with '1' at both ends
        String t = "1" + s + "1";
        
        // Find contiguous segments of '0's and '1's
        java.util.List<Integer> zeroLengths = new java.util.ArrayList<>();
        java.util.List<Boolean> hasTwoZeroNeighbors = new java.util.ArrayList<>();
        
        int n = t.length();
        int maxDelta = 0;
        
        // Parse segments
        int i = 0;
        java.util.List<Integer> segLens = new java.util.ArrayList<>();
        java.util.List<Character> segTypes = new java.util.ArrayList<>();
        
        while (i < n) {
            char ch = t.charAt(i);
            int j = i;
            while (j < n && t.charAt(j) == ch) {
                j++;
            }
            segTypes.add(ch);
            segLens.add(j - i);
            i = j;
        }

        // Check each '1'-segment (excluding augmented outer ones if not flanked by zeros)
        for (int k = 1; k < segTypes.size() - 1; k++) {
            if (segTypes.get(k) == '1' && segTypes.get(k - 1) == '0' && segTypes.get(k + 1) == '0') {
                int delta = segLens.get(k - 1) + segLens.get(k + 1);
                maxDelta = Math.max(maxDelta, delta);
            }
        }

        return initialOnes + maxDelta;
    }
}