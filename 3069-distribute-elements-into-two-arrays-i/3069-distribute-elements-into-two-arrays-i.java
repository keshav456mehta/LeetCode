class Solution {
    public int[] resultArray(int[] nums) {
        int n = nums.length;
        List<Integer> arr1 = new ArrayList<>();
        List<Integer> arr2 = new ArrayList<>();
        
        // Step 1: Initialize arr1 and arr2 with the first two elements
        arr1.add(nums[0]);
        arr2.add(nums[1]);
        
        // Step 2: Distribute remaining elements based on the last elements
        for (int i = 2; i < n; i++) {
            if (arr1.get(arr1.size() - 1) > arr2.get(arr2.size() - 1)) {
                arr1.add(nums[i]);
            } else {
                arr2.add(nums[i]);
            }
        }
        
        // Step 3: Concatenate arr1 and arr2 into the result array
        int[] result = new int[n];
        int index = 0;
        
        for (int num : arr1) {
            result[index++] = num;
        }
        for (int num : arr2) {
            result[index++] = num;
        }
        
        return result;
    }
}