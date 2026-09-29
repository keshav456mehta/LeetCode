class Solution 
{
    public ListNode middleNode(ListNode head) 
    {
        int count = 0 ;
        ListNode curr = head; 
        while(curr != null)
        {
            count+=1;
            curr = curr.next;
        }   
        curr = head; 
        for(int i = 0 ; i < count/2;i++)
        {
            curr = curr.next;
        }
        return curr;
    }
}