class Solution 
{
    public boolean isPalindrome(ListNode head) 
    {
        int count = 0;
        ListNode curr = head;

        // Count nodes
        while (curr != null)
        {
            count++;
            curr = curr.next;
        }

        // Move temp to middle
        ListNode temp = head;

        for (int i = 0; i < count / 2; i++)
        {
            temp = temp.next;
        }

        // Reverse second half
        ListNode prev = null;
        curr = temp;

        while (curr != null)
        {
            ListNode next = curr.next;
            curr.next = prev;
            prev = curr;
            curr = next;
        }

        // Compare first half and reversed second half
        ListNode first = head;
        ListNode second = prev;

        while (second != null)
        {
            if (first.val != second.val)
            {
                return false;
            }

            first = first.next;
            second = second.next;
        }

        return true;
    }
}