class Solution {
    public long sumAndMultiply(int n) {
        // Handle edge case where n is 0
        if (n == 0) {
            return 0;
        }

        long x = 0;
        long sum = 0;
        long placeValue = 1;

        // Process digits from right to left
        while (n > 0) {
            int digit = n % 10;
            if (digit != 0) {
                // Add to the sum of digits
                sum += digit;
                // Reconstruct x in the correct original order
                x = digit * placeValue + x;
                // Move the place value to the next position (tens, hundreds, etc.)
                placeValue *= 10;
            }
            n /= 10;
        }

        // Return the final multiplied product
        return x * sum;
    }
}