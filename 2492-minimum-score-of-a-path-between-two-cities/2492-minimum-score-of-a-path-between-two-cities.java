import java.util.*;

class Solution {
    public int minScore(int n, int[][] roads) {
        // Build the adjacency list: node -> list of [neighbor, distance]
        Map<Integer, List<int[]>> graph = new HashMap<>();
        for (int[] road : roads) {
            graph.computeIfAbsent(road[0], k -> new ArrayList<>()).add(new int[]{road[1], road[2]});
            graph.computeIfAbsent(road[1], k -> new ArrayList<>()).add(new int[]{road[0], road[2]});
        }

        int minScore = Integer.MAX_VALUE;
        boolean[] visited = new boolean[n + 1];
        Queue<Integer> queue = new LinkedList<>();
        
        // Start BFS from city 1
        queue.offer(1);
        visited[1] = true;

        while (!queue.isEmpty()) {
            int node = queue.poll();
            
            if (!graph.containsKey(node)) continue;
            
            for (int[] neighbor : graph.get(node)) {
                int nextNode = neighbor[0];
                int distance = neighbor[1];
                
                // Track the minimum edge distance seen in this component
                minScore = Math.min(minScore, distance);
                
                if (!visited[nextNode]) {
                    visited[nextNode] = true;
                    queue.offer(nextNode);
                }
            }
        }

        return minScore;
    }
}