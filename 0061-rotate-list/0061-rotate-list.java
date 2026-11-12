
class Solution 
{
    public ListNode rotateRight(ListNode head, int k) 
    {
        if(head == null || head.next == null || k == 0)
        {
            return head;
        }

        int count = 0;
        ListNode curr = head;

        while(curr != null)
        {
            count++;
            curr = curr.next;
        }

        k = k % count;

        for(int i = 0; i < k; i++)
        {
            curr = head;
            ListNode temp = null;

            while(curr.next != null)
            {
                temp = curr;
                curr = curr.next;
            }

            temp.next = null;
            curr.next = head;
            head = curr;
        }

        return head;
    }
}
