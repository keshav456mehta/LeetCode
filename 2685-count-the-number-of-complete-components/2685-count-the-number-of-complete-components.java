import java.util.*;

class Solution {
    public int countCompleteComponents(int n, int[][] edges) {
        // Build the adjacency list
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }
        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        boolean[] visited = new boolean[n];
        int completeComponentsCount = 0;

        // Traverse all nodes
        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                if (bfs(i, adj, visited)) {
                    completeComponentsCount++;
                }
            }
        }

        return completeComponentsCount;
    }

    private boolean bfs(int start, List<List<Integer>> adj, boolean[] visited) {
        Queue<Integer> queue = new LinkedList<>();
        queue.offer(start);
        visited[start] = true;

        int vertexCount = 0;
        int edgeCount = 0;

        while (!queue.isEmpty()) {
            int curr = queue.poll();
            vertexCount++;
            // Sum up the degrees of all nodes in this component
            edgeCount += adj.get(curr).size();

            for (int neighbor : adj.get(curr)) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.offer(neighbor);
                }
            }
        }

        // Since it's an undirected graph, each edge is counted twice in the total degree sum
        // A component is complete if total_edges == V * (V - 1) / 2 -> 2 * total_edges == V * (V - 1)
        return edgeCount == (long) vertexCount * (vertexCount - 1);
    }
}