
class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while (true) {
            ListNode kth = prev;

            // Find the kth node
            for (int i = 0; i < k; i++) {
                kth = kth.next;

                if (kth == null) {
                    return dummy.next;
                }
            }

            ListNode next = kth.next;

            // Reverse current group
            ListNode curr = prev.next;
            ListNode temp = next;

            while (curr != next) {
                ListNode front = curr.next;
                curr.next = temp;
                temp = curr;
                curr = front;
            }

            // Connect reversed group
            ListNode first = prev.next;
            prev.next = kth;
            prev = first;
        }
    }
}
