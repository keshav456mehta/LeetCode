class Solution 
{
    public ListNode deleteDuplicates(ListNode head) 
    {
        if(head == null)
        {
            return head;
        }

        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null)
        {
            if(slow.val == fast.val)
            {
                while(fast != null && slow.val == fast.val)
                {
                    fast = fast.next;
                }

                prev.next = fast;
                slow = fast;

                if(fast != null)
                {
                    fast = fast.next;
                }
            }
            else
            {
                prev = slow;
                slow = fast;
                fast = fast.next;
            }
        }

        return dummy.next;
    }
}