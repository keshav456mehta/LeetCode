class Solution 
{
    public double findMaxAverage(int[] nums, int k) 
    {
        int l = 0 ; int max = Integer.MIN_VALUE;int sum =0;
        for(int r = 0 ; r < nums.length ; r++)
        {
            sum = sum +nums[r];
            if(r-l+1 == k)
            {
                max = Math.max(max,sum);
                sum -= nums[l];
                l++;
            }
            // r++;
        }  
        return (double)max/k;  
    }
}