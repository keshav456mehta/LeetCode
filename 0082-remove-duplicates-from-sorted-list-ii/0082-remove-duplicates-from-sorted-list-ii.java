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
        ListNode curr = head;

        while(curr != null)
        {
            if(curr.next != null && curr.val == curr.next.val)
            {
                int duplicate = curr.val;

                while(curr != null && curr.val == duplicate)
                {
                    curr = curr.next;
                }

                prev.next = curr;
            }
            else
            {
                prev = prev.next;
                curr = curr.next;
            }
        }

        return dummy.next;
    }
}