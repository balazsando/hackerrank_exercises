package com.codecool.hackerrankExercise.evenTree;

import java.util.*;

public class Solution {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        int nodes = in.nextInt();
        int edges = in.nextInt();
        Graph graph = new Graph(nodes);
        for (int i = 0; i < edges; i++) {
            Node node1 = graph.nodes[in.nextInt()-1];
            Node node2 = graph.nodes[in.nextInt()-1];
            node2.children.add(node1);
        }
        System.out.println(graph.slicing());
    }
}

class Graph {
    Node[] nodes;

    Graph(int nodes) {
        this.nodes = new Node[nodes];
        for (int i = 0; i < nodes; i++) this.nodes[i] =new Node();
    }

    int slicing () {
        int slices = 0;
        for (Node node : nodes) {
            for (Node n : nodes) n.visited = false;
            for (Node child : node.children) if (bfs(child)) slices++;
        }
        return slices;
    }

    private boolean bfs(Node node) {
        int children = 0;
        Queue<Node> queue = new LinkedList<>(Arrays.asList(node));
        while(!queue.isEmpty()) {
            Node current = queue.remove();
            if(!current.visited) {
                current.visited = true;
                children++;
                for (Node neighbour : current.children) if (!neighbour.visited) queue.add(neighbour);
            }
        }
        return children % 2 == 0 && children != 0;
    }
}

class Node {
    List<Node> children;
    boolean visited;

    Node() {
        this.children = new ArrayList<>();
    }
}
