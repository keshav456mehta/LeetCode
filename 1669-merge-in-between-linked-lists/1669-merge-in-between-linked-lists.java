
class Solution {
    public ListNode mergeInBetween(ListNode list1, int a, int b, ListNode list2) {

        ListNode prev = list1;

        // Find node at index a - 1
        for (int i = 0; i < a - 1; i++) {
            prev = prev.next;
        }

        // Find node at index b + 1
        ListNode after = prev.next;

        for (int i = a; i <= b; i++) {
            after = after.next;
        }

        // Find the last node of list2
        ListNode tail = list2;

        while (tail.next != null) {
            tail = tail.next;
        }

        // Connect the three parts
        prev.next = list2;
        tail.next = after;

        return list1;
    }
}
