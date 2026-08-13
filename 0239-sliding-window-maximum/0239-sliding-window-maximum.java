class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {

        int[] ans = new int[nums.length - k + 1];

        // Deque mein indices rakhenge
        // Front par hamesha maximum ka index hoga
        Deque<Integer> dq = new ArrayDeque<>();

        int left = 0;
        int index = 0;

        for(int right = 0; right < nums.length; right++) {

            // Window se bahar wale indices hatao
            while(!dq.isEmpty() && dq.peekFirst() < left) {
                dq.pollFirst();
            }

            // Chhoti values hatao, kyunki current value badi hai
            while(!dq.isEmpty() &&
                  nums[dq.peekLast()] <= nums[right]) {
                dq.pollLast();
            }

            // Current index add karo
            dq.offerLast(right);

            // Window size k ho gayi
            if(right - left + 1 == k) {

                // Front = current window ka maximum
                ans[index++] = nums[dq.peekFirst()];

                // Next window
                left++;
            }
        }

        return ans;
    }
}