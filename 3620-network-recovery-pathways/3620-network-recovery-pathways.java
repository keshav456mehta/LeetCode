import java.util.*;

class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        
        // 1. Build adjacency list and calculate in-degrees for topological sort
        List<int[]>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }
        int[] inDegree = new int[n];
        int maxCost = 0;
        
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1], cost = edge[2];
            adj[u].add(edge);
            inDegree[v]++;
            maxCost = Math.max(maxCost, cost);
        }
        
        // 2. Perform a global topological sort since the graph is a DAG
        List<Integer> topoOrder = new ArrayList<>();
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < n; i++) {
            if (inDegree[i] == 0) {
                q.offer(i);
            }
        }
        while (!q.isEmpty()) {
            int u = q.poll();
            topoOrder.add(u);
            for (int[] edge : adj[u]) {
                int v = edge[1];
                if (--inDegree[v] == 0) {
                    q.offer(v);
                }
            }
        }
        
        // 3. Binary Search on the maximum possible minimum edge cost
        int low = 0, high = maxCost;
        int ans = -1;
        
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (isValid(mid, n, adj, topoOrder, online, k)) {
                ans = mid; // mid is achievable, look for a larger score
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        
        return ans;
    }
    
    private boolean isValid(int mid, int n, List<int[]>[] adj, List<Integer> topoOrder, boolean[] online, long k) {
        // dp[i] stores the minimum path cost to reach node i from node 0
        long[] dp = new long[n];
        Arrays.fill(dp, Long.MAX_VALUE);
        dp[0] = 0;
        
        // Process vertices in topological order
        for (int u : topoOrder) {
            if (dp[u] == Long.MAX_VALUE) continue;
            if (!online[u]) continue; // Intermediate node cannot be offline
            
            for (int[] edge : adj[u]) {
                int v = edge[1];
                int cost = edge[2];
                
                // Enforce our binary search criteria: ignore weaker links
                if (cost < mid) continue;
                if (!online[v]) continue;
                
                if (dp[u] + cost < dp[v]) {
                    dp[v] = dp[u] + cost;
                }
            }
        }
        
        return dp[n - 1] <= k;
    }
}