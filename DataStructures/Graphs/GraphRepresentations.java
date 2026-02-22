package DataStructures.Graphs;

import java.util.*;

/*
===========================================================
Graph Representations
===========================================================

1) Adjacency Matrix  - 2D array, O(V^2) space
2) Adjacency List    - Array of lists, O(V+E) space
3) Edge List         - List of edges

Both directed and undirected graphs.

===========================================================
*/

public class GraphRepresentations {

    // =====================================================
    // 1️⃣ ADJACENCY MATRIX
    // =====================================================
    static class AdjacencyMatrix {
        private int[][] matrix;
        private int vertices;

        AdjacencyMatrix(int vertices) {
            this.vertices = vertices;
            this.matrix = new int[vertices][vertices];
        }

        // Add undirected edge
        public void addEdge(int u, int v) {
            matrix[u][v] = 1;
            matrix[v][u] = 1;
        }

        // Add directed edge
        public void addDirectedEdge(int u, int v) { matrix[u][v] = 1; }

        // Add weighted edge
        public void addWeightedEdge(int u, int v, int weight) {
            matrix[u][v] = weight;
            matrix[v][u] = weight;
        }

        public boolean hasEdge(int u, int v) { return matrix[u][v] != 0; }

        public List<Integer> getNeighbors(int u) {
            List<Integer> neighbors = new ArrayList<>();
            for(int v = 0; v < vertices; v++) {
                if(matrix[u][v] != 0) neighbors.add(v);
            }
            return neighbors;
        }

        public void print() {
            System.out.println("Adjacency Matrix:");
            for(int[] row : matrix) System.out.println("  " + Arrays.toString(row));
        }
    }

    // =====================================================
    // 2️⃣ ADJACENCY LIST
    // =====================================================
    static class AdjacencyList {
        private List<List<Integer>> adjList;
        private int vertices;

        AdjacencyList(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList<>();
            for(int i = 0; i < vertices; i++) adjList.add(new ArrayList<>());
        }

        public void addEdge(int u, int v) {
            adjList.get(u).add(v);
            adjList.get(v).add(u);
        }

        public void addDirectedEdge(int u, int v) { adjList.get(u).add(v); }

        public boolean hasEdge(int u, int v) { return adjList.get(u).contains(v); }

        public List<Integer> getNeighbors(int u) { return adjList.get(u); }

        public int degree(int u) { return adjList.get(u).size(); }

        public void print() {
            System.out.println("Adjacency List:");
            for(int i = 0; i < vertices; i++) {
                System.out.println("  " + i + " -> " + adjList.get(i));
            }
        }
    }

    // =====================================================
    // 3️⃣ WEIGHTED ADJACENCY LIST
    // =====================================================
    static class WeightedAdjacencyList {
        private List<List<int[]>> adjList; // [neighbor, weight]
        private int vertices;

        WeightedAdjacencyList(int vertices) {
            this.vertices = vertices;
            adjList = new ArrayList<>();
            for(int i = 0; i < vertices; i++) adjList.add(new ArrayList<>());
        }

        public void addEdge(int u, int v, int weight) {
            adjList.get(u).add(new int[]{v, weight});
            adjList.get(v).add(new int[]{u, weight});
        }

        public void addDirectedEdge(int u, int v, int weight) {
            adjList.get(u).add(new int[]{v, weight});
        }

        public List<int[]> getNeighbors(int u) { return adjList.get(u); }

        public void print() {
            System.out.println("Weighted Adjacency List:");
            for(int i = 0; i < vertices; i++) {
                System.out.print("  " + i + " -> ");
                for(int[] edge : adjList.get(i)) {
                    System.out.print("[" + edge[0] + ",w=" + edge[1] + "] ");
                }
                System.out.println();
            }
        }
    }

    // =====================================================
    // 4️⃣ EDGE LIST
    // =====================================================
    static class EdgeList {
        static class Edge {
            int u, v, weight;
            Edge(int u, int v, int weight) { this.u=u; this.v=v; this.weight=weight; }
        }

        private List<Edge> edges = new ArrayList<>();
        private int vertices;

        EdgeList(int vertices) { this.vertices = vertices; }

        public void addEdge(int u, int v, int weight) { edges.add(new Edge(u, v, weight)); }

        public List<Edge> getEdges() { return edges; }

        public void print() {
            System.out.println("Edge List:");
            for(Edge e : edges) System.out.println("  " + e.u + " --" + e.weight + "--> " + e.v);
        }
    }

    // =====================================================
    // MAIN METHOD
    // =====================================================
    public static void main(String[] args) {
        System.out.println("=== Adjacency Matrix (5 vertices) ===");
        AdjacencyMatrix am = new AdjacencyMatrix(5);
        am.addEdge(0,1); am.addEdge(0,4); am.addEdge(1,2);
        am.addEdge(1,3); am.addEdge(1,4); am.addEdge(2,3); am.addEdge(3,4);
        am.print();
        System.out.println("Neighbors of 1: " + am.getNeighbors(1));
        System.out.println("Has edge 0-4: " + am.hasEdge(0,4));

        System.out.println("\n=== Adjacency List (5 vertices) ===");
        AdjacencyList al = new AdjacencyList(5);
        al.addEdge(0,1); al.addEdge(0,4); al.addEdge(1,2);
        al.addEdge(1,3); al.addEdge(1,4); al.addEdge(2,3); al.addEdge(3,4);
        al.print();
        System.out.println("Degree of 1: " + al.degree(1));

        System.out.println("\n=== Weighted Adjacency List ===");
        WeightedAdjacencyList wal = new WeightedAdjacencyList(4);
        wal.addEdge(0,1,4); wal.addEdge(0,2,1);
        wal.addEdge(2,1,2); wal.addEdge(1,3,1); wal.addEdge(2,3,5);
        wal.print();

        System.out.println("\n=== Edge List ===");
        EdgeList el = new EdgeList(4);
        el.addEdge(0,1,4); el.addEdge(0,2,1); el.addEdge(2,1,2);
        el.addEdge(1,3,1); el.addEdge(2,3,5);
        el.print();
    }
}

/*
===========================================================
Time & Space Complexity Summary
===========================================================

                  | Space    | addEdge | hasEdge | getNeighbors
Adjacency Matrix  | O(V^2)   | O(1)    | O(1)    | O(V)
Adjacency List    | O(V+E)   | O(1)    | O(deg)  | O(1)
Edge List         | O(E)     | O(1)    | O(E)    | O(E)

Use Matrix when: Dense graph, fast edge lookup needed
Use List when:   Sparse graph (most real-world graphs)
Use Edge List when: Sorting edges (Kruskal's algorithm)

===========================================================
*/
