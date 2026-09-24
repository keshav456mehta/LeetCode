class Solution 
{
    public int[] nextGreaterElements(int[] nums) 
    {
        int n = nums.length;
        int[] ans = new int[n];

        Stack<Integer> st = new Stack<Integer>();

        for(int i = 0; i < n; i++)
        {
            ans[i] = -1;
        }

        for(int i = 2*n-1; i >= 0; i--)
        {
            int index = i % n;

            while(!st.isEmpty() && st.peek() <= nums[index])
            {
                st.pop();
            }

            if(i < n && !st.isEmpty())
            {
                ans[index] = st.peek();
            }

            st.push(nums[index]);
        }

        return ans;
    }
}