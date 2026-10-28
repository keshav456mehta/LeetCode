class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        l1 = reverse(l1);
        l2 = reverse(l2);

        ListNode cur1 = l1;
        ListNode cur2 = l2;

        int digit = 0;

        if(cur1 != null) {
            digit += cur1.val;
            cur1 = cur1.next;
        }

        if(cur2 != null) {
            digit += cur2.val;
            cur2 = cur2.next;
        }

        int carry = digit / 10;

        digit %= 10;

        ListNode newHead = new ListNode(digit);

        ListNode cur = newHead;

        while(cur1 != null || cur2 != null || carry != 0) {
            digit = carry;

            if(cur1 != null) {
                digit += cur1.val;
                cur1 = cur1.next;
            }

            if(cur2 != null) {
                digit += cur2.val;
                cur2 = cur2.next;
            }

            carry = digit / 10;

            digit %= 10;

            ListNode newNode = new ListNode(digit);

            cur.next = newNode;

            cur = cur.next;
        }

        return reverse(newHead);
    }

    private static ListNode reverse(ListNode head) {
        if(head == null || head.next == null) return head;

        ListNode cur = head;
        ListNode pre = null;

        while(cur != null) {
            ListNode next = cur.next;
            cur.next = pre;
            pre = cur;
            cur = next;
        }

        return pre;
    }
}