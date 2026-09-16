class Solution 
{
    public ListNode mergeKLists(ListNode[] lists) 
    {
        ListNode dummy = new ListNode(-1);
        ListNode curr = dummy;

        ListNode[] head = lists;

        while (true)
        {
            int min = Integer.MAX_VALUE;
            int minIndex = -1;

            // Find the smallest current head
            for (int i = 0; i < head.length; i++)
            {
                if (head[i] != null && head[i].val < min)
                {
                    min = head[i].val;
                    minIndex = i;
                }
            }

            // No nodes left
            if (minIndex == -1)
            {
                break;
            }

            // Take the minimum node
            curr.next = head[minIndex];
            curr = curr.next;

            // Move that list's head forward
            head[minIndex] = head[minIndex].next;
        }

        return dummy.next;
    }
}