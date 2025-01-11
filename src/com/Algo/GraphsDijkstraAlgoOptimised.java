package com.Algo;

import java.util.*;

public class GraphsDijkstraAlgoOptimised {
    static int V;
    static List<List<iPair>> adjacenctList = new ArrayList<>();

    GraphsDijkstraAlgoOptimised(int V) {
        this.V = V;
        for(int i=0; i<V;i++) {
            adjacenctList.add(new ArrayList<>());
        }
    }

    static void addEdge(int u, int v, int w) {
        adjacenctList.get(u).add(new iPair(v,w));
        adjacenctList.get(v).add(new iPair(u,w));
    }

    static class iPair {
        int first, second;
        iPair(int first, int second) {
            this.first = first;
            this.second = second;
        }
    }

    static void shortestPath(int src){
        PriorityQueue<iPair> pq = new PriorityQueue<>(V, Comparator.comparing(o -> o.second));
        int[] dist = new int[V];
        for(int i=0;i<V;i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        pq.add(new iPair(0, src));
        dist[0] = 0;

        while(!pq.isEmpty()) {
            int u = pq.poll().first;
            for(iPair pair : adjacenctList.get(u)) {
                int v = pair.first;
                int weight = pair.second;
                if(dist[v] > dist[u] + weight) {
                    dist[v] = dist[u] + weight;
                    pq.add(new iPair(v, dist[v]));
                }
            }
        }
        System.out.println("Vertex Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }

    }
    public static void main(String[] args) {
        int V = 9;
        GraphsDijkstraAlgoOptimised g = new GraphsDijkstraAlgoOptimised(V);

        g.addEdge(0, 1, 4);
        g.addEdge(0, 7, 8);
        g.addEdge(1, 2, 8);
        g.addEdge(1, 7, 11);
        g.addEdge(2, 3, 7);
        g.addEdge(2, 8, 2);
        g.addEdge(2, 5, 4);
        g.addEdge(3, 4, 9);
        g.addEdge(3, 5, 14);
        g.addEdge(4, 5, 10);
        g.addEdge(5, 6, 2);
        g.addEdge(6, 7, 1);
        g.addEdge(6, 8, 6);
        g.addEdge(7, 8, 7);

        g.shortestPath(0);

//        Scanner s = new Scanner(System.in);
//        int v = s.nextInt(); // vertices
//        int e = s.nextInt(); // edges
//        int adjacencyMatrix[][] = new int[v][v];
//        for(int i =0;i<e;i++){
//            int v1 = s.nextInt();
//            int v2 = s.nextInt();
//            int weight = s.nextInt();
//            adjacencyMatrix[v1][v2] = weight;
//            adjacencyMatrix[v2][v1] = weight;
//        }
//        dijkstraPriorityQueue(adjacencyMatrix);
        /* input
        5 7
        0 1 4
        0 2 8
        1 3 5
        1 2 2
        2 3 5
        2 4 9
        3 4 4
        */
    }
}
