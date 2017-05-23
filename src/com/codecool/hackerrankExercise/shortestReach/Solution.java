package com.codecool.hackerrankExercise.shortestReach;

import java.util.*;

public class Solution {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int graphs = in.nextInt();
        for (int a0 = 0; a0 < graphs; a0++) {
            int nodes = in.nextInt();
            int edges = in.nextInt();
            Graph graph = new Graph(nodes);
            for (int a1 = 0; a1 < edges; a1++) {
                Integer node1 = in.nextInt()-1;
                Integer node2 = in.nextInt()-1;
                graph.nodes[node1].neighbours.add(node2);
                graph.nodes[node2].neighbours.add(node1);
            }
            int start = in.nextInt()-1;
            graph.bfs(start);
            StringBuilder sb = new StringBuilder();
            for (int i = 0; i < nodes; i++) {
                if(i != start) {
                    sb.append(graph.nodes[i].toString());
                    sb.append(i < nodes-1 ? " " : "");
                }
            }
            System.out.println(sb.toString());
        }
    }
}

class Graph {
    Node[] nodes;

    Graph (int nodes) {
        this.nodes = new Node[nodes];
        for (int i = 0; i < nodes; i++) this.nodes[i] = new Node();
    }

    void bfs (int start) {
        Queue<Node> visitedNodes = new LinkedList<>();
        nodes[start].distance = 0;
        visitedNodes.offer(nodes[start]);
        while (!visitedNodes.isEmpty()) {
            Node node = visitedNodes.poll();
            for (int neighbour : node.neighbours) {
                if (nodes[neighbour].distance < 0) {
                    nodes[neighbour].distance = node.distance + 6;
                    visitedNodes.offer(nodes[neighbour]);
                }
            }
        }
    }
}

class Node {
    List<Integer> neighbours;
    int distance = -1;

    Node() {
        this.neighbours = new ArrayList<>();
    }

    public String toString() {
        return String.valueOf(this.distance);
    }
}
