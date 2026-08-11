import java.util.HashSet;
import java.util.Set;

class Solution {
    public int missingInteger(int[] nums) {
        int sum = nums[0];
        
        // Find longest sequential prefix sum
        for (int i = 1; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + 1) {
                sum += nums[i];
            } else {
                break;
            }
        }
        
        // Collect elements in a set for lookup
        Set<Integer> set = new HashSet<>();
        for (int num : nums) {
            set.add(num);
        }
        
        // Find smallest missing integer >= sum
        while (set.contains(sum)) {
            sum++;
        }
        
        return sum;
    }
}