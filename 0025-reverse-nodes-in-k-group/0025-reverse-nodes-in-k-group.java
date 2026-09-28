class Solution 
{
    public ListNode reverseKGroup(ListNode head, int k) 
    {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while(true)
        {
            ListNode curr = prev;

            // Check whether k nodes are available
            for(int i = 0; i < k; i++)
            {
                curr = curr.next;

                if(curr == null)
                {
                    return dummy.next;
                }
            }

            ListNode groupEnd = curr;
            ListNode groupStart = prev.next;
            ListNode nextGroup = groupEnd.next;

            // Reverse current group
            ListNode prevNode = nextGroup;
            curr = groupStart;

            while(curr != nextGroup)
            {
                ListNode nextNode = curr.next;
                curr.next = prevNode;
                prevNode = curr;
                curr = nextNode;
            }

            // Connect previous part with reversed group
            prev.next = groupEnd;

            // Move prev to the end of reversed group
            prev = groupStart;
        }
    }
}