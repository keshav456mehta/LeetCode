class Solution {
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {

        ListNode dummy = new ListNode(0);
        ListNode tail = dummy;

        ListNode curr = list1;
        ListNode curr2 = list2;

        while (curr != null && curr2 != null) {

            if (curr.val <= curr2.val) {
                tail.next = curr;
                curr = curr.next;
            } 
            else {
                tail.next = curr2;
                curr2 = curr2.next;
            }

            tail = tail.next;
        }

        if (curr != null) {
            tail.next = curr;
        } 
        else {
            tail.next = curr2;
        }

        return dummy.next;
    }
}