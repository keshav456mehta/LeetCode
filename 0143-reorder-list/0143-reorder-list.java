class Solution 
{
    public void reorderList(ListNode head) 
    {
        if(head == null || head.next == null)
        {
            return;
        }

        ListNode curr = head;
        ListNode currHalf = head;
        int count = 0;

        // Count total nodes
        while(curr != null)
        {
            curr = curr.next;
            count++;
        }

        // Reach middle
        int half = count / 2;

        for(int i = 0; i < half; i++)
        {
            currHalf = currHalf.next;
        }

        // Reverse second half
        ListNode prevNode = null;
        ListNode currNode = currHalf.next;

        currHalf.next = null;

        while(currNode != null)
        {
            ListNode nextNode = currNode.next;

            currNode.next = prevNode;
            prevNode = currNode;
            currNode = nextNode;
        }

        // Merge both halves
        ListNode first = head;
        ListNode second = prevNode;

        while(second != null)
        {
            ListNode nextFirst = first.next;
            ListNode nextSecond = second.next;

            first.next = second;
            second.next = nextFirst;

            first = nextFirst;
            second = nextSecond;
        }
    }
}