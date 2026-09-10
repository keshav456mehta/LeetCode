/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */
class Solution {
    public ListNode middleNode(ListNode head) {
        int count = 0 ;
        ListNode curr = head; // never move head itself
        while (curr != null)
        {
            count = count +1;
            curr = curr.next;
        }
        ListNode temp = head;

        for (int i = 0; i < count/2; i++) 
        {
            temp = temp.next;
        }
        return temp;
    }
}