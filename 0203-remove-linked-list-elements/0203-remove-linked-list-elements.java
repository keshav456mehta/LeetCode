class Solution 
{
    public ListNode removeElements(ListNode head, int val) 
    {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode slow = dummy;

        while(slow.next != null)
        {
            if(slow.next.val == val)
            {
                slow.next = slow.next.next;
            }
            else
            {
                slow = slow.next;
            }
        }
        return dummy.next;
    }
}