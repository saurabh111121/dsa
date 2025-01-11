/*
There is one spaceship.
X and Y co-ordinate of the source of spaceship and destination spaceship is given.
There is N number of wormholes; each wormhole has 5 values.
First 2 values are starting co-ordinate of the wormhole and after that value no. 3 and 4 represent ending co-ordinate of the wormhole and last 5th value is represents a cost to pass through this wormhole.
Now, these warmholes are bi-directional. Now the to go from (x1,y1) to (x2,y2) is abs(x1-x2)+abs(y1-y2).
The main problem here is to find the minimum distance to reach spaceship from source to destination co-ordinate using any number of warm-hole.
It is ok if you won't uses any warmhole.

Sample Input -
0 0
100 100
3
1 2 120 120 2
4 5 120 100 3
6 8 150 180 4

 */



package com.interview.Samsung;

import java.util.*;

class Wormholes {
    // Class to represent a point in 2D space with x and y coordinates
    static class Point {
        int x, y;
        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    // Class to represent an edge in the graph with a target node and weight (travel time)
    static class Edge {
        int target, weight;
        public Edge(int target, int weight) {
            this.target = target;
            this.weight = weight;
        }
    }

    // Method to calculate the minimum time from S to D considering wormholes
    public static int findMinimumTime(Point S, Point D, List<int[]> wormholes) {
        int n = wormholes.size(); // Number of wormholes
        List<Point> points = new ArrayList<>(); // List to store all points (S, D, wormhole entry/exit points)
        points.add(S); // Add source point S
        points.add(D); // Add destination point D

        // Add entry and exit points of all wormholes
        for (int[] wormhole : wormholes) {
            points.add(new Point(wormhole[0], wormhole[1])); // Entry point of wormhole
            points.add(new Point(wormhole[2], wormhole[3])); // Exit point of wormhole
        }

        // Call Dijkstra's algorithm to calculate shortest path
        return dijkstra(S, D, points, wormholes);
    }

    // Implementation of Dijkstra's algorithm with dynamic edge generation
    private static int dijkstra(Point S, Point D, List<Point> points, List<int[]> wormholes) {
        // Priority queue to store nodes and their current distance, prioritized by distance
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[1]));

        // Array to store the minimum distance from S to each node
        int[] dist = new int[points.size()];
        Arrays.fill(dist, Integer.MAX_VALUE); // Initialize all distances to infinity
        dist[0] = 0; // Distance to source S is 0
        pq.offer(new int[]{0, 0}); // Add source S to the priority queue

        // Process nodes in the priority queue
        while (!pq.isEmpty()) {
            int[] current = pq.poll(); // Get the node with the smallest distance
            int node = current[0], d = current[1]; // Extract node index and current distance

            // Skip if the distance is already greater than the recorded distance
            if (d > dist[node]) continue;

            Point currentPoint = points.get(node); // Get the current point from the list

            // Check direct connections to all other points
            for (int i = 0; i < points.size(); i++) {
                if (i == node) continue; // Skip the current node itself
                // Calculate Manhattan distance between current node and other node
                int distance = Math.abs(currentPoint.x - points.get(i).x) + Math.abs(currentPoint.y - points.get(i).y);
                // Update distance if a shorter path is found
                if (dist[node] + distance < dist[i]) {
                    dist[i] = dist[node] + distance; // Update the distance
                    pq.offer(new int[]{i, dist[i]}); // Add the updated node to the priority queue
                }
            }

            // Check wormhole connections if the current node is a wormhole endpoint
            if (node >= 2) { // Wormhole endpoints start from index 2
                int wormholeIndex = (node - 2) / 2; // Identify the wormhole index
                int entry = 2 + 2 * wormholeIndex; // Entry point of the wormhole
                int exit = 3 + 2 * wormholeIndex; // Exit point of the wormhole
                int wormholeTime = wormholes.get(wormholeIndex)[4]; // Travel time through the wormhole

                // Determine the target node (entry or exit) of the wormhole
                int target = (node == entry) ? exit : entry; // Connect to the other end of the wormhole
                // Update distance if using the wormhole provides a shorter path
                if (dist[node] + wormholeTime < dist[target]) {
                    dist[target] = dist[node] + wormholeTime; // Update the distance
                    pq.offer(new int[]{target, dist[target]}); // Add the updated node to the priority queue
                }
            }
        }

        return dist[1]; // Return the minimum distance to the destination D
    }


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Point S = new Point(scanner.nextInt(), scanner.nextInt());

        Point D = new Point(scanner.nextInt(), scanner.nextInt());

        int n = scanner.nextInt();
        List<int[]> wormholes = new ArrayList<>();

        for (int i = 0; i < n; i++) {
            wormholes.add(new int[]{scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt(), scanner.nextInt()});
        }

        System.out.println("Minimum time: " + findMinimumTime(S, D, wormholes));
    }
}
