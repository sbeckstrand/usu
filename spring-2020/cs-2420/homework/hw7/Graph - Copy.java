import org.w3c.dom.ls.LSOutput;

import java.io.File;
import java.util.LinkedList;
import java.util.Scanner;

public class Graph {
    int vertexCt;  // Number of vertices in the graph.
    GraphNode[] G; // Adjacency list for graph.
    Integer[][] M; // Adjacency matrix
    Integer[][] R; // Residual matrix
    String graphName;  //The file from which the graph was created.

    public Graph() {
        this.vertexCt = 0;
        this.graphName = "";
    }

    public static void main(String[] args) {
        Graph graph1 = new Graph();
        Graph graph2 = new Graph();
        Graph graph3 = new Graph();
        Graph graph4 = new Graph();
        Graph graph5 = new Graph();
        Graph graph6 = new Graph();

        System.out.println("demands1.txt");
        graph1.makeGraph("demands1.txt");
        graph1.maxFlow(graph1.M);

        System.out.println("demands2.txt");
        graph2.makeGraph("demands2.txt");
        graph2.maxFlow(graph2.M);

        System.out.println("demands3.txt");
        graph3.makeGraph("demands3.txt");
        graph3.maxFlow(graph3.M);

        System.out.println("demands4.txt");
        graph4.makeGraph("demands4.txt");
        graph4.maxFlow(graph4.M);

        System.out.println("demands5.txt");
        graph5.makeGraph("demands5.txt");
        graph5.maxFlow(graph5.M);

        System.out.println("demands6.txt");
        graph6.makeGraph("demands6.txt");
        graph6.maxFlow(graph6.M);

    }

    public int getVertexCt() {
        return vertexCt;
    }

    public boolean addEdge(int source, int destination, int cap) {
        //System.out.println("addEdge " + source + "->" + destination + "(" + cap + ")");
        if (source < 0 || source >= vertexCt) return false;
        if (destination < 0 || destination >= vertexCt) return false;
        //add edge
        G[source].addEdge(source, destination, cap);
        return true;
    }

    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("The Graph ").append(graphName).append(" \n");

        for (int i = 0; i < vertexCt; i++) {
            sb.append(G[i].toString());
        }
        return sb.toString();
    }

    // Method to build our graph from text file wth graph data
    public void makeGraph(String filename) {
        try {
            graphName = filename;
            Scanner reader = new Scanner(new File(filename));
            vertexCt = reader.nextInt();
            G = new GraphNode[vertexCt];
            for (int i = 0; i < vertexCt; i++) {
                G[i] = new GraphNode(i);
            }
            while (reader.hasNextInt()) {
                int v1 = reader.nextInt();
                int v2 = reader.nextInt();
                int cap = reader.nextInt();
                if (!addEdge(v1, v2, cap))
                    throw new Exception();
            }
            reader.close();
        } catch (Exception e) {
            e.printStackTrace();
        }

        M = buildMatrix();
    }

    // Method to build a matrix.
    public Integer[][] buildMatrix() {
        Integer[][] matrix = new Integer[G.length][G.length];
        for (int i = 0; i < G.length; i++) {
            for (int j = 0; j < G.length; j++) {
                GraphNode.EdgeInfo edge = findEdge(G[i], G[j]);

                if (edge == null) {
                    matrix[i][j] = 0;
                }
                else {
                    matrix[i][j] = edge.capacity;
                }
            }
        }

        return matrix;
    }

    // Find if there is an edge between two nodes, if so, return that edge.
    private GraphNode.EdgeInfo findEdge(GraphNode a, GraphNode b) {
        GraphNode.EdgeInfo edge = null;
        for (GraphNode.EdgeInfo e : a.succ) {
            if (e.to == b.nodeID) {
                edge = e;
            }
        }

        return edge;
    }

    // Set our Nodes to show they are unvisted.
    private void resetVisited() {
        for (GraphNode node: G) {
            node.visited = false;
        }
    }

    // Method to perform a breadth first search to determine which nodes have a path to each other.
    boolean pathSearch(Integer[][] residualGraph, int[] parent) {
        LinkedList<Integer> queue = new LinkedList<Integer>();
        GraphNode start = G[0];
        queue.add(start.nodeID);
        G[0].visited = true;
        parent[start.nodeID]=-1;

        while (!queue.isEmpty()) {
            int current = queue.poll();
            for (int i=0; i<vertexCt; i++)
            {
                if (!G[i].visited && residualGraph[current][i] > 0)
                {
                    queue.add(i);
                    parent[i] = current;
                    G[i].visited = true;
                }
            }
        }

        if (G[vertexCt -1].visited) {
            resetVisited();
            return true;
        } else {
            return false;
        }
    }

    // Search to check which nodes can visit each other when considering a residual graph
    private void vistedSearch(int startIndex) {
        GraphNode start = G[startIndex];
        start.visited = true;


        for (int i = 0; i < vertexCt; i++) {
            if (R[start.nodeID][i] > 0 && !G[i].visited) {
                vistedSearch(i);
            }
        }
    }

    // Method to find which path results in the maxiumum amount of flow and outputs these flows until we cannot reach the destination from the start anymore.
    private void maxFlow(Integer[][] graph) {

        int[] parent = new int[vertexCt];
        int start = G[0].nodeID;
        int end = G[vertexCt -1].nodeID;
        int x, y;
        Integer[][] residualGraph = buildMatrix();
        int maxFlow = 0;



        while (pathSearch(residualGraph, parent)) {
            StringBuilder path = new StringBuilder();

            int flow = 100000;
            for (y=end; y!=start; y=parent[y])
            {
                x = parent[y];
                path.append(y).append(" ");

                flow = Math.min(flow, residualGraph[x][y]);

            }

            for (y=end; y != start; y=parent[y])
            {
                x = parent[y];
                residualGraph[x][y] -= flow;
                residualGraph[y][x] += flow;
            }

            maxFlow += flow;

            path.append("0").reverse();
            System.out.println("Found flow " + flow + ": " + path);

        }

        // Build a redidual graph to be used to check for minCut
        R = new Integer[vertexCt][vertexCt];
        for (int i = 0; i < vertexCt; i++) {
            for (int j = 0; j < vertexCt; j++) {
                R[i][j] = residualGraph[i][j];
            }
        }

        //Output the flow that is transported over our edges.
        System.out.println("Produced: " + maxFlow);
        System.out.println( );
        for (int i = 0; i < vertexCt; i++) {
            for (int j = 0; j < vertexCt; j++) {
                if (M[i][j] != 0) {
                    System.out.println("Edge (" + i + "," + j + ") transports " + M[i][j] + " cases");
                }
            }
        }
        System.out.println();

        // Check which nodes in our residual graph have flow to visit each other and then isolate which nodes are in groups that can connect to each other. The edges where there is no more flow and isolate the subsets are our minCut. Print the details of the edges.
        vistedSearch(0);
        System.out.println("Min Cut:");
        for (int i = 0; i < vertexCt; i++) {
            for (int j = 0; j < vertexCt; j++) {
                if (R[i][j] == 0 && G[i].visited && !G[j].visited) {
                    for (GraphNode.EdgeInfo edge : G[i].succ) {
                        if (edge.to == j) {

                            System.out.println("Edge (" + i + "," + j + ") transports " + edge.capacity + " cases");
                        }
                    }

                }
            }
        }
        System.out.println();
    }


}