class Solution {
    public ListNode deleteDuplicates(ListNode head) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;

        ListNode prev = dummy;

        while (head != null) {
            if (head.next != null && head.val == head.next.val) {
                int value = head.val;

                while (head != null && head.val == value) {
                    head = head.next;
                }

                prev.next = head;
            } else {
                prev = head;
                head = head.next;
            }
        }

        return dummy.next;
    }
}