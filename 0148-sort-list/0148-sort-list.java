class Solution 
{
    public ListNode sortList(ListNode head) 
    {
        // Empty list or only one node
        if(head == null || head.next == null)
        {
            return head;
        }

        // Find the middle of the list
        ListNode slow = head;
        ListNode fast = head.next;

        while(fast != null && fast.next != null)
        {
            slow = slow.next;
            fast = fast.next.next;
        }

        // Split the list into two halves
        ListNode second = slow.next;
        slow.next = null;

        // Sort both halves
        ListNode left = sortList(head);
        ListNode right = sortList(second);

        // Merge the two sorted halves
        return merge(left, right);
    }

    public ListNode merge(ListNode left, ListNode right)
    {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        while(left != null && right != null)
        {
            if(left.val <= right.val)
            {
                curr.next = left;
                left = left.next;
            }
            else
            {
                curr.next = right;
                right = right.next;
            }

            curr = curr.next;
        }

        // Attach remaining nodes
        if(left != null)
        {
            curr.next = left;
        }

        if(right != null)
        {
            curr.next = right;
        }

        return dummy.next;
    }
}