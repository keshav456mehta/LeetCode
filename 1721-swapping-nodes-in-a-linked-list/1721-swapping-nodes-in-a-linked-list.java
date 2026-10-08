class Solution
{
    public ListNode swapNodes(ListNode head, int k)
    {
        ListNode curr = head;
        ListNode ccurr = head;

        int count = 0;

        // Find kth node from beginning
        for(int i = 0; i < k - 1; i++)
        {
            curr = curr.next;
        }

        // Count total nodes
        while(ccurr != null)
        {
            count++;
            ccurr = ccurr.next;
        }

        // Find kth node from end
        ccurr = head;

        for(int i = 0; i < count - k; i++)
        {
            ccurr = ccurr.next;
        }

        // Swap values
        int temp = curr.val;
        curr.val = ccurr.val;
        ccurr.val = temp;

        return head;
    }
}