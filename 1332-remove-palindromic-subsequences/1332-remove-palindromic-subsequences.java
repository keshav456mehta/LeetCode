class Solution 
{
    public int removePalindromeSub(String s) 
    {
        String f = new StringBuilder(s).reverse().toString();
        if(s.length() == 0)
        {
            return 0;
        }    
        else if(s.equals(f))
        {
            return 1;
        }
        else{
            return 2;
        }
    }
}