import java.util.ArrayList;
import java.util.List;

class Solution {
    public List<List<Integer>> shiftGrid(int[][] grid, int k) {
        int m = grid.length;
        int n = grid[0].length;
        int totalElements = m * n;
        
        // Optimize k to avoid unnecessary full grid rotations
        k = k % totalElements;
        
        // Initialize the result list structure
        List<List<Integer>> result = new ArrayList<>();
        for (int i = 0; i < m; i++) {
            result.add(new ArrayList<>(n));
            for (int j = 0; j < n; j++) {
                result.get(i).add(0); // Fill with placeholder values
            }
        }
        
        // Map each element directly to its new position
        for (int r = 0; r < m; r++) {
            for (int c = 0; c < n; c++) {
                int newIndex = (r * n + c + k) % totalElements;
                int newR = newIndex / n;
                int newC = newIndex % n;
                result.get(newR).set(newC, grid[r][c]);
            }
        }
        
        return result;
    }
}