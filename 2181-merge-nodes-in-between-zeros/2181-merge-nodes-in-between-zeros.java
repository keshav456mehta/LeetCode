class Solution {
    public ListNode mergeNodes(ListNode head) {

        ListNode temp = head.next;
        ListNode nHead = new ListNode(0);
        ListNode curr = nHead;

        while (temp != null) {

            int sum = 0;

            while (temp.val != 0) {
                sum += temp.val;
                temp = temp.next;
            }

            curr.next = new ListNode(sum);
            curr = curr.next;

            temp = temp.next;
        }

        return nHead.next;
    }
}