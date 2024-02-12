package com.example.myapplication.graph;

import org.junit.Test;

import java.util.LinkedList;

public class BFS {
    @Test
    public void bfsTest() {
        BFSGraph g = new BFSGraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        System.out.println(
                "Breadth First Traversal (starting from vertex 2)");

        g.bfs(2);
    }
}

class BFSGraph {
    // number of vertexes
    private int v;
    private final LinkedList<Integer>[] adjacent;

    BFSGraph(int v) {
        this.v = v;
        adjacent = new LinkedList[v];
        for (int i = 0; i < v; ++i)
            adjacent[i] = new LinkedList();
    }

    void addEdge(int v, int w) {
        adjacent[v].add(w);
    }

    void bfs(int root) {
        boolean[] visited = new boolean[v];
        LinkedList<Integer> queue = new LinkedList<>();
        visited[root] = true;
        queue.add(root);
        while (queue.size() != 0) {
            int current = queue.poll();
            System.out.print(current + " ");
            for (int adj : adjacent[current]) {
                if (!visited[adj]) {
                    queue.add(adj);
                    visited[adj] = true;
                }
            }
        }
    }

}
