
class Solution 
{
    public ListNode rotateRight(ListNode head, int k) 
    {
        if(head == null || head.next == null || k == 0)
        {
            return head;
        }

        int count = 1;
        ListNode curr = head;

        while(curr.next != null)
        {
            count++;
            curr = curr.next;
        }

        k = k % count;

        if(k == 0)
        {
            return head;
        }

        curr.next = head;

        int steps = count - k - 1;

        ListNode newTail = head;

        for(int i = 0; i < steps; i++)
        {
            newTail = newTail.next;
        }

        ListNode newHead = newTail.next;

        newTail.next = null;

        return newHead;
    }
}
