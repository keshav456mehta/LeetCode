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
    public int[] nodesBetweenCriticalPoints(ListNode head) {
        if (head == null || head.next == null || head.next.next == null) {
            return new int[]{-1, -1};
        }

        ListNode prev = head;
        ListNode curr = head.next;
        ListNode next = curr.next;

        int firstCP = -1;
        int prevCP = -1;
        int minDistance = Integer.MAX_VALUE;
        int position = 1;

        while (next != null) {
            // Check if current node is a local maxima or minima
            if ((curr.val > prev.val && curr.val > next.val) || 
                (curr.val < prev.val && curr.val < next.val)) {
                
                if (firstCP == -1) {
                    firstCP = position;
                } else {
                    minDistance = Math.min(minDistance, position - prevCP);
                }
                prevCP = position;
            }

            // Move pointers forward
            prev = curr;
            curr = next;
            next = next.next;
            position++;
        }

        // If fewer than 2 critical points were found
        if (minDistance == Integer.MAX_VALUE) {
            return new int[]{-1, -1};
        }

        int maxDistance = prevCP - firstCP;
        return new int[]{minDistance, maxDistance};
    }
}