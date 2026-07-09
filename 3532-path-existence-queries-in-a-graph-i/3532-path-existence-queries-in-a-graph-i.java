class Solution {
    public boolean[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Array to store the component ID for each node
        int[] component = new int[n];
        int currentId = 0;
        component[0] = currentId;
        
        // Group nodes into continuous connected components
        for (int i = 1; i < n; i++) {
            if (nums[i] - nums[i - 1] > maxDiff) {
                currentId++; // Break in continuity, start a new component
            }
            component[i] = currentId;
        }
        
        // Answer each query in O(1) time
        boolean[] answer = new boolean[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int u = queries[i][0];
            int v = queries[i][1];
            answer[i] = (component[u] == component[v]);
        }
        
        return answer;
    }
}