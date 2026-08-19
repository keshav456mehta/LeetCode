import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {
        // Map row number to a bitmask representing reserved seats in positions 2..9
        Map<Integer, Integer> rowMap = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];
            // Only care about seats 2 to 9
            if (col >= 2 && col <= 9) {
                int mask = rowMap.getOrDefault(row, 0);
                mask |= (1 << (col - 2)); // Shift left so seat 2 maps to bit 0
                rowMap.put(row, mask);
            }
        }

        // Each unreserved row can accommodate 2 families
        int totalGroups = (n - rowMap.size()) * 2;

        for (int mask : rowMap.values()) {
            boolean left = (mask & 0b00001111) == 0;   // Seats 2,3,4,5 free (bits 0,1,2,3)
            boolean right = (mask & 0b11110000) == 0;  // Seats 6,7,8,9 free (bits 4,5,6,7)
            boolean middle = (mask & 0b00111100) == 0; // Seats 4,5,6,7 free (bits 2,3,4,5)

            if (left && right) {
                totalGroups += 2;
            } else if (left || right || middle) {
                totalGroups += 1;
            }
        }

        return totalGroups;
    }
}