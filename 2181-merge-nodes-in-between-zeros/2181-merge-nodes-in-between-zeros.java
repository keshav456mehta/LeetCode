class Solution {
    public ListNode mergeNodes(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }

        ListNode temp = head.next;
        ListNode nHead = temp;

        while (temp != null) {
            int sum = 0;

            while (temp.val != 0) {
                sum += temp.val;
                temp = temp.next;
            }

            nHead.val = sum;
            temp = temp.next;
            nHead.next = temp;
            nHead = nHead.next;
        }

        return head.next;
    }
}