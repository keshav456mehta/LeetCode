class Solution 
{
    public ListNode removeNodes(ListNode head) 
    {
        ListNode prev = null;
        ListNode curr = head;

        // Step 1: Reverse the linked list
        while(curr != null)
        {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        head = prev;

        // Step 2: Remove nodes smaller than maximum
        curr = head;
        int max = curr.val;

        while(curr.next != null)
        {
            if(curr.next.val < max)
            {
                curr.next = curr.next.next;
            }
            else
            {
                max = curr.next.val;
                curr = curr.next;
            }
        }

        // Step 3: Reverse again
        prev = null;
        curr = head;

        while(curr != null)
        {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        return prev;
    }
}