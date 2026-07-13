import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        List<Integer> result = new ArrayList<>();
        String sample = "123456789";
        
        // Loop through all possible lengths of sequential digits (from 2 to 9 digits long)
        for (int length = 2; length <= 9; length++) {
            // Sliding window over the sample string
            for (int start = 0; start <= 9 - length; start++) {
                String substring = sample.substring(start, start + length);
                int num = Integer.parseInt(substring);
                
                // Add to result if it fits within our given range
                if (num >= low && num <= high) {
                    result.add(num);
                }
            }
        }
        
        return result;
    }
}