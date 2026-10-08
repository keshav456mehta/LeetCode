class Solution {
    public ListNode swapNodes(ListNode head, int k) {
        ListNode node = head;
        for (int i = 1; i < k; i++) {
            node = node.next;
        }

        ListNode slow = node;
        ListNode fast = head;
        while (node.next != null) {
            node = node.next;
            fast = fast.next;
        }

        int temp = fast.val;
        fast.val = slow.val;
        slow.val = temp;

        return head;
    }
}