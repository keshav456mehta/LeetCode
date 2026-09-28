class Solution 
{
    public ListNode swapPairs(ListNode head) 
    {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while(prev.next != null && prev.next.next != null)
        {
            ListNode left = prev.next;
            ListNode right = left.next;

            left.next = right.next;
            right.next = left;
            prev.next = right;

            prev = left;
        }

        return dummy.next;
    }
}